package com.example.officeassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Employe_aboutDisclaimerActivity extends AppCompatActivity {


    private ImageView Aboutdisback_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employe_aboutdisclaimer_layout);

        Aboutdisback_btn = findViewById(R.id.Aboutdisback_btn);

        AboutBackfunction();


    }


    private void AboutBackfunction() {
        Aboutdisback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Employe_aboutDisclaimerActivity.this, Employ_About_Activity.class);
                startActivity(intent);
            }
        });

    }
}