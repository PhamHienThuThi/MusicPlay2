package com.phamhien.musicplay.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phamhien.musicplay.Model.ChuDe;
import com.phamhien.musicplay.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DsChudeAdapter extends RecyclerView.Adapter<DsChudeAdapter.ViewHolder> {
    Context context;
    ArrayList<ChuDe> mangchude;

    public DsChudeAdapter(Context context, ArrayList<ChuDe> mangchude) {
        this.context = context;
        this.mangchude = mangchude;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_cac_chude,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ChuDe chuDe = mangchude.get(position);
        Picasso.with(context).load(chuDe.getHinhChuDe()).into(holder.imghinhanh);
        holder.txttenplaylist.setText(chuDe.getTenChuDe());
    }

    @Override
    public int getItemCount() {
        return mangchude.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imghinhanh;
        TextView txttenplaylist;
        public ViewHolder(View itemView) {
            super(itemView);
            imghinhanh = itemView.findViewById(R.id.imageViewdsplaylist);
            txttenplaylist = itemView.findViewById(R.id.textViewtendsplaylist);
        }
    }
}
