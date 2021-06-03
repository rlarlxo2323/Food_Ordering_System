/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import GetSet.StoreNum;
import GetSet.OptionValue;
import Decorator.Basic;
import Decorator.Menu;
import Memento.CareTaker;
import static Memento.CareTaker.mementos;
import Memento.Information;
import Memento.Memento;
import Strategy.Price;
import Strategy.OptionPrice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 김두현
 */
public class Store extends javax.swing.JFrame {
<<<<<<< Updated upstream
    //Stack<Memento> mementos = new Stack<>();

    Information info = new Information("장바구니가 없습니다", "장바구니가 없습니다", 0);
=======
    Information info = new Information(null, null, null, 0);
>>>>>>> Stashed changes
    CareTaker caretaker = new CareTaker();


    public Store() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane = new javax.swing.JTabbedPane();
        Menu_Panel = new javax.swing.JPanel();
        Menu_jScrollPane = new javax.swing.JScrollPane();
        Menu_jTable = new javax.swing.JTable();
        Basket_jButton = new javax.swing.JButton();
        Menu_jLabel = new javax.swing.JLabel();
        Basket_jTabbedPane = new javax.swing.JTabbedPane();
        Basket_jScrollPane = new javax.swing.JScrollPane();
        Basket_jTable = new javax.swing.JTable();
        Size_jComboBox = new javax.swing.JComboBox<>();
        Price_jLabel = new javax.swing.JLabel();
        Pay_jButton = new javax.swing.JButton();
        Storeinfo_jPanel = new javax.swing.JPanel();
        Review_jPanel = new javax.swing.JPanel();
        Review_jTextField = new javax.swing.JTextField();
        Review_jScrollPane = new javax.swing.JScrollPane();
        Review_jTable = new javax.swing.JTable();
        Review_jButton = new javax.swing.JButton();
        Review_jSlider = new javax.swing.JSlider();
        Review_jLabel = new javax.swing.JLabel();
        Storename_jLabel = new javax.swing.JLabel();
        Back_jButton = new javax.swing.JButton();
        Totalreview_jLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        Menu_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MENU", "SIZE", "PRICE"
            }
        ));
        Menu_jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Menu_jTableMouseClicked(evt);
            }
        });
        Menu_jScrollPane.setViewportView(Menu_jTable);

        Basket_jButton.setText("장바구니 담기");
        Basket_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Basket_jButtonActionPerformed(evt);
            }
        });

        Menu_jLabel.setText("-");

        Basket_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MENU", "SIZE", "PRICE"
            }
        ));
        Basket_jScrollPane.setViewportView(Basket_jTable);

        Basket_jTabbedPane.addTab("장바구니", Basket_jScrollPane);

        Size_jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Small", "Medium", "Large" }));

        Price_jLabel.setText("jLabel3");

        Pay_jButton.setText("결제하기");
        Pay_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pay_jButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Menu_PanelLayout = new javax.swing.GroupLayout(Menu_Panel);
        Menu_Panel.setLayout(Menu_PanelLayout);
        Menu_PanelLayout.setHorizontalGroup(
            Menu_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Menu_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Menu_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Basket_jTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                    .addComponent(Menu_jScrollPane))
                .addGap(18, 18, 18)
                .addGroup(Menu_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Menu_PanelLayout.createSequentialGroup()
                        .addGroup(Menu_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Basket_jButton)
                            .addComponent(Menu_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Size_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Menu_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Price_jLabel)
                        .addComponent(Pay_jButton))))
        );
        Menu_PanelLayout.setVerticalGroup(
            Menu_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Menu_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Menu_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Menu_jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Menu_PanelLayout.createSequentialGroup()
                        .addComponent(Menu_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Size_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Basket_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(Menu_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Menu_PanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                        .addComponent(Price_jLabel)
                        .addGap(18, 18, 18)
                        .addComponent(Pay_jButton)
                        .addContainerGap())
                    .addGroup(Menu_PanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Basket_jTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))))
        );

        jTabbedPane.addTab("메뉴 확인", Menu_Panel);

        javax.swing.GroupLayout Storeinfo_jPanelLayout = new javax.swing.GroupLayout(Storeinfo_jPanel);
        Storeinfo_jPanel.setLayout(Storeinfo_jPanelLayout);
        Storeinfo_jPanelLayout.setHorizontalGroup(
            Storeinfo_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 679, Short.MAX_VALUE)
        );
        Storeinfo_jPanelLayout.setVerticalGroup(
            Storeinfo_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 463, Short.MAX_VALUE)
        );

        jTabbedPane.addTab("가맹점 정보", Storeinfo_jPanel);

        Review_jTextField.setText("리뷰 작성란");
        Review_jTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Review_jTextFieldActionPerformed(evt);
            }
        });

        Review_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "메뉴 이름", "리뷰", "평점", "작성일"
            }
        ));
        Review_jScrollPane.setViewportView(Review_jTable);

        Review_jButton.setText("작성");
        Review_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Review_jButtonActionPerformed(evt);
            }
        });

        Review_jSlider.setMaximum(5);
        Review_jSlider.setMinimum(1);
        Review_jSlider.setToolTipText("");
        Review_jSlider.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));

        Review_jLabel.setFont(new java.awt.Font("굴림", 1, 18)); // NOI18N

        javax.swing.GroupLayout Review_jPanelLayout = new javax.swing.GroupLayout(Review_jPanel);
        Review_jPanel.setLayout(Review_jPanelLayout);
        Review_jPanelLayout.setHorizontalGroup(
            Review_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Review_jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Review_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Review_jTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                    .addComponent(Review_jScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Review_jPanelLayout.createSequentialGroup()
                        .addComponent(Review_jSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Review_jButton)))
                .addContainerGap())
            .addGroup(Review_jPanelLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(Review_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Review_jPanelLayout.setVerticalGroup(
            Review_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Review_jPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(Review_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Review_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Review_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Review_jPanelLayout.createSequentialGroup()
                        .addComponent(Review_jSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Review_jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Review_jPanelLayout.createSequentialGroup()
                        .addComponent(Review_jButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane.addTab("리뷰 작성", Review_jPanel);

        Storename_jLabel.setFont(new java.awt.Font("굴림", 3, 24)); // NOI18N

        Back_jButton.setText("뒤로가기");
        Back_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Back_jButtonActionPerformed(evt);
            }
        });

        Totalreview_jLabel.setFont(new java.awt.Font("굴림", 1, 18)); // NOI18N

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(Storename_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Totalreview_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
            .addGroup(layout.createSequentialGroup()
                .addComponent(Back_jButton)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(Back_jButton)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Totalreview_jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Storename_jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                        .addGap(19, 19, 19)
                        .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Menu_jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Menu_jTableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) Menu_jTable.getModel();
        int i = Menu_jTable.getSelectedRow();

        Menu_jLabel.setText(model.getValueAt(i, 0).toString());

        OptionValue ov = new OptionValue();
        int price = ov.getMenuPrice();
        price = Integer.parseInt(model.getValueAt(i, 2).toString());
        ov.setMenuPrice(price);

        String myOption = ov.getMyOption();
        myOption = (String) model.getValueAt(i, 1);
        ov.setMyOption(myOption);
        System.out.println(myOption);
        Price option2 = new OptionPrice();
        option2.performFly();

    }//GEN-LAST:event_Menu_jTableMouseClicked

    private void Basket_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Basket_jButtonActionPerformed
        // TODO add your handling code here:      
        String menu = Menu_jLabel.getText();
        String sizeBox = (String) Size_jComboBox.getSelectedItem();
<<<<<<< Updated upstream

=======
        
        int sum = 0;
>>>>>>> Stashed changes
        if (menu.equals("-")) {
            JOptionPane.showMessageDialog(null, "메뉴를 선택해 주세요.");
        } else {
            if (sizeBox.equals("-")) {
                JOptionPane.showMessageDialog(null, "옵션을 선택해 주세요.");
            } else {
                Menu m = new Basic();
                Price option2 = new OptionPrice();
                int price = option2.display();

                DefaultTableModel model = (DefaultTableModel) Basket_jTable.getModel();

                Object[] row = new Object[3];
                row[0] = menu;
                row[1] = sizeBox;
                row[2] = price;
                model.addRow(row);
                /*
                //System.out.println(info.getData1());
                info.setData1(menu);
                info.setData2(sizeBox);
                info.setData3(price);
                caretaker.push(info.CreateMemento());
                System.out.println(info.getData1());*/
            }
        }
<<<<<<< Updated upstream
=======

        for (int i = 0; i < Basket_jTable.getRowCount(); i++) {
            String pocket = Basket_jTable.getValueAt(i, 3).toString();
            int s = Integer.parseInt(pocket);
            sum += s;
            String money = Integer.toString(sum);
            Price_jLabel.setText(money);
        }

>>>>>>> Stashed changes
        Size_jComboBox.setSelectedItem("-");
        Menu_jLabel.setText("-");
    }//GEN-LAST:event_Basket_jButtonActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        Price option2 = new OptionPrice();
        option2.performFly();

        StoreNum storelist = new StoreNum();
        String sl = storelist.getStoreList();

        Storename_jLabel.setText(sl);
        String s = storelist.getStore();
        DefaultTableModel model1 = (DefaultTableModel) Menu_jTable.getModel();
        DefaultTableModel model2 = (DefaultTableModel) Review_jTable.getModel();
        DefaultTableModel model3 = (DefaultTableModel) Basket_jTable.getModel();

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String menuSql = "select * from menu_info where menu_info.store_number = ";
        String myRating = "select avg(rating) from menu_info join reviews using (store_number) where reviews.store_number =";
        String reviewSql = "select * from menu_info join reviews using (store_number) where reviews.store_number =";

        try {
            con = DriverManager.getConnection("jdbc:mysql://115.85.182.30:3306/cse_swde_DB?zeroDateTimeBehavior=CONVERT_TO_NULL&characterEncoding=UTF-8&serverTimezone=UTC", "cse_swde", "password");
            st = con.prepareStatement(myRating + "'" + s + "'");
            rs = st.executeQuery();
            while (rs.next()) {
                String myRating2 = rs.getString("avg(rating)");
                Totalreview_jLabel.setText(myRating2);
            }
            //메뉴
            st = con.prepareStatement(menuSql + "'" + s + "'");
            rs = st.executeQuery();

            while (rs.next()) {
                String menu = rs.getString("menu_name");
                String option = rs.getString("menu_option");
                String price = rs.getString("menu_price");

                Object data[] = {menu, option, price};
                model1.addRow(data);
            }
            /*
            //리뷰
            st = con.prepareStatement(reviewSql + "'" + s + "'");
            rs = st.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String menuName = rs.getString("menu_name");
                String review = rs.getString("review");
                String storeRating = rs.getString("rating");
                String nowTime = rs.getString("time");

                Object data2[] = {id, menuName, review, storeRating, nowTime};
                model2.addRow(data2);
            }*/
 
            //장바구니
            if (mementos.isEmpty()) {
                System.out.println(info.getData1());
                caretaker.push(info.CreateMemento());
                Object data3[] = {info.getData1(), info.getData2(), info.getData3()};
                model3.addRow(data3);
            } else {
                while (!mementos.isEmpty()) {
                    info.RestorMemento(caretaker.pop());
                    Object data3[] = {info.getData1(), info.getData2(), info.getData3()};
                    model3.addRow(data3);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(StoreList.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_formWindowOpened

    private void Pay_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pay_jButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pay_jButtonActionPerformed

    private void Back_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Back_jButtonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) Basket_jTable.getModel();
        int row = model.getRowCount();
        System.out.println(row);
        int col = model.getColumnCount();
        for (int i = 0; i < row; i++) {
            String name = (String) model.getValueAt(i, 0);
            String option = (String) model.getValueAt(i, 1);
            int price = (int) model.getValueAt(i, 2);
            info.setData1(name);
            info.setData2(option);
            info.setData3(price);
            caretaker.push(info.CreateMemento());
        }

        User user = new User();
        user.setVisible(true);
        dispose();

    }//GEN-LAST:event_Back_jButtonActionPerformed

    private void Review_jTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Review_jTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Review_jTextFieldActionPerformed

    private void Review_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Review_jButtonActionPerformed
        // TODO add your handling code here:

        StoreNum storelist = new StoreNum();
        String s = storelist.getStore();

        int rating = Review_jSlider.getValue();
        String ratingCheck = rating + "점";
        Review_jLabel.setText(ratingCheck);

        SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월dd일 HH시mm분ss초");
        Date time = new Date();
        String now = date.format(time);

        DefaultTableModel model = (DefaultTableModel) Review_jTable.getModel();
        String reviewText = "insert into reviews (id, menu_name, review, store_number, rating, time) values ('id', '치즈 핫도그',";
        String reviewSql = "select * from menu_info join reviews using (store_number) where menu_info.menu_name = reviews.menu_name and reviews.store_number =";
        String reviewInput = Review_jTextField.getText();

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int num;

        try {
            con = DriverManager.getConnection("jdbc:mysql://115.85.182.30:3306/cse_swde_DB?zeroDateTimeBehavior=CONVERT_TO_NULL&characterEncoding=UTF-8&serverTimezone=UTC", "cse_swde", "password");
            st = con.prepareStatement(reviewText + "'" + reviewInput + "'," + "'" + s + "'," + "'" + rating + "'," + "'" + now + "')");
            num = st.executeUpdate();

            Object review[] = new Object[5];
            review[0] = "id";
            review[1] = "메뉴이름";
            review[2] = reviewInput;
            review[3] = rating;
            review[4] = now;

            model.addRow(review);

            String myRating = "select avg(rating) from menu_info join reviews using (store_number) where reviews.store_number =";
            st = con.prepareStatement(myRating + s);
            rs = st.executeQuery();
            while (rs.next()) {
                String myRating2 = rs.getString("avg(rating)");
                Totalreview_jLabel.setText(myRating2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Store.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_Review_jButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        info.RestorMemento(caretaker.pop());
        System.out.println(info.getData1());
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Store.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Store.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Store.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Store.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Store().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back_jButton;
    public javax.swing.JButton Basket_jButton;
    public javax.swing.JScrollPane Basket_jScrollPane;
    public javax.swing.JTabbedPane Basket_jTabbedPane;
    public javax.swing.JTable Basket_jTable;
    public javax.swing.JPanel Menu_Panel;
    public static javax.swing.JLabel Menu_jLabel;
    public javax.swing.JScrollPane Menu_jScrollPane;
    public javax.swing.JTable Menu_jTable;
    private javax.swing.JButton Pay_jButton;
    public javax.swing.JLabel Price_jLabel;
    private javax.swing.JButton Review_jButton;
    private javax.swing.JLabel Review_jLabel;
    private javax.swing.JPanel Review_jPanel;
    private javax.swing.JScrollPane Review_jScrollPane;
    private javax.swing.JSlider Review_jSlider;
    private javax.swing.JTable Review_jTable;
    public javax.swing.JTextField Review_jTextField;
    public static javax.swing.JComboBox<String> Size_jComboBox;
    private javax.swing.JPanel Storeinfo_jPanel;
    private javax.swing.JLabel Storename_jLabel;
    private javax.swing.JLabel Totalreview_jLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JTabbedPane jTabbedPane;
    // End of variables declaration//GEN-END:variables
}
