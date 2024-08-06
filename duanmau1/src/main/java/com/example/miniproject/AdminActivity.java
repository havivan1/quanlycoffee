package com.example.miniproject;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdminActivity extends AppCompatActivity {
    edtHoTen = findViewById(R.id.edtHoTen);
    edtTenNguoiDung = findViewById(R.id.edtTenNguoiDung);
    edtMatKhau = findViewById(R.id.edtMatKhau);
    edtEmail = findViewById(R.id.edtEmail);
    edtSoDienThoai = findViewById(R.id.edtSoDienThoai);
    edtDiaChi = findViewById(R.id.edtDiaChi);
    btnDangKy = findViewById(R.id.btnDangKy);
    
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