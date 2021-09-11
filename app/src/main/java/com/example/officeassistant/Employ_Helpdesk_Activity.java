package com.example.officeassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Employ_Helpdesk_Activity extends AppCompatActivity {

    private ImageView helpback_btn;
    private TextView helptxt_mail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employ__helpdesk_);

        helpback_btn=findViewById(R.id.helpback_btn);
        helptxt_mail=findViewById(R.id.helptxt_mail);

        helpBackfunction();

        helptxt_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendmailFunction();
            }
        });




    }

    private void helpBackfunction() {
        helpback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Employ_Helpdesk_Activity.this,EmployHome_Activity.class);
                startActivity(intent);
            }
        });


    }


    private void  sendmailFunction(){

        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri emailSend = Uri.parse("mailto:officeassistant632@gmail.com");
        intent.setData(emailSend);
        startActivity(intent);

    }

   
}