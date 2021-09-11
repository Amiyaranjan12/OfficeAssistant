package com.example.officeassistant.Admin.Admin_allnoti;

import android.graphics.Color;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.officeassistant.Admin.employ_list.Model.Admin_employListModel;
import com.example.officeassistant.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class NotieveryoneAdapter extends FirebaseRecyclerAdapter<Admin_employListModel, NotieveryoneAdapter.NotieveryoneHolder> {

    /*
    boolean isEnable=false;
    boolean isSelected=false;
    ArrayList<String>selectlist=new ArrayList<>();


     */

    public NotieveryoneAdapter(@NonNull FirebaseRecyclerOptions<Admin_employListModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NotieveryoneHolder holder, int position, @NonNull Admin_employListModel model) {



        if(model.getUserfullName()!=null){
            holder.admin_EmployNametxtnoti.setText(model.getUserfullName().toUpperCase());


        }

        else {

            holder.itemView.setVisibility(View.GONE);
            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
            params.height = 0;
            params.width = 0;
            holder.itemView.setLayoutParams(params);


        }
        /*
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!isEnable){
                    ActionMode.Callback callback=new ActionMode.Callback() {
                        @Override
                        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                            MenuInflater menuInflater=mode.getMenuInflater();
                            menuInflater.inflate(R.menu.memu,menu);

                            return true;
                        }

                        @Override
                        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                            isEnable=true;
                            clickitem(holder);
                            return false;
                        }

                        @Override
                        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                            int id=item.getItemId();
                            switch (id){
                                case R.id.menu_add:
                                    for(Strings:selectlist){

                                    }
                            }
                            return false;
                        }

                        @Override
                        public void onDestroyActionMode(ActionMode mode) {

                        }
                    };
                }
                return false;
            }

            private void clickitem(NotieveryoneHolder holder) {
                String ab=selectlist.(getholder.getAdapterPosition()); //
                if (holder.noti_checkbox.getVisibility()==View.GONE){
                    holder.noti_checkbox.setVisibility(View.VISIBLE);
                    holder.itemView.setBackgroundColor(Color.LTGRAY);
                    selectlist.add(ab);
                }
                else {
                    holder.noti_checkbox.setVisibility(View.GONE);
                    holder.itemView.setBackgroundColor(Color.TRANSPARENT);
                    selectlist.remove(ab);
                }
            }
        });


         */
    }

    @NonNull
    @Override
    public NotieveryoneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_everyone_row, parent, false);

        return new NotieveryoneAdapter.NotieveryoneHolder(view);
    }

    class NotieveryoneHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView admin_EmployNametxtnoti;
        CardView admin_allnotiCardview;
      //  CheckBox noti_checkbox;

        public NotieveryoneHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            admin_EmployNametxtnoti=itemView.findViewById(R.id.admin_EmployNametxtnoti);
            admin_allnotiCardview=itemView.findViewById(R.id.admin_allnotiCardview);
           // noti_checkbox=itemView.findViewById(R.id.noti_checkbox);
        }

        @Override
        public void onClick(View v) {
            //
            //color change
            admin_allnotiCardview.setCardBackgroundColor(Color.GREEN);

            //
            FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
            DatabaseReference dataReference=firebaseDatabase.getReference("Enotification");

            dataReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   Admin_employListModel admin_employListModel =dataSnapshot.getValue(Admin_employListModel.class);

                    String temp=admin_employListModel.getNotific().toString();
                    int rowPosition=getAdapterPosition();

                   //////


                    FirebaseDatabase  firebaseDatabase= FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference=firebaseDatabase.getReference(String.valueOf(getItem(rowPosition).getUserAcode()));


                    HashMap notification1hashMap = new HashMap();

                    notification1hashMap.put("employNotification", temp);


                    databaseReference.child("notification").push().updateChildren(notification1hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {


                        }
                    });


                }

                //////

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Toast.makeText(EmployHome_Activity.this, "Errror", Toast.LENGTH_SHORT).show();

                }
            });

            //
        }
    }
}
