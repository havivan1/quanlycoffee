package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.miniproject.dao.TaiKhoanDAO;
import com.example.miniproject.database.DBhelper;
import com.example.miniproject.model.TaiKhoan;
import com.example.miniproject.util.GiauThongTin;

public class HoSoActivity extends AppCompatActivity {
    private EditText edtTenNguoiDung;
    private TextView txtMatKhau, txtEmail, txtSDT, txtDiaChi, txtDoiMK, txtDoiEmail, txtDoiSDT, txtDoiDiaChi, txtThemDiaChi, txtXoaDiaChi;
    private AppCompatButton btnThayDoiThongTin;
    private Toolbar toolbar;
    private GiauThongTin info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ho_so);

        edtTenNguoiDung = findViewById(R.id.edtTenNguoiDung);
        txtMatKhau = findViewById(R.id.txtMatKhau);
        txtDoiMK = findViewById(R.id.txtDoiMK);
        txtSDT = findViewById(R.id.txtSDT);
        txtDoiSDT = findViewById(R.id.txtDoiSDT);
        txtEmail = findViewById(R.id.txtEmail);
        txtDoiEmail = findViewById(R.id.txtDoiEmail);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        txtDoiDiaChi = findViewById(R.id.txtDoiDiaChi);
        txtXoaDiaChi = findViewById(R.id.txtXoaDiaChi);
        txtThemDiaChi = findViewById(R.id.txtThemDiaChi);
        toolbar = findViewById(R.id.toolbar);
        btnThayDoiThongTin = findViewById(R.id.btnThayDoiThongTin);

        info = new GiauThongTin();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hồ sơ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_list);

        // Lấy thông tin người dùng từ cơ sở dữ liệu
        // Nhận userID từ Intent
        Intent intent = getIntent();
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO(this);
        int userID = intent.getIntExtra("USER_ID", -1);
                TaiKhoan user = taiKhoanDAO.layThongTinTaiKhoan(userID);

        if (user != null) {
            edtTenNguoiDung.setText(user.getTenNguoiDung());
            txtMatKhau.setText(info.AnMatKhau(user.getMatKhau()));
            txtEmail.setText(info.AnEmail(user.getEmail()));
            txtSDT.setText(info.AnSDT(user.getSoDienThoai()));
            txtDiaChi.setText(user.getDiaChi());
        }
    }
}