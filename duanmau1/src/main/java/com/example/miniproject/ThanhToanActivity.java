package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.miniproject.dao.ChiTietDonHangDAO;
import com.example.miniproject.dao.DonHangDAO;
import com.example.miniproject.dao.GioHangDAO;
import com.example.miniproject.dao.TaiKhoanDAO;
import com.example.miniproject.model.SanPham;
import com.example.miniproject.model.TaiKhoan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ThanhToanActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView textViewTotalAmount;
    private EditText edtHoTen, edtDiaChi;
    private RadioGroup radioGroupPaymentMethod;
    private Button btnDatHang;
    private GioHangDAO gioHangDAO;
    private List<SanPham> sanPhamList;
    private int totalAmount;
    private ImageView imgQR;
    private TaiKhoanDAO tkDAO;
    private DonHangDAO donHangDAO;
    private ChiTietDonHangDAO chiTietDonHangDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        // Khởi tạo các view
        toolbar = findViewById(R.id.toolbar);
        textViewTotalAmount = findViewById(R.id.textViewTotalAmount);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        radioGroupPaymentMethod = findViewById(R.id.radioGroupPaymentMethod);
        btnDatHang = findViewById(R.id.btnDatHang);
        gioHangDAO = new GioHangDAO(this);
        tkDAO = new TaiKhoanDAO(this);
        imgQR = findViewById(R.id.imgQR);
        chiTietDonHangDAO = new ChiTietDonHangDAO(this);
        donHangDAO = new DonHangDAO(this);

        //set up toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thanh Toán Đơn Hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Lấy thông tin người dùng từ Intent
        Intent intent = getIntent();
        int userID = intent.getIntExtra("USER_ID", -1);
        TaiKhoan user = tkDAO.layThongTinTaiKhoan(userID);

        if (user != null) {
            edtHoTen.setText("Họ tên: "+user.getHoTen());
            edtDiaChi.setText("Địa chỉ: " + user.getDiaChi());
        }

        // Lấy danh sách sản phẩm trong giỏ hàng
        sanPhamList = gioHangDAO.laySanPhamGioHang();
        DoDsSanPham();
        tinhTongTienDonHang();

        // Xử lý sự kiện nút đặt hàng
        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datHang();
            }
        });

        // Xử lý sự kiện thay đổi phương thức thanh toán
        radioGroupPaymentMethod.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                upImgQR(checkedId);
            }
        });
    }

    private void DoDsSanPham() {
        LinearLayout layoutProductList = findViewById(R.id.layoutProductList);
        layoutProductList.removeAllViews();
        for (SanPham sanPham : sanPhamList) {
            View itemView = LayoutInflater.from(this).inflate(R.layout.item_sanphamthanhtoan, layoutProductList, false);
            TextView tenSanPham = itemView.findViewById(R.id.tenSanPham);
            TextView giaBan = itemView.findViewById(R.id.giaBan);
            TextView soLuong = itemView.findViewById(R.id.soLuong);

            tenSanPham.setText(sanPham.getTenSanPham());
            giaBan.setText(String.format("%d VNĐ", sanPham.getGiaBan()));
            soLuong.setText(String.format("Số lượng: %d", sanPham.getSoLuong()));

            layoutProductList.addView(itemView);
        }
    }

    private void tinhTongTienDonHang() {
        totalAmount = 0;
        for (SanPham sanPham : sanPhamList) {
            totalAmount += sanPham.getGiaBan() * sanPham.getSoLuong();
        }
        textViewTotalAmount.setText(String.format("Tổng tiền: %d VNĐ", totalAmount));
    }

    private void datHang() {
        String name = edtHoTen.getText().toString().trim();
        String address = edtDiaChi.getText().toString().trim();
        int selectedPaymentMethodId = radioGroupPaymentMethod.getCheckedRadioButtonId();
        RadioButton selectedPaymentMethod = findViewById(selectedPaymentMethodId);

        if (name.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        String paymentMethod = selectedPaymentMethod != null ? selectedPaymentMethod.getText().toString() : "";

        // Lưu đơn hàng vào cơ sở dữ liệu
        int userID = getIntent().getIntExtra("USER_ID", -1);
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        long maDonHang = donHangDAO.themDonHang(userID, currentDate, totalAmount);

        // Lưu chi tiết đơn hàng vào cơ sở dữ liệu
        if (maDonHang != -1) {
            for (SanPham sanPham : sanPhamList) {
                chiTietDonHangDAO.themChiTietDonHang((int) maDonHang, sanPham.getMaSanPham(), sanPham.getSoLuong(), sanPham.getGiaBan());
            }
            // Xóa tất cả sản phẩm trong giỏ hàng sau khi đặt hàng
            gioHangDAO.xoaTatCaSanPham();
            Toast.makeText(this, "Đặt hàng thành công!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Có lỗi xảy ra khi đặt hàng!", Toast.LENGTH_SHORT).show();
        }


        // Quay về màn hình Home
        Intent homeIntent = new Intent(ThanhToanActivity.this, HomeActivity.class);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(homeIntent);
        finish();
    }

    private void upImgQR(int selectedPaymentMethodId) {
        ImageView imgQR = findViewById(R.id.imgQR);
        if (selectedPaymentMethodId == R.id.radioButtonTransfer) {
            imgQR.setVisibility(View.VISIBLE);
            imgQR.setImageResource(R.drawable.qrcode);
        } else {
            imgQR.setVisibility(View.GONE);
        }
    }
}
