package com.example.code_blossom.controller;

import com.example.code_blossom.model.Ordine;
import com.example.code_blossom.model.Prodotto;
import com.example.code_blossom.model.Utente;
import com.example.code_blossom.service.OrdineService;
import com.example.code_blossom.service.ProdottoService;
import com.example.code_blossom.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/carrello")
public class CarrelloController {

    @Autowired
    private ProdottoService prodottoService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private OrdineService ordineService;

    @GetMapping
    public String getPage(HttpSession session,
                          Model model,
                          @RequestParam(required = false) String send) {
        if (session.getAttribute("utente") == null)
            return "redirect:/login";

        Utente utente = (Utente) session.getAttribute("utente");
        List<Prodotto> carrello = prodottoService.carrelloUtente(session);
        double totaleCarrello = prodottoService.totaleCarrello(session);
        Ordine ordine = new Ordine();  // Crea un nuovo oggetto Ordine
        model.addAttribute("utente", utente);
        model.addAttribute("carrello", carrello);
        model.addAttribute("totale", totaleCarrello);
        model.addAttribute("send", send);
        model.addAttribute("ordine", ordine);  // Aggiungi l'oggetto Ordine al modello
        return "carrello";
    }


    @GetMapping("/aggiungi")
    public String aggiungiProdotto(@RequestParam int id,
                                   HttpSession session){
        if(!prodottoService.aggiungiAlCarrello(id, session))
            return "redirect:/carrello?add=no&id=" + id;
        return "redirect:/carrello?add=yes&id=" + id;
    }

    @GetMapping("/rimuovi")
    public String rimozioneProdotto(
            @RequestParam int id,
            HttpSession session){
        prodottoService.rimozioneCarrello(id,session);
        return "redirect:/carrello";
    }

    @PostMapping
    public String invioOrdine(@Valid @ModelAttribute("ordine") Ordine ordine,
                              BindingResult result,
                              HttpSession session,
                              Model model) {
        if (result.hasErrors()) {
            // se ci sono errori, ripristina gli attributi necessari nel modello
            Utente utente = (Utente) session.getAttribute("utente");
            List<Prodotto> carrello = prodottoService.carrelloUtente(session);
            double totaleCarrello = prodottoService.totaleCarrello(session);
            model.addAttribute("utente", utente);
            model.addAttribute("carrello", carrello);
            model.addAttribute("totale", totaleCarrello);
            return "carrello";  // Torna alla pagina del carrello con gli errori
        }

        // Inoltra l'ordine
        ordineService.inoltroOrdine(session, ordine.getDestinazione(), ordine.getNote());
        return "redirect:/carrello?send=yes";  // Successo, redirigi con il parametro send=yes
    }
}
