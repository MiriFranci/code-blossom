package com.example.code_blossom.controller;

import com.example.code_blossom.model.Utente;
import com.example.code_blossom.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/profilo")
public class ProfiloController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public String getPage(HttpSession session, Model model) {
        if (session.getAttribute("utente") == null)
            return "redirect:/login";

        if (model.containsAttribute("utente")) { // verifica se l'attributo "utente" è presente nel Model dopo il redirect

            Utente utenteModel = (Utente) model.getAttribute("utente"); // è l'utente che contiene gli errori di validazione (quello passato con RedirectAttributes nel metodo formManager)
            Utente utenteCompleto = utenteService.datiUtente(utenteModel.getId()); //  è l'utente ricaricato dal database, quindi contiene gli ordini.
            utenteModel.setOrdini(utenteCompleto.getOrdini()); // Copiamo gli ordini da utenteCompleto a utenteModel, in modo che lo storico ordini sia visibile.
            model.addAttribute("utente", utenteModel);

        } else {
            // Se la pagina viene caricata normalmente, carichiamo tutto l'utente
            Utente utenteSessione = (Utente) session.getAttribute("utente");
            Utente utenteCompleto = utenteService.datiUtente(utenteSessione.getId());
            model.addAttribute("utente", utenteCompleto);
        }
        return "area_riservata";
    }


    @GetMapping ("/logout") //distruggiamo la sessione e poi ne creiamo un'altra futuro o distruggiamo l'attributo
    public String logoutUtente(HttpSession session){
        session.removeAttribute("utente");
        return "redirect:/";
    }

    @PostMapping
    public String formManager(@Valid @ModelAttribute Utente utente,
                              BindingResult result,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // Recuperiamo l'utente dalla sessione per mantenere gli ordini
            Utente utenteSessione = (Utente) session.getAttribute("utente");
            if (utenteSessione != null)
                utente.setOrdini(utenteSessione.getOrdini()); // Riporta gli ordini nel nuovo utente

            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.utente", result);
            redirectAttributes.addFlashAttribute("utente", utente);
            return "redirect:/profilo";
        }

        // Se non ci sono errori, aggiorniamo l'utente nel database e in sessione
        utenteService.registrazioneUtente(utente);
        session.setAttribute("utente", utente);
        redirectAttributes.addFlashAttribute("successMessage", "Il profilo è stato aggiornato con successo.");
        return "redirect:/profilo";
    }
}