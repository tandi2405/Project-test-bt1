package com.example.ngotandi_2212_ktgk;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.content.Intent;

public class ProfileActivity extends AppCompatActivity {

    ListView lvSinhVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        lvSinhVien = findViewById(R.id.lvSinhVien);
        ArrayAdapter<com.example.test.MainActivity.SinhVien> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, com.example.test.MainActivity.dsSinhVien);
        lvSinhVien.setAdapter(adapter);

        lvSinhVien.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(ProfileActivity.this, com.example.test.MainActivity.class);
            i.putExtra("vitri", position);
            startActivity(i);
            finish();
        });
    }
}
