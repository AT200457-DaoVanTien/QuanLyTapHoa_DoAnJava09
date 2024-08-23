/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.quanlytaphoa.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quanlytaphoa.Model.Account;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import static java.io.File.separator;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class DangNhap extends javax.swing.JFrame {

    private static String CUR_DIR = System.getProperty("user.dir");

    public DangNhap() {
        
        initComponents();
        NhanVien_RadioButton.setSelected(true);
        buttonGroup1.add(NhanVien_RadioButton);
        buttonGroup1.add(admin_RadioButton);
        setLocationRelativeTo(null);
        setTitle("Login");
        
        this.setSize(800, 800);
    }
    
    // hàm kiểm tra user và password
    private void try2Login() {
        MainPage1 homePage = new MainPage1();
        String user, pass;
        user = userTextField.getText();
        pass = new String(PasswordField.getPassword());
        boolean status = false;
        // kiểm tra user & password
        String nhanVienPath, adminPath;
        nhanVienPath = CUR_DIR + separator + "Manage Files" + separator + "Account.json";
        adminPath = CUR_DIR + separator + "Manage Files" + separator + "AdminAccount.json";
        ArrayList<Account> stuffList = new ArrayList<>(), adminList = new ArrayList<>();
        FileReader fr = null;
        if (NhanVien_RadioButton.isSelected()) {
            try {
                // đọc file Account.json vào stuffList
                fr = new FileReader(nhanVienPath);
                java.lang.reflect.Type accountType = new TypeToken<Collection<Account>>() {
                }.getType();
                Gson gson = new Gson();
                stuffList = gson.fromJson(fr, accountType);
                // kiểm tra đăng nhập đúng hay chưa
                for (Account a : stuffList) {
                    if (a.getUser().equals(user) && a.getPassword().equals(pass)) {
                        status = true;
                        break;
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (status) {
                this.setVisible(false);
                homePage.setLocationRelativeTo(null);
                homePage.setExtendedState(JFrame.MAXIMIZED_BOTH);
                homePage.setVisible(true);
                homePage.getQLTK_Button().setVisible(false);
                homePage.getQLTK_Panel().setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this,
                        "WRRONG ACCOUNT!!!",
                        "FAIL TO LOGIN",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            try {
                // đọc file Account.json vào stuffList
                fr = new FileReader(adminPath);
                java.lang.reflect.Type accountType = new TypeToken<Collection<Account>>() {
                }.getType();
                Gson gson = new Gson();
                adminList = gson.fromJson(fr, accountType);
                // kiểm tra đăng nhập đúng hay chưa
                for (Account a : adminList) {
                    if (a.getUser().equals(user) && a.getPassword().equals(pass)) {
                        status = true;
                        break;
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (status) {
                this.setVisible(false);
                homePage.setLocationRelativeTo(null);
                homePage.setExtendedState(JFrame.MAXIMIZED_BOTH);
                homePage.setVisible(true);
                homePage.getQLTK_Button().setVisible(true);
                homePage.getQLTK_Panel().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this,
                        "WRRONG ACCOUNT!!!",
                        "FAIL TO LOGIN",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        userTextField = new javax.swing.JTextField();
        userName_label = new javax.swing.JLabel();
        pass_Label = new javax.swing.JLabel();
        PasswordField = new javax.swing.JPasswordField();
        login_Button = new javax.swing.JButton();
        login_Label = new javax.swing.JLabel();
        NhanVien_RadioButton = new javax.swing.JRadioButton();
        admin_RadioButton = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 255));
        setPreferredSize(new java.awt.Dimension(610, 450));

        userTextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        userTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userTextFieldKeyPressed(evt);
            }
        });

        userName_label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        userName_label.setText("Username");
        userName_label.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        userName_label.setIconTextGap(10);

        pass_Label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        pass_Label.setText("Password");
        pass_Label.setIconTextGap(20);

        PasswordField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordFieldActionPerformed(evt);
            }
        });
        PasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PasswordFieldKeyPressed(evt);
            }
        });

        login_Button.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        login_Button.setText("Login");
        login_Button.setAlignmentX(1.0F);
        login_Button.setAlignmentY(1.0F);
        login_Button.setPreferredSize(new java.awt.Dimension(50, 23));
        login_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_ButtonActionPerformed(evt);
            }
        });
        login_Button.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                login_ButtonKeyPressed(evt);
            }
        });

        login_Label.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        login_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        NhanVien_RadioButton.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        NhanVien_RadioButton.setText("Nhân viên");

        admin_RadioButton.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        admin_RadioButton.setText("Quản lý");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Bạn là: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(460, 460, 460)
                        .addComponent(login_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(520, 520, 520)
                        .addComponent(login_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pass_Label)
                                .addGap(30, 30, 30)
                                .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(userName_label, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(userTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(NhanVien_RadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(admin_RadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addGap(70, 70, 70))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(login_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(NhanVien_RadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(admin_RadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName_label, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pass_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(login_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void login_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login_ButtonActionPerformed
        // TODO add your handling code here:
        try2Login();
    }//GEN-LAST:event_login_ButtonActionPerformed

    private void PasswordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PasswordFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try2Login();
        }
    }//GEN-LAST:event_PasswordFieldKeyPressed

    private void userTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userTextFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try2Login();
        }
    }//GEN-LAST:event_userTextFieldKeyPressed

    private void login_ButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_login_ButtonKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try2Login();
        }
    }//GEN-LAST:event_login_ButtonKeyPressed

    private void PasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordFieldActionPerformed

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
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton NhanVien_RadioButton;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JRadioButton admin_RadioButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton login_Button;
    private javax.swing.JLabel login_Label;
    private javax.swing.JLabel pass_Label;
    private javax.swing.JLabel userName_label;
    private javax.swing.JTextField userTextField;
    // End of variables declaration//GEN-END:variables
}
