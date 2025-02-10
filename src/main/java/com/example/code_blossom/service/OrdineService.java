package com.example.code_blossom.service;

import com.example.code_blossom.model.Ordine;
import com.example.code_blossom.model.Utente;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface OrdineService {
    void inoltroOrdine(HttpSession session, String destinazione, String note);
}
