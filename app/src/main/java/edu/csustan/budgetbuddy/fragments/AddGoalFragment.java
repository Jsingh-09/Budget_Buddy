// written by: Stephanie Gamboa
// tested by: Stephanie, Jashan, Chris, and Jorge
// debugged by:  Stephanie Gamboa & Chris Gallo

package edu.csustan.budgetbuddy.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.text.DecimalFormat;

import edu.csustan.budgetbuddy.Expense;
import edu.csustan.budgetbuddy.R;
import edu.csustan.budgetbuddy.Saving;


public class AddGoalFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    public static String TAG = "AddGoalFragment";
    private EditText etSavingGoal;
    private EditText etDescription;
    private Spinner sGoal;
    private EditText sAmount;
    private EditText sStartAmount;
    private Button btnAddGoal;


    public AddGoalFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_goal, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etSavingGoal = view.findViewById(R.id.etSavingGoal);
        etDescription = view.findViewById(R.id.etSavingGoalDescription);
        sAmount = view.findViewById(R.id.etSavingGoalAmount);
        sStartAmount = view.findViewById(R.id.etSavingGoalAmount2);
        sGoal = view.findViewById(R.id.spinnerGoal);
        btnAddGoal = view.findViewById(R.id.btnAddGoal);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.SelectGoals, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sGoal.setAdapter(adapter);

        sGoal.setOnItemSelectedListener(this);

        btnAddGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Goal = etSavingGoal.getText().toString();
                String type = sGoal.getSelectedItem().toString();
                String amount = sAmount.getText().toString();
                String itemDes = etDescription.getText().toString();
                String amountSaved = sStartAmount.getText().toString();
                if (Goal.isEmpty() || amount.isEmpty() || type.isEmpty() || itemDes.isEmpty() || amountSaved.isEmpty()) {
                    Toast.makeText(getContext(), "Fields cannot be Empty", Toast.LENGTH_LONG).show();

                    return;
                }
                // the next two else ifs were developed by Chris
                // next line checks for too many decimal points in text input (invalid input)
                else if (notSingleDecimal(amount) || notSingleDecimal(amountSaved)) {
                    Toast.makeText(getContext(), "Too many decimals", Toast.LENGTH_LONG).show();
                    return;
                }
                // next line also checks for empty fields
                else if(amount.equals(".") || amountSaved.equals(".")) {
                    Toast.makeText(getContext(), "Invalid input",Toast.LENGTH_SHORT).show();
                    return;
                }

                amount = formatDecimalCurrency(amount).toString();
                ParseUser currentUser = ParseUser.getCurrentUser();

                saveExpense(Goal, currentUser, amount, type, amountSaved, itemDes);

            }
        });
    }

    //saves items from add to the database
    private void saveExpense(String Goal, ParseUser currentUser, String amount, String type, String amountSaved, String itemDes) {
        Saving saving = new Saving();
        saving.setGoal(Goal);
        saving.setUser(currentUser);
        saving.setGoalAmount(amount);
        saving.setCategory(type);
        saving.setAmountSaved(amountSaved);
        saving.setDescription(itemDes);
        saving.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "error while saving", e );
                    Toast.makeText(getContext(), "Error while saving", Toast.LENGTH_LONG).show();
                }
                Log.i(TAG, "Goal saved!");
                Toast.makeText(getContext(), "Your goal was saved!", Toast.LENGTH_LONG).show();
                etDescription.setText("");
                etSavingGoal.setText("");
                sAmount.setText("");
                sStartAmount.setText("");

                // etType.setText("");


            }
        });
    }

    // Developed by Chris
    // this function checks for multiple decimals in the input (checks for invalid input)
    private static boolean notSingleDecimal(String number) {
        int decimal = 0;
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == '.') {
                decimal += 1;
            }
        }
        return (decimal > 1);
    }

    // Developed by Chris
    // the code for the currency conversion comes from https://www.youtube.com/watch?v=-I_h1vEmEs4
    // this function formats the input so that it has exactly two numbers past the decimal
    private static String formatDecimalCurrency(String number) {
        DecimalFormat formatter = new DecimalFormat("###########0.00");
        return formatter.format(Double.parseDouble(number));
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}