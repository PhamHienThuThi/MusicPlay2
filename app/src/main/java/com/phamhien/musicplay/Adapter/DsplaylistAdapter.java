package com.phamhien.musicplay.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phamhien.musicplay.Activity.DanhsachPlaylist;
import com.phamhien.musicplay.Activity.DanhsachbaihatActivity;
import com.phamhien.musicplay.Model.Playlist;
import com.phamhien.musicplay.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DsplaylistAdapter extends RecyclerView.Adapter<DsplaylistAdapter.ViewHolder>  {

    Context context;
    ArrayList<Playlist> mangplaylist;

    public DsplaylistAdapter(Context context, ArrayList<Playlist> mangplaylist) {
        this.context = context;
        this.mangplaylist = mangplaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_dsplaylist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = mangplaylist.get(position);
        Picasso.with(context).load(playlist.getHinhAnh()).into(holder.imghinhanh);
        holder.txttenplaylist.setText(playlist.getTen());

    }

    @Override
    public int getItemCount() {
        return mangplaylist.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder {
        ImageView imghinhanh;
        TextView txttenplaylist;
        public ViewHolder(View itemView) {
            super(itemView);
            imghinhanh = itemView.findViewById(R.id.imageViewdsplaylist);
            txttenplaylist = itemView.findViewById(R.id.textViewtendsplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("itemplaylist",mangplaylist.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
