package com.example.code_blossom.controller;

import com.example.code_blossom.model.Prodotto;
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
@RequestMapping("/prodotti/categoria")
public class CategoriaController {

    @Autowired
    private ProdottoService prodottoService;

    @GetMapping
    public String getPage(@RequestParam int id, HttpSession session, Model model){
        List<Prodotto> prodotti = prodottoService.trovaProdottiPerCategoriaId(id);
        model.addAttribute("prodotti", prodotti);

        if (id == 1)
            return "fiori_sfusi";
        if (id==2)
            return "composizioni";
        else
            return "piante";
    }
}
