package com.saya.coco;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.saya.coco.db.NotificationModel;

import java.util.List;

public class NotificationRecyclerView extends RecyclerView.Adapter<NotificationRecyclerView.ViewHolder> {

    private List<NotificationModel> myEventList;
    private LayoutInflater mInflater;
    private NotificationRecyclerView.ItemClickListener mClickListener;

    // data is passed into the constructor
            public NotificationRecyclerView(Context context, List<NotificationModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.myEventList = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public NotificationRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.notification_item_list, parent, false);
        return new NotificationRecyclerView.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(NotificationRecyclerView.ViewHolder holder, int position) {
        holder.myNotificationView.setText(myEventList.get(position).notification);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return myEventList.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myNotificationView;
        CardView eventCardView;

        ViewHolder(View itemView) {
            super(itemView);
            myNotificationView = itemView.findViewById(R.id.tvNotificationName);
            eventCardView = itemView.findViewById(R.id.titleCardView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    NotificationModel getItem(int id) {
        return myEventList.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(NotificationRecyclerView.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
