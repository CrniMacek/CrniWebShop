/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crni.data;

import com.crni.model.Kategorija;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author igor
 */
public class KategorijaRepozitorij {
    
    private DataSource dataSource;

    KategorijaRepozitorij(DataSource dataSource) {
    
        this.dataSource = dataSource;
    }

    Kategorija kategorija_Select_By_Id(int kategorijaID) throws SQLException {

        try (Connection conn = dataSource.getConnection()) {
            CallableStatement cs;
            cs = conn.prepareCall("{CALL DohvatiKategorijuByID(?)}");
            cs.setInt("IDKategorija", kategorijaID);
            ResultSet rs = cs.executeQuery();
          
            if (rs.next()) {
                int id = rs.getInt("IDKategorija");
                String naziv = rs.getString("Naziv");
                
                Kategorija kategorija = new Kategorija(id, naziv);
                return kategorija;
            }
        }
        return null;
    }
    
}
