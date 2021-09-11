package com.example.officeassistant.Admin.Admin_allnoti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.officeassistant.Admin.employ_list.Model.Admin_employListModel;
import com.example.officeassistant.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NotificationEveryone_Activity extends AppCompatActivity {

    private RecyclerView admin_notiRecycler;
    private NotieveryoneAdapter adapter;
    private ImageView notiBack_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_everyone_layout);

        admin_notiRecycler=findViewById(R.id.admin_notiRecycler);
        notiBack_btn=findViewById(R.id.notiBack_btn);

        admin_notiRecycler.setLayoutManager(new LinearLayoutManager(this));


        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference();

        FirebaseRecyclerOptions<Admin_employListModel> options =
                new FirebaseRecyclerOptions.Builder<Admin_employListModel>()
                        .setQuery(databaseReference, Admin_employListModel.class)
                        .build();

        adapter=new NotieveryoneAdapter(options);
        admin_notiRecycler.setAdapter(adapter);


        notiBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NotificationEveryone_Activity.this,Allnoti_Activity.class);
                startActivity(intent);
            }
        });


    }


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

}