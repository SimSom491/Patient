package com.example.patient;

import android.content.Context;
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

public class PatientItemAdapter extends RecyclerView.Adapter<PatientItemAdapter.ViewHolder> implements Filterable {

    private ArrayList<PatientItem> mPationsData;
    private ArrayList<PatientItem> mPationsDataAll;
    private  Context mContext;
    private int lastPosition=-1;

    PatientItemAdapter(Context context, ArrayList<PatientItem> itemsData){
        this.mPationsData= itemsData;
        this.mPationsDataAll = itemsData;
        this.mContext=context;


    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.beteg,parent,false));
    }

    @Override
    public void onBindViewHolder(PatientItemAdapter.ViewHolder holder, int position) {
        PatientItem currentItem = mPationsData.get(position);
        holder.bindTo(currentItem);
        if(holder.getAdapterPosition()> lastPosition){
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slider);
            holder.itemView.startAnimation(animation);
            lastPosition=holder.getAdapterPosition();
        }
    }

    @Override
    public int getItemCount() {
        return mPationsData.size();
    }

    @Override
    public Filter getFilter() {
        return patientFilter;
    }

    private Filter patientFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<PatientItem> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if(constraint==null|| constraint.length()==0){
                results.count= mPationsDataAll.size();
                results.values= mPationsDataAll;
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (PatientItem item : mPationsDataAll) {
                    if (item.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
                results.count= filteredList.size();
                results.values= filteredList;
            }


            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mPationsData = (ArrayList) results.values;
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mPatientName;
        private TextView mActive;


        public ViewHolder(@NonNull View itemView){
            super(itemView);
             mPatientName=itemView.findViewById(R.id.itemnev);
             mActive=itemView.findViewById(R.id.itemactive);
             itemView.findViewById(R.id.itemmore).setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     System.out.println("részletek");
                 }
             });

        }

        public void bindTo(PatientItem currentItem) {

            mPatientName.setText(currentItem.getName());
            mActive.setText(currentItem.isActive()?"Active":"Not Active");
            Glide.with(mContext);
        }
    }
}


