package com.example.miniproject.model;

public class DonHang {
    private int maDonHang;
    private int maKhachHang;
    private String ngayDat;
    private int tongTien;

    // Constructor
    public DonHang(int maDonHang, int maKhachHang, String ngayDat, int tongTien) {
        this.maDonHang = maDonHang;
        this.maKhachHang = maKhachHang;
        this.ngayDat = ngayDat;
        this.tongTien = tongTien;
    }

    public DonHang() {

    }

    // Getters and Setters
    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}
