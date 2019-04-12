package com.phamhien.musicplay.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.phamhien.musicplay.Adapter.DsChudeAdapter;
import com.phamhien.musicplay.Adapter.DsplaylistAdapter;
import com.phamhien.musicplay.Model.ChuDe;
import com.phamhien.musicplay.Model.Playlist;
import com.phamhien.musicplay.R;
import com.phamhien.musicplay.Service.APIService;
import com.phamhien.musicplay.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachChudeActivity extends AppCompatActivity {

    RecyclerView recyclerViewchude;
    Toolbar toolbarchude;
    DsChudeAdapter dsChudeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_chude);
        init();
        getData();
    }

    private void getData() {
       Dataservice dataservice = APIService.getService();
       Call<List<ChuDe>> callback = dataservice.GETchude();
       callback.enqueue(new Callback<List<ChuDe>>() {
           @Override
           public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
               ArrayList<ChuDe> mangchude = (ArrayList<ChuDe>) response.body();
               dsChudeAdapter = new DsChudeAdapter(DanhSachChudeActivity.this,mangchude);
               recyclerViewchude.setLayoutManager(new GridLayoutManager(DanhSachChudeActivity.this,2));
               recyclerViewchude.setAdapter(dsChudeAdapter);
           }

           @Override
           public void onFailure(Call<List<ChuDe>> call, Throwable t) {

           }
       });
    }

    private void init() {
        recyclerViewchude = findViewById(R.id.relativechude);
        toolbarchude = findViewById(R.id.toolbarchude);
        setSupportActionBar(toolbarchude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbarchude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
