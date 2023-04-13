package com.example.th2_congviec.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th2_congviec.R;
import com.example.th2_congviec.UpdateDeleteActivity;
import com.example.th2_congviec.adapter.RecyclerViewAdapter;
import com.example.th2_congviec.database.SQLiteHelper;
import com.example.th2_congviec.model.CongViec;

import java.util.List;

public class FragmentList extends Fragment implements RecyclerViewAdapter.ItemListener {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private SQLiteHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recyclerView);
        adapter=new RecyclerViewAdapter();
        db=new SQLiteHelper(getContext());
        List<CongViec> list= db.getAllCongViec();
        adapter.setList(list);
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        List<CongViec> list= db.getAllCongViec();
        adapter=new RecyclerViewAdapter();
        adapter.setList(list);
    }

    @Override
    public void onItemClick(View view, int position) {
        CongViec congViec= adapter.getItem(position);
        Intent intent=new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("congviec",congViec);
        startActivity(intent);
    }


}
