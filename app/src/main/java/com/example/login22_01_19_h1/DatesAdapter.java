package com.example.login22_01_19_h1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DatesAdapter extends RecyclerView.Adapter<DatesAdapter.ViewHolder> {
    ArrayList<com.example.login22_01_19_h1.Dates> dates;
    Context context;
    //    OnDateListener mOnDateListener;
    final private DatesAdapter.OnDateListener m0onDateListener;

    public DatesAdapter(ArrayList<com.example.login22_01_19_h1.Dates> dates, OnDateListener onDateListener) {
        this.dates = dates;
        this.m0onDateListener = onDateListener;
    }

    public DatesAdapter(ArrayList<Dates> dates, MainActivity_Card_Date onDateListener, OnDateListener mOnClickListener) {
        this.m0onDateListener = mOnClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create View
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.datecard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // set text to the design
        holder.datetext.setText(dates.get(position).getTextdate());
        holder.dayname.setText(dates.get(position).getDaysname());
    }

    @Override
    public int getItemCount() {

        return dates.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Initialize variable
        TextView datetext;
        TextView dayname;

        //        OnDateListener onDateListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Assigning variable
            datetext = itemView.findViewById(R.id.datetext);
            dayname = itemView.findViewById(R.id.dayname);
//            this.onDateListener = onDateListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            m0onDateListener.onDateClick(getAdapterPosition());

        }
    }

    // to link the recyclerview with buutons on action
    public interface OnDateListener {
        void onDateClick(int position);
    }
}
