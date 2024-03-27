package com.example.Project_android_2.activities;

import android.annotation.SuppressLint;
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
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Project_android_2.R;
import com.example.Project_android_2.activities.RC_recyclerView.RCAdapter;
import com.example.Project_android_2.activities.RC_recyclerView.RCAdapter_Trending;
import com.example.Project_android_2.activities.RC_recyclerView.RCModel;
import com.example.Project_android_2.activities.RC_recyclerView.RCModel_title_story;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    RecyclerView recyclerView2;

    ArrayList<RCModel_title_story> modeArrList_story;

    RCAdapter_Trending rcAdapter2;

    String[] title_story = new String[]{
            "Luo Emperor Chapter 83","Mage Returns Chapter 91","Tom Raider Chapter 255","Versatile Maze Chapter 757"
    };
    ImageView Search_icon;
    SwitchCompat switchmode;
    boolean nightMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    DrawerLayout drawerLayout;
    ImageView buttonDrawerToggle;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);

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

    }
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