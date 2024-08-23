/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.quanlytaphoa.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quanlytaphoa.Model.Account;
import com.quanlytaphoa.Model.Bill_banHang;
import com.quanlytaphoa.Model.Bill_nhapHang;
import com.quanlytaphoa.Model.Product;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import static java.io.File.separator;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
<<<<<<< HEAD
=======
import javax.swing.JButton;
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
<<<<<<< HEAD
=======
import javax.swing.JPanel;
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
import javax.swing.RowFilter;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author DELL
 */
public class MainPage1 extends JFrame {

    /**
     * Creates new form MainPage
     */
    private static String CUR_DIR = System.getProperty("user.dir");
    private String SanPham_PATH = CUR_DIR + separator + "Manage Files" + separator + "SanPham.csv";
    private String hoaDonBan_PATH = CUR_DIR + separator + "Manage Files" + separator + "hoaDonBan.json";
    private String hoaDonNhap_PATH = CUR_DIR + separator + "Manage Files" + separator + "hoaDonNhap.json";
<<<<<<< HEAD
    private String taiKhoan_PATH = CUR_DIR + separator + "Manage Files" + separator + "Account.json";
=======
    private String tkNvien_PATH = CUR_DIR + separator + "Manage Files" + separator + "Account.json";
    private String tkAdmin_PATH = CUR_DIR + separator + "Manage Files" + separator + "AdminAccount.json";
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
    private BufferedReader br;
    private BufferedWriter bw;
    private LinkedList<Product> ListSanPham = new LinkedList<>();
    private ArrayList<Bill_nhapHang> DonNhap_ListBill = new ArrayList<>();
    private ArrayList<Bill_banHang> DonBan_ListBill = new ArrayList<>();
    private int BanHang_STT = 0, NhapHang_STT = 0, NhapHang_thanhTien = 0, NhapHangTT_SLcu, NhapHangTT_GiaNhapcu;
    private int Kho_STT = 0, DonBan_STT = 0, DonNhap_STT = 0, QLTK_STT=0;

    public MainPage1() {
<<<<<<< HEAD
        this.setTitle("TGT Store");
=======
        this.setTitle("Home Page");
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
        initComponents();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        DisplayMode dm = gd.getDisplayMode();
        this.setSize(dm.getHeight()/2, dm.getWidth()/2);
        this.setPreferredSize(new Dimension(dm.getHeight()/2, dm.getWidth()/2));
        create_Digital_Clock(RealityTimer_Label, LocalDateTime.now().getDayOfWeek().name() + " "
                + LocalDateTime.now().getDayOfMonth() + "/" + LocalDateTime.now().getMonthValue() + "/" + LocalDateTime.now().getYear() + "  ");
        home_XuLiDuLieu();
        BanHang_XuLiDuLieu();
        NhapHang_XuLiDuLieu();
        DonHang_XuLiDuLieu();
        Kho_XuLiDuLieu();
        QlyTk_XuLiDuLieu(); 
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

    private void home_XuLiDuLieu() {
        create_Digital_Calendar();
        create_Digital_Clock(Clock_Label, "");

        // cho sản phẩm vào ListSanPham
        Product hangHoa;
        try {
            br = new BufferedReader(new FileReader(SanPham_PATH));
            br.readLine();
            String strHH;
            String[] infor;
            while ((strHH = br.readLine()) != null) {
                infor = strHH.split(",");
                hangHoa = new Product();
                hangHoa.setMaSP(infor[0]);
                hangHoa.setTenSP(infor[1]);
                hangHoa.setNhaSX(infor[2]);
                hangHoa.setSoLuong(Integer.parseInt(infor[3]));
                hangHoa.setDonVi(infor[4]);
                hangHoa.setGia(Integer.parseInt(infor[5]));
                hangHoa.setNSX(infor[6]);
                hangHoa.setHSD(infor[7]);
                ListSanPham.add(hangHoa);
            }
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
        }
        ListSanPham.sort(Comparator.comparing(Product::getTenSP));
    }

    private void BanHang_XuLiDuLieu() {
        // lấy thông tin từ file hoaDonBan.json
        FileReader fr = null;
        try {
            fr = new FileReader(hoaDonBan_PATH);
            Gson gson = new Gson();
            java.lang.reflect.Type type = new TypeToken<Collection<Bill_banHang>>() {
            }.getType();
            DonBan_ListBill = gson.fromJson(fr, type);
            if (DonBan_ListBill == null) {
                DonBan_ListBill = new ArrayList<>();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void NhapHang_XuLiDuLieu() {
        // Đưa dữ liệu vào DonNhap_ListBill
        FileReader fr = null;
        try {
            fr = new FileReader(hoaDonNhap_PATH);
            Gson gson = new Gson();
            java.lang.reflect.Type type = new TypeToken<Collection<Bill_nhapHang>>() {
            }.getType();
            DonNhap_ListBill = gson.fromJson(fr, type);
            if (DonNhap_ListBill == null) {
                DonNhap_ListBill = new ArrayList<>();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void Data2KhoTable(DefaultTableModel tModel, Collection<Product> list) {
        Kho_STT=0;
        for (Product Kho_hangHoa : list) {
            tModel.addRow(new Object[]{
                0,
                (Kho_hangHoa.getMaSP().startsWith("DAN") ? "Do an" : (Kho_hangHoa.getMaSP().startsWith("DUN") ? "Do uong" : "Do gia dung")),
                Kho_hangHoa.getMaSP(),
                Kho_hangHoa.getTenSP(),
                Kho_hangHoa.getSoLuong(),
                Kho_hangHoa.getGia(),
                Kho_hangHoa.getDonVi(),
                Kho_hangHoa.getNhaSX(),
                Kho_hangHoa.getNSX(),
                Kho_hangHoa.getHSD()
            });
        }
        for (int i=1; i<=tModel.getRowCount(); i++){
            tModel.setValueAt(i, i-1, 0);
        }
    }

    private void Kho_XuLiDuLieu() {
        // đưa dữ liệu sản phẩm vào Kho_Table
        DefaultTableModel tModel = (DefaultTableModel) Kho_Table.getModel();
        //ListSanPham.sort(Comparator.comparing(Product::getTenSP));
        Data2KhoTable(tModel, ListSanPham);
    }

    private void DonHang_XuLiDuLieu() {
        // đọc file và đưa dữ liệu hoá đơn vào DonBan_Table
        br = null;
        try {
            Gson gson = new Gson();
            DefaultTableModel tModel;
            br = new BufferedReader(new FileReader(hoaDonBan_PATH));
            java.lang.reflect.Type type1 = new TypeToken<ArrayList<Bill_banHang>>() {
            }.getType();
            DonBan_ListBill = gson.fromJson(br, type1);
            if (DonBan_ListBill == null) {
                DonBan_ListBill = new ArrayList<>();
            }
            tModel = (DefaultTableModel) DonBan_Table.getModel();
            for (Bill_banHang ban : DonBan_ListBill) {
                tModel.addRow(new Object[]{
                    ban.getTenKhach(),
                    ban.getMaHoaDon(),
                    ban.getNgayTaoBill(),
                    ban.getThanhTien(),
                    ban.getThanhToan()
                });
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // đọc file và đưa dữ liệu hoá đơn vào DonNhap_Table
        FileReader fr = null;
        try {
            Gson gson = new Gson();
            DefaultTableModel tModel;
            fr = new FileReader(hoaDonNhap_PATH);
            java.lang.reflect.Type type2 = new TypeToken<ArrayList<Bill_nhapHang>>() {
            }.getType();
            DonNhap_ListBill = gson.fromJson(fr, type2);
            if (DonNhap_ListBill == null) {
                DonNhap_ListBill = new ArrayList<>();
            }
            tModel = (DefaultTableModel) DonNhap_Table.getModel();
            for (Bill_nhapHang bN : DonNhap_ListBill) {
                tModel.addRow(new Object[]{
                    bN.getNhaPhanPhoi(),
                    bN.getMaHoaDon(),
                    bN.getNgayTaoBill(),
                    bN.getThanhTien()
                });
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

<<<<<<< HEAD
    private void QlyTk_XuLiDuLieu() {
        ArrayList<Account> listAccount = new ArrayList<>();
        br = null;
        //đưa dữa liệu vào ListAccount
        try {
            br = new BufferedReader(new FileReader(taiKhoan_PATH));
=======
    public JButton getQLTK_Button(){
        return QLTK_Button;
    }
    
    public JPanel getQLTK_Panel(){
        return QuanLyTK_Panel;
    }
    private void QlyTk_XuLiDuLieu() {
        themNV_RadioButton.setSelected(true);
        ThemTK_buttonGroup.add(themNV_RadioButton);
        ThemTK_buttonGroup.add(ThemAdmin_RadioButton);
        
        ArrayList<Account> listAccount = new ArrayList<>(), listAdmin = new ArrayList<>();
        br = null;
        //đưa dữa liệu vào ListAccount của nhân viên
        try {
            br = new BufferedReader(new FileReader(tkNvien_PATH));
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
            Gson gson = new Gson();
            java.lang.reflect.Type type = new TypeToken<Collection<Account>>(){}.getType();
            listAccount = gson.fromJson(br, type);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
<<<<<<< HEAD
        // thêm dữ liệu vào QLTK_Table
        QLTK_STT=0;
        DefaultTableModel model = (DefaultTableModel) QLTK_Table.getModel();
=======
        // thêm dữ liệu vào QLTK_NhanVienTable
        QLTK_STT=0;
        DefaultTableModel model;
        model = (DefaultTableModel) QLTK_NhanVienTable.getModel();
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
        model.setRowCount(0);
        for (Account ac: listAccount){
            model.addRow(new Object[]{
                ++QLTK_STT,
                ac.getUser(),
                ac.getPassword()
            });
<<<<<<< HEAD
            
=======
        }
        
        //đưa dữa liệu vào ListAccount của admin
        try {
            br = new BufferedReader(new FileReader(tkAdmin_PATH));
            Gson gson = new Gson();
            java.lang.reflect.Type type = new TypeToken<Collection<Account>>(){}.getType();
            listAdmin = gson.fromJson(br, type);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // thêm dữ liệu vào QLTK_NhanVienTable
        QLTK_STT=0;
        model = (DefaultTableModel) QLTK_AdminTable.getModel();
        model.setRowCount(0);
        for (Account ac: listAdmin){
            model.addRow(new Object[]{
                ++QLTK_STT,
                ac.getUser(),
                ac.getPassword()
            });
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
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

<<<<<<< HEAD
=======
        ThemTK_buttonGroup = new javax.swing.ButtonGroup();
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
        jPanel1 = new javax.swing.JPanel();
        RealityTimer_Panel = new javax.swing.JPanel();
        RealityTimer_Label = new javax.swing.JLabel();
        JTabbedPane = new javax.swing.JTabbedPane();
<<<<<<< HEAD
=======
        Home_ScrollPane = new javax.swing.JScrollPane();
        Home_Panel = new javax.swing.JPanel();
        ngay_Label = new javax.swing.JLabel();
        thang_Label = new javax.swing.JLabel();
        nam_Label = new javax.swing.JLabel();
        DayValue_TextField = new javax.swing.JTextField();
        monthValue_TextField = new javax.swing.JTextField();
        yearValue_TextField = new javax.swing.JTextField();
        Clock_Label = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
        BanHang_ScrollPane = new javax.swing.JScrollPane();
        BanHang_Panel = new javax.swing.JPanel();
        BanHang_HoTen_Label = new javax.swing.JLabel();
        BanHang_HoTen_TextField = new javax.swing.JTextField();
        BanHang_MaBill_Label = new javax.swing.JLabel();
        maVoucher_Label = new javax.swing.JLabel();
        BanHang_MaVoucher_TextField = new javax.swing.JTextField();
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
        BanHang_DonGia_Label = new javax.swing.JLabel();
        BanHang_DonGia_TextField = new javax.swing.JTextField();
        vnd_Label5 = new javax.swing.JLabel();
        BanHang_ThanhToan_Button = new javax.swing.JButton();
        BanHang_MaBill_TextField = new javax.swing.JTextField();
        thanhToan_Label = new javax.swing.JLabel();
        tienThua_Label = new javax.swing.JLabel();
        BanHang_TienThua_TextField = new javax.swing.JTextField();
        thanhTien_Label = new javax.swing.JLabel();
        BanHang_ThanhTien_TextField = new javax.swing.JTextField();
        giamGia_Label = new javax.swing.JLabel();
        vnd_Label3 = new javax.swing.JLabel();
        vnd_Label4 = new javax.swing.JLabel();
        BanHang_GiamGia_TextField = new javax.swing.JTextField();
        BanHang_HuyDon_Button = new javax.swing.JButton();
        vnd_Label2 = new javax.swing.JLabel();
        vnd_Label1 = new javax.swing.JLabel();
        BanHang_ThanhToan_TextField = new javax.swing.JTextField();
        BanHang_SrollPaneTable = new javax.swing.JScrollPane();
        BanHang_Table = new javax.swing.JTable();
        BanHang_ChotDon_Button = new javax.swing.JButton();
        BanHang_taoDon_Button = new javax.swing.JButton();
        NhapHang_ScollPane = new javax.swing.JScrollPane();
        NhapHang_Panel = new javax.swing.JPanel();
        NhapHang_msp_Label = new javax.swing.JLabel();
        NhapHang_TenSP_Label = new javax.swing.JLabel();
        NhapHang_SoLuong_Label = new javax.swing.JLabel();
        NhapHang_GiaNhap_Label = new javax.swing.JLabel();
        NhapHang_GiaBan_Label = new javax.swing.JLabel();
        NhapHang_Loai_Label = new javax.swing.JLabel();
        NhapHang_Donvi_Label = new javax.swing.JLabel();
        NhapHang_msp_TextField = new javax.swing.JTextField();
        NhapHang_TenSP_TextField = new javax.swing.JTextField();
        NhapHang_GiaNhap_TextField = new javax.swing.JTextField();
        NhapHang_GiaBan_TextField = new javax.swing.JTextField();
        NhapHang_Loai_CbBox = new javax.swing.JComboBox<>();
        NhapHang_TimKiem_TextField = new javax.swing.JTextField();
        NhapHang_TimKiem_Button = new javax.swing.JButton();
        NhapHang_ThemSP_Button = new javax.swing.JButton();
        NhapHang_ScrollPaneTable = new javax.swing.JScrollPane();
        NhapHang_Table = new javax.swing.JTable();
        NhapHang_XoaSP_Button = new javax.swing.JButton();
        NhapHang_PhanPhoi_Label = new javax.swing.JLabel();
        NhapHang_PhanPhoi_TextField = new javax.swing.JTextField();
        NhapHang_Donvi_TextField = new javax.swing.JTextField();
        NhapHang_SuaSP_Button = new javax.swing.JButton();
        NhapHang_SoLuong_Spinner = new javax.swing.JSpinner();
        NhapHang_NhaSX_Label = new javax.swing.JLabel();
        NhapHang_NhaSX_TextField = new javax.swing.JTextField();
        NhapHang_NSX_Label = new javax.swing.JLabel();
        NhapHang_HSD_Label = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        NhapHang_ThanhTien_Label = new javax.swing.JLabel();
        NhapHang_ThanhTien_TextField = new javax.swing.JTextField();
        NhapHang_ThanhToan_Button = new javax.swing.JButton();
        NhapHang_NSX_FormatField = new javax.swing.JFormattedTextField();
        NhapHang_HSD_FormatField = new javax.swing.JFormattedTextField();
        NhapHang_Clear_Button = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        DonHang_ScrollPane = new javax.swing.JScrollPane();
        DonHang_Panel = new javax.swing.JTabbedPane();
        DonBan_Panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DonBan_Table = new javax.swing.JTable();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jScrollPane4 = new javax.swing.JScrollPane();
        DonBan_DSSP_Table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextArea1 = new javax.swing.JTextArea();
        DonNhap_Panel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DonNhap_Table = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        DonNhap_DSSP_Table = new javax.swing.JTable();
        jTextArea2 = new javax.swing.JTextArea();
        Kho_ScrollPane = new javax.swing.JScrollPane();
        Kho_Panel = new javax.swing.JPanel();
        Kho_GiaBan_TextField = new javax.swing.JTextField();
        Kho_Loai_CbBox = new javax.swing.JComboBox<>();
        TonKho_GiaBan_Label = new javax.swing.JLabel();
        TonKho_Loai_Label = new javax.swing.JLabel();
        TonKho_Donvi_Label = new javax.swing.JLabel();
        Kho_TimKiem_Button = new javax.swing.JButton();
        Kho_ScrollPaneTable = new javax.swing.JScrollPane();
        Kho_Table = new javax.swing.JTable();
        Kho_LuuDS_Button = new javax.swing.JButton();
        Kho_XoaSP_Button = new javax.swing.JButton();
        Kho_msp_TextField = new javax.swing.JTextField();
        Kho_XoaDL_Button = new javax.swing.JButton();
        Kho_TenSP_TextField = new javax.swing.JTextField();
        Kho_msp_Label = new javax.swing.JLabel();
        Kho_TenSP_Label = new javax.swing.JLabel();
        Kho_SoLuong_Label = new javax.swing.JLabel();
        Kho_SuaSP_Button = new javax.swing.JButton();
        Kho_SoLuong_Spinner = new javax.swing.JSpinner();
        Kho_Donvi_TextField = new javax.swing.JTextField();
        Kho_NSX_Label = new javax.swing.JLabel();
        Kho_NhaSX_Label = new javax.swing.JLabel();
        Kho_HSD_Label = new javax.swing.JLabel();
        Kho_NhaSX_TextField = new javax.swing.JTextField();
        Kho_NSX_TextField = new javax.swing.JTextField();
        Kho_HSD_TextField = new javax.swing.JTextField();
        Kho_TimKiem_CbBox = new javax.swing.JComboBox<>();
        Kho_TimKiem_TextField = new javax.swing.JTextField();
        QuanLyTK_ScrollPane = new javax.swing.JScrollPane();
        QuanLyTK_Panel = new javax.swing.JPanel();
        themTk_User_Label = new javax.swing.JLabel();
        themTk_Pass_Label = new javax.swing.JLabel();
        themTk_Button = new javax.swing.JButton();
        QLTK_User_TextField = new javax.swing.JTextField();
        QLTK_PassField = new javax.swing.JPasswordField();
        themTk_Verify_Label = new javax.swing.JLabel();
        QLTK_VerifyPassField = new javax.swing.JPasswordField();
        ThemTk_Status_Label = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
<<<<<<< HEAD
        QLTK_Table = new javax.swing.JTable();
        Home_ScrollPane = new javax.swing.JScrollPane();
        Home_Panel = new javax.swing.JPanel();
        ngay_Label = new javax.swing.JLabel();
        thang_Label = new javax.swing.JLabel();
        nam_Label = new javax.swing.JLabel();
        DayValue_TextField = new javax.swing.JTextField();
        monthValue_TextField = new javax.swing.JTextField();
        yearValue_TextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        textField1 = new java.awt.TextField();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
=======
        QLTK_NhanVienTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        QLTK_AdminTable = new javax.swing.JTable();
        themNV_RadioButton = new javax.swing.JRadioButton();
        ThemAdmin_RadioButton = new javax.swing.JRadioButton();
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
<<<<<<< HEAD
        jButton6 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        Clock_Label = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
=======
        QLTK_Button = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        DangXuat_Button = new javax.swing.JButton();
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1536, 960));

        jPanel1.setLayout(new java.awt.BorderLayout());

        RealityTimer_Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        RealityTimer_Panel.setLayout(new java.awt.BorderLayout());

        RealityTimer_Label.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        RealityTimer_Label.setForeground(new java.awt.Color(51, 51, 255));
        RealityTimer_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RealityTimer_Label.setText("Real Time");
        RealityTimer_Label.setPreferredSize(new java.awt.Dimension(76, 25));
<<<<<<< HEAD
        RealityTimer_Panel.add(RealityTimer_Label, java.awt.BorderLayout.PAGE_END);
=======
        RealityTimer_Panel.add(RealityTimer_Label, java.awt.BorderLayout.CENTER);
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370

        jPanel1.add(RealityTimer_Panel, java.awt.BorderLayout.PAGE_START);

        JTabbedPane.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        JTabbedPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JTabbedPane.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        JTabbedPane.setPreferredSize(new java.awt.Dimension(1060, 600));

<<<<<<< HEAD
=======
        Home_ScrollPane.setPreferredSize(new java.awt.Dimension(1536, 960));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap(345, Short.MAX_VALUE))
        );

        Home_ScrollPane.setViewportView(Home_Panel);

        JTabbedPane.addTab("Home", Home_ScrollPane);

>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
        BanHang_Panel.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        BanHang_Panel.setMinimumSize(new java.awt.Dimension(1060, 545));
        BanHang_Panel.setOpaque(true);
        BanHang_Panel.setPreferredSize(new java.awt.Dimension(1060, 720));
        BanHang_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BanHang_PanelMouseClicked(evt);
            }
        });

        BanHang_HoTen_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        BanHang_HoTen_Label.setText("Họ và Tên");

        BanHang_HoTen_TextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        BanHang_MaBill_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        BanHang_MaBill_Label.setText("Mã hoá đơn");

        maVoucher_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        maVoucher_Label.setText("Mã Voucher");

        BanHang_MaVoucher_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        sanPham_Panel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        BanHang_ThemSP_Button.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        BanHang_ThemSP_Button.setText("Thêm SP");
        BanHang_ThemSP_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BanHang_ThemSP_ButtonActionPerformed(evt);
            }
        });

        BanHang_SuaSP_Button.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        BanHang_SuaSP_Button.setText("Sửa SP");
        BanHang_SuaSP_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BanHang_SuaSP_ButtonActionPerformed(evt);
            }
        });

        BanHang_HuySP_Button.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        BanHang_HuySP_Button.setText("Xoá SP");
        BanHang_HuySP_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BanHang_HuySP_ButtonActionPerformed(evt);
            }
        });

        BanHang_TenSP_Label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BanHang_TenSP_Label.setText("Tên sản phẩm");

        BanHang_maSP_Label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BanHang_maSP_Label.setText("Mã sản phẩm");

        BanHang_Msp_TextField.setEditable(false);
        BanHang_Msp_TextField.setBackground(new java.awt.Color(255, 255, 255));
        BanHang_Msp_TextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        BanHang_SoLuong_Label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BanHang_SoLuong_Label.setText("Số lượng");

        BanHang_Soluong_Spinner.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BanHang_Soluong_Spinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99999, 1));

