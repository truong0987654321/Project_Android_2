package com.example.Project_android_2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

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
import com.makeramen.roundedimageview.RoundedImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.util.ArrayList;
import java.util.Collections;

public class ViewComic extends AppCompatActivity {
    private AppCompatImageView imageViewRefund;
    private View viewLeft;
    private View viewRight;
    private TextView txt_Chapter;
    private TextView imageViewChapter;
    private DatabaseReference databaseReference;

    private Bundle bundle;

    private ALodingDialog aLodingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcomic);
        aLodingDialog = new ALodingDialog(this);
        bundle = getIntent().getExtras();
        String idcomic = bundle.getString("ID_COMIC");
        Long chapterIndex = Long.parseLong(bundle.getString("CHAPTER_INDEX"));
        setUI();
        databaseReference = FirebaseDatabase.getInstance().getReference("chapter");
        imageViewRefund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewComic.this, SeenComic.class);

                startActivity(intent);
            }
        });

        viewLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Left(idcomic, chapterIndex);
            }
        });
        viewRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Right(idcomic, chapterIndex);
            }
        });
    }

    public void setUI()
    {
        String idcomic = bundle.getString("ID_COMIC");
        Long chapterIndex = Long.parseLong(bundle.getString("CHAPTER_INDEX"));
        imageViewRefund = findViewById(R.id.refund_ring);
        viewLeft = findViewById(R.id.arrow_left_long);
        viewRight = findViewById(R.id.arrow_right);
        if (chapterIndex == 1)
            viewLeft.setVisibility(View.GONE);
        else if (chapterIndex == bundle.getInt("SIZE"))
            viewRight.setVisibility(View.GONE);
        txt_Chapter = findViewById(R.id.chap1);
        txt_Chapter.setText("Chap " + chapterIndex);
        imageViewChapter = findViewById(R.id.txtViewComic);
        ViewChapter(idcomic,chapterIndex);
    }

    public void ViewChapter(String idcomic, Long chap)
    {
        databaseReference = FirebaseDatabase.getInstance().getReference("chapter");
        Query query = databaseReference.orderByChild("ID_COMIC").equalTo(idcomic);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        Long index = dataSnapshot.child("CHAPTER_INDEX").getValue(Long.class);
                        if(index != null && index == chap)
                        {
                            String content = dataSnapshot.child("CONTENT").getValue(String.class);
                            Integer html = dataSnapshot.child("has_html").getValue(Integer.class);
                            if(html == 1)
                            {

                                Spanned spannedText = Html.fromHtml(content);
                                imageViewChapter.setText(spannedText);
                            }
                            else imageViewChapter.setText(content);
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void Left(String idcomic, Long Index) {

        Intent intent1 = new Intent(ViewComic.this, ViewComic.class);
        Query query = databaseReference.orderByChild("ID_COMIC").equalTo(idcomic);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Long index = dataSnapshot.child("CHAPTER_INDEX").getValue(Long.class);
                        if (index != null && index == (Index - 1)) {
                            // Lấy giá trị của CONTENT và TITLE
                            String content = dataSnapshot.child("CONTENT").getValue(String.class);
                            String title = dataSnapshot.child("TITLE").getValue(String.class);
                            bundle.putString("CONTENT", content);
                            bundle.putString("TITLE", title);
                            bundle.putString("CHAPTER_INDEX", index + "");
                            break;
                        }
                    }
                    intent1.putExtras(bundle);
                    startActivity(intent1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void Right(String idcomic, Long Index) {

        Intent intent1 = new Intent(ViewComic.this, ViewComic.class);
        Query query = databaseReference.orderByChild("ID_COMIC").equalTo(idcomic);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Long index = dataSnapshot.child("CHAPTER_INDEX").getValue(Long.class);
                        if (index != null && index == (Index + 1)) {
                            // Lấy giá trị của CONTENT và TITLE
                            String content = dataSnapshot.child("CONTENT").getValue(String.class);
                            String title = dataSnapshot.child("TITLE").getValue(String.class);
                            bundle.putString("CONTENT", content);
                            bundle.putString("TITLE", title);
                            bundle.putString("CHAPTER_INDEX", index + "");
                            break;
                        }
                    }
                    intent1.putExtras(bundle);
                    startActivity(intent1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}