package com.phamhien.musicplay.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phamhien.musicplay.Activity.DanhsachbaihatActivity;
import com.phamhien.musicplay.Model.Album;
import com.phamhien.musicplay.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DsAlbumAdapter extends RecyclerView.Adapter<DsAlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> mangalbum;

    public DsAlbumAdapter(Context context, ArrayList<Album> mangalbum) {
        this.context = context;
        this.mangalbum = mangalbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_allalbum,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Album album = mangalbum.get(position);
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgalbum);
        holder.txttenalbum.setText(album.getTenAlbum());
    }

    @Override
    public int getItemCount() {
        return mangalbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgalbum;
        TextView txttenalbum;
        public ViewHolder(View itemView) {
            super(itemView);
            imgalbum = itemView.findViewById(R.id.imageViewdsalbum);
            txttenalbum = itemView.findViewById(R.id.textViewtendsalbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("dsalbum",mangalbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
