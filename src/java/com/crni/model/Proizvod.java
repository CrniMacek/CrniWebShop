/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crni.model;

import java.math.BigDecimal;

/**
 *
 * @author igor
 */
public class Proizvod {
    
    private Kategorija kategorija;

    private int kategorijaID;

    private int IDProizvod;

    private String naziv;

    private String opis;

    private String slike;

    private boolean vidljivZaProdaju;

    private BigDecimal cijenaKN;

    public Proizvod() {
    }

    public Proizvod(Kategorija kategorija, int kategorijaID, int IDProizvod, String naziv, String opis, String slike, boolean vidljivZaProdaju, BigDecimal cijenaKN) {
        this.kategorija = kategorija;
        this.kategorijaID = kategorijaID;
        this.IDProizvod = IDProizvod;
        this.naziv = naziv;
        this.opis = opis;
        this.slike = slike;
        this.vidljivZaProdaju = vidljivZaProdaju;
        this.cijenaKN = cijenaKN;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    public int getKategorijaID() {
        return kategorijaID;
    }

    public void setKategorijaID(int kategorijaID) {
        this.kategorijaID = kategorijaID;
    }

    public int getIDProizvod() {
        return IDProizvod;
    }

    public void setIDProizvod(int IDProizvod) {
        this.IDProizvod = IDProizvod;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getSlike() {
        return slike;
    }

    public void setSlike(String slike) {
        this.slike = slike;
    }

    public boolean isVidljivZaProdaju() {
        return vidljivZaProdaju;
    }

    public void setVidljivZaProdaju(boolean vidljivZaProdaju) {
        this.vidljivZaProdaju = vidljivZaProdaju;
    }

    public BigDecimal getCijenaKN() {
        return cijenaKN;
    }

    public void setCijenaKN(BigDecimal cijenaKN) {
        this.cijenaKN = cijenaKN;
    }
    
    
}
