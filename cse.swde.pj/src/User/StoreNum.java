/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 김두현
 */
public class StoreNum {

    public static String storeList = null;
    public static String store;

    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    String menuSql = "select store_name ,avg(rating) from store_list join reviews using (store_number) where store_list.store_category=";

    public static String getStoreList() {
        return storeList;
    }

    public void setStoreList(String storeList) {
        this.storeList = storeList;
    }

    public static String getStore() {

        
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String storeSql = "select store_number from store_list where store_name=";

        try {
            con = DriverManager.getConnection("jdbc:mysql://115.85.182.30:3306/cse_swde_DB?zeroDateTimeBehavior=CONVERT_TO_NULL&characterEncoding=UTF-8&serverTimezone=UTC", "cse_swde", "password");
            st = con.prepareStatement(storeSql + "'" + storeList + "'");
            rs = st.executeQuery();
            while(rs.next())
            {
                String storeNum = rs.getString("store_number");
                store = storeNum;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StoreList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return store;

    }

}
