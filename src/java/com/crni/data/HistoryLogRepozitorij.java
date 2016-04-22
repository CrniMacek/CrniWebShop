/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crni.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author igor
 */
public class HistoryLogRepozitorij {

    private DataSource dataSource;

    HistoryLogRepozitorij(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    void historyLog_Insert_New(String opis, int idKorisnik) throws SQLException {        
        
        try (Connection conn = dataSource.getConnection()) {

            CallableStatement cs = conn.prepareCall("{CALL DodajNoviHistory(?,?)}");
            cs.setString("OpisAkcije", opis);
            cs.setInt("KorisnikID", idKorisnik);
            
            boolean res = cs.execute();
        }
    }
    
}
