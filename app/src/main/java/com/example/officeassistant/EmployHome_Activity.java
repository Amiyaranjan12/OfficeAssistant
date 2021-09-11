package com.example.officeassistant;

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
import android.widget.Toast;

import com.example.officeassistant.Chat_page.ChatHome_Activity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EmployHome_Activity extends AppCompatActivity {

    private CardView employ_profileCard,employ_attendanceCard,employ_tasktodoCard,employ_salaryCard,
            employ_ChatCard,employ_eventsCard,employ_notificationCard,employ_AboutCard,employ_helpdeskCard;

    private TextView EmployName_home,profile_time, textviewDate,textViewDay;
    private ImageView elogout_btn;

    //databse11
    private static final String TAG ="EmployHome_Activity";
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    private ImageView image_homeprofile;


    //alert
    private AlertDialog.Builder alertDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employ_home);

        employ_profileCard=findViewById(R.id.employ_profileCard);
        employ_attendanceCard=findViewById(R.id.employ_attendanceCard);
        employ_tasktodoCard=findViewById(R.id.employ_tasktodoCard);
        employ_salaryCard=findViewById(R.id.employ_salaryCard);
        employ_ChatCard=findViewById(R.id.employ_ChatCard);
      //  employ_teamdeskCard=findViewById(R.id.employ_teamdeskCard);
        employ_eventsCard=findViewById(R.id.employ_eventsCard);
        employ_notificationCard=findViewById(R.id.employ_notificationCard);
        employ_AboutCard=findViewById(R.id.employ_AboutCard);
     //   employ_myworkCard=findViewById(R.id.employ_myworkCard);
        employ_helpdeskCard=findViewById(R.id.employ_helpdeskCard);
        image_homeprofile=findViewById(R.id.image_homeprofile);
        profile_time=findViewById(R.id.profile_time);

        elogout_btn=findViewById(R.id.elogout_btn);

        EmployName_home=findViewById(R.id.EmployName_home);
        textviewDate =findViewById(R.id.textviewDate);
        textViewDay=findViewById(R.id.textViewDay);

        profile_time.setText("HAVE A NICE DAY");


        //date

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");

        SimpleDateFormat format1=new SimpleDateFormat("EEEE");

        textviewDate.setText(format.format(calendar.getTime()));
        textViewDay.setText(format1.format(calendar.getTime()));
        //

        ///text time

        texttime();

        /////logout

        elogout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutfunction();
            }
        });


        ///////data 1
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getUid());
        DatabaseReference databaseReference1=databaseReference.child("image");
      //  EmployName_home.setText(firebaseAuth.getUid());

        ////////image

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String link=snapshot.getValue(String.class);
                Picasso.get().load(link).resize(150, 150).
                        into(image_homeprofile);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        /////image



       ///////////

        //profile

        employ_profileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EmployHome_Activity.this,Employ_Profile_Activity.class);
                startActivity(intent);

            }
        });


        //attendance
        employ_attendanceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(EmployHome_Activity.this,Employ_Attendance_Activity.class);
                startActivity(intent);

            }
        });


        //task_todo

        employ_tasktodoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(EmployHome_Activity.this, Employ_MailList_Activity.class);
                startActivity(intent);

            }
        });

        //salary

        employ_salaryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(EmployHome_Activity.this,Employ_Salary_Activity.class);
                startActivity(intent);

            }
        });
       //chat

        employ_ChatCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(EmployHome_Activity.this, ChatHome_Activity.class);
                startActivity(intent);

            }
        });


        //events

        employ_eventsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(EmployHome_Activity.this,Employ_Event_Activity.class);
                startActivity(intent);

            }
        });
        //notification

        employ_notificationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(EmployHome_Activity.this,Employ_notification_Activity.class);
                startActivity(intent);

            }
        });
        //reminder

        employ_AboutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(EmployHome_Activity.this, Employ_About_Activity.class);
                startActivity(intent);

            }
        });
        /*my works

        employ_myworkCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(EmployHome_Activity.this,Employ_Mywork_Activity.class);
                startActivity(intent);

            }
        });

         */
        //help desk

        employ_helpdeskCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(EmployHome_Activity.this,Employ_Helpdesk_Activity.class);
                startActivity(intent);

            }
        });

         /////////
    }
    ///////logout method
    private void logoutfunction() {

        alertDialog=new AlertDialog.Builder(EmployHome_Activity.this);
        alertDialog.setMessage("Do you want to logout?");
        alertDialog.setCancelable(false);

        alertDialog.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ///
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(EmployHome_Activity.this,"Logged out",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(EmployHome_Activity.this,LoginActivity.class);
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

    ////////exit



    private void exitfunction() {

        alertDialog=new AlertDialog.Builder(EmployHome_Activity.this);
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

    ///////

    private void texttime(){

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat format3=new SimpleDateFormat("HH");
        String time=format3.format(calendar.getTime());

        int a=Integer.parseInt(time);

        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getUid());

        if(a >=05 && a<12) {


            ///
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    EmployHomeProfile employHomeProfile=dataSnapshot.getValue(EmployHomeProfile.class);
                    EmployName_home.setText("GOOD MORNING"+" "+employHomeProfile.getUserName().toUpperCase());
                    //






                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Toast.makeText(EmployHome_Activity.this, "Errror", Toast.LENGTH_SHORT).show();

                }
            });
            ///

        }
        else if(a>=12 && a<=15) {

            ///
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    EmployHomeProfile employHomeProfile=dataSnapshot.getValue(EmployHomeProfile.class);
                    EmployName_home.setText("GOOD AFTERNOON"+" "+employHomeProfile.getUserName().toUpperCase());
                    //






                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Toast.makeText(EmployHome_Activity.this, "Errror", Toast.LENGTH_SHORT).show();

                }
            });
            ///

        }

        else if(a>15 && a<=19) {

            ///
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    EmployHomeProfile employHomeProfile=dataSnapshot.getValue(EmployHomeProfile.class);
                    EmployName_home.setText("GOOD EVENING"+" "+employHomeProfile.getUserName().toUpperCase());
                    //






                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Toast.makeText(EmployHome_Activity.this, "Errror", Toast.LENGTH_SHORT).show();

                }
            });
            ///

        }

        else if(a>19 && a<=24) {

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    EmployHomeProfile employHomeProfile=dataSnapshot.getValue(EmployHomeProfile.class);
                    EmployName_home.setText("GOOD NIGHT"+" "+employHomeProfile.getUserName().toUpperCase());
                    //






                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Toast.makeText(EmployHome_Activity.this, "Errror", Toast.LENGTH_SHORT).show();

                }
            });


        }
        else if(a>=01 && a<=05) {

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    EmployHomeProfile employHomeProfile=dataSnapshot.getValue(EmployHomeProfile.class);
                    EmployName_home.setText("GOOD NIGHT"+" "+employHomeProfile.getUserName().toUpperCase());
                    //






                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Toast.makeText(EmployHome_Activity.this, "Errror", Toast.LENGTH_SHORT).show();

                }
            });

        }



    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();

       exitfunction();
    }
}