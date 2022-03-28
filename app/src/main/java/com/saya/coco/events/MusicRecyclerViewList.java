package com.saya.coco.events;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.saya.coco.R;
import com.saya.coco.ViewBeYouEvent;
import com.saya.coco.ViewMusicEvent;
import com.saya.coco.db.EventModel;

import java.util.ArrayList;
import java.util.List;

class MusicRecyclerViewList extends RecyclerView.Adapter<MusicRecyclerViewList.ViewHolder> {

    private List<EventModel> myEventList;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MusicRecyclerViewList(Context context, List<EventModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.myEventList = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.music_list_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.myEventView.setText(myEventList.get(position).eventTitle);

        holder.eventCardView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ViewMusicEvent.class);
            intent.putExtra("clubName",myEventList.get(position).clubName);
            intent.putExtra("eventTitle",myEventList.get(position).eventTitle);
            intent.putExtra("eventLocation",myEventList.get(position).eventLocation);
            intent.putExtra("eventDay",myEventList.get(position).eventDay);
            intent.putExtra("startTime",myEventList.get(position).startTime);
            intent.putExtra("endTime",myEventList.get(position).endTime);
            intent.putExtra("frequency",myEventList.get(position).frequency);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            view.getContext().startActivity(intent);
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return myEventList.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myEventView;
        CardView eventCardView;

        ViewHolder(View itemView) {
            super(itemView);
            myEventView = itemView.findViewById(R.id.tvEventName);
            eventCardView = itemView.findViewById(R.id.titleCardView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    EventModel getItem(int id) {
        return myEventList.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
