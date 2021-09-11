package com.example.officeassistant.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.officeassistant.Admin.Update_profile.Admin_UserUpdate_Profile_Activity;
import com.example.officeassistant.Admin.employ_list.Model.Admin_employListModel;
import com.example.officeassistant.EmployHome_Activity;
import com.example.officeassistant.Employ_Salary_Activity;
import com.example.officeassistant.R;
import com.example.officeassistant.UserProfileData;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class admin_salarySlip_Activity extends AppCompatActivity {

    private AutoCompleteTextView adminSalaryMonthtext;
    private ImageView admin_monthOpImage,SalaryBack_btn;
    private TextView admin_uploadSalaryBtn,choosePdfText,admin_SalaryYeartext,pdfAddStrogeBtn
            ,admin_employId,admin_employName,admin_employEmail;
    private final String[] arraymonthName =new String[]{"JANUARY","FEBRUARY","MARCH","APRIL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER"};

    private Uri pdfUri;
    private String pdfName;
    private String dateYear;
    private Bundle bundle;
    private String updateUri;
    private StorageReference storageReference;
    private FirebaseDatabase firebaseDatabase;
    private String userLoginCode;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_salary_slip_layout);

        SalaryBack_btn=findViewById(R.id.SalaryBack_btn);
        admin_employId=findViewById(R.id.admin_employId);
        admin_employName=findViewById(R.id.admin_employName);
        admin_employEmail=findViewById(R.id.admin_employEmail);


        adminSalaryMonthtext=findViewById(R.id.adminSalaryMonthtext);
        admin_monthOpImage=findViewById(R.id.admin_monthOpImage);
        admin_uploadSalaryBtn=findViewById(R.id.admin_uploadSalaryBtn);
        choosePdfText=findViewById(R.id.chosePdfText);
        admin_SalaryYeartext=findViewById(R.id.admin_SalaryYeartext);
        pdfAddStrogeBtn=findViewById(R.id.pdfAddStrogeBtn);

        bundle=getIntent().getExtras();
        userLoginCode=bundle.getString("usercode");
        storageReference= FirebaseStorage.getInstance().getReference();

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("SalarySlip").child(String.valueOf(userLoginCode));
        //////

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat format=new SimpleDateFormat("yyyy");//dd/MM/yyyy
        dateYear=format.format(calendar.getTime());

        System.out.println(dateYear);

        admin_SalaryYeartext.setText(String.valueOf(dateYear));


        SalaryBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(admin_salarySlip_Activity.this,Admin_employeeEditHome_Activity.class);
                startActivity(intent);
            }
        });




        DatabaseReference databaseReference1=firebaseDatabase.getReference(String.valueOf(userLoginCode));
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfileData userProfileData=dataSnapshot.getValue(UserProfileData.class);
                Admin_employListModel a=dataSnapshot.getValue( Admin_employListModel.class);
                admin_employId.setText(userProfileData.getUserId().toString());
                admin_employName.setText(userProfileData.getUserfullName());
                admin_employEmail.setText(a.getUserMail());


                //






            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Toast.makeText(EmployHome_Activity.this, "Errror", Toast.LENGTH_SHORT).show();

            }
        });



        //




        choosePdfText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Toast.makeText(getApplicationContext(),"lollll",Toast.LENGTH_LONG).show();

                Dexter.withContext(admin_salarySlip_Activity.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                                Intent intent=new Intent();
                                intent.setType("application/pdf");//
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(intent,"Please Select File"),550);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                                // Toast.makeText(getApplicationContext(),"lollll",Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();






            }
        });

        //
        adminSalaryMonthtext.setThreshold(1);
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, arraymonthName);
        adminSalaryMonthtext.setAdapter(adapter);


        admin_monthOpImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminSalaryMonthtext.showDropDown();
            }
        });

        ///update btn
        pdfAddStrogeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pdfUri !=null){

                    updatepdf();
                }

                if (pdfUri==null){
                    Toast.makeText(admin_salarySlip_Activity.this,"Please click on text box",Toast.LENGTH_SHORT).show();

                }


                  //






                //


                //

                /////////
            }
        });
        admin_uploadSalaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String monthName=adminSalaryMonthtext.getText().toString();



                if (updateUri!=null){
                    HashMap SalarySliphashMap=new HashMap();

                    SalarySliphashMap.put(monthName,updateUri);


                    databaseReference.child(dateYear).updateChildren(SalarySliphashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(admin_salarySlip_Activity.this,"Successfully updated",Toast.LENGTH_SHORT).show();
                        }
                    });


                }



               // System.out.println(updateUri!=null);
                if (updateUri==null) {
                    if (pdfUri !=null){
                        Toast.makeText(admin_salarySlip_Activity.this,"Please click on upload button",Toast.LENGTH_SHORT).show();

                    }

                    if (pdfUri==null){
                        Toast.makeText(admin_salarySlip_Activity.this,"Please click on text box",Toast.LENGTH_SHORT).show();

                    }


                  }




            }
        });
    }



    //

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==550 && resultCode==RESULT_OK)
        {
            pdfUri=data.getData();

            if (pdfUri.toString().startsWith("content://")){
                Cursor cursor=null;

                try {
                    cursor=admin_salarySlip_Activity.this.getContentResolver().query(pdfUri,
                            null,null,null,null);
                    if (cursor !=null && cursor.moveToFirst()){
                        pdfName=cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }

                }catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
                }



            }
            else if (pdfUri.toString().startsWith("file://")){

                pdfName=new File(pdfUri.toString()).getName();
            }

            choosePdfText.setText(pdfName);

        }
    }


    private void updatepdf() {

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Wait......");
        progressDialog.show();

        StorageReference uploader = storageReference.child("SalaryPdf/"+System.currentTimeMillis()+pdfName);
        uploader.putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                        uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                //  final Map<String, Object> map = new HashMap<>();
                                //   map.put("image", uri.toString());

                                updateUri=String.valueOf(uri);

                               if (updateUri!=null){
                                   progressDialog.dismiss();
                                }



                              //Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_LONG).show();
                                // Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_LONG).show();
                            }
                        });




                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                float percent=(100*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                progressDialog.setMessage("Uploaded :"+(int)percent+"%");
            }
        });


    }

    //
}