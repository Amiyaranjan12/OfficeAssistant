package com.example.officeassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Employ_About_Activity extends AppCompatActivity {

    private RelativeLayout about_privacyBtn,about_disclaimerBtn,about_tcBtn;
    private ImageView Aboutback_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employ_about);

        about_disclaimerBtn=findViewById(R.id.about_disclaimerBtn);
        about_privacyBtn=findViewById(R.id.about_privacyBtn);
        about_tcBtn=findViewById(R.id.about_tcBtn);
        Aboutback_btn=findViewById(R.id.Aboutback_btn);



        Aboutback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Employ_About_Activity.this,EmployHome_Activity.class);
                startActivity(intent);
            }
        });

        about_disclaimerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Employ_About_Activity.this,Employe_aboutDisclaimerActivity.class);
                startActivity(intent);

            }
        });


        about_privacyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Employ_About_Activity.this,About_privacyActivity.class);
                startActivity(intent);

            }
        });





        about_tcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Employ_About_Activity.this,About_TC_Activity.class);
                startActivity(intent);



            }
        });





    }
}