/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlytaphoa.Model;

import java.time.LocalDate;

/**
 *
 * @author DELL
 */
public class Product {
    private String maSP, nhaSX;
    private String tenSP;
    private TypeOfProd loaiSP;
    private int soLuong;
    private String donVi;
    private int gia;
    public LocalDate NSX, HSD;

    public Product() {}

    public Product(String maSP, String tenSP, String nhaSX, TypeOfProd loaiSP, String donVi, int soLuong, int gia, LocalDate NSX, LocalDate HSD) {
        this.maSP = maSP;
        this.nhaSX = nhaSX;
        this.tenSP = tenSP;
        this.loaiSP = loaiSP;
        this.soLuong = soLuong;
        this.donVi = donVi;
        this.gia = gia;
        this.NSX = NSX;
        this.HSD = HSD;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getNhaSX() {
        return nhaSX;
    }

    public void setNhaSX(String nhaSX) {
        this.nhaSX = nhaSX;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public TypeOfProd getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(TypeOfProd loaiSP) {
        this.loaiSP = loaiSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    public void setDonVi(String donVi){
        this.donVi = donVi;
    }
    
    public String getDonVi(){
        return this.donVi;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public LocalDate getNSX() {
        return NSX;
    }

    public void setNSX(LocalDate NSX) {
        this.NSX = NSX;
    }

    public LocalDate getHSD() {
        return HSD;
    }

    public void setHSD(LocalDate HSD) {
        this.HSD = HSD;
    }
    
    // true: đã hết hạn - false: chưa hết hạn
    public boolean kTraHetHan()
    {
        return LocalDate.now().isAfter(HSD);
    }
    
}
