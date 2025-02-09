package com.example.code_blossom.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "prodotti")
public class Prodotto {

    @Id
    private int id;

    @Column
    private String nome;

    @Column
    private String descrizione;

    @Column
    private double prezzo;

    @Column
    private String foto;

    @ManyToOne (cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    private Categoria categoria;

    @ManyToMany (cascade = CascadeType.REFRESH)
    @JoinTable(name = "dettaglio_ordini",
                joinColumns = @JoinColumn(name = "id_prodotto", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "id_ordine", referencedColumnName = "id"))
    private List<Ordine> ordini = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Ordine> getOrdini() {
        return ordini;
    }

    public void setOrdini(List<Ordine> ordini) {
        this.ordini = ordini;
    }

}