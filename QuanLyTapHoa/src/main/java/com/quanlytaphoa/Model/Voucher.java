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
public class Voucher {
    public String VoucherID;
    private short phanTram;
    private int giaTriToiDa;
    public TypeOfProd loaiSP;
    public LocalDate HSD;

    @Override
    public String toString() {
        return ("Voucher ap dung cho loai san pham "+loaiSP+", giam "+phanTram+"% toi da "+giaTriToiDa);
    }
}
