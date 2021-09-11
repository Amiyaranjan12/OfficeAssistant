package com.example.officeassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forget_employ_Activity extends AppCompatActivity {

    private EditText editTextForgetEmploy;
    private TextView Forget_Employbtn,Forget_Employback;
    FirebaseAuth FAuth;
    private static final String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_employ);

        editTextForgetEmploy=findViewById(R.id.editTextForgetEmploy);
        Forget_Employbtn=findViewById(R.id.Forget_Employbtn);
        Forget_Employback=findViewById(R.id.Forget_Employback);




        FAuth = FirebaseAuth.getInstance();

        Forget_Employbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgetPasswordEmploy();
            }
        });

        ////

       Forget_Employback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Forget_employ_Activity.this,LoginActivity.class);
                startActivity(intent);

            }
        });

    }

    private void forgetPasswordEmploy() {

        String email=editTextForgetEmploy.getText().toString();

        if(TextUtils.isEmpty(email)){
            editTextForgetEmploy.setError("PLEASE ENTER YOUR EMAIL");
           return;

        }

        FAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){

                    Toast.makeText(Forget_employ_Activity.this,"PLEASE CHECK YOUR MAILBOX",Toast.LENGTH_LONG).show();
                }
                else {

                    Toast.makeText(Forget_employ_Activity.this,"PLEASE ENTER VALID EMAIL ADDRESS",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}