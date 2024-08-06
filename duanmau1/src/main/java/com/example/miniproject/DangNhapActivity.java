package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.miniproject.dao.TaiKhoanDAO;
import com.google.android.material.textfield.TextInputEditText;

public class DangNhapActivity extends AppCompatActivity {
    TextInputEditText edtTenDangNhap, edtMatKhau;
    Button btnDangNhap;
    TextView txtDangKy, txtQuenMK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dang_nhap);
        edtTenDangNhap = findViewById(R.id.edtTenTaiKhoan);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        txtDangKy = findViewById(R.id.txtDangKy);
        txtQuenMK = findViewById(R.id.txtQuenMK);

        // Nút đăng nhập
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenDangNhap = edtTenDangNhap.getText().toString();
                String matKhau = edtMatKhau.getText().toString();

                if (!tenDangNhap.isEmpty() && !matKhau.isEmpty()) {
                    TaiKhoanDAO tkDAO = new TaiKhoanDAO(DangNhapActivity.this);
                    Integer userID = tkDAO.dangNhapTaiKhoan(tenDangNhap, matKhau);
                    // Xử lí loại tài khoản là khách or Admin
                    if (userID != null) {
                        String loaiTaiKhoan = tkDAO.getHoTen(userID); // Lấy loại tài khoản nếu cần

                        Intent intent;
                        if (loaiTaiKhoan != null && loaiTaiKhoan.equalsIgnoreCase("admin")) {
                            intent = new Intent(DangNhapActivity.this, AdminActivity.class);
                        } else {
                            intent = new Intent(DangNhapActivity.this, HomeActivity.class);
                        }
                        intent.putExtra("USER_ID", userID); // Truyền userID
                        startActivity(intent);
                    } else {
                        Toast.makeText(DangNhapActivity.this, "Tên đăng nhập hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DangNhapActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Nút đăng kí
        txtDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhapActivity.this, DangKyActivity.class));
            }
        });

        // Nút quên mk
        txtQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhapActivity.this, QuenMKActivity.class));
            }
        });
    }
}