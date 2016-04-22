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
public class Korisnik {
    
    private KorisnickaGrupa korisnickaGrupa;

    private int IDKorisnickaGrupa;

    private int IDKorisnik;

    private String pwd;

    private String drzava;

    private String godrod;

    private String prezime;

    private String ime;

    private String uname;

    private String email;

    public Korisnik(KorisnickaGrupa korisnickaGrupa, int IDKorisnickaGrupa, int IDKorisnik, String pwd, String drzava, String godrod, String prezime, String ime, String uname, String email) {
        this.korisnickaGrupa = korisnickaGrupa;
        this.IDKorisnickaGrupa = IDKorisnickaGrupa;
        this.IDKorisnik = IDKorisnik;
        this.pwd = pwd;
        this.drzava = drzava;
        this.godrod = godrod;
        this.prezime = prezime;
        this.ime = ime;
        this.uname = uname;
        this.email = email;
    }

    public Korisnik() {
    }

    public KorisnickaGrupa getKorisnickaGrupa() {
        return korisnickaGrupa;
    }

    public void setKorisnickaGrupa(KorisnickaGrupa korisnickaGrupa) {
        this.korisnickaGrupa = korisnickaGrupa;
    }

    public int getIDKorisnickaGrupa() {
        return IDKorisnickaGrupa;
    }

    public void setIDKorisnickaGrupa(int IDKorisnickaGrupa) {
        this.IDKorisnickaGrupa = IDKorisnickaGrupa;
    }

    public int getIDKorisnik() {
        return IDKorisnik;
    }

    public void setIDKorisnik(int IDKorisnik) {
        this.IDKorisnik = IDKorisnik;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getGodrod() {
        return godrod;
    }

    public void setGodrod(String godrod) {
        this.godrod = godrod;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
