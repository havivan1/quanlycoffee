package com.example.miniproject.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.miniproject.database.DBhelper;
import com.example.miniproject.model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    private DBhelper dBhelper;
    public SanPhamDAO(Context context) {
        dBhelper = new DBhelper(context);
    }

    public List<SanPham> layTatCaSanPham() {
        List<SanPham> sanPhamList = new ArrayList<>();
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from SanPham", null);
        if (cursor.moveToFirst()) {
            do {
                SanPham sp = new SanPham();
                sp.setMaSanPham(cursor.getInt(cursor.getColumnIndex("MaSanPham")));
                sp.setTenSanPham(cursor.getString(cursor.getColumnIndex("TenSanPham")));
                sp.setGiaBan(cursor.getInt(cursor.getColumnIndex("GiaBan")));
                sp.setSoLuong(cursor.getInt(cursor.getColumnIndex("SoLuong")));
                sp.setLoaiSanPham(cursor.getString(cursor.getColumnIndex("LoaiSanPham")));
                sanPhamList.add(sp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return sanPhamList;
    }

}