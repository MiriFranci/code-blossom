package com.example.code_blossom.controller;



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

    private OrdineService ordineService;

    public String getPage(HttpSession session,
                          Model model,
                          @RequestParam(required = false)
                          String send){
        if(session.getAttribute("utente") == null)
            return "redirect:/login";

        Utente utente = (Utente) session.getAttribute("utente");
        List<Prodotto> carrello = prodottoService.carrelloUtente(session);
        double totaleCarrello = prodottoService.totaleCarrello(session);
        model.addAttribute("utente", utente);
        model.addAttribute("carrello", carrello);
        model.addAttribute("totale", totaleCarrello);
        model.addAttribute("send", send);
        return "provacarrello";

    }

    @GetMapping("/rimuovi")
    public String rimozioneProdotto(
            @RequestParam int id,
            HttpSession session){
        prodottoService.rimozioneCarrello(id,session);
        return "redirect:/carrello";
    }

    @GetMapping("/invio")
    public String invioOrdine(HttpSession session){
        ordineService.inoltroOrdine(session);
        return "redirect:/carrello?send";
    }




}
