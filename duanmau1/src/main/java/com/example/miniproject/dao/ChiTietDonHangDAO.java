package com.example.miniproject.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.miniproject.database.DBhelper;
import com.example.miniproject.model.ChiTietDonHang;

import java.util.ArrayList;
import java.util.List;

public class ChiTietDonHangDAO {
    private SQLiteDatabase db;
    private DBhelper dBhelper;

    public ChiTietDonHangDAO(Context context) {
        dBhelper = new DBhelper(context);
        db = dBhelper.getWritableDatabase();
    }

    public long themChiTietDonHang(int maDon, int maSanPham, int soLuongSanPham, int giaSanPham) {
        ContentValues values = new ContentValues();
        values.put("MaDon", maDon);
        values.put("MaSanPham", maSanPham);
        values.put("SoLuongSanPham", soLuongSanPham);
        values.put("GiaSanPham", giaSanPham);
        return db.insert("ChiTietDonHang", null, values);
    }

    public List<ChiTietDonHang> layChiTietDonHang(int maDonHang) {
        List<ChiTietDonHang> list = new ArrayList<>();
        Cursor cursor = db.query("ChiTietDonHang", null, "MaDonHang = ?", new String[]{String.valueOf(maDonHang)}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                ChiTietDonHang chiTiet = new ChiTietDonHang();
                chiTiet.setMaDonChiTiet(cursor.getInt(cursor.getColumnIndex("MaDon")));
                chiTiet.setMaSanPham(cursor.getInt(cursor.getColumnIndex("MaSanPham")));
                chiTiet.setSoLuongSanPham(cursor.getInt(cursor.getColumnIndex("SoLuongSanPham")));
                chiTiet.setGiaSanPham(cursor.getInt(cursor.getColumnIndex("GiaSanPham")));
                list.add(chiTiet);
            }
            cursor.close();
        } else {
            Log.e("ChiTietDonHangDAO", "Cursor is null for order ID: " + maDonHang);
        }
        return list;
    }


    public String layTenSanPham(int maSanPham) {
        String tenSanPham = "";
        Cursor cursor = db.rawQuery("SELECT TenSanPham FROM SanPham WHERE MaSanPham = ?", new String[]{String.valueOf(maSanPham)});
        if (cursor.moveToFirst()) {
            tenSanPham = cursor.getString(cursor.getColumnIndex("TenSanPham"));
        }
        cursor.close();
        return tenSanPham;
    }


}
