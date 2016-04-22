/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crni.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author igor
 */
public class Narudzba {
    
    public Date vrijemeNarudzbeDate;
	
    private int IDKorisnik;

    private int IDNarudzba;

    private String opisNarudzbe;

    private BigDecimal punaCijenaKn;

    private Calendar vrijemeNarudzbe;

    private List<Stavka> stavke;

    private Korisnik korisnik;

    public Narudzba() {
    }

    public Narudzba(Date vrijemeNarudzbeDate, int IDKorisnik, int IDNarudzba, String opisNarudzbe, BigDecimal punaCijenaKn, Calendar vrijemeNarudzbe, List<Stavka>  stavke, Korisnik korisnik) {
        this.vrijemeNarudzbeDate = vrijemeNarudzbeDate;
        this.IDKorisnik = IDKorisnik;
        this.IDNarudzba = IDNarudzba;
        this.opisNarudzbe = opisNarudzbe;
        this.punaCijenaKn = punaCijenaKn;
        this.vrijemeNarudzbe = vrijemeNarudzbe;
        this.stavke = stavke;
        this.korisnik = korisnik;
    }

    public Date getVrijemeNarudzbeDate() {
        return vrijemeNarudzbeDate;
    }

    public void setVrijemeNarudzbeDate(Date vrijemeNarudzbeDate) {
        this.vrijemeNarudzbeDate = vrijemeNarudzbeDate;
    }

    public int getIDKorisnik() {
        return IDKorisnik;
    }

    public void setIDKorisnik(int IDKorisnik) {
        this.IDKorisnik = IDKorisnik;
    }

    public int getIDNarudzba() {
        return IDNarudzba;
    }

    public void setIDNarudzba(int IDNarudzba) {
        this.IDNarudzba = IDNarudzba;
    }

    public String getOpisNarudzbe() {
        return opisNarudzbe;
    }

    public void setOpisNarudzbe(String opisNarudzbe) {
        this.opisNarudzbe = opisNarudzbe;
    }

    public BigDecimal getPunaCijenaKn() {
        return punaCijenaKn;
    }

    public void setPunaCijenaKn(BigDecimal punaCijenaKn) {
        this.punaCijenaKn = punaCijenaKn;
    }

    public Calendar getVrijemeNarudzbe() {
        return vrijemeNarudzbe;
    }

    public void setVrijemeNarudzbe(Calendar vrijemeNarudzbe) {
        this.vrijemeNarudzbe = vrijemeNarudzbe;
        this.vrijemeNarudzbeDate = vrijemeNarudzbe.getTime();
    }

    public List<Stavka>  getStavke() {
        return stavke;
    }

    public void setStavke(List<Stavka>  stavke) {
        this.stavke = stavke;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
    
    
}
