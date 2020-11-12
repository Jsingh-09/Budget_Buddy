package edu.csustan.budgetbuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ViewHolder> {
private Context context;
private List<Expense> expenses;

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

    @Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Expense expense = expenses.get(position);
        holder.bind(expense);
        }



@Override
public int getItemCount() {
        return expenses.size();
        }


class ViewHolder extends RecyclerView.ViewHolder{
    private TextView tvItemLocation;
    private TextView tvItemType;
    private TextView tvItemAmount;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        tvItemLocation = itemView.findViewById(R.id.tvItemLocation);
        tvItemType = itemView.findViewById(R.id.tvItemType);
        tvItemAmount = itemView.findViewById(R.id.tvItemAmount);
    }

    public void bind(Expense expense) {
        tvItemLocation.setText(expense.getLocation());
        tvItemType.setText(expense.getItemType());
        tvItemAmount.setText(expense.getAmount());


    }
}


}
