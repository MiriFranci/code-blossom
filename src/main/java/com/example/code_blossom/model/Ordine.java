package com.example.code_blossom.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ordini")
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private LocalDateTime dataOraOrdine;

    @Column
    private LocalDate dataConsegnaPrevista;

    @Pattern(regexp = "^[a-zA-Z0-9,\\s']+$", message = "La destinazione della spedizione può contenere solo lettere, numeri, virgole, trattini e apostrofi.")
    @Column
    private String destinazione;

    @Column
    private double costoConsegna;

    @Column
    private double prezzoTotale;

    @Size(max = 200, message = "Le note non possono superare i 200 caratteri.")
    @Column
    private String note;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_utente", referencedColumnName = "id")
    private Utente utente;

    @ManyToMany (cascade = CascadeType.REFRESH)
    @JoinTable(name = "dettaglio_ordini",
            joinColumns = @JoinColumn(name = "id_ordine", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_prodotto", referencedColumnName = "id"))
    private List<Prodotto> prodotti = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public LocalDateTime getDataOraOrdine() {
        return dataOraOrdine;
    }

    public void setDataOraOrdine(LocalDateTime dataOraOrdine) {
        this.dataOraOrdine = dataOraOrdine;
    }

    public LocalDate getDataConsegnaPrevista() {
        return dataConsegnaPrevista;
    }

    public void setDataConsegnaPrevista(LocalDate dataConsegnaPrevista) {
        this.dataConsegnaPrevista = dataConsegnaPrevista;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    public double getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    public double getCostoConsegna() {
        return costoConsegna;
    }

    public void setCostoConsegna(double costoConsegna) {
        this.costoConsegna = costoConsegna;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }
}
