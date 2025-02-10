package com.example.code_blossom.service;

import com.example.code_blossom.dao.ProdottoDao;
import com.example.code_blossom.model.Prodotto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdottoServiceImpl implements ProdottoService {

    @Autowired
    private ProdottoDao prodottoDao;

    @Override
    public Prodotto datiProdotto(int id) {
        Optional<Prodotto> prodottoOptional = prodottoDao.findById(id);
        if (prodottoOptional.isPresent())
            return prodottoOptional.get();
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
            carrello.add(prodotto);
            session.setAttribute("carrello", carrello);
            return true;
        } else {
            carrello = new ArrayList<>();
            carrello.add(prodotto);
            session.setAttribute("carrello", carrello);
            return true;
        }
    }

    @Override
    public void rimozioneCarrello(int idProdotto, HttpSession session) {

        //otteniamo la certezza di avere un carrello
        List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");
        //rimozione prodotto dal carrello
        for(Prodotto prodotto : carrello)
            if(prodotto.getId() == idProdotto){
                carrello.remove(prodotto);
                break;
            }
        if(carrello.size() > 0 )//abbiamo qualcosa e sovrascriviamo
            session.setAttribute("carrello", carrello);
        else session.removeAttribute("carrello");
    }

    @Override
    public double totaleCarrello(HttpSession session) {

        List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");
        if(carrello != null){
            double totale = 0;
            for(Prodotto prodotto : carrello)
                totale += prodotto.getPrezzo();
            return (totale + 5) ;
        }
        return 0;
    }

    @Override
    public List<Prodotto> carrelloUtente(HttpSession session) {
        if(session.getAttribute("carrello") != null)
            return (List<Prodotto>) session.getAttribute("carrello");
        return null;
    }

}


