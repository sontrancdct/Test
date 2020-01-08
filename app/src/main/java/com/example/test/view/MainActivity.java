package com.example.test.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.example.test.adapter.NguoidungAdapter;
import com.example.test.model.NguoiDung;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Context context;
        ListView lvNguoidung;

    ArrayList<NguoiDung> arrayNguoidung;
    NguoidungAdapter nguoidungAdapter;

    EditText edtID, edtName, edtPhone;
    Button btnAdd, btnRead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;

        addControls();
        addEvents();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    private void addControls() {
        edtID   = findViewById(R.id.edtID);
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        btnAdd  = findViewById(R.id.btnAdd);
        btnRead = findViewById(R.id.btnRead);
        //anhxa
        lvNguoidung = findViewById(R.id.lvDanhSachTen);
        //khoi tao list
        arrayNguoidung = new ArrayList<>();
        //truyen
        nguoidungAdapter = new NguoidungAdapter(MainActivity.this, R.layout.item_row,arrayNguoidung);
        //lay ra
        lvNguoidung.setAdapter(nguoidungAdapter);
        nguoidungAdapter.notifyDataSetChanged();

    }

    private void addEvents() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, new2Activity.class);
                startActivity(intent);
            }
        });
    }

    private void addUser() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Nguoidung", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int id = edtID.getText().length();
        String name = edtName.getText().toString();
        String phone = edtPhone.getText().toString();

        if(id > 0 && edtName.getText().length() > 0 && edtPhone.getText().length() >0){
            NguoiDung nguoiDung = new NguoiDung(edtID.getText().length(), edtName.getText().toString(), edtPhone.getText().toString());
            arrayNguoidung.add(nguoiDung);
            nguoidungAdapter.notifyDataSetChanged();

            editor.putInt("id",id);
            editor.putString("name",name);
            editor.putString("phone",phone);
            editor.apply();


            Toast.makeText(context, "Thêm thành công !", Toast.LENGTH_SHORT).show();
            edtID.setText("");
            edtName.setText("");
            edtPhone.setText("");

        }else {
            Toast.makeText(this, "Vui lòng nhập đủ thông tin !", Toast.LENGTH_SHORT).show();
        }


    }


}
