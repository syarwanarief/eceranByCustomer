package com.bootcamp.eceran.Menu;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

public class DetailTempatActivity extends AppCompatActivity {

    List<ModelMenu> list;
    ProgressDialog dialog;
    RecyclerView recyclerView;
    View ve;
    DatabaseReference reference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tempat);

        dialog = new ProgressDialog(this);

        dialog.setMessage("Memuat Data...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setIndeterminate(true);
        dialog.show();

        Bundle extras = getIntent().getExtras();
        String child = extras.getString("key");
        recyclerView = (RecyclerView) findViewById(R.id.idAja);
        reference = FirebaseDatabase.getInstance().getReference().child("Eceran").child("List Tempat").child(child);
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

                    RecyclerAdapterMenu adapter = new RecyclerAdapterMenu(list, DetailTempatActivity.this);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(DetailTempatActivity.this, 1);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                    dialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
