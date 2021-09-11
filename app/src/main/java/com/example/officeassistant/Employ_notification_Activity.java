package com.example.officeassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.officeassistant.notification_page.Adapter.NotificAdapter;
import com.example.officeassistant.notification_page.Model.Notification_List;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Employ_notification_Activity extends AppCompatActivity {

    private ImageView notificationback_btn;

    private RecyclerView recyclerView;
    private NotificAdapter adapter;

    ///////
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    /////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employ_notification);

        notificationback_btn=findViewById(R.id.notificationback_btn);
        recyclerView=findViewById(R.id.notification_recycle);
       // recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notificationBackfunction();

        //////
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getUid());
        DatabaseReference databaseReference1=databaseReference.child("notification");


        ////////
        FirebaseRecyclerOptions<Notification_List> options =
                new FirebaseRecyclerOptions.Builder<Notification_List>()
                        .setQuery(databaseReference1, Notification_List.class)
                        .build();

        adapter=new NotificAdapter(options);
        recyclerView.setAdapter(adapter);

        ////////

    }

    ///////
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    //////

    private void notificationBackfunction() {
        notificationback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Employ_notification_Activity.this,EmployHome_Activity.class);
                startActivity(intent);
            }
        });


    }
}