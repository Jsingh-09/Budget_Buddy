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

import edu.csustan.budgetbuddy.ExpensesAdapter;
import edu.csustan.budgetbuddy.GoalsAdapter;
import edu.csustan.budgetbuddy.R;
import edu.csustan.budgetbuddy.Saving;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

// written by: Natasha Garcia ( resuable code from Stephanie's AddFragment )
// tested by: Stephanie, Jashan, Chris, and Jorge
// debugged by:  Stephanie



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


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvGoals);

        adapter.setOnItemClickListener(new GoalsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onItemLongClicked(int position) {


            }
        });


    }
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.deleteItem(viewHolder.getAdapterPosition());

                allGoals.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                Toast.makeText(getContext(), "item deleted", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent))
                        .addSwipeRightActionIcon(R.drawable.ic_baseline_delete_forever_24_white)
                        .create()
                        .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };


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





