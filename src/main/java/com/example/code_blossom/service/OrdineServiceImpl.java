package com.example.code_blossom.service;

import com.example.code_blossom.dao.OrdineDao;
import com.example.code_blossom.model.Ordine;
import com.example.code_blossom.model.Prodotto;
import com.example.code_blossom.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class OrdineServiceImpl implements OrdineService{

    @Autowired
    private OrdineDao ordineDao;

    @Autowired
    private ProdottoService prodottoService;


    @Override
    public void inoltroOrdine(HttpSession session) {
        // ottenimento carrelllo e cliente loggato
        List<Prodotto> carrello = prodottoService.carrelloUtente(session);
        Utente utente = (Utente) session.getAttribute("utente");
        //verifica validità carrello e utente
        if(carrello != null && utente != null){
            //creazione oggetto ordine
            Ordine ordine = new Ordine();
            //impostazione data
            ordine.setDataOraOrdine(LocalDateTime.now());
            //impostiamo la data di consegna
            ordine.setDataConsegnaPrevista(ordine.getDataOraOrdine().toLocalDate().plusDays(3));
            //impostazione costo fisso di consegna, 5 euro
            ordine.setCostoConsegna(5);
            //imposazione importo
            ordine.setPrezzoTotale(prodottoService.totaleCarrello(session));
            //impostazione Utente associato a Ordine
            ordine.setUtente(utente);
            //impostazione lista Libri
            ordine.setProdotti(carrello);
            //passaggio ordine a componente DAO per inserimento
            ordineDao.save(ordine);
            //eliminazione carrello diventato ordine
            session.removeAttribute("carrello");
        }
    }
}
