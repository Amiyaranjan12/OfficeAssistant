package com.example.officeassistant.Admin.Update_profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.officeassistant.Admin.Admin_employeeEditHome_Activity;
import com.example.officeassistant.Admin.admin_salarySlip_Activity;
import com.example.officeassistant.Admin.employ_list.Model.Admin_employListModel;
import com.example.officeassistant.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
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
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Admin_UserUpdate_Profile_Activity extends AppCompatActivity {

    private TextView admin_employIdText,admin_employeJDateText,admin_employefullNameText,admin_employeDesignationText,admin_employeMobileText
            ,admin_employeBasicText,admin_employeAddressText,admin_employeNameText,admin_employeEmailText;

    private Button admin_employeEditProfileBtn;
    private Bundle bundle;

    private FirebaseDatabase fDatabase;
    private  DatabaseReference dReference;
    private StorageReference storageReference;


    String temp;
    ///////
    private ImageView admin_employImage;
           TextView admin_employImageBtn;
    private Uri imageUri;
    private String userLoginCode;
    Bitmap bitmap;

    ///////



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin__user_update__profile_layout);


        admin_employIdText=findViewById(R.id.admin_employIdText);
        admin_employeJDateText=findViewById(R.id.admin_employeJDateText);
        admin_employefullNameText=findViewById(R.id.admin_employefullNameText);
        admin_employeDesignationText=findViewById(R.id.admin_employeDesignationText);
        admin_employeMobileText=findViewById(R.id.admin_employeMobileText);
        admin_employeBasicText=findViewById(R.id.admin_employeBasicText);
        admin_employeAddressText=findViewById(R.id.admin_employeAddressText);
        admin_employeNameText=findViewById(R.id.admin_employeNameText);
        admin_employeEmailText=findViewById(R.id.admin_employeEmailText);
        admin_employeEditProfileBtn=findViewById(R.id.admin_employeEditProfileBtn);
        admin_employImage=findViewById(R.id.admin_employImage);
        admin_employImageBtn=findViewById(R.id.admin_employImageBtn);

        bundle=getIntent().getExtras();
        String userLoginCode=bundle.getString("usercode");

        fDatabase= FirebaseDatabase.getInstance();
        DatabaseReference dReference=fDatabase.getReference(String.valueOf(userLoginCode));

        /////////

        dReference.child("image").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String link=snapshot.getValue(String.class);
                Picasso.get().load(link).resize(150, 150).
                        into(admin_employImage);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //////image
        storageReference= FirebaseStorage.getInstance().getReference();
        admin_employImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"lollll",Toast.LENGTH_LONG).show();

                Dexter.withContext(Admin_UserUpdate_Profile_Activity.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {

                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {


                                CropImage.activity()
                                        .setGuidelines(CropImageView.Guidelines.ON)
                                        .start(Admin_UserUpdate_Profile_Activity.this);
/*
                                Intent intent=new Intent();
                                intent.setType("image/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(intent,"Please Select File"),500);

 */
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




        admin_employImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imageUri !=null){

                    updateimage();
                }

                if (imageUri==null){
                    Toast.makeText(Admin_UserUpdate_Profile_Activity.this,"Please click on image box",Toast.LENGTH_SHORT).show();

                }

            }
        });




        ///////






        dReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Admin_userProfileEdit_Model modelObject=dataSnapshot.getValue(Admin_userProfileEdit_Model.class);


                admin_employIdText.setText(modelObject.getUserId());
                admin_employeJDateText.setText(modelObject.getUserJdate());
                admin_employefullNameText.setText(modelObject.getUserfullName());
                admin_employeDesignationText.setText(modelObject.getUserDesignation());
                admin_employeMobileText.setText(modelObject.getUserMobile());
                admin_employeBasicText.setText(modelObject.getUserBasicpay());
                admin_employeAddressText.setText(modelObject.getUserAddress());
                admin_employeNameText.setText(modelObject.getUserName());
                admin_employeEmailText.setText(modelObject.getUserMail());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Toast.makeText(EmployHome_Activity.this, "Errror", Toast.LENGTH_SHORT).show();

            }
        });






        admin_employeEditProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String uId= admin_employIdText.getText().toString();
                String uJdate=admin_employeJDateText.getText().toString();
                String ufullName= admin_employefullNameText.getText().toString();
                String uDesignation=admin_employeDesignationText.getText().toString();
                String uMobile= admin_employeMobileText.getText().toString();
                String uBasicpay= admin_employeBasicText.getText().toString();
                String uAddress=  admin_employeAddressText.getText().toString();
                String uName= admin_employeNameText.getText().toString();
                String uMail= admin_employeEmailText.getText().toString();
            //    String uAcode=String.valueOf(userLoginCode);
               // int uadmin=0;


                /////////

                HashMap editProfilehashMap=new HashMap();

                editProfilehashMap.put("userId",uId);
                editProfilehashMap.put("userJdate", uJdate);
                editProfilehashMap.put("userfullName",ufullName);
                editProfilehashMap.put("userDesignation",uDesignation);
                editProfilehashMap.put("userMobile",uMobile);
                editProfilehashMap.put("userBasicpay",uBasicpay);
                editProfilehashMap.put("userAddress",uAddress);
                editProfilehashMap.put("userName",uName);
                editProfilehashMap.put("userMail",uMail);
                editProfilehashMap.put("image",temp);


                dReference.updateChildren(editProfilehashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(Admin_UserUpdate_Profile_Activity.this,"Successfully updated",Toast.LENGTH_SHORT).show();
                    }
                });
                /////////



          //





            }
        });













    }

    //
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageUri = result.getUri();
                admin_employImage.setImageURI(imageUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
        /*
        if(requestCode==500 && resultCode==RESULT_OK)
        {
            imageUri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(imageUri);
                bitmap= BitmapFactory.decodeStream(inputStream);
                admin_employImage.setImageBitmap(bitmap);
            }catch (Exception ex)
            {
                Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
            }
        }

         */
    }
//

    public void updateimage() {

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Wait......");
        progressDialog.show();

        StorageReference uploader = storageReference.child("img" + System.currentTimeMillis());
        uploader.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                            uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                  //  final Map<String, Object> map = new HashMap<>();
                                 //   map.put("image", uri.toString());

                                       temp=String.valueOf(uri);

                                       System.out.println(temp);
                                    progressDialog.dismiss();

                                    Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_LONG).show();
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


    } //

}



