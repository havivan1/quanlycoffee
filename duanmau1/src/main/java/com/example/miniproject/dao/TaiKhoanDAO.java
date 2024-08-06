package com.example.miniproject.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.miniproject.database.DBhelper;
import com.example.miniproject.model.TaiKhoan;

public class TaiKhoanDAO {
    private DBhelper dBhelper;

    public TaiKhoanDAO(Context context) {
        dBhelper = new DBhelper(context);
    }

    // Thêm tài khoản
    public void themTaiKhoan(TaiKhoan taiKhoan) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenNguoiDung", taiKhoan.getTenNguoiDung());
        values.put("MatKhau", taiKhoan.getMatKhau());
        values.put("HoTen", taiKhoan.getHoTen());
        values.put("LoaiTaiKhoan", taiKhoan.getLoaiTaiKhoan());
        values.put("Email", taiKhoan.getEmail());
        values.put("SoDienThoai", taiKhoan.getSoDienThoai());
        values.put("DiaChi", taiKhoan.getDiaChi());
        db.insert("TaiKhoan", null, values);
        db.close();
    }

    public Integer dangNhapTaiKhoan(String tenNguoiDung, String matKhau) {
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        String sql = "SELECT MaTaiKhoan FROM TaiKhoan WHERE TenNguoiDung = ? AND MatKhau = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{tenNguoiDung, matKhau});
        if (cursor.moveToFirst()) {
            int userID = cursor.getInt(cursor.getColumnIndex("MaTaiKhoan"));
            cursor.close();
            db.close();
            return userID; // Trả về ID của người dùng
        } else {
            cursor.close();
            db.close();
            return null; // Nếu không tìm thấy
        }
    }


    // Kiểm tra email tồn tại trong data
    public boolean kiemTraEmail(String email) {
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        String sql = "SELECT count(*) FROM TaiKhoan WHERE Email = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{email});
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        db.close();
        return count > 0;
    }

    // Cập nhật mật khẩu mới
    public void capNhatMK(String email, String matKhauMoi) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MatKhau", matKhauMoi);
        db.update("TaiKhoan", values, "Email = ?", new String[]{email});
        db.close();
    }

    public String getHoTen(int userID) {
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT HoTen FROM TaiKhoan WHERE MaTaiKhoan = ?", new String[]{String.valueOf(userID)});
        if (cursor.moveToFirst()) {
            String hoTen = cursor.getString(cursor.getColumnIndex("HoTen"));
            cursor.close();
            return hoTen;
        }
        cursor.close();
        return null;
    }

    public TaiKhoan layThongTinTaiKhoan(int userID) {
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TaiKhoan WHERE MaTaiKhoan = ?", new String[]{String.valueOf(userID)});
        if (cursor.moveToFirst()) {
            String tenNguoiDung = cursor.getString(cursor.getColumnIndex("TenNguoiDung"));
            String matKhau = cursor.getString(cursor.getColumnIndex("MatKhau"));
            String hoTen = cursor.getString(cursor.getColumnIndex("HoTen"));
            String loaiTaiKhoan = cursor.getString(cursor.getColumnIndex("LoaiTaiKhoan"));
            String email = cursor.getString(cursor.getColumnIndex("Email"));
            String sdt = cursor.getString(cursor.getColumnIndex("SoDienThoai"));
            String diaChi = cursor.getString(cursor.getColumnIndex("DiaChi"));

            cursor.close();
            db.close();

            return new TaiKhoan(tenNguoiDung, matKhau, hoTen, loaiTaiKhoan, email, sdt, diaChi);
        }
        cursor.close();
        db.close();
        return null;
    }


}
