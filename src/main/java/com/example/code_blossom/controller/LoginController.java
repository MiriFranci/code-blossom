package com.example.code_blossom.controller;

import com.example.code_blossom.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public String getPage(
            @RequestParam(required = false) String errore,
            Model model,
            HttpSession session) {

        if(session.getAttribute("utente") != null)
            return "redirect:/profilo";
        model.addAttribute("errore", errore);
        return "provalogin";
    }

    @PostMapping
    public String formManager(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session) {

        if(!utenteService.loginUtente(username, password, session))
            return "redirect:/login?errore";
        return "redirect:/profilo";
    }
}
