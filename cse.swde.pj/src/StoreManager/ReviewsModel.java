/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StoreManager;

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
public class ReviewsModel extends DbConnection {
   private static ReviewsModel rModel;
    private ReviewsModel(){
        super();
        System.out.println("ReviewsModel 생성자");
    }

    public static ReviewsModel getInstance(){
        if(rModel == null) {rModel = new ReviewsModel();}
        return rModel;
    }
    
    public DefaultTableModel setReviewsTable(String storeNumber) {        
        String[] column = {"아이디", "메뉴 이름", "리뷰 내용", "평점", "리뷰 작성 시간"}; // jtable의 column 내용
        DefaultTableModel model = new DefaultTableModel(null, column){
            @Override
            public boolean isCellEditable(int row, int column){ // 셀 수정 못하게 하는 부분 
                //return false; 
                return super.isCellEditable(row, column);
            }
        };
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = "select id, menu_name, review, rating, time from reviews where store_number = ?"; // sql문 완성
        
        try (Connection con = getConnection()) { // 데이터베이스와 연결하는 객체로 부모 클래스(DbConnection)의 메소드이다.           
            System.out.println("[ReviewsModel.setReviewsTable 연결 성공]");  
            System.out.println("ReviewsModel의 storeNumber: "+storeNumber);
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, storeNumber);
            rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                String[] data = new String[5];
                data[0] = rs.getString(1); // 아이디
                data[1] = rs.getString(2); // 메뉴 이름
                data[2] = rs.getString(3); // 리뷰 내용
                data[3] = rs.getString(4); // 평점
                data[4] = rs.getString(5); // 리뷰 작성 시간           
                model.addRow(data); // db에 튜플이 있으면 연결 리스트에 String 배열 형식으로 바로 넣어줌
                
                for(int i=0 ; i< 5; i++)
                    System.out.println("ReviewsModel의 'setReviewsTable' -> " + i + "번째 " + data[i]);                
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
    
    public void reviewsDelete(String storeNumber, String id, String menuName, String time) {
        PreparedStatement preparedStatement = null;
        
        try (Connection con = getConnection()){
            System.out.println("[MenuInfoModel.menuInfoDelete 연결 성공]");
            String sql = "delete from reviews where store_number = ? and id = ? and menu_name = ? and time = ?"; // 선택한 store_number의 데이터를 삭제
            System.out.println(sql);
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, storeNumber);
            preparedStatement.setString(2, id);
            preparedStatement.setString(3, menuName);
            preparedStatement.setString(4, time);
            
            int result = preparedStatement.executeUpdate();
            if (result > 0){ // 변경이 성공해서 영향받은 레코드 갯수를 반환하면
                JOptionPane.showMessageDialog(null, "리뷰 삭제가 완료되었습니다.");
            } else {
                JOptionPane.showMessageDialog(null, "리뷰 삭제를 실패하였습니다.");
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
