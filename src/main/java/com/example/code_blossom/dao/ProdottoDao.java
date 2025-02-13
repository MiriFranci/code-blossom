package com.example.code_blossom.dao;

import com.example.code_blossom.model.Prodotto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProdottoDao extends CrudRepository<Prodotto, Integer>{
    List<Prodotto> findByCategoriaId(int idCategoria);
    List<Prodotto> findTop6ByOrderByNomeAscPrezzoDesc();
    List<Prodotto> findByNomeContainingIgnoreCase(String ricerca);
}
