package com.bootcamp.eceran.RecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bootcamp.eceran.FirebaseModel.ModelMenu;
import com.bootcamp.eceran.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapterMenu extends RecyclerView.Adapter<RecyclerAdapterMenu.ViewHolder> {
    List<ModelMenu> list;
    Context context;

    public RecyclerAdapterMenu(List<ModelMenu> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapterMenu.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.custome_menu_utama,parent,false);
        RecyclerAdapterMenu.ViewHolder myHoder = new RecyclerAdapterMenu.ViewHolder(view);

        return myHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterMenu.ViewHolder holder, final int position) {
        ModelMenu mylist = list.get(position);

        holder.Lokasi.setText(mylist.getLokasi());
        holder.Alamat.setText(mylist.getAlamat());
        Picasso.with(context).load(mylist.getImage())
                .resize(200,100)
                .onlyScaleDown()
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView Lokasi;
        TextView Alamat;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            Lokasi = (TextView) itemView.findViewById(R.id.idLokasi);
            Alamat = (TextView) itemView.findViewById(R.id.idAlamat);
            imageView  = (ImageView) itemView.findViewById(R.id.idImage);
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

    RecyclerAdapterMenu.OnItemClick onItemClick;

    public void setOnItemClick(RecyclerAdapterMenu.OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void getPosition(int pos); //pass any things
    }
}
