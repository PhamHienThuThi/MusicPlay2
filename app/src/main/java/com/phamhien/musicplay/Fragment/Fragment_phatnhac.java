package com.phamhien.musicplay.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phamhien.musicplay.Activity.PhatnhacActivity;
import com.phamhien.musicplay.Adapter.PhatnhacAdapter;
import com.phamhien.musicplay.R;

public class Fragment_phatnhac extends Fragment {
    View view;

    RecyclerView recyclerViewphatnhac;
    PhatnhacAdapter phatnhacAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_phatnhac,container,false);
        recyclerViewphatnhac = view.findViewById(R.id.relative1phatnhac);
        if (PhatnhacActivity.baihatphatnhac.size() >0){
            phatnhacAdapter = new PhatnhacAdapter(getActivity(), PhatnhacActivity.baihatphatnhac);
            recyclerViewphatnhac.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewphatnhac.setAdapter(phatnhacAdapter);
        }

        return view ;
    }
}
