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

import java.text.DecimalFormat;

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

                if(numAmountBalance.equals("") || numAverageExpenses.equals("") || numIncome.equals("") ){
                    Toast.makeText(getApplicationContext(), "Fill in empty fields",Toast.LENGTH_SHORT).show();
                }
                else if((notSingleDecimal(numAmountBalance) || notSingleDecimal(numAverageExpenses) || notSingleDecimal(numIncome))) {
                    Toast.makeText(getApplicationContext(), "Too many decimals",Toast.LENGTH_SHORT).show();
                }
                else if(numAmountBalance.equals(".") || numAverageExpenses.equals(".") || numIncome.equals(".")) {
                    Toast.makeText(getApplicationContext(), "Invalid input",Toast.LENGTH_SHORT).show();
                }
                else {
                    String numRemainingBalance = formatDecimalCurrency(String.valueOf(Double.parseDouble(numAmountBalance) - Double.parseDouble(numAverageExpenses) + Double.parseDouble(numIncome)));
                    String numTotalDisposable = formatDecimalCurrency(String.valueOf(Double.parseDouble(numIncome) - Double.parseDouble(numAverageExpenses)));
                    
                    if(Double.parseDouble(numRemainingBalance) >= 0) {
                        remainingBalance.setText("Remaining Balance: $" + formatCommasCurrency(numRemainingBalance));
                    }
                    else {
                        remainingBalance.setText("Remaining Balance: -$" + formatCommasCurrency(String.valueOf(Math.abs(Double.parseDouble(numRemainingBalance)))));
                    }
                    if(Double.parseDouble(numTotalDisposable) >= 0) {
                        totalDisposable.setText("Total Disposable: $" + formatCommasCurrency(numTotalDisposable));
                        balance.setText("Good Job!");
                        balance.setTextColor(Color.parseColor("#008000"));
                    }
                    else {
                        totalDisposable.setText("Total Disposable: -$" + formatCommasCurrency(String.valueOf(Math.abs(Double.parseDouble(numTotalDisposable)))));
                        balance.setText("You're spending too much");
                        balance.setTextColor(Color.parseColor("#DC143C"));
                    }
                }
            }
        });
    }

    private static String formatDecimalCurrency(String number) {
        DecimalFormat formatter = new DecimalFormat("###########0.00");
        return formatter.format(Double.parseDouble(number));
    }

    private static String formatCommasCurrency(String number) {
        DecimalFormat formatter = new DecimalFormat("###,###,###,##0.00");
        return formatter.format(Double.parseDouble(number));
    }

    private static boolean notSingleDecimal(String number) {
        int decimal = 0;
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == '.') {
                decimal += 1;
            }
        }
        return (decimal > 1);
    }
}