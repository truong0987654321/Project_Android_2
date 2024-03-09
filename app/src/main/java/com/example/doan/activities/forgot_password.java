package com.example.doan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.doan.R;

public class forgot_password extends AppCompatActivity {
    private AppCompatImageView appCompatImageView_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        appCompatImageView_back = findViewById(R.id.back);

        appCompatImageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(forgot_password.this, sign_up.class);
                onBackPressed();
            }
        });
    }
}
