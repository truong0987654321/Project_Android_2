package com.example.Project_android_2.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Project_android_2.R;
import com.example.Project_android_2.RC_recyclerView.RCAdapter;
import com.example.Project_android_2.RC_recyclerView.RCAdapter_Trending;
import com.example.Project_android_2.RC_recyclerView.RCModel;
import com.example.Project_android_2.RC_recyclerView.RCModel_title_story;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    ArrayList<RCModel> modelArrayList;
    ArrayList<RCModel_title_story> modeArrList_story;
    RCAdapter rcAdapter;
    RCAdapter_Trending rcAdapter2;
    String[] title = new String[]{
            "Action","Love","Adventure","Horror","Mystery","Comedy","Life","Drama"
    };
    String[] title_story = new String[]{
            "Luo Emperor Chapter 83","Mage Returns Chapter 91","Tom Raider Chapter 255","Versatile Maze Chapter 757"
    };
    SwitchCompat switchmode;
    boolean nightMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        createList_cate();
        createList_story();
        handleswitch();
    }
    public void createList_cate(){
        recyclerView = findViewById(R.id.rc_View);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        modelArrayList = new ArrayList<>();
        rcAdapter = new RCAdapter(this,modelArrayList);
        recyclerView.setAdapter(rcAdapter);
        for(int i = 0; i < title.length;i++){
            RCModel rcModel = new RCModel(title[i]);
            modelArrayList.add(rcModel);
        }
        rcAdapter.notifyDataSetChanged();
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
    public void handleswitch(){
        switchmode = findViewById(R.id.switchMode);
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("nightMode",false);
        if(nightMode){
            switchmode.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        switchmode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nightMode){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("nightMode",false);
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("nightMode",true);
                }
                editor.apply();
            }
        });
    }
}

