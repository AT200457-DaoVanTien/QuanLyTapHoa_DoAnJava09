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
public class Bill_banHang extends Bill{
    String tenKhach;

    public Bill_banHang() {
    }

    public long getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(long thanhToan) {
        this.thanhToan = thanhToan;
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
