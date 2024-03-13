package com.example.Project_android_2.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.Project_android_2.R;

public class ViewComic extends AppCompatActivity {
    private AppCompatImageView imageViewRefund;
    private View viewUser;
    private View viewLeft;
    private View viewRight;
    private TextView txt_Chapter;
    private ImageView imageViewChapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcomic);
        imageViewRefund = findViewById(R.id.refund_ring);
        viewUser = findViewById(R.id.group_4);
        viewLeft = findViewById(R.id.arrow_left_long);
        viewRight = findViewById(R.id.arrow_right);
        txt_Chapter = findViewById(R.id.chap1);
        imageViewChapter = findViewById(R.id.imageViewComic);
    }
}
