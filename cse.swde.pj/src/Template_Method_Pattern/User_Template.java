/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template_Method_Pattern;

import Connect_DB.Connect_DB;
import Login.Login_Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author rlarl
 */
public class User_Template extends Abstract {
    @Override
    public void End(){
        End_Button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Overlap_Label.getText().contains("가능")
                    &&(PW_Test_Label.getText().equals("O"))
                    &&(RE_PW_Test_Label.getText().equals("O"))
                    &&(!Name_Field.getText().equals(""))
                    &&(!RRN_Field.getText().equals(""))
                    &&(!Detail_Address.getText().equals(""))
                    &&(!Set_Address.getText().equals(""))){
                        String ID = ID_Field.getText();
                        String PW = PW_Field.getText();
                        String Name = Name_Field.getText();
                        String RRN = RRN_Field.getText();
                        int Age = Integer.parseInt(Age_Field.getText());
                        String Gender="";
                        if(M_RButton.isSelected()){
                            Gender = M_RButton.getText();
                        }
                        else if(FM_RButton.isSelected()){
                            Gender = FM_RButton.getText();
                        }
                        String Address = (Set_Address.getText()+"/"+Detail_Address.getText());
                        System.out.println(Address);

                        int bool = JOptionPane.showConfirmDialog(null, "입력하신 정보로 가입하시겠습니까?", "회원가입", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

                        if(bool==0){
                            Connect_DB db = new Connect_DB();
                            Connection con;
                            try {
                                con = db.getConnection();
                                PreparedStatement preparedStatement = null;
                                String sql = "insert into actor values(?, ?, ?, ?, ?, ?, ?, 'user')";
                                preparedStatement = con.prepareStatement(sql);
                                preparedStatement.setString(1, ID);
                                preparedStatement.setString(2, PW);
                                preparedStatement.setString(3, Name);
                                preparedStatement.setString(4, RRN);
                                preparedStatement.setString(5, Gender);
                                preparedStatement.setString(6, Address);
                                preparedStatement.setInt(7, Age);
                                preparedStatement.executeUpdate();
                                JOptionPane.showMessageDialog(null, "회원가입 완료");
                                
                                Login_Frame frame = new Login_Frame();
                                frame.setVisible(true);
                                Frame.dispose();
                            }catch(SQLException er){
                                er.printStackTrace();
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "회원가입 취소");
                        }

                }
                else{
                    JOptionPane.showMessageDialog(null, "다시 한번 확인하세요.","회원가입 실패", JOptionPane.WARNING_MESSAGE);
                }
                    }
        });
    }

    @Override
    public void Upload() {
    }

    @Override
    public void Category() {
    }
}
