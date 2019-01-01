package com.bootcamp.eceran.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bootcamp.eceran.Paket_User.Login;
import com.bootcamp.eceran.R;

/**
 * Created by User on 07/12/2018.
 */

public class LainnyaActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lainnya);
    }

    public void kliklogin(View view) {
        Intent intent = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
    }

    public void klikmusik(View view) {
        Intent intent = new Intent(getApplicationContext(),MusicActivity.class);
        startActivity(intent);
    }
}
