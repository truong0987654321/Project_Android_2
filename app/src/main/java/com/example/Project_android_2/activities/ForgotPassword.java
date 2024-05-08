package com.example.Project_android_2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.Project_android_2.R;

public class ForgotPassword extends AppCompatActivity {
    private AppCompatImageView appCompatImageView_back;

    private EditText editTextEmail;
    private Button buttonSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        setupUI();

        chestExdit();
        handleclickbutton();
    }
    private void setupUI(){
        appCompatImageView_back = findViewById(R.id.back);
        editTextEmail = findViewById(R.id.butonemail);
        buttonSignIn = findViewById(R.id.buttonsignin);
    }
    private void handleclickbutton() {
        appCompatImageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void chestExdit(){
        editTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Nothing to do here
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Check if email is not empty
                if (!TextUtils.isEmpty(s) && Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
                    // Enable the button
                    buttonSignIn.setEnabled(true);
                    buttonSignIn.setAlpha(1.0f); // Set alpha back to full opacity
                } else {
                    // Disable the button if email is empty or not in valid format
                    buttonSignIn.setEnabled(false);
                    buttonSignIn.setAlpha(0.5f); // Reduce opacity
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Nothing to do here
            }
        });
    }
}
