package com.example.code_blossom.service;

import com.example.code_blossom.dao.UtenteDao;
import com.example.code_blossom.model.Ordine;
import com.example.code_blossom.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UtenteServiceImpl implements UtenteService{

    @Autowired
     private UtenteDao utenteDao;

    @Override
    public boolean loginUtente(String username, String password, HttpSession session) {
        Utente utente = utenteDao.findByUsernameAndPassword(username, password);
        if(utente != null){
            session.setAttribute("utente", utente);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void registrazioneUtente(Utente utente) {
        utenteDao.save(utente);

    }

    //da chiedere al prof perchè nell'implementazione del metodo in UtenteDao i lmetodo restitutisce un oggetto e invece qui restituisce un boolean
    @Override
    public boolean controlloUsername(String username) {
        return utenteDao.findByUsername(username) == null;
    }






}
