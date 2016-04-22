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
public class KorisnickaGrupa {
    
    private int IDKorisnickaGrupa;

    private String naziv;

    public KorisnickaGrupa(int IDKorisnickaGrupa, String naziv) {
        this.IDKorisnickaGrupa = IDKorisnickaGrupa;
        this.naziv = naziv;
    }

    public int getIDKorisnickaGrupa() {
        return IDKorisnickaGrupa;
    }

    public void setIDKorisnickaGrupa(int IDKorisnickaGrupa) {
        this.IDKorisnickaGrupa = IDKorisnickaGrupa;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
}
