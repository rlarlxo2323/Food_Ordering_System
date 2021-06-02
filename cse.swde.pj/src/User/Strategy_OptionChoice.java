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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 김두현
 */
class test implements Strategy_OptionPrice {

    OptionValue sv = new OptionValue();
    String menuL = sv.getMenuLabel();
    String sizeBox = sv.getSizeComboBox();

    @Override
    public void action() {

        StoreNum storelist = new StoreNum();
        String sl = storelist.getStoreList();
        String s = storelist.getStore();

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String menuSql = "select menu_price from menu_info where menu_info.store_number =";
        try {
            con = DriverManager.getConnection("jdbc:mysql://115.85.182.30:3306/cse_swde_DB?zeroDateTimeBehavior=CONVERT_TO_NULL&characterEncoding=UTF-8&serverTimezone=UTC", "cse_swde", "password");
            st = con.prepareStatement(menuSql + "'" + s + "'" + "and menu_info.menu_name =" + "'" + menuL + "';");
            rs = st.executeQuery();
            while (rs.next()) {
                int menuPrice = rs.getInt("menu_price");
                System.out.println(menuPrice);
                int price = sv.getMenuPrice();
                price = menuPrice;
                sv.setMenuPrice(price);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(StoreList.class.getName()).log(Level.SEVERE, null, ex);
        }
        menuL = "-";
    }
}



class Small implements Strategy_OptionPrice {

    OptionValue sv = new OptionValue();
    String menuL = sv.getMenuLabel();
    String sizeBox = sv.getSizeComboBox();

    @Override
    public void action() {

        StoreNum storelist = new StoreNum();
        String sl = storelist.getStoreList();
        String s = storelist.getStore();

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String menuSql = "select menu_price from menu_info join store_info using (store_number) where store_info.store_number =";
        try {
            con = DriverManager.getConnection("jdbc:mysql://115.85.182.30:3306/cse_swde?useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8", "cse_swde", "password");
            st = con.prepareStatement(menuSql + "'" + s + "'" + "and menu_info.menu_name =" + "'" + menuL + "';");
            rs = st.executeQuery();
            while (rs.next()) {
                int menuPrice = rs.getInt("menu_price");
                System.out.println(menuPrice);
                int price = sv.getMenuPrice();
                price = menuPrice;
                sv.setMenuPrice(price);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(StoreList.class.getName()).log(Level.SEVERE, null, ex);
        }
        menuL = "-";
    }
}

class Medium extends Store implements Strategy_OptionPrice {

    OptionValue sv = new OptionValue();
    String menuL = sv.getMenuLabel();
    String sizeBox = sv.getSizeComboBox();

