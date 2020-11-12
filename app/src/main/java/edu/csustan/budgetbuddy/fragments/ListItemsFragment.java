package edu.csustan.budgetbuddy.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import edu.csustan.budgetbuddy.Expense;
import edu.csustan.budgetbuddy.ExpensesAdapter;
import edu.csustan.budgetbuddy.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListItemsFragment#} factory method to
 * create an instance of this fragment.
 */
public class ListItemsFragment extends Fragment {

    public static final String TAG = "ListItemsFragment";
    private RecyclerView rvExpenses;
    private ExpensesAdapter adapter;
    private List<Expense> allExpenses;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_items, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvExpenses = view.findViewById(R.id.rvExpenses);

        allExpenses = new ArrayList<>();
        adapter = new ExpensesAdapter(getContext(), allExpenses);

        rvExpenses.setAdapter(adapter);

        rvExpenses.setLayoutManager(new LinearLayoutManager(getContext()));

        queryExpenses();

    }
    private void queryExpenses() {
        ParseQuery<Expense> query = ParseQuery.getQuery(Expense.class);
        query.include(Expense.KEY_USER);
        query.whereEqualTo(Expense.KEY_USER, ParseUser.getCurrentUser());
        query.addDescendingOrder(Expense.KEY_CREATED);
        query.findInBackground(new FindCallback<Expense>() {
            @Override
            public void done(List<Expense> expenses, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with expense", e);
                }
                for (Expense expense : expenses) {
                    Log.i(TAG, "Expense:" + expense.getAmount() + ", Location" + expense.getLocation() + ", Item" + expense.getItemType());
                }
                allExpenses.addAll(expenses);
                adapter.notifyDataSetChanged();

            }
        });


    }
}