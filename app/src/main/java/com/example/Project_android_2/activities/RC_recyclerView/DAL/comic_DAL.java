package com.example.Project_android_2.activities.RC_recyclerView.DAL;

import androidx.annotation.NonNull;

import com.example.Project_android_2.activities.RC_recyclerView.comic_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class comic_DAL {

    public void getList_comic (comic_interface comiclistener){
        ArrayList<comic_model> arr_comic = new ArrayList<comic_model>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference comicsRef = database.getReference("comics");
        comicsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot comicsnap : snapshot.getChildren()){
                    comic_model model = comicsnap.getValue(comic_model.class);
                    arr_comic.add(model);
                }
                comiclistener.onComicListLoad(arr_comic);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                comiclistener.onComicListLoad(arr_comic);
            }
        });
    }
}
