package com.bootcamp.eceran.Menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.bootcamp.eceran.MenuUtama.MenuAwal;
import com.bootcamp.eceran.Paket_User.Login;
import com.bootcamp.eceran.R;

public class SplashActivity extends AppCompatActivity {

    public static int SPLASH_TIME_OUT = 2300;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Pass = "passKey";
    public static final String Emaill = "emailKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Emaill) && sharedpreferences.contains(Pass)) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Intent homeIntent = new Intent(SplashActivity.this, MenuAwal.class);
                    startActivity(homeIntent);
                    finish();
                }
            },SPLASH_TIME_OUT);

        }else {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Intent homeIntent = new Intent(SplashActivity.this, Login.class);
                    startActivity(homeIntent);
                    finish();
                }
            },SPLASH_TIME_OUT);
        }
    }
}

