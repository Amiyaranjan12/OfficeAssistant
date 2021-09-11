package com.example.officeassistant.notification_page.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.officeassistant.R;
import com.example.officeassistant.notification_page.Model.Notification_List;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class NotificAdapter extends FirebaseRecyclerAdapter<Notification_List, NotificAdapter.NotificationHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public NotificAdapter(@NonNull FirebaseRecyclerOptions<Notification_List> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NotificationHolder holder, int position, @NonNull Notification_List model) {

        holder.notification_txt.setText("*"+" "+model.getEmployNotification());

    }

    @NonNull
    @Override
    public NotificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_listrow_layout, parent, false);

        return new NotificationHolder(view);
    }

    class NotificationHolder extends RecyclerView.ViewHolder{

        TextView notification_txt;


        public NotificationHolder(@NonNull View itemView) {
            super(itemView);

            notification_txt=itemView.findViewById(R.id.notification_txt);
        }
    }

}

