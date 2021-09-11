package com.example.officeassistant.Chat_page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.example.officeassistant.Admin.employ_list.Adapter.Admin_Employ_list_Adapter;
import com.example.officeassistant.Admin.employ_list.Model.Admin_employListModel;
import com.example.officeassistant.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AcceptRequest_Activity extends AppCompatActivity {

    RecyclerView FriendAccept_recyclerview;
    private AcceptRequestAdapter adapter;
    private LinearLayout chatA_Btn,SendA_Btn;
    private SearchView AsearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accept_request_activity);

        chatA_Btn=findViewById(R.id.chatA_Btn);
        SendA_Btn=findViewById(R.id.SendA_Btn);
        AsearchView=findViewById(R.id.AsearchView);


        FriendAccept_recyclerview=findViewById(R.id.FriendAccept_recyclerview);

        FriendAccept_recyclerview.setLayoutManager(new LinearLayoutManager(this));





        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference();

        FirebaseRecyclerOptions<Admin_employListModel> options =
                new FirebaseRecyclerOptions.Builder<Admin_employListModel>()
                        .setQuery(databaseReference, Admin_employListModel.class)
                        .build();

        adapter=new AcceptRequestAdapter(options);
        FriendAccept_recyclerview.setAdapter(adapter);

        chatA_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AcceptRequest_Activity.this,ChatHome_Activity.class);
                startActivity(intent);
            }
        });

        SendA_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AcceptRequest_Activity.this,SendRequest_Activity.class);
                startActivity(intent);
            }
        });


        AsearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s_text) {

                AsearchView_function(s_text);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s_text) {
                AsearchView_function(s_text);
                return false;
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
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(AcceptRequest_Activity.this, SendRequest_Activity.class);
        startActivity(intent);
    }

    private void AsearchView_function(String s_text) {

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference();


        FirebaseRecyclerOptions<Admin_employListModel> options =
                new FirebaseRecyclerOptions.Builder<Admin_employListModel>()
                        .setQuery(databaseReference.orderByChild("userfullName")
                                .startAt(s_text).endAt(s_text+"\uf8ff"), Admin_employListModel.class)
                        .build();

        adapter=new AcceptRequestAdapter(options);
        adapter.startListening();
        FriendAccept_recyclerview.setAdapter(adapter);
    }

}