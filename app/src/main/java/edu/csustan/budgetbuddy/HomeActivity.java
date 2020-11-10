package edu.csustan.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.ParseUser;

public class HomeActivity extends AppCompatActivity {

    TextView tvName, tvEmail;

    Spinner spinner;

    TestFragment testFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ParseUser currentUser = ParseUser.getCurrentUser();


        spinner = findViewById(R.id.spinner);

        testFragment = new TestFragment();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(HomeActivity.this,R.layout.custom_spinner, getResources().getStringArray(R.array.fragments));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {

                // Add more cases base on our fragments(Pages)
                switch (i) {
                    case 0:
                        setFragment(testFragment);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tvEmail = findViewById(R.id.tvName);
        tvName = findViewById(R.id.tvName);

        if(currentUser!=null) {
            tvName.setText(currentUser.getString("name"));
            tvEmail.setText(currentUser.getString("name"));
        }

    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
    }

    public void login(View view) {
        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Loading ...");
        progress.show();
        ParseUser.logOut();
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}