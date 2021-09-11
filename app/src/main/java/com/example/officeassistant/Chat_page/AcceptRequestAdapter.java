package com.example.officeassistant.Chat_page;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.officeassistant.Admin.employ_list.Model.Admin_employListModel;
import com.example.officeassistant.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class AcceptRequestAdapter extends FirebaseRecyclerAdapter<Admin_employListModel,AcceptRequestAdapter.AcceptRequestHolder> {

    Context context;
    public AcceptRequestAdapter(@NonNull FirebaseRecyclerOptions<Admin_employListModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AcceptRequestHolder holder, int position, @NonNull Admin_employListModel model) {

        //////////////



        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("FriendList");

        DatabaseReference dReference=firebaseDatabase.getReference().child("FriendRequest").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        ////////////
        if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals(model.getUserAcode()))
        {

            holder.itemView.setVisibility(View.GONE);
            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
            params.height = 0;
            params.width = 0;
            holder.itemView.setLayoutParams(params);


        }
        if(model.getUserfullName()!=null){

            //

            dReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.hasChild(model.getUserAcode())){


                        //
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if (!snapshot.hasChild(model.getUserAcode())){

                                    holder. SFName.setText(model.getUserfullName().toUpperCase());
                                    Picasso.get().load(model.getImage()).resize(150, 150).
                                            into(holder.SFimage);
                                }

                                else {

                                    holder.itemView.setVisibility(View.GONE);
                                    ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
                                    params.height = 0;
                                    params.width = 0;
                                    holder.itemView.setLayoutParams(params);


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


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });





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
                DatabaseReference databaseReference=firebaseDatabase.getReference(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("FriendList");


                //hashmap for current user
                HashMap FHashmap=new HashMap();

                FHashmap.put(getItem(position).getUserAcode(),"1");


                databaseReference.updateChildren(FHashmap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                        Toast.makeText(context, "Now send message in chat activity",
                                Toast.LENGTH_SHORT).show();


                    }
                });






                ///////////

            }
        });

        holder.reject_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(model.getUserAcode())){
                            dReference.child(model.getUserAcode()).removeValue();
                            notifyDataSetChanged();

                            //
                            ///
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });









        ////////////

    }

    @NonNull
    @Override
    public AcceptRequestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.acceptfriend_row, parent, false);

        return new AcceptRequestAdapter.AcceptRequestHolder(view);
    }

    class AcceptRequestHolder extends RecyclerView.ViewHolder {

        TextView SFName;
        CardView Friendrequest_Btn,reject_Btn;
        ImageView SFimage;


        public AcceptRequestHolder(@NonNull View itemView) {
             super(itemView);
             SFName=itemView.findViewById(R.id.SFName);
             Friendrequest_Btn=itemView.findViewById(R.id.Friendrequest_Btn);
             SFimage=itemView.findViewById(R.id.SFimage);
             reject_Btn=itemView.findViewById(R.id.reject_Btn);
         }
     }
}
