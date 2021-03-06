/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StoreOwner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * 가맹점주가 gui(View 클래스)에서 입력한 값을 Model 클래스에
 * 전달해주어 이벤트가 실행되도록 한다.
 * @author 정진희 
 */
public class StoreOwnerController implements ActionListener{
    String storeNumber; // 로그인 시 받아오는 사업자 등록번호    
    StoreOwnerView storeOwnerView; // 가맹점주 gui
    StoreInfoModelSingleton storeInfoModelSingleton;//가게 영업정보 등록 및 수정 DB 작업처리
    MenuInfoModelSingleton menuInfoModelSingleton; // 메뉴 정보 등록, 삭제 DB 작업처리
    ReviewsModelSingleton reviewsModelSingleton; // 리뷰 관리(삭제) DB 작업처리    
    JButton storeInfoRegisterBtn; // 가게 영업정보 등록 및 수정하는 버튼    
    JTable menuInfoTable; // 가게 메뉴 목록을 보여주는 jtable    
    JButton menuInfoRegisterBtn; // 가게 메뉴 등록하는 버튼
    JButton menuInfoDeleteBtn; // 가게 메뉴 삭제하는 버튼
    JButton menuInfoRegisterRefreshBtn; // 가게 메뉴 새로고침하는 버튼    
    JTable reviewsTable; // 가게 리뷰 목록 보여주는 jtable
    JButton reviewsDeleteBtn; // 가게 리뷰 삭제하는 버튼
    JButton reviewsRefreshBtn; // 가게 리뷰 목록 새로고침하는 버튼
    
