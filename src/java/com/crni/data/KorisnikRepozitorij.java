/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crni.data;

import com.crni.model.KorisnickaGrupa;
import com.crni.model.Korisnik;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author igor
 */
public class KorisnikRepozitorij {

    private DataSource dataSource;
    private KorisnickaGrupaRepozitorij korisnickaGrupaRepozitorij;

    KorisnikRepozitorij(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Korisnik> korisnik_Select_All() throws SQLException {
        List<Korisnik> korisnici = new ArrayList();
        try (Connection conn = dataSource.getConnection()) {

            CallableStatement cs = conn.prepareCall("{CALL DohvatiSveKorisnike()}");
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("IDKorisnik");
                String ime = rs.getString("Ime");
                String prezime = rs.getString("Prezime");
                String email = rs.getString("Email");
                String username = rs.getString("UName");
                String password = rs.getString("PwdHash");
                String godRodenja = rs.getString("GodRod");
                String drzava = rs.getString("Drzava");
                int grupaID = rs.getInt("KorisnickaGrupaID");

                this.korisnickaGrupaRepozitorij = new KorisnickaGrupaRepozitorij(dataSource);
                KorisnickaGrupa korisnickaGrupa = korisnickaGrupaRepozitorij.korisnicka_Grupa_Select_By_Id(grupaID);
                Korisnik kor;

                if (username.isEmpty() || password.isEmpty()) {
                    return null;
                } else {
                    kor = new Korisnik(korisnickaGrupa, grupaID, id, password, drzava, godRodenja, prezime, ime, username, email);
                }
                korisnici.add(kor);
            }
            return korisnici;
        }
    }

    boolean korisnik_Insert_NewRegistration(String uname, String pwd, String email, int i) throws SQLException {

        boolean res = false;

        try (Connection conn = dataSource.getConnection()) {

            if (uname.isEmpty() || pwd.isEmpty() || email.isEmpty()) {
                return res;
            }
            CallableStatement cs;
            cs = conn.prepareCall("{CALL DodajNovogKorisnika(?,?,?,?)}");
            cs.setString("UName", uname);
            cs.setString("PwdHash", pwd);
            cs.setString("Email", email);
            cs.setInt("KorisnickaGrupaID", i);

            res = cs.execute();
        }

        return res;
    }

    Korisnik korisnik_Select_By_Id(int korisnikID) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            CallableStatement cs;
            cs = conn.prepareCall("{CALL DohvatiKorisnikaByID(?)}");
            cs.setInt("IDKorisnik", korisnikID);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("IDKorisnik");
                String ime = rs.getString("Ime");
                String prezime = rs.getString("Prezime");
                String email = rs.getString("Email");
                String username = rs.getString("UName");
                String password = rs.getString("PwdHash");
                String godRodenja = rs.getString("GodRod");
                String drzava = rs.getString("Drzava");
                int grupaID = rs.getInt("KorisnickaGrupaID");

                this.korisnickaGrupaRepozitorij = new KorisnickaGrupaRepozitorij(dataSource);
                KorisnickaGrupa korisnickaGrupa = korisnickaGrupaRepozitorij.korisnicka_Grupa_Select_By_Id(grupaID);
                Korisnik kor;

                if (username.isEmpty() || password.isEmpty()) {
                    return null;
                } else {
                    kor = new Korisnik(korisnickaGrupa, grupaID, id, password, drzava, godRodenja, prezime, ime, username, email);
                }
                return kor;
            }
        }
        return null;
    }

}
