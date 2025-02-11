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
        if (session.getAttribute("utente") == null) {
            return "redirect:/login";
        }
        // ottieni l'utente dalla sessione
        Utente utenteSessione = (Utente) session.getAttribute("utente");

        // se il model contiene già un utente (ad esempio, dopo un errore di validazione), usa quello
        if (model.containsAttribute("utente")) {
            Utente utenteModel = (Utente) model.getAttribute("utente"); // recuperare l'oggetto Utente dal model
            utenteModel.setOrdini(utenteService.datiUtente(utenteModel.getId()).getOrdini());  // aggiorna l'oggetto utenteModel con gli ordini associati all'utente
            model.addAttribute("utente", utenteModel);
        } else {
            // altrimenti, carica l'utente completo dal database
            Utente utenteCompleto = utenteService.datiUtente(utenteSessione.getId());
            model.addAttribute("utente", utenteCompleto);
        }
        return "provaprofilo";
    }

    @GetMapping("/logout")
    public String logoutUtente(HttpSession session) {
        session.removeAttribute("utente");
        return "redirect:/";
    }

    @PostMapping
    public String formManager(@Valid @ModelAttribute("utente") Utente utente,
                              BindingResult result,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        // verifica se ci sono errori di validazione
        if (result.hasErrors()) {
            // aggiungi gli errori e l'utente al modello per il redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.utente", result);
            redirectAttributes.addFlashAttribute("utente", utente);
            return "redirect:/profilo";
        }

        // se non ci sono errori, aggiorna l'utente nel database e nella sessione
        utenteService.registrazioneUtente(utente);
        session.setAttribute("utente", utente);
        return "redirect:/profilo";
    }
}