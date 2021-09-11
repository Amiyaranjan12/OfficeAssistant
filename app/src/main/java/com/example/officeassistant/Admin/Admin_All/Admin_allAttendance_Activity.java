package com.example.officeassistant.Admin.Admin_All;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.officeassistant.Admin.Admin_Home_Activity;
import com.example.officeassistant.Admin.employ_list.Model.Admin_employListModel;
import com.example.officeassistant.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Admin_allAttendance_Activity extends AppCompatActivity {

    private RecyclerView admin_attendAll_Recycler;
    private Allattend_Adapter adapter;
    private ImageView attendanceReportBack_btn,admin_monthOpImage;
    private AutoCompleteTextView admin_AttendanceMonthtext;
    private TextView allAttendance_Btn;
    private final String[] arraymonthName =new String[]{"January","February","March","April","May",
            "June","July","August","September","October","November","December"};
    private String moName=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_all_attendance_layout);

        admin_attendAll_Recycler=findViewById(R.id.admin_attendAll_Recycler);
        attendanceReportBack_btn=findViewById(R.id.attendanceReportBack_btn);

        admin_monthOpImage=findViewById(R.id.admin_monthOpImage);
        admin_AttendanceMonthtext=findViewById(R.id.admin_AttendanceMonthtext);
        allAttendance_Btn=findViewById(R.id.allAttendance_Btn);


        //////////

        admin_AttendanceMonthtext.setThreshold(1);
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, arraymonthName);
        admin_AttendanceMonthtext.setAdapter(adapter1);


        admin_monthOpImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin_AttendanceMonthtext.showDropDown();
            }
        });



        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String latestm_name = month_date.format(cal.getTime());


        ///////
        admin_attendAll_Recycler.setLayoutManager(new LinearLayoutManager(this));

        attendanceReportBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_allAttendance_Activity.this, Admin_Home_Activity.class);
                startActivity(intent);
            }
        });

        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference();

        FirebaseRecyclerOptions<Admin_employListModel> options =
                new FirebaseRecyclerOptions.Builder<Admin_employListModel>()
                        .setQuery(databaseReference, Admin_employListModel.class)
                        .build();

        adapter=new Allattend_Adapter(options,latestm_name);
        admin_attendAll_Recycler.setAdapter(adapter);

        allAttendance_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moName=admin_AttendanceMonthtext.getText().toString();
               ///

                FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
                DatabaseReference databaseReference=firebaseDatabase.getReference();

                FirebaseRecyclerOptions<Admin_employListModel> options =
                        new FirebaseRecyclerOptions.Builder<Admin_employListModel>()
                                .setQuery(databaseReference, Admin_employListModel.class)
                                .build();

                adapter=new Allattend_Adapter(options,moName);
                admin_attendAll_Recycler.setAdapter(adapter);
                adapter.startListening();
                ///

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