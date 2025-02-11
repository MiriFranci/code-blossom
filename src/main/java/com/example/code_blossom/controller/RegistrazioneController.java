package com.example.code_blossom.controller;

import com.example.code_blossom.model.Utente;
import com.example.code_blossom.service.UtenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.Period;

@Controller
@RequestMapping("/registrazione")
public class RegistrazioneController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public String getPage(Model model) {
        Utente utente = new Utente();
        model.addAttribute("utente", utente);
        return "provaregistrazione";
    }

    @PostMapping
    public String formManager(
            @Valid @ModelAttribute Utente utente,
            BindingResult result,
            Model model, RedirectAttributes redirectAttributes) {

        if(result.hasErrors())
            return "provaregistrazione";

        if(!utenteService.controlloUsername(utente.getUsername())) {
            model.addAttribute("duplicato", "Username Occupato");
            return "provaregistrazione";
        }

        if (Period.between(utente.getDataDiNascita(), LocalDate.now()).getYears() < 16) {
            model.addAttribute("etaMinima", "Devi avere almeno 16 anni");
            return "provaregistrazione";
        }
        utenteService.registrazioneUtente(utente);
        redirectAttributes.addFlashAttribute("successMessage", "Registrazione avvenuta con successo! Ora puoi accedere.");
        return "redirect:/login";
    }
}