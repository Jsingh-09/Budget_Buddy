package edu.csustan.budgetbuddy.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import edu.csustan.budgetbuddy.Expense;
import edu.csustan.budgetbuddy.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {

    public static final String TAG = "AddFragment";
    //private Spinner sTypes;
    private EditText etLocation;
    private EditText etAmount;
    private EditText etType;
    private Button btnAdd;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etLocation = view.findViewById(R.id.etLocation);
        etAmount = view.findViewById(R.id.etAmount);
        btnAdd = view.findViewById(R.id.btnAddNew);
        etType = view.findViewById(R.id.etType);

       /* sTypes = view.findViewById(R.id.sTypes);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.itemType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sTypes.setAdapter(adapter);

        sTypes.setOnItemSelectedListener(this); */

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = etAmount.getText().toString();
                String itemType = etType.getText().toString();
                String location = etLocation.getText().toString();
                if (location.isEmpty() || amount.isEmpty() || itemType.isEmpty()) {
                    Toast.makeText(getContext(), "Fields cannot be Empty", Toast.LENGTH_LONG).show();

                    return;
                }
                 ParseUser currentUser = ParseUser.getCurrentUser();

                saveExpense(location, currentUser, amount, itemType);
               // saveExpense(location, amount, type);
            }
        });
    }

    //
    private void saveExpense(String location, ParseUser currentUser, String amount, String itemType) {
        Expense expense = new Expense();
        expense.setLocation(location);
        expense.setUser(currentUser);
        expense.setAmount(amount);
        expense.setItemType(itemType);
        expense.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "error while saving", e );
                    Toast.makeText(getContext(), "Error while saving", Toast.LENGTH_LONG).show();
                }
                Log.i(TAG, "Expense saved!");
                etLocation.setText("");
                etAmount.setText("");
                etType.setText("");


            }
        });
    }

   /* @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    } */


}