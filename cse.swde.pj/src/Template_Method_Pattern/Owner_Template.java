/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template_Method_Pattern;

import Connect_DB.Connect_DB;
import Login.Login_Frame;
import SFTP.JSchWrapper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author rlarl
 */
public class Owner_Template extends Abstract {

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
                    &&(!Brand_Name_Field.getText().equals(""))
                    &&(!Brand_Num_Field.getText().equals(""))
                    &&(!Set_Address.getText().equals(""))
                    &&(!File_Name_Label.getText().equals(""))){
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
                            db.Use_DB();
                            db.Command_ExecuteUpdate("insert into Actor values('"+ID+"', '"+PW+"', '"+RRN+"', '"+Name+"', '"+Age+"', '"+Gender+"', '"+Address+"', 'owner');");
                            JOptionPane.showMessageDialog(null, "회원가입 완료");
                            Login_Frame frame = new Login_Frame();
                            frame.setVisible(true);
                            Frame.dispose();
                            
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
        Upload_Button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JSchWrapper jschWrapper = null;
                FileChooser.showOpenDialog(null);
                String path = FileChooser.getSelectedFile().getPath();
                String file_name = FileChooser.getSelectedFile().getName();
                try {
                    jschWrapper = new JSchWrapper();

                    // SFTP 접속하기 (주소, 포트번호, 사용자아이디, 패스워드)
                    jschWrapper.connectSFTP("118.67.130.238", 22, "root", "rl794613!");

                    // /itarchives 폴더 위치에 test 폴더 생성
                    // C:\\test\\upload\\0001.png 파일을 /itarchives/test 위치에 업로드
                    jschWrapper.uploadFile(path, "/home/sub_admin/cse_swde/img");

                    // /itarchives/test/0001.png 파일을 C:\\test\\download 위치에 다운로드
                    //jschWrapper.downloadFile("/itarchives/test/0001.png", "C:\\test\\download", false);
                    File_Name_Label.setText(file_name);
                } catch (Exception er) {
                    er.printStackTrace();

                } finally {
                    // SFTP 접속해제
                    jschWrapper.disconnectSFTP();
                }
            }
            
        });
    }
    
    
    
}
