package com.bootcamp.eceran.PaketFragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bootcamp.eceran.Menu.MusicActivity;
import com.bootcamp.eceran.Paket_User.Login;
import com.bootcamp.eceran.R;

public class Lainnya extends Fragment {
    public Lainnya() {
        // Required empty public constructor
    }
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Pass = "passKey";
    public static final String Emaill = "emailKey";
    SharedPreferences sharedpreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lainnya, container, false);
        ImageView login = (ImageView) view.findViewById(R.id.KlikLogin);
        ImageView musik = (ImageView) view.findViewById(R.id.musik);

        musik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klikmusik(v);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kliklogin(v);
            }
        });
        return view;

    }
    public void kliklogin(View view) {
        Intent intent = new Intent(getContext(),Login.class);/*
        sharedpreferences = getS(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove(Emaill);
        editor.remove(Pass);
        editor.commit();*/
        startActivity(intent);
    }

    public void klikmusik(View view) {
        Intent intent = new Intent(getContext(),MusicActivity.class);
        startActivity(intent);
    }
}
