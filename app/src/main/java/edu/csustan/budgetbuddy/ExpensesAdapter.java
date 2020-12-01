package edu.csustan.budgetbuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Stephanie's Code
//Adapter for expense recyclerview
public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ViewHolder> {

    private Context context;

    private List<Expense> expenses;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onItemLongClicked(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;

    }


    public ExpensesAdapter(Context context, List<Expense> expenses) {
        this.context = context;
        this.expenses = expenses;
        }


        @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_expense, parent, false);
        return new ViewHolder(view);


    }

    public void deleteItem(int position) {
        
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Expense expense = expenses.get(position);
        holder.bind(expense);
        }




    @Override
    public int getItemCount() {
        return expenses.size();
        }

    //define viewholder
    class ViewHolder extends RecyclerView.ViewHolder{
    private TextView tvItemLocation;
    private TextView tvItemType;
    private TextView tvItemAmount;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        tvItemLocation = itemView.findViewById(R.id.tvItemLocation);
        tvItemType = itemView.findViewById(R.id.tvItemType);
        tvItemAmount = itemView.findViewById(R.id.tvItemAmount);

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

    public void bind(Expense expense) {
        //Bind the expense data to the view element
        tvItemLocation.setText(expense.getLocation());
        tvItemType.setText(expense.getItemType());
        tvItemAmount.setText(expense.getAmount());
       // tvUsername.setText(expense.getUser().getUsername());


    }
}



}
