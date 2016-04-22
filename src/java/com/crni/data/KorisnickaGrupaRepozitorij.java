/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crni.data;

import com.crni.model.KorisnickaGrupa;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author igor
 */
public class KorisnickaGrupaRepozitorij {
    
    private DataSource dataSource;

    KorisnickaGrupaRepozitorij(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public KorisnickaGrupa korisnicka_Grupa_Select_By_Id(int grupaID) throws SQLException {
    
        try (Connection conn = dataSource.getConnection()) {
            CallableStatement cs;
            cs = conn.prepareCall("{CALL DohvatiKorisnickeGrupeByID(?)}");
            cs.setInt("IDKorisnickaGrupa", grupaID);
            ResultSet rs = cs.executeQuery();
          
            if (rs.next()) {
                int id = rs.getInt("IDKorisnickaGrupa");
                String naziv = rs.getString("Naziv");
                
                KorisnickaGrupa korGrupa = new KorisnickaGrupa(id, naziv);
                return korGrupa;
            }
        }
        return null; 
    }
    
}
