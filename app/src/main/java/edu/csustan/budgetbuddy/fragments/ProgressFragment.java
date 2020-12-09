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

            }

            @Override
            public void onItemLongClicked(int position) {



            }
        });

    }



        //create the data source
        private void queryGoals() {
            ParseQuery<Saving> query = ParseQuery.getQuery(Saving.class);
            query.include(Saving.KEY_USER);
            query.whereEqualTo(Saving.KEY_USER, ParseUser.getCurrentUser());
            query.addDescendingOrder(Saving.KEY_CREATED);
            query.findInBackground(new FindCallback<Saving>() {

                @Override
                public void done(List<Saving> savings, ParseException e) {
                    if (e != null) {
                        Log.e(TAG, "Issue with goal", e);
                    }
                    for (Saving saving : savings) {
                        Log.i(TAG, "Saving:" + saving.getGoal() + ", GoalAmount" + saving.getGoalAmount() + ", AmountSaved" + saving.getAmountSaved() + ", Created" + saving.getCreatedAt() + ", Description" + saving.getDescription() + "Category" + saving.getCategory());
                    }
                    allGoals.addAll(savings);
                    adapter.notifyDataSetChanged();

                }
            });
        }
    }



