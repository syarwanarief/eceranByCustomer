package com.bootcamp.eceran.PaketFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bootcamp.eceran.FirebaseModel.ModelMenu;
import com.bootcamp.eceran.R;
import com.bootcamp.eceran.RecyclerAdapter.RecyclerAdapterMenu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 */
public class MenuUtama extends Fragment {
    public MenuUtama() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;
    List<ModelMenu> list;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_utama, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerMenu);
        reference = FirebaseDatabase.getInstance().getReference().child("Eceran").child("Daftar Eceran");
        reference.keepSynced(true);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list = new ArrayList<ModelMenu>();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ModelMenu model = new ModelMenu();
                    ModelMenu value = dataSnapshot1.getValue(ModelMenu.class);
                    String vlokasi = value.getLokasi();
                    String valamat = value.getAlamat();
                    String vImage = value.getImage();
                    model.setLokasi(vlokasi);
                    model.setAlamat(valamat);
                    model.setImage(vImage);
                    list.add(model);

                    RecyclerAdapterMenu adapter = new RecyclerAdapterMenu(list, getActivity());
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;

    }
}
