/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StoreManager;

/**
 *
 * @author jda05
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {    
    // 데이터베이스에 연결하기 위한 정보 
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // 1. JDBC Driver Class 
    private static final String url = "jdbc:mysql://118.67.130.238:3306/cse_swde?zeroDateTimeBehavior=CONVERT_TO_NULL&characterEncoding=UTF-8&serverTimezone=UTC";  
    // jdbc:mysql://localhost:3306/sw_design?&characterEncoding=UTF-8&serverTimezone=UTC
    private static final String user = "test"; // 데이터베이스 ID 
    private static final String pw = "password"; // 데이터베이스 PW
    //private Connection con;
        
    public DbConnection()  {
        try {
            Class.forName(driver); // JDBC 드라이버 로딩 
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getException());
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    protected Connection getConnection() throws SQLException {        
        /*
        try {
            con = DriverManager.getConnection(url, user, pw); // Do something with the Connection           
         } catch (SQLException ex) {
             // handle any errors
             System.out.println("SQLException: " + ex.getMessage());
             System.out.println("SQLState: " + ex.getSQLState());
             System.out.println("VendorError: " + ex.getErrorCode());
         }
        */
        return DriverManager.getConnection(url, user, pw);
    } 
}