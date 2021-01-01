package com.example.trek.LoginSignUpFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.trek.MainScreenFragHolder;
import com.example.trek.R;
import com.google.firebase.auth.FirebaseAuth;


public class LoginSignUpFrag extends Fragment implements View.OnClickListener {
    Button button,button2;
    FirebaseAuth fAuth;
    public LoginSignUpFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login_sign_up, container, false);
        button = (Button) view.findViewById(R.id.signin_btn);
        button.setOnClickListener(this);
        button2 = (Button) view.findViewById(R.id.signup_btn);
        button2.setOnClickListener(this);



        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.signin_btn:
                Fragment login_frag = new LoginFrag();
                getFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,new LoginFrag()).addToBackStack("frags").commit();
                break;
            case R.id.signup_btn:
                Fragment signup_frag = new RegisterFrag01();
                getFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,new RegisterFrag01()).addToBackStack("frags").commit();
                break;
            default:
                break;
        }
    }
}
