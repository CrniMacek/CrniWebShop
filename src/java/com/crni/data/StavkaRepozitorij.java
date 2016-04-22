/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crni.data;

import com.crni.model.Narudzba;
import com.crni.model.Proizvod;
import com.crni.model.Stavka;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 *
 * @author igor
 */
class StavkaRepozitorij {
    
    private ProizvodRepozitorij proizvodRepozitorij;
    private NarudbaRepozitorij narudbaRepozitorij;

    private DataSource dataSource;

    public StavkaRepozitorij(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    void stavka_Insert_New(BigDecimal cijenaStavkeKN, Integer kolicinaProizvoda, String opisStavke, int proizvodID, int nid) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {

            CallableStatement cs;
            cs = conn.prepareCall("{CALL DodajNovuStavku(?,?,?,?,?)}");
            cs.setBigDecimal("CijenaStavkeKN", cijenaStavkeKN);
            cs.setInt("KolicinaProizvoda", kolicinaProizvoda);
            cs.setString("OpisStavke", opisStavke);
            cs.setInt("ProizvodID", proizvodID);
            cs.setInt("NarudzbaID", nid);

            cs.execute();

        }
    }

    ArrayList<Stavka> dohvati_sve_stavke(int idNarudzba) throws SQLException {
        ArrayList<Stavka> stavke = new ArrayList();

        try (Connection conn = dataSource.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL DohvatiSveStavkeNarudbe(?)}");
            cs.setInt("IDNarudba", idNarudzba);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                int IDStavka = rs.getInt("IDStavka");
                BigDecimal cijenaStavkeKN = rs.getBigDecimal("CijenaStavkeKN");
                int kolicinaProizvoda = rs.getInt("KolicinaProizvoda");
                String opisStavke = rs.getString("OpisStavke");
                
                int proizvodID = rs.getInt("ProizvodID");
                int narudzbaID = rs.getInt("NarudzbaID");

                this.proizvodRepozitorij = new ProizvodRepozitorij(dataSource);
                Proizvod proizvod = proizvodRepozitorij.proizvod_Select_Single_By_Id(proizvodID);
                
                this.narudbaRepozitorij = new NarudbaRepozitorij(dataSource);
                Narudzba narudba = narudbaRepozitorij.narudzba_Select_SingleById(narudzbaID);
                
                Stavka stavka = new Stavka(proizvod, narudba, IDStavka, kolicinaProizvoda, proizvodID, narudzbaID, cijenaStavkeKN, opisStavke);
                stavke.add(stavka);
            }
        }
        return stavke;
    }

}
