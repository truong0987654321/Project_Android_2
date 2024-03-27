package com.example.Project_android_2.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Project_android_2.R;
import com.example.Project_android_2.activities.RC_recyclerView.RCAdapter;
import com.example.Project_android_2.activities.RC_recyclerView.RCAdapter_type_two;
import com.example.Project_android_2.activities.RC_recyclerView.RCModel;
import com.example.Project_android_2.activities.RC_recyclerView.RCModel_type_two;

import java.util.ArrayList;

public class Search_comic extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView rc_of_detail_cato;
    ArrayList<RCModel> modelArrayList;
    ArrayList<RCModel_type_two> modelArrtype_two;
    RCAdapter rcAdapter;
    RCAdapter_type_two rcAdapterTypeTwo;
    String[] title = new String[]{
            "Action","Love","Adventure","Horror","Mystery","Comedy","Life","Drama"
    };

    RCModel_type_two[] array_of_comic = new RCModel_type_two[]{
            new  RCModel_type_two("Magic Emperor","no name"),
            new  RCModel_type_two("Magic Emperor","no name"),
            new  RCModel_type_two("Magic Emperor","no name"),
            new  RCModel_type_two("Magic Emperor","no name"),
            new  RCModel_type_two("Magic Emperor","no name"),
            new  RCModel_type_two("Magic Emperor","no name"),
            new  RCModel_type_two("Magic Emperor","no name"),
            new  RCModel_type_two("Magic Emperor","no name"),
            new  RCModel_type_two("Magic Emperor","no name"),
            new  RCModel_type_two("Magic Emperor","no name"),
            new  RCModel_type_two("Magic Emperor","no name"),
            new  RCModel_type_two("Magic Emperor","no name"),
            new  RCModel_type_two("Magic Emperor","no name"),
            new  RCModel_type_two("Magic Emperor","no name"),
            new  RCModel_type_two("Magic Emperor","no name"),

            new  RCModel_type_two("Dungeon Reset","no name"),
            new  RCModel_type_two("Dungeon Reset","no name"),
            new  RCModel_type_two("Dungeon Reset","no name"),
            new  RCModel_type_two("Dungeon Reset","no name"),
            new  RCModel_type_two("Dungeon Reset","no name"),
            new  RCModel_type_two("Dungeon Reset","no name"),
            new  RCModel_type_two("Dungeon Reset","no name"),
            new  RCModel_type_two("Dungeon Reset","no name"),
            new  RCModel_type_two("Dungeon Reset","no name"),
            new  RCModel_type_two("Dungeon Reset","no name"),
            new  RCModel_type_two("Dungeon Reset","no name"),
            new  RCModel_type_two("Dungeon Reset","no name"),
            new  RCModel_type_two("Dungeon Reset","no name"),
            new  RCModel_type_two("Dungeon Reset","no name"),
            new  RCModel_type_two("Dungeon Reset","no name"),
            new  RCModel_type_two("Dungeon Reset","no name"),
            new  RCModel_type_two("Dungeon Reset","no name"),
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        createList_cate();
        createList_cate_two();
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
    public void createList_cate_two(){
        rc_of_detail_cato = findViewById(R.id.rc_comic_two);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rc_of_detail_cato.setLayoutManager(layoutManager);

        rc_of_detail_cato.setHasFixedSize(true);
        modelArrtype_two = new ArrayList<>();
        rcAdapterTypeTwo = new RCAdapter_type_two(this,modelArrtype_two);
        rc_of_detail_cato.setAdapter(rcAdapterTypeTwo);
        for(int i = 0; i < array_of_comic.length;i++){
            RCModel_type_two rcmodel = new RCModel_type_two
                    (array_of_comic[i].getName_comic(),array_of_comic[i].getName_author());
            modelArrtype_two.add(rcmodel);
        }
        rcAdapterTypeTwo.notifyDataSetChanged();
    }
}