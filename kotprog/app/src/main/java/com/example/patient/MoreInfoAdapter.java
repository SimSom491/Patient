/*
package com.example.patient;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MoreInfoAdapter extends RecyclerView.Adapter<PatientItemAdapter.ViewHolder> {

    private PatientItem itemData;

    private Context mContext;
    private int lastPosition = -1;

    MoreInfoAdapter(Context context, PatientItem items) {
        this.itemData = itemData;
        this.mContext = context;


    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.more_info, parent, false));
    }

    @Override
    public void onBindViewHolder(PatientItemAdapter.ViewHolder holder, int position) {
        PatientItem currentItem = itemData;
        holder.bindTo(currentItem);
        if (holder.getAdapterPosition() > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slider);
            holder.itemView.startAnimation(animation);
            lastPosition = holder.getAdapterPosition();
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mPatientName;
        private TextView maddress;
        private TextView mphone;
        private TextView mdateOfBirth;
        private TextView mclosestRelative;
        private TextView mgender;
        private TextView mgeneralPractitioner;
        private TextView mpreflang;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mPatientName = itemView.findViewById(R.id.name);
            maddress = itemView.findViewById(R.id.adress);
            mphone = itemView.findViewById(R.id.phone);
            mdateOfBirth = itemView.findViewById(R.id.dateOfBurn);
            mclosestRelative = itemView.findViewById(R.id.closestTelative);
            mgender = itemView.findViewById(R.id.gender);
            mgeneralPractitioner = itemView.findViewById(R.id.itemactive);
            mpreflang = itemView.findViewById(R.id.generalPractitioner);

        }

        public void bindTo(PatientItem currentItem) {

            mPatientName.setText(currentItem.getName());
            maddress.setText(currentItem.getAddress());
            mphone.setText(currentItem.getPhone());
            mdateOfBirth.setText(currentItem.getDateOfBirth());
            mclosestRelative.setText(currentItem.getClosestRelative());
            mgender.setText(currentItem.getGender() ? "Férfi" : "Nő");
            mgeneralPractitioner.setText(currentItem.getGeneralPractitioner());
            mpreflang.setText(currentItem.getPreflang());
            Glide.with(mContext);
        }
    }
}


*/