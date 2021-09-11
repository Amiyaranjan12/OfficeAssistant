package com.example.officeassistant.Admin.Admin_All;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.officeassistant.Admin.employ_list.Model.Admin_employListModel;
import com.example.officeassistant.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Allattend_Adapter extends FirebaseRecyclerAdapter<Admin_employListModel,Allattend_Adapter.Allattend_Holder> {

    String mm="null";

    public Allattend_Adapter(@NonNull FirebaseRecyclerOptions<Admin_employListModel> options,String monthNa) {
        super(options);

        mm=monthNa;


    }

    @Override
    protected void onBindViewHolder(@NonNull Allattend_Holder holder, int position, @NonNull Admin_employListModel model) {





        ////////////////////////store total day
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Attendance");
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MONTH, 0);
        calendar1.set(Calendar.DATE, calendar1.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date monthFirstDay = calendar1.getTime();
        calendar1.set(Calendar.DATE, calendar1.getActualMaximum(Calendar.DAY_OF_MONTH));
       // Date monthLastDay = calendar1.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy,MM,dd");
        String startDate = df.format(monthFirstDay);


      //  String endDate = df.format(monthLastDay);


        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat format=new SimpleDateFormat("yyyy,MM,dd");
        String ddate=format.format(calendar.getTime());
        Date date3=null ;

        try {
            date3=new SimpleDateFormat("yyyy,MM,dd").parse(ddate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String last_date  = (String) DateFormat.format("dd",   date3); // 20



        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String latestm_name = month_date.format(cal.getTime());
      ////////////////////////////

        if(model.getUserfullName()!=null){
            holder.textViewEName.setText(model.getUserfullName().toUpperCase());


            /////////////////////////////////




            if (latestm_name.equals(mm)){
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int countPnum=0;
                        for (DataSnapshot Datechild:snapshot.getChildren()) {

                            if(Datechild.child(getItem(position).getUserAcode()).exists() && Datechild.child(getItem(position).getUserAcode()).getValue().equals("1") &&
                                    Datechild.getKey().compareTo(startDate) >= 0 ){


                                countPnum=countPnum+1;
                                // attendPresentText.setText(String.valueOf(countPnum));

                            }


                        }

                        int tt=Integer.parseInt(last_date);
                        int countAbsent=tt- countPnum;


                        holder.textViewEpresent.setText(String.valueOf(countPnum));
                        holder.textViewEabsent.setText(String.valueOf(countAbsent));


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }else {
                if (mm.equals("January")){
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int countPFeb =0;
                            for (DataSnapshot Datechild:snapshot.getChildren()) {

                                if(Datechild.child(getItem(position).getUserAcode()).exists() && Datechild.child(getItem(position).getUserAcode()).getValue().equals("1") &&
                                        Datechild.getKey().compareTo("2021,01,01") >= 0 && Datechild.getKey().compareTo("2021,01,30") <= 0 ){


                                    countPFeb=countPFeb+1;
                                    // attendPresentText.setText(String.valueOf(countPnum));

                                }


                            }

                            int countAbsent=30- countPFeb;


                            holder.textViewEpresent.setText(String.valueOf(countPFeb));
                            holder.textViewEabsent.setText(String.valueOf(countAbsent));


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                if (mm.equals("February")){

                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int countPFeb =0;
                            for (DataSnapshot Datechild:snapshot.getChildren()) {

                                if(Datechild.child(getItem(position).getUserAcode()).exists() && Datechild.child(getItem(position).getUserAcode()).getValue().equals("1") &&
                                        Datechild.getKey().compareTo("2021,02,01") >= 0 && Datechild.getKey().compareTo("2021,02,28") <= 0 ){


                                    countPFeb=countPFeb+1;
                                    // attendPresentText.setText(String.valueOf(countPnum));

                                }


                            }

                            int countAbsent=28- countPFeb;


                            holder.textViewEpresent.setText(String.valueOf(countPFeb));
                            holder.textViewEabsent.setText(String.valueOf(countAbsent));


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
                if (mm.equals("March")){
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int countPFeb =0;
                            for (DataSnapshot Datechild:snapshot.getChildren()) {

                                if(Datechild.child(getItem(position).getUserAcode()).exists() && Datechild.child(getItem(position).getUserAcode()).getValue().equals("1") &&
                                        Datechild.getKey().compareTo("2021,03,01") >= 0 && Datechild.getKey().compareTo("2021,03,31") <= 0 ){


                                    countPFeb=countPFeb+1;
                                    // attendPresentText.setText(String.valueOf(countPnum));

                                }


                            }

                            int countAbsent=31- countPFeb;


                            holder.textViewEpresent.setText(String.valueOf(countPFeb));
                            holder.textViewEabsent.setText(String.valueOf(countAbsent));


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                if (mm.equals("April")){
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int countPFeb =0;
                            for (DataSnapshot Datechild:snapshot.getChildren()) {

                                if(Datechild.child(getItem(position).getUserAcode()).exists() && Datechild.child(getItem(position).getUserAcode()).getValue().equals("1") &&
                                        Datechild.getKey().compareTo("2021,04,01") >= 0 && Datechild.getKey().compareTo("2021,04,30") <= 0 ){


                                    countPFeb=countPFeb+1;
                                    // attendPresentText.setText(String.valueOf(countPnum));

                                }


                            }

                            int countAbsent=30- countPFeb;


                            holder.textViewEpresent.setText(String.valueOf(countPFeb));
                            holder.textViewEabsent.setText(String.valueOf(countAbsent));


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                if (mm.equals("May")){
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int countPFeb =0;
                            for (DataSnapshot Datechild:snapshot.getChildren()) {

                                if(Datechild.child(getItem(position).getUserAcode()).exists() && Datechild.child(getItem(position).getUserAcode()).getValue().equals("1") &&
                                        Datechild.getKey().compareTo("2021,05,01") >= 0 && Datechild.getKey().compareTo("2021,05,31") <= 0 ){


                                    countPFeb=countPFeb+1;
                                    // attendPresentText.setText(String.valueOf(countPnum));

                                }


                            }

                            int countAbsent=31- countPFeb;


                            holder.textViewEpresent.setText(String.valueOf(countPFeb));
                            holder.textViewEabsent.setText(String.valueOf(countAbsent));


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                if (mm.equals("June")){
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int countPFeb =0;
                            for (DataSnapshot Datechild:snapshot.getChildren()) {

                                if(Datechild.child(getItem(position).getUserAcode()).exists() && Datechild.child(getItem(position).getUserAcode()).getValue().equals("1") &&
                                        Datechild.getKey().compareTo("2021,06,01") >= 0 && Datechild.getKey().compareTo("2021,06,30") <= 0 ){


                                    countPFeb=countPFeb+1;
                                    // attendPresentText.setText(String.valueOf(countPnum));

                                }


                            }

                            int countAbsent=30- countPFeb;


                            holder.textViewEpresent.setText(String.valueOf(countPFeb));
                            holder.textViewEabsent.setText(String.valueOf(countAbsent));


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                if (mm.equals("July")){
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int countPFeb =0;
                            for (DataSnapshot Datechild:snapshot.getChildren()) {

                                if(Datechild.child(getItem(position).getUserAcode()).exists() && Datechild.child(getItem(position).getUserAcode()).getValue().equals("1") &&
                                        Datechild.getKey().compareTo("2021,07,01") >= 0 && Datechild.getKey().compareTo("2021,07,31") <= 0 ){


                                    countPFeb=countPFeb+1;
                                    // attendPresentText.setText(String.valueOf(countPnum));

                                }


                            }

                            int countAbsent=31- countPFeb;


                            holder.textViewEpresent.setText(String.valueOf(countPFeb));
                            holder.textViewEabsent.setText(String.valueOf(countAbsent));


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                if (mm.equals("August")){
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int countPFeb =0;
                            for (DataSnapshot Datechild:snapshot.getChildren()) {

                                if(Datechild.child(getItem(position).getUserAcode()).exists() && Datechild.child(getItem(position).getUserAcode()).getValue().equals("1") &&
                                        Datechild.getKey().compareTo("2021,08,01") >= 0 && Datechild.getKey().compareTo("2021,08,31") <= 0 ){


                                    countPFeb=countPFeb+1;
                                    // attendPresentText.setText(String.valueOf(countPnum));

                                }


                            }

                            int countAbsent=31- countPFeb;


                            holder.textViewEpresent.setText(String.valueOf(countPFeb));
                            holder.textViewEabsent.setText(String.valueOf(countAbsent));


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                if (mm.equals("September")){
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int countPFeb =0;
                            for (DataSnapshot Datechild:snapshot.getChildren()) {

                                if(Datechild.child(getItem(position).getUserAcode()).exists() && Datechild.child(getItem(position).getUserAcode()).getValue().equals("1") &&
                                        Datechild.getKey().compareTo("2021,09,01") >= 0 && Datechild.getKey().compareTo("2021,09,30") <= 0 ){


                                    countPFeb=countPFeb+1;
                                    // attendPresentText.setText(String.valueOf(countPnum));

                                }


                            }

                            int countAbsent=30- countPFeb;


                            holder.textViewEpresent.setText(String.valueOf(countPFeb));
                            holder.textViewEabsent.setText(String.valueOf(countAbsent));


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                if (mm.equals("October")){
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int countPFeb =0;
                            for (DataSnapshot Datechild:snapshot.getChildren()) {

                                if(Datechild.child(getItem(position).getUserAcode()).exists() && Datechild.child(getItem(position).getUserAcode()).getValue().equals("1") &&
                                        Datechild.getKey().compareTo("2021,10,01") >= 0 && Datechild.getKey().compareTo("2021,10,31") <= 0 ){


                                    countPFeb=countPFeb+1;
                                    // attendPresentText.setText(String.valueOf(countPnum));

                                }


                            }

                            int countAbsent=31- countPFeb;


                            holder.textViewEpresent.setText(String.valueOf(countPFeb));
                            holder.textViewEabsent.setText(String.valueOf(countAbsent));


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                if (mm.equals("November")){
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int countPFeb =0;
                            for (DataSnapshot Datechild:snapshot.getChildren()) {

                                if(Datechild.child(getItem(position).getUserAcode()).exists() && Datechild.child(getItem(position).getUserAcode()).getValue().equals("1") &&
                                        Datechild.getKey().compareTo("2021,11,01") >= 0 && Datechild.getKey().compareTo("2021,11,30") <= 0 ){


                                    countPFeb=countPFeb+1;
                                    // attendPresentText.setText(String.valueOf(countPnum));

                                }


                            }

                            int countAbsent=30- countPFeb;


                            holder.textViewEpresent.setText(String.valueOf(countPFeb));
                            holder.textViewEabsent.setText(String.valueOf(countAbsent));


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                if (mm.equals("December")){
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int countPFeb =0;
                            for (DataSnapshot Datechild:snapshot.getChildren()) {

                                if(Datechild.child(getItem(position).getUserAcode()).exists() && Datechild.child(getItem(position).getUserAcode()).getValue().equals("1") &&
                                        Datechild.getKey().compareTo("2021,12,01") >= 0 && Datechild.getKey().compareTo("2021,12,31") <= 0 ){


                                    countPFeb=countPFeb+1;
                                    // attendPresentText.setText(String.valueOf(countPnum));

                                }


                            }

                            int countAbsent=31- countPFeb;


                            holder.textViewEpresent.setText(String.valueOf(countPFeb));
                            holder.textViewEabsent.setText(String.valueOf(countAbsent));


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }




            ///////////////////////////////

        }
        else {

            holder.itemView.setVisibility(View.GONE);
            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
            params.height = 0;
            params.width = 0;
            holder.itemView.setLayoutParams(params);


        }

        ///////////////////////////////




    }


    @NonNull
    @Override
    public Allattend_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_attend_layout_row, parent, false);

        return new Allattend_Adapter.Allattend_Holder(view);
    }

    class Allattend_Holder extends RecyclerView.ViewHolder {
        TextView textViewEName, textViewEpresent,textViewEabsent;

        public Allattend_Holder(@NonNull View itemView) {
           super(itemView);
            textViewEName=itemView.findViewById(R.id.textViewEName);
            textViewEpresent =itemView.findViewById(R.id.textViewEpresent);
            textViewEabsent=itemView.findViewById(R.id.textViewEabsent);





        }
    }

}

