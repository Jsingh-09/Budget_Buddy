package edu.csustan.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class addActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String TAG = "AddActivity";
    private Spinner sTypes;
    private EditText etLocation;
    private EditText etAmount;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);

        etLocation = findViewById(R.id.etLocation);
        etAmount = findViewById(R.id.etAmount);
        btnAdd = findViewById(R.id.btnAddNew);

        sTypes = findViewById(R.id.sTypes);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.itemType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sTypes.setAdapter(adapter);

        sTypes.setOnItemSelectedListener(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = etLocation.getText().toString();
                if (location.isEmpty()) {
                    Toast.makeText(addActivity2.this, "Location Cannot be Empty", Toast.LENGTH_LONG).show();

                    return;
                }
                ParseUser currentUser = ParseUser.getCurrentUser();
                String amount = etAmount.getText().toString();
                saveExpense(location, currentUser, amount);
            }
        });
    }

    private void saveExpense(String location, ParseUser currentUser, String amount) {
        Expense expense = new Expense();
        expense.setLocation(location);
        expense.setUser(currentUser);
        expense.setAmount(amount);
        expense.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "error while saving", e );
                    Toast.makeText(addActivity2.this, "Error while saving", Toast.LENGTH_LONG).show();
                }
                Log.i(TAG, "Expense saved!");
                etLocation.setText("");
                etAmount.setText("");


            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}