package com.example.officeassistant.event_page.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.officeassistant.Employ_Event_Activity;
import com.example.officeassistant.R;
import com.example.officeassistant.event_page.Event_details;
import com.example.officeassistant.event_page.Model.Event_list;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class EventAdapter extends FirebaseRecyclerAdapter<Event_list,EventAdapter.EventHolder> {


    //
     Context context;


    public EventAdapter(@NonNull FirebaseRecyclerOptions<Event_list> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull EventHolder holder, int position, @NonNull Event_list model) {

        holder.event_txt.setText(model.geteEvent());


    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_listrow_layout, parent, false);

        return new EventAdapter.EventHolder(view);

    }

    class EventHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView event_txt;
        public EventHolder(@NonNull View itemView) {
            super(itemView);

            context=itemView.getContext();


            event_txt=itemView.findViewById(R.id.event_txt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int rowPosition=getAdapterPosition();

        //  Toast.makeText(context,"position"+rowPosition,Toast.LENGTH_SHORT).show();
          Intent intent=new Intent(context,Event_details.class);
          intent.putExtra("eventDetails",getItem(rowPosition).eDetails);

          context.startActivity(intent);


        }
    }
}
