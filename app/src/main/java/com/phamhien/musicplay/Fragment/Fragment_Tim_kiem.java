package com.phamhien.musicplay.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phamhien.musicplay.Adapter.SearchBaiHatAdapter;
import com.phamhien.musicplay.Model.Baihat;
import com.phamhien.musicplay.R;
import com.phamhien.musicplay.Service.APIService;
import com.phamhien.musicplay.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tim_kiem extends Fragment{
    View view;
    Toolbar toolbartk;
    RecyclerView recyclerViewtkbaihat;
    TextView txtkhongcodl;

    SearchBaiHatAdapter searchBaiHatAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_tim_kiem,container,false);
       toolbartk = view.findViewById(R.id.toolbartimkiembaihat);
       recyclerViewtkbaihat = view.findViewById(R.id.relative1timkiembaihat);
       txtkhongcodl = view.findViewById(R.id.textViewkhongcodl);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbartk);
        toolbartk.setTitle("");
        setHasOptionsMenu(true);
        return view ;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view,menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchTukhoaTimkiem(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
    private void SearchTukhoaTimkiem(String query){
        Dataservice dataservice= APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetSearchbaihat(query);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                ArrayList<Baihat> mangbaihat = (ArrayList<Baihat>) response.body();
                if (mangbaihat.size() > 0){
                    searchBaiHatAdapter = new SearchBaiHatAdapter(getActivity(),mangbaihat);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewtkbaihat.setLayoutManager(linearLayoutManager);
                    recyclerViewtkbaihat.setAdapter(searchBaiHatAdapter);
                    txtkhongcodl.setVisibility(View.GONE);
                    recyclerViewtkbaihat.setVisibility(View.VISIBLE);

                }else {
                    recyclerViewtkbaihat.setVisibility(View.GONE);
                    txtkhongcodl.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
}
