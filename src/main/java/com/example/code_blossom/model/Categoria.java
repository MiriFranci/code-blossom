package com.example.code_blossom.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorie")
public class Categoria {

    @Id
    private int id;

    @Column
    private String nome;

    @Column
    private String foto;

    @OneToMany
            (
                    mappedBy = "categoria",
                    cascade = CascadeType.REMOVE,
                    fetch = FetchType.EAGER,
                    orphanRemoval = true
            )
    private List<Prodotto> prodotti = new ArrayList<>();

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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
