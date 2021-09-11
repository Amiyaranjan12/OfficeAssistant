package com.example.officeassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Employ_MailList_Activity extends AppCompatActivity {

    private CardView TextViewCeo,TextViewCto,TextViewCoo,TextViewCmo,TextViewHr,
            TextViewManager,TextViewAdmin,TextViewClerk,TextViewTeam,TextViewDataClerk;
    private ImageView mailistback_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employ__maillist);

        TextViewCeo=findViewById(R.id.TextViewCeo);
        TextViewCto=findViewById(R.id.TextViewCto);
        TextViewCoo=findViewById(R.id.TextViewCoo);
        TextViewCmo=findViewById(R.id.TextViewCmo);
        TextViewHr=findViewById(R.id.TextViewHr);
        TextViewManager=findViewById(R.id.TextViewManager);
        TextViewAdmin=findViewById(R.id.TextViewAdmin);
        TextViewClerk=findViewById(R.id.TextViewClerk);
        TextViewTeam=findViewById(R.id.TextViewTeam);
        TextViewDataClerk=findViewById(R.id.TextViewDataClerk);
        mailistback_btn=findViewById(R.id.mailistback_btn);


        mailistback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Employ_MailList_Activity.this,EmployHome_Activity.class);
                startActivity(intent);
            }
        });
        //

        TextViewCeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail="democeo@gmail.com";
                sendallMails(mail);

            }
        });
        //
        //
        TextViewCto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail="democto@gmail.com";
                sendallMails(mail);

            }
        });
        //
        //
        TextViewCoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail="democoo@gmail.com";
                sendallMails(mail);

            }
        });
        //
        //
        TextViewCmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail="democmo@gmail.com";
                sendallMails(mail);

            }
        });
        //
        //
        TextViewHr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail="demohr@gmail.com";
                sendallMails(mail);

            }
        });
        //
        //

        TextViewManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail="demomanager@gmail.com";
                sendallMails(mail);

            }
        });
        //
        //
        TextViewAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail="demoadmin@gmail.com";
                sendallMails(mail);

            }
        });
        //
        //
        TextViewClerk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail="democlerk@gmail.com";
                sendallMails(mail);

            }
        });
        //
        //

        TextViewTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail="demoteam@gmail.com";
                sendallMails(mail);

            }
        });
        //
        //

        TextViewDataClerk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail="demodatacleark@gmail.com";
                sendallMails(mail);

            }
        });
        //






    }

    private void sendallMails(String mail) {


        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri emailSend = Uri.parse("mailto:"+mail);
        intent.setData(emailSend);
        startActivity(intent);

    }
}