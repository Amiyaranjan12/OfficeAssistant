package com.example.officeassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import com.example.officeassistant.Admin.Admin_Home_Activity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Splash_Activity extends AppCompatActivity {


    private static final String TAG = "Splash_Activity";
    int SCREENTIME_OUT= 2500;

    ////
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;


    ///////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

       // Log.d(TAG, "Thread id:"+Thread.currentThread().getId());

     

      new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        keeploginFunction();
                    }
                }).start();
            }
        },SCREENTIME_OUT);





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
                        Intent intent=new Intent(Splash_Activity.this, Admin_Home_Activity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Intent intent=new Intent(Splash_Activity.this, EmployHome_Activity.class);
                        startActivity(intent);
                        finish();

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }


            });


        }

        else {
           Intent intent=new Intent(Splash_Activity.this,LoginActivity.class);
           startActivity(intent);
            finish();

        }

    }
}