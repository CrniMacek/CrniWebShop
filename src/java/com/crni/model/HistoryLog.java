/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crni.model;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author igor
 */
public class HistoryLog {
    
    public Date vrijeme;
	
    private Korisnik korisnik;

    private int IDHistoryLog;

    private int korisnikID;

    private String opisAkcije;

    private Calendar logFirstTimeEntry;

    private Calendar logLastTimeEntry;

    public HistoryLog(Date vrijeme, Korisnik korisnik, int IDHistoryLog, int korisnikID, String opisAkcije, Calendar logFirstTimeEntry, Calendar logLastTimeEntry) {
        this.vrijeme = vrijeme;
        this.korisnik = korisnik;
        this.IDHistoryLog = IDHistoryLog;
        this.korisnikID = korisnikID;
        this.opisAkcije = opisAkcije;
        this.logFirstTimeEntry = logFirstTimeEntry;
        this.logLastTimeEntry = logLastTimeEntry;
    }

    public HistoryLog() {
    }

    public Date getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(Date vrijeme) {
        this.vrijeme = vrijeme;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public int getIDHistoryLog() {
        return IDHistoryLog;
    }

    public void setIDHistoryLog(int IDHistoryLog) {
        this.IDHistoryLog = IDHistoryLog;
    }

    public int getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(int korisnikID) {
        this.korisnikID = korisnikID;
    }

    public String getOpisAkcije() {
        return opisAkcije;
    }

    public void setOpisAkcije(String opisAkcije) {
        this.opisAkcije = opisAkcije;
    }

    public Calendar getLogFirstTimeEntry() {
        return logFirstTimeEntry;
    }

    public void setLogFirstTimeEntry(Calendar logFirstTimeEntry) {
        this.logFirstTimeEntry = logFirstTimeEntry;
        this.vrijeme = logFirstTimeEntry.getTime();
    }

    public Calendar getLogLastTimeEntry() {
        return logLastTimeEntry;
    }

    public void setLogLastTimeEntry(Calendar logLastTimeEntry) {
        this.logLastTimeEntry = logLastTimeEntry;
    }
    
    
}
