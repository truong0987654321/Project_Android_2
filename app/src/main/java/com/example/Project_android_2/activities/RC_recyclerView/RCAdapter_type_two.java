package com.example.Project_android_2.activities.RC_recyclerView;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Project_android_2.R;

import java.util.ArrayList;

public class RCAdapter_type_two extends RecyclerView.Adapter<RCAdapter_type_two.RCViewHolder_type_two> {

    Context context;
    ArrayList<RCModel_type_two> modelArrtype_two;
    public RCAdapter_type_two (Context context,ArrayList<RCModel_type_two> modelArrtype_two){
        this.context = context;
        this.modelArrtype_two = modelArrtype_two;
    }
    @NonNull
    @Override
    public RCViewHolder_type_two onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rc_item_type_two,parent,false);
        return new RCViewHolder_type_two(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RCViewHolder_type_two holder, int position) {
        RCModel_type_two rcmodel = modelArrtype_two.get(position);
        holder.Name_comic.setText(rcmodel.getName_comic());
        holder.Name_author.setText(rcmodel.getName_author());
    }
    @Override
    public int getItemCount() {
        return modelArrtype_two.size();
    }
    public class RCViewHolder_type_two extends RecyclerView.ViewHolder{
        TextView Name_comic;
        TextView Name_author;
        public RCViewHolder_type_two (View itemView){
            super(itemView);
            Name_comic = itemView.findViewById(R.id.txt_name_comic);
            Name_author =itemView.findViewById(R.id.txt_name_author);
        }
    }
}