    public StoreOwnerController(String number){ // storeNumber를 생성자에서 받을것임
        storeNumber = number; // 로그인에서 가져온 사업자 등록번호(store_number)를 인스턴스변수 storeNumber에 넘겨줌        
        storeOwnerView = new StoreOwnerView(storeNumber); // 가맹점주 gui 화면 호출
        storeInfoModelSingleton = StoreInfoModelSingleton.getInstance();
        menuInfoModelSingleton = MenuInfoModelSingleton.getInstance();
        reviewsModelSingleton = ReviewsModelSingleton.getInstance();
        
        storeInfoRegisterBtn = storeOwnerView.jButton1; // 가게 영업정보 등록 및 수정하는 버튼    
        storeInfoRegisterBtn.addActionListener(this);
        
        menuInfoTable = storeOwnerView.getTable1(); // 가맹점주 gui에서 메뉴 목록 리스트 jtable       
        menuInfoRegisterBtn = storeOwnerView.jButton3; // 메뉴 등록 버튼
        menuInfoRegisterBtn.addActionListener(this);
        menuInfoDeleteBtn = storeOwnerView.jButton4; // 가게 메뉴 삭제하는 버튼
        menuInfoDeleteBtn.addActionListener(this);
        menuInfoRegisterRefreshBtn = storeOwnerView.jButton5; // 가게 메뉴 새로고침하는 버튼 
        menuInfoRegisterRefreshBtn.addActionListener(this);
        
        reviewsTable = storeOwnerView.getTable2();  // 가맹점주 gui에서 리뷰 목록 리스트 jtable       
        reviewsDeleteBtn = storeOwnerView.jButton6;  // 가게 리뷰 삭제하는 버튼
        reviewsDeleteBtn.addActionListener(this);
        reviewsRefreshBtn = storeOwnerView.jButton7; // 가게 리뷰 목록 새로고침하는 버튼
        reviewsRefreshBtn.addActionListener(this);
    }    
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == storeInfoRegisterBtn){  // 가맹점 승인을 위해 "승인 버튼" 클릭시 발생하는 이벤트
            System.out.println("가게 정보 등록 버튼 누름");
            storeInfoRegister();                          
        } else if(e.getSource() == menuInfoRegisterBtn){  // 가맹점 승인을 위해 "승인 버튼" 클릭시 발생하는 이벤트
            System.out.println("메뉴 등록 버튼 누름"); 
            //System.out.println("버튼쪽 : "+storeNumber);            
            menuInfoRegister();                      
        } else if(e.getSource() == menuInfoDeleteBtn){ // 데이터 삭제를 위해 "삭제 버튼" 클릭 시 발생하는 이벤트
            System.out.println("메뉴 삭제 버튼 누름");            
            menuInfoDelete();         
        } else if(e.getSource() == menuInfoRegisterRefreshBtn){ // 데이터를 가져오기위해 "새로고침 버튼" 클릭 시 발생하는 이벤트
            System.out.println("메뉴 탭의 새로고침 버튼 누름");
            refreshMenuInfoTable1();            
        } else if(e.getSource() == reviewsDeleteBtn){ // 데이터 삭제를 위해 "삭제 버튼" 클릭 시 발생하는 이벤트
            System.out.println("리뷰 삭제 버튼 누름");            
            reviewsDelete();         
        } else if(e.getSource() == reviewsRefreshBtn){ // 데이터를 가져오기위해 "새로고침 버튼" 클릭 시 발생하는 이벤트
            System.out.println("리뷰 탭의 새로고침 버튼 누름");
            refreshReviewsTable2();            
        }      
    }
    
    public void storeInfoRegister(){ // 가게 정보 등록 버튼을 누르면 실행함
        String introduction = storeOwnerView.JTextArea1.getText(); // 가게 소개
        String operatingTime = storeOwnerView.JTextField1.getText(); // 운영 시간
        String closedDays = storeOwnerView.JTextField2.getText(); // 휴무일
        String phone = storeOwnerView.JTextField3.getText(); // 전화번호
        String address = storeOwnerView.JTextField4.getText(); // 주소
        String deliveryCost = storeOwnerView.JTextField5.getText(); // 배달팁
       
        // 정보가 비어있는지 확인하는 if문
        if(introduction.equals("") || operatingTime.equals("") ||closedDays.equals("") || phone.equals("") || address.equals("")){ 
            JOptionPane.showMessageDialog(null, "가게 정보가 비어있습니다. 모두 채워주세요.");       
        } else { //DB 처리 클래스 호출                        
            storeInfoModelSingleton.setData(introduction, operatingTime, closedDays, phone, address, deliveryCost); // 입력받은 정보를 넘겨줘서 DB 작업을함
            storeInfoModelSingleton.storeInfoUpdate(storeNumber); // 해당 storeNumber를 가지는 가맹점의 가게 정보를 업데이트 한다.
        }    
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void menuInfoRegister(){ // 메뉴 등록 버튼 누름
        String menuName = storeOwnerView.JTextField6.getText();
        String menuOption = storeOwnerView.jComboBox1.getSelectedItem().toString();
        int menuPrice = Integer.parseInt(storeOwnerView.JTextField7.getText());
        String menuHashtag = storeOwnerView.JTextField8.getText();
        
        if(menuName.equals("") || menuOption.equals("") || menuPrice == 0 || menuHashtag.equals("")){ 
            JOptionPane.showMessageDialog(null, "메뉴 정보가 비어있습니다. 모두 채워주세요.");       
        } else { //DB 처리 클래스 호출      
            menuInfoModelSingleton.menuInfoInsert(menuName, menuOption, menuPrice, menuHashtag); // 입력받은 정보를 넘겨줘서 DB 작업을함   
            refreshMenuInfoTable1(); // 메뉴 목록을 새로고침해서 보여줌
        }
    }
    
    public void menuInfoDelete(){ // 메뉴 삭제 버튼 누름
        DefaultTableModel tableModel2 = (DefaultTableModel)menuInfoTable.getModel(); // 실행했을 때 저장된 상태의 jtable의 모델을 가져온다.
        int selectedRow = menuInfoTable.getSelectedRow(); // jtable에서 선택한 row의 index 번호        
        System.out.println(selectedRow);
        
        if (selectedRow != -1){
            String menuName = (String) menuInfoTable.getValueAt(selectedRow, 0); // 선택한 row에서 메뉴 이름을 가져옴          
            menuInfoModelSingleton.menuInfoDelete(storeNumber, menuName); // 데이터를 삭제 할 db 호출        
            tableModel2.removeRow(selectedRow); // 선택한 row가 table에서 삭제된걸 화면에 바로 보여준다.
            refreshMenuInfoTable1();
        } else {
            JOptionPane.showMessageDialog(null, "삭제할 데이터가 선택되지 않았습니다. 다시 선택해 주세요.");
        }         
    }
    
    public void refreshMenuInfoTable1(){ // 메뉴 탭의 새로고침 버튼 누름
        DefaultTableModel tableModel1 = storeOwnerView.getRefreshTable1(); // 가장 최신의 jtable 모델을 가져온다.
        tableModel1.setRowCount(0); // 전체 테이블 화면을 모두 비운다.           
        tableModel1 = menuInfoModelSingleton.setMenuInfoTable(storeNumber); //select문 결과를 담는다.
        menuInfoTable.setModel(tableModel1); // controller에 저장되어있는 jtable 객체도 바뀐 값을 넣어준다.
        storeOwnerView.setRefreshTable1(tableModel1); // 업데이트된 tablemodel을 jtable에 넣어준다.
    }    
 //////////////////////////////////////////////////////////////////////////////////////////////////////////////   
    public void reviewsDelete(){ // 리뷰 삭제 버튼 누름
        DefaultTableModel tableModel2 = (DefaultTableModel)reviewsTable.getModel(); // 실행했을 때 저장된 상태의 jtable의 모델을 가져온다.
        int selectedRow = reviewsTable.getSelectedRow(); // jtable에서 선택한 row의 index 번호        
        System.out.println(selectedRow);
        
        if (selectedRow != -1){
            String id = (String) reviewsTable.getValueAt(selectedRow, 0); // 선택한 row에서 메뉴 이름을 가져옴          
            //String menuName = (String) reviewsTable.getValueAt(selectedRow, 1); // 선택한 row에서 메뉴 이름을 가져옴          
            String time = (String) reviewsTable.getValueAt(selectedRow, 3); // 선택한 row에서 메뉴 이름을 가져옴          
            reviewsModelSingleton.reviewsDelete(storeNumber, id, time); // 데이터를 삭제 할 db 호출         
            tableModel2.removeRow(selectedRow); // 선택한 row가 table에서 삭제된걸 화면에 바로 보여준다.
            refreshReviewsTable2();
        } else {
            JOptionPane.showMessageDialog(null, "삭제할 데이터가 선택되지 않았습니다. 다시 선택해 주세요.");
        }                 
    }
    
    public void refreshReviewsTable2(){
        DefaultTableModel tableModel2 = storeOwnerView.getRefreshTable2(); // 가장 최신의 jtable 모델을 가져온다.
        tableModel2.setRowCount(0); // 전체 테이블 화면의 row를 모두 비운다.           
        tableModel2 = reviewsModelSingleton.setReviewsTable(storeNumber); //select문 결과를 담는다.
        reviewsTable.setModel(tableModel2); // controller에 저장되어있는 jtable 객체도 바뀐 값을 넣어준다.
        storeOwnerView.setRefreshTable2(tableModel2); // 업데이트된 tablemodel을 jtable에 넣어준다.
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String number = null;
        StoreOwnerController ctr = new StoreOwnerController(number);
    }
    
}
