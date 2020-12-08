package edu.csustan.budgetbuddy;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GoalsAdapter extends RecyclerView.Adapter<GoalsAdapter.ViewHolder>  {

    private Context context;

    private List<Saving> savings;



    @NonNull
    @Override
    public GoalsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull GoalsAdapter.ViewHolder holder, int position) {
        Saving saving = savings.get(position);
        holder.bind(saving);

    }

    @Override
    public int getItemCount() {
        return savings.size();
    }

    public GoalsAdapter(Context context, List<Saving> savings) {
        this.context = context;
        this.savings = savings;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvGoalName;
        private TextView tvGoalCategory;
        private TextView tvGoalAmount;
        private TextView tvGoalDescription;
        private TextView tvGoalSaved;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemLocation = itemView.findViewById(R.id.tvItemLocation);
            tvItemType = itemView.findViewById(R.id.tvItemType);
            tvItemAmount = itemView.findViewById(R.id.tvItemAmount);
            tvDatePlaced = itemView.findViewById(R.id.tvDatePlaced);



        }

    }
