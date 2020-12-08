package edu.csustan.budgetbuddy.fragments;

import android.graphics.Canvas;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import edu.csustan.budgetbuddy.Expense;

import edu.csustan.budgetbuddy.ExpensesAdapter;
import edu.csustan.budgetbuddy.GoalsAdapter;
import edu.csustan.budgetbuddy.R;
import edu.csustan.budgetbuddy.Saving;

// work on this one - list of goals
public class ProgressFragment extends Fragment {
    public static final String TAG = "ProgressFragment";
    private RecyclerView rvGoals;
    private GoalsAdapter adapter;
    private List<Saving> allGoals;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_progress, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        rvGoals = view.findViewById(R.id.rvGoals);
        allGoals = new ArrayList<>();

        //create adapter
        adapter = new GoalsAdapter(getContext(), allGoals);
        //set adapter on the recycler view
        rvGoals.setAdapter(adapter);
        //set the layout manager on the recycler view
        rvGoals.setLayoutManager(new LinearLayoutManager(getContext()));

        queryGoals();


       

        adapter.setOnItemClickListener(new GoalsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Toast.makeText(getContext(), "item", Toast.LENGTH_SHORT).show();
                // allExpenses.get(position);
            }

            @Override
            public void onItemLongClicked(int position) {
                // adapter.deleteItem(position);


            }
        });

    }



        //create the data source
        private void queryGoals() {
            ParseQuery<Saving> query = ParseQuery.getQuery(Saving.class);
            query.include(Saving.KEY_USER);
            query.whereEqualTo(Saving.KEY_USER, ParseUser.getCurrentUser()); //pulls only the data  of the user who is signed in
            query.addDescendingOrder(Saving.KEY_CREATED); //places most recent expense at the top
            query.findInBackground(new FindCallback<Saving>() {

                @Override
                public void done(List<Saving> allGoals, ParseException e) {
                    if (e != null) {
                        Log.e(TAG, "Issue with goal", e);
                    }
                    for (Saving saving : allGoals) {
                        Log.i(TAG, "Saving:" + saving.getGoal() + ", GoalAmount" + saving.getGoalAmount() + ", AmountSaved" + saving.getAmountSaved() + ", Created" + saving.getCreatedAt() + ", Description" + saving.getDescription() + "Category" + saving.getCategory());
                    }
                    allGoals.addAll(allGoals);
                    adapter.notifyDataSetChanged();

                }
            });
        }
    }



