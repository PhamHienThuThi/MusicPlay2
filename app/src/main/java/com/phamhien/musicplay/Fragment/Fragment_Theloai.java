package com.phamhien.musicplay.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.phamhien.musicplay.Activity.DanhSachChudeActivity;
import com.phamhien.musicplay.Adapter.ChudeAdapter;
import com.phamhien.musicplay.Adapter.PlaylistAdapter;
import com.phamhien.musicplay.Model.ChuDe;
import com.phamhien.musicplay.Model.Playlist;
import com.phamhien.musicplay.Model.TheLoai;
import com.phamhien.musicplay.R;
import com.phamhien.musicplay.Service.APIService;
import com.phamhien.musicplay.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Theloai extends Fragment {


    View view;
    ListView lvplaylist;
    TextView txttitleplaylist,txtxemthemplaylist;
    com.phamhien.musicplay.Adapter.ChudeAdapter chudeAdapter;
    ArrayList<ChuDe> mangchude;
    private Object ChudeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fregment_the_loai,container,false);
        lvplaylist = view.findViewById(R.id.listviewplaylist);
        txttitleplaylist = view.findViewById(R.id.textviewtitleplaylist);
        txtxemthemplaylist = view.findViewById(R.id.textviewmoreplaylist);
        txtxemthemplaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachChudeActivity.class);
                startActivity(intent);
            }
        });
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<ChuDe>> callback = dataservice.GETChudeCurrentDay();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                mangchude = (ArrayList<ChuDe>) response.body();
                chudeAdapter = new ChudeAdapter(getActivity(),android.R.layout.simple_list_item_1,mangchude);
                lvplaylist.setAdapter(chudeAdapter);
                setListViewHeightBasedOnChildren(lvplaylist);

            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });

    }


    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                //listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
