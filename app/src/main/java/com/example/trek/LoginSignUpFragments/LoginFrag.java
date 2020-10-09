package com.example.trek.LoginSignUpFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trek.MainScreenFragHolder;
import com.example.trek.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFrag extends Fragment implements View.OnClickListener {
private Button Button;
private EditText Email, Password;
private TextView Forgotpassword;
FirebaseAuth fAuth;

    public LoginFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Email = (EditText) view.findViewById(R.id.enter_email);
        Password = (EditText) view.findViewById(R.id.enter_password);
        Button = (Button) view.findViewById(R.id.signin_btn1);
        Forgotpassword = (TextView) view.findViewById(R.id.forgot_password);
        fAuth = FirebaseAuth.getInstance();

        Button.setOnClickListener(this);
        Forgotpassword.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.signin_btn1:
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Email.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Password.setError("Password is required");
                    return;
                }
                if(password.length() < 6){
                    Password.setError("Password needs to be longer than 6 characters");
                    return;
                }
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            getActivity().finish();
                            Toast.makeText(getActivity(),"Login Successful.",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(), MainScreenFragHolder.class));
                        }
                    }
                });
                break;
            case R.id.forgot_password:
                Fragment fragment = new PasswordResetFrag01();
                getFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,new PasswordResetFrag01()).addToBackStack("Frag").commit();
                break;
            default:
                break;
        }
    }
}