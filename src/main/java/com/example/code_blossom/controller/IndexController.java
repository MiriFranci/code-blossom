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
import org.springframework.web.bind.annotation.RequestParam;

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
            model.addAttribute("cartCount", session.getAttribute("carrelloCount"));
        }
        List<Prodotto> prodotti = prodottoService.elencoSeiProdotti();
        model.addAttribute("prodotti", prodotti);
        return "index";
    }

    @GetMapping("/cerca")
    public String cercaProdotti(@RequestParam("keyword") String keyword, Model model) {
        List<Prodotto> prodottiTrovati = prodottoService.cercaPerNome(keyword);
        model.addAttribute("prodottiTrovati", prodottiTrovati);
        return "risultati"; // Nome del template HTML che mostrerà i risultati
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
}
