package com.example.officeassistant.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.officeassistant.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.eazegraph.lib.models.PieModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sun.bob.mcalendarview.MarkStyle;
import sun.bob.mcalendarview.listeners.OnMonthChangeListener;
import sun.bob.mcalendarview.views.ExpCalendarView;
import sun.bob.mcalendarview.vo.DateData;

public class Admin_attenImage_Activity extends AppCompatActivity {

    private ImageView admin_atten_imageView;
    private Bundle bundle;
    private TextView scrol_monthtext;
    private Date date1,date2,date3;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.admin_atten_image_layout);

        admin_atten_imageView=findViewById(R.id.admin_atten_imageView);


        bundle=getIntent().getExtras();
        String ULcode=bundle.getString("userAcessCode");

        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference1=firebaseDatabase.getReference(String.valueOf(ULcode));
        //  EmployName_home.setText(firebaseAuth.getUid());


        ////



        ////////image

        new Thread(new Runnable() {
            @Override
            public void run() {
                databaseReference1.child("image").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String link=snapshot.getValue(String.class);
                        Picasso.get().load(link).fit().
                                into(admin_atten_imageView);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }).start();


       //////


        ExpCalendarView calendarView = (ExpCalendarView) findViewById(R.id.calendar_p);
        scrol_monthtext=findViewById(R.id.scrol_monthtext);

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        String date5=format.format(calendar.getTime());

        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Attendance");

        String uid=String.valueOf(ULcode);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot idchild:snapshot.getChildren()) {

                    if(idchild.child(uid).exists() && idchild.child(uid).getValue().equals("1")){

                        /// System.out.println(String.valueOf(idchild.getKey()));


                        try {
                            date1=new SimpleDateFormat("yyyy,MM,dd").parse(String.valueOf(idchild.getKey()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String day          = (String) DateFormat.format("dd",   date1); // 20
                        String monthNumber  = (String) DateFormat.format("MM",   date1); // 06
                        String year         = (String) DateFormat.format("yyyy", date1); // 2013

                        // System.out.println(day+"nn"+monthNumber+" nm b"+year);


                        calendarView.markDate(Integer.parseInt(year),Integer.parseInt(monthNumber), Integer.parseInt(day));



                        //
                    }


                    if(idchild.child(uid).exists() && idchild.child(uid).getValue().equals("0")){

                        System.out.println(String.valueOf(idchild.getKey()));
                    }



                    if(idchild.child(uid).exists() && idchild.child(uid).getValue().equals("0")){

                        /// System.out.println(String.valueOf(idchild.getKey()));


                        try {
                            date2=new SimpleDateFormat("yyyy,MM,dd").parse(String.valueOf(idchild.getKey()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String day1          = (String) DateFormat.format("dd",   date2); // 20
                        String monthNumber1  = (String) DateFormat.format("MM",   date2); // 06
                        String year1         = (String) DateFormat.format("yyyy", date2); // 2013

                        // System.out.println(day+"nn"+monthNumber+" nm b"+year);


                        calendarView.markDate(
                                new DateData(Integer.parseInt(year1),Integer.parseInt(monthNumber1), Integer.parseInt(day1)).setMarkStyle(new MarkStyle(MarkStyle.DEFAULT, Color.RED)));






                        //
                    }




                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

      //////////////////////////////////////////////////////////////////////////////////// /////////////////// //

        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MONTH, 0);
        calendar1.set(Calendar.DATE, calendar1.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date monthFirstDay = calendar1.getTime();
        calendar1.set(Calendar.DATE, calendar1.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date monthLastDay = calendar1.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy,MM,dd");
        String startDate = df.format(monthFirstDay);


        String endDate = df.format(monthLastDay);

        ///

        try {
            date3=new SimpleDateFormat("yyyy,MM,dd").parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String last_date  = (String) DateFormat.format("dd",   date3); // 20


        new Thread(new Runnable() {
            @Override
            public void run() {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                       int countPnum=0;
                        for (DataSnapshot Datechild:snapshot.getChildren()) {

                            if(Datechild.child(uid).exists() && Datechild.child(uid).getValue().equals("1") && Datechild.getKey().compareTo(startDate) >= 0 ){

                                countPnum=countPnum+1;
                               // attendPresentText.setText(String.valueOf(countPnum));

                            }


                        }

                        int tt=Integer.parseInt(last_date);
                        int countAbsent=tt- countPnum;

                        System.out.println(countAbsent);
                        System.out.println(countPnum);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        }).start();
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        ///

        //

        calendarView.setOnMonthChangeListener(new OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {


                if (month==1){
                    scrol_monthtext.setText("JANUARY"+" "+String.format("%d",year));
                }
                if (month==2){
                    scrol_monthtext.setText("FEBRUARY"+" "+String.format("%d",year));
                }
                if (month==3){
                    scrol_monthtext.setText("MARCH"+" "+String.format("%d",year));
                }
                if (month==4){
                    scrol_monthtext.setText("APRIL"+" "+String.format("%d",year));
                }
                if (month==5){
                    scrol_monthtext.setText("MAY"+" "+String.format("%d",year));
                }
                if (month==6){
                    scrol_monthtext.setText("JUNE"+" "+String.format("%d",year));
                }
                if (month==7){
                    scrol_monthtext.setText("JULY"+" "+String.format("%d",year));
                }
                if (month==8){
                    scrol_monthtext.setText("AUGUST"+" "+String.format("%d",year));
                }
                if (month==9){
                    scrol_monthtext.setText("SEPTEMBER"+" "+String.format("%d",year));
                }
                if (month==10){
                    scrol_monthtext.setText("OCTOBER"+" "+String.format("%d",year));
                }
                if (month==11){
                    scrol_monthtext.setText("NOVEMBER"+" "+String.format("%d",year));
                }
                if (month==12){
                    scrol_monthtext.setText("DECEMBER"+" "+String.format("%d",year));
                }



            }
        });


        ///////////
    }


    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Admin_attenImage_Activity.this,Admin_attendance_Activity.class);
        startActivity(intent);
    }
}