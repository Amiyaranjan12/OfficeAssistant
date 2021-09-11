package com.example.officeassistant.Admin.Admin_event;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.officeassistant.Admin.Update_profile.Admin_userProfileEdit_Model;
import com.example.officeassistant.Admin.admin_notification.Admin_notification_Activity;
import com.example.officeassistant.R;
import com.example.officeassistant.event_page.Event_details;
import com.example.officeassistant.event_page.Model.Event_list;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;

public class Admin_employ_eventActivity extends AppCompatActivity {


    private TextView admin_event1Text,admin_event2Text,admin_event3Text,admin_event4Text,admin_event5Text,
            admin_eventDetails1Text,admin_eventDetails2Text,admin_eventDetails3Text,admin_eventDetails4Text,admin_eventDetails5Text,admin_employEventBtn;

    private Bundle extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_employ_event_layout);

        admin_event1Text=findViewById(R.id.admin_event1Text);
        admin_event2Text=findViewById(R.id.admin_event2Text);
        admin_event3Text=findViewById(R.id.admin_event3Text);
        admin_event4Text=findViewById(R.id.admin_event4Text);
        admin_event5Text=findViewById(R.id.admin_event5Text);

        admin_eventDetails1Text=findViewById(R.id.admin_eventDetails1Text);
        admin_eventDetails2Text=findViewById(R.id.admin_eventDetails2Text);
        admin_eventDetails3Text=findViewById(R.id.admin_eventDetails3Text);
        admin_eventDetails4Text=findViewById(R.id.admin_eventDetails4Text);
        admin_eventDetails5Text=findViewById(R.id.admin_eventDetails5Text);
        admin_employEventBtn=findViewById(R.id.admin_employEventBtn);



        extra=getIntent().getExtras();
        String userLId=extra.getString("useracode");
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference(String.valueOf(userLId)).child("event");


        new Thread(new Runnable() {
            @Override
            public void run() {
                databaseReference.child("e1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Event_list event_list=dataSnapshot.getValue(Event_list.class);

                        admin_event1Text.setText(event_list.geteEvent());
                        admin_eventDetails1Text.setText(event_list.geteDetails());


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Toast.makeText(EmployHome_Activity.this, "Errror", Toast.LENGTH_SHORT).show();

                    }
                });

                databaseReference.child("e2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Event_list event_list=dataSnapshot.getValue(Event_list.class);

                        admin_event2Text.setText(event_list.geteEvent());
                        admin_eventDetails2Text.setText(event_list.geteDetails());


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Toast.makeText(EmployHome_Activity.this, "Errror", Toast.LENGTH_SHORT).show();

                    }
                });
                databaseReference.child("e3").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Event_list event_list=dataSnapshot.getValue(Event_list.class);

                        admin_event3Text.setText(event_list.geteEvent());
                        admin_eventDetails3Text.setText(event_list.geteDetails());


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Toast.makeText(EmployHome_Activity.this, "Errror", Toast.LENGTH_SHORT).show();

                    }
                });
                databaseReference.child("e4").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Event_list event_list=dataSnapshot.getValue(Event_list.class);

                        admin_event4Text.setText(event_list.geteEvent());
                        admin_eventDetails4Text.setText(event_list.geteDetails());


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Toast.makeText(EmployHome_Activity.this, "Errror", Toast.LENGTH_SHORT).show();

                    }
                });
                databaseReference.child("e5").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Event_list event_list=dataSnapshot.getValue(Event_list.class);

                        admin_event5Text.setText(event_list.geteEvent());
                        admin_eventDetails5Text.setText(event_list.geteDetails());


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Toast.makeText(EmployHome_Activity.this, "Errror", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }).start();


        admin_employEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //

                String event1=admin_event1Text.getText().toString();
                String event2=admin_event2Text.getText().toString();
                String event3=admin_event3Text.getText().toString();
                String event4=admin_event4Text.getText().toString();
                String event5=admin_event5Text.getText().toString();
                String eventDetails1= admin_eventDetails1Text.getText().toString();
                String eventDetails2=admin_eventDetails2Text.getText().toString();
                String eventDetails3=admin_eventDetails3Text.getText().toString();
                String eventDetails4=admin_eventDetails4Text.getText().toString();
                String eventDetails5=admin_eventDetails5Text.getText().toString();


                HashMap eventn1hashMap=new HashMap();

                eventn1hashMap.put("eEvent",event1);
                eventn1hashMap.put("eDetails",eventDetails1);



                databaseReference.child("e1").updateChildren(eventn1hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }
                });


                HashMap eventn2hashMap=new HashMap();

                eventn2hashMap.put("eEvent",event2);
                eventn2hashMap.put("eDetails",eventDetails2);



                databaseReference.child("e2").updateChildren(eventn2hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {



                    }
                });


                HashMap eventn3hashMap=new HashMap();

                eventn3hashMap.put("eEvent",event3);
                eventn3hashMap.put("eDetails",eventDetails3);

                databaseReference.child("e3").updateChildren(eventn3hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {



                    }
                });

                HashMap eventn4hashMap=new HashMap();
                eventn4hashMap.put("eEvent",event4);
                eventn4hashMap.put("eDetails",eventDetails4);

                databaseReference.child("e4").updateChildren(eventn4hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {



                    }
                });

                HashMap eventn5hashMap=new HashMap();
                eventn5hashMap.put("eEvent",event5);
                eventn5hashMap.put("eDetails",eventDetails5);

                databaseReference.child("e5").updateChildren(eventn5hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {



                    }
                });

                Toast.makeText(Admin_employ_eventActivity.this,"Successfully updated",Toast.LENGTH_SHORT).show();



                //




            }
        });


    }
}