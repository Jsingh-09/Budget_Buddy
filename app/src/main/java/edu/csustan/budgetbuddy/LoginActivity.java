// written by: Jashandeep Singh
// tested by: Stephanie, Jashan, Chris, Jorge and Natasha
// debugged by: Jashandeep Singh

package edu.csustan.budgetbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * A login screen that offers login via username/password
 */

// https://guides.codepath.com/android/Building-Data-driven-Apps-with-Parse
public class LoginActivity extends AppCompatActivity {

    // The EditText field and the button that are used for the login
    private static final String TAG = "LoginActivity";
    private EditText edEmail;
    private EditText edPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        }

        // Initializing the EditText and button
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        Button btnLogin = findViewById(R.id.btnLogin);

        // Takes the user directly to the login page when the app is launched
        if(ParseUser.getCurrentUser()!=null) {
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
        }

        // Create the onClickListener handler for the login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Log In Button has been clicked");
                String username = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                login(username, password);
            }
        });
    }

    // Create the intent to go to main activity
    private void goMainActivity() {
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    // Error messages that are shown to the user when the Text fields are empty
    private void login(String username, String password) {
        Log.i(TAG, "Attempting to Log In User: " + username);
        if (TextUtils.isEmpty(username)){
            edEmail.setError( "Email is required!" );
        } else if (TextUtils.isEmpty(password)) {
            edPassword.setError("Password is required!");
        }
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e != null) {
                    // better error handling
                    Log.e(TAG, "User authentication failed", e);
                    Toast.makeText(LoginActivity.this, "Issue with Login", Toast.LENGTH_SHORT).show();
                    return;
                }
                // navigate to the main activity if the user has signed in properly
                goMainActivity();
                Toast.makeText(LoginActivity.this, "Welcome to Budget Buddy", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "User has been authenticated and signed in.");
            }
        });
    }

    // This will take the user to the sign up page when user clicks on sign up for budget buddy
    public void signup(View view) {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    // This will take the user to the reset password page when user clicks on reset password
    public void forgotPassword(View view) {
        Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
        startActivity(intent);

    }
}
