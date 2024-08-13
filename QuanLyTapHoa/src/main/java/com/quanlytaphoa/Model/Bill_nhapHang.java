/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlytaphoa.Model;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Bill_nhapHang extends Bill{

    String NhaPhanPhoi;

    public Bill_nhapHang() {
    }

    public Bill_nhapHang(String NhaPhanPhoi) {
        this.NhaPhanPhoi = NhaPhanPhoi;
    }

    public long getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(long thanhToan) {
        this.thanhToan = thanhToan;
    }
    
    public String getNhaPhanPhoi() {
        return NhaPhanPhoi;
    }

    public void setNhaPhanPhoi(String NhaPhanPhoi) {
        this.NhaPhanPhoi = NhaPhanPhoi;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getNgayTaoBill() {
        return ngayTaoBill;
    }

    public void setNgayTaoBill(String ngayTaoBill) {
        this.ngayTaoBill = ngayTaoBill;
    }

    public ArrayList<Product> getDsachSP() {
        return dsachSP;
    }

    public void setDsachSP(ArrayList<Product> dsachSP) {
        this.dsachSP = dsachSP;
    }

    public long getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }
    
    
    
    @Override
    public void XuatBill() {
        
    }
    
}
