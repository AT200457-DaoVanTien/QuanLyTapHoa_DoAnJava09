/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlytaphoa.Model;

import java.time.LocalDateTime;
import java.util.LinkedList;

/**
 *
 * @author DELL
 */
public class Customer {
    private String ID_KH, name;
    private LocalDateTime ngayMua;
    private LinkedList<Product> dSachSP;
    private Voucher giamGia;
    
    public Customer() {
    }

    public Customer(String ID_KH, String name, LocalDateTime ngayMua, LinkedList<Product> dSachSP, Voucher giamGia) {
        this.ID_KH = ID_KH;
        this.name = name;
        this.ngayMua = ngayMua;
        this.dSachSP = dSachSP;
        this.giamGia = giamGia;
    }

    public String getID_KH() {
        return ID_KH;
    }

    public void setID_KH(String ID_KH) {
        this.ID_KH = ID_KH;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(LocalDateTime ngayMua) {
        this.ngayMua = ngayMua;
    }

    public LinkedList<Product> getdSachSP() {
        return dSachSP;
    }

    public void setdSachSP(LinkedList<Product> dSachSP) {
        this.dSachSP = dSachSP;
    }

    public Voucher getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(Voucher giamGia) {
        this.giamGia = giamGia;
    }
    
    
}
