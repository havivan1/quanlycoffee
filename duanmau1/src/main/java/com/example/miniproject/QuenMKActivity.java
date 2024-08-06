package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.miniproject.dao.TaiKhoanDAO;
import com.example.miniproject.util.SendMail;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;

public class QuenMKActivity extends AppCompatActivity {
    TextInputEditText edtEmailQuenMK;
    Button btnXacNhanQuenMK;
    private SendMail sendMail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quen_mkactivity);

        edtEmailQuenMK = findViewById(R.id.edtEmailQuenMK);
        btnXacNhanQuenMK = findViewById(R.id.btnXacNhanQuenMK);
        sendMail = new SendMail();

        btnXacNhanQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmailQuenMK.getText().toString();
                if(!email.isEmpty()) {
                    TaiKhoanDAO tkDAO = new TaiKhoanDAO(QuenMKActivity.this);
                    boolean checkEmail = tkDAO.kiemTraEmail(email);

                    if(checkEmail) {
                        String matKhauMoi = taoMatKhauMoi();
                        tkDAO.capNhatMK(email, matKhauMoi);
                        guiEmail(email,matKhauMoi);
                        Toast.makeText(QuenMKActivity.this, "Mật khẩu mới đã được gửi về email", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(QuenMKActivity.this, DangNhapActivity.class));
                    }
                    else
                        Toast.makeText(QuenMKActivity.this, "Email không tồn tại", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(QuenMKActivity.this, "Vui lòng điền Email", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String taoMatKhauMoi() {
        // Tạo mật khẩu mới ngẫu nhiên
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random rnd = new Random();
        while (password.length() < 8) { // Mật khẩu tối thiểu 8 ký tự
            int index = (int) (rnd.nextFloat() * chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }

    //Gửi email quên mk
    private void guiEmail(String email, String matKhauMoi) {
        sendMail.send(QuenMKActivity.this, email, "Lấy lại mật khẩu", "Mật khẩu mới của bạn là: "+ matKhauMoi);
    }
}