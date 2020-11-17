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

//Stephanie's Code
//this fragment covers the expense list
public class ListItemsFragment extends Fragment {

    public static final String TAG = "ListItemsFragment";
    private RecyclerView rvExpenses;   //reference to recycler view
    private ExpensesAdapter adapter;
    private List<Expense> allExpenses;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    // The onCreateView method is called when Fragment should create its View object hierarchy
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_items, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Setup any handles to view objects here
        rvExpenses = view.findViewById(R.id.rvExpenses);

        allExpenses = new ArrayList<>();

        //create adapter
        adapter = new ExpensesAdapter(getContext(), allExpenses);
        //set adapter on the recycler view
        rvExpenses.setAdapter(adapter);
        //set the layout manager on the recycler view
        rvExpenses.setLayoutManager(new LinearLayoutManager(getContext()));

        queryExpenses();

    }
    //create the data source
    private void queryExpenses() {
        ParseQuery<Expense> query = ParseQuery.getQuery(Expense.class);
        query.include(Expense.KEY_USER);
        query.whereEqualTo(Expense.KEY_USER, ParseUser.getCurrentUser()); //pulls only the data  of the user who is signed in
        query.addDescendingOrder(Expense.KEY_CREATED); //places most recent expense at the top
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