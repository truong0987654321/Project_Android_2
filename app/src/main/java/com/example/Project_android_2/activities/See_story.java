package com.example.Project_android_2.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.Project_android_2.R;
import com.example.Project_android_2.activities.Fragment_Seencomic.SeenComicAdapter;
import com.google.android.material.tabs.TabLayout;

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