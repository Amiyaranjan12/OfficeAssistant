package com.example.officeassistant.Chat_page;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.officeassistant.Admin.employ_list.Model.Admin_employListModel;
import com.example.officeassistant.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ChatAdapter extends FirebaseRecyclerAdapter<Admin_employListModel,ChatAdapter.chatHolder> {

    Context context;

    public ChatAdapter(@NonNull FirebaseRecyclerOptions<Admin_employListModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull chatHolder holder, int position, @NonNull Admin_employListModel model) {



        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference dReference=firebaseDatabase.getReference(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("FriendList");

        //

        if(model.getUserfullName()!=null){

            //

            dReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(model.getUserAcode())){
                        holder. ChatName.setText(model.getUserfullName().toUpperCase());
                        Picasso.get().load(model.getImage()).resize(150, 150).
                                into(holder.chat_image);
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









    }

    @NonNull
    @Override
    public chatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_row, parent, false);

        return new ChatAdapter.chatHolder(view);
    }

    class chatHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView ChatName;
        ImageView chat_image;
        public chatHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ChatName=itemView.findViewById(R.id.ChatName);
            chat_image=itemView.findViewById(R.id.chat_image);

        }

        @Override
        public void onClick(View v) {
            context=itemView.getContext();
            int rowPosition=getAdapterPosition();
            Intent intent=new Intent(context, Message_Activity.class);
            intent.putExtra("userAcessCode",getItem(rowPosition).getUserAcode());
            intent.putExtra("UserName",getItem(rowPosition).getUserfullName());
            intent.putExtra("UserImage",getItem(rowPosition).getImage());
            intent.putExtra("Designation",getItem(rowPosition).getUserDesignation());

            context.startActivity(intent);


        }
    }
}
