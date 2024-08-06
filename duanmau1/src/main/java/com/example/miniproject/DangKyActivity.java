package com.example.miniproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.miniproject.dao.TaiKhoanDAO;
import com.example.miniproject.model.TaiKhoan;
import com.google.android.material.textfield.TextInputEditText;

public class DangKyActivity extends AppCompatActivity {
    TextInputEditText edtHoTen, edtTenNguoiDung, edtMatKhau, edtEmail, edtSoDienThoai, edtDiaChi;
    Button btnDangKy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dang_ky);

        edtHoTen = findViewById(R.id.edtHoTen);
        edtTenNguoiDung = findViewById(R.id.edtTenNguoiDung);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        edtEmail = findViewById(R.id.edtEmail);
        edtSoDienThoai = findViewById(R.id.edtSoDienThoai);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        btnDangKy = findViewById(R.id.btnDangKy);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = edtHoTen.getText().toString();
                String tenNguoiDung = edtTenNguoiDung.getText().toString();
                String matKhau = edtMatKhau.getText().toString();
                String email = edtEmail.getText().toString();
                String soDienThoai = edtSoDienThoai.getText().toString();
                String diaChi = edtDiaChi.getText().toString();

                if(!hoTen.isEmpty() && !tenNguoiDung.isEmpty() && !matKhau.isEmpty()
                && !email.isEmpty() && !soDienThoai.isEmpty() && !diaChi.isEmpty()) {
                    TaiKhoanDAO tkDAO = new TaiKhoanDAO(DangKyActivity.this);
                    TaiKhoan tk = new TaiKhoan(0, tenNguoiDung, matKhau, hoTen, "Khách hàng", email, soDienThoai, diaChi);
                    tkDAO.themTaiKhoan(tk);
                    Toast.makeText(DangKyActivity.this, "Đăng ký tài khoản thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                Toast.makeText(DangKyActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}