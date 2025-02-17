package com.example.code_blossom.service;

import com.example.code_blossom.model.Prodotto;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface ProdottoService {
    Prodotto datiProdotto(int id);
    List<Prodotto> elencoProdotti();
    List<Prodotto> elencoSeiProdotti();
    boolean aggiungiAlCarrello(int idProdotto, HttpSession session);
    void rimozioneCarrello(int idProdotto, HttpSession session);
    double totaleCarrello(HttpSession session);
    List<Prodotto> carrelloUtente(HttpSession session);
    List<Prodotto> trovaProdottiPerCategoriaId(int idCategoria);
    List<Prodotto> cercaPerNome(String ricerca);
    List<Prodotto> trovaPerId(Iterable<Integer> ids);
}