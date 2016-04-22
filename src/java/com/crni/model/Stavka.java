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
public class Stavka implements java.io.Serializable {

    private Proizvod proizvod;

    private Narudzba narudzba;

    private int IDStavka;

    private Integer kolicinaProizvoda;

    private int proizvodID;

    private int narudzbaID;

    private BigDecimal cijenaStavkeKN;

    private String opisStavke;

    public Stavka() {
    }

    public Stavka(Proizvod proizvod, Narudzba narudzba, int IDStavka, Integer kolicinaProizvoda, int proizvodID, int narudzbaID, BigDecimal cijenaStavkeKN, String opisStavke) {
        this.proizvod = proizvod;
        this.narudzba = narudzba;
        this.IDStavka = IDStavka;
        this.kolicinaProizvoda = kolicinaProizvoda;
        this.proizvodID = proizvodID;
        this.narudzbaID = narudzbaID;
        this.cijenaStavkeKN = cijenaStavkeKN;
        this.opisStavke = opisStavke;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

    public Narudzba getNarudzba() {
        return narudzba;
    }

    public void setNarudzba(Narudzba narudzba) {
        this.narudzba = narudzba;
    }

    public int getIDStavka() {
        return IDStavka;
    }

    public void setIDStavka(int IDStavka) {
        this.IDStavka = IDStavka;
    }

    public Integer getKolicinaProizvoda() {
        return kolicinaProizvoda;
    }

    public void setKolicinaProizvoda(Integer kolicinaProizvoda) {
        this.kolicinaProizvoda = kolicinaProizvoda;
    }

    public int getProizvodID() {
        return proizvodID;
    }

    public void setProizvodID(int proizvodID) {
        this.proizvodID = proizvodID;
    }

    public int getNarudzbaID() {
        return narudzbaID;
    }

    public void setNarudzbaID(int narudzbaID) {
        this.narudzbaID = narudzbaID;
    }

    public BigDecimal getCijenaStavkeKN() {
        return cijenaStavkeKN;
    }

    public void setCijenaStavkeKN(BigDecimal cijenaStavkeKN) {
        this.cijenaStavkeKN = cijenaStavkeKN;
    }

    public String getOpisStavke() {
        return opisStavke;
    }

    public void setOpisStavke(String opisStavke) {
        this.opisStavke = opisStavke;
    }
}
