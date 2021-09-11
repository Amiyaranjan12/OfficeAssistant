package com.example.officeassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.officeassistant.Admin.Update_profile.Admin_userProfileEdit_Model;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Employ_Profile_Activity extends AppCompatActivity {

   private TextView employId,joningDate,employName,employDesignation,employMobile,employBasicpay,employAddress,textEmail;
   private  ImageView image_employ;

   //datavae
      private FirebaseDatabase fireDatabase;
      private FirebaseAuth fireAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employ__profile);

        employId=findViewById(R.id.employId);
        joningDate=findViewById(R.id.joningDate);
        employName=findViewById(R.id.employName);
        employDesignation=findViewById(R.id.employDesignation);
        employMobile=findViewById(R.id.employMobile);
        employBasicpay=findViewById(R.id.employBasicpay);
        employAddress=findViewById(R.id.employAddress);
        image_employ=findViewById(R.id.image_employ);
        textEmail=findViewById(R.id.textEmail);


        ////////data bae


        ///////data 1
        fireDatabase=FirebaseDatabase.getInstance();
        fireAuth=FirebaseAuth.getInstance();
        DatabaseReference dataReference=fireDatabase.getReference(fireAuth.getUid());
        DatabaseReference databaseReference2=dataReference.child("image");

        //image

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String link=snapshot.getValue(String.class);
                Picasso.get().load(link).resize(150, 150).
                        into(image_employ);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //

       dataReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfileData userProfileData=dataSnapshot.getValue(UserProfileData.class);
                employId.setText(userProfileData.getUserId().toString());
                joningDate.setText(userProfileData.getUserJdate().toString());
                employName.setText(userProfileData.getUserfullName());
                employDesignation.setText(userProfileData.getUserDesignation());
                employMobile.setText(userProfileData.getUserMobile());
                employBasicpay.setText(userProfileData.getUserBasicpay());
                employAddress.setText(userProfileData.getUserAddress());
                Admin_userProfileEdit_Model modelObject=dataSnapshot.getValue(Admin_userProfileEdit_Model.class);

                textEmail.setText(modelObject.getUserMail());

                //






            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Toast.makeText(EmployHome_Activity.this, "Errror", Toast.LENGTH_SHORT).show();

            }
        });








        ////////////database



    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Employ_Profile_Activity.this,EmployHome_Activity.class);
        startActivity(intent);

    }
}