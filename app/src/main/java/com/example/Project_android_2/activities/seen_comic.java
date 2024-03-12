package com.example.Project_android_2.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.Project_android_2.R;

public class seen_comic extends AppCompatActivity {
    private View viewLogo;
    private View viewUser;
    private View viewCardBoard;
    private View viewPremium;
    private View viewCheck;
    private View viewHeart;
    private AppCompatImageView imageViewRefund;
    private ImageView imageViewLogo;
    private Button btnConfirm;
    private TextView txtview_Title;
    private TextView txtview_Author;
    private TextView txtview_Chapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seencomic);
        viewLogo = findViewById(R.id.logo_195e0d);
        viewUser = findViewById(R.id.group_4);
        viewCardBoard = findViewById(R.id.cardboard);
        viewPremium = findViewById(R.id.premium_qua);
        viewCheck = findViewById(R.id.check_1);
        viewHeart = findViewById(R.id.heart_1_1);
        imageViewRefund = findViewById(R.id.refund_ring);
        imageViewLogo = findViewById(R.id.imageViewLogo);
        btnConfirm = findViewById(R.id.confirm_button);
        txtview_Author = findViewById(R.id.author);
        txtview_Title = findViewById(R.id.textViewTitle);
        txtview_Chapter = findViewById(R.id.chap1);

        txtview_Chapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable = getResources().getDrawable(R.drawable.retangle_red);
                txtview_Chapter.setBackground(drawable);
                Intent intent = new Intent(seen_comic.this, view_comic.class);
                startActivity(intent);
            }
        });
    }
}
