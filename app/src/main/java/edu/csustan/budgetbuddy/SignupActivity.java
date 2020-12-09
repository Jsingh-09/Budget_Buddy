// Jashandeep Singh
package edu.csustan.budgetbuddy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    // Text field for the user to enter information to sign up for the app
    EditText edEmail, edPassword, edName, edConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        }

        // Initializing the Text fields
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        edName = findViewById(R.id.edName);
        edConfirmPassword = findViewById(R.id.edConfirmPassword);
    }

    // Method used to display error messages if the field is left empty
    public void signup(View view) {
        if (TextUtils.isEmpty(edName.getText())){
            edName.setError( "Name is required!" );
        }else if (TextUtils.isEmpty(edEmail.getText())){
            edEmail.setError( "Email is required!" );
        } else if (TextUtils.isEmpty(edPassword.getText())){
            edPassword.setError( "Password is required!");
            // Error shown if the password don't match the confirm password
        } else if (TextUtils.isEmpty(edConfirmPassword.getText())){
            edConfirmPassword.setError( "Confirm password is required!");
        }else if (!edPassword.getText().toString().equals(edConfirmPassword.getText().toString())){
            Toast.makeText(SignupActivity.this, "Password are not the same!", Toast.LENGTH_LONG).show();
        }else {
            final ProgressDialog progress = new ProgressDialog(this);
            // Displaying a loading screen after the user clicks on sign up button
            progress.setMessage("Loading ...");
            progress.show();
            ParseUser user = new ParseUser();
            user.setUsername(edName.getText().toString().trim());
            user.setEmail(edEmail.getText().toString().trim());
            user.setPassword(edPassword.getText().toString());
            user.put("name", edName.getText().toString().trim());
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    progress.dismiss();
                    if (e == null) {
                        Toast.makeText(SignupActivity.this, "Welcome!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignupActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        ParseUser.logOut();
                        Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

}