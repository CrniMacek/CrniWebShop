/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crni.data;

import com.crni.model.Korisnik;
import com.crni.model.Narudzba;
import com.crni.model.Stavka;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author igor
 */
class NarudbaRepozitorij {

    private DataSource dataSource;
    private KorisnikRepozitorij korisnikRepozitorij;
    private StavkaRepozitorij stavkaRepozitorij;

    public NarudbaRepozitorij(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    int narudzba_Insert_Nova(String opisNarudzbe, BigDecimal bigDecimal, Calendar instance, int idKorisnik) throws SQLException {

        try (Connection conn = dataSource.getConnection()) {

            CallableStatement cs;
            cs = conn.prepareCall("{CALL DodajNovuNarudbu(?,?,?,?)}");
            cs.setString("OpisNarudzbe", opisNarudzbe);
            cs.setBigDecimal("PunaCijenaKN", bigDecimal);
            cs.setDate("VrijemeNarudzbe", new java.sql.Date(instance.getTimeInMillis()));
            cs.setInt("KorisnikID", idKorisnik);

            ResultSet rs = cs.executeQuery();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt("narudbaID");
            }
            return id;
        }
    }

    void narudzba_Update_SingleById(int nid, BigDecimal suma) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {

            CallableStatement cs;
            cs = conn.prepareCall("{CALL PrepraviNarudbu(?,?)}");
            cs.setBigDecimal("PunaCijena", suma);
            cs.setInt("IDNarudba", nid);

            cs.execute();
        }
    }

    Narudzba narudzba_Select_SingleById(int nid, List<Stavka> stavke) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL DohvatiNarudzbeByID(?)}");
            cs.setInt("IDNarudba", nid);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                int IDNarudba = rs.getInt("IDNarudzba");
                String opis = rs.getString("OpisNarudzbe");
                BigDecimal punaCijenaKN = rs.getBigDecimal("PunaCijenaKN");
                Date vrijemeNarudbe = rs.getDate("VrijemeNarudzbe");
                Calendar cal = new GregorianCalendar();
                cal.setTime(vrijemeNarudbe);
                int korisnikID = rs.getInt("KorisnikID");

                this.korisnikRepozitorij = new KorisnikRepozitorij(dataSource);
                Korisnik korisnik = korisnikRepozitorij.korisnik_Select_By_Id(korisnikID);
                Narudzba narudba = new Narudzba(vrijemeNarudbe, korisnikID, IDNarudba, opis, punaCijenaKN, cal, stavke, korisnik);
                return narudba;
            }
        }
        return null;
    }

    Narudzba narudzba_Select_AllItems(int idNarudzba) throws SQLException {

        this.stavkaRepozitorij = new StavkaRepozitorij(dataSource);
        ArrayList<Stavka> stavke = stavkaRepozitorij.dohvati_sve_stavke(idNarudzba);

        return narudzba_Select_SingleById(idNarudzba, stavke);

    }

    Narudzba narudzba_Select_SingleById(int narudzbaID) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL DohvatiNarudzbeByID(?)}");
            cs.setInt("IDNarudba", narudzbaID);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                int IDNarudba = rs.getInt("IDNarudzba");
                String opis = rs.getString("OpisNarudzbe");
                BigDecimal punaCijenaKN = rs.getBigDecimal("PunaCijenaKN");
                Date vrijemeNarudbe = rs.getDate("VrijemeNarudzbe");
                Calendar cal = new GregorianCalendar();
                cal.setTime(vrijemeNarudbe);
                int korisnikID = rs.getInt("KorisnikID");

                this.korisnikRepozitorij = new KorisnikRepozitorij(dataSource);
                Korisnik korisnik = korisnikRepozitorij.korisnik_Select_By_Id(korisnikID);
                Narudzba narudba = new Narudzba(vrijemeNarudbe, korisnikID, IDNarudba, opis, punaCijenaKN, cal, null, korisnik);
                return narudba;
            }
        }
        return null;
    }

    void narudzba_Delete_BYId(int idNarudzba) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {

            CallableStatement cs;
            cs = conn.prepareCall("{CALL IzbrisiNarudzbu(?)}");
            cs.setInt("IDNarudba", idNarudzba);

            cs.execute();
        }
    }

}
