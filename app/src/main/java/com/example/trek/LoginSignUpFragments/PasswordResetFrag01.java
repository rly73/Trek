package com.example.trek.LoginSignUpFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trek.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class PasswordResetFrag01 extends Fragment implements View.OnClickListener {
private Button button;
private EditText Email;
private FirebaseAuth fAuth;

    public PasswordResetFrag01() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_password_reset_frag01, container, false);
        button = (Button) view.findViewById(R.id.send_otp_btn);
        Email = (EditText) view.findViewById(R.id.enter_email_otp);
        button.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.send_otp_btn:

                break;
            default:
                break;
        }
    }
}