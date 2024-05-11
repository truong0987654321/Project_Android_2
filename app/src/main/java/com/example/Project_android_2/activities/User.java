package com.example.Project_android_2.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.example.Project_android_2.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class User extends AppCompatActivity {
    private RelativeLayout editProfileLayout, editTextSizeLayout, editLanguageLayout;
    private AppCompatImageView appCompatImageView_back;
    private RoundedImageView img_user;
    private TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        setupUI();
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        String userUid = sharedPreferences.getString("userUid", "");
        String userPhotoUrl = sharedPreferences.getString("photoUrl", "");
        String userName = sharedPreferences.getString("userName", "");
        String userEmail = sharedPreferences.getString("userEmail", "");

        Glide.with(this).load(userPhotoUrl).into(img_user);
        username.setText(userName);
        appCompatImageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User.this, Home.class);
                startActivity(intent);
                finish();
            }
        });
        editProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User.this, EditUser.class);
                startActivity(intent);
            }
        });
        editTextSizeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User.this, EditTextSize.class);
                startActivity(intent);
            }
        });
        editLanguageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User.this, EditLanguage.class);
                startActivity(intent);
            }
        });
    }

    public void setupUI() {
        appCompatImageView_back = findViewById(R.id.back);
        editProfileLayout = findViewById(R.id.editProfileLayout);
        editTextSizeLayout = findViewById(R.id.editTextSizeLayout);
        editLanguageLayout = findViewById(R.id.editLanguageLayout);
        img_user = findViewById(R.id.img_user);
        username = findViewById(R.id.username);
    }
}
