package com.example.code_blossom.controller;

import com.example.code_blossom.model.Ordine;
import com.example.code_blossom.model.Utente;
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
@RequestMapping("/profilo")
public class ProfiloController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public String getPage(
            HttpSession session, Model model) {
        if(session.getAttribute("utente") == null)
            return "redirect:/login";
        Utente utente = (Utente) session.getAttribute("utente");
        model.addAttribute("utente", utente);

        return "provaprofilo";
    }

    @GetMapping ("/logout") //distruggiamo la sessione e poi ne creiamo un'altra futuro o distruggiamo l'attributo
    public String logoutUtente(HttpSession session){
        session.removeAttribute("utente");
        return "redirect:/";
    }

    @PostMapping
    public String formManager(@Valid @ModelAttribute Utente utente,
                              BindingResult result,
                              HttpSession session){
        if(result.hasErrors())
            return "profilo";
        utenteService.registrazioneUtente(utente);
        session.setAttribute("utente", utente);
        return "redirect:/profilo";

    }

    public List<Ordine> storicoAcqusti(HttpSession session){

    }
}