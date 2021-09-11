package com.example.officeassistant.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.officeassistant.Admin.Admin_All.Admin_allAttendance_Activity;
import com.example.officeassistant.Admin.Admin_allnoti.Allnoti_Activity;
import com.example.officeassistant.Admin.Admin_allnoti.NotificationEveryone_Activity;
import com.example.officeassistant.Admin.Admin_event.Admin_employ_eventActivity;
import com.example.officeassistant.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import sun.bob.mcalendarview.MarkStyle;
import sun.bob.mcalendarview.vo.DateData;

public class Admin_Home_Activity extends AppCompatActivity {

    private RelativeLayout admin_logout_button,employ_list_button,Admin_eAttendanceBtn,all_employattendBtn,Admin_allnotificBtn;
    private AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin__home_layout);

        admin_logout_button=findViewById(R.id.admin_logout_button);
        employ_list_button=findViewById(R.id.employ_list_button);
        Admin_eAttendanceBtn=findViewById(R.id.Admin_eAttendanceBtn);
        all_employattendBtn=findViewById(R.id.all_employattendBtn);
        Admin_allnotificBtn=findViewById(R.id.Admin_allnotificBtn);



        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Attendance");

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat format=new SimpleDateFormat("yyyy,MM,dd");
        String ddate=format.format(calendar.getTime());



        Admin_allnotificBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Admin_Home_Activity.this, Allnoti_Activity.class);

                startActivity(intent);
            }
        });

        all_employattendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_Home_Activity.this, Admin_allAttendance_Activity.class);
                startActivity(intent);

                //
            }
        });

        Admin_eAttendanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              databaseReference.addValueEventListener(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot snapshot) {

                      if (!snapshot.hasChild(ddate)){

                          databaseReference.child("default_id").addListenerForSingleValueEvent(new ValueEventListener() {
                              @Override
                              public void onDataChange(@NonNull DataSnapshot snapshot) {
                                  for (DataSnapshot temp:snapshot.getChildren()) {

                                      String aa=temp.child("code").getValue(String.class);

                                      databaseReference.child(ddate).child(aa).setValue("0");


                                  }

                              }

                              @Override
                              public void onCancelled(@NonNull DatabaseError error) {

                              }
                          });

                          //color logic

                          FirebaseDatabase ffDatabase1= FirebaseDatabase.getInstance();
                          DatabaseReference ddReference1=ffDatabase1.getReference();
                          ddReference1.addValueEventListener(new ValueEventListener() {
                              @Override
                              public void onDataChange(@NonNull DataSnapshot snapshot) {
                                  for (DataSnapshot p:snapshot.getChildren()) {

                                      if (p.child("cid").exists()){

                                          String bb=p.child("userAcode").getValue(String.class);

                                          HashMap cdHashmap1=new HashMap();

                                          cdHashmap1.put("cid","0");


                                          ddReference1.child(String.valueOf(bb)).updateChildren(cdHashmap1).addOnSuccessListener(new OnSuccessListener() {
                                              @Override
                                              public void onSuccess(Object o) {
                                              }
                                          });

                                          //

                                      }
                                  }
                              }

                              @Override
                              public void onCancelled(@NonNull DatabaseError error) {

                              }
                          });


                          ////


                      }

                      else{

                          Intent intent=new Intent(Admin_Home_Activity.this,Admin_attendance_Activity.class);
                          startActivity(intent);
                      }

                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError error) {

                  }
              });


            }
        });
            /////////


        /////logout button
        admin_logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logoutfunction();
            }
        });
        /////

        employ_list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_Home_Activity.this, Admin_employList_Activity.class);
                startActivity(intent);
            }
        });

    }


    private void logoutfunction() {

        alertDialog=new AlertDialog.Builder(Admin_Home_Activity.this);
        alertDialog.setMessage("Do you want to logout?");
        alertDialog.setCancelable(false);

        alertDialog.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ///
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Admin_Home_Activity.this,"Logged out",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Admin_Home_Activity.this, Admin_login_Activity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
                ///

            }
        });

        alertDialog.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog=alertDialog.create();
        dialog.show();
        //



        //
    }

    private void exitfunction() {

        alertDialog=new AlertDialog.Builder(Admin_Home_Activity.this);
        alertDialog.setMessage("Do you want to exit?");
        alertDialog.setCancelable(false);

        alertDialog.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ///
                finish();
                moveTaskToBack(true);
                System.exit(0);

                ///

            }
        });

        alertDialog.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog=alertDialog.create();
        dialog.show();
        //



        //
    }

    @Override
    public void onBackPressed() {
        exitfunction();
    }
}