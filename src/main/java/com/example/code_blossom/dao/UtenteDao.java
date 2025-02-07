package com.example.code_blossom.dao;

import com.example.code_blossom.model.Utente;
import org.springframework.data.repository.CrudRepository;

public interface UtenteDao extends CrudRepository<Utente, Integer> {

    Utente findByProfiloUsernameAndProfiloPassword(String username, String password);

    Utente findProfiloUsername(String username);
}
