package com.example.miniproject;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.miniproject.dao.DonHangDAO;
import com.example.miniproject.dao.ChiTietDonHangDAO;
import com.example.miniproject.model.ChiTietDonHang;
import com.example.miniproject.model.DonHang;

import java.util.List;

public class DonHangActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView textViewOrderId, textViewOrderDate, textViewTotalAmount;
    private LinearLayout layoutOrderDetails;
    private DonHangDAO donHangDAO;
    private ChiTietDonHangDAO chiTietDonHangDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);

        // Khởi tạo các view
        toolbar = findViewById(R.id.toolbar);
        textViewOrderId = findViewById(R.id.textViewOrderId);
        textViewOrderDate = findViewById(R.id.textViewOrderDate);
        textViewTotalAmount = findViewById(R.id.textViewTotalAmount);
        layoutOrderDetails = findViewById(R.id.layoutOrderDetails);

        donHangDAO = new DonHangDAO(this);
        chiTietDonHangDAO = new ChiTietDonHangDAO(this);

        // Lấy mã đơn hàng từ Intent
        int maDonHang = getIntent().getIntExtra("MA_DON_HANG", -1);

        if (maDonHang != -1) {
            // Hiển thị thông tin đơn hàng
            DonHang donHang = donHangDAO.layDonHang(maDonHang);
            if (donHang != null) {
                textViewOrderId.setText("Mã Đơn Hàng: " + donHang.getMaDonHang());
                textViewOrderDate.setText("Ngày Đặt: " + donHang.getNgayDat());
                textViewTotalAmount.setText("Tổng Tiền: " + donHang.getTongTien() + " VNĐ");

                // Hiển thị chi tiết đơn hàng
                List<ChiTietDonHang> chiTietDonHangList = chiTietDonHangDAO.layChiTietDonHang(maDonHang);
                hienThiChiTietDonHang(chiTietDonHangList);
            }
        }
    }

    private void hienThiChiTietDonHang(List<ChiTietDonHang> chiTietDonHangList) {
        layoutOrderDetails.removeAllViews();
        for (ChiTietDonHang chiTiet : chiTietDonHangList) {
            View itemView = getLayoutInflater().inflate(R.layout.item_chi_tiet_don_hang, layoutOrderDetails, false);
            TextView tenSanPham = itemView.findViewById(R.id.tenSanPham);
            TextView giaSanPham = itemView.findViewById(R.id.giaSanPham);
            TextView soLuong = itemView.findViewById(R.id.soLuong);

            String tenSanPhamStr = chiTietDonHangDAO.layTenSanPham(chiTiet.getMaSanPham());
            tenSanPham.setText(tenSanPhamStr);
            giaSanPham.setText(String.format("Giá: %d VNĐ", chiTiet.getGiaSanPham()));
            soLuong.setText(String.format("Số lượng: %d", chiTiet.getSoLuongSanPham()));

            layoutOrderDetails.addView(itemView);
        }
    }
}
