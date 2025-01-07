package com.example.nutrilens;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUsername, etEmail, etPin, etConfirmPin;
    private TextView tvDOB;
    private Button btnRegister;
    private ImageView ivCalendarIcon;
    private String username, email, pin, confirmPin, dob;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize views
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPin = findViewById(R.id.etPin);
        etConfirmPin = findViewById(R.id.etConfirmPin);
        tvDOB = findViewById(R.id.tvDOB);
        ivCalendarIcon = findViewById(R.id.ivCalendarIcon);
        btnRegister = findViewById(R.id.btnRegister);

        // Date of Birth picker
        ivCalendarIcon.setOnClickListener(v -> showDatePickerDialog());

        // Register Button click event
        btnRegister.setOnClickListener(v -> registerUser());
    }

    private void showDatePickerDialog() {
        // Show a date picker dialog when the calendar icon is clicked
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                RegisterActivity.this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Format the selected date and update the DOB TextView
                    selectedMonth = selectedMonth + 1; // Months are 0-indexed
                    dob = selectedDay + "/" + selectedMonth + "/" + selectedYear;
                    tvDOB.setText(dob);
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void registerUser() {
        // Get the input values
        username = etUsername.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        pin = etPin.getText().toString().trim();
        confirmPin = etConfirmPin.getText().toString().trim();

        // Validate input fields
        if (username.isEmpty() || email.isEmpty() || pin.isEmpty() || confirmPin.isEmpty() || dob.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate email format
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(RegisterActivity.this, "Invalid email format!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate PIN and confirm PIN match
        if (!pin.equals(confirmPin)) {
            Toast.makeText(RegisterActivity.this, "PINs do not match!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate PIN length (Firebase requires at least 6 characters)
        if (pin.length() < 6) {
            Toast.makeText(RegisterActivity.this, "PIN must be at least 6 digits!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Register user in Firebase
        mAuth.createUserWithEmailAndPassword(email, pin)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Send verification email
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            user.sendEmailVerification()
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            Toast.makeText(RegisterActivity.this, "Registration successful. Please verify your email.", Toast.LENGTH_SHORT).show();

                                            // Redirect to login page after registration
                                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                            finish(); // Close the RegisterActivity
                                        }
                                    });
                        }
                    } else {
                        // Registration failed, show specific error message
                        Toast.makeText(RegisterActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
