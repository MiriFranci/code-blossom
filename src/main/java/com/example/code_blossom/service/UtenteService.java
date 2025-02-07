package com.example.code_blossom.service;

import com.example.code_blossom.model.Ordine;
import com.example.code_blossom.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UtenteService {

    boolean loginUtente(String username, String password, HttpSession session);
    void registrazioneUtente(Utente utente);
    boolean controlloUsername(String username);

}
