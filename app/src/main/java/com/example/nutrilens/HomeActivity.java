package com.example.nutrilens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ImageView homeIcon, profileIcon,centerLogo;
    private Button nutriTrackButton, nutriDietButton, nutriManageButton, nutriBotButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Uses your XML layout file

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Find views by ID
        homeIcon = findViewById(R.id.homeIcon);
        profileIcon = findViewById(R.id.profileIcon);
        nutriTrackButton = findViewById(R.id.nutriTrackButton);
        nutriDietButton = findViewById(R.id.nutriDietButton);
        nutriManageButton = findViewById(R.id.nutriManageButton);
        nutriBotButton = findViewById(R.id.nutriBotButton);
        centerLogo = findViewById(R.id.centerLogo);

        // Display a dynamic welcome message
        String userEmail = mAuth.getCurrentUser() != null ? mAuth.getCurrentUser().getEmail() : "User";
        Toast.makeText(this, "Welcome, " + userEmail, Toast.LENGTH_SHORT).show();

        // Set click listeners for icons
        homeIcon.setOnClickListener(v -> {
             Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
             startActivity(intent);;
        });

        profileIcon.setOnClickListener(v -> {
            // Profile icon clicked, you can navigate to Profile Screen
            Toast.makeText(HomeActivity.this, "Profile clicked!", Toast.LENGTH_SHORT).show();
            // For example, start a ProfileActivity (if you have it):
            // Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
            // startActivity(intent);
        });

        centerLogo.setOnClickListener(v -> {
            // Profile icon clicked, you can navigate to Profile Screen
            Toast.makeText(HomeActivity.this, "daily facts clicked!", Toast.LENGTH_SHORT).show();
            // Intent intent = new Intent(HomeActivity.this, DailyFactsActivity.class);
            // startActivity(intent);
        });

        // Set click listeners for the buttons
        nutriTrackButton.setOnClickListener(v -> {
            // NutriTrack button clicked
            Toast.makeText(HomeActivity.this, "NutriTrack clicked!", Toast.LENGTH_SHORT).show();
            // Navigate to NutriTrack Activity if needed
            // Intent intent = new Intent(HomeActivity.this, NutriTrackActivity.class);
            // startActivity(intent);
        });

        nutriDietButton.setOnClickListener(v -> {
            // NutriDiet button clicked
            if (mAuth.getCurrentUser() != null) {
                // If user is logged in, navigate to NutriBalance Activity
                Toast.makeText(HomeActivity.this, "NutriDiet clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this,NutriDietActivity.class);
                startActivity(intent);
            }
        });

        nutriManageButton.setOnClickListener(v -> {
            // NutriManage button clicked
            Toast.makeText(HomeActivity.this, "NutriManage clicked!", Toast.LENGTH_SHORT).show();
            // Navigate to NutriManage Activity if needed
            // Intent intent = new Intent(HomeActivity.this, NutriManageActivity.class);
            // startActivity(intent);
        });

        nutriBotButton.setOnClickListener(v -> {
            // NutriBot button clicked
            Toast.makeText(HomeActivity.this, "NutriBot clicked!", Toast.LENGTH_SHORT).show();
            // Navigate to NutriBot Activity if needed
            // Intent intent = new Intent(HomeActivity.this, NutriBotActivity.class);
            // startActivity(intent);
        });
    }
}
