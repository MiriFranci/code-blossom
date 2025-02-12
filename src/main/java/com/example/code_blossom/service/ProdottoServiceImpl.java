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
        Prodotto prodotto = datiProdotto(idProdotto); // Recupera il prodotto dal database o da un'altra fonte
        List<Prodotto> carrello;

        // Controlliamo se esiste già un carrello nella sessione
        if (session.getAttribute("carrello") != null) {
            carrello = (List<Prodotto>) session.getAttribute("carrello"); // Se il carrello esiste, lo recuperiamo dalla sessione
        } else {
            carrello = new ArrayList<>(); // Se il carrello non esiste, creiamo una nuova lista di prodotti
        }
        carrello.add(prodotto); // Aggiungi il prodotto al carrello

        // Aggiorna il contatore del carrello
        int carrelloCount = carrello.size();
        session.setAttribute("carrelloCount", carrelloCount); // Aggiorna il contatore nella sessione
        session.setAttribute("carrello", carrello); // Aggiorna il carrello nella sessione

        return true; // Restituisci true per indicare che l'operazione è riuscita
    }

    @Override
    public void rimozioneCarrello(int idProdotto, HttpSession session) {
        // recuperiamo il carrello dalla sessione (se esiste)
        List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");

        // cicliamo tra i prodotti nel carrello per trovare quello da rimuovere
        for(Prodotto prodotto : carrello)
            if(prodotto.getId() == idProdotto){
                carrello.remove(prodotto);
                break;
            }

        // Aggiorniamo il contatore del carrello
        int carrelloCount = carrello.size();
        session.setAttribute("carrelloCount", carrelloCount); // Aggiorna il contatore nella sessione

        // se ci sono ancora prodotti nel carrello, aggiorniamo la sessione
        if (carrello.size() > 0) {
            session.setAttribute("carrello", carrello);
        } else {
            // Altrimenti, se il carrello è vuoto, rimuoviamo l'attributo dalla sessione
            session.removeAttribute("carrello");
            session.removeAttribute("carrelloCount"); // Rimuovi anche il contatore
        }
        System.out.println("Prodotto rimosso! cartCount attuale: " + carrelloCount);
    }

    @Override
    public double totaleCarrello(HttpSession session) {
        // recuperiamo il carrello dalla sessione (se esiste)
        List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");
        if(carrello != null){
            double totale = 0;

            // cicliamo attraverso i prodotti nel carrello e sommiamo i prezzi
            for(Prodotto prodotto : carrello)
                totale += prodotto.getPrezzo();
            // aggiungiamo 5 € di spedizione al prezzo totale
            return (totale + 5) ;
        }
        // se il carrello è vuoto o non esiste, ritorniamo 0
        return 0;
    }

    @Override
    public List<Prodotto> carrelloUtente(HttpSession session) {
        // verifica se esiste un carrello nella sessione dell'utente
        if(session.getAttribute("carrello") != null)
            return (List<Prodotto>) session.getAttribute("carrello"); // se il carrello esiste, lo recuperiamo e lo ritorniamo
        return null;
    }
}