        BanHang_TenSP_ComboBox.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BanHang_TenSP_ComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BanHang_TenSP_ComboBoxActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Loại SP");

        BanHang_LoaiSP_CbBox.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BanHang_LoaiSP_CbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Do an", "Do uong", "Do gia dung" }));
        BanHang_LoaiSP_CbBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BanHang_LoaiSP_CbBoxActionPerformed(evt);
            }
        });

        BanHang_Donvi_Label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BanHang_Donvi_Label.setText("Đơn vị");

        BanHang_Donvi_TextField.setEditable(false);
        BanHang_Donvi_TextField.setBackground(new java.awt.Color(255, 255, 255));
        BanHang_Donvi_TextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        BanHang_DonGia_Label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BanHang_DonGia_Label.setText("Đơn giá");

        BanHang_DonGia_TextField.setEditable(false);
        BanHang_DonGia_TextField.setBackground(new java.awt.Color(255, 255, 255));
        BanHang_DonGia_TextField.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N

        vnd_Label5.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        vnd_Label5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vnd_Label5.setText("VNĐ");

        javax.swing.GroupLayout sanPham_PanelLayout = new javax.swing.GroupLayout(sanPham_Panel);
        sanPham_Panel.setLayout(sanPham_PanelLayout);
        sanPham_PanelLayout.setHorizontalGroup(
            sanPham_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sanPham_PanelLayout.createSequentialGroup()
                .addGroup(sanPham_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sanPham_PanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4)
                        .addGap(63, 63, 63)
                        .addComponent(BanHang_LoaiSP_CbBox, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sanPham_PanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(BanHang_maSP_Label)
                        .addGap(23, 23, 23)
                        .addComponent(BanHang_Msp_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sanPham_PanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(BanHang_TenSP_Label)
                        .addGap(18, 18, 18)
                        .addComponent(BanHang_TenSP_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sanPham_PanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(BanHang_SoLuong_Label)
                        .addGap(56, 56, 56)
                        .addComponent(BanHang_Soluong_Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sanPham_PanelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(sanPham_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BanHang_Donvi_Label)
                            .addComponent(BanHang_DonGia_Label))
                        .addGap(63, 63, 63)
                        .addGroup(sanPham_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BanHang_Donvi_TextField, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(BanHang_DonGia_TextField))
                        .addGroup(sanPham_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sanPham_PanelLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(sanPham_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BanHang_ThemSP_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BanHang_SuaSP_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BanHang_HuySP_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(sanPham_PanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vnd_Label5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(24, 24, 24))
        );
        sanPham_PanelLayout.setVerticalGroup(
            sanPham_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sanPham_PanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(sanPham_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sanPham_PanelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addComponent(BanHang_LoaiSP_CbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(sanPham_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sanPham_PanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(sanPham_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sanPham_PanelLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(BanHang_maSP_Label))
                            .addComponent(BanHang_Msp_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(sanPham_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sanPham_PanelLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(BanHang_TenSP_Label))
                            .addGroup(sanPham_PanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(BanHang_TenSP_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(sanPham_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sanPham_PanelLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(BanHang_SoLuong_Label))
                            .addComponent(BanHang_Soluong_Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(sanPham_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BanHang_Donvi_Label)
                            .addComponent(BanHang_Donvi_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(sanPham_PanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BanHang_ThemSP_Button)
                        .addGap(34, 34, 34)
                        .addComponent(BanHang_SuaSP_Button)
                        .addGap(39, 39, 39)
                        .addComponent(BanHang_HuySP_Button)))
                .addGap(18, 18, 18)
                .addGroup(sanPham_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BanHang_DonGia_Label)
                    .addGroup(sanPham_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BanHang_DonGia_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(vnd_Label5)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        BanHang_ThanhToan_Button.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        BanHang_ThanhToan_Button.setText("Thanh toán");
        BanHang_ThanhToan_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BanHang_ThanhToan_ButtonActionPerformed(evt);
            }
        });

        BanHang_MaBill_TextField.setEditable(false);
        BanHang_MaBill_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        thanhToan_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        thanhToan_Label.setText("Thanh toán");

        tienThua_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tienThua_Label.setText("Tiền thừa");

        BanHang_TienThua_TextField.setEditable(false);
        BanHang_TienThua_TextField.setBackground(new java.awt.Color(255, 255, 255));
        BanHang_TienThua_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        BanHang_TienThua_TextField.setText("0");

        thanhTien_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        thanhTien_Label.setText("Thành tiền");

        BanHang_ThanhTien_TextField.setEditable(false);
        BanHang_ThanhTien_TextField.setBackground(new java.awt.Color(255, 255, 255));
        BanHang_ThanhTien_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        BanHang_ThanhTien_TextField.setText("0");

        giamGia_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        giamGia_Label.setText("Giảm giá");

        vnd_Label3.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        vnd_Label3.setText("VNĐ");

        vnd_Label4.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        vnd_Label4.setText("VNĐ");

        BanHang_GiamGia_TextField.setEditable(false);
        BanHang_GiamGia_TextField.setBackground(new java.awt.Color(255, 255, 255));
        BanHang_GiamGia_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        BanHang_GiamGia_TextField.setText("0");

        BanHang_HuyDon_Button.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        BanHang_HuyDon_Button.setText("Huỷ Đơn");
        BanHang_HuyDon_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BanHang_HuyDon_ButtonActionPerformed(evt);
            }
        });

        vnd_Label2.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        vnd_Label2.setText("VNĐ");

        vnd_Label1.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        vnd_Label1.setText("VNĐ");

        BanHang_ThanhToan_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        BanHang_ThanhToan_TextField.setText("0");
        BanHang_ThanhToan_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BanHang_ThanhToan_TextFieldKeyPressed(evt);
            }
        });

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
        BanHang_SrollPaneTable.setViewportView(BanHang_Table);

        BanHang_ChotDon_Button.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        BanHang_ChotDon_Button.setText("Chốt đơn");
        BanHang_ChotDon_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BanHang_ChotDon_ButtonActionPerformed(evt);
            }
        });

        BanHang_taoDon_Button.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        BanHang_taoDon_Button.setText("Tạo Đơn");
        BanHang_taoDon_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BanHang_taoDon_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BanHang_PanelLayout = new javax.swing.GroupLayout(BanHang_Panel);
        BanHang_Panel.setLayout(BanHang_PanelLayout);
        BanHang_PanelLayout.setHorizontalGroup(
            BanHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BanHang_PanelLayout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(BanHang_HoTen_Label)
                .addGap(18, 18, 18)
                .addComponent(BanHang_HoTen_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(357, 357, 357)
                .addComponent(BanHang_MaBill_Label)
                .addGap(18, 18, 18)
                .addComponent(BanHang_MaBill_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(BanHang_PanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(BanHang_SrollPaneTable, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addGroup(BanHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sanPham_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(BanHang_PanelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(BanHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BanHang_PanelLayout.createSequentialGroup()
                                .addComponent(thanhTien_Label)
                                .addGap(58, 58, 58)
                                .addComponent(BanHang_ThanhTien_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(vnd_Label1))
                            .addGroup(BanHang_PanelLayout.createSequentialGroup()
                                .addComponent(maVoucher_Label)
                                .addGap(43, 43, 43)
                                .addComponent(BanHang_MaVoucher_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))))))
            .addGroup(BanHang_PanelLayout.createSequentialGroup()
                .addGap(580, 580, 580)
                .addComponent(BanHang_ChotDon_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150)
                .addComponent(giamGia_Label)
                .addGap(68, 68, 68)
                .addComponent(BanHang_GiamGia_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(vnd_Label2))
            .addGroup(BanHang_PanelLayout.createSequentialGroup()
                .addGap(860, 860, 860)
                .addComponent(thanhToan_Label)
                .addGap(54, 54, 54)
                .addComponent(BanHang_ThanhToan_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(vnd_Label3))
            .addGroup(BanHang_PanelLayout.createSequentialGroup()
                .addGap(860, 860, 860)
                .addComponent(tienThua_Label)
                .addGap(65, 65, 65)
                .addComponent(BanHang_TienThua_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(vnd_Label4))
            .addGroup(BanHang_PanelLayout.createSequentialGroup()
                .addGap(760, 760, 760)
                .addComponent(BanHang_ThanhToan_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(BanHang_taoDon_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(BanHang_HuyDon_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        BanHang_PanelLayout.setVerticalGroup(
            BanHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BanHang_PanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(BanHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BanHang_PanelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(BanHang_HoTen_Label))
                    .addGroup(BanHang_PanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(BanHang_HoTen_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BanHang_PanelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(BanHang_MaBill_Label))
                    .addComponent(BanHang_MaBill_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(BanHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BanHang_SrollPaneTable, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(BanHang_PanelLayout.createSequentialGroup()
                        .addComponent(sanPham_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(BanHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(thanhTien_Label)
                            .addComponent(BanHang_ThanhTien_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(BanHang_PanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(vnd_Label1)))
                        .addGap(9, 9, 9)
                        .addGroup(BanHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BanHang_PanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(maVoucher_Label))
                            .addComponent(BanHang_MaVoucher_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addGroup(BanHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BanHang_ChotDon_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(BanHang_PanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(giamGia_Label))
                    .addGroup(BanHang_PanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(BanHang_GiamGia_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BanHang_PanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(vnd_Label2)))
                .addGroup(BanHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(thanhToan_Label)
                    .addComponent(BanHang_ThanhToan_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(BanHang_PanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(vnd_Label3)))
                .addGap(19, 19, 19)
                .addGroup(BanHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tienThua_Label)
                    .addComponent(BanHang_TienThua_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vnd_Label4))
                .addGap(50, 50, 50)
                .addGroup(BanHang_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BanHang_ThanhToan_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BanHang_taoDon_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BanHang_HuyDon_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        BanHang_ScrollPane.setViewportView(BanHang_Panel);

        JTabbedPane.addTab("Bán hàng", BanHang_ScrollPane);

        NhapHang_Panel.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        NhapHang_Panel.setPreferredSize(new java.awt.Dimension(1080, 449));
        NhapHang_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NhapHang_PanelMouseClicked(evt);
            }
        });
        NhapHang_Panel.setLayout(null);

        NhapHang_msp_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_msp_Label.setText("Mã sản phẩm");
        NhapHang_Panel.add(NhapHang_msp_Label);
<<<<<<< HEAD
        NhapHang_msp_Label.setBounds(50, 190, 104, 24);
=======
        NhapHang_msp_Label.setBounds(50, 190, 109, 24);
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370

        NhapHang_TenSP_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_TenSP_Label.setText("Tên sản phẩm");
        NhapHang_Panel.add(NhapHang_TenSP_Label);
<<<<<<< HEAD
        NhapHang_TenSP_Label.setBounds(50, 90, 108, 40);
=======
        NhapHang_TenSP_Label.setBounds(50, 90, 112, 40);
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370

        NhapHang_SoLuong_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_SoLuong_Label.setText("Số lượng");
        NhapHang_Panel.add(NhapHang_SoLuong_Label);
<<<<<<< HEAD
        NhapHang_SoLuong_Label.setBounds(374, 200, 72, 24);
=======
        NhapHang_SoLuong_Label.setBounds(374, 200, 73, 24);
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370

        NhapHang_GiaNhap_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_GiaNhap_Label.setText("Giá nhập");
        NhapHang_Panel.add(NhapHang_GiaNhap_Label);
<<<<<<< HEAD
        NhapHang_GiaNhap_Label.setBounds(374, 100, 71, 24);
=======
        NhapHang_GiaNhap_Label.setBounds(374, 100, 73, 24);
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370

        NhapHang_GiaBan_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_GiaBan_Label.setText("Giá bán");
        NhapHang_Panel.add(NhapHang_GiaBan_Label);
<<<<<<< HEAD
        NhapHang_GiaBan_Label.setBounds(373, 150, 62, 24);
=======
        NhapHang_GiaBan_Label.setBounds(373, 150, 63, 24);
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370

        NhapHang_Loai_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_Loai_Label.setText("Loại");
        NhapHang_Panel.add(NhapHang_Loai_Label);
        NhapHang_Loai_Label.setBounds(120, 140, 36, 30);

        NhapHang_Donvi_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_Donvi_Label.setText("Đơn vị");
        NhapHang_Panel.add(NhapHang_Donvi_Label);
        NhapHang_Donvi_Label.setBounds(651, 40, 55, 24);

        NhapHang_msp_TextField.setEditable(false);
        NhapHang_msp_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_Panel.add(NhapHang_msp_TextField);
        NhapHang_msp_TextField.setBounds(183, 190, 140, 30);

        NhapHang_TenSP_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_Panel.add(NhapHang_TenSP_TextField);
        NhapHang_TenSP_TextField.setBounds(184, 90, 140, 30);

        NhapHang_GiaNhap_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_Panel.add(NhapHang_GiaNhap_TextField);
        NhapHang_GiaNhap_TextField.setBounds(466, 100, 140, 30);

        NhapHang_GiaBan_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_Panel.add(NhapHang_GiaBan_TextField);
        NhapHang_GiaBan_TextField.setBounds(464, 150, 140, 30);

        NhapHang_Loai_CbBox.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_Loai_CbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Do an", "Do uong", "Do gia dung" }));
        NhapHang_Loai_CbBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NhapHang_Loai_CbBoxActionPerformed(evt);
            }
        });
        NhapHang_Panel.add(NhapHang_Loai_CbBox);
        NhapHang_Loai_CbBox.setBounds(183, 140, 140, 30);

        NhapHang_TimKiem_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_Panel.add(NhapHang_TimKiem_TextField);
        NhapHang_TimKiem_TextField.setBounds(957, 241, 212, 30);

        NhapHang_TimKiem_Button.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_TimKiem_Button.setText("Tìm kiếm");
        NhapHang_Panel.add(NhapHang_TimKiem_Button);
<<<<<<< HEAD
        NhapHang_TimKiem_Button.setBounds(1187, 241, 105, 31);
=======
        NhapHang_TimKiem_Button.setBounds(1187, 241, 107, 31);
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370

        NhapHang_ThemSP_Button.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        NhapHang_ThemSP_Button.setText("Thêm SP");
        NhapHang_ThemSP_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NhapHang_ThemSP_ButtonActionPerformed(evt);
            }
        });
        NhapHang_Panel.add(NhapHang_ThemSP_Button);
<<<<<<< HEAD
        NhapHang_ThemSP_Button.setBounds(986, 40, 117, 40);
=======
        NhapHang_ThemSP_Button.setBounds(986, 40, 118, 40);
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370

        NhapHang_ScrollPaneTable.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        NhapHang_Table.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        NhapHang_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Loại", "Mã SP", "Tên SP", "Số lượng", "Đơn vị", "Nhà SX", "Ngày nhập", "Giá nhập", "Giá bán", "NSX", "HSD"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        NhapHang_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                NhapHang_TableMousePressed(evt);
            }
        });
        NhapHang_ScrollPaneTable.setViewportView(NhapHang_Table);

        NhapHang_Panel.add(NhapHang_ScrollPaneTable);
        NhapHang_ScrollPaneTable.setBounds(50, 290, 1324, 495);

        NhapHang_XoaSP_Button.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        NhapHang_XoaSP_Button.setText("Xoá SP");
        NhapHang_XoaSP_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NhapHang_XoaSP_ButtonActionPerformed(evt);
            }
        });
        NhapHang_Panel.add(NhapHang_XoaSP_Button);
        NhapHang_XoaSP_Button.setBounds(986, 148, 118, 40);

        NhapHang_PhanPhoi_Label.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        NhapHang_PhanPhoi_Label.setText("Nhà phân phối");
        NhapHang_Panel.add(NhapHang_PhanPhoi_Label);
<<<<<<< HEAD
        NhapHang_PhanPhoi_Label.setBounds(50, 50, 106, 21);
=======
        NhapHang_PhanPhoi_Label.setBounds(50, 50, 105, 21);
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370

        NhapHang_PhanPhoi_TextField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        NhapHang_Panel.add(NhapHang_PhanPhoi_TextField);
        NhapHang_PhanPhoi_TextField.setBounds(179, 50, 142, 27);

        NhapHang_Donvi_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_Panel.add(NhapHang_Donvi_TextField);
        NhapHang_Donvi_TextField.setBounds(731, 40, 120, 30);

        NhapHang_SuaSP_Button.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        NhapHang_SuaSP_Button.setText("Sửa SP");
        NhapHang_SuaSP_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NhapHang_SuaSP_ButtonActionPerformed(evt);
            }
        });
        NhapHang_Panel.add(NhapHang_SuaSP_Button);
        NhapHang_SuaSP_Button.setBounds(986, 92, 118, 40);

        NhapHang_SoLuong_Spinner.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_SoLuong_Spinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        NhapHang_Panel.add(NhapHang_SoLuong_Spinner);
        NhapHang_SoLuong_Spinner.setBounds(465, 200, 100, 30);

        NhapHang_NhaSX_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_NhaSX_Label.setText("Nhà SX");
        NhapHang_Panel.add(NhapHang_NhaSX_Label);
<<<<<<< HEAD
        NhapHang_NhaSX_Label.setBounds(369, 50, 62, 24);
=======
        NhapHang_NhaSX_Label.setBounds(369, 50, 64, 24);
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370

        NhapHang_NhaSX_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_Panel.add(NhapHang_NhaSX_TextField);
        NhapHang_NhaSX_TextField.setBounds(461, 40, 140, 30);

        NhapHang_NSX_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_NSX_Label.setText("NSX");
        NhapHang_Panel.add(NhapHang_NSX_Label);
<<<<<<< HEAD
        NhapHang_NSX_Label.setBounds(666, 91, 39, 24);
=======
        NhapHang_NSX_Label.setBounds(666, 91, 40, 24);
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370

        NhapHang_HSD_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_HSD_Label.setText("HSD");
        NhapHang_Panel.add(NhapHang_HSD_Label);
<<<<<<< HEAD
        NhapHang_HSD_Label.setBounds(670, 151, 39, 24);
=======
        NhapHang_HSD_Label.setBounds(670, 151, 40, 24);
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370

        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("format: dd/MM/yyyy");
        NhapHang_Panel.add(jLabel2);
        jLabel2.setBounds(735, 180, 120, 16);

        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("format: dd/MM/yyyy");
        NhapHang_Panel.add(jLabel3);
        jLabel3.setBounds(731, 120, 120, 16);

        NhapHang_ThanhTien_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_ThanhTien_Label.setText("Thành tiền");
        NhapHang_Panel.add(NhapHang_ThanhTien_Label);
<<<<<<< HEAD
        NhapHang_ThanhTien_Label.setBounds(1055, 803, 82, 30);
=======
        NhapHang_ThanhTien_Label.setBounds(1055, 803, 85, 30);
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370

        NhapHang_ThanhTien_TextField.setEditable(false);
        NhapHang_ThanhTien_TextField.setBackground(new java.awt.Color(255, 255, 255));
        NhapHang_ThanhTien_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NhapHang_Panel.add(NhapHang_ThanhTien_TextField);
        NhapHang_ThanhTien_TextField.setBounds(1158, 803, 160, 30);

        NhapHang_ThanhToan_Button.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        NhapHang_ThanhToan_Button.setText("Thanh toán");
        NhapHang_ThanhToan_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NhapHang_ThanhToan_ButtonActionPerformed(evt);
            }
        });
        NhapHang_Panel.add(NhapHang_ThanhToan_Button);
        NhapHang_ThanhToan_Button.setBounds(1136, 92, 140, 40);

        NhapHang_NSX_FormatField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        NhapHang_NSX_FormatField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        NhapHang_Panel.add(NhapHang_NSX_FormatField);
        NhapHang_NSX_FormatField.setBounds(731, 90, 120, 27);

        NhapHang_HSD_FormatField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        NhapHang_HSD_FormatField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        NhapHang_Panel.add(NhapHang_HSD_FormatField);
        NhapHang_HSD_FormatField.setBounds(735, 150, 120, 27);

        NhapHang_Clear_Button.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        NhapHang_Clear_Button.setText("Clear");
        NhapHang_Clear_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NhapHang_Clear_ButtonActionPerformed(evt);
            }
        });
        NhapHang_Panel.add(NhapHang_Clear_Button);
        NhapHang_Clear_Button.setBounds(1136, 42, 140, 36);

        jComboBox1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Loại SP", "Mã SP", "Tên SP", "" }));
        NhapHang_Panel.add(jComboBox1);
