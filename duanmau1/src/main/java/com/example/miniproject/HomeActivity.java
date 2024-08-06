package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.example.miniproject.adapter.ImagePagerAdapter;
import com.example.miniproject.adapter.SanPhamAdapter;
import com.example.miniproject.dao.SanPhamDAO;
import com.example.miniproject.dao.TaiKhoanDAO;
import com.example.miniproject.model.SanPham;
import com.google.android.material.navigation.NavigationView;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private RecyclerView recyclerView;
    private NavigationView navigationView;
    private ImagePagerAdapter adapter;
    private Handler handler;
    private Runnable runnable;
    private int currentPage = 0;
    private final int NUM_PAGES = 3;
    private List<SanPham> sanPhamList;
    private SanPhamAdapter sanPhamAdapter;
    private SanPhamDAO spDao;
    private DrawerLayout drawerLayout;
    private TextView txtXinChao;
    private TaiKhoanDAO taiKhoanDAO;
    private int currentUserID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.viewPager);
        recyclerView = findViewById(R.id.recyclerView);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.main);
        spDao = new SanPhamDAO(this);
        taiKhoanDAO = new TaiKhoanDAO(this);

        // Nhận userID từ Intent
        Intent intent = getIntent();
        currentUserID = intent.getIntExtra("USER_ID", -1);

        // Lấy header của NavigationView
        View headerView = navigationView.getHeaderView(0);
        txtXinChao = headerView.findViewById(R.id.txtXinChao);

        // Lấy tên người dùng và cập nhật giao diện
        String hoTen = taiKhoanDAO.getHoTen(currentUserID);
        if (hoTen != null) {
            txtXinChao.setText("Xin chào, " + hoTen);
        } else {
            txtXinChao.setText("Xin chào!");
        }

        // Set up toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Trang Chủ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_list);

        // Khởi tạo ViewPager và ImagePagerAdapter
        int[] imageIds = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3};
        adapter = new ImagePagerAdapter(this, imageIds);
        viewPager.setAdapter(adapter);

        // Banner tự động chạy
        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                if (adapter != null) {
                    if (currentPage == adapter.getCount()) {
                        currentPage = 0;
                    } else {
                        currentPage++;
                    }
                    viewPager.setCurrentItem(currentPage, true);
                    handler.postDelayed(this, 3000);
                }
            }
        };
        handler.postDelayed(runnable, 3000);

        // Lấy danh sách sản phẩm từ database
        sanPhamList = spDao.layTatCaSanPham();
        sanPhamAdapter = new SanPhamAdapter(HomeActivity.this, sanPhamList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(sanPhamAdapter);


        // Xử lí bấm menu trong nav
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_donhang) {
                    Intent intent = new Intent(HomeActivity.this, DonHangActivity.class);
                    intent.putExtra("USER_ID", currentUserID);
                    startActivity(intent);
                } else if (id == R.id.nav_profile) {
                    Intent intent = new Intent(HomeActivity.this, HoSoActivity.class);
                    intent.putExtra("USER_ID", currentUserID);
                    startActivity(intent);
                } else if (id == R.id.nav_cart) {
                    Intent intent = new Intent(HomeActivity.this, GioHangActivity.class);
                    intent.putExtra("USER_ID", currentUserID);
                    startActivity(intent);
                } else if (id == R.id.nav_logout) {
                    // Đăng xuất
                    Intent logoutIntent = new Intent(HomeActivity.this, DangNhapActivity.class);
                    logoutIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(logoutIntent);
                    Toast.makeText(HomeActivity.this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
                }

                // Đóng Drawer sau khi chọn mục
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
