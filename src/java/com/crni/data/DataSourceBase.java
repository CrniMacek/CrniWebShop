/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crni.data;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

/**
 *
 * @author igor
 */
public class DataSourceBase {
    public static javax.sql.DataSource getDataSource() {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("CrniWebShop");
        dataSource.setUser("sa");
        dataSource.setPassword("SQL");
        return dataSource;
    }
}
