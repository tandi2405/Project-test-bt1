package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import com.example.ngotandi_2212_ktgk.ProfileActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtTen, edtMSSV, edtNamSinh, edtLop;
    Button btnThem, btnSua, btnXoa, btnDanhSach;
    static ArrayList<SinhVien> dsSinhVien = new ArrayList<>();
    static int viTriDangChon = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTen = findViewById(R.id.edtTen);
        edtMSSV = findViewById(R.id.edtMSSV);
        edtNamSinh = findViewById(R.id.edtNamSinh);
        edtLop = findViewById(R.id.edtLop);
        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
        btnDanhSach = findViewById(R.id.btnDanhSach);

        // Nếu có dữ liệu từ ProfileActivity gửi về (chọn sinh viên)
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("vitri")) {
            viTriDangChon = intent.getIntExtra("vitri", -1);
            if (viTriDangChon != -1) {
                SinhVien sv = dsSinhVien.get(viTriDangChon);
                edtTen.setText(sv.ten);
                edtMSSV.setText(sv.mssv);
                edtNamSinh.setText(sv.namSinh);
                edtLop.setText(sv.lop);
            }
        }

        btnThem.setOnClickListener(v -> {
            String ten = edtTen.getText().toString();
            String mssv = edtMSSV.getText().toString();
            String nam = edtNamSinh.getText().toString();
            String lop = edtLop.getText().toString();

            if (ten.isEmpty() || mssv.isEmpty() || nam.isEmpty() || lop.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            dsSinhVien.add(new SinhVien(ten, mssv, nam, lop));
            Toast.makeText(this, "Đã thêm sinh viên", Toast.LENGTH_SHORT).show();
            clearInput();
        });

        btnSua.setOnClickListener(v -> {
            if (viTriDangChon != -1) {
                SinhVien sv = dsSinhVien.get(viTriDangChon);
                sv.ten = edtTen.getText().toString();
                sv.mssv = edtMSSV.getText().toString();
                sv.namSinh = edtNamSinh.getText().toString();
                sv.lop = edtLop.getText().toString();
                Toast.makeText(this, "Đã cập nhật thông tin", Toast.LENGTH_SHORT).show();
                viTriDangChon = -1;
                clearInput();
            } else {
                Toast.makeText(this, "Chưa chọn sinh viên để sửa", Toast.LENGTH_SHORT).show();
            }
        });

        btnXoa.setOnClickListener(v -> {
            if (viTriDangChon != -1) {
                dsSinhVien.remove(viTriDangChon);
                Toast.makeText(this, "Đã xóa sinh viên", Toast.LENGTH_SHORT).show();
                viTriDangChon = -1;
                clearInput();
            } else {
                Toast.makeText(this, "Chưa chọn sinh viên để xóa", Toast.LENGTH_SHORT).show();
            }
        });

        btnDanhSach.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(i);
        });
    }

    private void clearInput() {
        edtTen.setText("");
        edtMSSV.setText("");
        edtNamSinh.setText("");
        edtLop.setText("");
    }

    // Lớp model sinh viên
    public static class SinhVien {
        String ten, mssv, namSinh, lop;
        public SinhVien(String ten, String mssv, String namSinh, String lop) {
            this.ten = ten;
            this.mssv = mssv;
            this.namSinh = namSinh;
            this.lop = lop;
        }
        @Override
        public String toString() {
            return ten + " - " + mssv + " (" + lop + ")";
        }
    }
}
