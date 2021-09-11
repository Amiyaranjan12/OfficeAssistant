package com.example.officeassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class About_TC_Activity extends AppCompatActivity {


    ImageView Abouttcback_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about__tc_layout);

        Abouttcback_btn=findViewById(R.id.Abouttcback_btn);

        Abouttcback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(About_TC_Activity.this,Employ_About_Activity.class);
                startActivity(intent);
            }
        });
    }
}