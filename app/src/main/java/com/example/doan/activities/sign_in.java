package com.example.doan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.doan.R;

public class sign_in extends AppCompatActivity {

    private TextView textv_fpassword;
    private TextView textv_SignUpnow;
    private AppCompatImageView appCompatImageView_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        textv_fpassword = findViewById(R.id.textv_fpassword);
        textv_SignUpnow = findViewById(R.id.textv_SignUpnow);
        appCompatImageView_back = findViewById(R.id.back);
        textv_fpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sign_in.this, forgot_password.class);
                startActivity(intent);
            }
        });
        textv_SignUpnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sign_in.this, sign_up.class);
                startActivity(intent);
            }
        });
        appCompatImageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sign_in.this, sign_up.class);
                onBackPressed();
            }
        });
    }
}