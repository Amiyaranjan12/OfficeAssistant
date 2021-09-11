package com.example.officeassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.officeassistant.event_page.Adapter.EventAdapter;
import com.example.officeassistant.event_page.Model.Event_list;
import com.example.officeassistant.notification_page.Adapter.NotificAdapter;
import com.example.officeassistant.notification_page.Model.Notification_List;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Employ_Event_Activity extends AppCompatActivity {


    private ImageView eventback_btn;

    private RecyclerView recyclerView;
    private EventAdapter adapter;

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employ__event_);

        eventback_btn=findViewById(R.id.eventback_btn);

        recyclerView=findViewById(R.id.evnet_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        eventBackfunction();

        //////
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getUid());
        DatabaseReference databaseReference1=databaseReference.child("event");


        ////////
        FirebaseRecyclerOptions<Event_list> options =
                new FirebaseRecyclerOptions.Builder<Event_list>()
                        .setQuery(databaseReference1, Event_list.class)
                        .build();

        adapter=new EventAdapter(options);
        recyclerView.setAdapter(adapter);

        ////////

    }

    //////////

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

    private void eventBackfunction() {
        eventback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Employ_Event_Activity.this,EmployHome_Activity.class);
                startActivity(intent);
            }
        });


    }
    ///////
}