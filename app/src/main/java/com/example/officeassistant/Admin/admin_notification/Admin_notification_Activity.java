package com.example.officeassistant.Admin.admin_notification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.officeassistant.Admin.Update_profile.Admin_UserUpdate_Profile_Activity;
import com.example.officeassistant.R;
import com.example.officeassistant.notification_page.Model.Notification_List;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Admin_notification_Activity extends AppCompatActivity {

    private TextView admin_notific1Text,admin_notific2Text,admin_notific3Text,admin_notific4Text,admin_notific5Text,
            admin_notific6Text,admin_notific7Text,admin_notific8Text;

    private Bundle bundle;
    private  DatabaseReference databaseReference;
    private RelativeLayout admin_employeNotitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_notification_layout);


        admin_notific1Text=findViewById(R.id.admin_notific1Text);
        admin_notific2Text =findViewById(R.id. admin_notific2Text);
        admin_notific3Text=findViewById(R.id.admin_notific3Text);
        admin_notific4Text=findViewById(R.id.admin_notific4Text);
        admin_notific5Text=findViewById(R.id.admin_notific5Text);
        admin_notific6Text=findViewById(R.id. admin_notific6Text);
        admin_notific7Text=findViewById(R.id.admin_notific7Text);
        admin_notific8Text=findViewById(R.id.admin_notific8Text);
        admin_employeNotitBtn=findViewById(R.id.admin_employeNotitBtn);


        bundle=getIntent().getExtras();
        String userLoginCode=bundle.getString("useracode");

        FirebaseDatabase  firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference(String.valueOf(userLoginCode));

    //////////////
        databaseReference.child("notification").child("n1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Notification_List notification_list=snapshot.getValue(Notification_List.class);

                admin_notific1Text.setText(notification_list.getEmployNotification());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ///////

        databaseReference.child("notification").child("n2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Notification_List notification_list=snapshot.getValue(Notification_List.class);

                admin_notific2Text.setText(notification_list.getEmployNotification());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ///////
        databaseReference.child("notification").child("n3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Notification_List notification_list=snapshot.getValue(Notification_List.class);

                admin_notific3Text.setText(notification_list.getEmployNotification());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ///////
        databaseReference.child("notification").child("n4").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Notification_List notification_list=snapshot.getValue(Notification_List.class);

                admin_notific4Text.setText(notification_list.getEmployNotification());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ///////
        databaseReference.child("notification").child("n5").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Notification_List notification_list=snapshot.getValue(Notification_List.class);

                admin_notific5Text.setText(notification_list.getEmployNotification());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ///////

        databaseReference.child("notification").child("n6").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Notification_List notification_list=snapshot.getValue(Notification_List.class);

                admin_notific6Text.setText(notification_list.getEmployNotification());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ///////
        databaseReference.child("notification").child("n7").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Notification_List notification_list=snapshot.getValue(Notification_List.class);

                admin_notific7Text.setText(notification_list.getEmployNotification());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ///////
        databaseReference.child("notification").child("n8").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Notification_List notification_list=snapshot.getValue(Notification_List.class);

                admin_notific8Text.setText(notification_list.getEmployNotification());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ///////







        admin_employeNotitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String noti1 = admin_notific1Text.getText().toString();
                String noti2 = admin_notific2Text.getText().toString();
                String noti3 = admin_notific3Text.getText().toString();
                String noti4 = admin_notific4Text.getText().toString();
                String noti5 = admin_notific5Text.getText().toString();
                String noti6 = admin_notific6Text.getText().toString();
                String noti7 = admin_notific7Text.getText().toString();
                String noti8 = admin_notific8Text.getText().toString();


                /////////

                HashMap notification1hashMap = new HashMap();

                notification1hashMap.put("employNotification", noti1);


                databaseReference.child("notification").child("n1").updateChildren(notification1hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }
                });
                /////////

                /////////

                HashMap notification2hashMap = new HashMap();

                notification2hashMap.put("employNotification", noti2);


                databaseReference.child("notification").child("n2").updateChildren(notification2hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }
                });
                /////////

                /////////

                HashMap notification3hashMap = new HashMap();

                notification3hashMap.put("employNotification", noti3);


                databaseReference.child("notification").child("n3").updateChildren(notification3hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }
                });

                //

                /////////

                HashMap notification4hashMap = new HashMap();

                notification4hashMap.put("employNotification", noti4);


                databaseReference.child("notification").child("n4").updateChildren(notification4hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }
                });


                /////////

                HashMap notification5hashMap = new HashMap();

                notification5hashMap.put("employNotification", noti5);


                databaseReference.child("notification").child("n5").updateChildren(notification5hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }
                });

                /////////

                HashMap notification6hashMap = new HashMap();

                notification6hashMap.put("employNotification", noti6);


                databaseReference.child("notification").child("n6").updateChildren(notification6hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }
                });


                HashMap notification7hashMap = new HashMap();

                notification7hashMap.put("employNotification", noti7);


                databaseReference.child("notification").child("n7").updateChildren(notification7hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }
                });


                /////////

                HashMap notification8hashMap = new HashMap();

                notification8hashMap.put("employNotification", noti8);

                databaseReference.child("notification").child("n8").updateChildren(notification8hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }
                });


                Toast.makeText(Admin_notification_Activity.this, "Successfully updated", Toast.LENGTH_SHORT).show();

            }
        });





    }
}