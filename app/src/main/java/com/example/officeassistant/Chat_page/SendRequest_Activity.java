package com.example.officeassistant.Chat_page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.example.officeassistant.Admin.Admin_Home_Activity;
import com.example.officeassistant.Admin.Admin_attend.AdminAttendAdapter;
import com.example.officeassistant.Admin.Admin_attendance_Activity;
import com.example.officeassistant.Admin.employ_list.Model.Admin_employListModel;
import com.example.officeassistant.EmployHome_Activity;
import com.example.officeassistant.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SendRequest_Activity extends AppCompatActivity {

    private RecyclerView FriendRequest_recyclerview;
    private SendRequestAdapter adapter;
    private LinearLayout chatS_Btn,acceptS_Btn;
    private SearchView SsearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_request_);

        acceptS_Btn=findViewById(R.id.acceptS_Btn);
        chatS_Btn=findViewById(R.id.chatS_Btn);
        SsearchView=findViewById(R.id.SsearchView);

        FriendRequest_recyclerview=findViewById(R.id.FriendRequest_recyclerview);
        FriendRequest_recyclerview.setLayoutManager(new LinearLayoutManager(this));





        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference();

        FirebaseRecyclerOptions<Admin_employListModel> options =
                new FirebaseRecyclerOptions.Builder<Admin_employListModel>()
                        .setQuery(databaseReference, Admin_employListModel.class)
                        .build();

        adapter=new SendRequestAdapter(options);
        FriendRequest_recyclerview.setAdapter(adapter);


        chatS_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SendRequest_Activity.this,ChatHome_Activity.class);
                startActivity(intent);
            }
        });

        acceptS_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SendRequest_Activity.this,AcceptRequest_Activity.class);
                startActivity(intent);
            }
        });

        SsearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        Intent intent=new Intent(SendRequest_Activity.this, ChatHome_Activity.class);
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

        adapter=new SendRequestAdapter(options);
        adapter.startListening();
        FriendRequest_recyclerview.setAdapter(adapter);
    }
}