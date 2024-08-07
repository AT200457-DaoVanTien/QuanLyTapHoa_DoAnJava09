/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.quanlytaphoa.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quanlytaphoa.Model.Account;
import com.quanlytaphoa.Model.Customer;
import com.quanlytaphoa.Model.Product;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import static java.io.File.separator;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class HomePage extends JFrame {

    /**
     * Creates new form HomePage
     */
    private Customer khachHang;
    private String READ_PATH;
    private BufferedReader br = null;
    private BufferedWriter bw = null;
    private Product BanHang_hangHoa = null;
    private ArrayList<Customer> list_KH;
    private ArrayList<Product> list_SanPham;
    private boolean themTk_Status;
    private int countID_KH = 0, BanHang_STT=0;
    private LocalDateTime buyTime;

    public HomePage() {
        this.setTitle("Home Page");
        initComponents();
        create_Digital_Clock(RealityTimer_Label, LocalDateTime.now().getDayOfWeek().name() + " " + LocalDateTime.now().getDayOfMonth() + "/" + LocalDateTime.now().getMonthValue() + "/" + LocalDateTime.now().getYear() + "  ");
        home_XuLiDuLieu();
        BanHang_XuLiDuLieu();
        NhapHang_XuLiDuLieu();
        DonHang_XuLiDuLieu();
        TonKho_XuLiDuLieu();
        ThongKe_XuLiDuLieu();
        QlyTk_XuLiDuLieu();
    }
    
    // nhiệm vụ: set themTk_Status thành false nếu trùng
    private void add_Account() {
        Account ac;
        if (!Arrays.toString(themTk_VerifyPassField.getPassword()).equals(Arrays.toString(themTk_PassField.getPassword()))) {
            themTk_Status = false;
            ThemTk_Status_Label.setText("Fail");
            ThemTk_Status_Label.setBackground(Color.RED);
            ThemTk_Status_Label.setOpaque(true);
        } else if (! Arrays.toString(themTk_PassField.getPassword()).equals("")&&
                   ! Arrays.toString(themTk_VerifyPassField.getPassword()).equals("")&&
                   !themTk_User_TextField.getText().equals("")){
            themTk_Status = true;
            ac = new Account(themTk_User_TextField.getText(), Arrays.toString(themTk_VerifyPassField.getPassword()));            
            ThemTk_Status_Label.setText("Success");
            ThemTk_Status_Label.setBackground(Color.GREEN);
            ThemTk_Status_Label.setOpaque(true);

            //Ktra xem có bị trùng Account không
            String filePath;
            filePath = "D:" + separator + "Learning Java" + separator + "QuanLyTapHoa_DoAnJava09" + separator + "Manage Files" + separator + "Account.json";
            //D:/Learning Java/QuanLyTapHoa_DoAnJava09/Manage Files/Account.json
            FileReader fr = null;
            try {
                // đọc file Account.json vào listAccount
                fr = new FileReader(filePath);
                java.lang.reflect.Type accountType = new TypeToken<Collection<Account>>() {
                }.getType();
                ArrayList<Account> listAccount = new ArrayList<>();
                Gson gson = new Gson();
                listAccount = gson.fromJson(fr, accountType);
                for (Account a : listAccount) {
                    if (a.getUser().equals(ac.getUser()) && a.getPassword().equals(ac.getPassword())) {
                        // trùng với tài khoản đã có
                        themTk_Status = false;
                        JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại", "", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void themTK_EnterPress()
    {
        if (themTk_Status) {
            JOptionPane.showMessageDialog(this, "Đăng kí thành công");
            String filePath;
            Account ac;
            filePath = "D:" + separator + "Learning Java" + separator + "QuanLyTapHoa_DoAnJava09" + separator + "Manage Files" + separator + "Account.json";
            //D:/Learning Java/QuanLyTapHoa_DoAnJava09/Manage Files/Account.json
            FileReader fr = null;
            FileWriter fw = null;
            try {
                // đọc file Account.json vào listAccount
                fr = new FileReader(filePath);
                java.lang.reflect.Type accountType = new TypeToken<Collection<Account>>() {
                }.getType();
                ArrayList<Account> listAccount = new ArrayList<>();
                Gson gson = new Gson();
                listAccount = gson.fromJson(fr, accountType);
                // thêm tài khoản vào file
                ac = new Account(themTk_User_TextField.getText(), new String(themTk_VerifyPassField.getPassword()));
                listAccount.add(ac);
                try {
                    fw = new FileWriter(filePath);
                    gson.toJson(listAccount, fw);
                } catch (IOException ex) {
                    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        fw.flush();
                        fw.close();
                    } catch (IOException ex) {
                        Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Unsuccessful", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    // tạo lịch ngày tháng năm
    private void create_Digital_Calendar() {
        LocalDateTime date = LocalDateTime.now();
        if (date.getDayOfMonth() < 10) {
            DayValue_TextField.setText("0" + date.getDayOfMonth());
        } else {
            DayValue_TextField.setText(date.getDayOfMonth() + "");
        }

        if (date.getMonthValue() < 10) {
            monthValue_TextField.setText("0" + date.getMonthValue());
        } else {
            monthValue_TextField.setText(date.getMonthValue() + "");
        }
        yearValue_TextField.setText(date.getYear() + "");
    }

    // tạo đồng hồ 
    private void create_Digital_Clock(JLabel label, String header) {
        label.setHorizontalAlignment(JLabel.CENTER);
        Thread updateTimeThread;
        updateTimeThread = new Thread(() -> {
            while (true) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String currentTime = sdf.format(calendar.getTime());
                label.setText(header + currentTime);
                try {
                    Thread.sleep(1000); // Cập nhật thời gian mỗi giây
                } catch (InterruptedException e) {
                }
            }
        });
        updateTimeThread.start();
    }

    private void home_XuLiDuLieu(){
        create_Digital_Calendar();
        create_Digital_Clock(Clock_Label, "");
    }
    
    private void BanHang_XuLiDuLieu()
    {
        int numBill;
        try {
            READ_PATH = "D:\\Learning Java\\QuanLyTapHoa_DoAnJava09\\Manage Files\\hoaDonBan.csv";
            br = new BufferedReader(new FileReader(READ_PATH));
            numBill = Integer.parseInt(br.readLine());
            if (++numBill<10){
                BanHang_MaBill_TextField.setText("HDB00"+numBill);
            } else if (numBill<100){
                BanHang_MaBill_TextField.setText("HDB0"+numBill);
            } else{
                BanHang_MaBill_TextField.setText("HDB"+numBill);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
    private void NhapHang_XuLiDuLieu()
    {
        
    }
    
    private void DonHang_XuLiDuLieu()
    {
        
    }
    
    private void TonKho_XuLiDuLieu()
    {
        
    }
    
    private void ThongKe_XuLiDuLieu()
    {
        
    }
    
    private void QlyTk_XuLiDuLieu()
    {
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TabbedPane = new javax.swing.JTabbedPane();
        Home_Panel = new javax.swing.JPanel();
        ngay_Label = new javax.swing.JLabel();
        thang_Label = new javax.swing.JLabel();
        nam_Label = new javax.swing.JLabel();
        DayValue_TextField = new javax.swing.JTextField();
        monthValue_TextField = new javax.swing.JTextField();
        yearValue_TextField = new javax.swing.JTextField();
        Clock_Label = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        BanHang_Panel = new javax.swing.JPanel();
        BanHang_HoTen_Label = new javax.swing.JLabel();
        BanHang_HoTen_TextField = new javax.swing.JTextField();
        BanHang_MaBill_Label = new javax.swing.JLabel();
        maVoucher_Label = new javax.swing.JLabel();
        maVoucher_TextField = new javax.swing.JTextField();
        sanPham_Panel = new javax.swing.JPanel();
        BanHang_ThemSP_Button = new javax.swing.JButton();
        BanHang_SuaSP_Button = new javax.swing.JButton();
        BanHang_HuySP_Button = new javax.swing.JButton();
        BanHang_TenSP_Label = new javax.swing.JLabel();
        BanHang_maSP_Label = new javax.swing.JLabel();
        BanHang_Msp_TextField = new javax.swing.JTextField();
        BanHang_SoLuong_Label = new javax.swing.JLabel();
        BanHang_Soluong_Spinner = new javax.swing.JSpinner();
        BanHang_TenSP_ComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        BanHang_LoaiSP_CbBox = new javax.swing.JComboBox<>();
        BanHang_Donvi_Label = new javax.swing.JLabel();
        BanHang_Donvi_TextField = new javax.swing.JTextField();
        thanhToan_Button = new javax.swing.JButton();
        BanHang_MaBill_TextField = new javax.swing.JTextField();
        thanhToan_Label = new javax.swing.JLabel();
        tienThua_Label = new javax.swing.JLabel();
        tienThua_TextField = new javax.swing.JTextField();
        thanhTien_Label = new javax.swing.JLabel();
        thanhTien_TextField = new javax.swing.JTextField();
        giamGia_Label = new javax.swing.JLabel();
        vnd_Label3 = new javax.swing.JLabel();
        vnd_Label4 = new javax.swing.JLabel();
        giamGia_TextField = new javax.swing.JTextField();
        huyDon_Button = new javax.swing.JButton();
        vnd_Label2 = new javax.swing.JLabel();
        vnd_Label1 = new javax.swing.JLabel();
        thanhToan_TextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        BanHang_Table = new javax.swing.JTable();
        NhapHang_Panel = new javax.swing.JPanel();
        NhapHang_msp_Label = new javax.swing.JLabel();
        NhapHang_TenSP_Label = new javax.swing.JLabel();
        NhapHang_SoLuong_Label = new javax.swing.JLabel();
        NhapHang_GiaNhap_Label = new javax.swing.JLabel();
        NhapHang_GiaBan_Label = new javax.swing.JLabel();
        NhapHang_Loai_Label = new javax.swing.JLabel();
        NhapHang_Donvi_Label = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        NhapHang_Table = new javax.swing.JTable();
        NhapHang_msp_TextField = new javax.swing.JTextField();
        NhapHang_TenSP_TextField = new javax.swing.JTextField();
        NhapHang_SoLuong_TextField = new javax.swing.JTextField();
        NhapHang_GiaNhap_TextField = new javax.swing.JTextField();
        NhapHang_GiaBan_TextField = new javax.swing.JTextField();
        NhapHang_Loai_CbBox = new javax.swing.JComboBox<>();
        NhapHang_Donvi_CbBox = new javax.swing.JComboBox<>();
        NhapHang_TimKiem_TextField = new javax.swing.JTextField();
        NhapHang_TimKiem_Button = new javax.swing.JButton();
        NhapHang_Add_Button = new javax.swing.JButton();
        NhapHang_Del_Button = new javax.swing.JButton();
        NhapHang_Save_Button = new javax.swing.JButton();
        DonHang_Panel = new javax.swing.JPanel();
        TonKho_Panel = new javax.swing.JPanel();
        TonKho_GiaBan_TextField = new javax.swing.JTextField();
        TonKho_GiaNhap_Label = new javax.swing.JLabel();
        TonKho_Loai_CbBox = new javax.swing.JComboBox<>();
        TonKho_GiaBan_Label = new javax.swing.JLabel();
        TonKho_Donvi_CbBox = new javax.swing.JComboBox<>();
        TonKho_Loai_Label = new javax.swing.JLabel();
        TonKho_TimKiem_TextField = new javax.swing.JTextField();
        TonKho_Donvi_Label = new javax.swing.JLabel();
        TonKho_TimKiem_Button = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TonKho_Table = new javax.swing.JTable();
        TonKho_Save_Button = new javax.swing.JButton();
        TonKho_Del_Button = new javax.swing.JButton();
        TonKho_msp_TextField = new javax.swing.JTextField();
        TonKho_Clear_Button = new javax.swing.JButton();
        TonKho_TenSP_TextField = new javax.swing.JTextField();
        TonKho_msp_Label = new javax.swing.JLabel();
        TonKho_SoLuong_TextField = new javax.swing.JTextField();
        TonKho_TenSP_Label = new javax.swing.JLabel();
        TonKho_GiaNhap_TextField = new javax.swing.JTextField();
        TonKho_SoLuong_Label = new javax.swing.JLabel();
        ThongKe_Panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        themTk_User_Label = new javax.swing.JLabel();
        themTk_Pass_Label = new javax.swing.JLabel();
        themTk_Button = new javax.swing.JButton();
        themTk_User_TextField = new javax.swing.JTextField();
        themTk_PassField = new javax.swing.JPasswordField();
        themTk_Verify_Label = new javax.swing.JLabel();
        themTk_VerifyPassField = new javax.swing.JPasswordField();
        ThemTk_Status_Label = new javax.swing.JLabel();
        RealityTimer_Panel = new javax.swing.JPanel();
        RealityTimer_Label = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1536, 960));
        setSize(new java.awt.Dimension(1536, 960));

        TabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        TabbedPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TabbedPane.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        TabbedPane.setPreferredSize(new java.awt.Dimension(1060, 600));

        Home_Panel.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        Home_Panel.setPreferredSize(new java.awt.Dimension(966, 720));

        ngay_Label.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        ngay_Label.setText("Ngày");

        thang_Label.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        thang_Label.setText("Tháng");

        nam_Label.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        nam_Label.setText("Năm");

        DayValue_TextField.setEditable(false);
        DayValue_TextField.setBackground(new java.awt.Color(204, 204, 204));
        DayValue_TextField.setFont(new java.awt.Font("Times New Roman", 0, 40)); // NOI18N
        DayValue_TextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        monthValue_TextField.setEditable(false);
        monthValue_TextField.setBackground(new java.awt.Color(204, 204, 204));
        monthValue_TextField.setFont(new java.awt.Font("Times New Roman", 0, 40)); // NOI18N
        monthValue_TextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        yearValue_TextField.setEditable(false);
        yearValue_TextField.setBackground(new java.awt.Color(204, 204, 204));
        yearValue_TextField.setFont(new java.awt.Font("Times New Roman", 0, 40)); // NOI18N
        yearValue_TextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        Clock_Label.setBackground(new java.awt.Color(0, 51, 102));
        Clock_Label.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        Clock_Label.setForeground(new java.awt.Color(255, 255, 255));
        Clock_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Clock_Label.setText("Time");
        Clock_Label.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.lightGray, null, java.awt.Color.white));
        Clock_Label.setOpaque(true);

        javax.swing.GroupLayout Home_PanelLayout = new javax.swing.GroupLayout(Home_Panel);
        Home_Panel.setLayout(Home_PanelLayout);
        Home_PanelLayout.setHorizontalGroup(
            Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Home_PanelLayout.createSequentialGroup()
                .addContainerGap(307, Short.MAX_VALUE)
                .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Home_PanelLayout.createSequentialGroup()
                            .addGap(100, 100, 100)
                            .addComponent(ngay_Label)
                            .addGap(22, 22, 22)
                            .addComponent(DayValue_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(thang_Label)
                            .addGap(18, 18, 18)
                            .addComponent(monthValue_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(24, 24, 24)
                            .addComponent(nam_Label)
                            .addGap(18, 18, 18)
                            .addComponent(yearValue_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Home_PanelLayout.createSequentialGroup()
                        .addComponent(Clock_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(345, 345, 345)))
                .addGap(264, 264, 264))
        );
        Home_PanelLayout.setVerticalGroup(
            Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Home_PanelLayout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ngay_Label)
                    .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DayValue_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(thang_Label))
                    .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nam_Label)
                        .addComponent(yearValue_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(monthValue_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(216, 216, 216)
                .addComponent(Clock_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(441, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Home", Home_Panel);

        BanHang_Panel.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        BanHang_Panel.setMinimumSize(new java.awt.Dimension(1060, 545));
        BanHang_Panel.setOpaque(true);
        BanHang_Panel.setPreferredSize(new java.awt.Dimension(1060, 720));
        BanHang_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BanHang_HoTen_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        BanHang_HoTen_Label.setText("Họ và Tên");
        BanHang_Panel.add(BanHang_HoTen_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 18, -1, -1));

        BanHang_HoTen_TextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BanHang_Panel.add(BanHang_HoTen_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 17, 260, -1));

        BanHang_MaBill_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        BanHang_MaBill_Label.setText("Mã hoá đơn");
        BanHang_Panel.add(BanHang_MaBill_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(811, 18, -1, -1));

        maVoucher_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        maVoucher_Label.setText("Mã Voucher");
        BanHang_Panel.add(maVoucher_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 440, -1, -1));

        maVoucher_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        BanHang_Panel.add(maVoucher_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 430, 188, -1));

        sanPham_Panel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sanPham_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BanHang_ThemSP_Button.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        BanHang_ThemSP_Button.setText("Thêm SP");
        BanHang_ThemSP_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BanHang_ThemSP_ButtonActionPerformed(evt);
            }
        });
        sanPham_Panel.add(BanHang_ThemSP_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 22, 133, -1));

        BanHang_SuaSP_Button.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        BanHang_SuaSP_Button.setText("Sửa SP");
        BanHang_SuaSP_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BanHang_SuaSP_ButtonActionPerformed(evt);
            }
        });
        sanPham_Panel.add(BanHang_SuaSP_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 92, 133, -1));

        BanHang_HuySP_Button.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        BanHang_HuySP_Button.setText("Huỷ SP");
        sanPham_Panel.add(BanHang_HuySP_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 160, 133, -1));

        BanHang_TenSP_Label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BanHang_TenSP_Label.setText("Tên sản phẩm");
        sanPham_Panel.add(BanHang_TenSP_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 112, -1, -1));

        BanHang_maSP_Label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BanHang_maSP_Label.setText("Mã sản phẩm");
        sanPham_Panel.add(BanHang_maSP_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 67, -1, -1));

        BanHang_Msp_TextField.setEditable(false);
        BanHang_Msp_TextField.setBackground(new java.awt.Color(255, 255, 255));
        BanHang_Msp_TextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        sanPham_Panel.add(BanHang_Msp_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 64, 175, -1));

        BanHang_SoLuong_Label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BanHang_SoLuong_Label.setText("Số lượng");
        sanPham_Panel.add(BanHang_SoLuong_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 157, -1, -1));

        BanHang_Soluong_Spinner.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BanHang_Soluong_Spinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99999, 1));
        sanPham_Panel.add(BanHang_Soluong_Spinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 154, 100, -1));

        BanHang_TenSP_ComboBox.setEditable(true);
        BanHang_TenSP_ComboBox.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BanHang_TenSP_ComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BanHang_TenSP_ComboBoxActionPerformed(evt);
            }
        });
        sanPham_Panel.add(BanHang_TenSP_ComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 109, 175, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Loại SP");
        sanPham_Panel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 21, -1, -1));

        BanHang_LoaiSP_CbBox.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BanHang_LoaiSP_CbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Đồ ăn", "Đồ uống", "Đồ gia dụng" }));
        BanHang_LoaiSP_CbBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BanHang_LoaiSP_CbBoxActionPerformed(evt);
            }
        });
        sanPham_Panel.add(BanHang_LoaiSP_CbBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 18, 175, -1));

        BanHang_Donvi_Label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BanHang_Donvi_Label.setText("Đơn vị");
        sanPham_Panel.add(BanHang_Donvi_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        BanHang_Donvi_TextField.setEditable(false);
        BanHang_Donvi_TextField.setBackground(new java.awt.Color(255, 255, 255));
        BanHang_Donvi_TextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        sanPham_Panel.add(BanHang_Donvi_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 170, -1));

        BanHang_Panel.add(sanPham_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, 510, 270));

        thanhToan_Button.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        thanhToan_Button.setText("Thanh toán");
        BanHang_Panel.add(thanhToan_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 650, 174, 90));

        BanHang_MaBill_TextField.setEditable(false);
        BanHang_MaBill_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        BanHang_Panel.add(BanHang_MaBill_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(923, 15, 160, -1));

        thanhToan_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        thanhToan_Label.setText("Thanh toán");
        BanHang_Panel.add(thanhToan_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 530, -1, -1));

        tienThua_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tienThua_Label.setText("Tiền thừa");
        BanHang_Panel.add(tienThua_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 580, -1, -1));

        tienThua_TextField.setEditable(false);
        tienThua_TextField.setBackground(new java.awt.Color(255, 255, 255));
        tienThua_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tienThua_TextField.setText("0");
        BanHang_Panel.add(tienThua_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 580, 188, -1));

        thanhTien_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        thanhTien_Label.setText("Thành tiền");
        BanHang_Panel.add(thanhTien_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 390, -1, -1));

        thanhTien_TextField.setEditable(false);
        thanhTien_TextField.setBackground(new java.awt.Color(255, 255, 255));
        thanhTien_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        thanhTien_TextField.setText("0");
        BanHang_Panel.add(thanhTien_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 390, 188, -1));

        giamGia_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        giamGia_Label.setText("Giảm giá");
        BanHang_Panel.add(giamGia_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 490, -1, -1));

        vnd_Label3.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        vnd_Label3.setText("VNĐ");
        BanHang_Panel.add(vnd_Label3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 540, -1, -1));

        vnd_Label4.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        vnd_Label4.setText("VNĐ");
        BanHang_Panel.add(vnd_Label4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 580, -1, -1));

        giamGia_TextField.setEditable(false);
        giamGia_TextField.setBackground(new java.awt.Color(255, 255, 255));
        giamGia_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        giamGia_TextField.setText("0");
        BanHang_Panel.add(giamGia_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 480, 188, -1));

        huyDon_Button.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        huyDon_Button.setText("Huỷ Hoá đơn");
        BanHang_Panel.add(huyDon_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 650, 170, 90));

        vnd_Label2.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        vnd_Label2.setText("VNĐ");
        BanHang_Panel.add(vnd_Label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 490, -1, -1));

        vnd_Label1.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        vnd_Label1.setText("VNĐ");
        BanHang_Panel.add(vnd_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 390, -1, -1));

        thanhToan_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        thanhToan_TextField.setText("0");
        BanHang_Panel.add(thanhToan_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 530, 188, -1));

        BanHang_Table.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BanHang_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Số lượng", "Đơn vị", "Đơn giá", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        BanHang_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BanHang_TableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(BanHang_Table);

        BanHang_Panel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 80, 690, 402));

        TabbedPane.addTab("Bán hàng", BanHang_Panel);

        NhapHang_Panel.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        NhapHang_Panel.setPreferredSize(new java.awt.Dimension(1080, 449));

        NhapHang_msp_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_msp_Label.setText("Mã sản phẩm");

        NhapHang_TenSP_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_TenSP_Label.setText("Tên sản phẩm");

        NhapHang_SoLuong_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_SoLuong_Label.setText("Số lượng");

        NhapHang_GiaNhap_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_GiaNhap_Label.setText("Giá nhập");

        NhapHang_GiaBan_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_GiaBan_Label.setText("Giá bán");

        NhapHang_Loai_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_Loai_Label.setText("Loại");

        NhapHang_Donvi_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_Donvi_Label.setText("Đơn vị");

        jScrollPane3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        NhapHang_Table.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        NhapHang_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Số lượng", "Ngày nhập", "NSX", "HSD", "Giá nhập", "Giá bán", "Loại", "Đơn vị"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(NhapHang_Table);

        NhapHang_msp_TextField.setEditable(false);
        NhapHang_msp_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        NhapHang_TenSP_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        NhapHang_SoLuong_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        NhapHang_GiaNhap_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        NhapHang_GiaBan_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        NhapHang_Loai_CbBox.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_Loai_CbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        NhapHang_Donvi_CbBox.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_Donvi_CbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        NhapHang_TimKiem_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        NhapHang_TimKiem_Button.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_TimKiem_Button.setText("Tìm kiếm");

        NhapHang_Add_Button.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        NhapHang_Add_Button.setText("Thêm");

        NhapHang_Del_Button.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        NhapHang_Del_Button.setText("Xoá");

        NhapHang_Save_Button.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        NhapHang_Save_Button.setText("Lưu");

        javax.swing.GroupLayout NhapHang_PanelLayout = new javax.swing.GroupLayout(NhapHang_Panel);
        NhapHang_Panel.setLayout(NhapHang_PanelLayout);
        NhapHang_PanelLayout.setHorizontalGroup(
            NhapHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhapHang_PanelLayout.createSequentialGroup()
                .addGroup(NhapHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NhapHang_PanelLayout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addGroup(NhapHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(NhapHang_PanelLayout.createSequentialGroup()
                                .addGroup(NhapHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NhapHang_SoLuong_Label)
                                    .addComponent(NhapHang_GiaNhap_Label)
                                    .addComponent(NhapHang_GiaBan_Label)
                                    .addComponent(NhapHang_Loai_Label)
                                    .addComponent(NhapHang_Donvi_Label)
                                    .addComponent(NhapHang_Add_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(NhapHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NhapHang_Loai_CbBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NhapHang_Donvi_CbBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(NhapHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(NhapHang_Del_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(NhapHang_GiaNhap_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(NhapHang_SoLuong_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(NhapHang_GiaBan_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(NhapHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(NhapHang_PanelLayout.createSequentialGroup()
                                    .addComponent(NhapHang_msp_Label)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(NhapHang_msp_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(NhapHang_PanelLayout.createSequentialGroup()
                                    .addComponent(NhapHang_TenSP_Label)
                                    .addGap(18, 18, 18)
                                    .addComponent(NhapHang_TenSP_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(NhapHang_PanelLayout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(NhapHang_Save_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(NhapHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NhapHang_PanelLayout.createSequentialGroup()
                        .addGap(356, 356, 356)
                        .addComponent(NhapHang_TimKiem_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(NhapHang_TimKiem_Button))
                    .addGroup(NhapHang_PanelLayout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        NhapHang_PanelLayout.setVerticalGroup(
            NhapHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhapHang_PanelLayout.createSequentialGroup()
                .addGroup(NhapHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NhapHang_PanelLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(NhapHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NhapHang_TimKiem_Button)
                            .addComponent(NhapHang_TimKiem_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(NhapHang_PanelLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(NhapHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NhapHang_msp_Label)
                            .addComponent(NhapHang_msp_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(NhapHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NhapHang_TenSP_Label)
                            .addComponent(NhapHang_TenSP_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(NhapHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NhapHang_SoLuong_Label)
                            .addComponent(NhapHang_SoLuong_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(NhapHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NhapHang_GiaNhap_Label)
                            .addComponent(NhapHang_GiaNhap_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(NhapHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NhapHang_GiaBan_Label)
                            .addComponent(NhapHang_GiaBan_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(NhapHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NhapHang_Loai_Label)
                            .addComponent(NhapHang_Loai_CbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(NhapHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NhapHang_Donvi_Label)
                            .addComponent(NhapHang_Donvi_CbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(NhapHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NhapHang_Add_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NhapHang_Del_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(NhapHang_Save_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(354, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Nhập hàng", NhapHang_Panel);

        javax.swing.GroupLayout DonHang_PanelLayout = new javax.swing.GroupLayout(DonHang_Panel);
        DonHang_Panel.setLayout(DonHang_PanelLayout);
        DonHang_PanelLayout.setHorizontalGroup(
            DonHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1441, Short.MAX_VALUE)
        );
        DonHang_PanelLayout.setVerticalGroup(
            DonHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 932, Short.MAX_VALUE)
        );

        TabbedPane.addTab("Đơn hàng", DonHang_Panel);

        TonKho_Panel.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        TonKho_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TonKho_GiaBan_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TonKho_Panel.add(TonKho_GiaBan_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 180, -1));

        TonKho_GiaNhap_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TonKho_GiaNhap_Label.setText("Giá nhập");
        TonKho_Panel.add(TonKho_GiaNhap_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 193, -1, -1));

        TonKho_Loai_CbBox.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TonKho_Loai_CbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        TonKho_Panel.add(TonKho_Loai_CbBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, 140, -1));

        TonKho_GiaBan_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TonKho_GiaBan_Label.setText("Giá bán");
        TonKho_Panel.add(TonKho_GiaBan_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 244, -1, -1));

        TonKho_Donvi_CbBox.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TonKho_Donvi_CbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        TonKho_Panel.add(TonKho_Donvi_CbBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, 140, -1));

        TonKho_Loai_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TonKho_Loai_Label.setText("Loại");
        TonKho_Panel.add(TonKho_Loai_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 295, -1, -1));

        TonKho_TimKiem_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TonKho_Panel.add(TonKho_TimKiem_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, 212, -1));

        TonKho_Donvi_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TonKho_Donvi_Label.setText("Đơn vị");
        TonKho_Panel.add(TonKho_Donvi_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 346, -1, -1));

        TonKho_TimKiem_Button.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TonKho_TimKiem_Button.setText("Tìm kiếm");
        TonKho_Panel.add(TonKho_TimKiem_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 20, -1, -1));

        jScrollPane4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        TonKho_Table.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        TonKho_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên SP", "Số lượng", "Ngày nhập", "HSD", "Giá nhập", "Giá bán", "Loại", "Đơn vị"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(TonKho_Table);

        TonKho_Panel.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 840, 420));

        TonKho_Save_Button.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        TonKho_Save_Button.setText("Save");
        TonKho_Panel.add(TonKho_Save_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 90, 70));

        TonKho_Del_Button.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        TonKho_Del_Button.setText("Delete");
        TonKho_Panel.add(TonKho_Del_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 100, 70));

        TonKho_msp_TextField.setEditable(false);
        TonKho_msp_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TonKho_Panel.add(TonKho_msp_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 180, -1));

        TonKho_Clear_Button.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        TonKho_Clear_Button.setText("Clear");
        TonKho_Panel.add(TonKho_Clear_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 420, 90, 70));

        TonKho_TenSP_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TonKho_Panel.add(TonKho_TenSP_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 180, -1));

        TonKho_msp_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TonKho_msp_Label.setText("Mã sản phẩm");
        TonKho_Panel.add(TonKho_msp_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        TonKho_SoLuong_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TonKho_Panel.add(TonKho_SoLuong_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 180, -1));

        TonKho_TenSP_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TonKho_TenSP_Label.setText("Tên sản phẩm");
        TonKho_Panel.add(TonKho_TenSP_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 91, -1, -1));

        TonKho_GiaNhap_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TonKho_Panel.add(TonKho_GiaNhap_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 180, -1));

        TonKho_SoLuong_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TonKho_SoLuong_Label.setText("Số lượng");
        TonKho_Panel.add(TonKho_SoLuong_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 142, -1, -1));

        TabbedPane.addTab("Tồn kho", TonKho_Panel);

        javax.swing.GroupLayout ThongKe_PanelLayout = new javax.swing.GroupLayout(ThongKe_Panel);
        ThongKe_Panel.setLayout(ThongKe_PanelLayout);
        ThongKe_PanelLayout.setHorizontalGroup(
            ThongKe_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1441, Short.MAX_VALUE)
        );
        ThongKe_PanelLayout.setVerticalGroup(
            ThongKe_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 932, Short.MAX_VALUE)
        );

        TabbedPane.addTab("Thống kê", ThongKe_Panel);

        themTk_User_Label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        themTk_User_Label.setText("User");

        themTk_Pass_Label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        themTk_Pass_Label.setText("Password");

        themTk_Button.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        themTk_Button.setText("Thêm Tài khoản");

        themTk_User_TextField.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        themTk_PassField.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        themTk_Verify_Label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        themTk_Verify_Label.setText("Verify Password");

        themTk_VerifyPassField.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        ThemTk_Status_Label.setBackground(new java.awt.Color(204, 204, 204));
        ThemTk_Status_Label.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ThemTk_Status_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ThemTk_Status_Label.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(themTk_Verify_Label)
                        .addGap(18, 18, 18)
                        .addComponent(themTk_VerifyPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ThemTk_Status_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(themTk_Pass_Label)
                            .addComponent(themTk_User_Label))
                        .addGap(68, 68, 68)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(themTk_PassField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(themTk_User_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(themTk_Button)
                        .addGap(33, 33, 33)))
                .addContainerGap(953, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themTk_User_Label)
                    .addComponent(themTk_User_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themTk_Pass_Label)
                    .addComponent(themTk_PassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themTk_Verify_Label)
                    .addComponent(themTk_VerifyPassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ThemTk_Status_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addComponent(themTk_Button)
                .addContainerGap(612, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Q.lý Tk", jPanel1);

        getContentPane().add(TabbedPane, java.awt.BorderLayout.CENTER);

        RealityTimer_Panel.setLayout(new java.awt.BorderLayout());

        RealityTimer_Label.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        RealityTimer_Label.setForeground(new java.awt.Color(51, 51, 255));
        RealityTimer_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RealityTimer_Label.setText("Real Time");
        RealityTimer_Label.setPreferredSize(new java.awt.Dimension(76, 25));
        RealityTimer_Panel.add(RealityTimer_Label, java.awt.BorderLayout.CENTER);

        jSeparator2.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator2.setForeground(new java.awt.Color(204, 204, 204));
        jSeparator2.setOpaque(true);
        RealityTimer_Panel.add(jSeparator2, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(RealityTimer_Panel, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BanHang_ThemSP_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BanHang_ThemSP_ButtonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tableModel = (DefaultTableModel)BanHang_Table.getModel();
        tableModel.addRow(new Object[]{
            ++BanHang_STT,
            BanHang_Msp_TextField.getText(),
            BanHang_TenSP_ComboBox.getSelectedItem().toString(),
            BanHang_Soluong_Spinner.getValue(),
            BanHang_Donvi_TextField.getText(),
            BanHang_hangHoa.getGia(),
            (Integer)BanHang_Soluong_Spinner.getValue() * BanHang_hangHoa.getGia()
        });
    }//GEN-LAST:event_BanHang_ThemSP_ButtonActionPerformed

    private void BanHang_LoaiSP_CbBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BanHang_LoaiSP_CbBoxActionPerformed
        // TODO add your handling code here:
        list_SanPham = new ArrayList<>();
        JComboBox<String> combo = (JComboBox<String>) evt.getSource();
        BanHang_TenSP_ComboBox.removeAllItems();
        BanHang_TenSP_ComboBox.addItem("");
        
        String selectItem = combo.getSelectedItem().toString();
        switch (selectItem) {
            case "Đồ ăn" -> {
                READ_PATH = "D:\\Learning Java\\QuanLyTapHoa_DoAnJava09\\Manage Files\\SanPham_Food.csv";
            }
            case "Đồ uống" -> {
                READ_PATH = "D:\\Learning Java\\QuanLyTapHoa_DoAnJava09\\Manage Files\\SanPham_Drink.csv";
            }
            case "Đồ gia dụng" -> {
                READ_PATH = "D:\\Learning Java\\QuanLyTapHoa_DoAnJava09\\Manage Files\\SanPham_Houseware.csv";
            }
        }
        try {
            br = new BufferedReader(new FileReader(READ_PATH));
            br.readLine();
            String strHH;
            String[] infor;
            while ((strHH = br.readLine())!= null){
                // tạo sản phẩm để cho vào list_SanPham
                infor = strHH.split(",");
                BanHang_hangHoa = new Product();
                BanHang_hangHoa.setMaSP(infor[0]);
                BanHang_hangHoa.setTenSP(infor[1]);
                BanHang_hangHoa.setSoLuong((Integer)BanHang_Soluong_Spinner.getValue());
                BanHang_hangHoa.setDonVi(infor[4]);
                BanHang_hangHoa.setGia(Integer.parseInt(infor[5]));
                list_SanPham.add(BanHang_hangHoa);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for (Product spham: list_SanPham){
            BanHang_TenSP_ComboBox.addItem(spham.getTenSP());
        }
    }//GEN-LAST:event_BanHang_LoaiSP_CbBoxActionPerformed

    private void BanHang_TenSP_ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BanHang_TenSP_ComboBoxActionPerformed
        // TODO add your handling code here:
         JComboBox<String> combo = (JComboBox<String>) evt.getSource();
        String tensp = combo.getSelectedItem().toString();
        for (Product sp: list_SanPham){
            if (sp.getTenSP().equals(tensp)){
                BanHang_hangHoa = sp;
                BanHang_Msp_TextField.setText(sp.getMaSP());
                BanHang_Donvi_TextField.setText(sp.getDonVi());
                break;
            }
        }
    }//GEN-LAST:event_BanHang_TenSP_ComboBoxActionPerformed

    private void BanHang_TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BanHang_TableMouseClicked
        // TODO add your handling code here:
        int selectedRow = BanHang_Table.getSelectedRow();
        DefaultTableModel tableModel = (DefaultTableModel) BanHang_Table.getModel();
        BanHang_Msp_TextField.setText(tableModel.getValueAt(selectedRow, 1).toString());
        BanHang_TenSP_ComboBox.setSelectedItem(tableModel.getValueAt(selectedRow, 2));
        BanHang_Soluong_Spinner.setValue(tableModel.getValueAt(selectedRow, 3));
        BanHang_Donvi_TextField.setText(tableModel.getValueAt(selectedRow, 4).toString());
    }//GEN-LAST:event_BanHang_TableMouseClicked

    private void BanHang_SuaSP_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BanHang_SuaSP_ButtonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tableModel = (DefaultTableModel) BanHang_Table.getModel();
        if (tableModel.getRowCount()==0){
            JOptionPane.showMessageDialog(this, "Table is empty");
        } else if (BanHang_Table.getSelectedRow()>1){
            JOptionPane.showMessageDialog(this, "Just select only one row");
        } else{
            int selectedRow = BanHang_Table.getSelectedRow();
            tableModel.setValueAt(BanHang_Msp_TextField.getText(), selectedRow, 1);
            tableModel.setValueAt(BanHang_TenSP_ComboBox.getSelectedItem(), selectedRow, 2);
            tableModel.setValueAt(BanHang_Soluong_Spinner.getValue().toString(), selectedRow, 3);
            tableModel.setValueAt(BanHang_Donvi_TextField, selectedRow, 4);
            for (Product sp: list_SanPham){
                if (sp.getMaSP().equals(BanHang_Msp_TextField.getText())){
                    tableModel.setValueAt(sp.getGia(), selectedRow, 5);
                    tableModel.setValueAt(Integer.parseInt(BanHang_Soluong_Spinner.getValue().toString())*sp.getGia(), selectedRow, 6);
                    break;
                }
            }
        }
        
    }//GEN-LAST:event_BanHang_SuaSP_ButtonActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            HomePage homePage = new HomePage();
            //homePage.setSize(1536, 960);
            homePage.setExtendedState(JFrame.MAXIMIZED_BOTH);
            homePage.setLocationRelativeTo(null);
            homePage.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BanHang_Donvi_Label;
    private javax.swing.JTextField BanHang_Donvi_TextField;
    private javax.swing.JLabel BanHang_HoTen_Label;
    private javax.swing.JTextField BanHang_HoTen_TextField;
    private javax.swing.JButton BanHang_HuySP_Button;
    private javax.swing.JComboBox<String> BanHang_LoaiSP_CbBox;
    private javax.swing.JLabel BanHang_MaBill_Label;
    private javax.swing.JTextField BanHang_MaBill_TextField;
    private javax.swing.JTextField BanHang_Msp_TextField;
    private javax.swing.JPanel BanHang_Panel;
    private javax.swing.JLabel BanHang_SoLuong_Label;
    private javax.swing.JSpinner BanHang_Soluong_Spinner;
    private javax.swing.JButton BanHang_SuaSP_Button;
    private javax.swing.JTable BanHang_Table;
    private javax.swing.JComboBox<String> BanHang_TenSP_ComboBox;
    private javax.swing.JLabel BanHang_TenSP_Label;
    private javax.swing.JButton BanHang_ThemSP_Button;
    private javax.swing.JLabel BanHang_maSP_Label;
    private javax.swing.JLabel Clock_Label;
    private javax.swing.JTextField DayValue_TextField;
    private javax.swing.JPanel DonHang_Panel;
    private javax.swing.JPanel Home_Panel;
    private javax.swing.JButton NhapHang_Add_Button;
    private javax.swing.JButton NhapHang_Del_Button;
    private javax.swing.JComboBox<String> NhapHang_Donvi_CbBox;
    private javax.swing.JLabel NhapHang_Donvi_Label;
    private javax.swing.JLabel NhapHang_GiaBan_Label;
    private javax.swing.JTextField NhapHang_GiaBan_TextField;
    private javax.swing.JLabel NhapHang_GiaNhap_Label;
    private javax.swing.JTextField NhapHang_GiaNhap_TextField;
    private javax.swing.JComboBox<String> NhapHang_Loai_CbBox;
    private javax.swing.JLabel NhapHang_Loai_Label;
    private javax.swing.JPanel NhapHang_Panel;
    private javax.swing.JButton NhapHang_Save_Button;
    private javax.swing.JLabel NhapHang_SoLuong_Label;
    private javax.swing.JTextField NhapHang_SoLuong_TextField;
    private javax.swing.JTable NhapHang_Table;
    private javax.swing.JLabel NhapHang_TenSP_Label;
    private javax.swing.JTextField NhapHang_TenSP_TextField;
    private javax.swing.JButton NhapHang_TimKiem_Button;
    private javax.swing.JTextField NhapHang_TimKiem_TextField;
    private javax.swing.JLabel NhapHang_msp_Label;
    private javax.swing.JTextField NhapHang_msp_TextField;
    private javax.swing.JLabel RealityTimer_Label;
    private javax.swing.JPanel RealityTimer_Panel;
    private javax.swing.JTabbedPane TabbedPane;
    private javax.swing.JLabel ThemTk_Status_Label;
    private javax.swing.JPanel ThongKe_Panel;
    private javax.swing.JButton TonKho_Clear_Button;
    private javax.swing.JButton TonKho_Del_Button;
    private javax.swing.JComboBox<String> TonKho_Donvi_CbBox;
    private javax.swing.JLabel TonKho_Donvi_Label;
    private javax.swing.JLabel TonKho_GiaBan_Label;
    private javax.swing.JTextField TonKho_GiaBan_TextField;
    private javax.swing.JLabel TonKho_GiaNhap_Label;
    private javax.swing.JTextField TonKho_GiaNhap_TextField;
    private javax.swing.JComboBox<String> TonKho_Loai_CbBox;
    private javax.swing.JLabel TonKho_Loai_Label;
    private javax.swing.JPanel TonKho_Panel;
    private javax.swing.JButton TonKho_Save_Button;
    private javax.swing.JLabel TonKho_SoLuong_Label;
    private javax.swing.JTextField TonKho_SoLuong_TextField;
    private javax.swing.JTable TonKho_Table;
    private javax.swing.JLabel TonKho_TenSP_Label;
    private javax.swing.JTextField TonKho_TenSP_TextField;
    private javax.swing.JButton TonKho_TimKiem_Button;
    private javax.swing.JTextField TonKho_TimKiem_TextField;
    private javax.swing.JLabel TonKho_msp_Label;
    private javax.swing.JTextField TonKho_msp_TextField;
    private javax.swing.JLabel giamGia_Label;
    private javax.swing.JTextField giamGia_TextField;
    private javax.swing.JButton huyDon_Button;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel maVoucher_Label;
    private javax.swing.JTextField maVoucher_TextField;
    private javax.swing.JTextField monthValue_TextField;
    private javax.swing.JLabel nam_Label;
    private javax.swing.JLabel ngay_Label;
    private javax.swing.JPanel sanPham_Panel;
    private javax.swing.JLabel thang_Label;
    private javax.swing.JLabel thanhTien_Label;
    private javax.swing.JTextField thanhTien_TextField;
    private javax.swing.JButton thanhToan_Button;
    private javax.swing.JLabel thanhToan_Label;
    private javax.swing.JTextField thanhToan_TextField;
    private javax.swing.JButton themTk_Button;
    private javax.swing.JPasswordField themTk_PassField;
    private javax.swing.JLabel themTk_Pass_Label;
    private javax.swing.JLabel themTk_User_Label;
    private javax.swing.JTextField themTk_User_TextField;
    private javax.swing.JPasswordField themTk_VerifyPassField;
    private javax.swing.JLabel themTk_Verify_Label;
    private javax.swing.JLabel tienThua_Label;
    private javax.swing.JTextField tienThua_TextField;
    private javax.swing.JLabel vnd_Label1;
    private javax.swing.JLabel vnd_Label2;
    private javax.swing.JLabel vnd_Label3;
    private javax.swing.JLabel vnd_Label4;
    private javax.swing.JTextField yearValue_TextField;
    // End of variables declaration//GEN-END:variables
}
