package com.example.Project_android_2.activities;

import android.annotation.SuppressLint;
<<<<<<< HEAD
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.SwitchCompat;
=======

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.view.GravityCompat;
>>>>>>> 1ba55fdf714bea67dc4b63bcaaf75ead075ac222
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Project_android_2.R;
<<<<<<< HEAD
import com.example.Project_android_2.RC_recyclerView.RCAdapter;
import com.example.Project_android_2.RC_recyclerView.RCAdapter_Trending;
import com.example.Project_android_2.RC_recyclerView.RCModel;
import com.example.Project_android_2.RC_recyclerView.RCModel_title_story;
=======
import com.example.Project_android_2.activities.RC_recyclerView.RCAdapter;
import com.example.Project_android_2.activities.RC_recyclerView.RCAdapter_Trending;
import com.example.Project_android_2.activities.RC_recyclerView.RCModel;
import com.example.Project_android_2.activities.RC_recyclerView.RCModel_title_story;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;
>>>>>>> 1ba55fdf714bea67dc4b63bcaaf75ead075ac222

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    RecyclerView recyclerView2;

    ArrayList<RCModel_title_story> modeArrList_story;

    RCAdapter_Trending rcAdapter2;
<<<<<<< HEAD

    String[] title_story = new String[]{
            "Luo Emperor Chapter 83","Mage Returns Chapter 91","Tom Raider Chapter 255","Versatile Maze Chapter 757"
    };
    ImageView Search_icon;
=======
    String[] title_story = new String[]{
            "Luo Emperor Chapter 83", "Mage Returns Chapter 91", "Tom Raider Chapter 255", "Versatile Maze Chapter 757"
    };
    ImageView Search_icon, img;
>>>>>>> 1ba55fdf714bea67dc4b63bcaaf75ead075ac222
    SwitchCompat switchmode;
    boolean nightMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    DrawerLayout drawerLayout;
<<<<<<< HEAD
    ImageView buttonDrawerToggle;
=======
    ShapeableImageView buttonDrawerToggle, imageViewDrawer,img_fb;
    TextView username, useremail;

>>>>>>> 1ba55fdf714bea67dc4b63bcaaf75ead075ac222
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
<<<<<<< HEAD

        createList_story();
        handleSearch();
        handleslidermenu();
      //  handleswitch();
    }

    @SuppressLint("WrongViewCast")
    public void createList_story(){
        recyclerView2 = findViewById((R.id.rc_story));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setHasFixedSize(true);
        modeArrList_story = new ArrayList<>();
        rcAdapter2 = new RCAdapter_Trending(this,modeArrList_story);
        recyclerView2.setAdapter(rcAdapter2);
        for(int i = 0;i < title_story.length;i++){
            RCModel_title_story rcModel2 = new RCModel_title_story(title_story[i]);
            modeArrList_story.add(rcModel2);
        }
        rcAdapter2.notifyDataSetChanged();
=======
>>>>>>> 1ba55fdf714bea67dc4b63bcaaf75ead075ac222


        Intent intent = getIntent();
        String userPhotoUrl = intent.getStringExtra("user_photo_url");
        String userName = intent.getStringExtra("user_name");
        String userEmail = intent.getStringExtra("user_email");
        buttonDrawerToggle = findViewById(R.id.imageUser);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_logout) {
                    // Kết thúc Activity Home
                    finish();
                    // Tạo Intent để chuyển đến AccountManagement
                    Intent intent = new Intent(Home.this, AccountManagement.class);
                    // Gửi thông điệp yêu cầu đăng xuất
                    intent.putExtra("logout", true);
                    startActivity(intent);
                    // Đóng drawer sau khi xử lý sự kiện
                    DrawerLayout drawer = findViewById(R.id.drawerLayout);
                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                }
                return false;
            }
        });



        Glide.with(this).load(userPhotoUrl).into(buttonDrawerToggle);
        createList_story();
        handleSearch();
        handleslidermenu(userPhotoUrl, userName, userEmail);
        //  handleswitch();
    }
<<<<<<< HEAD
    @SuppressLint("ResourceType")
    public void handleSearch(){
        Search_icon = findViewById(R.id.imageSearch);
        Search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,Search_comic.class);
                startActivity(intent);
            }
        });
    }
    @SuppressLint("WrongViewCast")
    public void handleslidermenu (){
        drawerLayout = findViewById(R.id.drawerLayout);
        buttonDrawerToggle = findViewById(R.id.imageUser);
        buttonDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });
    }
//    public void handleswitch(){
//        switchmode = findViewById(R.id.switchMode);
//        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
//        nightMode = sharedPreferences.getBoolean("nightMode",false);
//        if(nightMode){
//            switchmode.setChecked(true);
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }
//        switchmode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(nightMode){
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                    editor = sharedPreferences.edit();
//                    editor.putBoolean("nightMode",false);
//                }else {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                    editor = sharedPreferences.edit();
//                    editor.putBoolean("nightMode",true);
//                }
//                editor.apply();
//            }
//        });
//    }
}
=======
>>>>>>> 1ba55fdf714bea67dc4b63bcaaf75ead075ac222


    @SuppressLint("WrongViewCast")
    public void createList_story() {
        recyclerView2 = findViewById((R.id.rc_story));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setHasFixedSize(true);
        modeArrList_story = new ArrayList<>();
        rcAdapter2 = new RCAdapter_Trending(this, modeArrList_story);
        recyclerView2.setAdapter(rcAdapter2);
        for (int i = 0; i < title_story.length; i++) {
            RCModel_title_story rcModel2 = new RCModel_title_story(title_story[i]);
            modeArrList_story.add(rcModel2);
        }
        rcAdapter2.notifyDataSetChanged();
    }

    @SuppressLint("ResourceType")
    public void handleSearch() {
        Search_icon = findViewById(R.id.imageSearch);
        Search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Search_comic.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("WrongViewCast")
    public void handleslidermenu(String userPhotoUrl, String userName, String userEmail) {
        drawerLayout = findViewById(R.id.drawerLayout);
        buttonDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
                imageViewDrawer = drawerLayout.findViewById(R.id.imageUserViewDrawer);
                username = drawerLayout.findViewById(R.id.username);
                useremail = drawerLayout.findViewById(R.id.userEmail);
                Glide.with(Home.this).load(userPhotoUrl).into(imageViewDrawer);
                username.setText(userName);
                useremail.setText(userEmail);
            }
        });
    }


//    public void handleswitch(){
//        switchmode = findViewById(R.id.switchMode);
//        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
//        nightMode = sharedPreferences.getBoolean("nightMode",false);
//        if(nightMode){
//            switchmode.setChecked(true);
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }
//        switchmode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(nightMode){
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                    editor = sharedPreferences.edit();
//                    editor.putBoolean("nightMode",false);
//                }else {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                    editor = sharedPreferences.edit();
//                    editor.putBoolean("nightMode",true);
//                }
//                editor.apply();
//            }
//        });
//    }
}