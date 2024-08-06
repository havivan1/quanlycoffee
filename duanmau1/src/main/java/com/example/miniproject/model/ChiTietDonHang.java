package com.example.miniproject.model;

public class ChiTietDonHang {
    private int maDonChiTiet;
    private int maDon;
    private int maSanPham;
    private int soLuongSanPham;
    private int giaSanPham;

    // Constructor
    public ChiTietDonHang(int maDonChiTiet, int maDon, int maSanPham, int soLuongSanPham, int giaSanPham) {
        this.maDonChiTiet = maDonChiTiet;
        this.maDon = maDon;
        this.maSanPham = maSanPham;
        this.soLuongSanPham = soLuongSanPham;
        this.giaSanPham = giaSanPham;
    }

    public ChiTietDonHang() {

    }

    // Getters and Setters
    public int getMaDonChiTiet() {
        return maDonChiTiet;
    }

    public void setMaDonChiTiet(int maDonChiTiet) {
        this.maDonChiTiet = maDonChiTiet;
    }

    public int getMaDon() {
        return maDon;
    }

    public void setMaDon(int maDon) {
        this.maDon = maDon;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuongSanPham() {
        return soLuongSanPham;
    }

    public void setSoLuongSanPham(int soLuongSanPham) {
        this.soLuongSanPham = soLuongSanPham;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }
}
