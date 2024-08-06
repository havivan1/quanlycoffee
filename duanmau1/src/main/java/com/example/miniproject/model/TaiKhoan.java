package com.example.miniproject.model;

public class TaiKhoan {
    private int maTaiKhoan;
    private String tenNguoiDung;
    private String matKhau;
    private String hoTen;
    private String loaiTaiKhoan;
    private String email;
    private String soDienThoai;
    private String diaChi;

    public TaiKhoan(int maTaiKhoan, String tenNguoiDung, String matKhau, String hoTen, String loaiTaiKhoan, String email, String soDienThoai, String diaChi) {
        this.maTaiKhoan = maTaiKhoan;
        this.tenNguoiDung = tenNguoiDung;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.loaiTaiKhoan = loaiTaiKhoan;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
    }

    public TaiKhoan(String tenNguoiDung, String matKhau, String hoTen, String loaiTaiKhoan, String email, String soDienThoai, String diaChi) {
        this.tenNguoiDung = tenNguoiDung;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.loaiTaiKhoan = loaiTaiKhoan;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
    }

    public TaiKhoan() {
    }

    public int getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(int maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getLoaiTaiKhoan() {
        return loaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(String loaiTaiKhoan) {
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
