 package com.example.officeassistant.Admin.Admin_attend;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.officeassistant.Admin.Admin_attenImage_Activity;
import com.example.officeassistant.Admin.employ_list.Model.Admin_employListModel;
import com.example.officeassistant.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AdminAttendAdapter extends FirebaseRecyclerAdapter<Admin_employListModel,AdminAttendAdapter.AdminAttendance_Holder> {

    Context context;
    public AdminAttendAdapter(@NonNull FirebaseRecyclerOptions<Admin_employListModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdminAttendance_Holder holder, int position, @NonNull Admin_employListModel model) {



        

        if(model.getUserfullName()!=null){
            holder.admin_EmployNametxt.setText(model.getUserfullName().toUpperCase());

            if(model.getCid().equals("1")){
                holder.admin_attendCardview.setBackgroundColor(Color.rgb( 152,251,152));
                holder.Adimn_attendanceSwitch.setChecked(true);



            }

        }



        else {

            holder.itemView.setVisibility(View.GONE);
            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
            params.height = 0;
            params.width = 0;
            holder.itemView.setLayoutParams(params);


        }



        //////


        holder.Adimn_attendanceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Calendar calendar=Calendar.getInstance();
                SimpleDateFormat format=new SimpleDateFormat("yyyy,MM,dd");
                String ddate=format.format(calendar.getTime());

                FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
                DatabaseReference dReference=firebaseDatabase.getReference("Attendance").child(ddate);
                ///



                DatabaseReference ddReference=firebaseDatabase.getReference(getItem(position).getUserAcode());
                if (isChecked){

                    HashMap presentHashmap=new HashMap();

                    presentHashmap.put(getItem(position).getUserAcode(),"1");


                    dReference.updateChildren(presentHashmap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {



                        }
                    });




                    HashMap cdHashmap=new HashMap();

                    cdHashmap.put("cid","1");


                    ddReference.updateChildren(cdHashmap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {



                        }
                    });




                    // Toast.makeText(context,"checked",Toast.LENGTH_SHORT).show();
                }

                else {

                    HashMap AbsentHashmap=new HashMap();

                    AbsentHashmap.put(getItem(position).getUserAcode(),"0");


                    dReference.updateChildren(AbsentHashmap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {



                        }
                    });

                    HashMap cAHashmap=new HashMap();

                    cAHashmap.put("cid","0");


                    ddReference.updateChildren(cAHashmap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {



                        }
                    });


                }

            }
        });

        //////











    }

    @NonNull
    @Override
    public AdminAttendance_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admin_attendance_row_layout, parent, false);

        return new com.example.officeassistant.Admin.Admin_attend.AdminAttendAdapter.AdminAttendance_Holder(view);
    }

    class AdminAttendance_Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView admin_EmployNametxt;
        CardView admin_attendCardview;
        Switch Adimn_attendanceSwitch;



        public AdminAttendance_Holder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            context=itemView.getContext();
            Adimn_attendanceSwitch=itemView.findViewById(R.id.Adimn_attendanceSwitch);
            admin_EmployNametxt=itemView.findViewById(R.id.admin_EmployNametxt);
            admin_attendCardview=itemView.findViewById(R.id.admin_attendCardview);
            //








            /////
        }

        @Override
        public void onClick(View v) {
            int rowPosition=getAdapterPosition();
            Intent intent=new Intent(context, Admin_attenImage_Activity.class);
            intent.putExtra("userAcessCode",getItem(rowPosition).getUserAcode());
            context.startActivity(intent);
        }
    }
}
