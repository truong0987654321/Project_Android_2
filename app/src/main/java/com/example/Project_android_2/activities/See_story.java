package com.example.Project_android_2.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Project_android_2.R;
import com.example.Project_android_2.activities.Fragment_Seencomic.ListChapterFragment;
import com.example.Project_android_2.activities.Fragment_Seencomic.SeenComicAdapter;
import com.example.Project_android_2.activities.RC_recyclerView.RCAdapter_Chap;
import com.example.Project_android_2.activities.RC_recyclerView.RCModel_Chap;
import com.example.Project_android_2.models.chapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;

public class See_story extends AppCompatActivity {

    private View viewHeart;
    private AppCompatImageView imageViewRefund;
    private ImageView imageViewLogo;
    private Button btnConfirm;
    private TextView txtview_Title;
    private TextView txtview_Author;
    private RecyclerView rc_Chapter;



    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_story); // Thay đổi cách set content view

        // Tham chiếu các thành phần giao diện
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.nav_viewcomic);
        viewHeart = findViewById(R.id.heart_1_1);
        imageViewLogo = findViewById(R.id.imageViewLogo);
        btnConfirm = findViewById(R.id.confirm_button);
        txtview_Title = findViewById(R.id.textViewTitle);
        txtview_Author = findViewById(R.id.author); // Thêm tham chiếu cho txtview_Author

        // Khởi tạo adapter cho ViewPager
        SeenComicAdapter adapter = new SeenComicAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


}