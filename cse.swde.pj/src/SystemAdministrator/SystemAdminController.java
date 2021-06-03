/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemAdministrator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author jda05
 */
public class SystemAdminController implements ActionListener {
    SystemAdminView systemAdminView;
    ModifyView modifyView;
    ApproveRefuseModel approveRefuseModelSingleton;
    ModifyDeleteModel modifyDeleteModelSingleton;
   
    JTable approveRefuseTable;    
    JButton approveBtn;
    JButton refuseBtn;
    JButton refreshBtn1;
    
    JTable modifyDeleteTable;
    JButton modifyBtn;
    JButton modifyCompleteBtn;
    JButton deleteBtn;  
    JButton refreshBtn2;
    
    public SystemAdminController(){
        systemAdminView = new SystemAdminView();    
        modifyView = new ModifyView();
        approveRefuseModelSingleton =  ApproveRefuseModel.getInstance();       
        modifyDeleteModelSingleton =  ModifyDeleteModel.getInstance();
        
        approveRefuseTable = systemAdminView.getTable1();
        approveBtn = systemAdminView.getButton1();
        approveBtn.addActionListener(this);
        refuseBtn = systemAdminView.getButton2();
        refuseBtn.addActionListener(this);
        refreshBtn1 = systemAdminView.jButton5;
        refreshBtn1.addActionListener(this);
        
        modifyDeleteTable = systemAdminView.getTable2();
        modifyBtn = systemAdminView.getButton3();
        modifyBtn.addActionListener(this);
        modifyCompleteBtn = ModifyView.jButton1; 
        modifyCompleteBtn.addActionListener(this);
        deleteBtn = systemAdminView.getButton4();
        deleteBtn.addActionListener(this);    
        refreshBtn2 = systemAdminView.jButton6;
        refreshBtn2.addActionListener(this);
    }
    
    public void setQuery(String sql){
        approveRefuseModelSingleton.sql = sql;
    }   

    public void actionUpdate(String sql){
        int selectedRow = approveRefuseTable.getSelectedRow(); // jtable에서 선택한 row의 index 번호       
        if (selectedRow != -1){
            Object selectedStoreNumber = approveRefuseTable.getValueAt(selectedRow, 1); // jtable에서 선택한 row의 store_number
            System.out.println("selectedStoreNumber: " + selectedStoreNumber);                  
            setQuery(sql);
            approveRefuseModelSingleton.storeStateUpdate(selectedStoreNumber); // db에서 선택한 store_number의 store_state를 y로 변경       
            refreshTable1(); //  변경된 db의 데이터로 jtable을 새로고침 기능 실행
        } else {
            JOptionPane.showMessageDialog(null, "승인 처리 할 데이터가 선택되지 않았습니다. 다시 선택해 주세요.");
        }   
    }
    
    public void refreshTable1(){
    DefaultTableModel tableModel1 = systemAdminView.getRefreshTable1(); // 가장 최신의 jtable 모델을 가져온다.
    //tableModel1 = systemAdminView.getRefreshTable1(); // 가장 최신의 jtable 모델을 가져온다.
    tableModel1.setRowCount(0); // 전체 테이블 화면을 모두 비운다.           
    tableModel1 = approveRefuseModelSingleton.setTable1(); //select문 결과를 담는다.
    approveRefuseTable.setModel(tableModel1); // controller에 저장되어있는 jtable 객체도 바뀐 값을 넣어준다.
    systemAdminView.setRefreshTable1(tableModel1); // 업데이트된 tablemodel을 jtable에 넣어준다.
    }    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    String originalStoreNumber ; // 선택한 row의 db상의 store_number
    public void actionModify(){              
        int selectedRow = modifyDeleteTable.getSelectedRow(); // jtable에서 선택한 row의 index 번호          
        int colCount = modifyDeleteTable.getColumnCount(); // jtable의 column 수 
        System.out.println("row: " + selectedRow + " / " + "colCount: " + colCount);
        
        if (selectedRow != -1 && colCount > 0){           
            originalStoreNumber = callSelectedRow(selectedRow, colCount, modifyDeleteTable); // 선택한 row의 값을 수정 gui에서 보여주기
            modifyView.setTitle("가맹점 정보 수정");
            modifyView.setVisible(true); // 수정하는 창을 보이게 함 
            System.out.println("actionModify - 수정 창이 보일것임");                     
        } else {
            JOptionPane.showMessageDialog(null, "수정할 데이터가 선택되지 않았습니다. 다시 선택해 주세요.");
        }        
    }

    public String callSelectedRow(int selectedRow, int colCount, JTable table){ // 선택한 row의 값을 새로운 창의 textFeild에 넣어서 보여준다.    
        for (int i = 0; i < colCount; i++) {            
            System.out.print("callSelectedRow: " + table.getModel().getValueAt(selectedRow, i )+" / ");                 
            String data = (String) table.getModel().getValueAt(selectedRow, i );
            
            switch(i){               
                case 1:
                    originalStoreNumber = data;
                    ModifyView.jTextField1.setText(data);
                    break;                    
                case 2:
                    ModifyView.jTextField2.setText(data);
                    break;
                case 3:
                    ModifyView.jTextField3.setText(data);
                    break;                      
                case 4:
                    ModifyView.jTextField4.setText(data);
                    break;
                case 5:    
                    ModifyView.jTextField5.setText(data);
                    break;          
            }                              
        }     System.out.println();
        return originalStoreNumber;
    }
    
