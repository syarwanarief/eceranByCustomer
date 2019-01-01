package com.bootcamp.eceran.MenuAdmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bootcamp.eceran.R;

public class PilihanMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihan_menu);
    }

    public void tambah(View view) {
        Intent intent = new Intent(PilihanMenu.this, Tambah.class);
        startActivity(intent);
    }

    public void Ubah(View view) {
        Intent intent = new Intent(PilihanMenu.this, EditHapus.class);
        startActivity(intent);
    }
}