    @Override
    public void action() {

        StoreNum storelist = new StoreNum();
        String sl = storelist.getStoreList();
        String s = storelist.getStore();

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String menuSql = "select menu_price from menu_info join store_info using (store_number) where store_info.store_number =";
        try {
            con = DriverManager.getConnection("jdbc:mysql://115.85.182.30:3306/cse_swde_DB?zeroDateTimeBehavior=CONVERT_TO_NULL&characterEncoding=UTF-8&serverTimezone=UTC", "cse_swde", "password");
            st = con.prepareStatement(menuSql + "'" + s + "'" + "and menu_info.menu_name =" + "'" + menuL + "';");
            rs = st.executeQuery();
            while (rs.next()) {
                int menuPrice = rs.getInt("menu_price") + 1000;
                System.out.println(menuPrice);
                int price = sv.getMenuPrice();
                price = menuPrice;
                sv.setMenuPrice(price);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StoreList.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

class Large extends Store implements Strategy_OptionPrice {

    OptionValue sv = new OptionValue();
    String menuL = sv.getMenuLabel();
    String sizeBox = sv.getSizeComboBox();

    @Override
    public void action() {

        StoreNum storelist = new StoreNum();
        String sl = storelist.getStoreList();
        String s = storelist.getStore();

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String menuSql = "select menu_price from menu_info join store_info using (store_number) where store_info.store_number =";
        try {
            con = DriverManager.getConnection("jdbc:mysql://115.85.182.30:3306/cse_swde_DB?zeroDateTimeBehavior=CONVERT_TO_NULL&characterEncoding=UTF-8&serverTimezone=UTC", "test", "password");
            st = con.prepareStatement(menuSql + "'" + s + "'" + "and menu_info.menu_name =" + "'" + menuL + "';");
            rs = st.executeQuery();
            while (rs.next()) {
                int menuPrice = rs.getInt("menu_price") + 2000;
                System.out.println(menuPrice);
                int price = sv.getMenuPrice();
                price = menuPrice;
                sv.setMenuPrice(price);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StoreList.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
class HashSmall extends HashtagStore implements Strategy_OptionPrice {

    OptionValue sv = new OptionValue();
    String menuL = sv.getMenuLabel();
    String sizeBox = sv.getSizeComboBox();

    @Override
    public void action() {

        HashSearch hashsearch = new HashSearch();
        String hs = hashsearch.getHashSearch();
        
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "select * from menu_info join store_info using (store_number) where menu_info.menu_hashtag = ";

        try {
            con = DriverManager.getConnection("jdbc:mysql://115.85.182.30:3306/cse_swde_DB?zeroDateTimeBehavior=CONVERT_TO_NULL&characterEncoding=UTF-8&serverTimezone=UTC", "test", "password");
            st = con.prepareStatement(sql + "'" + hs + "'" + "and menu_info.menu_name =" + "'" + menuL + "';");
            rs = st.executeQuery();
            while (rs.next()) {
                int menuPrice = rs.getInt("menu_price");
                System.out.println(menuPrice);
                int price = sv.getMenuPrice();
                price = menuPrice;
                sv.setMenuPrice(price);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(StoreList.class.getName()).log(Level.SEVERE, null, ex);
        }
        menuL = "-";
    }
}

class HashMedium extends HashtagStore implements Strategy_OptionPrice {

    OptionValue sv = new OptionValue();
    String menuL = sv.getMenuLabel();
    String sizeBox = sv.getSizeComboBox();

    @Override
    public void action() {

        HashSearch hashsearch = new HashSearch();
        String hs = hashsearch.getHashSearch();
        
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "select * from menu_info join store_info using (store_number) where menu_info.menu_hashtag = ";

        try {
            con = DriverManager.getConnection("jdbc:mysql://115.85.182.30:3306/cse_swde_DB?zeroDateTimeBehavior=CONVERT_TO_NULL&characterEncoding=UTF-8&serverTimezone=UTC", "test", "password");
            st = con.prepareStatement(sql + "'" + hs + "'" + "and menu_info.menu_name =" + "'" + menuL + "';");
            rs = st.executeQuery();
            while (rs.next()) {
                int menuPrice = rs.getInt("menu_price");
                System.out.println(menuPrice);
                int price = sv.getMenuPrice();
                price = menuPrice;
                sv.setMenuPrice(price);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(StoreList.class.getName()).log(Level.SEVERE, null, ex);
        }
        menuL = "-";
    }
}

class HashLarge extends HashtagStore implements Strategy_OptionPrice {

    OptionValue sv = new OptionValue();
    String menuL = sv.getMenuLabel();
    String sizeBox = sv.getSizeComboBox();

    @Override
    public void action() {


        HashSearch hashsearch = new HashSearch();
        String hs = hashsearch.getHashSearch();
        
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "select * from menu_info join store_info using (store_number) where menu_info.menu_hashtag = ";

        try {
            con = DriverManager.getConnection("jdbc:mysql://115.85.182.30:3306/cse_swde_DB?zeroDateTimeBehavior=CONVERT_TO_NULL&characterEncoding=UTF-8&serverTimezone=UTC", "test", "password");
            st = con.prepareStatement(sql + "'" + hs + "'" + "and menu_info.menu_name =" + "'" + menuL + "';");
            rs = st.executeQuery();
            while (rs.next()) {
                int menuPrice = rs.getInt("menu_price");
                System.out.println(menuPrice);
                int price = sv.getMenuPrice();
                price = menuPrice;
                sv.setMenuPrice(price);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(StoreList.class.getName()).log(Level.SEVERE, null, ex);
        }
        menuL = "-";
    }
}