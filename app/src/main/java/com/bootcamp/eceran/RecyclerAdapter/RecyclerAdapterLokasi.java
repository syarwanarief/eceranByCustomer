package com.bootcamp.eceran.RecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bootcamp.eceran.FirebaseModel.ModelLokasi;
import com.bootcamp.eceran.R;

import java.util.List;

public class RecyclerAdapterLokasi extends RecyclerView.Adapter<RecyclerAdapterLokasi.ViewHolder> {
    List<ModelLokasi> list;
    Context context;

    public RecyclerAdapterLokasi(List<ModelLokasi> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapterLokasi.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.custome_list_lokasi,parent,false);
        RecyclerAdapterLokasi.ViewHolder myHoder = new RecyclerAdapterLokasi.ViewHolder(view);

        return myHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterLokasi.ViewHolder holder, final int position) {
        ModelLokasi mylist = list.get(position);

        holder.Lokasi.setText(mylist.getLokasi());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.getPosition(position);
            }
        });

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView Lokasi;

        public ViewHolder(View itemView) {
            super(itemView);
            Lokasi = (TextView) itemView.findViewById(R.id.lokasi);
        }
    }

    @Override
    public int getItemCount() {
        int arr = 0;

        try{
            if(list.size()==0){
                arr = 0;
            }
            else{

                arr=list.size();
            }
        }catch (Exception e){
        }
        return arr;
    }

    RecyclerAdapterLokasi.OnItemClick onItemClick;

    public void setOnItemClick(RecyclerAdapterLokasi.OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void getPosition(int pos); //pass any things
    }
}
