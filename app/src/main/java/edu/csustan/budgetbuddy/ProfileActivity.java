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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

import edu.csustan.budgetbuddy.fragments.AddFragment;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvEmail;

    Spinner spinner;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ParseUser currentUser = ParseUser.getCurrentUser();


        spinner = findViewById(R.id.spinner);



        ArrayAdapter<String> adapter = new ArrayAdapter<>(ProfileActivity.this,R.layout.custom_spinner, getResources().getStringArray(R.array.Items));



        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Items")){

                }
                else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

                    if (parent.getItemAtPosition(position).equals("Budget Calculator")) {
                        Intent intent = new Intent(ProfileActivity.this, Budget_Calculator.class);
                        startActivity(intent);
                    } else if (parent.getItemAtPosition(position).equals("Home")) {
                        Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                    }
                    else if (parent.getItemAtPosition(position).equals("Track my Expenses")) {
                        Intent intent = new Intent(ProfileActivity.this, Track_Expenses.class);
                        startActivity(intent);

                    }
                    else if (parent.getItemAtPosition(position).equals("Building Credit")) {
                        Intent intent = new Intent(ProfileActivity.this, BuildingCreditActivity.class);
                        startActivity(intent);

                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);

        if(currentUser!=null) {
            tvName.setText(currentUser.getString("name"));
            tvEmail.setText(currentUser.getString("email"));
        }

    }

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