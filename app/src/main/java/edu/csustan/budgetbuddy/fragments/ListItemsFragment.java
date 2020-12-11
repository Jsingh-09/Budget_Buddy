package edu.csustan.budgetbuddy.fragments;

import android.graphics.Canvas;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import edu.csustan.budgetbuddy.Expense;
import edu.csustan.budgetbuddy.ExpensesAdapter;
import edu.csustan.budgetbuddy.R;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;


//Stephanie's Code
//this fragment covers the expense list
public class ListItemsFragment extends Fragment {

    public static final String TAG = "ListItemsFragment";
    private RecyclerView rvExpenses;   //reference to recycler view
    private ExpensesAdapter adapter;
    private List<Expense> allExpenses;
   //Toolbar toolbar;




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
        //androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) view.findViewById(R.id.toolbar);
     // ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
       //


        rvExpenses = view.findViewById(R.id.rvExpenses);

        allExpenses = new ArrayList<>();

        //create adapter
        adapter = new ExpensesAdapter(getContext(), allExpenses);
        //set adapter on the recycler view
        rvExpenses.setAdapter(adapter);
        //set the layout manager on the recycler view
        rvExpenses.setLayoutManager(new LinearLayoutManager(getContext()));

        queryExpenses();



        //part of the swipe delete feature
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvExpenses);

        adapter.setOnItemClickListener(new ExpensesAdapter.OnItemClickListener() {
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

    //swipe delete feature
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,  ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            //Delete from list and database
            adapter.deleteItem(viewHolder.getAdapterPosition());

            allExpenses.remove(viewHolder.getAdapterPosition());
            adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            Toast.makeText(getContext(), "item deleted", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent ))
                    .addSwipeRightActionIcon(R.drawable.ic_baseline_delete_forever_24_white)
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

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
                    Log.i(TAG, "Expense:" + expense.getAmount() + ", Location" + expense.getLocation() + ", Item" + expense.getItemType() +", Created" + expense.getCreatedAt());
                }
                allExpenses.addAll(expenses);
                adapter.notifyDataSetChanged();

            }
        });
    }



}