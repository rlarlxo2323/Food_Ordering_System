/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemAdministrator;

import Connect_DB.Connect_DB;
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
public class ApproveRefuseModel extends Connect_DB {
    private static ApproveRefuseModel arModel;
    private ApproveRefuseModel(){
        super();
        System.out.println("ApproveRefuseModel 생성자");
    }
    
    public static ApproveRefuseModel getInstance(){
        if(arModel == null) {arModel = new ApproveRefuseModel();}
        return arModel;
    }
    
    public DefaultTableModel setTable1() {
        String[] column = {"아이디", "사업자 등록번호", "가게 카테고리","상호명", "대표자명", "사업자주소"}; // jtable의 column 내용
        DefaultTableModel model = new DefaultTableModel(column, 0){
            @Override
            public boolean isCellEditable(int row, int column){ // 셀 수정 못하게 하는 부분 
                return false; 
            }
        };
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = "select id, store_number, store_category, store_name, store_owner, store_address from store_list where store_state = 'w'"; // sql문 완성
        
        try (Connection con = getConnection()) { // 데이터베이스와 연결하는 객체로 부모 클래스(DbConnection)의 메소드이다.           
            System.out.println("[ApproveRefuseModel.setTable1 연결 성공]");            
            //System.out.println(sql);
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                String[] data = new String[6];
                data[0] = rs.getString(1); // 아이디
                data[1] = rs.getString(2); // 사업자 등록번호
                data[2] = rs.getString(3); // 가게 카테고리
                data[3] = rs.getString(4); // 상호명
                data[4] = rs.getString(5); // 대표자명
                data[5] = rs.getString(6); // 사업자주소                
                model.addRow(data); // db에 튜플이 있으면 연결 리스트에 String 배열 형식으로 바로 넣어줌
                
                for(int i=0 ; i< 6; i++)
                    System.out.println("ApproveRefuseModel의 'setTable1' -> " + i + "번째 " + data[i]);                
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
    
    public String sql; // store_state를 y 또는 n으로 바꾸는 sql문
    public void storeStateUpdate(Object selectedStoreNumber){
        PreparedStatement preparedStatement = null;
        String storeNumber = selectedStoreNumber.toString(); // jtable에서 선택한 row의 store_number를 string 타입으로 변환        
        
        try (Connection con = getConnection()) { // 데이터베이스와 연결하는 객체로 부모 클래스(DbConnection)의 메소드     
            System.out.println("[ApproveRefuseModel.storeStateUpdate 연결 성공]");            
            //System.out.println(sql);
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, storeNumber);
            
            int result = preparedStatement.executeUpdate();
            if (result > 0){ // 변경이 성공해서 영향받은 레코드 갯수를 반환하면                
                storeInfoDataUpdate1(storeNumber); // store_info DB에 승인된 가맹점의 주소와 가맹점 번호를 넣어준다.
                JOptionPane.showMessageDialog(null, "가맹점 신청 상태 변경이 완료되었습니다.");
            } else {
                JOptionPane.showMessageDialog(null, "가맹점 신청 상태 변경이 실패하였습니다.");
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
    
    public void storeInfoDataUpdate1(String storeNumber){ // store_info DB에 승인된 가맹점의 주소와 가맹점 번호를 넣어준다.
        String storeAddress = null; // 사업자주소  
        PreparedStatement preparedStatement = null;       
        ResultSet rs = null;
        String sql = "select store_address from store_list where store_number = ?"; // sql문 완성
        
        try (Connection con = getConnection()) { // 데이터베이스와 연결하는 객체로 부모 클래스(DbConnection)의 메소드이다.           
            System.out.println("[ApproveRefuseModel.storeInfoDataUpdate 연결 성공]");            
            //System.out.println(sql);
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, storeNumber);
            rs = preparedStatement.executeQuery();
            
            while(rs.next()){ // store_list에서 사업자 주소를 가녀오는게 성공하면        
                storeAddress = rs.getString("store_address"); // 사업자 주소 값을 넣어준다. 
                storeInfoDataUpdate2(storeNumber, storeAddress);                                    
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
    
    public void storeInfoDataUpdate2(String storeNumber, String storeAddress){ // stor_info에 가맹점 주소와 가맹점등록번호 데이터를 넣어준다.
        PreparedStatement preparedStatement2 = null;
        String sql2 = "insert into store_info values(?, ?, ?, ?, ?, ?, ?)"; 
                
        try (Connection con = getConnection()) { // 데이터베이스와 연결하는 객체로 부모 클래스(DbConnection)의 메소드     
            System.out.println("[ApproveRefuseModel.storeInfoDataUpdate2 연결 성공]");            
            //System.out.println(sql);            
            preparedStatement2 = con.prepareStatement(sql2);
            preparedStatement2.setString(1, storeNumber);
            preparedStatement2.setString(2, "");                
            preparedStatement2.setString(3, "");                
            preparedStatement2.setString(4, "");                
            preparedStatement2.setString(5, "");                
            preparedStatement2.setString(6, storeAddress);                
            preparedStatement2.setString(7, "");                
                int result = preparedStatement2.executeUpdate();
                if (result > 0){ // 등록이 성공해서 영향받은 레코드 갯수를 반환하면
                    System.out.println("store_info에 데이터를 넣었습니다.");
                } else {
                    System.out.println("store_info에 데이터를 넣는데 실패하였습니다.");
                }                   
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (preparedStatement2 != null) {
                try {
                    preparedStatement2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }               
        }  
    }  
  
}
