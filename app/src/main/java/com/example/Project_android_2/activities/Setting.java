package com.example.Project_android_2.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.example.Project_android_2.R;

public class Setting extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    boolean isNightMode;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user); // Thay thế your_setting_layout bằng tên layout của bạn

        // Xác định RelativeLayout bằng findViewById
        RelativeLayout editProfileLayout = findViewById(R.id.editProfileLayout);
        RelativeLayout editTextSizeLayout = findViewById(R.id.editTextSizeLayout);
        RelativeLayout editLanguageLayout = findViewById(R.id.editLanguageLayout);
//        SwitchCompat switchCompat = findViewById(R.id.switchNightMode);
//        sharedPreferences = getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);
//        editor = sharedPreferences.edit();
//
//        isNightMode = sharedPreferences.getBoolean("nightMode", false);
//        if (isNightMode){
//            switchCompat.setChecked(true);
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }
//
//
//    //on off night mode
//        switchCompat.setOnClickListener(v -> {
//            myThemes();
//        });


        // Gán OnClickListener cho RelativeLayout
        editProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, EditUser.class ); // Thay EditProfileActivity bằng tên Activity bạn muốn mở
                startActivity(intent);
            }
        });

        editTextSizeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, EditTextSize.class ); // Thay EditProfileActivity bằng tên Activity bạn muốn mở
                startActivity(intent);
            }
        });

        editLanguageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, EditLanguage.class ); // Thay EditProfileActivity bằng tên Activity bạn muốn mở
                startActivity(intent);
            }
        });

    }

//    private void myThemes() {
//        if(isNightMode){
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//            editor.putBoolean("nightMode", false);
//        }else {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//            editor.putBoolean("nightMode",true);
//        }
//        editor.apply();
//
//    }
}