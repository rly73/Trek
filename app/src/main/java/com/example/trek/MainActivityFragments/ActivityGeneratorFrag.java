package com.example.trek.MainActivityFragments;


import android.app.Dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.example.trek.ImageAdapter;
import com.example.trek.R;


public class ActivityGeneratorFrag extends Fragment implements View.OnClickListener {
private ImageView filter, settings;
private ImageButton generatebtn;
private Dialog filterDialog;

    public ActivityGeneratorFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_activity_generator, container, false);
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        ImageAdapter adapter = new ImageAdapter(getActivity());
        viewPager.setAdapter(adapter);

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        filter = (ImageView) view.findViewById(R.id.filter);
        settings = (ImageView) view.findViewById(R.id.settings);
        generatebtn = (ImageButton) view.findViewById(R.id.generate_btn);

        filterDialog = new Dialog(getActivity());

        filter.setOnClickListener(this);
        settings.setOnClickListener(this);
        generatebtn.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.filter:
                filterDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                filterDialog.setContentView(R.layout.filter_popup);
                filterDialog.show();
                break;
            case R.id.settings:
                System.out.println("Say Setting");
                break;
            case R.id.generate_btn:
                System.out.println("Generate");
                break;
            default:
                break;
        }
    }
}