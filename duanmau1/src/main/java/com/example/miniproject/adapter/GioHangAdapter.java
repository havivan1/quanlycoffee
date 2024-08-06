package com.example.miniproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.miniproject.R;
import com.example.miniproject.dao.GioHangDAO;
import com.example.miniproject.model.SanPham;
import java.util.List;

import javax.naming.Context;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.GioHangViewHolder> {
    private Context context;
    private List<SanPham> sanPhamList;
    private GioHangDAO gioHangDAO;


    public GioHangAdapter(Context context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.gioHangDAO = new GioHangDAO(context);
    }


    @NonNull
    @Override
    public GioHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_giohang, parent, false);
        return new GioHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangViewHolder holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        holder.tenSanPham.setText(sanPham.getTenSanPham());
        holder.giaBan.setText(String.format("%d VNĐ", sanPham.getGiaBan()));
        holder.soLuong.setText(String.format("Số lượng: %d", sanPham.getSoLuong()));
        holder.hinhAnh.setImageResource(R.drawable.product1);

        holder.xoaKhoiGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gioHangDAO.xoaSanPhamGioHang(sanPham.getMaSanPham());
                sanPhamList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, sanPhamList.size());
            }
        });

        // Xử lý sự kiện tăng số lượng
        holder.tangSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gioHangDAO.tangSoLuongSanPham(sanPham.getMaSanPham());
                sanPham.setSoLuong(sanPham.getSoLuong() + 1);
                holder.soLuong.etText(String.format("Số lượng: %d", sanPham.getSoLuong()));
            }
        });

        // Xử lý sự kiện giảm số lượng
        holder.giamSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sanPham.getSoLuong() > 1) { // Đảm bảo số lượng không nhỏ hơn 1
                    gioHangDAO.giamSoLuongSanPham(sanPham.getMaSanPham());
                    sanPham.setSoLuong(sanPham.getSoLuong() - 1);
                    holder.soLuong.setText(String.format("Số lượng: %d", sanPham.getSoLuong()));
                } else {
                    // Bạn có thể thêm thông báo hoặc xử lý khác ở đây nếu cần
                    Toast.makeText(context, "Số lượng không thể giảm dưới 1", Toast.LENGTH_SHORT).show();
                }
            }
        });
}

    @Override
    public int getItemCount() {
        return sanPhamList != null ? sanPhamList.size() : 0;
    }

    public class GioHangViewHolder extends RecyclerView.ViewHolder {
        TextView tenSanPham;
        TextView giaBan;
        TextView soLuong;
        ImageView hinhAnh;
        TextView xoaKhoiGioHang;
        TextView tangSL, giamSL;

        public GioHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tenSanPham = itemView.findViewById(R.id.tenSanPham);
            giaBan = itemView.findViewById(R.id.giaBan);
            soLuong = itemView.findViewById(R.id.soLuong);
            hinhAnh = itemView.findViewById(R.id.hinhAnh);
            xoaKhoiGioHang = itemView.findViewById(R.id.xoaKhoiGioHang);
            tangSL = itemView.findViewById(R.id.tangSL);
            giamSL = itemView.findViewById(R.id.giamSL);
        }
    }
}
