package edu.csustan.budgetbuddy.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Calendar;

import edu.csustan.budgetbuddy.Expense;
import edu.csustan.budgetbuddy.R;

//Stephanie's Code
//this code covers the Add expenses fragment
public class AddFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    public static final String TAG = "AddFragment";
    private Spinner sTypes;
    private EditText etLocation;
    private EditText etAmount;
   // private EditText etType;
    private Button btnAdd;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    // The onCreateView method is called when Fragment should create its View object hierarchy
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Setup any handles to view objects here

        mDisplayDate = view.findViewById(R.id.tvChooseDate);




        etLocation = view.findViewById(R.id.etLocation);
        etAmount = view.findViewById(R.id.etAmount);
        btnAdd = view.findViewById(R.id.btnAddNew);
       // etType = view.findViewById(R.id.etType);
        sTypes = view.findViewById(R.id.sTypes);

       sTypes = view.findViewById(R.id.sTypes);

       ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.itemType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sTypes.setAdapter(adapter);

        sTypes.setOnItemSelectedListener(this);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Calendar cal = Calendar.getInstance();
                                                int year = cal.get(Calendar.YEAR);
                                                int month = cal.get(Calendar.MONTH);
                                                int day = cal.get(Calendar.DAY_OF_MONTH);

                                                DatePickerDialog dialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Dialog_MinWidth, mDateSetListener, year, month, day);

                                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                                dialog.show();
                                            }
                                        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String date = mDisplayDate.getText().toString();
                        String type = sTypes.getSelectedItem().toString();
                        String amount = etAmount.getText().toString();
                        // String itemType = etType.getText().toString();
                        String location = etLocation.getText().toString();
                        if (location.isEmpty() || amount.isEmpty() || type.isEmpty() || date.isEmpty()) {
                            Toast.makeText(getContext(), "Fields cannot be Empty", Toast.LENGTH_LONG).show();

                            return;
                        }
                        ParseUser currentUser = ParseUser.getCurrentUser();

                        saveExpense(location, currentUser, amount, type, date);
                        // saveExpense(location, amount, type);
                    }
                });
    }

    //
    private void saveExpense(String location, ParseUser currentUser, String amount, String type, String date) {
        Expense expense = new Expense();
        expense.setLocation(location);
        expense.setUser(currentUser);
        expense.setAmount(amount);
        expense.setItemType(type);
        expense.setDate(date);
        expense.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "error while saving", e );
                    Toast.makeText(getContext(), "Error while saving", Toast.LENGTH_LONG).show();
                }
                Log.i(TAG, "Expense saved!");
                Toast.makeText(getContext(), "Your Expense was saved!", Toast.LENGTH_LONG).show();
                etLocation.setText("");
                etAmount.setText("");
               // etType.setText("");


            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
      // String text = adapterView.getItemAtPosition(i).toString();
      // Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}