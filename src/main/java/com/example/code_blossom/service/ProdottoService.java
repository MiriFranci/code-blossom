package com.example.code_blossom.service;

import com.example.code_blossom.model.Prodotto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProdottoService {

    Prodotto datiProdotto(int id);
    List<Prodotto> elencoProdotti();
    boolean aggiungiAlCarrello(int idProdotto, HttpSession session);
    void rimozioneCarrello(int idProdotto, HttpSession session);
    void totaleCarrello(HttpSession session);

}
