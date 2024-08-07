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
public abstract class Bill {
    public String maHoaDon;
    public LocalDateTime ngayTaoBill;
    public ArrayList<Product> dsachSP;
    public int donGia;
    public long thanhTien;
    
    public abstract void XuatBill();
}
