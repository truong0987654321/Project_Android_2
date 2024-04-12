package com.example.Project_android_2.activities.RC_recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Project_android_2.R;

import java.util.ArrayList;

public class RCAdapter_Trending extends RecyclerView.Adapter<RCAdapter_Trending.RCViewholder_story>  {
    Context context;
    ArrayList<RCModel_title_story> mListStory;
    public RCAdapter_Trending(Context context,ArrayList<RCModel_title_story> mlistStory){
        this.context = context;
        this.mListStory = mlistStory;
    }

    @NonNull
    @Override
    public RCViewholder_story onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rc_item_img_txt,parent,false);
        return new RCViewholder_story(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RCViewholder_story holder, int position) {
        RCModel_title_story mstory = mListStory.get(position);
        holder.rc_textview.setText(mstory.title);

    }

    @Override
    public int getItemCount() {
        return mListStory.size();
    }
    public class RCViewholder_story extends RecyclerView.ViewHolder {
        TextView rc_textview;

        public RCViewholder_story(@NonNull View itemView) {
            super(itemView);
            rc_textview = itemView.findViewById(R.id.rc_title_story);
        }
    }
}