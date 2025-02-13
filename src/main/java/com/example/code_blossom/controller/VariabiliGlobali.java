package com.example.code_blossom.controller;

import com.example.code_blossom.model.Utente;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@ControllerAdvice
public class VariabiliGlobali {

    @ModelAttribute
    public void utenteLoggato(Model model, HttpSession session) {
        if(session.getAttribute("utente") != null) {
            Utente utente = (Utente) session.getAttribute("utente");
            model.addAttribute("utente", utente);
            model.addAttribute("cartCount", session.getAttribute("carrelloCount"));
        }
    }
}
