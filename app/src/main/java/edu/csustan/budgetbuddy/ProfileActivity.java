// written by: Jashandeep Singh
// tested by: Stephanie, Jashan, Chris, Jorge and Natasha
// debugged by: Jashandeep Singh
package edu.csustan.budgetbuddy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvEmail;

    ImageButton budget_calculator_button;
    ImageButton building_credit_button;
    ImageButton saving_goal_button;
    ImageButton tracking_expense_button;

    // This function will have the image so that the user can click on it to navigate through the application
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        }
        ParseUser currentUser = ParseUser.getCurrentUser();

        // Activity connection with the image
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
