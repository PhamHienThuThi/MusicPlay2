package com.phamhien.musicplay.Activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toolbar;

import com.phamhien.musicplay.Adapter.DsplaylistAdapter;
import com.phamhien.musicplay.Model.Playlist;
import com.phamhien.musicplay.R;
import com.phamhien.musicplay.Service.APIService;
import com.phamhien.musicplay.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachPlaylist extends AppCompatActivity {

   android.support.v7.widget.Toolbar toolbar;
    RecyclerView recyclerViewdsplaylist;
    DsplaylistAdapter dsplaylistAdapter;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach_playlist);
        anhxa();
        init();
        getData();
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Playlist>> callback = dataservice.GETdsplaylist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> mangplaylist = (ArrayList<Playlist>) response.body();
                dsplaylistAdapter = new DsplaylistAdapter(DanhsachPlaylist.this,mangplaylist);
                recyclerViewdsplaylist.setLayoutManager(new GridLayoutManager(DanhsachPlaylist.this,2));
                recyclerViewdsplaylist.setAdapter(dsplaylistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }
    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Play Lists");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }


    private void anhxa() {
        toolbar = findViewById(R.id.toolbardsplaylist);
        recyclerViewdsplaylist = findViewById(R.id.relative1dsplaylist);
    }
}
