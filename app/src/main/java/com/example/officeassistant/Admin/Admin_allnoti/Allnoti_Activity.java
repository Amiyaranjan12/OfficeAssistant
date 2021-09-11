package com.example.officeassistant.Admin.Admin_allnoti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.officeassistant.Admin.employ_list.Model.Admin_employListModel;
import com.example.officeassistant.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Allnoti_Activity extends AppCompatActivity {

    private CardView save_notiBtn;
    private EditText notification_allBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allnoti_activity);

        save_notiBtn=findViewById(R.id.save_notiBtn);
        notification_allBox=findViewById(R.id.notification_allBox);

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference dataReference=firebaseDatabase.getReference("Enotification");

        save_notiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /////




                String noti_meassage=notification_allBox.getText().toString();


                if (noti_meassage.equals("")){
                    Toast.makeText(Allnoti_Activity.this,"write notification",Toast.LENGTH_SHORT).show();
                }

                if (!noti_meassage.equals("")){

                    HashMap notification11hashMap = new HashMap();

                    notification11hashMap.put("notific",noti_meassage);


                    dataReference.updateChildren(notification11hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {

                            Intent intent=new Intent(Allnoti_Activity.this,NotificationEveryone_Activity.class);

                            startActivity(intent);
                        }
                    });

                }






                ///
            }
        });
    }
}