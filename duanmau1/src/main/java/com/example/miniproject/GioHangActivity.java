package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniproject.adapter.GioHangAdapter;
import com.example.miniproject.dao.GioHangDAO;
import com.example.miniproject.dao.TaiKhoanDAO;
import com.example.miniproject.model.SanPham;
import com.example.miniproject.model.TaiKhoan;

import java.util.List;

public class GioHangActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GioHangAdapter gioHangAdapter;
    private List<SanPham> gioHangList;
    private GioHangDAO gioHangDAO;
    private Button btnThanhToan;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        recyclerView = findViewById(R.id.RecycleGioHang);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Giỏ Hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO(this);
        int userID = intent.getIntExtra("USER_ID", -1);
        TaiKhoan user = taiKhoanDAO.layThongTinTaiKhoan(userID);

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GioHangActivity.this, ThanhToanActivity.class);
                intent.putExtra("USER_ID", userID);
                startActivity(intent);
            }
        });

        gioHangDAO = new GioHangDAO(this);
        gioHangList = gioHangDAO.laySanPhamGioHang();

        gioHangAdapter = new GioHangAdapter(this, gioHangList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(gioHangAdapter);
    }

    protected void onResume() {
        super.onResume();
        // Làm mới danh sách giỏ hàng
        gioHangList.clear();
        gioHangList.addAll(gioHangDAO.laySanPhamGioHang());
        gioHangAdapter.notifyDataSetChanged();
    }
}
