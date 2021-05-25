/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StoreManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author jda05
 */
public class MenuInfoDb extends DbConnection{
    String menuName; // 메뉴명
    String menuDescription; // 메뉴 설명
    String menuPrice; // 가격
    String menuHashtag; // 해시 태그      

    public MenuInfoDb(String menuName, String menuDescription, String menuPrice, String menuHashtag) throws ClassNotFoundException { // 생성자
        super(); // 부모 클래스(DbConnection)의 생성자 호출
        this.menuName = menuName;
        this.menuDescription = menuDescription;
        this.menuPrice = menuPrice;
        this.menuHashtag = menuHashtag;        
    }

    public void register() {
        System.out.println(menuName);
        System.out.println(menuDescription);
        System.out.println(menuPrice + menuHashtag);
        PreparedStatement preparedStatement = null;
        
        try {
            Connection con = getConnection(); // 데이터베이스와 연결하는 객체  
            System.out.println("[Database 연결 성공]");
            String sql = "insert into menu_info(menu_name, menu_description, menu_price, menu_hashtag) values(?, ?, ?, ?)"; // sql문 완성
            System.out.println(sql);
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, menuName);
            preparedStatement.setString(2, menuDescription);
            preparedStatement.setString(3, menuPrice);
            preparedStatement.setString(4, menuHashtag);            
            
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
    
}
