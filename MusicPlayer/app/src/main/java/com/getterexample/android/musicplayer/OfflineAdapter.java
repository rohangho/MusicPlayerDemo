package com.getterexample.android.musicplayer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ROHAN on 09-02-2018.
 */

public class OfflineAdapter extends RecyclerView.Adapter<OfflineAdapter.ViewHolder> {

    Context context;
    ArrayList<String> name;

    public OfflineAdapter(ArrayList<String> name, Context ctx) {
        this.name = name;
        this.context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offlinelist, parent, false);
        ViewHolder detailview = new ViewHolder(view, context, name);
        return detailview;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String a=name.get(position);
        holder.title.setText(a);
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ArrayList<String> name=new ArrayList<String>();
        Context context;
        TextView title;
        public ViewHolder(View itemView, Context context, ArrayList<String> name) {
            super(itemView);
            this.context=context;
            this.name=name;

            title=(TextView)itemView.findViewById(R.id.name);
        }
    }
}
