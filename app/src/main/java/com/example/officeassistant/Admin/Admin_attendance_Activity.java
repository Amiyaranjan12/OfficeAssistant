package com.example.officeassistant.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.officeassistant.Admin.Admin_attend.AdminAttendAdapter;

import com.example.officeassistant.Admin.employ_list.Model.Admin_employListModel;
import com.example.officeassistant.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Admin_attendance_Activity extends AppCompatActivity {


    private RecyclerView  recyclerView;
    private AdminAttendAdapter adapter;
    private TextView today_dateText;
    private ImageView attendanceBack_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_attendance_layout);

        recyclerView=findViewById(R.id.admin_attendRecycler);
        today_dateText=findViewById(R.id.today_dateText);
        attendanceBack_btn=findViewById(R.id.attendanceBack_btn);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        new Thread(new Runnable() {
            @Override
            public void run() {

                attendanceBack_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Admin_attendance_Activity.this,Admin_Home_Activity.class);
                        startActivity(intent);
                    }
                });

                ///
                Calendar calendar=Calendar.getInstance();
                SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
                String ddate=format.format(calendar.getTime());
                today_dateText.setText(ddate);


                ///

            }
        }).start();

        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference();

        FirebaseRecyclerOptions<Admin_employListModel> options =
                new FirebaseRecyclerOptions.Builder<Admin_employListModel>()
                        .setQuery(databaseReference, Admin_employListModel.class)
                        .build();

        adapter=new AdminAttendAdapter(options);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Admin_attendance_Activity.this,Admin_Home_Activity.class);
        startActivity(intent);

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