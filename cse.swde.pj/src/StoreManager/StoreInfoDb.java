/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StoreManager;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author jda05
 */
public class StoreInfoDb extends DbConnection {
    String introduction; // 가게 소개
    String operatingTime; // 운영시간
    String closedDays; // 휴무일
    String address; // 주소
    int deliveryCost; // 배달팁
    
    public StoreInfoDb(String introduction, String operatingTime, String closedDays, String address, int deliveryCost) throws ClassNotFoundException { // 생성자
        super(); // 부모 클래스(DbConnection)의 생성자 호출
        this.introduction = introduction;
        this.operatingTime = operatingTime;
        this.closedDays = closedDays;
        this.address = address;
        this.deliveryCost = deliveryCost;
    }
    
    public void register() {
        System.out.println(introduction);
        System.out.println(operatingTime + closedDays);
        System.out.println(address + deliveryCost);
        PreparedStatement preparedStatement = null;
        
        try (Connection con = getConnection()) { // 데이터베이스와 연결하는 객체로 부모 클래스(DbConnection)의 메소드이다.           
            System.out.println("[StoreInfoDb.register 연결 성공]");
            String sql = "insert into store_info(store_introduction, store_operatingtime, store_closeddays, store_address, store_deliverycost) values(?, ?, ?, ?, ?)"; // sql문 완성
            //System.out.println(sql);
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, introduction);
            preparedStatement.setString(2, operatingTime);
            preparedStatement.setString(3, closedDays);
            preparedStatement.setString(4, address);
            preparedStatement.setInt(5, deliveryCost);
            
            int result = preparedStatement.executeUpdate();
            if (result > 0){ // 등록이 성공해서 영향받은 레코드 갯수를 반환하면
                JOptionPane.showMessageDialog(null, "가게정보를 등록하였습니다.");
            } else {
                JOptionPane.showMessageDialog(null, "가게정보를 등록을 실패하였습니다.");
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
