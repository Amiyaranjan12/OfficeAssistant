package com.example.officeassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class About_privacyActivity extends AppCompatActivity {

    private ImageView Aboutpisback_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_privacylayout);

        Aboutpisback_btn=findViewById(R.id.Aboutpisback_btn);

        Aboutpisback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(About_privacyActivity.this, Employ_About_Activity.class);
                startActivity(intent);
            }
        });
    }
}