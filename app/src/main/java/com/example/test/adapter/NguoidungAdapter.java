package com.example.test.adapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.test.R;
import com.example.test.model.NguoiDung;

import java.util.List;

public class NguoidungAdapter extends ArrayAdapter<NguoiDung> {

    private Activity context;
    int resource;
    private List<NguoiDung> objects;


    public NguoidungAdapter(@NonNull Activity context, int resource, @NonNull List<NguoiDung> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        @SuppressLint("ViewHolder") View view = inflater.inflate(this.resource, null);


        TextView txtName = view.findViewById(R.id.tvName);
        TextView txtPhone = view.findViewById(R.id.tvPhone);
        ImageButton btncall = view.findViewById(R.id.imgbtCall);
        ImageButton btnDelete = view.findViewById(R.id.imgbtDelete);
        final NguoiDung nguoiDung = this.objects.get(position);

        txtName.setText(nguoiDung.getName());
        txtPhone.setText(nguoiDung.getPhone());

        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processingCall(nguoiDung);
                Toast.makeText(context, "Đang xử lý cuộc gọi !", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processingDelete(nguoiDung);
            }
        });

        return view;
    }

    private void processingCall(NguoiDung nguoiDung) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri uri = Uri.parse("tel:" + nguoiDung.getPhone());
        intent.setData(uri);

        if (ActivityCompat.checkSelfPermission(this.context,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        this.context.startActivity(intent);
    }

    private void processingDelete(NguoiDung nguoiDung) {
        this.remove(nguoiDung);
    }
}
