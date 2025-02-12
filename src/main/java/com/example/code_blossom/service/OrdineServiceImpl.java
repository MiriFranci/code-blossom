package com.example.code_blossom.service;

import com.example.code_blossom.dao.OrdineDao;
import com.example.code_blossom.model.Ordine;
import com.example.code_blossom.model.Prodotto;
import com.example.code_blossom.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrdineServiceImpl implements OrdineService{

    @Autowired
    private OrdineDao ordineDao;

    @Autowired
    private ProdottoService prodottoService;

    @Override
    public void inoltroOrdine(HttpSession session, String destinazione, String note) {
        // ottenimento carrello e dell' utente loggato
        List<Prodotto> carrello = prodottoService.carrelloUtente(session);
        Utente utente = (Utente) session.getAttribute("utente");

        // verifica validità carrello e utente
        if(carrello != null && utente != null){
            // creazione oggetto ordine
            Ordine ordine = new Ordine();
            // impostazione data e ora dell'ordine
            ordine.setDataOraOrdine(LocalDateTime.now());
            // impostazione data di consegna
            ordine.setDataConsegnaPrevista(ordine.getDataOraOrdine().toLocalDate().plusDays(3));
            // impostazione costo fisso di consegna, 5 €
            ordine.setCostoConsegna(5);
            // impostazione prezzo totale
            ordine.setPrezzoTotale(prodottoService.totaleCarrello(session));
            // impostazione Utente associato a Ordine
            ordine.setUtente(utente);
            // impostazione lista dei Prodotti
            ordine.setProdotti(carrello);
            // impostazione destinazione e note
            ordine.setDestinazione(destinazione);
            ordine.setNote(note);
            // passaggio ordine a componente DAO per l'inserimento del record nella tabella "ordini"
            ordineDao.save(ordine);
            // il carrello viene svuotato, dopo aver effettuato l'ordine
            session.removeAttribute("carrello");
            // anche il contatore del carrello
            session.removeAttribute("carrelloCount");
        }
    }
}
