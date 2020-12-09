package edu.csustan.budgetbuddy.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

    private static final String TAG = "UpdateFragment";
    private Spinner spinnerUpdate;
    private EditText etUpdate;
    private Button btUpdate;


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
        spinnerUpdate = view.findViewById(R.id.spinnerUpdate);
        etUpdate = view.findViewById(R.id.etNewAmount);
        btUpdate = view.findViewById(R.id.btUpdate);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.SelectGoals, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUpdate.setAdapter(adapter);

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Saving saving = new Saving();
                String update = spinnerUpdate.getSelectedItem().toString();
                final String newAmount = etUpdate.getText().toString();

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Saving");
                query.whereEqualTo("user", ParseUser.getCurrentUser());
                query.whereEqualTo("category", update);
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(e == null){
                            object.put("amountSaved", newAmount);
                            object.saveInBackground();
                            Toast.makeText(getContext(), "Update saved", Toast.LENGTH_SHORT).show();
                        }else{
                            Log.i(TAG, "Update Failed");
                        }
                    }
                });
                etUpdate.setText("");
            }
        });
    }
}