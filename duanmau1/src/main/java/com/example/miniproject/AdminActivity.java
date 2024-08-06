package com.example.miniproject;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdminActivity extends AppCompatActivity {
super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
    setContentView(R.layout.activity_dang_nhap);
    edtTenDangNhap = findViewById(R.id.edtTenTaiKhoan);
    edtMatKhau = findViewById(R.id.edtMatKhau);
    btnDangNhap = findViewById(R.id.btnDangNhap);
    txtDangKy = findViewById(R.id.txtDangKy);
    txtQuenMK = findViewById(R.id.txtQuenMK);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}