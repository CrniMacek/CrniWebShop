/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crni.model;

/**
 *
 * @author igor
 */
public class Kategorija {
    
    private int IDKategorija;

    private String naziv;

    public Kategorija(int IDKategorija, String naziv) {
        this.IDKategorija = IDKategorija;
        this.naziv = naziv;
    }

    public int getIDKategorija() {
        return IDKategorija;
    }

    public void setIDKategorija(int IDKategorija) {
        this.IDKategorija = IDKategorija;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    
}
