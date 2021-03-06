// written by: Jashandeep Singh
// tested by: Stephanie, Jashan, Chris, Jorge and Natasha
// debugged by: Jashandeep Singh
package edu.csustan.budgetbuddy;

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
import com.parse.RequestPasswordResetCallback;

public class ResetPasswordActivity extends AppCompatActivity {
    EditText edEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        }
        edEmail = findViewById(R.id.edEmail);
    }

    // Using the documentation from back4app for the user to receive a email to reset the password
    // https://www.back4app.com/docs/get-started/welcome
    public void resetPassword(View view) {
        if (TextUtils.isEmpty(edEmail.getText())) {
            edEmail.setError("Email is required!");
        } else {
            ParseUser.requestPasswordResetInBackground(edEmail.getText().toString(), new RequestPasswordResetCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        Toast.makeText(ResetPasswordActivity.this, "An email was successfully sent with reset instructions. ", Toast.LENGTH_LONG).show();
                        // We want to send the user back to login activity
                        login();
                    } else {
                        Toast.makeText(ResetPasswordActivity.this, "Something went wrong. Look at the ParseException to see what's up.", Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }

    private void login() {
        // Creating the intent to navigation to login page
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}