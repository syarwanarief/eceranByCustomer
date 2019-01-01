package com.bootcamp.eceran.PaketFragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bootcamp.eceran.FirebaseModel.ModelLokasi;
import com.bootcamp.eceran.R;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class FragmentDetailLokasi extends Fragment {

    public FragmentDetailLokasi() {
        // Required empty public constructor
    }

    List<ModelLokasi> list;
    ProgressDialog dialog;
    RecyclerView recyclerView;
    View ve;
    DatabaseReference reference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_list_tempat, container, false);


        return view;
    }
}
