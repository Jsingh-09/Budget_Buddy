package edu.csustan.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /* added a comment to see if I could commit - Chris */
    //adding comment - Steph
    //added a comment - p

    private EditText amountBalance;
    private EditText averageExpenses;
    private EditText income;
    private Button calculateRemainingBalance;
    private TextView remainingBalance;
    private TextView totalDisposable;
    private TextView balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountBalance = (EditText) findViewById(R.id.etAmountBalance);
        averageExpenses = (EditText) findViewById(R.id.etAverageExpenses);
        income = (EditText) findViewById(R.id.etIncome);
        calculateRemainingBalance = (Button) findViewById(R.id.btnCalculateRemainingBalance);
        remainingBalance = (TextView) findViewById(R.id.tvRemainingBalance);
        totalDisposable = (TextView) findViewById(R.id.tvTotalDisposable);
        balance = (TextView) findViewById(R.id.tvBalance);

        calculateRemainingBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numAmountBalance = Integer.parseInt(amountBalance.getText().toString());
                int numAverageExpenses = Integer.parseInt(averageExpenses.getText().toString());
                int numIncome = Integer.parseInt(income.getText().toString());
                int numRemainingBalance = numAmountBalance - numAverageExpenses + numIncome;
                int numTotalDisposable = numIncome - numAverageExpenses;

                // check for null values after clicking button

                if(numRemainingBalance >= 0) {
                    remainingBalance.setText("Remaining Balance: $" + String.valueOf(numRemainingBalance));
                }
                else {
                    remainingBalance.setText("Remaining Balance: -$" + String.valueOf(Math.abs(numRemainingBalance)));
                }
                if(numTotalDisposable >= 0) {
                    totalDisposable.setText("Total Disposable: $" + String.valueOf(numTotalDisposable));
                    balance.setText("Good Job!");
                    balance.setTextColor(Color.parseColor("#008000"));
                }
                else {
                    totalDisposable.setText("Total Disposable: -$" + String.valueOf(Math.abs(numTotalDisposable)));
                    balance.setText("You're spending too much");
                    balance.setTextColor(Color.parseColor("#DC143C"));
                }
            }
        });
    }
}