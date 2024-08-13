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
public abstract class Bill {
    public String maHoaDon;
    public String ngayTaoBill;
    public ArrayList<Product> dsachSP;
    public long thanhTien;
    public long thanhToan;
    
    public abstract void XuatBill();
}
