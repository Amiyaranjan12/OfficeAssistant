package com.example.officeassistant.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.officeassistant.Admin.Admin_event.Admin_employ_eventActivity;
import com.example.officeassistant.Admin.Update_profile.Admin_UserUpdate_Profile_Activity;
import com.example.officeassistant.Admin.Update_profile.Admin_userProfileEdit_Model;
import com.example.officeassistant.Admin.admin_notification.Admin_notification_Activity;
import com.example.officeassistant.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin_employeeEditHome_Activity extends AppCompatActivity {

    private Bundle extra;

    private TextView admin_textEmployeName,admin_employMailText,admin_removeUserBtn,admin_updateNotiBtn,admin_eventAddBtn
            ,admin_salarySlipBtn;
    private CardView editProfile_btn;
    private ImageView admin_edithomeBack;
    private AlertDialog.Builder alertDialog;

    private FirebaseDatabase firebaseDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_employee_edit_home_layout);


        admin_textEmployeName=findViewById(R.id.admin_textEmployeName);
        admin_employMailText=findViewById(R.id.admin_employMailText);
        editProfile_btn=findViewById(R.id.editProfile_btn);
        admin_edithomeBack=findViewById(R.id.admin_edithomeBack);
        admin_removeUserBtn=findViewById(R.id.admin_removeUserBtn);
        admin_updateNotiBtn=findViewById(R.id.admin_updateNotiBtn);
        admin_eventAddBtn=findViewById(R.id.admin_eventAddBtn);
        admin_salarySlipBtn=findViewById(R.id.admin_salarySlipBtn);

        extra=getIntent().getExtras();
        String userLId=extra.getString("userAcessCode");
        firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference(String.valueOf(userLId));




        admin_salarySlipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Intent intent=new Intent(Admin_employeeEditHome_Activity.this,admin_salarySlip_Activity.class);
                intent.putExtra("usercode",userLId);
              startActivity(intent);
            }
        });

      admin_removeUserBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

               /////
              alertDialog=new AlertDialog.Builder(Admin_employeeEditHome_Activity.this);
              alertDialog.setTitle("Alert!");
              alertDialog.setMessage("Do you want to delete user account?");
              alertDialog.setCancelable(false);

              alertDialog.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      ///

                      databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                          @Override
                          public void onComplete(@NonNull Task<Void> task) {

                          }
                      });

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


              ///////

          }
      });


        admin_edithomeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_employeeEditHome_Activity.this,Admin_employList_Activity.class);
                startActivity(intent);

            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Admin_userProfileEdit_Model modelObject=dataSnapshot.getValue(Admin_userProfileEdit_Model.class);


                        admin_textEmployeName.setText(modelObject.getUserName());
                        admin_employMailText.setText(modelObject.getUserMail());


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Toast.makeText(EmployHome_Activity.this, "Errror", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }).start();






        editProfile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_employeeEditHome_Activity.this, Admin_UserUpdate_Profile_Activity.class);

                intent.putExtra("usercode",userLId);
                startActivity(intent);
            }
        });

        admin_updateNotiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_employeeEditHome_Activity.this, Admin_notification_Activity.class);

                intent.putExtra("useracode",userLId);
                startActivity(intent);
            }
        });



        admin_eventAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_employeeEditHome_Activity.this, Admin_employ_eventActivity.class);

                intent.putExtra("useracode",userLId);
                startActivity(intent);
            }
        });








    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Admin_employeeEditHome_Activity.this,Admin_employList_Activity.class);
        startActivity(intent);
    }
}