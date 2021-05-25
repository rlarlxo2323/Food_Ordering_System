package NewUser;

import Address_API.dial;
import Connect_DB.Connect_DB;
import Login.Login_Frame;
import java.awt.Color;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author rlarl
 */
public abstract class Abstract {
    JFrame Frame;
    JTextField Name_Field;
    JTextField Age_Field;
    JTextField RRN_Field;
    JTextField ID_Field;
    JButton Overlap_Button;
    JLabel Overlap_Label;
    JTextField PW_Field;
    JLabel PW_Test_Label;
    JTextField RE_PW_Field;
    JLabel RE_PW_Test_Label;
    JRadioButton M_RButton;
    JRadioButton FM_RButton;
    JTextField Address_Field;
    JTextField Set_Address;
    JTextField Detail_Address;
    JButton Address_Button;
    JButton End_Button;
    String[] data;
    
    JTextField Brand_Name_Field;
    JTextField Brand_Num_Field;
    JButton Upload_Button;
    JLabel File_Name_Label;
    JFileChooser FileChooser;
    
    
    public final void Join( JFrame Frame,
                            JTextField Name,
                            JTextField Age,
                            JTextField RRN,
                            JTextField ID,
                            JButton Overlap_Button,
                            JLabel Overlap_Label,
                            JTextField PW,
                            JLabel PW_Test,
                            JTextField RE_PW,
                            JLabel RE_PW_Test,
                            JRadioButton M_RButton,
                            JRadioButton FM_RButton,
                            JTextField Address_Field,
                            JTextField Set_Address,
                            JTextField Detail_Address,
                            JButton Address_Button,
                            JButton End_Button){
        getData(Frame, Name, Age, RRN, ID, Overlap_Button, Overlap_Label, PW, PW_Test, RE_PW, RE_PW_Test, M_RButton, FM_RButton, Address_Field, Set_Address, Detail_Address, Address_Button, End_Button);
        RRN();
        verify_ID();
        verify_PW();
        verify_RE_PW();
        End();
        Search_Address();
        Upload();
    }
    public final void Brand_Data(JTextField Brand_Name_Field, JTextField Brand_Num_Field, JButton Upload_Button, JLabel File_Name_Label, JFileChooser FileChooser){
        get_Brand_Data(Brand_Name_Field, Brand_Num_Field, Upload_Button, File_Name_Label, FileChooser);
    }

    public abstract void End();
    public abstract void Upload();
    
    public void getData(JFrame Frame, JTextField Name, JTextField Age, JTextField RRN, JTextField ID,
            JButton Overlap_Button, JLabel Overlap_Label, JTextField PW, JLabel PW_Test,
            JTextField RE_PW, JLabel RE_PW_Test, JRadioButton M_RButton, JRadioButton FM_RButton,
            JTextField Address_Field, JTextField Set_Address, JTextField Detail_Address,
            JButton Address_Button, JButton End_Button){
            this.Frame = Frame;
            this.Name_Field = Name;
            this.Age_Field = Age;
            this.RRN_Field = RRN;
            this.ID_Field = ID;
            this.Overlap_Button = Overlap_Button;
            this.Overlap_Label = Overlap_Label;
            this.PW_Field = PW;
            this.PW_Test_Label = PW_Test;
            this.RE_PW_Field = RE_PW;
            this.RE_PW_Test_Label = RE_PW_Test;
            this.M_RButton = M_RButton;
            this.FM_RButton = FM_RButton;
            this.Address_Field = Address_Field;
            this.Set_Address = Set_Address;
            this.Detail_Address = Detail_Address;
            this.Address_Button = Address_Button;
            this.End_Button = End_Button;
    }
    
    public void get_Brand_Data(JTextField Brand_Name_Field, JTextField Brand_Num_Field, JButton Upload_Button, JLabel File_Name_Label, JFileChooser FileChooser){
        this.Brand_Name_Field = Brand_Name_Field;
        this.Brand_Num_Field = Brand_Num_Field;
        this.Upload_Button = Upload_Button;
        this.File_Name_Label = File_Name_Label;
        this.FileChooser = FileChooser;
    }
    
