package com.example.code_blossom.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utenti")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @Pattern(regexp = "[a-zA-Z\\sàèìòù']{1,50}", message = "Caratteri non ammessi")
    private String nome;

    @Column
    @Pattern(regexp = "[a-zA-Z\\sàèìòù']{1,50}", message = "Caratteri non ammessi")
    private String cognome;

    @Column
    private String telefono;

    @Column
    private LocalDate dataDiNascita;

    @Column
    private String email;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private char genere;

    @Column
    private String citta;

    @Column
    private String indirizzo;

    @Column
    private String foto;

    @Column
    private LocalDate dataDiRegistrazione;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Ordine> ordini = new ArrayList<>();


    public List<Ordine> getOrdini() {
        return ordini;
    }

    public void setOrdini(List<Ordine> ordini) {
        this.ordini = ordini;
    }

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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getGenere() {
        return genere;
    }

    public void setGenere(char genere) {
        this.genere = genere;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public LocalDate getDataDiRegistrazione() {
        return dataDiRegistrazione;
    }

    public void setDataDiRegistrazione(LocalDate dataDiRegistrazione) {
        this.dataDiRegistrazione = dataDiRegistrazione;
    }


}
