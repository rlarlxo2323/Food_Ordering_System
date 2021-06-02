/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StoreOwner;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author jda05
 */
public class StoreInfoModel extends DbConnection {
    String introduction; // 가게 소개
    String operatingTime; // 운영시간
    String closedDays; // 휴무일
    String phone; // 전화번호
    String address; // 주소
    String deliveryCost; // 배달팁  
    
    private static StoreInfoModel storeInfoModel;
    private StoreInfoModel(){
        super();  // 부모 클래스(DbConnection)의 생성자 호출
        System.out.println("StoreInfoModel 생성자");
    }
    
    public static StoreInfoModel getInstance(){
        if(storeInfoModel == null) {storeInfoModel = new StoreInfoModel();}
        return storeInfoModel;
    }
    
//    public StoreInfoModel(String introduction, String operatingTime, String closedDays, String address, int deliveryCost) throws ClassNotFoundException { // 생성자
//        super(); // 부모 클래스(DbConnection)의 생성자 호출
//        this.introduction = introduction;
//        this.operatingTime = operatingTime;
//        this.closedDays = closedDays;
//        this.address = address;
//        this.deliveryCost = deliveryCost;
//    }
    
    public void setStoreInfoTextField(String storeNumber){        
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = "select store_introduction, store_operatingtime, store_closeddays, store_phone, store_address, store_deliverycost from store_info where store_number = ?"; // 
        
        try (Connection con = getConnection()) { // 데이터베이스와 연결하는 객체로 부모 클래스(DbConnection)의 메소드이다.           
            System.out.println("[StoreInfoModel.setTextField 연결 성공]");            
            //System.out.println(sql);
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, storeNumber);
            rs = preparedStatement.executeQuery();
            
            while(rs.next()){                
                for(int i =0; i<6; i++){
                    switch(i){
                        case 0:
                            introduction = rs.getString(i+1);
                            break;
                        case 1:
                            operatingTime = rs.getString(i+1);
                            break;
                        case 2:
                            closedDays = rs.getString(i+1);
                            break;
                        case 3:
                            phone = rs.getString(i+1);
                            break;
                        case 4:
                            address = rs.getString(i+1);
                            break;
                        case 5:
                            deliveryCost = rs.getString(i+1);
                            break;                                
                    }
                }                    
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
    
    public void setData(String introduction, String operatingTime, String closedDays, String phone ,String address, String deliveryCost){
        this.introduction = introduction;
        this.operatingTime = operatingTime;
        this.closedDays = closedDays;
        this.phone = phone;
        this.address = address;
        this.deliveryCost = deliveryCost;
    }
    
    public void storeInfoUpdate(String storeNumber) {
        System.out.println(introduction);
        System.out.println(operatingTime + closedDays);
        System.out.println(phone + address + deliveryCost);
        PreparedStatement preparedStatement = null;
        
        try (Connection con = getConnection()) { // 데이터베이스와 연결하는 객체로 부모 클래스(DbConnection)의 메소드이다.           
            System.out.println("[StoreInfoModel.storeInfoRegisterUpdate 연결 성공]");
            String sql = "update store_info set store_introduction = ?, store_operatingtime = ?, store_closeddays = ?, store_phone = ?, store_address = ?, store_deliverycost = ? where store_number = ?"; // sql문 완성
            //System.out.println(sql);
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, introduction);
            preparedStatement.setString(2, operatingTime);
            preparedStatement.setString(3, closedDays);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, deliveryCost);
            preparedStatement.setString(7, storeNumber);
            
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
