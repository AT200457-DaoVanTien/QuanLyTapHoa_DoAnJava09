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
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class DangNhap extends javax.swing.JFrame {

    private static String CUR_DIR = System.getProperty("user.dir");
    private ArrayList<Account> stuffList, adminList;

    public DangNhap() {

        initComponents();
        setLocationRelativeTo(null);
        setTitle("Đăng Nhập");
        setPreferredSize(new Dimension(610, 450));
        NhanVien_RadioButton.setSelected(true);
        buttonGroup1.add(NhanVien_RadioButton);
        buttonGroup1.add(QuanLy_RadioButton);
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
                homePage.setSize(1100, 600);
                homePage.setPreferredSize(new Dimension(1100, 600));
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
                homePage.setSize(1100, 600);
                homePage.setPreferredSize(new Dimension(1100, 600));
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
        jPanel1 = new javax.swing.JPanel();
        login_Label = new javax.swing.JLabel();
        userTextField = new javax.swing.JTextField();
        userName_label = new javax.swing.JLabel();
        pass_Label = new javax.swing.JLabel();
        PasswordField = new javax.swing.JPasswordField();
        login_Button = new javax.swing.JButton();
        NhanVien_RadioButton = new javax.swing.JRadioButton();
        QuanLy_RadioButton = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(610, 450));

        login_Label.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        login_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        login_Label.setText("Đăng Nhập");

        userTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userTextFieldKeyPressed(evt);
            }
        });

        userName_label.setText("User name");

        pass_Label.setText("Password");

        PasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PasswordFieldKeyPressed(evt);
            }
        });

        login_Button.setText("Login");
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

        NhanVien_RadioButton.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        NhanVien_RadioButton.setText("Nhân viên");

        QuanLy_RadioButton.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        QuanLy_RadioButton.setText("Quản lý");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel1.setText("Vai trò của bạn là: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(userName_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pass_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(login_Button)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(userTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(login_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(NhanVien_RadioButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(QuanLy_RadioButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(login_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(NhanVien_RadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(QuanLy_RadioButton)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName_label, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pass_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(login_Button)
                .addGap(88, 88, 88))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(149, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
    private javax.swing.JRadioButton QuanLy_RadioButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton login_Button;
    private javax.swing.JLabel login_Label;
    private javax.swing.JLabel pass_Label;
    private javax.swing.JLabel userName_label;
    private javax.swing.JTextField userTextField;
    // End of variables declaration//GEN-END:variables
}
