package com.example.officeassistant.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import com.example.officeassistant.Admin.employ_list.Adapter.Admin_Employ_list_Adapter;
import com.example.officeassistant.Admin.employ_list.Model.Admin_employListModel;
import com.example.officeassistant.R;
import com.example.officeassistant.event_page.Adapter.EventAdapter;
import com.example.officeassistant.event_page.Model.Event_list;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin_employList_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Admin_Employ_list_Adapter adapter;
    private FirebaseDatabase firebaseDatabase;
    private SearchView Admin_employ_searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_employ_list_layout);

        recyclerView=findViewById(R.id.admin_employList_RecycleView);
        Admin_employ_searchView=findViewById(R.id.Admin_employ_searchView);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference();

        FirebaseRecyclerOptions<Admin_employListModel> options =
                new FirebaseRecyclerOptions.Builder<Admin_employListModel>()
                        .setQuery(databaseReference, Admin_employListModel.class)
                        .build();


        adapter=new Admin_Employ_list_Adapter(options);
        recyclerView.setAdapter(adapter);
         /////////


        Admin_employ_searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s_text) {

                admin_searchView_function(s_text);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s_text) {
                admin_searchView_function(s_text);
                return false;
            }
        });
        ///////////

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

    private void admin_searchView_function(String s_text) {

        firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference();


        FirebaseRecyclerOptions<Admin_employListModel> options =
                new FirebaseRecyclerOptions.Builder<Admin_employListModel>()
                        .setQuery(databaseReference.orderByChild("userfullName")
                                .startAt(s_text).endAt(s_text+"\uf8ff"), Admin_employListModel.class)
                        .build();

        adapter=new Admin_Employ_list_Adapter(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Admin_employList_Activity.this,Admin_Home_Activity.class);
        startActivity(intent);
    }
}