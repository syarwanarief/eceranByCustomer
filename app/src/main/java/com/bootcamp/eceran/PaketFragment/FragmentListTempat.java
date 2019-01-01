package com.bootcamp.eceran.PaketFragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bootcamp.eceran.Menu.DetailTempatActivity;
import com.bootcamp.eceran.FirebaseModel.ModelLokasi;
import com.bootcamp.eceran.MenuUtama.MenuAwal;
import com.bootcamp.eceran.R;
import com.bootcamp.eceran.RecyclerAdapter.RecyclerAdapterLokasi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentListTempat extends Fragment {
    public FragmentListTempat() {
        // Required empty public constructor
    }

    List<ModelLokasi> list;
    ProgressDialog dialog;
    RecyclerView recyclerView;
    View ve;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_list_tempat, container, false);
        Intent intent = new Intent(getActivity(),MenuAwal.class);
        intent.putExtra("key","null");
        intent.putExtra("keyChild","null");

        recyclerView = (RecyclerView) view.findViewById(R.id.listLokasi);
        reference = FirebaseDatabase.getInstance().getReference().child("Daftar Lokasi");
        reference.keepSynced(true);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list = new ArrayList<ModelLokasi>();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ModelLokasi model = new ModelLokasi();
                    ModelLokasi value = dataSnapshot1.getValue(ModelLokasi.class);
                    String vlokasi = value.getLokasi();
                    model.setLokasi(vlokasi);
                    list.add(model);

                    RecyclerAdapterLokasi adapter = new RecyclerAdapterLokasi(list, getActivity());
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                    dialog.dismiss();

                    adapter.setOnItemClick(new RecyclerAdapterLokasi.OnItemClick() {
                        @Override
                        public void getPosition(int pos) {
                            switch (pos){
                                case 0:{

                                    Intent intent = new Intent(getActivity(),DetailTempatActivity.class);
                                    intent.putExtra("key","Kedaton");
                                    startActivity(intent);
                                    break;

                                }case 1:{
                                    Intent intent1 = new Intent(getActivity(),DetailTempatActivity.class);
                                    intent1.putExtra("key","Rajabasa");
                                    startActivity(intent1);
                                    break;

                                }case 2:{
                                    Intent intent2 = new Intent(getActivity(),DetailTempatActivity.class);
                                    intent2.putExtra("key","Teluk");
                                    startActivity(intent2);
                                    break;

                                }case 3:{
                                    Intent intent3 = new Intent(getActivity(),DetailTempatActivity.class);
                                    intent3.putExtra("key","Panjang");
                                    startActivity(intent3);
                                    break;

                                }case 4:{
                                    Intent intent4 = new Intent(getActivity(),DetailTempatActivity.class);
                                    intent4.putExtra("key","Sukarame");
                                    startActivity(intent4);
                                    break;
                                }
                                default:
                            }
                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.ve = view;

        dialog = new ProgressDialog(getActivity());

        dialog.setMessage("Memuat Data \nMohon Tunggu...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setIndeterminate(true);
        dialog.show();
    }
}
