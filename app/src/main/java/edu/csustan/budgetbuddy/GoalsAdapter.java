package edu.csustan.budgetbuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// written by: Natasha Garcia ( resuable code from Stephanie's ExpensesAdapter )
// tested by: Stephanie, Jashan, Chris, and Jorge
// debugged by:  Natasha


public class GoalsAdapter extends RecyclerView.Adapter<GoalsAdapter.ViewHolder> {

    private Context context;

    private List<Saving> savings;
    private GoalsAdapter.OnItemClickListener mListener;


    public interface OnItemClickListener {
        void onItemClick(int position);
        void onItemLongClicked(int position);
    }
    public void setOnItemClickListener(GoalsAdapter.OnItemClickListener listener){
        mListener = listener;

    }

    public GoalsAdapter(Context context, List<Saving> savings) {
        this.context = context;
        this.savings = savings;
    }

    @NonNull
    @Override
    public GoalsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_goal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Saving saving = savings.get(position);
        holder.bind(saving);

    }

    public void deleteItem(int position) {
        Saving saving = savings.get(position);
        saving.deleteInBackground();

    }


    @Override
    public int getItemCount() {
        return savings.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvGoalName;
        private TextView tvGoalCategory;
        private TextView tvGoalDescription;
        private TextView tvGoalSaved;
        private TextView tvGoalAmount;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGoalName = itemView.findViewById(R.id.tvGoalName);
            tvGoalCategory = itemView.findViewById(R.id.tvGoalCategory);
            tvGoalDescription = itemView.findViewById(R.id.tvGoalDescription);
            tvGoalSaved = itemView.findViewById(R.id.tvGoalSaved);
            tvGoalAmount = itemView.findViewById(R.id.tvGoalAmount);


        }

        public void bind(Saving saving) {
            //Bind the expense data to the view element
            tvGoalName.setText(saving.getGoal());
            tvGoalCategory.setText(saving.getCategory());
            tvGoalDescription.setText(saving.getDescription());
            tvGoalSaved.setText(saving.getAmountSaved());
            tvGoalAmount.setText(saving.getGoalAmount());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemLongClicked(position);
                        }
                    }

                }
            });


        }
    }
}
