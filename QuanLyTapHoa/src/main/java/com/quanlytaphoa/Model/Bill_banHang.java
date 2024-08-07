/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlytaphoa.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Bill_banHang extends Bill{
    String tenKhach;

    public Bill_banHang() {
    }

    public Bill_banHang(String tenKhach) {
        this.tenKhach = tenKhach;
    }

    public String getTenKhach() {
        return tenKhach;
    }

    public void setTenKhach(String tenKhach) {
        this.tenKhach = tenKhach;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public LocalDateTime getNgayTaoBill() {
        return ngayTaoBill;
    }

    public void setNgayTaoBill(LocalDateTime ngayTaoBill) {
        this.ngayTaoBill = ngayTaoBill;
    }

    public ArrayList<Product> getDsachSP() {
        return dsachSP;
    }

    public void setDsachSP(ArrayList<Product> dsachSP) {
        this.dsachSP = dsachSP;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
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
