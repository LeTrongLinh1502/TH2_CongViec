package com.example.th2_congviec.model;

import java.io.Serializable;

public class CongViec implements Serializable {
    private int id;
    private String ten,noidung,ngayHT,tinhtrang,congtac;

    public CongViec() {
    }

    public CongViec(int id, String ten, String noidung, String ngayHT, String tinhtrang, String congtac) {
        this.id = id;
        this.ten = ten;
        this.noidung = noidung;
        this.ngayHT = ngayHT;
        this.tinhtrang = tinhtrang;
        this.congtac = congtac;
    }

    public CongViec(String ten, String noidung, String ngayHT, String tinhtrang, String congtac) {
        this.ten = ten;
        this.noidung = noidung;
        this.ngayHT = ngayHT;
        this.tinhtrang = tinhtrang;
        this.congtac = congtac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getNgayHT() {
        return ngayHT;
    }

    public void setNgayHT(String ngayHT) {
        this.ngayHT = ngayHT;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public String getCongtac() {
        return congtac;
    }

    public void setCongtac(String congtac) {
        this.congtac = congtac;
    }
}
