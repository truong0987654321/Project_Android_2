package com.example.Project_android_2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.Project_android_2.R;

public class accountmanagement extends AppCompatActivity {
    private AppCompatImageView appCompatImageView_exit;
    private Button butonsignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountmanagement);

        appCompatImageView_exit = findViewById(R.id.exit);
        butonsignin = findViewById(R.id.butonsignin);

        appCompatImageView_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        butonsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(accountmanagement.this, sign_in.class);
                startActivity(intent);
            }
        });
    }
}
