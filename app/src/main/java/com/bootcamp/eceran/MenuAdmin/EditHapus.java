package com.bootcamp.eceran.MenuAdmin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bootcamp.eceran.FirebaseModel.ModelMenu;
import com.bootcamp.eceran.R;
import com.bootcamp.eceran.RecyclerAdapter.RecyclerAdapterAdmin;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditHapus extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ModelMenu> list;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hapus);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
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

                    RecyclerAdapterAdmin adapter = new RecyclerAdapterAdmin(list, EditHapus.this);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(EditHapus.this, 1);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
