package com.saya.coco;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.saya.coco.db.Club;

import java.util.List;

public class ClubRecyClerView extends RecyclerView.Adapter<ClubRecyClerView.ViewHolder> {


    private List<Club> myClubs;
    private LayoutInflater mInflater;
    private NotificationRecyclerView.ItemClickListener mClickListener;

    // data is passed into the constructor
    public ClubRecyClerView(Context context, List<Club> data) {
        this.mInflater = LayoutInflater.from(context);
        this.myClubs = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ClubRecyClerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.club_item_list, parent, false);
        return new ClubRecyClerView.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ClubRecyClerView.ViewHolder holder, int position) {
        holder.myClubName.setText(myClubs.get(position).clubName);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return myClubs.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myClubName;
        CardView eventCardView;

        ViewHolder(View itemView) {
            super(itemView);
            myClubName = itemView.findViewById(R.id.tvNotificationName);
            eventCardView = itemView.findViewById(R.id.titleCardView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Club getItem(int id) {
        return myClubs.get(id);
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
