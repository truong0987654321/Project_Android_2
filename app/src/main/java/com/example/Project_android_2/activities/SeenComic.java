package com.example.Project_android_2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

public class SeenComic extends AppCompatActivity {
    private View viewLogo;
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

    private DatabaseReference databaseReference;
    private ALodingDialog aLodingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seencomic);
        aLodingDialog = new ALodingDialog(this);
        setUI();
        createListChap();

        imageViewRefund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeenComic.this, SearchComic.class);
                startActivity(intent);
            }
        });
    }

    private void setUI()
    {
        viewLogo = findViewById(R.id.logo_195e0d);
        viewCardBoard = findViewById(R.id.cardboard);
        viewPremium = findViewById(R.id.premium_qua);
        viewCheck = findViewById(R.id.check_1);
        viewHeart = findViewById(R.id.heart_1_1);
        imageViewRefund = findViewById(R.id.refund_ring);
        imageViewLogo = findViewById(R.id.imageViewLogo);
        btnConfirm = findViewById(R.id.confirm_button);
        txtview_Author = findViewById(R.id.author);
        txtview_Author.setText("AUTHOR : ");
        txtview_Title = findViewById(R.id.textViewTitle);
        ComicUI();
    }

    public void ComicUI()
    {
        Bundle bundle = getIntent().getExtras();
        databaseReference = FirebaseDatabase.getInstance().getReference("comic");
        String idcomic = bundle.getString("ID_COMIC");
        if(idcomic != null)
        {
            Query query = databaseReference.orderByChild("ID").equalTo(idcomic);
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot comicSnapshot : snapshot.getChildren()) {
                            String image = comicSnapshot.child("THUMBNAIL").getValue(String.class);
                            String name_comic = comicSnapshot.child("TITLE").getValue(String.class);
                            String author = comicSnapshot.child("AUTHOR").getValue(String.class);
                            Picasso.get().load(image).into(imageViewLogo);
                            txtview_Title.setText(name_comic);
                            getAuthor(author);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public void getAuthor(String id)
    {
        databaseReference = FirebaseDatabase.getInstance().getReference("author").child(id);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    String author = snapshot.child("NAME").getValue(String.class);
                    txtview_Author.setText("AUTHOR : "+ author);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
        Intent intent1 = getIntent();
        String idcomic = intent1.getStringExtra("ID_COMIC");

        if (aLodingDialog != null) {
            aLodingDialog.show();
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("chapter");
        Query query = databaseReference.orderByChild("ID_COMIC").equalTo(idcomic);

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
                        chapter Chapter = new chapter(id, title, index, content,idcomic);
                        RCModel_Chap rc = new RCModel_Chap(Chapter);
                        modelArrayList.add(rc);
                    }
                }
                if (aLodingDialog != null && aLodingDialog.isShowing()) {
                    aLodingDialog.dismiss();
                }
                int size = (int) snapshot.getChildrenCount();
                Collections.sort(modelArrayList);
                rcAdapter = new RCAdapter_Chap(SeenComic.this, modelArrayList);
                rc_Chapter.setAdapter(rcAdapter);
                rcAdapter.notifyDataSetChanged();
                rcAdapter.setOnItemClickListener(new RCAdapter_Chap.OnItemClickListener() {
                    @Override
                    public void onItemClick(chapter position) {
                        Intent intent = new Intent(SeenComic.this, ViewComic.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", String.valueOf(position.getId()));
                        bundle.putString("CHAPTER_INDEX", position.getIndex());
                        bundle.putString("ID_COMIC", position.getId_comic());
                        bundle.putInt("SIZE", size);
                        Log.d("Test", position.getIndex());
                        intent.putExtras(bundle);
                        //intent.putExtras(bundle1);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                if (aLodingDialog != null && aLodingDialog.isShowing()) {
                    aLodingDialog.dismiss();
                }
            }
        });
    }
}