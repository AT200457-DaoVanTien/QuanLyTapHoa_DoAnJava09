/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlytaphoa.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author DELL
 */
public class Product {

    private String maSP, nhaSX;
    private String tenSP;
    private int soLuong;
    private String donVi;
    private int gia;
    public String NSX, HSD;

    public Product() {
    }

    public Product(String maSP, String tenSP, String nhaSX, String donVi, int soLuong, int gia, String NSX, String HSD) {
        this.maSP = maSP;
        this.nhaSX = nhaSX;
        this.tenSP = tenSP;
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public String getDonVi() {
        return this.donVi;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getNSX() {
        return NSX;
    }

    public void setNSX(String NSX) {
        this.NSX = NSX;
    }

    public String getHSD() {
        return HSD;
    }

    public void setHSD(String HSD) {
        this.HSD = HSD;
    }

    // true: đã hết hạn - false: chưa hết hạn
    public boolean kTraHetHan() {
        return LocalDate.now().isAfter(LocalDate.parse(HSD, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    public Product copyData(Product sp) {
        this.setMaSP(sp.getMaSP());
        this.setTenSP(sp.getTenSP());
        this.setNhaSX(sp.getNhaSX());
        this.setSoLuong(sp.getSoLuong());
        this.setDonVi(sp.getDonVi());
        this.setGia(sp.getGia());
        this.setNSX(sp.getNSX());
        this.setHSD(sp.getHSD());
        return this;
    }

    @Override
    public String toString() {
        return "Product{" + "maSP=" + maSP + ", nhaSX=" + nhaSX + ", tenSP=" + tenSP + ", soLuong=" + soLuong + ", donVi=" + donVi + ", gia=" + gia + ", NSX=" + NSX + ", HSD=" + HSD + '}';
    }

}
