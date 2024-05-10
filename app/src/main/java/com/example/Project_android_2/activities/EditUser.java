package com.example.Project_android_2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.example.Project_android_2.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class EditUser extends AppCompatActivity {
    private AppCompatImageView appCompatImageView_back;
    private EditText edtext_edProfile;
    private RoundedImageView imageProfile;
    private TextView textAdImage, clearTextView_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        Intent intent = getIntent();
        String userName = intent.getStringExtra("user_name");
        String userPhoto = intent.getStringExtra("user_photo_url");

        appCompatImageView_back = findViewById(R.id.back);
        edtext_edProfile = findViewById(R.id.edtext_edProfile);
        imageProfile = findViewById(R.id.imageProfile);
        textAdImage = findViewById(R.id.textAdImage);
        clearTextView_email = findViewById(R.id.clearTextView_email);
        textAdImage.setText("");
        Glide.with(this).load(userPhoto).into(imageProfile);

        edtext_edProfile.setText(userName);

        edtext_edProfile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String Username = edtext_edProfile.getText().toString().trim();
                if (Username.isEmpty()) {
                    clearTextView_email.setVisibility(View.GONE);
                } else {
                    clearTextView_email.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        appCompatImageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        clearTextView_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtext_edProfile.setText("");
            }
        });


    }


}