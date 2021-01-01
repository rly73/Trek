package com.example.trek.LoginSignUpFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

import com.example.trek.R;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class RegisterFrag01 extends Fragment implements View.OnClickListener {
    public static final String TAG = "TAG";
    EditText FirstName, LastName, Email, Password, Username;
    Button CreateAccountBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    public RegisterFrag01() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register01, container, false);

        FirstName = view.findViewById(R.id.firstname_input);
        LastName = view.findViewById(R.id.lastname_input);
        Email = view.findViewById(R.id.email_input);
        Username = view.findViewById(R.id.username);
        Password = view.findViewById(R.id.password_input);

        CreateAccountBtn = (Button) view.findViewById(R.id.create_account_btn);

        CreateAccountBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        final String firstname = FirstName.getText().toString().trim();
        final String lastname = LastName.getText().toString().trim();
        final String email = Email.getText().toString().trim();
        final String username = Username.getText().toString().trim();
        final String password = Password.getText().toString().trim();
        Amplify.Auth.signUp(
                username,
                password,
                AuthSignUpOptions.builder().userAttribute(AuthUserAttributeKey.email(), email).build(),
                result -> Log.i("AuthQuickStart", "Result: " + result.toString()),
                error -> Log.e("AuthQuickStart", "Sign up failed", error)
        );
    }
}