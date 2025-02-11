package com.example.code_blossom.service;

import com.example.code_blossom.model.Prodotto;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface ProdottoService {
    Prodotto datiProdotto(int id);
    List<Prodotto> elencoProdotti();
    boolean aggiungiAlCarrello(int idProdotto, HttpSession session);
    public void rimozioneCarrello(int idProdotto, HttpSession session);
    public double totaleCarrello(HttpSession session);
    List<Prodotto> carrelloUtente(HttpSession session);
}
