package com.example.miniproject.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.miniproject.database.DBhelper;
import com.example.miniproject.model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class GioHangDAO {
    private DBhelper dBhelper;

    public GioHangDAO(Context context) {
        dBhelper = new DBhelper(context);
    }

    public boolean themSanPhamVaoGioHang(SanPham sanPham) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();

        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
        Cursor cursor = db.rawQuery("SELECT * FROM GioHang WHERE MaSanPham = ?", new String[]{String.valueOf(sanPham.getMaSanPham())});
        if (cursor.moveToFirst()) {
            // Sản phẩm đã có trong giỏ hàng, tăng số lượng lên 1
            int currentQuantity = cursor.getInt(cursor.getColumnIndex("SoLuong"));
            ContentValues values = new ContentValues();
            values.put("SoLuong", currentQuantity + 1);
            int rowsAffected = db.update("GioHang", values, "MaSanPham = ?", new String[]{String.valueOf(sanPham.getMaSanPham())});
            cursor.close();
            return rowsAffected > 0;
        } else {
            // Sản phẩm chưa có trong giỏ hàng, thêm mới với số lượng là 1
            ContentValues values = new ContentValues();
            values.put("MaSanPham", sanPham.getMaSanPham());
            values.put("TenSanPham", sanPham.getTenSanPham());
            values.put("GiaBan", sanPham.getGiaBan());
            values.put("SoLuong", 1);
            values.put("LoaiSanPham", sanPham.getLoaiSanPham());
            long result = db.insert("GioHang", null, values);
            cursor.close();
            return result != -1;
        }
    }

    public List<SanPham> laySanPhamGioHang() {
        List<SanPham> list = new ArrayList<>();
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from GioHang", null);
        if (cursor.moveToFirst()) {
            do {
                SanPham sp = new SanPham();
                sp.setMaSanPham(cursor.getInt(cursor.getColumnIndex("MaSanPham")));
                sp.setTenSanPham(cursor.getString(cursor.getColumnIndex("TenSanPham")));
                sp.setGiaBan(cursor.getInt(cursor.getColumnIndex("GiaBan")));
                sp.setSoLuong(cursor.getInt(cursor.getColumnIndex("SoLuong")));
                sp.setLoaiSanPham(cursor.getString(cursor.getColumnIndex("LoaiSanPham")));
                list.add(sp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    //Xóa sp khỏi giỏ hàng
    public void xoaSanPhamGioHang(int maSanPham) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        db.delete("GioHang", "MaSanPham = ?", new String[]{String.valueOf(maSanPham)});
        db.close();
    }

    public void tangSoLuongSanPham(int maSanPham) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        db.execSQL("UPDATE GioHang SET SoLuong = SoLuong + 1 WHERE MaSanPham = ?", new Object[]{maSanPham});
    }

    public void giamSoLuongSanPham(int maSanPham) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        db.execSQL("UPDATE GioHang SET SoLuong = SoLuong - 1 WHERE MaSanPham = ?", new Object[]{maSanPham});
    }

    // Phương thức xóa tất cả sản phẩm trong giỏ hàng
    public void xoaTatCaSanPham() {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        db.execSQL("DELETE FROM GioHang");
        db.close();
    }

}
