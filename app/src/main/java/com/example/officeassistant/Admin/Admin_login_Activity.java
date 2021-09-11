package com.example.officeassistant.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.officeassistant.EmployHomeProfile;
import com.example.officeassistant.EmployHome_Activity;
import com.example.officeassistant.LoginActivity;
import com.example.officeassistant.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin_login_Activity extends AppCompatActivity {

    private EditText adminLogin_emailTxt,adminLogin_password;
    private TextView admin_loginBtn,admin_forget_passbtn;

    private RelativeLayout EmployBackLog_btn;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login_layout);

        adminLogin_emailTxt=findViewById(R.id.adminLogin_emailTxt);
        adminLogin_password=findViewById(R.id.adminLogin_password);
        admin_loginBtn=findViewById(R.id.admin_loginBtn);
        admin_forget_passbtn=findViewById(R.id.admin_forget_passbtn);
        EmployBackLog_btn=findViewById(R.id.EmployBackLog_btn);


        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();


        EmployBackLog_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_login_Activity.this,LoginActivity.class);
                startActivity(intent);

            }
        });


        admin_forget_passbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_login_Activity.this,Admin_forgetPass_Activity.class);
                startActivity(intent);
            }
        });

        admin_loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  new Thread(new Runnable() {
                    @Override
                    public void run() {

                    }
                }).start();

               */

                adminLogin_function();

            }
        });
    }

    //admin Login
    private void adminLogin_function() {


        String email=adminLogin_emailTxt.getText().toString();
        String  password=adminLogin_password.getText().toString();

        if(TextUtils.isEmpty(email)){
            adminLogin_emailTxt.setError("PLEASE ENTER YOUR EMAIL");
            return;
        }
        else if(TextUtils.isEmpty(password)){
            adminLogin_password.setError("PLEASE ENTER YOUR PASSWORD");
            return;
        }



        ProgressDialog progressDialog =new ProgressDialog(this);

        progressDialog.setMessage("processing......");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);



        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            String uid=user.getUid();

                            ///////
                            firebaseDatabase=FirebaseDatabase.getInstance();
                            firebaseDatabase.getReference().child(uid).child("admin").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    int admin=snapshot.getValue(Integer.class);
                                    if (admin ==1){
                                        Intent intent=new Intent(Admin_login_Activity.this,Admin_Home_Activity.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        mAuth.getInstance().signOut();
                                        Toast.makeText(Admin_login_Activity.this, "Only Admin Can Login Here",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                    progressDialog.dismiss();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(Admin_login_Activity.this, "Check your email and Password",
                                            Toast.LENGTH_SHORT).show();
                                }

                            });

                            /////////

                        } else {

                            // If sign in fails, display a message to the user.
                            Toast.makeText(Admin_login_Activity.this, "Check your email and Password",
                                    Toast.LENGTH_SHORT).show();

                        }
                      progressDialog.dismiss();


                    }

                });


    }


    //check admin or not

    @Override
    public void onBackPressed() {

        Intent intent=new Intent(Admin_login_Activity.this,LoginActivity.class);
        startActivity(intent);
    }


    ///
}