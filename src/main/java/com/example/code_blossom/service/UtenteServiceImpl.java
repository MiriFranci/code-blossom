package com.example.code_blossom.service;

import com.example.code_blossom.dao.UtenteDao;
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
        // cerchiamo l'utente nel database tramite username e password
        Utente utente = utenteDao.findByUsernameAndPassword(username, password);

        if(utente != null){
            session.setAttribute("utente", utente); // se l'utente esiste, lo salviamo nella sessione per mantenere l'accesso
            return true;
        }
        return false;
    }

    @Override
    public void registrazioneUtente(Utente utente) {
        if(utente.getDataRegistrazione() == null)
           utente.setDataRegistrazione(LocalDate.now()); // se la data di registrazione è null, la impostiamo con la data odierna
        utenteDao.save(utente); // salviamo l'utente nel database
    }

    // verifica se un nome utente è già stato utilizzato o meno.
    // Se il risultato è true, significa che il nome utente è disponibile per una nuova registrazione
    @Override
    public boolean controlloUsername(String username) {
        return utenteDao.findByUsername(username) == null;
    }
}