<<<<<<< HEAD
        jComboBox1.setBounds(843, 243, 97, 27);
=======
        jComboBox1.setBounds(843, 243, 96, 27);
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370

        NhapHang_ScollPane.setViewportView(NhapHang_Panel);

        JTabbedPane.addTab("Nhập hàng", NhapHang_ScollPane);

        DonBan_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên khách", "Mã Hoá đơn", "Ngày mua", "Thành tiền", "Thanh toán"
            }
        ));
        DonBan_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                DonBan_TableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(DonBan_Table);

        DonBan_DSSP_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Loại SP", "Mã SP", "Tên SP", "Số lượng", "Đơn vị", "HSD", "Đơn giá", "Thành tiền"
            }
        ));
        DonBan_DSSP_Table.setEnabled(false);
        jScrollPane4.setViewportView(DonBan_DSSP_Table);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Hoá Đơn Bán Hàng");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("Danh \n sách\n sản \nphẩm");
        jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout DonBan_PanelLayout = new javax.swing.GroupLayout(DonBan_Panel);
        DonBan_Panel.setLayout(DonBan_PanelLayout);
        DonBan_PanelLayout.setHorizontalGroup(
            DonBan_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DonBan_PanelLayout.createSequentialGroup()
                .addGroup(DonBan_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DonBan_PanelLayout.createSequentialGroup()
                        .addGap(1392, 1392, 1392)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DonBan_PanelLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(DonBan_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(DonBan_PanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(716, 716, 716))
                            .addGroup(DonBan_PanelLayout.createSequentialGroup()
                                .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1188, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(DonBan_PanelLayout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        DonBan_PanelLayout.setVerticalGroup(
            DonBan_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DonBan_PanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(DonBan_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DonBan_PanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DonBan_PanelLayout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(354, 354, 354))
        );

        DonHang_Panel.addTab("Đơn Bán", DonBan_Panel);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Hoá Đơn Nhập Hàng");

        DonNhap_Table.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        DonNhap_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nhà Phân phối", "Mã Hoá đơn", "Ngày nhập", "Thành tiền"
            }
        ));
        DonNhap_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                DonNhap_TableMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(DonNhap_Table);

        DonNhap_DSSP_Table.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        DonNhap_DSSP_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Loại SP", "Mã SP", "Tên SP", "Số lượng", "Đơn vị", "HSD", "Đơn giá", "Thành tiền"
            }
        ));
        DonNhap_DSSP_Table.setEnabled(false);
        jScrollPane6.setViewportView(DonNhap_DSSP_Table);

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jTextArea2.setRows(5);
        jTextArea2.setText("Danh\n sách\n sản\nphẩm");

        javax.swing.GroupLayout DonNhap_PanelLayout = new javax.swing.GroupLayout(DonNhap_Panel);
        DonNhap_Panel.setLayout(DonNhap_PanelLayout);
        DonNhap_PanelLayout.setHorizontalGroup(
            DonNhap_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DonNhap_PanelLayout.createSequentialGroup()
                .addGroup(DonNhap_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DonNhap_PanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(DonNhap_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DonNhap_PanelLayout.createSequentialGroup()
                                .addComponent(jTextArea2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1188, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(DonNhap_PanelLayout.createSequentialGroup()
                        .addGap(219, 219, 219)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        DonNhap_PanelLayout.setVerticalGroup(
            DonNhap_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DonNhap_PanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(DonNhap_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DonNhap_PanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DonNhap_PanelLayout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(jTextArea2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(355, Short.MAX_VALUE))
        );

        DonHang_Panel.addTab("Đơn Nhập", DonNhap_Panel);

        DonHang_ScrollPane.setViewportView(DonHang_Panel);

        JTabbedPane.addTab("Đơn hàng", DonHang_ScrollPane);

        Kho_Panel.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        Kho_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Kho_PanelMouseClicked(evt);
            }
        });

        Kho_GiaBan_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        Kho_Loai_CbBox.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Kho_Loai_CbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Do an", "Do uong", "Do gia dung" }));

        TonKho_GiaBan_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TonKho_GiaBan_Label.setText("Giá bán");

        TonKho_Loai_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TonKho_Loai_Label.setText("Loại");

        TonKho_Donvi_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TonKho_Donvi_Label.setText("Đơn vị");

        Kho_TimKiem_Button.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Kho_TimKiem_Button.setText("Tìm kiếm");
        Kho_TimKiem_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kho_TimKiem_ButtonActionPerformed(evt);
            }
        });

        Kho_ScrollPaneTable.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        Kho_Table.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        Kho_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Loại", "Mã sản phẩm", "Tên SP", "Số lượng", "Giá bán", "Đơn vị", "Nhà SX", "NSX", "HSD"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Kho_Table.setRequestFocusEnabled(false);
        Kho_Table.setShowGrid(true);
        Kho_Table.setUpdateSelectionOnSort(false);
        Kho_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Kho_TableMousePressed(evt);
            }
        });
        Kho_ScrollPaneTable.setViewportView(Kho_Table);

        Kho_LuuDS_Button.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        Kho_LuuDS_Button.setText("Lưu DS");
        Kho_LuuDS_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kho_LuuDS_ButtonActionPerformed(evt);
            }
        });

        Kho_XoaSP_Button.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        Kho_XoaSP_Button.setText("Xoá SP");
        Kho_XoaSP_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kho_XoaSP_ButtonActionPerformed(evt);
            }
        });

        Kho_msp_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        Kho_XoaDL_Button.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        Kho_XoaDL_Button.setText("Xoá DL");
        Kho_XoaDL_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kho_XoaDL_ButtonActionPerformed(evt);
            }
        });

        Kho_TenSP_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        Kho_msp_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Kho_msp_Label.setText("Mã sản phẩm");

        Kho_TenSP_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Kho_TenSP_Label.setText("Tên sản phẩm");

        Kho_SoLuong_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Kho_SoLuong_Label.setText("Số lượng");

        Kho_SuaSP_Button.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        Kho_SuaSP_Button.setText("Sửa SP");
        Kho_SuaSP_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kho_SuaSP_ButtonActionPerformed(evt);
            }
        });

        Kho_SoLuong_Spinner.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Kho_SoLuong_Spinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        Kho_Donvi_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        Kho_NSX_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Kho_NSX_Label.setText("NSX");

        Kho_NhaSX_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Kho_NhaSX_Label.setText("Nhà SX");

        Kho_HSD_Label.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Kho_HSD_Label.setText("HSD");

        Kho_NhaSX_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        Kho_NSX_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        Kho_HSD_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        Kho_TimKiem_CbBox.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Kho_TimKiem_CbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Top 10 SP cùng Nhà SX", "Top 10 SP có số lượng nhỏ nhất", "Top 10 SP có số lượng lớn nhất", "Top 10 SP có NSX gần hiện tại nhất", "Top 10 SP đã hết HSD" }));
        Kho_TimKiem_CbBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kho_TimKiem_CbBoxActionPerformed(evt);
            }
        });

        Kho_TimKiem_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Kho_TimKiem_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kho_TimKiem_TextFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Kho_TimKiem_TextFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout Kho_PanelLayout = new javax.swing.GroupLayout(Kho_Panel);
        Kho_Panel.setLayout(Kho_PanelLayout);
        Kho_PanelLayout.setHorizontalGroup(
            Kho_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Kho_PanelLayout.createSequentialGroup()
                .addGroup(Kho_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Kho_PanelLayout.createSequentialGroup()
                        .addGap(410, 410, 410)
                        .addComponent(Kho_TimKiem_CbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(Kho_TimKiem_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Kho_TimKiem_Button))
                    .addGroup(Kho_PanelLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(TonKho_Loai_Label)
                        .addGap(84, 84, 84)
                        .addComponent(Kho_Loai_CbBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160)
                        .addComponent(Kho_SoLuong_Label)
                        .addGap(18, 18, 18)
                        .addComponent(Kho_SoLuong_Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addComponent(Kho_NhaSX_Label)
                        .addGap(18, 18, 18)
                        .addComponent(Kho_NhaSX_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Kho_PanelLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(Kho_msp_Label)
                        .addGap(16, 16, 16)
                        .addComponent(Kho_msp_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160)
                        .addComponent(TonKho_Donvi_Label)
                        .addGap(35, 35, 35)
                        .addComponent(Kho_Donvi_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addComponent(Kho_NSX_Label)
                        .addGap(41, 41, 41)
                        .addComponent(Kho_NSX_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Kho_PanelLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(Kho_TenSP_Label)
                        .addGap(12, 12, 12)
                        .addComponent(Kho_TenSP_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(TonKho_GiaBan_Label)
                        .addGap(28, 28, 28)
                        .addComponent(Kho_GiaBan_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addComponent(Kho_HSD_Label)
                        .addGap(41, 41, 41)
                        .addComponent(Kho_HSD_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Kho_PanelLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(Kho_ScrollPaneTable, javax.swing.GroupLayout.PREFERRED_SIZE, 1084, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addGroup(Kho_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Kho_SuaSP_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Kho_XoaSP_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Kho_XoaDL_Button)
                            .addComponent(Kho_LuuDS_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        Kho_PanelLayout.setVerticalGroup(
            Kho_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Kho_PanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(Kho_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Kho_TimKiem_CbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Kho_TimKiem_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Kho_TimKiem_Button))
                .addGroup(Kho_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Kho_PanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(Kho_ScrollPaneTable, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Kho_PanelLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(Kho_SuaSP_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Kho_XoaSP_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Kho_XoaDL_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Kho_LuuDS_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(Kho_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Kho_PanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(TonKho_Loai_Label))
                    .addComponent(Kho_Loai_CbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Kho_SoLuong_Label)
                    .addComponent(Kho_SoLuong_Spinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Kho_NhaSX_Label)
                    .addComponent(Kho_NhaSX_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(Kho_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Kho_msp_Label)
                    .addComponent(Kho_msp_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TonKho_Donvi_Label)
                    .addComponent(Kho_Donvi_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Kho_NSX_Label)
                    .addComponent(Kho_NSX_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(Kho_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Kho_TenSP_Label)
                    .addComponent(Kho_TenSP_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Kho_PanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(Kho_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TonKho_GiaBan_Label)
                            .addComponent(Kho_GiaBan_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Kho_HSD_Label)
                            .addComponent(Kho_HSD_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        Kho_ScrollPane.setViewportView(Kho_Panel);

        JTabbedPane.addTab("Kho", Kho_ScrollPane);

        themTk_User_Label.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        themTk_User_Label.setText("User");

        themTk_Pass_Label.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        themTk_Pass_Label.setText("Password");

        themTk_Button.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        themTk_Button.setText("Thêm Tài khoản");

        QLTK_User_TextField.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N

        QLTK_PassField.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N

        themTk_Verify_Label.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        themTk_Verify_Label.setText("Verify Password");

        QLTK_VerifyPassField.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N

        ThemTk_Status_Label.setBackground(new java.awt.Color(204, 204, 204));
        ThemTk_Status_Label.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ThemTk_Status_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ThemTk_Status_Label.setOpaque(true);

<<<<<<< HEAD
        QLTK_Table.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        QLTK_Table.setModel(new javax.swing.table.DefaultTableModel(
=======
        QLTK_NhanVienTable.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        QLTK_NhanVienTable.setModel(new javax.swing.table.DefaultTableModel(
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
<<<<<<< HEAD
                "STT", "User", "Password"
            }
        ));
        jScrollPane3.setViewportView(QLTK_Table);
=======
                "STT", "Username", "Password"
            }
        ));
        jScrollPane3.setViewportView(QLTK_NhanVienTable);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel7.setText("Tài khoản nhân viên");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel8.setText("Tài khoản Admin");

        QLTK_AdminTable.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        QLTK_AdminTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Username ", "Password"
            }
        ));
        jScrollPane5.setViewportView(QLTK_AdminTable);

        themNV_RadioButton.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        themNV_RadioButton.setText("Thêm tài khoản nhân viên");

        ThemAdmin_RadioButton.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        ThemAdmin_RadioButton.setText("Thêm tài khoản admin");
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370

        javax.swing.GroupLayout QuanLyTK_PanelLayout = new javax.swing.GroupLayout(QuanLyTK_Panel);
        QuanLyTK_Panel.setLayout(QuanLyTK_PanelLayout);
        QuanLyTK_PanelLayout.setHorizontalGroup(
            QuanLyTK_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
<<<<<<< HEAD
            .addGroup(QuanLyTK_PanelLayout.createSequentialGroup()
                .addGroup(QuanLyTK_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(QuanLyTK_PanelLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
=======
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, QuanLyTK_PanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(386, 386, 386))
            .addGroup(QuanLyTK_PanelLayout.createSequentialGroup()
                .addGroup(QuanLyTK_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(QuanLyTK_PanelLayout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addGroup(QuanLyTK_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(themNV_RadioButton, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(ThemAdmin_RadioButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(QuanLyTK_PanelLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
                        .addGroup(QuanLyTK_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(QuanLyTK_PanelLayout.createSequentialGroup()
                                .addComponent(themTk_Verify_Label)
                                .addGap(18, 18, 18)
                                .addComponent(QLTK_VerifyPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(ThemTk_Status_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(QuanLyTK_PanelLayout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(QuanLyTK_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(themTk_Pass_Label)
                                    .addComponent(themTk_User_Label))
                                .addGap(29, 29, 29)
                                .addGroup(QuanLyTK_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
<<<<<<< HEAD
                                    .addComponent(QLTK_PassField, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                    .addComponent(QLTK_User_TextField)))))
                    .addGroup(QuanLyTK_PanelLayout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(themTk_Button)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(240, 240, 240))
=======
                                    .addComponent(QLTK_PassField)
                                    .addComponent(QLTK_User_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(QuanLyTK_PanelLayout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(themTk_Button)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                .addGroup(QuanLyTK_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, QuanLyTK_PanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(405, 405, 405))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, QuanLyTK_PanelLayout.createSequentialGroup()
                        .addGroup(QuanLyTK_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane5)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))
                        .addGap(195, 195, 195))))
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
        );
        QuanLyTK_PanelLayout.setVerticalGroup(
            QuanLyTK_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QuanLyTK_PanelLayout.createSequentialGroup()
<<<<<<< HEAD
                .addGroup(QuanLyTK_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(QuanLyTK_PanelLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
=======
                .addGap(24, 24, 24)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(QuanLyTK_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(QuanLyTK_PanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(QuanLyTK_PanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(themNV_RadioButton)
                        .addGap(18, 18, 18)
                        .addComponent(ThemAdmin_RadioButton)
                        .addGap(37, 37, 37)
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
                        .addGroup(QuanLyTK_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(QLTK_User_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(themTk_User_Label))
                        .addGap(40, 40, 40)
                        .addGroup(QuanLyTK_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(themTk_Pass_Label)
                            .addComponent(QLTK_PassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(QuanLyTK_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ThemTk_Status_Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(QuanLyTK_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(themTk_Verify_Label)
                                .addComponent(QLTK_VerifyPassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(59, 59, 59)
<<<<<<< HEAD
                        .addComponent(themTk_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(QuanLyTK_PanelLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(720, Short.MAX_VALUE))
=======
                        .addComponent(themTk_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(588, Short.MAX_VALUE))
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
        );

        QuanLyTK_ScrollPane.setViewportView(QuanLyTK_Panel);

        JTabbedPane.addTab("Qly_Tk", QuanLyTK_ScrollPane);

<<<<<<< HEAD
        Home_ScrollPane.setPreferredSize(new java.awt.Dimension(1536, 960));

        Home_Panel.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        Home_Panel.setName(""); // NOI18N
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
        DayValue_TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DayValue_TextFieldActionPerformed(evt);
            }
        });

        monthValue_TextField.setEditable(false);
        monthValue_TextField.setBackground(new java.awt.Color(204, 204, 204));
        monthValue_TextField.setFont(new java.awt.Font("Times New Roman", 0, 40)); // NOI18N
        monthValue_TextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        monthValue_TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthValue_TextFieldActionPerformed(evt);
            }
        });

        yearValue_TextField.setEditable(false);
        yearValue_TextField.setBackground(new java.awt.Color(204, 204, 204));
        yearValue_TextField.setFont(new java.awt.Font("Times New Roman", 0, 40)); // NOI18N
        yearValue_TextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setText("voucher");

        jLabel10.setText("jLabel10");

        jLabel11.setText("jLabel11");

        jLabel12.setText("jLabel12");

        jLabel13.setText("jLabel13");

        jLabel14.setText("jLabel14");

        jLabel15.setText("jLabel15");

        jLabel16.setText("jLabel16");

        jLabel7.setText("jLabel7");

        jLabel17.setText("jLabel17");

        jLabel18.setText("jLabel18");

        jLabel19.setText("jLabel19");

        jLabel20.setText("jLabel20");

        jLabel21.setText("jLabel21");

        jLabel22.setText("jLabel22");

        textField1.setText("textField1");

        label1.setText("label1");

        label2.setText("label2");

        label3.setText("label3");

        jLabel28.setText("jLabel28");

        jLabel29.setText("jLabel29");

        jLabel30.setText("jLabel30");

        jLabel31.setText("jLabel31");

        jLabel32.setText("jLabel32");

        jLabel33.setText("jLabel33");

        jLabel34.setText("jLabel34");

        jLabel35.setText("jLabel35");

        jLabel36.setText("jLabel36");

        jLabel37.setText("jLabel37");

        javax.swing.GroupLayout Home_PanelLayout = new javax.swing.GroupLayout(Home_Panel);
        Home_Panel.setLayout(Home_PanelLayout);
        Home_PanelLayout.setHorizontalGroup(
            Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Home_PanelLayout.createSequentialGroup()
                .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Home_PanelLayout.createSequentialGroup()
                        .addGap(279, 279, 279)
                        .addComponent(ngay_Label)
                        .addGap(26, 26, 26)
                        .addComponent(DayValue_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(thang_Label)
                        .addGap(32, 32, 32)
                        .addComponent(monthValue_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(nam_Label)
                        .addGap(31, 31, 31)
                        .addComponent(yearValue_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Home_PanelLayout.createSequentialGroup()
                        .addGap(304, 304, 304)
                        .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Home_PanelLayout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(197, 197, 197)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Home_PanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(Home_PanelLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Home_PanelLayout.createSequentialGroup()
                                .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(Home_PanelLayout.createSequentialGroup()
                                        .addGap(103, 103, 103)
                                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(Home_PanelLayout.createSequentialGroup()
                                        .addGap(128, 128, 128)
                                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(349, 349, 349)
                                .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(Home_PanelLayout.createSequentialGroup()
                                        .addGap(421, 421, 421)
                                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(435, 435, 435)
                                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(228, 228, 228))
                                    .addGroup(Home_PanelLayout.createSequentialGroup()
                                        .addGap(118, 118, 118)
                                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1004, 1004, 1004))
                                    .addGroup(Home_PanelLayout.createSequentialGroup()
                                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(588, 588, 588)
                                        .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(135, 135, 135)
                                        .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(Home_PanelLayout.createSequentialGroup()
                                                .addGap(187, 187, 187)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(Home_PanelLayout.createSequentialGroup()
                                                .addGap(176, 176, 176)
                                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)))
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(968, 968, 968)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1330, 1330, 1330)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 1274, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Home_PanelLayout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(166, 166, 166)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Home_PanelLayout.setVerticalGroup(
            Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Home_PanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ngay_Label)
                    .addComponent(DayValue_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thang_Label)
                    .addComponent(monthValue_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nam_Label)
                    .addComponent(yearValue_TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Home_PanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Home_PanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(Home_PanelLayout.createSequentialGroup()
                                .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Home_PanelLayout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(Home_PanelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)))))
                    .addGroup(Home_PanelLayout.createSequentialGroup()
                        .addGap(324, 324, 324)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Home_PanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Home_PanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Home_PanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Home_PanelLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Home_PanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Home_PanelLayout.createSequentialGroup()
                                .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Home_PanelLayout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(Home_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Home_PanelLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Home_PanelLayout.createSequentialGroup()
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3))
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(96, 96, 96))
        );

        Home_ScrollPane.setViewportView(Home_Panel);

        JTabbedPane.addTab("Home", Home_ScrollPane);

=======
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
        jPanel1.add(JTabbedPane, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

<<<<<<< HEAD
        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Máy tính\\icon_home.png")); // NOI18N
        jButton1.setText("Home");
        jButton1.setIconTextGap(15);
=======
        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton1.setText("Home");
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
<<<<<<< HEAD
        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Máy tính\\icon_banhang.png")); // NOI18N
        jButton2.setText("Bán hàng");
        jButton2.setIconTextGap(15);
=======
        jButton2.setText("Bán hàng");
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
<<<<<<< HEAD
        jButton3.setIcon(new javax.swing.ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Máy tính\\icon_nhaphang.png")); // NOI18N
        jButton3.setText("Nhập hàng");
        jButton3.setIconTextGap(15);
=======
        jButton3.setText("Nhập hàng");
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
<<<<<<< HEAD
        jButton4.setIcon(new javax.swing.ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Máy tính\\icon_donhang.png")); // NOI18N
        jButton4.setText("Đơn hàng");
        jButton4.setIconTextGap(20);
=======
        jButton4.setText("Đơn hàng");
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

<<<<<<< HEAD
        jButton5.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Máy tính\\icon_warehourse.png")); // NOI18N
        jButton5.setText("Kho");
        jButton5.setIconTextGap(35);
=======
        jButton5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton5.setText("Kho");
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

<<<<<<< HEAD
        jButton6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Máy tính\\icon_quanlyacc (1).png")); // NOI18N
        jButton6.setText("Q.Lý T.Khoản");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Máy tính\\logo (138_116).png")); // NOI18N
        jLabel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel5.setOpaque(true);

=======
        QLTK_Button.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        QLTK_Button.setText("Q.Lý T.Khoản");
        QLTK_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QLTK_ButtonActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(153, 153, 153));
        jLabel5.setOpaque(true);

        DangXuat_Button.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        DangXuat_Button.setText("Đăng xuất");
        DangXuat_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DangXuat_ButtonActionPerformed(evt);
            }
        });

>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
<<<<<<< HEAD
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(20, 20, 20)))
                .addContainerGap(11, Short.MAX_VALUE))
=======
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(QLTK_Button, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(DangXuat_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
<<<<<<< HEAD
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Clock_Label.setBackground(new java.awt.Color(0, 51, 102));
        Clock_Label.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        Clock_Label.setForeground(new java.awt.Color(255, 255, 255));
        Clock_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Clock_Label.setText("Time");
        Clock_Label.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.lightGray, null, java.awt.Color.white));
        Clock_Label.setOpaque(true);

        jLabel9.setIcon(new javax.swing.ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Máy tính\\clock-icon (2).png")); // NOI18N

        jLabel23.setFont(new java.awt.Font("Viner Hand ITC", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 51, 102));
        jLabel23.setText("SHOPPING HERE");

        jLabel24.setFont(new java.awt.Font("Viner Hand ITC", 1, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 51, 204));
        jLabel24.setText("AWESOME ");

        jLabel25.setFont(new java.awt.Font("Viner Hand ITC", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 102, 51));
        jLabel25.setText("EXPERIENCE!!!");

        jLabel26.setIcon(new javax.swing.ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Máy tính\\shopping-cart-icon.png")); // NOI18N
        jLabel26.setText("jLabel26");

        jLabel27.setIcon(new javax.swing.ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Máy tính\\people_goc.png")); // NOI18N

=======
                .addComponent(QLTK_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(DangXuat_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
<<<<<<< HEAD
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Clock_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel9)))
                .addContainerGap(22, Short.MAX_VALUE))
=======
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1436, javax.swing.GroupLayout.PREFERRED_SIZE))
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
<<<<<<< HEAD
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(Clock_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(31, 31, 31))
        );

        jLabel23.getAccessibleContext().setAccessibleName("MUA SẮM TẠI ĐÂY ");
        jLabel24.getAccessibleContext().setAccessibleName("");

=======
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );

>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Kho_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Kho_PanelMouseClicked
        // TODO add your handling code here:
        if (!Kho_ScrollPaneTable.getBounds().contains(evt.getPoint())) {
            Kho_Table.clearSelection();
        }
    }//GEN-LAST:event_Kho_PanelMouseClicked

    private void Kho_TimKiem_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kho_TimKiem_TextFieldKeyReleased
        // TODO add your handling code here:
        DefaultTableModel khoModel = (DefaultTableModel) Kho_Table.getModel();
        TableRowSorter<TableModel> sorter;
        String text = Kho_TimKiem_TextField.getText();
        if (text.length()==0){
            khoModel.setRowCount(0);
            Data2KhoTable(khoModel, ListSanPham);
        }

        if (Kho_TimKiem_CbBox.getSelectedIndex() == 0) {

            sorter = new TableRowSorter<>(khoModel);
            Kho_Table.setRowSorter(sorter);
            if (text.trim().length() == 0) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 3));
                for (int i=0; i<sorter.getViewRowCount(); i++){
                    khoModel.setValueAt(i+1, sorter.convertRowIndexToModel(i), 0);
                }

            }
        }
    }//GEN-LAST:event_Kho_TimKiem_TextFieldKeyReleased

    private void Kho_TimKiem_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kho_TimKiem_TextFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Kho_FuncTimKiem();
        }
    }//GEN-LAST:event_Kho_TimKiem_TextFieldKeyPressed

    private void Kho_TimKiem_CbBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kho_TimKiem_CbBoxActionPerformed
        // TODO add your handling code here:
        if (Kho_TimKiem_CbBox.getSelectedIndex()==0){
            if (Kho_TimKiem_TextField.getText().isEmpty()){
                ((DefaultTableModel) Kho_Table.getModel()).setRowCount(0);
                Data2KhoTable((DefaultTableModel) Kho_Table.getModel(), ListSanPham);
            }
        }
    }//GEN-LAST:event_Kho_TimKiem_CbBoxActionPerformed

    private void Kho_SuaSP_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kho_SuaSP_ButtonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tModel = (DefaultTableModel) Kho_Table.getModel();
        if (tModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Không có sản phẩm nào trong kho");
        } else if (Kho_Table.getSelectedRowCount() > 1) {
            JOptionPane.showMessageDialog(this, "Chỉ được chọn duy nhất MỘT dòng!!!");
        } else if (Kho_Table.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Chưa chọn dòng nào để sửa!!!");
        } else {
            int sltRow = Kho_Table.getSelectedRow();
            if (sltRow == -1) {
                return;
            }
            // sửa trong Table
            tModel.setValueAt(Kho_Loai_CbBox.getSelectedItem(), sltRow, 1);
            tModel.setValueAt(Kho_msp_TextField.getText(), sltRow, 2);
            tModel.setValueAt(Kho_TenSP_TextField.getText(), sltRow, 3);
            tModel.setValueAt(Kho_SoLuong_Spinner.getValue(), sltRow, 4);
            tModel.setValueAt(Kho_GiaBan_TextField.getText(), sltRow, 5);
            tModel.setValueAt(Kho_Donvi_TextField.getText(), sltRow, 6);
            tModel.setValueAt(Kho_NhaSX_TextField.getText(), sltRow, 7);
            tModel.setValueAt(Kho_NSX_TextField.getText(), sltRow, 8);
            tModel.setValueAt(Kho_HSD_TextField.getText(), sltRow, 9);

            // sửa trong list
            ListSanPham.get(sltRow).setTenSP(Kho_TenSP_TextField.getText());
            ListSanPham.get(sltRow).setSoLuong(Integer.parseInt(Kho_SoLuong_Spinner.getValue().toString()));
            ListSanPham.get(sltRow).setGia(Integer.parseInt(Kho_GiaBan_TextField.getText()));
            ListSanPham.get(sltRow).setDonVi(Kho_Donvi_TextField.getText());
            ListSanPham.get(sltRow).setNhaSX(Kho_NhaSX_TextField.getText());
            ListSanPham.get(sltRow).setNSX(Kho_NSX_TextField.getText());
            ListSanPham.get(sltRow).setHSD(Kho_HSD_TextField.getText());
            JOptionPane.showMessageDialog(this, "Sửa thành công !!!");
        }
    }//GEN-LAST:event_Kho_SuaSP_ButtonActionPerformed

    private void Kho_XoaDL_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kho_XoaDL_ButtonActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn làm mới phần ghi dữ liệu?");
        if (a == JOptionPane.NO_OPTION) {
            return;
        }
        Kho_Loai_CbBox.setSelectedIndex(0);
        Kho_msp_TextField.setText("");
        Kho_TenSP_TextField.setText("");
        Kho_SoLuong_Spinner.setValue(0);
        Kho_GiaBan_TextField.setText("");
        Kho_Donvi_TextField.setText("");
        Kho_NhaSX_TextField.setText("");
        Kho_NSX_TextField.setText("");
        Kho_HSD_TextField.setText("");
    }//GEN-LAST:event_Kho_XoaDL_ButtonActionPerformed

    private void Kho_XoaSP_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kho_XoaSP_ButtonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tModel = (DefaultTableModel) Kho_Table.getModel();
        if (tModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Table is empty");
        } else {
            int sltedRow = Kho_Table.getSelectedRow();
            if (sltedRow == -1) {
                JOptionPane.showMessageDialog(this, "Chưa chọn dòng nào để xoá");
                return;
            }
            int choice = JOptionPane.showConfirmDialog(this, "Bạn muốn xoá sản phẩm này?");
            if (choice == JOptionPane.YES_OPTION) {
                ListSanPham.remove(sltedRow); // xoá trong list
                tModel.removeRow(sltedRow); // xoá trong Table
                Kho_STT = tModel.getRowCount();
                for (int i = sltedRow; i < tModel.getRowCount(); i++) {
                    Kho_Table.setValueAt((Integer) Kho_Table.getValueAt(i, 0) - 1, i, 0);
                }
                Kho_Loai_CbBox.setSelectedIndex(0);
                Kho_msp_TextField.setText("");
                Kho_TenSP_TextField.setText("");
                Kho_SoLuong_Spinner.setValue(0);
                Kho_GiaBan_TextField.setText("");
                Kho_Donvi_TextField.setText("");
                Kho_NhaSX_TextField.setText("");
                Kho_NSX_TextField.setText("");
                Kho_HSD_TextField.setText("");
            }
        }
    }//GEN-LAST:event_Kho_XoaSP_ButtonActionPerformed

    private void Kho_LuuDS_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kho_LuuDS_ButtonActionPerformed
        try {
            // TODO add your handling code here:
            Product p;
            DefaultTableModel tModel = (DefaultTableModel) Kho_Table.getModel();
            bw = new BufferedWriter(new FileWriter(SanPham_PATH));
            bw.write("Ma SP,Ten SP,Nha SX,So luong,Don vi,Gia ban,NSX,HSD\n");
            for (int row = 0; row < Kho_Table.getRowCount(); row++) {
                p = new Product();
                p.setMaSP(tModel.getValueAt(row, 2).toString());
                p.setTenSP(tModel.getValueAt(row, 3).toString());
                p.setSoLuong(Integer.parseInt(tModel.getValueAt(row, 4).toString()));
                p.setGia(Integer.parseInt(tModel.getValueAt(row, 5).toString()));
                p.setDonVi(tModel.getValueAt(row, 6).toString());
                p.setNhaSX(tModel.getValueAt(row, 7).toString());
                p.setNSX(tModel.getValueAt(row, 8).toString());
                p.setHSD(tModel.getValueAt(row, 9).toString());
                // ghi sản phẩm vào file
                bw.append(String.format("%s,%s,%s,%d,%s,%d,%s,%s\n",
                    p.getMaSP(), p.getTenSP(), p.getNhaSX(), p.getSoLuong(),
                    p.getDonVi(), p.getGia(), p.getNSX(), p.getHSD()));
        }
        bw.flush();
        bw.close();
        } catch (IOException ex) {
            Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Kho_LuuDS_ButtonActionPerformed

    private void Kho_TableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Kho_TableMousePressed
        // TODO add your handling code here:
        DefaultTableModel tModel = (DefaultTableModel) Kho_Table.getModel();
        int sltRow = Kho_Table.getSelectedRow();
        if (sltRow >= 0) {
            Kho_Loai_CbBox.setSelectedItem(tModel.getValueAt(sltRow, 1).toString());
            Kho_msp_TextField.setText(tModel.getValueAt(sltRow, 2).toString());
            Kho_TenSP_TextField.setText(tModel.getValueAt(sltRow, 3).toString());
            Kho_SoLuong_Spinner.setValue(Integer.valueOf(tModel.getValueAt(sltRow, 4).toString()));
            Kho_GiaBan_TextField.setText(Integer.valueOf(tModel.getValueAt(sltRow, 5).toString()) + "");
            Kho_Donvi_TextField.setText(tModel.getValueAt(sltRow, 6).toString());
            Kho_NhaSX_TextField.setText(tModel.getValueAt(sltRow, 7).toString());
            Kho_NSX_TextField.setText(tModel.getValueAt(sltRow, 8).toString());
            Kho_HSD_TextField.setText(tModel.getValueAt(sltRow, 9).toString());
        }
    }//GEN-LAST:event_Kho_TableMousePressed

    private void Kho_TimKiem_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kho_TimKiem_ButtonActionPerformed
        // TODO add your handling code here:
        Kho_FuncTimKiem();
    }//GEN-LAST:event_Kho_TimKiem_ButtonActionPerformed

    private void DonNhap_TableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DonNhap_TableMousePressed
        // TODO add your handling code here:
        DefaultTableModel ListModel = (DefaultTableModel) DonNhap_DSSP_Table.getModel();
        int sltRow = DonNhap_Table.getSelectedRow();
        if (sltRow >= 0) {
            ListModel.setRowCount(0);
            DonNhap_STT = 0;
            for (Bill_nhapHang bN : DonNhap_ListBill) {
                if (bN.getMaHoaDon().equals(DonNhap_Table.getValueAt(sltRow, 1).toString())) {
                    ArrayList<Product> tempList = bN.getDsachSP();
                    for (Product sp : tempList) {
                        ListModel.addRow(new Object[]{
                            ++DonNhap_STT,
                            (sp.getMaSP().startsWith("DAN") ? "Do an" : (sp.getMaSP().startsWith("DUN") ? "Do uong" : "Do gia dung")),
                            sp.getMaSP(),
                            sp.getTenSP(),
                            sp.getSoLuong(),
                            sp.getDonVi(),
                            sp.getHSD(),
                            sp.getGia(),
                            sp.getSoLuong() * sp.getGia()
                        });
                    }
                }
            }
        }
    }//GEN-LAST:event_DonNhap_TableMousePressed

    private void DonBan_TableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DonBan_TableMousePressed
        // TODO add your handling code here:
        DefaultTableModel ListModel = (DefaultTableModel) DonBan_DSSP_Table.getModel();
        int sltRow = DonBan_Table.getSelectedRow();
        if (sltRow >= 0) {
            ListModel.setRowCount(0);
            DonBan_STT = 0;
            for (Bill_banHang bB : DonBan_ListBill) {
                if (bB.getMaHoaDon().equals(DonBan_Table.getValueAt(sltRow, 1).toString())) {
                    ArrayList<Product> tempList = bB.getDsachSP();
                    for (Product sp : tempList) {
                        ListModel.addRow(new Object[]{
                            ++DonBan_STT,
                            (sp.getMaSP().startsWith("DAN") ? "Do an" : (sp.getMaSP().startsWith("DUN") ? "Do uong" : "Do gia dung")),
                            sp.getMaSP(),
                            sp.getTenSP(),
                            sp.getSoLuong(),
                            sp.getDonVi(),
                            sp.getHSD(),
                            sp.getGia(),
                            sp.getSoLuong() * sp.getGia()
                        });
                    }
                }
            }
        }
    }//GEN-LAST:event_DonBan_TableMousePressed

    private void NhapHang_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NhapHang_PanelMouseClicked
        // TODO add your handling code here:
        if (!NhapHang_ScrollPaneTable.getBounds().contains(evt.getPoint())) {
            NhapHang_Table.clearSelection();
        }
    }//GEN-LAST:event_NhapHang_PanelMouseClicked

    private void NhapHang_Clear_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NhapHang_Clear_ButtonActionPerformed
        // TODO add your handling code here:
        NhapHang_PhanPhoi_TextField.setText("");
        NhapHang_Loai_CbBox.setSelectedIndex(0);
        NhapHang_msp_TextField.setText("");
        NhapHang_TenSP_TextField.setText("");
        NhapHang_SoLuong_Spinner.setValue(0);
        NhapHang_Donvi_TextField.setText("");
        NhapHang_NhaSX_TextField.setText("");
        NhapHang_GiaNhap_TextField.setText("");
        NhapHang_GiaBan_TextField.setText("");
        NhapHang_NSX_FormatField.setText("");
        NhapHang_HSD_FormatField.setText("");
    }//GEN-LAST:event_NhapHang_Clear_ButtonActionPerformed

    private void NhapHang_ThanhToan_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NhapHang_ThanhToan_ButtonActionPerformed
        // TODO add your handling code here:

        DefaultTableModel tModel = (DefaultTableModel) NhapHang_Table.getModel();
        int rowCount = tModel.getRowCount();
        if (rowCount == 0) {
            JOptionPane.showMessageDialog(this, "Chưa nhập mặt hàng nào!!!");
        } else {
            ArrayList<Product> sanPhamNhap = new ArrayList<>();
            Product sPham;
            for (int row = 0; row < rowCount; row++) {
                boolean check = false;
                sPham = new Product();
                sPham.setMaSP(tModel.getValueAt(row, 2).toString());
                sPham.setTenSP(tModel.getValueAt(row, 3).toString());
                sPham.setSoLuong(Integer.parseInt(tModel.getValueAt(row, 4).toString()));
                sPham.setDonVi(tModel.getValueAt(row, 5).toString());
                sPham.setNhaSX(tModel.getValueAt(row, 6).toString());
                sPham.setGia(Integer.parseInt(tModel.getValueAt(row, 9).toString()));
                sPham.setNSX(tModel.getValueAt(row, 10).toString());
                sPham.setHSD(tModel.getValueAt(row, 11).toString());
                sanPhamNhap.add(sPham);

                // nếu đã tồn tại mã sp thì tăng số lượng lên
                for (int i = 0; i < ListSanPham.size(); i++) {
                    if (ListSanPham.get(i).getMaSP().equals(tModel.getValueAt(row, 2))) {
                        check = true;
                        int newSL = ListSanPham.get(i).getSoLuong() + Integer.parseInt(tModel.getValueAt(row, 4).toString());
                        ListSanPham.get(i).setSoLuong(newSL);
                        break;
                    }
                }

                // nếu chưa có sản phẩm thì thêm vào list
                if (!check) {
                    Product sp = new Product();
                    sp.setMaSP(tModel.getValueAt(row, 2).toString());
                    sp.setTenSP(tModel.getValueAt(row, 3).toString());
                    sp.setSoLuong(Integer.parseInt(tModel.getValueAt(row, 4).toString()));
                    sp.setDonVi(tModel.getValueAt(row, 5).toString());
                    sp.setNhaSX(tModel.getValueAt(row, 6).toString());
                    sp.setGia(Integer.parseInt(tModel.getValueAt(row, 9).toString()));
                    sp.setNSX(tModel.getValueAt(row, 10).toString());
                    sp.setHSD(tModel.getValueAt(row, 11).toString());
                    ListSanPham.add(sp);
                }
            }

            // Cập nhật sản phẩm vào file sanPham.csv
            try {
                bw = new BufferedWriter(new FileWriter(SanPham_PATH));
                bw.write("Ma SP,Ten SP,Nha SX,So luong,Don vi,Gia ban,NSX,HSD\n");
                for (Product p : ListSanPham) {
                    bw.append(String.format("%s,%s,%s,%d,%s,%d,%s,%s\n",
                        p.getMaSP(), p.getTenSP(), p.getNhaSX(), p.getSoLuong(),
                        p.getDonVi(), p.getGia(), p.getNSX(), p.getHSD()));
            }
            bw.flush();
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
        }

        //cập nhật vào kho_Table
        DefaultTableModel KhoModel = (DefaultTableModel) Kho_Table.getModel();
        KhoModel.setRowCount(0);
        Kho_STT = 0;
        for (Product sp : ListSanPham) {
            KhoModel.addRow(new Object[]{
                ++Kho_STT,
                sp.getMaSP(),
                (sp.getMaSP().startsWith("DAN") ? "Do an" : (sp.getMaSP().startsWith("DUN") ? "Do uong" : "Do gia dung")),
                sp.getTenSP(),
                sp.getSoLuong(),
                sp.getGia(),
                sp.getDonVi(),
                sp.getNhaSX(),
                sp.getNSX(),
                sp.getHSD()
            });
        }

        // lấy số lượng hoá đơn bán và mua trong file Numbers.csv
        String Numbers_PATH = CUR_DIR + separator + "Manage Files" + separator + "Numbers.csv";
        String[] soDon = null;
        try {
            br = new BufferedReader(new FileReader(Numbers_PATH));
            br.readLine();
            soDon = br.readLine().split(",");
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
        }

        //tạo bill nhập
        int hdn = Integer.parseInt(soDon[1]);
        Bill_nhapHang billNhap;
        billNhap = new Bill_nhapHang();
        billNhap.setNhaPhanPhoi(NhapHang_PhanPhoi_TextField.getText());
        billNhap.setMaHoaDon((hdn < 10) ? ("HDN00" + hdn) : ((hdn < 100) ? ("HDN0" + hdn) : ("HDN" + hdn)));
        billNhap.setDsachSP(sanPhamNhap);
        billNhap.setNgayTaoBill(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        billNhap.setThanhTien(NhapHang_thanhTien);
        billNhap.setThanhToan(NhapHang_thanhTien);
        DonNhap_ListBill.add(billNhap); // thêm vào dsach hoá đơn bán

        // cập nhật vào Đơn hàng -> đơn nhập -> DonNhap_Table
        DefaultTableModel donNhapModel = (DefaultTableModel) DonNhap_Table.getModel();
        donNhapModel.addRow(new Object[]{
            billNhap.getNhaPhanPhoi(),
            billNhap.getMaHoaDon(),
            billNhap.getNgayTaoBill(),
            billNhap.getThanhTien()
        });

        // lưu bill nhập hàng vào file hoaDonNhap.json
        FileWriter fw = null;
        try {
            fw = new FileWriter(hoaDonNhap_PATH);
            Gson gson = new Gson();
            gson.toJson(DonNhap_ListBill, fw);
        } catch (IOException ex) {
            Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.flush();
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //cập nhật số đơn bán vào Numbers.csv
        try {
            bw = new BufferedWriter(new FileWriter(Numbers_PATH));
            bw.write("soDonBan,soDonNhap\n");
            bw.append(soDon[0] + "," + (Integer.parseInt(soDon[1]) + 1));
            bw.flush();
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
        }

        JOptionPane.showMessageDialog(this, "Bạn đã thanh toán " + NhapHang_thanhTien + " VNĐ", "Thanh toán thành công", JOptionPane.INFORMATION_MESSAGE);

        // xong r thì xoá hết dữ liệu
        NhapHang_thanhTien = 0;
        NhapHang_STT = 0;
        NhapHang_PhanPhoi_TextField.setText("");
        NhapHang_Loai_CbBox.setSelectedIndex(0);
        NhapHang_msp_TextField.setText("");
        NhapHang_TenSP_TextField.setText("");
        NhapHang_SoLuong_Spinner.setValue(0);
        NhapHang_Donvi_TextField.setText("");
        NhapHang_NhaSX_TextField.setText("");
        NhapHang_GiaNhap_TextField.setText("");
        NhapHang_GiaBan_TextField.setText("");
        NhapHang_NSX_FormatField.setText("");
        NhapHang_HSD_FormatField.setText("");
        NhapHang_ThanhTien_TextField.setText("");
        DefaultTableModel tm = (DefaultTableModel) NhapHang_Table.getModel();
        tm.setRowCount(0);
        }
    }//GEN-LAST:event_NhapHang_ThanhToan_ButtonActionPerformed

    private void NhapHang_SuaSP_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NhapHang_SuaSP_ButtonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tableModel = (DefaultTableModel) NhapHang_Table.getModel();
        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Bảng đang trống");
        } else if (NhapHang_Table.getSelectedRowCount() > 1) {
            JOptionPane.showMessageDialog(this, "Chỉ được chọn MỘT dòng");
        } else {
            int a = JOptionPane.showConfirmDialog(this, "Bạn muốn lưu thay đổi không?");
            if (a == JOptionPane.NO_OPTION) {
                return;
            }
            int sltedRow = NhapHang_Table.getSelectedRow();
            if (sltedRow < 0) {
                JOptionPane.showMessageDialog(this, "Chưa có dòng nào được chọn!!!");
                return;
            }
            // tính Thành tiền
            NhapHangTT_SLcu = Integer.parseInt(tableModel.getValueAt(sltedRow, 4).toString());
            NhapHangTT_GiaNhapcu = Integer.parseInt(tableModel.getValueAt(sltedRow, 8).toString());
            NhapHang_thanhTien = NhapHang_thanhTien - NhapHangTT_GiaNhapcu * NhapHangTT_SLcu
            + (Integer.parseInt(NhapHang_GiaNhap_TextField.getText())) * (Integer.parseInt(NhapHang_SoLuong_Spinner.getValue().toString()));
            NhapHang_ThanhTien_TextField.setText(NhapHang_thanhTien + "");
            // đưa data vào table
            tableModel.setValueAt(NhapHang_Loai_CbBox.getSelectedItem(), sltedRow, 1);
            tableModel.setValueAt(NhapHang_msp_TextField.getText(), sltedRow, 2);
            tableModel.setValueAt(NhapHang_TenSP_TextField.getText(), sltedRow, 3);
            tableModel.setValueAt(NhapHang_SoLuong_Spinner.getValue(), sltedRow, 4);
            tableModel.setValueAt(NhapHang_Donvi_TextField.getText(), sltedRow, 5);
            tableModel.setValueAt(NhapHang_NhaSX_TextField.getText(), sltedRow, 6);
            tableModel.setValueAt(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), sltedRow, 7);
            tableModel.setValueAt(NhapHang_GiaNhap_TextField.getText(), sltedRow, 8);
            tableModel.setValueAt(NhapHang_GiaBan_TextField.getText(), sltedRow, 9);
            tableModel.setValueAt(NhapHang_NSX_FormatField.getText(), sltedRow, 10);
            tableModel.setValueAt(NhapHang_HSD_FormatField.getText(), sltedRow, 11);
        }
    }//GEN-LAST:event_NhapHang_SuaSP_ButtonActionPerformed

    private void NhapHang_XoaSP_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NhapHang_XoaSP_ButtonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tModel = (DefaultTableModel) NhapHang_Table.getModel();
        if (tModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Table is empty");
        } else {
            int choice = JOptionPane.showConfirmDialog(this, "Bạn muốn xoá sản phẩm này?");
            if (choice == JOptionPane.YES_OPTION) {
                int sltedRow = NhapHang_Table.getSelectedRow();
                NhapHang_thanhTien -= Integer.parseInt(NhapHang_Table.getValueAt(sltedRow, 4).toString()) * Integer.parseInt(NhapHang_Table.getValueAt(sltedRow, 8).toString());
                NhapHang_ThanhTien_TextField.setText(NhapHang_thanhTien + "");
                tModel.removeRow(sltedRow);
                NhapHang_STT = tModel.getRowCount();
                for (int i = sltedRow; i < tModel.getRowCount(); i++) {
                    NhapHang_Table.setValueAt((Integer) NhapHang_Table.getValueAt(i, 0) - 1, i, 0);
                }
            }
        }
    }//GEN-LAST:event_NhapHang_XoaSP_ButtonActionPerformed

    private void NhapHang_TableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NhapHang_TableMousePressed
        // TODO add your handling code here:
        // đưa data từ table lên
        DefaultTableModel tModel = (DefaultTableModel) NhapHang_Table.getModel();
        int sltRow = NhapHang_Table.getSelectedRow();
        NhapHang_Loai_CbBox.setSelectedItem(tModel.getValueAt(sltRow, 1));
        NhapHang_msp_TextField.setText(tModel.getValueAt(sltRow, 2).toString());
        NhapHang_TenSP_TextField.setText(tModel.getValueAt(sltRow, 3).toString());
        NhapHang_SoLuong_Spinner.setValue(Integer.valueOf(tModel.getValueAt(sltRow, 4).toString()));
        NhapHang_Donvi_TextField.setText(tModel.getValueAt(sltRow, 5).toString());
        NhapHang_NhaSX_TextField.setText(tModel.getValueAt(sltRow, 6).toString());
        NhapHang_GiaNhap_TextField.setText(tModel.getValueAt(sltRow, 8).toString());
        NhapHang_GiaBan_TextField.setText(tModel.getValueAt(sltRow, 9).toString());
        NhapHang_NSX_FormatField.setText(tModel.getValueAt(sltRow, 10).toString());
        NhapHang_HSD_FormatField.setText(tModel.getValueAt(sltRow, 11).toString());
    }//GEN-LAST:event_NhapHang_TableMousePressed

    private void NhapHang_ThemSP_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NhapHang_ThemSP_ButtonActionPerformed
        // TODO add your handling code here:
        if (NhapHang_msp_TextField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa có dữ liệu");
            return;
        }

        DefaultTableModel tableModel = (DefaultTableModel) NhapHang_Table.getModel();
        // nếu sản phẩm đã tồn tại trong bảng thì tăng số lượng
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            if (NhapHang_msp_TextField.getText().equals(tableModel.getValueAt(row, 2).toString())) {
                if (JOptionPane.showConfirmDialog(this, "Bạn muốn thêm " + NhapHang_SoLuong_Spinner.getValue().toString() + " sản phẩm?") == JOptionPane.YES_OPTION) {
                    // Sửa số lượng
                    tableModel.setValueAt(Integer.parseInt(tableModel.getValueAt(row, 4).toString())
                        + Integer.parseInt(NhapHang_SoLuong_Spinner.getValue().toString()), row, 4);
                    // sửa thành tiền
                    try {
                        NhapHang_thanhTien += Integer.parseInt(NhapHang_GiaNhap_TextField.getText()) * Integer.parseInt(NhapHang_SoLuong_Spinner.getValue().toString());
                        NhapHang_ThanhTien_TextField.setText(NhapHang_thanhTien + "");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Sai định dạng giá nhập!!!");
                        return;
                    }
                    return;
                } else {
                    return;
                }
            }
        }
        try {
            NhapHang_thanhTien += Integer.parseInt(NhapHang_GiaNhap_TextField.getText()) * Integer.parseInt(NhapHang_SoLuong_Spinner.getValue().toString());
            NhapHang_ThanhTien_TextField.setText(NhapHang_thanhTien + "");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Sai định dạng giá nhập!!!");
            return;
        }
        // đưa dữ liệu vào bảng
        DefaultTableModel model = (DefaultTableModel) NhapHang_Table.getModel();
        model.addRow(new Object[]{
            ++NhapHang_STT,
            NhapHang_Loai_CbBox.getSelectedItem().toString(),
            NhapHang_msp_TextField.getText(),
            NhapHang_TenSP_TextField.getText(),
            NhapHang_SoLuong_Spinner.getValue().toString(),
            NhapHang_Donvi_TextField.getText(),
            NhapHang_NhaSX_TextField.getText(),
            LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            NhapHang_GiaNhap_TextField.getText(),
            NhapHang_GiaBan_TextField.getText(),
            NhapHang_NSX_FormatField.getText(),
            NhapHang_HSD_FormatField.getText(),});

    // làm mới phần nhập
    NhapHang_Loai_CbBox.setSelectedIndex(0);
    NhapHang_msp_TextField.setText("");
    NhapHang_TenSP_TextField.setText("");
    NhapHang_SoLuong_Spinner.setValue(0);
    NhapHang_Donvi_TextField.setText("");
    NhapHang_NhaSX_TextField.setText("");
    NhapHang_GiaNhap_TextField.setText("");
    NhapHang_GiaBan_TextField.setText("");
    NhapHang_NSX_FormatField.setText("");
    NhapHang_HSD_FormatField.setText("");
    }//GEN-LAST:event_NhapHang_ThemSP_ButtonActionPerformed

    private void NhapHang_Loai_CbBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NhapHang_Loai_CbBoxActionPerformed
        // TODO add your handling code here:
        boolean check = false;
        JComboBox<String> combo = (JComboBox<String>) evt.getSource();
        String sltedItem = (String) combo.getSelectedItem();
        if (!NhapHang_TenSP_TextField.getText().equals("")) {
            switch (sltedItem) {
                case "Do an" -> {
                    for (Product p : ListSanPham) {
                        // nếu đã có trong list thì không cần tạo mới mã SP
                        if (p.getTenSP().equals(NhapHang_TenSP_TextField.getText()) && p.getMaSP().startsWith("DAN")) {
                            check = true;
                            NhapHang_msp_TextField.setText(p.getMaSP());
                            NhapHang_NhaSX_TextField.setText(p.getNhaSX());
                            NhapHang_GiaBan_TextField.setText(p.getGia() + "");
                            NhapHang_Donvi_TextField.setText(p.getDonVi());
                            NhapHang_NSX_FormatField.setText(p.getNSX());
                            NhapHang_HSD_FormatField.setText(p.getHSD());
                            break;
                        }
                    }

                    // nếu đã có trong dsach nhập hàng thì ko cần tạo mã sp mới
                    if (!check) {
                        for (int row = 0; row < NhapHang_Table.getRowCount(); row++) {
                            if (NhapHang_Table.getValueAt(row, 3).equals(NhapHang_TenSP_TextField.getText())
                                && NhapHang_Table.getValueAt(row, 2).toString().startsWith("DAN")) {
                                check = true;
                                NhapHang_msp_TextField.setText(NhapHang_Table.getValueAt(row, 2).toString());
                                NhapHang_SoLuong_Spinner.setValue(NhapHang_Table.getValueAt(row, 4).toString());
                                NhapHang_Donvi_TextField.setText(NhapHang_Table.getValueAt(row, 5).toString());
                                NhapHang_NhaSX_TextField.setText(NhapHang_Table.getValueAt(row, 6).toString());
                                NhapHang_GiaNhap_TextField.setText(NhapHang_Table.getValueAt(row, 8).toString());
                                NhapHang_GiaBan_TextField.setText(NhapHang_Table.getValueAt(row, 9).toString());
                                NhapHang_NSX_FormatField.setText(NhapHang_Table.getValueAt(row, 10).toString());
                                NhapHang_HSD_FormatField.setText(NhapHang_Table.getValueAt(row, 11).toString());
                                break;
                            }
                        }
                    }

                    // tạo mã sp mới
                    if (!check) {
                        String BiggestID;
                        // kiểm tra để tìm số mã lớn nhất có trong bảng
                        boolean check1 = false;
                        for (int row = NhapHang_Table.getRowCount() - 1; row >= 0; row--) {
                            if (NhapHang_Table.getValueAt(row, 2).toString().startsWith("DAN")) {
                                BiggestID = NhapHang_Table.getValueAt(row, 2).toString();
                                int valOfNewID = Integer.parseInt(BiggestID.substring(3)) + 1;
                                if (valOfNewID < 10) {
                                    NhapHang_msp_TextField.setText("DAN00" + valOfNewID);
                                } else if (valOfNewID < 100) {
                                    NhapHang_msp_TextField.setText("DAN0" + valOfNewID);
                                } else {
                                    NhapHang_msp_TextField.setText("DAN" + valOfNewID);
                                }
                                check1 = true;
                                break;
                            }
                        }
                        // kiểm tra tìm số mã lớn nhất trong list sản phẩm
                        if (!check1){
                            for (int r = ListSanPham.size() - 1; r >= 0; r--) {
                                if (ListSanPham.get(r).getMaSP().startsWith("DAN")) {
                                    BiggestID = ListSanPham.get(r).getMaSP();
                                    int valOfNewID = Integer.parseInt(BiggestID.substring(3)) + 1;
                                    if (valOfNewID < 10) {
                                        NhapHang_msp_TextField.setText("DAN00" + valOfNewID);
                                    } else if (valOfNewID < 100) {
                                        NhapHang_msp_TextField.setText("DAN0" + valOfNewID);
                                    } else {
                                        NhapHang_msp_TextField.setText("DAN" + valOfNewID);
                                    }
                                    return;
                                }
                            }
                        }
                    }
                }
                case "Do uong" -> {
                    for (Product p : ListSanPham) {
                        // nếu đã có trong kho thì không cần tạo mới mã SP
                        if (p.getTenSP().equals(NhapHang_TenSP_TextField.getText()) && p.getMaSP().startsWith("DUN")) {
                            check = true;
                            NhapHang_msp_TextField.setText(p.getMaSP());
                            NhapHang_NhaSX_TextField.setText(p.getNhaSX());
                            NhapHang_GiaBan_TextField.setText(p.getGia() + "");
                            NhapHang_Donvi_TextField.setText(p.getDonVi());
                            NhapHang_NSX_FormatField.setText(p.getNSX());
                            NhapHang_HSD_FormatField.setText(p.getHSD());
                            break;
                        }
                    }

                    // nếu đã có trong dsach nhập hàng thì ko cần tạo mã sp mới
                    if (!check) {
                        for (int row = 0; row < NhapHang_Table.getRowCount(); row++) {
                            if (NhapHang_Table.getValueAt(row, 3).equals(NhapHang_TenSP_TextField.getText())
                                && NhapHang_Table.getValueAt(row, 2).toString().startsWith("DUN")) {
                                check = true;
                                NhapHang_msp_TextField.setText(NhapHang_Table.getValueAt(row, 2).toString());
                                NhapHang_SoLuong_Spinner.setValue(NhapHang_Table.getValueAt(row, 4).toString());
                                NhapHang_Donvi_TextField.setText(NhapHang_Table.getValueAt(row, 5).toString());
                                NhapHang_NhaSX_TextField.setText(NhapHang_Table.getValueAt(row, 6).toString());
                                NhapHang_GiaNhap_TextField.setText(NhapHang_Table.getValueAt(row, 8).toString());
                                NhapHang_GiaBan_TextField.setText(NhapHang_Table.getValueAt(row, 9).toString());
                                NhapHang_NSX_FormatField.setText(NhapHang_Table.getValueAt(row, 10).toString());
                                NhapHang_HSD_FormatField.setText(NhapHang_Table.getValueAt(row, 11).toString());

                                break;
                            }
                        }
                    }

                    // chưa có thì tạo mã sản phẩm mới và đưa vào NhapHang_Msp_TextField
                    if (!check) {
                        String BiggestID;
                        for (int row = NhapHang_Table.getRowCount() - 1; row >= 0; row--) {
                            if (NhapHang_Table.getValueAt(row, 2).toString().startsWith("DUN")) {
                                BiggestID = NhapHang_Table.getValueAt(row, 2).toString();
                                int valOfNewID = Integer.parseInt(BiggestID.substring(3)) + 1;
                                if (valOfNewID < 10) {
                                    NhapHang_msp_TextField.setText("DUN00" + valOfNewID);
                                } else if (valOfNewID < 100) {
                                    NhapHang_msp_TextField.setText("DUN0" + valOfNewID);
                                } else {
                                    NhapHang_msp_TextField.setText("DUN" + valOfNewID);
                                }
                                return;
                            }
                        }
                        for (int r = ListSanPham.size() - 1; r >= 0; r--) {
                            if (ListSanPham.get(r).getMaSP().startsWith("DUN")) {
                                BiggestID = ListSanPham.get(r).getMaSP();
                                int valOfNewID = Integer.parseInt(BiggestID.substring(3)) + 1;
                                if (valOfNewID < 10) {
                                    NhapHang_msp_TextField.setText("DUN00" + valOfNewID);
                                } else if (valOfNewID < 100) {
                                    NhapHang_msp_TextField.setText("DUN0" + valOfNewID);
                                } else {
                                    NhapHang_msp_TextField.setText("DUN" + valOfNewID);
                                }
                                break;
                            }
                        }
                    }
                }
                case "Do gia dung" -> {
                    for (Product p : ListSanPham) {
                        // nếu đã có trong kho thì không cần tạo mới mã SP
                        if (p.getTenSP().equals(NhapHang_TenSP_TextField.getText()) && p.getMaSP().startsWith("DGD")) {
                            check = true;
                            NhapHang_msp_TextField.setText(p.getMaSP());
                            NhapHang_NhaSX_TextField.setText(p.getNhaSX());
                            NhapHang_GiaBan_TextField.setText(p.getGia() + "");
                            NhapHang_Donvi_TextField.setText(p.getDonVi());
                            NhapHang_NSX_FormatField.setText(p.getNSX());
                            NhapHang_HSD_FormatField.setText(p.getHSD());
                            break;
                        }
                    }

                    // nếu đã có trong dsach nhập hàng thì ko cần tạo mã sp mới
                    if (!check) {
                        for (int row = 0; row < NhapHang_Table.getRowCount(); row++) {
                            if (NhapHang_Table.getValueAt(row, 3).equals(NhapHang_TenSP_TextField.getText())
                                && NhapHang_Table.getValueAt(row, 2).toString().startsWith("DGD")) {
                                check = true;
                                NhapHang_msp_TextField.setText(NhapHang_Table.getValueAt(row, 2).toString());
                                NhapHang_SoLuong_Spinner.setValue(NhapHang_Table.getValueAt(row, 4).toString());
                                NhapHang_Donvi_TextField.setText(NhapHang_Table.getValueAt(row, 5).toString());
                                NhapHang_NhaSX_TextField.setText(NhapHang_Table.getValueAt(row, 6).toString());
                                NhapHang_GiaNhap_TextField.setText(NhapHang_Table.getValueAt(row, 8).toString());
                                NhapHang_GiaBan_TextField.setText(NhapHang_Table.getValueAt(row, 9).toString());
                                NhapHang_NSX_FormatField.setText(NhapHang_Table.getValueAt(row, 10).toString());
                                NhapHang_HSD_FormatField.setText(NhapHang_Table.getValueAt(row, 11).toString());
                                break;
                            }
                        }
                    }

                    // chưa có thì tạo mã sản phẩm mới và đưa vào NhapHang_Msp_TextField
                    if (!check) {
                        String BiggestID;
                        for (int row = NhapHang_Table.getRowCount() - 1; row >= 0; row--) {
                            if (NhapHang_Table.getValueAt(row, 2).toString().startsWith("DGD")) {
                                BiggestID = NhapHang_Table.getValueAt(row, 2).toString();
                                int valOfNewID = Integer.parseInt(BiggestID.substring(3)) + 1;
                                if (valOfNewID < 10) {
                                    NhapHang_msp_TextField.setText("DGD00" + valOfNewID);
                                } else if (valOfNewID < 100) {
                                    NhapHang_msp_TextField.setText("DGD0" + valOfNewID);
                                } else {
                                    NhapHang_msp_TextField.setText("DGD" + valOfNewID);
                                }
                                return;
                            }
                        }
                        for (int r = ListSanPham.size() - 1; r >= 0; r--) {
                            if (ListSanPham.get(r).getMaSP().startsWith("DGD")) {
                                BiggestID = ListSanPham.get(r).getMaSP();
                                int valOfNewID = Integer.parseInt(BiggestID.substring(3)) + 1;
                                if (valOfNewID < 10) {
                                    NhapHang_msp_TextField.setText("DGD00" + valOfNewID);
                                } else if (valOfNewID < 100) {
                                    NhapHang_msp_TextField.setText("DGD0" + valOfNewID);
                                } else {
                                    NhapHang_msp_TextField.setText("DGD" + valOfNewID);
                                }
                                break;
                            }
                        }
                    }
                }
                default -> {
                    NhapHang_msp_TextField.setText("");
                }
            }
        }
    }//GEN-LAST:event_NhapHang_Loai_CbBoxActionPerformed

    private void BanHang_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BanHang_PanelMouseClicked
        // TODO add your handling code here:
        if (!BanHang_SrollPaneTable.getBounds().contains(evt.getPoint())) {
            BanHang_Table.clearSelection();
        }
    }//GEN-LAST:event_BanHang_PanelMouseClicked

    private void BanHang_taoDon_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BanHang_taoDon_ButtonActionPerformed
        // TODO add your handling code here:
        BanHang_STT = 0;
        BanHang_HoTen_TextField.setText("");
        DefaultTableModel tModel = (DefaultTableModel) BanHang_Table.getModel();
        tModel.setRowCount(0);
        BanHang_LoaiSP_CbBox.setSelectedIndex(0);
        BanHang_Msp_TextField.setText("");
        BanHang_TenSP_ComboBox.removeAllItems();
        BanHang_Soluong_Spinner.setValue(0);
        BanHang_Donvi_TextField.setText("");
        BanHang_DonGia_TextField.setText("0");
        BanHang_ThanhTien_TextField.setText("0");
        BanHang_MaVoucher_TextField.setText("");
        BanHang_GiamGia_TextField.setText("0");
        BanHang_ThanhToan_TextField.setText("0");
        BanHang_TienThua_TextField.setText("0");
        int numBillBan;
        try {
            String Numbers_PATH = CUR_DIR + separator + "Manage Files" + separator + "Numbers.csv";
            br = new BufferedReader(new FileReader(Numbers_PATH));
            br.readLine();
            numBillBan = Integer.parseInt(br.readLine().split(",")[0]);
            if (numBillBan < 10) {
                BanHang_MaBill_TextField.setText("HDB00" + numBillBan);
            } else if (numBillBan < 100) {
                BanHang_MaBill_TextField.setText("HDB0" + numBillBan);
            } else {
                BanHang_MaBill_TextField.setText("HDB" + numBillBan);
            }

            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BanHang_taoDon_ButtonActionPerformed

    private void BanHang_ChotDon_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BanHang_ChotDon_ButtonActionPerformed
        // TODO add your handling code here:
        int rowNum = BanHang_Table.getRowCount();
        int thanhTien = 0;
        for (int i = 0; i < rowNum; i++) {
            thanhTien += (int) BanHang_Table.getValueAt(i, 6);
        }
        BanHang_ThanhTien_TextField.setText(thanhTien + "");
    }//GEN-LAST:event_BanHang_ChotDon_ButtonActionPerformed

    private void BanHang_TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BanHang_TableMouseClicked
        // TODO add your handling code here:
        int selectedRow = BanHang_Table.getSelectedRow();
        DefaultTableModel tableModel = (DefaultTableModel) BanHang_Table.getModel();
        BanHang_Msp_TextField.setText(tableModel.getValueAt(selectedRow, 1).toString());
        BanHang_TenSP_ComboBox.setSelectedItem(tableModel.getValueAt(selectedRow, 2));
        BanHang_Soluong_Spinner.setValue(Integer.valueOf(tableModel.getValueAt(selectedRow, 3).toString()));
        BanHang_Donvi_TextField.setText(tableModel.getValueAt(selectedRow, 4).toString());
    }//GEN-LAST:event_BanHang_TableMouseClicked

    private void BanHang_ThanhToan_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BanHang_ThanhToan_TextFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int thanhTien = Integer.parseInt(BanHang_ThanhTien_TextField.getText());
            int giamGia = Integer.parseInt(BanHang_GiamGia_TextField.getText());
            int thanhToan = Integer.parseInt(BanHang_ThanhToan_TextField.getText());
            BanHang_TienThua_TextField.setText((thanhToan - thanhTien - giamGia) + "");
        }
    }//GEN-LAST:event_BanHang_ThanhToan_TextFieldKeyPressed

    private void BanHang_HuyDon_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BanHang_HuyDon_ButtonActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(this, "Bạn muốn huỷ đơn hàng?");
        if (a == JOptionPane.NO_OPTION) {
            return;
        }
        BanHang_STT = 0;
        BanHang_HoTen_TextField.setText("");
        BanHang_MaBill_TextField.setText("");
        DefaultTableModel tModel = (DefaultTableModel) BanHang_Table.getModel();
        tModel.setRowCount(0);
        BanHang_LoaiSP_CbBox.setSelectedIndex(0);
        BanHang_Msp_TextField.setText("");
        BanHang_TenSP_ComboBox.removeAllItems();
        BanHang_Soluong_Spinner.setValue(0);
        BanHang_Donvi_TextField.setText("");
        BanHang_DonGia_TextField.setText("0");
        BanHang_ThanhTien_TextField.setText("0");
        BanHang_MaVoucher_TextField.setText("");
        BanHang_GiamGia_TextField.setText("0");
        BanHang_ThanhToan_TextField.setText("0");
        BanHang_TienThua_TextField.setText("0");
    }//GEN-LAST:event_BanHang_HuyDon_ButtonActionPerformed

    private void BanHang_ThanhToan_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BanHang_ThanhToan_ButtonActionPerformed
        // TODO add your handling code here:
        int thanhTien = Integer.parseInt(BanHang_ThanhTien_TextField.getText());
        int giamGia = Integer.parseInt(BanHang_GiamGia_TextField.getText());
        int thanhToan = Integer.parseInt(BanHang_ThanhToan_TextField.getText());
        int tienThua = thanhToan - giamGia - thanhTien;
        ArrayList<Product> sanPhamBan = new ArrayList<>(); // để cho vào Bill_banHang
        BanHang_TienThua_TextField.setText(tienThua + "");
        if (tienThua < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa trả đủ tiền", "", JOptionPane.ERROR_MESSAGE);
        } else if (BanHang_HoTen_TextField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa có tên khách hàng", "", JOptionPane.ERROR_MESSAGE);
        } else {
            // giảm số lượng sản phẩm trong kho
            for (int row = 0; row < BanHang_Table.getRowCount(); row++) {
                int i = 0;
                while (i < ListSanPham.size()) {
                    if (ListSanPham.get(i).getMaSP().equals(BanHang_Table.getValueAt(row, 1))) {

                        // giam so luong trong kho
                        int soLuongTrc = ListSanPham.get(i).getSoLuong();
                        int soLuongSau = soLuongTrc - Integer.parseInt(BanHang_Table.getValueAt(row, 3).toString());
                        ListSanPham.get(i).setSoLuong(soLuongSau);
                    }
                    i++;
                }
            }

            //cập nhật vào kho_Table
            DefaultTableModel KhoModel = (DefaultTableModel) Kho_Table.getModel();
            KhoModel.setRowCount(0);
            Kho_STT = 0;
            for (Product sp : ListSanPham) {
                KhoModel.addRow(new Object[]{
                    ++Kho_STT,
                    sp.getMaSP(),
                    (sp.getMaSP().startsWith("DAN") ? "Do an" : (sp.getMaSP().startsWith("DUN") ? "Do uong" : "Do gia dung")),
                    sp.getTenSP(),
                    sp.getSoLuong(),
                    sp.getGia(),
                    sp.getDonVi(),
                    sp.getNhaSX(),
                    sp.getNSX(),
                    sp.getHSD()
                });
            }

            // lưu sản phẩm vào list sanPhamBan
            for (int row = 0; row < BanHang_Table.getRowCount(); row++) {
                Product p;
                for (Product sp : ListSanPham) {
                    if (sp.getMaSP().equals(BanHang_Table.getValueAt(row, 1))) {
                        p = new Product();
                        p.copyData(sp);
                        p.setSoLuong(Integer.parseInt(BanHang_Table.getValueAt(row, 3).toString()));
                        sanPhamBan.add(p);
                    }
                }
            }

            // cập nhật số lượng sản phẩm từ listSanPham vào file
            try {
                bw = new BufferedWriter(new FileWriter(SanPham_PATH));
                bw.write("Ma SP,Ten SP,Nha SX,So luong,Don vi,Gia ban,NSX,HSD\n");
                for (Product sp : ListSanPham) {
                    bw.append(String.format("%s,%s,%s,%d,%s,%d,%s,%s\n", sp.getMaSP(), sp.getTenSP(),
                        sp.getNhaSX(), sp.getSoLuong(), sp.getDonVi(), sp.getGia(),
                        sp.getNSX(),
                        sp.getHSD()));
            }
            bw.flush();
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(this, "Thanh toán thành công");

        // tạo bill bán hàng
        Bill_banHang billBan;
        billBan = new Bill_banHang(BanHang_HoTen_TextField.getText());
        billBan.setMaHoaDon(BanHang_MaBill_TextField.getText());
        billBan.setDsachSP(sanPhamBan);
        billBan.setNgayTaoBill(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        billBan.setThanhTien(Long.parseLong(BanHang_ThanhTien_TextField.getText()));
        billBan.setThanhToan(Long.parseLong(BanHang_ThanhToan_TextField.getText()));
        DonBan_ListBill.add(billBan); // thêm vào dsach hoá đơn bán

        // cập nhật vào đơn_hàng -> đơn bán -> DonBan_Table
        DefaultTableModel donBanModel = (DefaultTableModel) DonBan_Table.getModel();
        donBanModel.addRow(new Object[]{
            billBan.getTenKhach(),
            billBan.getMaHoaDon(),
            billBan.getNgayTaoBill(),
            billBan.getThanhTien(),
            billBan.getThanhToan()
        });

        // Lưu hoá đơn vào file hoaDonBan.json
        FileWriter fw = null;
        try {
            fw = new FileWriter(hoaDonBan_PATH);
            Gson gson = new Gson();
            gson.toJson(DonBan_ListBill, fw);
        } catch (IOException ex) {
            Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.flush();
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //cập nhật số đơn bán vào Numbers.csv
        String Numbers_PATH = CUR_DIR + separator + "Manage Files" + separator + "Numbers.csv";
        String[] soDon = null;
        try {
            br = new BufferedReader(new FileReader(Numbers_PATH));
            br.readLine();
            soDon = br.readLine().split(",");
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            bw = new BufferedWriter(new FileWriter(Numbers_PATH));
            bw.write("soDonBan,soDonNhap\n");
            bw.append((Integer.parseInt(soDon[0]) + 1) + "," + soDon[1]);
            bw.flush();
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(MainPage1.class.getName()).log(Level.SEVERE, null, ex);
        }

        // xong thì xoá hết dữ liệu
        BanHang_HoTen_TextField.setText("");
        BanHang_MaBill_TextField.setText("");
        DefaultTableModel tModel = (DefaultTableModel) BanHang_Table.getModel();
        tModel.setRowCount(0);
        BanHang_LoaiSP_CbBox.setSelectedIndex(0);
        BanHang_Msp_TextField.setText("");
        BanHang_TenSP_ComboBox.removeAllItems();
        BanHang_Soluong_Spinner.setValue(0);
        BanHang_Donvi_TextField.setText("");
        BanHang_DonGia_TextField.setText("0");
        BanHang_ThanhTien_TextField.setText("0");
        BanHang_MaVoucher_TextField.setText("");
        BanHang_GiamGia_TextField.setText("0");
        BanHang_ThanhToan_TextField.setText("0");
        BanHang_TienThua_TextField.setText("0");
        }
    }//GEN-LAST:event_BanHang_ThanhToan_ButtonActionPerformed

    private void BanHang_LoaiSP_CbBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BanHang_LoaiSP_CbBoxActionPerformed
        // TODO add your handling code here:
        JComboBox<String> combo = (JComboBox<String>) evt.getSource();
        BanHang_TenSP_ComboBox.removeAllItems();
        BanHang_TenSP_ComboBox.addItem("");
        String selectedtItem = combo.getSelectedItem().toString();
        switch (selectedtItem) {
            case "Do an" -> {
                for (Product p : ListSanPham) {
                    if (p.getMaSP().startsWith("DAN")) {
                        BanHang_TenSP_ComboBox.addItem(p.getTenSP());
                    }
                }
            }
            case "Do uong" -> {
                for (Product p : ListSanPham) {
                    if (p.getMaSP().startsWith("DUN")) {
                        BanHang_TenSP_ComboBox.addItem(p.getTenSP());
                    }
                }
            }
            case "Do gia dung" -> {
                for (Product p : ListSanPham) {
                    if (p.getMaSP().startsWith("DGD")) {
                        BanHang_TenSP_ComboBox.addItem(p.getTenSP());
                    }
                }
            }
        }
    }//GEN-LAST:event_BanHang_LoaiSP_CbBoxActionPerformed

    private void BanHang_TenSP_ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BanHang_TenSP_ComboBoxActionPerformed
        // TODO add your handling code here:
        JComboBox<String> combo = (JComboBox<String>) evt.getSource();
        String tensp = (String) combo.getSelectedItem();
        for (Product sp : ListSanPham) {
            if (sp.getTenSP().equals(tensp)) {
                if (sp.getSoLuong() == 0) {
                    JOptionPane.showMessageDialog(this, "Sản phẩm này đã hết hàng");
                } else {
                    BanHang_Msp_TextField.setText(sp.getMaSP());
                    BanHang_Donvi_TextField.setText(sp.getDonVi());
                    BanHang_Soluong_Spinner.setModel(new SpinnerNumberModel(0, 0, sp.getSoLuong(), 1));
                    BanHang_DonGia_TextField.setText(sp.getGia() + "");
                }
                break;
            }
        }
    }//GEN-LAST:event_BanHang_TenSP_ComboBoxActionPerformed

    private void BanHang_HuySP_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BanHang_HuySP_ButtonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tModel = (DefaultTableModel) BanHang_Table.getModel();
        if (tModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Bảng đang trống");
        } else if (BanHang_Table.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Chưa có sản phẩm nào được chọn !!!");
        } else {
            int choice = JOptionPane.showConfirmDialog(this, "Bạn muốn huỷ sản phẩm này?");
            if (choice == JOptionPane.YES_OPTION) {
                int sltedRow = BanHang_Table.getSelectedRow();
                tModel.removeRow(sltedRow);
                BanHang_STT = tModel.getRowCount();
                for (int i = sltedRow; i < tModel.getRowCount(); i++) {
                    BanHang_Table.setValueAt((Integer) BanHang_Table.getValueAt(i, 0) - 1, i, 0);
                }
            }
        }
    }//GEN-LAST:event_BanHang_HuySP_ButtonActionPerformed

    private void BanHang_SuaSP_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BanHang_SuaSP_ButtonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tableModel = (DefaultTableModel) BanHang_Table.getModel();
        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Bảng đang trống");
        } else if (BanHang_Table.getSelectedRowCount() > 1) {
            JOptionPane.showMessageDialog(this, "Chỉ được chọn MỘT dòng");
        } else {
            int a = JOptionPane.showConfirmDialog(this, "Bạn muốn lưu thay đổi không?");
            if (a == JOptionPane.NO_OPTION) {
                return;
            }
            int selectedRow = BanHang_Table.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Chưa có dòng nào được chọn!!!");
                return;
            }
            tableModel.setValueAt(BanHang_Msp_TextField.getText(), selectedRow, 1);
            tableModel.setValueAt(BanHang_TenSP_ComboBox.getSelectedItem(), selectedRow, 2);
            tableModel.setValueAt(BanHang_Soluong_Spinner.getValue().toString(), selectedRow, 3);
            tableModel.setValueAt(BanHang_Donvi_TextField.getText(), selectedRow, 4);
            for (Product sp : ListSanPham) {
                if (sp.getMaSP().equals(BanHang_Msp_TextField.getText())) {
                    tableModel.setValueAt(sp.getGia() + "", selectedRow, 5);
                    tableModel.setValueAt(Integer.parseInt(BanHang_Soluong_Spinner.getValue().toString()) * sp.getGia(), selectedRow, 6);
                    break;
                }
            }
        }
    }//GEN-LAST:event_BanHang_SuaSP_ButtonActionPerformed

    private void BanHang_ThemSP_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BanHang_ThemSP_ButtonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tableModel = (DefaultTableModel) BanHang_Table.getModel();
        // nếu số lượng sản phẩm = 0 thì không thêm vào table
        if (Integer.parseInt(BanHang_Soluong_Spinner.getValue().toString()) == 0) {
            JOptionPane.showMessageDialog(this, "Không thêm vào được do số lượng = 0");
        } else {
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                if (BanHang_Msp_TextField.getText().equals(tableModel.getValueAt(row, 1).toString())) {
                    if (JOptionPane.showConfirmDialog(this, "Bạn muốn thêm " + BanHang_Soluong_Spinner.getValue().toString() + " sản phẩm?") == JOptionPane.YES_OPTION) {
                        tableModel.setValueAt(Integer.parseInt(BanHang_Soluong_Spinner.getValue().toString())
                            + Integer.parseInt(tableModel.getValueAt(row, 3).toString()), row, 3);
                        tableModel.setValueAt(Integer.parseInt(tableModel.getValueAt(row, 3).toString())
                            * Integer.parseInt(tableModel.getValueAt(row, 5).toString()), row, 6);
                        return;
                    } else {
                        return;
                    }
                }
            }
            tableModel.addRow(new Object[]{
                ++BanHang_STT,
                BanHang_Msp_TextField.getText(),
                BanHang_TenSP_ComboBox.getSelectedItem().toString(),
                BanHang_Soluong_Spinner.getValue(),
                BanHang_Donvi_TextField.getText(),
                BanHang_DonGia_TextField.getText(),
                (Integer) BanHang_Soluong_Spinner.getValue() * Integer.valueOf(BanHang_DonGia_TextField.getText())
            });
        }
    }//GEN-LAST:event_BanHang_ThemSP_ButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JTabbedPane.setSelectedIndex(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        JTabbedPane.setSelectedIndex(1);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        JTabbedPane.setSelectedIndex(2);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        JTabbedPane.setSelectedIndex(3);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        JTabbedPane.setSelectedIndex(4);
    }//GEN-LAST:event_jButton5ActionPerformed

