package com.example.officeassistant.event_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.officeassistant.EmployHome_Activity;
import com.example.officeassistant.Employ_Event_Activity;
import com.example.officeassistant.Employ_notification_Activity;
import com.example.officeassistant.R;

public class Event_details extends AppCompatActivity {

    private TextView textView;
    private Bundle extra;

    private ImageView eventDBack_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details_layout);

        textView=findViewById(R.id.textView2);
        eventDBack_btn=findViewById(R.id.eventDback_btn);
        extra=getIntent().getExtras();
        textView.setText(extra.getString("eventDetails"));
        eventBackfunction();

    }

    private void eventBackfunction() {
        eventDBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Event_details.this, Employ_Event_Activity.class);
                startActivity(intent);
            }
        });


    }


}