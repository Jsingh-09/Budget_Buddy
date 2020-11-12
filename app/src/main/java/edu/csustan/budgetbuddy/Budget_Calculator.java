package edu.csustan.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Budget_Calculator extends AppCompatActivity {

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
        setContentView(R.layout.activity_budget__calculator);

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
                String numAmountBalance = amountBalance.getText().toString();
                String numAverageExpenses = averageExpenses.getText().toString();
                String numIncome = income.getText().toString();



                if(numAmountBalance.matches("") || numAverageExpenses.matches("") || numIncome.matches("")){
                    Toast.makeText(getApplicationContext(), "Fill in empty fields",Toast.LENGTH_SHORT).show();
                }
                else {
                    int numRemainingBalance = Integer.parseInt(numAmountBalance) - Integer.parseInt(numAverageExpenses) + Integer.parseInt(numIncome);
                    int numTotalDisposable = Integer.parseInt(numIncome) - Integer.parseInt(numAverageExpenses);
                    
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
            }
        });
    }
}