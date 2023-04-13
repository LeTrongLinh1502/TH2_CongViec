package com.example.th2_congviec.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.th2_congviec.R;
import com.example.th2_congviec.adapter.RecyclerViewAdapter;
import com.example.th2_congviec.database.SQLiteHelper;
import com.example.th2_congviec.model.CongViec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentSearch extends Fragment implements View.OnClickListener{
    private EditText editText;
    private Button btnSearch, btnStatistic;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private SQLiteHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        adapter=new RecyclerViewAdapter();
        db=new SQLiteHelper(getContext());
        List<CongViec> list=db.getAllCongViec();
        adapter.setList(list);
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        btnSearch.setOnClickListener(this);
        btnStatistic.setOnClickListener(this);
    }

    private void initView(View view) {
        editText = view.findViewById(R.id.editText);
        btnSearch = view.findViewById(R.id.btnSearch);
        btnStatistic = view.findViewById(R.id.btnGetStatistic);
        recyclerView = view.findViewById(R.id.recyclerViewFragmentSearch);
    }

    @Override
    public void onClick(View view) {
        if (view==btnStatistic){
            int chuathuchien=db.demTinhTrang1();
            int dangthuchien=db.demTinhTrang2();
            int hoanthanh=db.demTinhTrang3();
            String [] arr1 = new String[]{"Số công việc chưa thực hiện", "Số công việc đang thực hiện", "Số công việc hoàn thành"};
            int [] arr2 = new int[]{chuathuchien, dangthuchien, hoanthanh};
            for (int i = 0 ; i < arr2.length - 1; i++) {
                for (int j = i + 1; j < arr2.length; j++) {
                    if (arr2[i] < arr2[j]) {
                        int temp = arr2[j];
                        arr2[j] = arr2[i];
                        arr2[i] = temp;
                        String tmp = arr1[j];
                        arr1[j] = arr1[i];
                        arr1[i] = tmp;
                    }
                }
            }
            String ans=arr1[0]+" :"+arr2[0]+"\n"+arr1[1]+" :"+arr2[1]+"\n"+arr1[2]+" :"+arr2[2]+"\n";
            Toast.makeText(this.getContext().getApplicationContext(),ans,Toast.LENGTH_LONG).show();
        }
        if (view==btnSearch){;
            List<CongViec> congviecByNoiDung = db.searchByNoiDung(editText.getText().toString().trim().toLowerCase());
            adapter.setList(congviecByNoiDung);
        }
    }


}
