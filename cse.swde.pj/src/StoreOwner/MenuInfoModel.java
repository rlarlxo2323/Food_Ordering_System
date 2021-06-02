/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StoreOwner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jda05
 */
public class MenuInfoModel extends DbConnection{
    String storeNumber; // 사업자 등록번호
    String menuName; // 메뉴 이름
    String menuOption; // 메뉴 옵션
    String menuPrice; // 가격
    String menuHashtag; // 해시 태그   
    
    private static MenuInfoModel menuInfoModel;
    private MenuInfoModel(){
        super();  // 부모 클래스(DbConnection)의 생성자 호출
        System.out.println("MenuInfoModel 생성자");
    }
    
    public static MenuInfoModel getInstance(){
        if(menuInfoModel == null) {menuInfoModel = new MenuInfoModel();}
        return menuInfoModel;
    }
    
    public DefaultTableModel setMenuInfoTable(String storeNumber) {
        this.storeNumber = storeNumber;
        String[] column = {"메뉴 이름", "메뉴 옵션", "가격","해시 태그"}; // jtable의 column 내용
        DefaultTableModel model = new DefaultTableModel(null, column){
            @Override
            public boolean isCellEditable(int row, int column){ // 셀 수정 못하게 하는 부분 
                return false; 
            }
        };
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = "select menu_name, menu_option, menu_price, menu_hashtag from menu_info where store_number = ?";
        
        try (Connection con = getConnection()) { // 데이터베이스와 연결하는 객체로 부모 클래스(DbConnection)의 메소드이다.           
            System.out.println("[MenuInfoModel.setTable1 연결 성공]");            
            System.out.println("MenuInfoModel의 storeNumber: "+storeNumber);
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, storeNumber);
            rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                String[] data = new String[4];
                data[0] = rs.getString(1); // 메뉴 이름
                data[1] = rs.getString(2); // 메뉴 옵션
                data[2] = rs.getString(3); // 가격
                data[3] = rs.getString(4); // 해시 태그                            
                model.addRow(data); // db에 튜플이 있으면 연결 리스트에 String 배열 형식으로 바로 넣어줌
                
                for(int i=0 ; i< 4; i++)
                    System.out.println("MenuInfoModel의 'setTable1' -> " + i + "번째 " + data[i]);                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }               
        }  
        return model;    
    }
        
    public void menuInfoInsert(String menuName, String menuOption, int menuPrice, String menuHashtag) { // 메뉴 정보 등록
        System.out.println(menuName);
        System.out.println(menuOption);
        System.out.println(menuPrice + menuHashtag);
        PreparedStatement preparedStatement = null;
        
        try {
            Connection con = getConnection(); // 데이터베이스와 연결하는 객체  
            System.out.println("[MenuInfoModel.menuInfoInsert 연결 성공]");
            String sql = "insert into menu_info(store_number, menu_name, menu_option, menu_price, menu_hashtag) values(?, ?, ?, ?, ?)"; // sql문 완성
            //System.out.println(sql);
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, storeNumber);
            preparedStatement.setString(2, menuName);
            preparedStatement.setString(3, menuOption);
            preparedStatement.setInt(4, menuPrice);
            preparedStatement.setString(5, menuHashtag);            
            
            int result = preparedStatement.executeUpdate();
            if (result > 0){ // 등록이 성공해서 영향받은 레코드 갯수를 반환하면
                JOptionPane.showMessageDialog(null, "메뉴를 등록하였습니다.");
            } else {
                JOptionPane.showMessageDialog(null, "메뉴 등록을 실패하였습니다.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }               
        }  
    }
    
    public void menuInfoDelete(String storeNumber, String menuName){
        PreparedStatement preparedStatement = null;
        
        try (Connection con = getConnection()){
            System.out.println("[MenuInfoModel.menuInfoDelete 연결 성공]");
            String sql = "delete from menu_info where store_number = ? and menu_name = ?"; // 선택한 store_number의 데이터를 삭제
            System.out.println(sql);
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, storeNumber);
            preparedStatement.setString(2, menuName);
            
            int result = preparedStatement.executeUpdate();
            if (result > 0){ // 변경이 성공해서 영향받은 레코드 갯수를 반환하면
                JOptionPane.showMessageDialog(null, "메뉴 정보 삭제가 완료되었습니다.");
            } else {
                JOptionPane.showMessageDialog(null, "메뉴 정보 삭제를 실패하였습니다.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }               
        }                
    }
    
}
