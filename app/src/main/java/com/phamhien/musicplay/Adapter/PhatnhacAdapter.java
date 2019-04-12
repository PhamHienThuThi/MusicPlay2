package com.phamhien.musicplay.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phamhien.musicplay.Model.Baihat;
import com.phamhien.musicplay.R;

import java.util.ArrayList;

public class PhatnhacAdapter extends RecyclerView.Adapter<PhatnhacAdapter.ViewHolder> {
    Context context;
    ArrayList<Baihat> mangbaihat;

    public PhatnhacAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_phatnhac,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Baihat baihat = mangbaihat.get(position);
        holder.txtplay.setText(position +1 +"");
        holder.txtplayten.setText(baihat.getTenbaihat());
        holder.txtplaytencs.setText(baihat.getCasi());

    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder {
        TextView txtplay,txtplayten,txtplaytencs;
        public ViewHolder(View itemView) {
            super(itemView);
            txtplay = itemView.findViewById(R.id.textViewplaynhac);
            txtplayten  = itemView.findViewById(R.id.textViewplaytenbaihat);
            txtplaytencs = itemView.findViewById(R.id.textViewplaytencasi);
        }
    }
}
