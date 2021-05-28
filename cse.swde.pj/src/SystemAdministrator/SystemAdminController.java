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
    SystemAdminView view;
    ApproveRefuseModel model1;
    ModifyDeleteModel model2;
    
    JTable table1;
    JTable table2;
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    //DefaultTableModel tableModel1;
    
    public SystemAdminController(){
        view = new SystemAdminView();      
        model1 =  ApproveRefuseModel.getInstance();       
        model2 =  ModifyDeleteModel.getInstance();
        
        table1 = view.getTable1();
        table2 = view.getTable2();
        button1 = view.getButton1();
        button1.addActionListener(this);
        button2 = view.getButton2();
        button2.addActionListener(this);
        button3 = view.getButton3();
        button3.addActionListener(this);
        button4 = view.getButton4();
        button4.addActionListener(this);
    }
 
    public void setQuery(String sql){
        model1.sql = sql;
    }   

    public void actionUpdate(String sql){
        int selectedRow = table1.getSelectedRow(); // jtable에서 선택한 row의 index
        if (selectedRow > 0){
            Object selectedStoreNumber = table1.getValueAt(selectedRow, 1); // jtable에서 선택한 row의 store_number
            System.out.println(selectedStoreNumber);                  
            setQuery(sql);
            model1.storeStateUpdate(selectedStoreNumber); // db에서 선택한 store_number의 store_state를 y로 변경       
            //refreshTable(); // 변경된 db의 데이터로 jtable을 새로 보여줌
        } else {
            JOptionPane.showMessageDialog(null, "승인 처리 할 데이터가 선택되지 않았습니다. 다시 선택해 주세요.");
        }   
    }

    public void actionDelete(){
        DefaultTableModel tableModel2 = (DefaultTableModel)table2.getModel();
        int row = table2.getSelectedRow(); 
        System.out.println(row);
        if (row > 0){
            String storeNumber = (String) table2.getValueAt(row, 1); // 선택한 row에서 사업자 등록번호를 가져옴            
            model2.deleteRow(storeNumber); // 데이터를 삭제 할 db 호출
            //model.removeRow(row); // row를 table에서 삭제     
            tableModel2.removeRow(row); // 선택한 row가 table에서 삭제된걸 화면에 바로 보여준다.
        } else {
            JOptionPane.showMessageDialog(null, "삭제할 데이터가 선택되지 않았습니다. 다시 선택해 주세요.");
        }     
    }
    
    public void refreshTable1(){
    DefaultTableModel tableModel1 = view.getRefreshTable1();
    //tableModel1 = view.getRefreshTable1(); // 가장 최신의 jtable 모델을 가져온다.
    tableModel1.setRowCount(0); // 전체 테이블 화면을 모두 비운다.           
    tableModel1 = model1.setTable1(); //select문 결과를 담는다.
    table1.setModel(tableModel1); // controller에 저장되어있는 jtable 객체도 바뀐 값을 넣어준다.
    view.setRefreshTable1(tableModel1); // 업데이트된 tablemodel을 jtable에 넣어준다.
    }
    
    public void refreshTable2(){
    DefaultTableModel tableModel2 = view.getRefreshTable2();
    //tableModel2 = view.getRefreshTable2(); // 가장 최신의 jtable 모델을 가져온다.
    tableModel2.setRowCount(0); // 전체 테이블 화면을 모두 비운다.           
    tableModel2 = model2.setTable2(); //select문 결과를 담는다.
    table2.setModel(tableModel2); // controller에 저장되어있는 jtable 객체도 바뀐 값을 넣어준다.
    view.setRefreshTable2(tableModel2); // 업데이트된 tablemodel을 jtable에 넣어준다.
    }
    
    public void tab1Buttons(){}
    
    public void actionPerformed(ActionEvent e){
        String sql;
        if(e.getSource() == button1){  // 가맹점 승인을 위해 "승인 버튼" 클릭시 발생하는 이벤트
            System.out.println("승인 버튼 누름");
            sql = "update store_list set store_state = 'y' where store_number = ?"; // 선택한 store_number의 store_state를 y로 변경
            actionUpdate(sql);            
            refreshTable1();
        } else if(e.getSource() == button2){  // 가맹점 승인을 위해 "거절 버튼" 클릭시 발생하는 이벤트
            System.out.println("거절 버튼 누름");
            sql = "update store_list set store_state = 'n' where store_number = ?"; // 선택한 store_number의 store_state를 n으로 변경
            actionUpdate(sql);    
            refreshTable1();
        } else if(e.getSource() == button3){  // 데이터 수정을 위해 "수정 버튼" 클릭 시 발생하는 이벤트
            System.out.println("수정 버튼 누름");
            // 추가해야함
        } else if(e.getSource() == button4){ // 데이터 삭제를 위해 "삭제 버튼" 클릭 시 발생하는 이벤트
            System.out.println("삭제 버튼 누름");
            actionDelete();
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
