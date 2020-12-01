package edu.csustan.budgetbuddy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CreditAdapter extends RecyclerView.Adapter<CreditViewHolder> implements Filterable {

    ArrayList<CreditModel> data;
    ArrayList<CreditModel> backup;
    Context context;

    public CreditAdapter(ArrayList<CreditModel> data, Context context) {
        this.data = data;
        this.context = context;
        backup = new ArrayList<>(data);
    }

    @NonNull
    @Override
    public CreditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.item_credit,parent,false);
        return new CreditViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreditViewHolder holder, int position) {

        final CreditModel temp = data.get(position);

        holder.t1.setText(data.get(position).getHeader());
        holder.t2.setText(data.get(position).getDesc());
        holder.t3.setText(data.get(position).getInfo());
        holder.img.setImageResource(data.get(position).getImgname());


        holder.img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, BuildingCredit2.class);
                intent.putExtra("imagename", temp.getImgname());
                intent.putExtra("header", temp.getHeader());
                intent.putExtra("desc", temp.getDesc());
                intent.putExtra("info", temp.getInfo());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override //background
        protected FilterResults performFiltering(CharSequence keyword) {
            ArrayList<CreditModel> filtereddata = new ArrayList<>();

            if (keyword.toString().isEmpty())
                filtereddata.addAll(backup);
            else {
                for (CreditModel obj : backup) {
                    if (obj.getInfo().toString().toLowerCase().contains(keyword.toString().toLowerCase()))
                        filtereddata.add(obj);
                }
            }

            FilterResults results = new FilterResults();
            results.values = filtereddata;
            return results;

        }

        @Override //UI
        protected void publishResults(CharSequence constraint, FilterResults results) {

            data.clear();
            data.addAll((ArrayList<CreditModel>)results.values);
            notifyDataSetChanged();


        }
    };
}
