package com.example.trek.LoginSignUpFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trek.MainScreenFragHolder;
import com.example.trek.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class RegisterFrag01 extends Fragment implements View.OnClickListener {
    public static final String TAG = "TAG";
    EditText FirstName, LastName, Email, Password;
Button CreateAccountBtn;
FirebaseAuth fAuth;
FirebaseFirestore fStore;
String userID;
    public RegisterFrag01() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register01, container, false);

        FirstName = view.findViewById(R.id.firstname_input);
        LastName = view.findViewById(R.id.lastname_input);
        Email = view.findViewById(R.id.email_input);
        Password = view.findViewById(R.id.password_input);
        CreateAccountBtn = (Button) view.findViewById(R.id.create_account_btn);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        CreateAccountBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        final String firstname = FirstName.getText().toString().trim();
        final String lastname = LastName.getText().toString().trim();
        final String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();

        if(TextUtils.isEmpty(firstname)){
            Email.setError("First Name is required");
        }

        if(TextUtils.isEmpty(lastname)){
            LastName.setError("Last Name is required");
        }

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
        fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    userID = fAuth.getCurrentUser().getUid();
                    getActivity().finish();
                    Toast.makeText(getActivity(),"User Created.",Toast.LENGTH_SHORT).show();
                    DocumentReference documentReference = fStore.collection("users").document(userID);
                    Map<String,Object> user = new HashMap<>();
                    user.put("firstName",firstname);
                    user.put("lastName",lastname);
                    user.put("email", email);
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "onSuccess: user Profile is created for " + userID);
                        }
                    });
                    startActivity(new Intent(getContext(), MainScreenFragHolder.class));
                }
                else{
                    Toast.makeText(getActivity(),"Error ! " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}