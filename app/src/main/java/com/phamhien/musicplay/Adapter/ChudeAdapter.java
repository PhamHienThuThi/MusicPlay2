package com.phamhien.musicplay.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phamhien.musicplay.Model.ChuDe;
import com.phamhien.musicplay.Model.Playlist;
import com.phamhien.musicplay.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChudeAdapter extends ArrayAdapter<ChuDe> {

    public ChudeAdapter(@NonNull Context context, int resource, @NonNull List<ChuDe> objects) {
        super(context, resource, objects);
    }

    class ViewHolder{
        TextView txttenplaylist;
        ImageView imgbackground,imgplaylist;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ChudeAdapter.ViewHolder viewHolder = null;
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dong_playlist,null);
            viewHolder = new ViewHolder();
            viewHolder.txttenplaylist = convertView.findViewById(R.id.textviewtenplaylist);
            viewHolder.imgplaylist = convertView.findViewById(R.id.imageviewplaylist);
            viewHolder.imgbackground = convertView.findViewById(R.id.imageviewbackgroundplaylist);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ChudeAdapter.ViewHolder) convertView.getTag();
        }
        ChuDe chuDe = getItem(position);
        Picasso.with(getContext()).load(chuDe.getHinhChuDe()).into(viewHolder.imgbackground);
        viewHolder.txttenplaylist.setText(chuDe.getTenChuDe());

        return convertView ;
    }
}
