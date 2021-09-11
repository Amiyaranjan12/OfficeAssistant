package com.example.officeassistant;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.officeassistant.Admin.Admin_login_Activity;
import com.example.officeassistant.Admin.employ_list.Model.Admin_employListModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sun.bob.mcalendarview.MarkStyle;
import sun.bob.mcalendarview.vo.DateData;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class Employ_Salary_Activity extends AppCompatActivity {


    private TextView salary_genBtn,employ_SalaryYeartext;

    private TextView admin_employId,admin_employName;
    private AutoCompleteTextView   employSalaryMonthtext;
    private ImageView salaryback_btn;



    private final String[] arraymonthName =new String[]{"JANUARY","FEBRUARY","MARCH","APRIL","MAY",
            "JUNE","JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER"};

    private ImageView employ_monthOpImage;

    private int ch=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employ__salary);

        salary_genBtn=findViewById(R.id.salary_genBtn);
        admin_employId=findViewById(R.id.admin_employId);
        admin_employName=findViewById(R.id.admin_employName);
        employ_SalaryYeartext=findViewById(R.id.employ_SalaryYeartext);
        employSalaryMonthtext=findViewById(R.id.employSalaryMonthtext);
        employ_monthOpImage=findViewById(R.id.employ_monthOpImage);
        salaryback_btn=findViewById(R.id.salaryback_btn);


        salaryback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Employ_Salary_Activity.this,EmployHome_Activity.class);
                startActivity(intent);
            }
        });

        //
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Attendance");
        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();

        String uid=firebaseAuth.getUid();
        DatabaseReference databaseReference1=firebaseDatabase.getReference(uid);

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat format=new SimpleDateFormat("yyyy");//dd/MM/yyyy
        String edateYear=format.format(calendar.getTime());
        employ_SalaryYeartext.setText(String.valueOf(edateYear));


        //
        employSalaryMonthtext.setThreshold(1);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, arraymonthName);
        employSalaryMonthtext.setAdapter(adapter);


        employ_monthOpImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employSalaryMonthtext.showDropDown();
            }
        });

        //


        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfileData userProfileData=dataSnapshot.getValue(UserProfileData.class);
                admin_employId.setText(userProfileData.getUserId().toString());
                admin_employName.setText(userProfileData.getUserfullName());



                //






            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Toast.makeText(EmployHome_Activity.this, "Errror", Toast.LENGTH_SHORT).show();

            }
        });






        //
        employSalaryMonthtext.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);

                if (item!=null){
                    ch=1;

                }





            }
        });




        salary_genBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (ch==1){

                    salaryPDF();
                }
                if (ch==0){

                    Toast.makeText(Employ_Salary_Activity.this, "Please select month",
                            Toast.LENGTH_SHORT).show();

                }

            }
        });





    }

    private void salaryPDF() {

        String monthName=employSalaryMonthtext.getText().toString();
       //
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat format=new SimpleDateFormat("yyyy");//dd/MM/yyyy
        String edateYear=format.format(calendar.getTime());
        //
        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        String uid=firebaseAuth.getUid();
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("SalarySlip").child(uid).child(edateYear);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.child(monthName).exists()){
                    databaseReference.child(monthName).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String temp=snapshot.getValue(String.class);
                            StorageReference t= FirebaseStorage.getInstance().getReferenceFromUrl(temp);
                            String filen=String.valueOf(t.getName());


                            downloadPdf(Employ_Salary_Activity.this,filen,".pdf",DIRECTORY_DOWNLOADS,temp);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else {

                    Toast.makeText(Employ_Salary_Activity.this, "Currently Unavailable",
                            Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }


    public long downloadPdf(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {


        DownloadManager downloadmanager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        return downloadmanager.enqueue(request);
    }




}