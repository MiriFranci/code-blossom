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
        return "index";
    }

    @GetMapping("/punti-vendita")
    public String puntiVendita() {
        return "punti_vendita";
    }

    @GetMapping("/contattaci")
    public String contattaci() {
        return "contattaci";
    }

    @GetMapping("/lavora-con-noi")
    public String lavoraConNoi() {
        return "lavora_con_noi";
    }

    @GetMapping("/composizioni")
    public String composizioni() {
        return "composizioni";
    }

    @GetMapping("/fiori-sfusi")
    public String fioriSfusi() {
        return "fiori_sfusi";
    }

    @GetMapping("/piante")
    public String piante() {
        return "piante";
    }

    @GetMapping("/registrati")
    public String registrati() {
        return "registrati";
    }
}
