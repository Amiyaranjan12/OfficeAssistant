package com.example.officeassistant.Chat_page;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.officeassistant.Admin.Admin_attenImage_Activity;
import com.example.officeassistant.Admin.employ_list.Model.Admin_employListModel;
import com.example.officeassistant.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class SendRequestAdapter extends FirebaseRecyclerAdapter<Admin_employListModel, SendRequestAdapter.sendRequest_Holder> {

    Context context;

    public SendRequestAdapter(@NonNull FirebaseRecyclerOptions<Admin_employListModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull sendRequest_Holder holder, int position, @NonNull Admin_employListModel model) {


        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference dReference=firebaseDatabase.getReference("FriendRequest").
                child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals(model.getUserAcode()))
        {

            holder.itemView.setVisibility(View.GONE);
            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
            params.height = 0;
            params.width = 0;
            holder.itemView.setLayoutParams(params);


        }

        //


        //

        if(model.getUserfullName()!=null){

            //
            dReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(model.getUserAcode())){
                        holder.itemView.setVisibility(View.GONE);
                        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
                        params.height = 0;
                        params.width = 0;
                        holder.itemView.setLayoutParams(params);
                    }
                    else {
                        holder. SFName.setText(model.getUserfullName().toUpperCase());
                        Picasso.get().load(model.getImage()).resize(150, 150).
                                into(holder.SFimage);

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            //




        }



        else {

            holder.itemView.setVisibility(View.GONE);
            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
            params.height = 0;
            params.width = 0;
            holder.itemView.setLayoutParams(params);


        }






        //////

        holder.Friendrequest_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /////////

                context=holder.itemView.getContext();
                FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
                DatabaseReference databaseReference=firebaseDatabase.getReference().child("FriendRequest");


                    //hashmap for current user
                    HashMap requestHashmap=new HashMap();

                    requestHashmap.put(getItem(position).getUserAcode(),"1");


                    databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(requestHashmap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {

                            Toast.makeText(context, "Your friend request is send",
                                    Toast.LENGTH_SHORT).show();


                        }
                    });






                ///////////

            }
        });



        //////











    }

    @NonNull
    @Override
    public sendRequest_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sendrequest_row, parent, false);

        return new SendRequestAdapter.sendRequest_Holder(view);
    }

    class sendRequest_Holder extends RecyclerView.ViewHolder  {

        TextView SFName;
        CardView Friendrequest_Btn;
        ImageView SFimage;




        public sendRequest_Holder(@NonNull View itemView) {
            super(itemView);


            SFName=itemView.findViewById(R.id.SFName);
            Friendrequest_Btn=itemView.findViewById(R.id.Friendrequest_Btn);
            SFimage=itemView.findViewById(R.id.SFimage);

        }


    }
}
