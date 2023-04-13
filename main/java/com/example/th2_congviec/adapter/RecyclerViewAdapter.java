package com.example.th2_congviec.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th2_congviec.R;
import com.example.th2_congviec.model.CongViec;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.HomeViewHolder> {
    private List<CongViec> list;
    private ItemListener itemListener;
    public RecyclerViewAdapter() {
        list=new ArrayList<>();
    }

    public void setList(List<CongViec> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public CongViec getItem(int position){
        return list.get(position);

    }
    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view,parent,false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        CongViec congViec= list.get(position);
        holder.tvTen.setText(congViec.getTen());
        holder.tvNoiDung.setText(congViec.getNoidung());
        holder.tvNgay.setText(congViec.getNgayHT());
        holder.tvTinhTrang.setText(congViec.getTinhtrang());
        holder.tvCongTac.setText(congViec.getCongtac());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        private TextView tvTen, tvNoiDung, tvNgay, tvTinhTrang, tvCongTac;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen = itemView.findViewById(R.id.tvTen);
            tvNoiDung = itemView.findViewById(R.id.tvNoiDung);
            tvNgay = itemView.findViewById(R.id.tvNgay);
            tvTinhTrang = itemView.findViewById(R.id.tvTinhTrang);
            tvCongTac = itemView.findViewById(R.id.tvCongTac);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemListener!=null){
                itemListener.onItemClick(view,getAdapterPosition());
            }
        }
    }
    public interface ItemListener{
        void onItemClick (View view,int position);
    }
}
