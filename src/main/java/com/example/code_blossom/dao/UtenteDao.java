package com.example.code_blossom.dao;

import com.example.code_blossom.model.Utente;
import org.springframework.data.repository.CrudRepository;

public interface UtenteDao extends CrudRepository<Utente, Integer> {
    Utente findByUsernameAndPassword(String username, String password);
    Utente findByUsername(String username);
}
