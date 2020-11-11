package edu.csustan.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /* added a comment to see if I could commit - Chris */
    //adding comment - Steph
    //added a comment - p

    private Button btnBudgetCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBudgetCalc = (Button) findViewById(R.id.btnBudgetCalc);
        btnBudgetCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBudgetCalc();
            }
        });
    }

    public void openBudgetCalc() {
        Intent intent = new Intent(this, Budget_Calculator.class);
        startActivity(intent);
    }

}