<<<<<<< HEAD
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        JTabbedPane.setSelectedIndex(5);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void monthValue_TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthValue_TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_monthValue_TextFieldActionPerformed

    private void DayValue_TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DayValue_TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DayValue_TextFieldActionPerformed
=======
    private void QLTK_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QLTK_ButtonActionPerformed
        // TODO add your handling code here:
        JTabbedPane.setSelectedIndex(5);
    }//GEN-LAST:event_QLTK_ButtonActionPerformed

    private void DangXuat_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DangXuat_ButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new DangNhap().setVisible(true);
    }//GEN-LAST:event_DangXuat_ButtonActionPerformed
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370

    private void Kho_FuncTimKiem() {
        ArrayList<Product> demoList; // lưu sản phẩm được lọc
        DefaultTableModel khoModel = (DefaultTableModel) Kho_Table.getModel();
        switch (Kho_TimKiem_CbBox.getSelectedIndex()) {
            // top 10 sp cùng Nhà SX
            case 1 -> {
                demoList = new ArrayList<>();
                for (int i = 0; i < khoModel.getRowCount() && demoList.size() < 10; i++) {
                    if (khoModel.getValueAt(i, 7).toString().equals(Kho_TimKiem_TextField.getText())) {
                        demoList.add(new Product(khoModel.getValueAt(i, 2).toString(),
                                khoModel.getValueAt(i, 3).toString(),
                                khoModel.getValueAt(i, 7).toString(),
                                khoModel.getValueAt(i, 6).toString(),
                                Integer.parseInt(khoModel.getValueAt(i, 4).toString()),
                                Integer.parseInt(khoModel.getValueAt(i, 5).toString()),
                                khoModel.getValueAt(i, 8).toString(),
                                khoModel.getValueAt(i, 9).toString()));
                        System.out.println(demoList.get(demoList.size() - 1).getNhaSX());
                    }
                }
                if (demoList.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy Nhà SX " + "\"" + Kho_TimKiem_TextField.getText() + "\"");
                    return;
                }
                demoList.sort(Comparator.comparing(Product::getTenSP));
                khoModel.setRowCount(0);
                Kho_STT = 0;
                for (Product Kho_hangHoa : demoList) {
                    khoModel.addRow(new Object[]{
                        ++Kho_STT,
                        (Kho_hangHoa.getMaSP().startsWith("DAN") ? "Do an" : (Kho_hangHoa.getMaSP().startsWith("DUN") ? "Do uong" : "Do gia dung")),
                        Kho_hangHoa.getMaSP(),
                        Kho_hangHoa.getTenSP(),
                        Kho_hangHoa.getSoLuong(),
                        Kho_hangHoa.getGia(),
                        Kho_hangHoa.getDonVi(),
                        Kho_hangHoa.getNhaSX(),
                        Kho_hangHoa.getNSX(),
                        Kho_hangHoa.getHSD()
                    });
                }
            }

            // top 10 sp có số lượng nhỏ nhất
            case 2 -> {
                demoList = new ArrayList<>();
                Kho_STT = 0;
                if (ListSanPham.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Bảng không có dữ liệu");
                    return;
                }
                khoModel.setRowCount(0);
                ListSanPham.sort(Comparator.comparing(Product::getSoLuong));
                for (int i = 0; i < 10 && i < ListSanPham.size(); i++) {
                    demoList.add(new Product().copyData(ListSanPham.get(i)));
                }
                demoList.sort(Comparator.comparing(Product::getTenSP));
                Data2KhoTable(khoModel, demoList);
                
                ListSanPham.sort(Comparator.comparing(Product::getTenSP));
            }

            // top 10 sp có số lượng lớn nhất
            case 3 -> {
                if (ListSanPham.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Bảng không có dữ liệu");
                    return;
                }
                demoList = new ArrayList<>();
                khoModel.setRowCount(0);
                Kho_STT = 0;
                ListSanPham.sort(Comparator.comparing(Product::getSoLuong).reversed());
                for (int i = 0; i < 10 && i < ListSanPham.size(); i++) {
                    demoList.add(new Product().copyData(ListSanPham.get(i)));
                }
                demoList.sort(Comparator.comparing(Product::getTenSP));
                Data2KhoTable(khoModel, demoList);
                ListSanPham.sort(Comparator.comparing(Product::getTenSP));
            }

            // top 10 sp NSX gần hiện tại nhất
            case 4 -> {
                demoList = new ArrayList<>();
                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date, dateNow = LocalDate.now();
                Period MinDiff, diff;
                int index;
                
                for (int i=0; i<ListSanPham.size()-1; i++){
                    String nsx;
                    index = i;
                    nsx = ListSanPham.get(i).getNSX();
                    date = LocalDate.parse(nsx, df);
                    MinDiff = Period.between(date, dateNow);
                    for (int j=i+1; j<ListSanPham.size(); j++){
                        nsx = ListSanPham.get(j).getNSX();
                        date = LocalDate.parse(nsx, df);
                        diff = Period.between(date, dateNow);
                        if (diff.getYears()<MinDiff.getYears()){
                            MinDiff = diff;
                            index = j;
                        } else if (diff.getYears() == MinDiff.getYears()){
                            if (diff.getMonths() < MinDiff.getMonths()){
                                MinDiff = diff;
                                index = j;
                            } else if (diff.getMonths() == MinDiff.getMonths()){
                                if (diff.getDays() < MinDiff.getDays()){
                                    MinDiff = diff;
                                    index = j;
                                }
                            }
                        } 
                    }
                    if (index != i){
                        ListSanPham.add(i, ListSanPham.get(index));
                        ListSanPham.remove(index+1);
                    }
                }
                // đưa dữ liệu đã lọc vào demoList
                for (int i=0; i<10 && i<ListSanPham.size(); i++){
                    demoList.add(new Product().copyData(ListSanPham.get(i)));
                }
                // đưa dữ liệu vào bảng
                khoModel.setRowCount(0);
                demoList.sort(Comparator.comparing(Product::getTenSP));
                Data2KhoTable(khoModel, demoList);
                ListSanPham.sort(Comparator.comparing(Product::getTenSP));
            }

            // top 10 sp đã hết HSD
            case 5 -> {
                demoList = new ArrayList<>();
                for (Product p: ListSanPham){
                    LocalDate dateHSD = LocalDate.parse(p.getHSD(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    if (LocalDate.now().isAfter(dateHSD) && demoList.size()<10){
                        demoList.add(new Product().copyData(p));
                    }
                }
                demoList.sort(Comparator.comparing(Product::getTenSP));
                khoModel.setRowCount(0);
                Data2KhoTable(khoModel, demoList);
            }
        }
    }

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
            java.util.logging.Logger.getLogger(MainPage1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new RunnableImpl());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BanHang_ChotDon_Button;
    private javax.swing.JLabel BanHang_DonGia_Label;
    private javax.swing.JTextField BanHang_DonGia_TextField;
    private javax.swing.JLabel BanHang_Donvi_Label;
    private javax.swing.JTextField BanHang_Donvi_TextField;
    private javax.swing.JTextField BanHang_GiamGia_TextField;
    private javax.swing.JLabel BanHang_HoTen_Label;
    private javax.swing.JTextField BanHang_HoTen_TextField;
    private javax.swing.JButton BanHang_HuyDon_Button;
    private javax.swing.JButton BanHang_HuySP_Button;
    private javax.swing.JComboBox<String> BanHang_LoaiSP_CbBox;
    private javax.swing.JLabel BanHang_MaBill_Label;
    private javax.swing.JTextField BanHang_MaBill_TextField;
    private javax.swing.JTextField BanHang_MaVoucher_TextField;
    private javax.swing.JTextField BanHang_Msp_TextField;
    private javax.swing.JPanel BanHang_Panel;
    private javax.swing.JScrollPane BanHang_ScrollPane;
    private javax.swing.JLabel BanHang_SoLuong_Label;
    private javax.swing.JSpinner BanHang_Soluong_Spinner;
    private javax.swing.JScrollPane BanHang_SrollPaneTable;
    private javax.swing.JButton BanHang_SuaSP_Button;
    private javax.swing.JTable BanHang_Table;
    private javax.swing.JComboBox<String> BanHang_TenSP_ComboBox;
    private javax.swing.JLabel BanHang_TenSP_Label;
    private javax.swing.JTextField BanHang_ThanhTien_TextField;
    private javax.swing.JButton BanHang_ThanhToan_Button;
    private javax.swing.JTextField BanHang_ThanhToan_TextField;
    private javax.swing.JButton BanHang_ThemSP_Button;
    private javax.swing.JTextField BanHang_TienThua_TextField;
    private javax.swing.JLabel BanHang_maSP_Label;
    private javax.swing.JButton BanHang_taoDon_Button;
    private javax.swing.JLabel Clock_Label;
<<<<<<< HEAD
=======
    private javax.swing.JButton DangXuat_Button;
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
    private javax.swing.JTextField DayValue_TextField;
    private javax.swing.JTable DonBan_DSSP_Table;
    private javax.swing.JPanel DonBan_Panel;
    private javax.swing.JTable DonBan_Table;
    private javax.swing.JTabbedPane DonHang_Panel;
    private javax.swing.JScrollPane DonHang_ScrollPane;
    private javax.swing.JTable DonNhap_DSSP_Table;
    private javax.swing.JPanel DonNhap_Panel;
    private javax.swing.JTable DonNhap_Table;
    private javax.swing.JPanel Home_Panel;
    private javax.swing.JScrollPane Home_ScrollPane;
    private javax.swing.JTabbedPane JTabbedPane;
    private javax.swing.JTextField Kho_Donvi_TextField;
    private javax.swing.JTextField Kho_GiaBan_TextField;
    private javax.swing.JLabel Kho_HSD_Label;
    private javax.swing.JTextField Kho_HSD_TextField;
    private javax.swing.JComboBox<String> Kho_Loai_CbBox;
    private javax.swing.JButton Kho_LuuDS_Button;
    private javax.swing.JLabel Kho_NSX_Label;
    private javax.swing.JTextField Kho_NSX_TextField;
    private javax.swing.JLabel Kho_NhaSX_Label;
    private javax.swing.JTextField Kho_NhaSX_TextField;
    private javax.swing.JPanel Kho_Panel;
    private javax.swing.JScrollPane Kho_ScrollPane;
    private javax.swing.JScrollPane Kho_ScrollPaneTable;
    private javax.swing.JLabel Kho_SoLuong_Label;
    private javax.swing.JSpinner Kho_SoLuong_Spinner;
    private javax.swing.JButton Kho_SuaSP_Button;
    private javax.swing.JTable Kho_Table;
    private javax.swing.JLabel Kho_TenSP_Label;
    private javax.swing.JTextField Kho_TenSP_TextField;
    private javax.swing.JButton Kho_TimKiem_Button;
    private javax.swing.JComboBox<String> Kho_TimKiem_CbBox;
    private javax.swing.JTextField Kho_TimKiem_TextField;
    private javax.swing.JButton Kho_XoaDL_Button;
    private javax.swing.JButton Kho_XoaSP_Button;
    private javax.swing.JLabel Kho_msp_Label;
    private javax.swing.JTextField Kho_msp_TextField;
    private javax.swing.JButton NhapHang_Clear_Button;
    private javax.swing.JLabel NhapHang_Donvi_Label;
    private javax.swing.JTextField NhapHang_Donvi_TextField;
    private javax.swing.JLabel NhapHang_GiaBan_Label;
    private javax.swing.JTextField NhapHang_GiaBan_TextField;
    private javax.swing.JLabel NhapHang_GiaNhap_Label;
    private javax.swing.JTextField NhapHang_GiaNhap_TextField;
    private javax.swing.JFormattedTextField NhapHang_HSD_FormatField;
    private javax.swing.JLabel NhapHang_HSD_Label;
    private javax.swing.JComboBox<String> NhapHang_Loai_CbBox;
    private javax.swing.JLabel NhapHang_Loai_Label;
    private javax.swing.JFormattedTextField NhapHang_NSX_FormatField;
    private javax.swing.JLabel NhapHang_NSX_Label;
    private javax.swing.JLabel NhapHang_NhaSX_Label;
    private javax.swing.JTextField NhapHang_NhaSX_TextField;
    private javax.swing.JPanel NhapHang_Panel;
    private javax.swing.JLabel NhapHang_PhanPhoi_Label;
    private javax.swing.JTextField NhapHang_PhanPhoi_TextField;
    private javax.swing.JScrollPane NhapHang_ScollPane;
    private javax.swing.JScrollPane NhapHang_ScrollPaneTable;
    private javax.swing.JLabel NhapHang_SoLuong_Label;
    private javax.swing.JSpinner NhapHang_SoLuong_Spinner;
    private javax.swing.JButton NhapHang_SuaSP_Button;
    private javax.swing.JTable NhapHang_Table;
    private javax.swing.JLabel NhapHang_TenSP_Label;
    private javax.swing.JTextField NhapHang_TenSP_TextField;
    private javax.swing.JLabel NhapHang_ThanhTien_Label;
    private javax.swing.JTextField NhapHang_ThanhTien_TextField;
    private javax.swing.JButton NhapHang_ThanhToan_Button;
    private javax.swing.JButton NhapHang_ThemSP_Button;
    private javax.swing.JButton NhapHang_TimKiem_Button;
    private javax.swing.JTextField NhapHang_TimKiem_TextField;
    private javax.swing.JButton NhapHang_XoaSP_Button;
    private javax.swing.JLabel NhapHang_msp_Label;
    private javax.swing.JTextField NhapHang_msp_TextField;
<<<<<<< HEAD
    private javax.swing.JPasswordField QLTK_PassField;
    private javax.swing.JTable QLTK_Table;
=======
    private javax.swing.JTable QLTK_AdminTable;
    private javax.swing.JButton QLTK_Button;
    private javax.swing.JTable QLTK_NhanVienTable;
    private javax.swing.JPasswordField QLTK_PassField;
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
    private javax.swing.JTextField QLTK_User_TextField;
    private javax.swing.JPasswordField QLTK_VerifyPassField;
    private javax.swing.JPanel QuanLyTK_Panel;
    private javax.swing.JScrollPane QuanLyTK_ScrollPane;
    private javax.swing.JLabel RealityTimer_Label;
    private javax.swing.JPanel RealityTimer_Panel;
<<<<<<< HEAD
=======
    private javax.swing.JRadioButton ThemAdmin_RadioButton;
    private javax.swing.ButtonGroup ThemTK_buttonGroup;
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
    private javax.swing.JLabel ThemTk_Status_Label;
    private javax.swing.JLabel TonKho_Donvi_Label;
    private javax.swing.JLabel TonKho_GiaBan_Label;
    private javax.swing.JLabel TonKho_Loai_Label;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel giamGia_Label;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
<<<<<<< HEAD
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
=======
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
<<<<<<< HEAD
    private javax.swing.JLabel jLabel9;
=======
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
<<<<<<< HEAD
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
=======
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
    private javax.swing.JLabel maVoucher_Label;
    private javax.swing.JTextField monthValue_TextField;
    private javax.swing.JLabel nam_Label;
    private javax.swing.JLabel ngay_Label;
    private javax.swing.JPanel sanPham_Panel;
<<<<<<< HEAD
    private java.awt.TextField textField1;
    private javax.swing.JLabel thang_Label;
    private javax.swing.JLabel thanhTien_Label;
    private javax.swing.JLabel thanhToan_Label;
=======
    private javax.swing.JLabel thang_Label;
    private javax.swing.JLabel thanhTien_Label;
    private javax.swing.JLabel thanhToan_Label;
    private javax.swing.JRadioButton themNV_RadioButton;
>>>>>>> 5fe60cc73a453574c93911a25776c6160a542370
    private javax.swing.JButton themTk_Button;
    private javax.swing.JLabel themTk_Pass_Label;
    private javax.swing.JLabel themTk_User_Label;
    private javax.swing.JLabel themTk_Verify_Label;
    private javax.swing.JLabel tienThua_Label;
    private javax.swing.JLabel vnd_Label1;
    private javax.swing.JLabel vnd_Label2;
    private javax.swing.JLabel vnd_Label3;
    private javax.swing.JLabel vnd_Label4;
    private javax.swing.JLabel vnd_Label5;
    private javax.swing.JTextField yearValue_TextField;
    // End of variables declaration//GEN-END:variables

    private static class RunnableImpl implements Runnable {

        public RunnableImpl() {
        }

        @Override
        public void run() {
            MainPage1 homePage = new MainPage1();
            homePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //homePage.setSize(1536, 960);
            homePage.setExtendedState(JFrame.MAXIMIZED_BOTH);
            homePage.setLocationRelativeTo(null);
            homePage.setVisible(true);
        }
    }
}
