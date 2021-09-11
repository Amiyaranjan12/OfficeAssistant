package com.example.officeassistant.Chat_page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.example.officeassistant.Admin.employ_list.Model.Admin_employListModel;
import com.example.officeassistant.EmployHome_Activity;
import com.example.officeassistant.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatHome_Activity extends AppCompatActivity {

    private LinearLayout findFriend_Btn,acceptRequest_Btn;
    private RecyclerView chat_recyclerview;
    private ChatAdapter adapter;
    private SearchView CsearchView;
    private ImageView chat_backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_home_);
        findFriend_Btn=findViewById(R.id.findFriend_Btn);
        acceptRequest_Btn=findViewById(R.id.acceptRequest_Btn);
        CsearchView=findViewById(R.id.CsearchView);
        chat_backBtn=findViewById(R.id.chat_backBtn);




        chat_recyclerview=findViewById(R.id.chat_recyclerview);

        chat_recyclerview.setLayoutManager(new LinearLayoutManager(this));





        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference();

        FirebaseRecyclerOptions<Admin_employListModel> options =
                new FirebaseRecyclerOptions.Builder<Admin_employListModel>()
                        .setQuery(databaseReference, Admin_employListModel.class)
                        .build();

        adapter=new ChatAdapter(options);
        chat_recyclerview.setAdapter(adapter);



        findFriend_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChatHome_Activity.this,SendRequest_Activity.class);
                startActivity(intent);
            }
        });

        acceptRequest_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChatHome_Activity.this,AcceptRequest_Activity.class);
                startActivity(intent);
            }
        });

        CsearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        chat_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChatHome_Activity.this,EmployHome_Activity.class);
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

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(ChatHome_Activity.this, EmployHome_Activity.class);
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

        adapter=new ChatAdapter(options);
        adapter.startListening();
        chat_recyclerview.setAdapter(adapter);
    }

}