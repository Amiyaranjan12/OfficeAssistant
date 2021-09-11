package com.example.officeassistant.Admin.employ_list.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.officeassistant.Admin.Admin_employeeEditHome_Activity;
import com.example.officeassistant.Admin.employ_list.Model.Admin_employListModel;
import com.example.officeassistant.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Admin_Employ_list_Adapter extends FirebaseRecyclerAdapter<Admin_employListModel, Admin_Employ_list_Adapter.Admin_Employ_list_Holder> {

    private Context context;

    public Admin_Employ_list_Adapter(@NonNull FirebaseRecyclerOptions<Admin_employListModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Admin_Employ_list_Holder holder, int position, @NonNull Admin_employListModel model) {



       if(model.getUserfullName()!=null){
            holder.admin_EmployNametxt.setText(model.getUserfullName().toUpperCase());
            holder.admin_employeDesignationtext.setText(model.getUserDesignation());


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
    public Admin_Employ_list_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admin_employlist_row_layout, parent, false);

        return new Admin_Employ_list_Adapter.Admin_Employ_list_Holder(view);

    }

    class Admin_Employ_list_Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView admin_EmployNametxt,admin_employeDesignationtext;
        ///////
        ///////




        public Admin_Employ_list_Holder(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();
            itemView.setOnClickListener(this);


            admin_EmployNametxt=itemView.findViewById(R.id.admin_EmployNametxt);
            admin_employeDesignationtext=itemView.findViewById(R.id.admin_employeDesignationtext);
        }

        @Override
        public void onClick(View v) {
            int rowPosition=getAdapterPosition();
             Intent intent=new Intent(context, Admin_employeeEditHome_Activity.class);
             intent.putExtra("userAcessCode",getItem(rowPosition).getUserAcode());
             intent.putExtra("adminUserName",getItem(rowPosition).getUserName());
            intent.putExtra("adminUserEmail",getItem(rowPosition).getUserMail());
             context.startActivity(intent);

           // Toast.makeText(context,getItem(rowPosition).getUserAcode(),Toast.LENGTH_SHORT).show();



        }
    }
}