    public void RRN(){
        RRN_Field.addFocusListener(new FocusListener(){
            @Override
            public void focusLost(FocusEvent e) {
                if(e.getSource()==RRN_Field){
                    String years = RRN_Field.getText();
                    years = years.substring(0, 2);
                    if(Integer.parseInt(years)>50){
                        years="19"+years;
                    }
                    else{
                         years="20"+years;
                    }
                    int year = Integer.parseInt(years);
                    Age_Field.setText(Integer.toString(2021-year+1));
                }
            }
            @Override
            public void focusGained(FocusEvent e) {
            }
        });
    }
    
    public void verify_ID(){
        Overlap_Button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = ID_Field.getText(); //ID 값 변수
                if(!ID.equals("")){
                    try{
                    Connect_DB db = new Connect_DB();
                    db.Use_DB();
                    ResultSet rs = db.Command_ExecuteQuery("select ID from Actor where ID='"+ID+"'"); //ID가 입력받은 ID와 같은 데이터 검사
                    if(rs.next()){ //같은 ID가 있으면
                        Overlap_Label.setText("이미 존재하는 아이디입니다.");
                        Overlap_Label.setForeground(Color.red);
                    }
                    else{
                        ID_Field.setEnabled(false);
                        Overlap_Label.setText("사용 가능한 아이디입니다.");
                        Overlap_Label.setForeground(Color.blue);
                    }
                    }catch(SQLException er){
                        er.printStackTrace();
                    }
                }else{
                    Overlap_Label.setText("아이디를 입력하세요.");
                    Overlap_Label.setForeground(Color.red);
                }
                
            }
        });
    }
    
    public void verify_PW(){
        PW_Field.addCaretListener(new CaretListener(){
            @Override
            public void caretUpdate(CaretEvent e) {
                if(PW_Field.getText().equals("")){ //텍스트 필드가 비어있는 경우
                    PW_Test_Label.setText("비어있음.");
                    PW_Test_Label.setForeground(Color.red);
                }
                else if(PW_Field.getText().length()<7){ //텍스트 필드의 값이 7자리 이하
                    PW_Test_Label.setText("너무 짧습니다.");
                    PW_Test_Label.setForeground(Color.red);
                }
                else{//텍스트 필드 길이가 조건에 만족 시
                    boolean Number = false; //영문자 판별 부호
                    boolean Alpha = false; //숫자 판별 부호
                    for(int i=0; i<PW_Field.getText().length();i++){
                    int index = PW_Field.getText().charAt(i);

                    if(index>=65 && index<=122){ //영어 검출
                        Alpha = true;  
                    }
                    else if(index<=57 && index >= 48){//숫자 검출
                        Number = true;
                    }
                   }
                    if(Number==false){
                        PW_Test_Label.setText("숫자를 포함해 주세요.");
                    }
                    else if(Alpha==false){
                        PW_Test_Label.setText("영문자를 포함해 주세요");
                    }
                    else{//모든 조건 만족
                        PW_Test_Label.setText("O");
                        PW_Test_Label.setForeground(Color.blue);
                    }
                }
            }
            
        });
    }
    
    public void verify_RE_PW(){
        RE_PW_Field.addCaretListener(new CaretListener(){
            @Override
            public void caretUpdate(CaretEvent e) {
                if(RE_PW_Field.getText().equals("")){
                    RE_PW_Test_Label.setText("비어있음.");
                    RE_PW_Test_Label.setForeground(Color.red);
                }
                else if(!RE_PW_Field.getText().equals(PW_Field.getText())){//이전에 입력한 패스워드와 불일치 시
                    RE_PW_Test_Label.setText("일치하지 않습니다.");
                    RE_PW_Test_Label.setForeground(Color.red);
                } else if(RE_PW_Field.getText().equals(PW_Field.getText())){
                    RE_PW_Test_Label.setText("O");
                    RE_PW_Test_Label.setForeground(Color.blue);
                }
            }
        });
    }
    
    public void Search_Address(){
        Address_Button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dial s = new dial(Frame, true);
                s.setVisible(true);
                data=dial.Select_Adr;
                Address_Field.setText(data[0]);
                Set_Address.setText(data[1]);
            }
            
        });
    }
}
