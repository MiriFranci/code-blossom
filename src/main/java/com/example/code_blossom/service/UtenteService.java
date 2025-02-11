package com.example.code_blossom.service;

import com.example.code_blossom.model.Utente;
import jakarta.servlet.http.HttpSession;

public interface UtenteService {
    Utente datiUtente(int id);
    boolean loginUtente(String username, String password, HttpSession session);
    void registrazioneUtente(Utente utente);
    boolean controlloUsername(String username);
}
