package com.bootcamp.eceran.RecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bootcamp.eceran.FirebaseModel.ModelMenu;
import com.bootcamp.eceran.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapterAdmin extends RecyclerView.Adapter<RecyclerAdapterAdmin.ViewHolder> {
    List<ModelMenu> list;
    Context context;

    public RecyclerAdapterAdmin(List<ModelMenu> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapterAdmin.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.custome_menu_utama,parent,false);
        RecyclerAdapterAdmin.ViewHolder myHoder = new RecyclerAdapterAdmin.ViewHolder(view);

        return myHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        ModelMenu mylist = list.get(position);

        holder.Lokasi.setText(mylist.getLokasi());
        holder.Alamat.setText(mylist.getAlamat());
        Picasso.with(context).load(mylist.getImage())
                .fit()
                .centerCrop()
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, holder.view);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_long_click);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item
                                .getMenuInfo();

                        switch (item.getItemId()) {
                            case R.id.edit:
                                Toast.makeText(context, "Sedang Dalam Pengembangan\nSilahkan Tunggu Hingga Update Selanjutnya", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.delete:
                                Toast.makeText(context, "Sedang Dalam Pengembangan\nSilahkan Tunggu Hingga Update Selanjutnya", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                Toast.makeText(context.getApplicationContext(), "Data Tidak Ditemukan ", Toast.LENGTH_SHORT).show();
                                return false;
                        }
                    }
                });
                //displaying the popup
                popup.show();
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView Lokasi;
        TextView Alamat;
        ImageView imageView;
        CardView view;

        public ViewHolder(View itemView) {
            super(itemView);
            Lokasi = (TextView) itemView.findViewById(R.id.idLokasi);
            Alamat = (TextView) itemView.findViewById(R.id.idAlamat);
            imageView  = (ImageView) itemView.findViewById(R.id.idImage);
            view = (CardView) itemView.findViewById(R.id.view);
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

    RecyclerAdapterAdmin.OnItemClick onItemClick;

    public void setOnItemClick(RecyclerAdapterAdmin.OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void getPosition(int pos); //pass any things
    }
}
