/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GetSet;

import Connect_DB.Connect_DB;
import User.StoreList;
import java.sql.Connection;
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

    public static String getStoreList() {
        return storeList;
    }

    public void setStoreList(String storeList) {
        this.storeList = storeList;
    }

    public static String getStore() {

        String storeSql = "select store_number from store_list where store_name=?";
        Connect_DB db = new Connect_DB();
        try {
            Connection con = db.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(storeSql);
            preparedStatement.setString(1, storeList);
            ResultSet rs = preparedStatement.executeQuery();
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
