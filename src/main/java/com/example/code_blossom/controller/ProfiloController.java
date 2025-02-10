package com.example.code_blossom.controller;

import com.example.code_blossom.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/profilo")
public class ProfiloController {

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
}