    public void actionModifyUpdate(){ // 수정 완료 버튼을 눌렀을 때 발생하는 이벤트로 입력받은 새로운 정보 db에 업데이트 하기         
        String storeNumber = ModifyView.jTextField1.getText();
        String storeCategory = ModifyView.jTextField2.getText();
        String storeName = ModifyView.jTextField3.getText();
        String storeOwner = ModifyView.jTextField4.getText();
        String storeAddress = ModifyView.jTextField5.getText();
        
        String[] modifyData = {storeNumber, storeCategory, storeName, storeOwner, storeAddress};
        System.out.println("actionModifyUpdate : " + storeNumber + storeCategory + storeName + storeOwner + storeAddress);
        modifyDeleteModelSingleton.modifyUpdate(modifyData, originalStoreNumber); // db에서 수정하기위해 새로 입력받은 textfield 값을 배열형태로 넘겨줌
         
        modifyView.dispose(); // 떠있는 창이 사라짐
        modifyView.setTitle("가맹점 신청 관리"); // gui창 제목 지정  
        modifyView.setVisible(true); // 떠있는 창이 변경된 데이터로 다시 나타남
        systemAdminView.dispose(); // 현재 떠있는 창을 끄고
        refreshTable2(); // 바뀐 정보로 table을 업데이트하고
        systemAdminView.setVisible(true); // 변경된 db의 데이터로 jtable을 새로 보여줌
    }
    
    public void actionDelete(){
        DefaultTableModel tableModel2 = (DefaultTableModel)modifyDeleteTable.getModel(); // 실행했을 때 저장된 상태의 jtable의 모델을 가져온다.
        int selectedRow = modifyDeleteTable.getSelectedRow(); // jtable에서 선택한 row의 index 번호        
        System.out.println(selectedRow);
        
        if (selectedRow != -1){
            String storeNumber = (String) modifyDeleteTable.getValueAt(selectedRow, 1); // 선택한 row에서 사업자 등록번호를 가져옴          
            modifyDeleteModelSingleton.storeDataDelete(storeNumber); // 데이터를 삭제 할 db 호출        
            tableModel2.removeRow(selectedRow); // 선택한 row가 table에서 삭제된걸 화면에 바로 보여준다.
            refreshTable2();
        } else {
            JOptionPane.showMessageDialog(null, "삭제할 데이터가 선택되지 않았습니다. 다시 선택해 주세요.");
        }     
    }    
    
    public void refreshTable2(){
    DefaultTableModel tableModel2 = systemAdminView.getRefreshTable2(); // 가장 최신의 jtable 모델을 가져온다.
    //tableModel2 = systemAdminView.getRefreshTable2(); // 가장 최신의 jtable 모델을 가져온다.
    tableModel2.setRowCount(0); // 전체 테이블 화면의 row를 모두 비운다.           
    tableModel2 = modifyDeleteModelSingleton.setTable2(); //select문 결과를 담는다.
    modifyDeleteTable.setModel(tableModel2); // controller에 저장되어있는 jtable 객체도 바뀐 값을 넣어준다.
    systemAdminView.setRefreshTable2(tableModel2); // 업데이트된 tablemodel을 jtable에 넣어준다.
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String sql;
        if(e.getSource() == approveBtn){  // 가맹점 승인을 위해 "승인 버튼" 클릭시 발생하는 이벤트
            System.out.println("승인 버튼 누름");
            sql = "update store_list set store_state = 'y' where store_number = ?"; // 선택한 store_number의 store_state를 y로 변경
            actionUpdate(sql); // sql문에 따른 승인 또는 거절 버튼의 기능 실행                
        } else if(e.getSource() == refuseBtn){  // 가맹점 승인을 위해 "거절 버튼" 클릭시 발생하는 이벤트
            System.out.println("거절 버튼 누름");
            sql = "update store_list set store_state = 'n' where store_number = ?"; // 선택한 store_number의 store_state를 n으로 변경
            actionUpdate(sql); // sql문에 따른 승인 또는 거절 버튼의 기능 실행     
        } else if(e.getSource() == refreshBtn1){ // 데이터를 가져오기위해 "새로고침 버튼" 클릭 시 발생하는 이벤트
            System.out.println("새로고침1 버튼 누름");
            refreshTable1();            
        } else if(e.getSource() == modifyBtn){  // 데이터 수정을 위해 "수정 버튼" 클릭 시 발생하는 이벤트
            System.out.println("수정 버튼 누름");            
            actionModify();
        } else if(e.getSource() == modifyCompleteBtn){ // "수정 버튼" 클릭시 새로 뜬 창의 "수정 완료 버튼" 클릭 시 발생하는 이벤트
            System.out.println("ModifyView의 수정 완료 버튼 누름");
            actionModifyUpdate();            
        } else if(e.getSource() == deleteBtn){ // 데이터 삭제를 위해 "삭제 버튼" 클릭 시 발생하는 이벤트
            System.out.println("삭제 버튼 누름");
            actionDelete();            
        } else if(e.getSource() == refreshBtn2){ // 데이터를 가져오기위해 "새로고침 버튼" 클릭 시 발생하는 이벤트
            System.out.println("새로고침2 버튼 누름");
            refreshTable2();            
        } 
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        // TODO code application logic here
        SystemAdminController controller = new SystemAdminController();   
    }
}
