package com.getterexample.android.musicplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
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
    ArrayList<String> path;
    public static int a=0;

    public OfflineAdapter(ArrayList<String> name, Context ctx,ArrayList<String> path) {
        this.name = name;
        this.context = ctx;
        this.path=path;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offlinelist, parent, false);
        ViewHolder detailview = new ViewHolder(view, context, name,path);
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MediaPlayer  player=new MediaPlayer();;
        ArrayList<String> name=new ArrayList<String>();
        ArrayList<String> path=new ArrayList<String>();
        Context context;
        TextView title;
        public ViewHolder(View itemView, Context context, ArrayList<String> name, ArrayList<String> path) {
            super(itemView);
            this.context=context;
            this.name=name;
            this.path=path;

            title=(TextView)itemView.findViewById(R.id.name);
            title.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                a = getAdapterPosition();
                if(player==null)
                {
                    player=MediaPlayer.create(context, Uri.parse(path.get(a)));
                    player.start();
                }
                else
                {
                    player.stop();
                    player= MediaPlayer.create(context, Uri.parse(path.get(a)));
                    player.start();
                }
                //Offline off = new Offline();
               // off.play(a);


        }
    }
}
