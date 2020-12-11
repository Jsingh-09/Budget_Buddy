//Author: Jorge Hernandez Ortega
//The following code was partially adapted using the Back4App documentation: https://www.back4app.com/docs/get-started/welcome

package edu.csustan.budgetbuddy.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import edu.csustan.budgetbuddy.R;
import edu.csustan.budgetbuddy.Saving;


public class UpdateFragment extends Fragment {

    private static final String TAG = "UpdateFragment";                                                     //TAG used for Log debugging
    private Spinner spinnerUpdate;                                                                          //creates a Spinner that will be bound to the spinner in the xml
    private EditText etUpdate;                                                                              //creates an EditText that will be bound to the EditText in the xml
    private Button btUpdate;                                                                                //creates a Button that will be bound to the Button in the xml


    public UpdateFragment() {
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
        return inflater.inflate(R.layout.fragment_update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinnerUpdate = view.findViewById(R.id.spinnerUpdate);                                              //Binds the spinner created to the spinner in xml
        etUpdate = view.findViewById(R.id.etNewAmount);                                                     //Binds the edit text to the one in the xml
        btUpdate = view.findViewById(R.id.btUpdate);                                                        //Binds the button to the one in the xml

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext()                   //creates a CharSequence adapter for the spinner
                , R.array.SelectGoals, android.R.layout.simple_spinner_item);                               //continuation of previous line
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);                     //binds the spinner layout to the adapter
        spinnerUpdate.setAdapter(adapter);                                                                  //sets the adapter to the spinner(this gives it the actual strings that appear when you click it)

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                                                 //initiates a onClickListener for the button
                String update = spinnerUpdate.getSelectedItem().toString();                                 //initates a String which will be used to query a specific goal
                final String updateAmount = etUpdate.getText().toString();                                  //initiates a String used to store the value the user wants to add to their save amount
                final Double addAmount = Double.parseDouble(updateAmount);                                  //stores updateAmount as a Double

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Saving");                              //initiates a query within the Saving class in Back4app database
                query.whereEqualTo("user", ParseUser.getCurrentUser());                                     //query parameter for current user
                query.whereEqualTo("category", update);                                                     //query parameter for goal stored in update
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {                                                  //gets the first query that matches our parameters
                        if(e == null){
                            String currentAmount = object.getString("amountSaved");                    //String used to store the current amount saved from the queried goal
                            Double newAmount = Double.parseDouble(currentAmount);                           //stores current amount as a double
                            Double dataAmount = newAmount + addAmount;                                      //Double used to store newAmount + addAmount
                            object.put("amountSaved", dataAmount.toString());                               //puts dataAmount into the amountSaved of the queried object
                            object.saveInBackground();                                                      //saves the updated object into the database
                            Toast.makeText(getContext(), "Update saved", Toast.LENGTH_SHORT).show();   //Toast to notify user that update was saved
                        }else{
                            Log.i(TAG, "Update Failed");                                               //log used to debug(if update failed)
                        }
                    }
                });
                etUpdate.setText("");                                                                        //resets EditText in xml after update is done
            }
        });
    }
}