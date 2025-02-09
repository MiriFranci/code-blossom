package com.example.code_blossom.controller;

import com.example.code_blossom.model.Prodotto;
import com.example.code_blossom.model.Utente;
import com.example.code_blossom.service.ProdottoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private ProdottoService prodottoService;

    @GetMapping
    public String getPage(HttpSession session, Model model){
        if(session.getAttribute("utente") != null) {
            Utente utente = (Utente) session.getAttribute("utente");
            model.addAttribute("utente", utente);
        }
        List<Prodotto> prodotti = prodottoService.elencoProdotti();
        model.addAttribute("prodotti", prodotti);
        return "provaindex";
    }
}
