package com.example.code_blossom.service;

import com.example.code_blossom.dao.ProdottoDao;
import com.example.code_blossom.model.Prodotto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdottoServiceImpl implements ProdottoService {

    @Autowired
    private ProdottoDao prodottoDao;

    @Override
    public Prodotto datiProdotto(int id) {
        return null;
    }

    @Override
    public List<Prodotto> elencoProdotti() {
        return (List<Prodotto>) prodottoDao.findAll();
    }

    @Override
    public boolean aggiungiAlCarrello(int idProdotto, HttpSession session) {
        Prodotto prodotto = datiProdotto(idProdotto);
        List<Prodotto> carrello;
        if (session.getAttribute("carrello") != null) {
            carrello = (List<Prodotto>) session.getAttribute("carrello");
            for (Prodotto p : carrello) {
                if (p.getId() == idProdotto) {
                    return false;
                }
            }
            carrello.add(prodotto);
            session.setAttribute("carrello", carrello);
            return true;
        } else
            carrello = new ArrayList<>();
        carrello.add(prodotto);
        session.setAttribute("carrello", carrello);
        return true;

    }

    @Override
    public void rimozioneCarrello(int idProdotto, HttpSession session) {

    }

    @Override
    public void totaleCarrello(HttpSession session) {

    }


}


