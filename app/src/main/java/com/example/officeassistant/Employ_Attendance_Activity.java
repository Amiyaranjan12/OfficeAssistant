package com.example.officeassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sun.bob.mcalendarview.MarkStyle;
import sun.bob.mcalendarview.listeners.OnMonthChangeListener;
import sun.bob.mcalendarview.views.ExpCalendarView;
import sun.bob.mcalendarview.vo.DateData;

public class Employ_Attendance_Activity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private Date date1,date2;
    private TextView EA_dateText,scrol_monthtext,attendPresentText,attendAbsentText;

    private  int countPnum=0;
    private int counttert =0;
    private Date date3,date4;
    private String last_date;
    private ImageView attback_btn;

    private PieChart Attendpiechart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employ__attendance);

        ExpCalendarView calendarView = (ExpCalendarView) findViewById(R.id.calendar_exp);

        EA_dateText=findViewById(R.id.EA_dateText);
        scrol_monthtext=findViewById(R.id.scrol_monthtext);
        attendAbsentText=findViewById(R.id.attendAbsentText);
        attendPresentText=findViewById(R.id.attendPresentText);
        Attendpiechart=findViewById(R.id.Attendpiechart);
        attback_btn=findViewById(R.id.attback_btn);

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        String date5=format.format(calendar.getTime());
        EA_dateText.setText(date5);

        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Attendance");
        firebaseAuth= FirebaseAuth.getInstance();

        String uid=firebaseAuth.getUid();

        attback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Employ_Attendance_Activity.this,EmployHome_Activity.class);
                startActivity(intent);
            }
        });

        /////****

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
        Calendar calendar5=Calendar.getInstance();
        SimpleDateFormat format2=new SimpleDateFormat("yyyy,MM,dd");
        String ddate=format2.format(calendar5.getTime());

        try {
            date3=new SimpleDateFormat("yyyy,MM,dd").parse(ddate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String last_date  = (String) DateFormat.format("dd",   date3); // 20




        //defualt month name

        try {
            date4=new SimpleDateFormat("yyyy,MM,dd").parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String monthName = (String) DateFormat.format("MM",   date4); // 06
        String year         = (String) DateFormat.format("yyyy", date4);
       // int month1=Integer.parseInt(monthName);


        if (monthName.equals("01")){
            scrol_monthtext.setText("JANUARY"+" "+year);
        }
        if (monthName.equals("02")){
            scrol_monthtext.setText("FEBRUARY"+" "+year);
        }
        if (monthName.equals("03")){
            scrol_monthtext.setText("MARCH"+" "+year);
        }

        if (monthName.equals("04")){
            scrol_monthtext.setText("APRIL"+" "+year);
        }
        if (monthName.equals("05")){
            scrol_monthtext.setText("MAY"+" "+year);
        }
        if (monthName.equals("06")){
            scrol_monthtext.setText("JUNE"+" "+year);
        }
        if (monthName.equals("07")){
            scrol_monthtext.setText("JULY"+" "+year);
        }
        if (monthName.equals("08")){
            scrol_monthtext.setText("AUGUST"+" "+year);
        }
        if (monthName.equals("09")){
            scrol_monthtext.setText("SEPTEMBER"+" "+year);
        }
        if (monthName.equals("10")){
            scrol_monthtext.setText("OCTOBER"+" "+year);
        }
        if (monthName.equals("11")){
            scrol_monthtext.setText("NOVEMBER"+" "+year);
        }
        if (monthName.equals("12")){
            scrol_monthtext.setText("DECEMBER"+" "+year);
        }






        //

        new Thread(new Runnable() {
            @Override
            public void run() {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot Datechild:snapshot.getChildren()) {

                            if(Datechild.child(uid).exists() && Datechild.child(uid).getValue().equals("1") && Datechild.getKey().compareTo(startDate) >= 0 ){

                                counttert = counttert +1;
                                countPnum=countPnum+1;
                                attendPresentText.setText(String.valueOf(countPnum));

                            }


                        }
                        int tt=Integer.parseInt(last_date);
                        float countp=(float) counttert/(float) tt*100;
                        int countpresent=Math.round(countp);
                        int countAbsent=100-countpresent;
                       //pie data

                        Attendpiechart.addPieSlice(
                                new PieModel(
                                        "Present",
                                        Integer.parseInt(String.valueOf(countpresent)),
                                        Color.parseColor("#B92A68D3")));
                        Attendpiechart.addPieSlice(
                                new PieModel(
                                        "Absent",
                                        Integer.parseInt(String.valueOf(countAbsent)),
                                        Color.parseColor("#D3FF1744")));

                        // To animate the pie chart
                        Attendpiechart.startAnimation();


                        int countAnum=tt-(int) counttert;
                        attendAbsentText.setText(String.valueOf(countAnum));

                        ///


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        }).start();




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



    }

}