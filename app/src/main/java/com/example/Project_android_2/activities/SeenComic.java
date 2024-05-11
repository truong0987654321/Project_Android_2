package com.example.Project_android_2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Project_android_2.R;
import com.example.Project_android_2.activities.RC_recyclerView.RCAdapter_Chap;
import com.example.Project_android_2.activities.RC_recyclerView.RCModel_Chap;
import com.example.Project_android_2.models.chapter;
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

public class SeenComic extends AppCompatActivity {
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
    private RecyclerView rc_Chapter;
    private RCAdapter_Chap rcAdapter;

    ArrayList<RCModel_Chap> modelArrayList;

    private StorageReference storageReference;

    private DatabaseReference databaseReference;

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

        storageReference = FirebaseStorage.getInstance().getReference("CHAPTER");
        databaseReference = FirebaseDatabase.getInstance().getReference("chapter");

        createListChap();
        /*
         * txtview_Chapter.setOnClickListener(new View.OnClickListener() {
         *
         * @Override
         * public void onClick(View view) {
         * Drawable drawable = getResources().getDrawable(R.drawable.retangle_red);
         * txtview_Chapter.setBackground(drawable);
         * Intent intent = new Intent(SeenComic.this, ViewComic.class);
         * startActivity(intent);
         * }
         * });
         */
        Log.d("test", "test" + rc_Chapter);
        imageViewRefund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeenComic.this, Home.class);
                startActivity(intent);
            }
        });
    }

    public void createListChap() {
        rc_Chapter = findViewById(R.id.rc_chapter);
        rc_Chapter.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rc_Chapter.setHasFixedSize(true);
        modelArrayList = new ArrayList<>();
        getIndexChap();
    }

    public void getIndexChap() {
        // Intent intent1 = getIntent();
        // String idcomic = intent1.getStringExtra("ID");
        Query query = databaseReference.orderByChild("ID_COMIC").equalTo("a5366deb-7b91-473a-a9c1-05107154cfed");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot chapterSnapshot : snapshot.getChildren()) {
                        String id = chapterSnapshot.getKey();
                        String content = chapterSnapshot.child("CONTENT").getValue(String.class);
                        String title = chapterSnapshot.child("TITLE").getValue(String.class);
                        Long chapterIndex = chapterSnapshot.child("CHAPTER_INDEX").getValue(Long.class);
                        String index = chapterIndex + "";
                        chapter Chapter = new chapter(id, title, index, content,
                                "a5366deb-7b91-473a-a9c1-05107154cfed");
                        RCModel_Chap rc = new RCModel_Chap(Chapter);
                        modelArrayList.add(rc);
                        Log.d("MyApp", "Giá trị số nguyên: " + content);
                    }
                }

                Collections.sort(modelArrayList);
                Integer size = modelArrayList.size();
                rcAdapter = new RCAdapter_Chap(SeenComic.this, modelArrayList);
                rc_Chapter.setAdapter(rcAdapter);
                rcAdapter.notifyDataSetChanged();
                rcAdapter.setOnItemClickListener(new RCAdapter_Chap.OnItemClickListener() {
                    @Override
                    public void onItemClick(chapter position) {
                        Intent intent = new Intent(SeenComic.this, ViewComic.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", String.valueOf(position.getId()));
                        bundle.putString("CONTENT", position.getContent());
                        bundle.putString("CHAPTER_INDEX", position.getIndex());
                        bundle.putString("ID_COMIC", position.getId_comic());
                        bundle.putString("TITLE", position.getTitle());
                        bundle.putInt("SIZE", size);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}