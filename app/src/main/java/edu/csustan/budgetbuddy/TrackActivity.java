package edu.csustan.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class TrackActivity extends AppCompatActivity {
    private RecyclerView rvExpenses;
    private Button btnAddNew;
    public static final String TAG = "TrackActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        rvExpenses = findViewById(R.id.rvExpenses);
        btnAddNew = findViewById(R.id.btnAddNew);

        queryExpenses();

    }

    private void queryExpenses() {
        ParseQuery<Expense> query = ParseQuery.getQuery(Expense.class);
        query.include(Expense.KEY_USER);
        query.findInBackground(new FindCallback<Expense>() {
            @Override
            public void done(List<Expense> expenses, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with expense", e);
                }
                for (Expense expense : expenses) {
                    Log.i(TAG, "Expense:" + expense.getAmount() + ", Location" + expense.getLocation());
                }

            }
        });
    }


}