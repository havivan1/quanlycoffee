package com.example.miniproject.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.miniproject.database.DBhelper;
import com.example.miniproject.model.DonHang;

public class DonHangDAO {
    private SQLiteDatabase db;
    private DBhelper dBhelper;

    public DonHangDAO (Context context) {
        dBhelper = new DBhelper(context);
        db = dBhelper.getWritableDatabase();
    }

    public long themDonHang(int maKhachHang, String ngayDat, int tongTien) {
        ContentValues values = new ContentValues();
        values.put("MaKhachHang", maKhachHang);
        values.put("NgayDat", ngayDat);
        values.put("TongTien", tongTien);
        return db.insert("DonHang", null, values);
    }

    public DonHang layDonHang(int maDonHang) {
        Cursor cursor = db.query("DonHang", null, "MaDonHang = ?", new String[]{String.valueOf(maDonHang)}, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                DonHang donHang = new DonHang();
                donHang.setMaDonHang(cursor.getInt(cursor.getColumnIndex("MaDonHang")));
                donHang.setMaKhachHang(cursor.getInt(cursor.getColumnIndex("MaKhachHang")));
                donHang.setNgayDat(cursor.getString(cursor.getColumnIndex("NgayDat")));
                donHang.setTongTien(cursor.getInt(cursor.getColumnIndex("TongTien")));
                cursor.close();
                return donHang;
            } else {
                Log.e("DonHangDAO", "No order found for ID: " + maDonHang);
            }
            cursor.close();
        } else {
            Log.e("DonHangDAO", "Cursor is null for order ID: " + maDonHang);
        }
        return null;
    }



}
