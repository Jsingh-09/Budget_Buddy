// Jashandeep Singh
package edu.csustan.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

import edu.csustan.budgetbuddy.fragments.AddFragment;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvEmail;

    ImageButton budget_calculator_button;
    ImageButton building_credit_button;
    ImageButton saving_goal_button;
    ImageButton tracking_expense_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ParseUser currentUser = ParseUser.getCurrentUser();

        budget_calculator_button = (ImageButton) findViewById(R.id.budget_calculator);
        budget_calculator_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(ProfileActivity.this, Budget_Calculator.class);
                startActivity(intentLoadNewActivity);
            }
        });
        building_credit_button = (ImageButton) findViewById(R.id.building_credit);
        building_credit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(ProfileActivity.this, BuildingCredit.class);
                startActivity(intentLoadNewActivity);
            }
        });
        saving_goal_button = (ImageButton) findViewById(R.id.saving_button);
        saving_goal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(ProfileActivity.this, SavingGoalActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });
        tracking_expense_button = (ImageButton) findViewById(R.id.tracking_button);
        tracking_expense_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(ProfileActivity.this, Track_Expenses.class);
                startActivity(intentLoadNewActivity);
            }
        });

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);

        if(currentUser!=null) {
            tvName.setText(currentUser.getString("name"));
            tvEmail.setText(currentUser.getString("email"));
        }

    }

    // Displays a Loading page after the user login
    public void login(View view) {
        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Loading ...");
        progress.show();
        ParseUser.logOut();
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
