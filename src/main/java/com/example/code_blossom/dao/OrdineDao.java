package com.example.code_blossom.dao;

import com.example.code_blossom.model.Prodotto;
import org.springframework.data.repository.CrudRepository;

public interface OrdineDao extends CrudRepository<Prodotto, Integer> {
}
