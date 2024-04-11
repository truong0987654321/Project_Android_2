package com.example.Project_android_2.activities.RC_recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Project_android_2.R;
import com.example.Project_android_2.activities.Search_comic;

import java.util.ArrayList;

public class RCAdapter extends RecyclerView.Adapter<RCAdapter.RCViewHolder> {
    Context context;
    ArrayList<RCModel> modelArrayList;
    public RCAdapter(Context context, ArrayList<RCModel> modelArrayList){
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    public RCAdapter(Search_comic context, ArrayList<RCModel_type_two> modelArray) {
    }

    @NonNull
    @Override
    public RCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rc_item,parent,false);
        return new RCViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RCViewHolder holder, int position) {
        RCModel rcModel = modelArrayList.get(position);
        holder.rc_title.setText(rcModel.title);
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class RCViewHolder extends RecyclerView.ViewHolder {
        TextView rc_title;
        public RCViewHolder(@NonNull View itemView){
            super(itemView);
            rc_title = itemView.findViewById(R.id.rc_title);
        }
    }
}