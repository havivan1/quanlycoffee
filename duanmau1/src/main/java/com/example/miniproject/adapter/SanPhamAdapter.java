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

import com.example.miniproject.HomeActivity;
import com.example.miniproject.R;
import com.example.miniproject.dao.GioHangDAO;
import com.example.miniproject.model.SanPham;

import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder> {
    private Context context;
    private List<SanPham> sanPhamList;
    private GioHangDAO gioHangDAO;

    public SanPhamAdapter(HomeActivity context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.gioHangDAO = new GioHangDAO(context);
    }

    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        holder.tenSanPham.setText(sanPham.getTenSanPham());
        holder.giaBan.setText(String.format("%d VNĐ", sanPham.getGiaBan()));
        holder.loaiSanPham.setText(sanPham.getLoaiSanPham());
        holder.hinhAnh.setImageResource(R.drawable.product1);

        holder.themVaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = gioHangDAO.themSanPhamVaoGioHang(sanPham);
                if (success) {
                    Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Lỗi khi thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList != null ? sanPhamList.size() : 0;
    }

    public class SanPhamViewHolder extends RecyclerView.ViewHolder {
        TextView tenSanPham;
        TextView giaBan;
        TextView loaiSanPham;
        ImageView hinhAnh;
        TextView themVaoGioHang;

        public SanPhamViewHolder(@NonNull View itemView) {
            super(itemView);
            tenSanPham = itemView.findViewById(R.id.tenSanPham);
            giaBan = itemView.findViewById(R.id.giaBan);
            loaiSanPham = itemView.findViewById(R.id.loaiSanPham);
            hinhAnh = itemView.findViewById(R.id.hinhAnh);
            themVaoGioHang = itemView.findViewById(R.id.themVaoGioHang);
        }
    }
}
