package com.phamhien.musicplay.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phamhien.musicplay.Activity.DsAlbumActivity;
import com.phamhien.musicplay.Adapter.AlbumAdapter;
import com.phamhien.musicplay.Model.Album;
import com.phamhien.musicplay.R;
import com.phamhien.musicplay.Service.APIRetrofitClient;
import com.phamhien.musicplay.Service.APIService;
import com.phamhien.musicplay.Service.Dataservice;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Album_Hot extends Fragment {
    View view;
    RecyclerView recyclerViewalbum;
    TextView txtxemthemalbum;
    AlbumAdapter albumAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_album_hot,container,false);
       getData();
       recyclerViewalbum = view.findViewById(R.id.recycleviewalbum);
       txtxemthemalbum = view.findViewById(R.id.textviewxemthemalbum);
       txtxemthemalbum.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getActivity(), DsAlbumActivity.class);
               startActivity(intent);
           }
       });
       return view;

    }

    private void getData() {

        Dataservice dataservice = APIService.getService();
        Call<List<Album>> callback = dataservice.GETAlbumHot();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList = (ArrayList<Album>) response.body();
               albumAdapter = new AlbumAdapter(getActivity(),albumArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewalbum.setLayoutManager(linearLayoutManager);
                recyclerViewalbum.setAdapter(albumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
}
