package Login;

import Connect_DB.Connect_DB;
import NewUser.NewUser_Frame;
import StoreOwner.StoreOwnerController;
import SystemAdministrator.SystemAdminController;
import User.User;
import java.awt.Cursor;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Login_Frame extends javax.swing.JFrame{
    
    public static String Login_Session;
    
    public Login_Frame(){
        initComponents();
        NewUser_Label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ID_Field = new javax.swing.JTextField();
        PW_Field = new javax.swing.JPasswordField();
        Login_Button = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        NewUser_Label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("굴림", 1, 14)); // NOI18N
        jLabel1.setText("P W");

        jLabel2.setFont(new java.awt.Font("굴림", 1, 14)); // NOI18N
        jLabel2.setText("I  D");

        Login_Button.setText("로그인");
        Login_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Login_ButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("회원이 아니시라면?");

        NewUser_Label.setFont(new java.awt.Font("굴림", 1, 12)); // NOI18N
        NewUser_Label.setForeground(new java.awt.Color(51, 51, 255));
        NewUser_Label.setText("회원가입");
        NewUser_Label.setToolTipText("회원가입");
        NewUser_Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NewUser_LabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ID_Field, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PW_Field, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(Login_Button)
                .addGap(45, 45, 45))
            .addGroup(layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NewUser_Label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(ID_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(PW_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(99, 99, 99))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(Login_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(NewUser_Label)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Login_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Login_ButtonActionPerformed
        String ID = ID_Field.getText();
        String PW = PW_Field.getText();
        if((ID.equals("")) || (PW.equals(""))){
            JOptionPane.showMessageDialog(null, "아이디 및 패스워드를 입력하세요.");
        }
        else{
            try {
                Connect_DB db = new Connect_DB();
                Connection con = db.getConnection();
                PreparedStatement preparedStatement = null;
                String sql = "select id, pw, class from actor where id=? and pw=?";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, ID);
                preparedStatement.setString(2, PW);
                ResultSet rs = preparedStatement.executeQuery();
                if(rs.next()){
                    
                    String data = rs.getString("class");
                    System.out.println(data);
                    if(data.equals("owner")){
                        sql = "select * from store_list where id=?";
                        preparedStatement = con.prepareStatement(sql);
                        preparedStatement.setString(1, ID);
                        rs = preparedStatement.executeQuery();
                        rs.next();
                        String state = rs.getString("store_state");
                        System.out.println(state);
                        if(state.equals("y")){
                            sql = "select store_number from store_list where id = ?";
                            preparedStatement = con.prepareStatement(sql);
                            preparedStatement.setString(1, ID);
                            rs = preparedStatement.executeQuery();
                            if(rs.next()){
                                String store_num = rs.getString("store_number");
                                JOptionPane.showMessageDialog(null, "로그인 성공");
                                Login_Session = ID;
                                StoreOwnerController view= new StoreOwnerController(store_num);
                                this.setVisible(false);
                            }else{
                                JOptionPane.showMessageDialog(null, "로그인 실패");
                            }
                            
                        }
                        else if(state.equals("w")){
                            JOptionPane.showMessageDialog(null, "로그인 대기");
                        }
                        else{
                            int bool = JOptionPane.showConfirmDialog(null, "가맹점 신청이 취소되었습니다.\n해당 계정을 유지하시겠습니까?(취소 시 계정 자동 삭제)",
                                    "회원가입", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                            if(bool==1){
                                sql = "delete from store_list where id=?";
                                preparedStatement = con.prepareStatement(sql);
                                preparedStatement.setString(1, ID);
                                preparedStatement.executeUpdate();
                                sql = "delete from actor where id=?";
                                preparedStatement = con.prepareStatement(sql);
                                preparedStatement.setString(1, ID);
                                preparedStatement.executeUpdate();
                                JOptionPane.showMessageDialog(null, "계정이 삭제되었습니다.");
                            }
                        }
                    }
                    else if(data.equals("admin")){
                        JOptionPane.showMessageDialog(null, "로그인 성공");
                        Login_Session = ID;
                        SystemAdminController view = new SystemAdminController();
                        this.setVisible(false);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "로그인 성공");
                        Login_Session = ID;
                        User frame = new User();
                        frame.setVisible(true);
                        this.setVisible(false);
                    }
                    
                }
                else{
                    JOptionPane.showMessageDialog(null, "아이디나 패스워드가 일치하지 않습니다.");
                } preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Login_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_Login_ButtonActionPerformed

    private void NewUser_LabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewUser_LabelMouseClicked
        NewUser_Frame s = new NewUser_Frame();
        s.setVisible(true);
        dispose();
    }//GEN-LAST:event_NewUser_LabelMouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login_Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ID_Field;
    private javax.swing.JButton Login_Button;
    private javax.swing.JLabel NewUser_Label;
    private javax.swing.JPasswordField PW_Field;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
