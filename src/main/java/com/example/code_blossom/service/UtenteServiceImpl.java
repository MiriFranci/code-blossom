package com.example.code_blossom.service;

import com.example.code_blossom.dao.UtenteDao;
import com.example.code_blossom.model.Ordine;
import com.example.code_blossom.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UtenteServiceImpl implements UtenteService{

    @Autowired
     private UtenteDao utenteDao;

    @Override
    public Utente datiUtente(int id) {
        return utenteDao.findById(id).get();
    }

    @Override
    public boolean loginUtente(String username, String password, HttpSession session) {
        Utente utente = utenteDao.findByUsernameAndPassword(username, password);
        if(utente != null){
            session.setAttribute("utente", utente);
            return true;
        }
        return false;
    }

    @Override
    public void registrazioneUtente(Utente utente) {
        if(utente.getDataRegistrazione() == null)
           utente.setDataRegistrazione(LocalDate.now());
        utenteDao.save(utente);
    }

    @Override
    public boolean controlloUsername(String username) {
        return utenteDao.findByUsername(username) == null;
    }

}
