package com.example.officeassistant.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.officeassistant.Forget_employ_Activity;
import com.example.officeassistant.LoginActivity;
import com.example.officeassistant.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Admin_forgetPass_Activity extends AppCompatActivity {

    private EditText editTextForgetadmin;
    private TextView Forget_adminbtn,Forget_adminback;
    private FirebaseAuth FAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_forgetpass_layout);

        editTextForgetadmin=findViewById(R.id.editTextForgetadmin);
        Forget_adminbtn=findViewById(R.id.Forget_adminbtn);
        Forget_adminback=findViewById(R.id.Forget_adminback);

        FAuth = FirebaseAuth.getInstance();

        Forget_adminbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgetPasswordadmin();
            }
        });

        ////

        Forget_adminback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_forgetPass_Activity.this, Admin_login_Activity.class);
                startActivity(intent);

            }
        });
    }

    private void forgetPasswordadmin() {

        String email=editTextForgetadmin.getText().toString();

        if(TextUtils.isEmpty(email)){
            editTextForgetadmin.setError("PLEASE ENTER YOUR EMAIL");
            return;

        }

        FAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){

                    Toast.makeText(Admin_forgetPass_Activity.this,"PLEASE CHECK YOUR MAILBOX",Toast.LENGTH_LONG).show();
                }
                else {

                    Toast.makeText(Admin_forgetPass_Activity.this,"PLEASE ENTER VALID EMAIL ADDRESS",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}