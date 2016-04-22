/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crni.data;

import com.crni.model.Kategorija;
import com.crni.model.Korisnik;
import com.crni.model.Proizvod;
import java.math.BigDecimal;
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
public class ProizvodRepozitorij {

    private DataSource dataSource;
    private KategorijaRepozitorij kategorijaRepozitorij;

    ProizvodRepozitorij(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Proizvod> proizvod_Select_All() throws SQLException {

        List<Proizvod> proizvodi = new ArrayList();
        try (Connection conn = dataSource.getConnection()) {

            CallableStatement cs = conn.prepareCall("{CALL DohvatiSveProizvode()}");
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                int IDProizvod = rs.getInt("IDProizvod");
                String naziv = rs.getString("Naziv");
                String opis = rs.getString("Opis");
                String slike = rs.getString("Slike");
                boolean vidljivZaProdaju = rs.getBoolean("VidljivZaProdaju");

                BigDecimal cijenaKN = rs.getBigDecimal("CijenaKN");

                int kategorijaID = rs.getInt("KategorijaID");

                this.kategorijaRepozitorij = new KategorijaRepozitorij(dataSource);
                Kategorija kategorija = kategorijaRepozitorij.kategorija_Select_By_Id(kategorijaID);
                Proizvod proizvod = new Proizvod(kategorija, kategorijaID, IDProizvod, naziv, opis, slike, vidljivZaProdaju, cijenaKN);

                proizvodi.add(proizvod);
            }
        }
        return proizvodi;
    }

    Proizvod proizvod_Select_Single_By_Id(int idProizvod) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL DohvatiProizvodByID(?)}");
            cs.setInt("IDProizvod", idProizvod);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                int IDProizvod = rs.getInt("IDProizvod");
                String naziv = rs.getString("Naziv");
                String opis = rs.getString("Opis");
                String slike = rs.getString("Slike");
                boolean vidljivZaProdaju = rs.getBoolean("VidljivZaProdaju");

                BigDecimal cijenaKN = rs.getBigDecimal("CijenaKN");

                int kategorijaID = rs.getInt("KategorijaID");

                this.kategorijaRepozitorij = new KategorijaRepozitorij(dataSource);
                Kategorija kategorija = kategorijaRepozitorij.kategorija_Select_By_Id(kategorijaID);
                Proizvod proizvod = new Proizvod(kategorija, kategorijaID, IDProizvod, naziv, opis, slike, vidljivZaProdaju, cijenaKN);
                return proizvod;
            }
        }
        return null;
    }

}
