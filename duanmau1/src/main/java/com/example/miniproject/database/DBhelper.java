package com.example.miniproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(@Nullable Context context) {
        super(context, "mini_project01", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TaiKhoan = "Create table TaiKhoan" +
                "( MaTaiKhoan Integer Primary key AUTOINCREMENT, TenNguoiDung Text, MatKhau Text, HoTen Text, LoaiTaiKhoan Text, Email Text, SoDienThoai Text, DiaChi Text)";
        String SanPham = "Create table SanPham" +
                "(MaSanPham Integer Primary key AUTOINCREMENT, TenSanPham Text, GiaBan Integer, SoLuong Integer, LoaiSanPham Text)";
        String DonHang = "Create table DonHang" +
                "(MaDonHang Integer Primary key AUTOINCREMENT, MaKhachHang Integer, NgayDat Date, TongTien Integer)";
        String ChiTietDonHang = "Create table ChiTietDonHang" +
                "(MaDonChiTiet Integer Primary key AUTOINCREMENT, MaDon Integer, MaSanPham Integer, SoLuongSanPham Integer, GiaSanPham Integer)";
        String GioHang = "Create table GioHang" +
                "(MaSanPham Integer Primary key , TenSanPham Text, GiaBan Integer, SoLuong Integer, LoaiSanPham Text)";


        db.execSQL(TaiKhoan);
        db.execSQL(SanPham);
        db.execSQL(DonHang);
        db.execSQL(ChiTietDonHang);
        db.execSQL(GioHang);

        String admin = "INSERT INTO TaiKhoan VALUES" +
                "(1, 'admin', '123456', 'Admin', 'admin', 'thanhhbps38236@gmail.com', '0123456789', 'Nghe An')";
        db.execSQL(admin);

        String themSanPham = "INSERT INTO SanPham (MaSanPham, TenSanPham, GiaBan, SoLuong, LoaiSanPham) VALUES " +
                "(1, 'Cà Phê Sữa', 30000, 100, 'Đồ uống'), " +
                "(2, 'Bánh Ngọt Xanh', 25000, 50, 'Bánh ngọt'), " +
                "(3, 'Trà Sữa Trân Châu', 35000, 75, 'Đồ uống'), " +
                "(4, 'Bánh Mì Kẹp Thịt', 20000, 120, 'Bánh mì')," +
                "(5, 'Cà Phê Sữa', 30000, 100, 'Đồ uống')," +
                "(6, 'Bánh Ngọt Xanh', 25000, 50, 'Bánh ngọt')," +
                "(7, 'Trà Sữa Trân Châu', 35000, 75, 'Đồ uống')";
        db.execSQL(themSanPham);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS TaiKhoan");
            db.execSQL("DROP TABLE IF EXISTS SanPham");
            db.execSQL("DROP TABLE IF EXISTS DonHang");
            db.execSQL("DROP TABLE IF EXISTS ChiTietDonHang");
            db.execSQL("DROP TABLE IF EXISTS GioHang");
            onCreate(db);
        }
    }
}