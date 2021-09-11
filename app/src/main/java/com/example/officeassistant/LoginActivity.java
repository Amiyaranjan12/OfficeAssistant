package com.example.officeassistant;

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

import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.officeassistant.Admin.Admin_Home_Activity;
import com.example.officeassistant.Admin.Admin_login_Activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmployEmail,editTextTextPassword;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private static final String TAG = "LoginActivity";
    private ProgressDialog progressDialog;
    private TextView forget_View, EmployLog_button;

    private RelativeLayout admin_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ///////////// keep login


           //     keeploginFunction();




        /////////////
      /*  mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=mAuth.getCurrentUser();

        if (firebaseUser!= null) {
            startActivity(new Intent(LoginActivity.this,EmployHome_Activity.class));
            finish();
        }
        */

        /////////
        setContentView(R.layout.activity_login);

        editTextEmployEmail=(EditText) findViewById(R.id.editTextEmployEmail);
        editTextTextPassword=(EditText) findViewById(R.id.editTextTextPassword);
        EmployLog_button=(TextView) findViewById(R.id.EmployLog_button);
        forget_View=(TextView) findViewById(R.id.forget_View);
        admin_btn=findViewById(R.id.admin_btn);

        ///btn

        admin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, Admin_login_Activity.class);
                startActivity(intent);
            }
        });

        ///




        forget_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,Forget_employ_Activity.class);
                startActivity(intent);
            }
        });



        //Firebase login code
        mAuth = FirebaseAuth.getInstance();

        EmployLog_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                employLogin_function();
            }
        });


    }

    private void keeploginFunction() {

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        firebaseDatabase= FirebaseDatabase.getInstance();
        if (user!= null) {

            firebaseDatabase.getReference().child(user.getUid()).child("admin").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int admin=snapshot.getValue(Integer.class);
                    if (admin ==1){
                        Intent intent=new Intent(LoginActivity.this, Admin_Home_Activity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Intent intent=new Intent(LoginActivity.this, EmployHome_Activity.class);
                        startActivity(intent);
                        finish();

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }


            });


        }
    }

    //firebase log in
  /*  private void EmployLogin() {



        String email=editTextEmployEmail.getText().toString();
        String  password=editTextTextPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            editTextEmployEmail.setError("PLEASE ENTER YOUR EMAIL");
            return;
        }
        else if(TextUtils.isEmpty(password)){
            editTextTextPassword.setError("PLEASE ENTER YOUR PASSWORD");
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
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            //888


                            Intent intent=new Intent(LoginActivity.this,EmployHome_Activity.class);
                            intent.putExtra("email",email);
                            startActivity(intent);
                            //99
                        } else {

                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);

                        }
                     progressDialog.dismiss();


                    }

                });
    }



   */

    ////
    private void employLogin_function() {


        String email=editTextEmployEmail.getText().toString();
        String  password=editTextTextPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            editTextEmployEmail.setError("PLEASE ENTER YOUR EMAIL");
            return;
        }
        else if(TextUtils.isEmpty(password)){
            editTextTextPassword.setError("PLEASE ENTER YOUR PASSWORD");
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

                            ///////////////////////
                           firebaseDatabase.getReference().addValueEventListener(new ValueEventListener() {
                              @Override
                              public void onDataChange(@NonNull DataSnapshot snapshot) {
                                  if (snapshot.child(uid).exists()){


                                      /////

                                      firebaseDatabase.getReference().child(uid).child("admin").addListenerForSingleValueEvent(new ValueEventListener() {
                                          @Override
                                          public void onDataChange(@NonNull DataSnapshot snapshot) {
                                              int admin=snapshot.getValue(Integer.class);
                                              if (admin ==0){

                                                  Intent intent=new Intent(LoginActivity.this,EmployHome_Activity.class);
                                                  startActivity(intent);


                                              }
                                              else{

                                                  mAuth.getInstance().signOut();
                                                  Toast.makeText(LoginActivity.this, "Only Employees Can Login Here",
                                                          Toast.LENGTH_SHORT).show();

                                              }
                                              progressDialog.dismiss();
                                          }

                                          @Override
                                          public void onCancelled(@NonNull DatabaseError error) {
                                              Toast.makeText(LoginActivity.this, "Check your email and Password",
                                                      Toast.LENGTH_SHORT).show();
                                          }

                                      });

                                      //
                                  }
                                  if (!snapshot.child(uid).exists()){

                                      mAuth.getInstance().signOut();
                                      progressDialog.dismiss();
                                      Toast.makeText(LoginActivity.this, "Invalid id",
                                              Toast.LENGTH_SHORT).show();


                                  }
                              }

                              @Override
                              public void onCancelled(@NonNull DatabaseError error) {

                              }
                           });
                            //////////////////////////////

                            /////////

                        } else {

                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Check your email and Password",
                                    Toast.LENGTH_SHORT).show();

                        }
                        progressDialog.dismiss();


                    }

                });


    }

    //////


    @Override
    public void onBackPressed() {
        finish();
        moveTaskToBack(true);
    }
}