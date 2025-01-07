package com.example.nutrilens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class NutriDietActivity extends AppCompatActivity {

    EditText etHeight, etWeight, etAge;
    Button btnCalculate;
    TextView tvResult;
    ImageView homeIcon, profileIcon, centerLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutri_diet);

        // Initialize the views
        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        etAge = findViewById(R.id.etAge);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        // Bottom Navigation Views
        homeIcon = findViewById(R.id.homeIcon);
        profileIcon = findViewById(R.id.profileIcon);
        centerLogo = findViewById(R.id.centerLogo);

        // Set up the button click listener to calculate the diet
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateDiet();
            }
        });

        // Navigation OnClickListeners
        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Home Activity
                startActivity(new Intent(NutriDietActivity.this, HomeActivity.class));
            }
        });

        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Profile Activity
                //]startActivity(new Intent(NutriDietActivity.this, ProfileActivity.class));
            }
        });

        centerLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Home or Dashboard Activity
                //startActivity(new Intent(NutriDietActivity.this, DailyFactsActivity.class));
            }
        });
    }

    private void calculateDiet() {
        // Get the input values from the EditTexts
        String height = etHeight.getText().toString();
        String weight = etWeight.getText().toString();
        String age = etAge.getText().toString();

        // Check if any input field is empty
        if (height.isEmpty() || weight.isEmpty() || age.isEmpty()) {
            tvResult.setText("Please fill all fields.");
            return;
        }

        // Parse the height, weight, and age values
        double heightValue = Double.parseDouble(height);
        double weightValue = Double.parseDouble(weight);
        int ageValue = Integer.parseInt(age);

        // Generate the diet plan based on the inputs
        String dietPlan = generateDietPlan(heightValue, weightValue, ageValue);
        tvResult.setText(dietPlan);
    }

    private String generateDietPlan(double height, double weight, int age) {
        double bmi = weight / (height * height);
        StringBuilder dietPlan = new StringBuilder();

        // Diet plan for age under 18
        if (age < 18) {
            dietPlan.append("For age under 18:\n");
            if (bmi < 18.5) {
                dietPlan.append("You are underweight.\n")
                        .append("- Breakfast:\n" +
                                "            Whole grain toast with avocado and boiled eggs.\n")
                        .append("- Lunch:\n" +
                                "            Grilled chicken with brown rice and steamed vegetables.\n")
                        .append("- Snack: \n" +
                                "           Nuts and a fruit smoothie.\n")
                        .append("- Dinner:\n" +
                                "            Baked salmon with quinoa and a side salad.\n")
                        .append("- Activity:\n" +
                                "            1.Engage in strength-building exercises like push-ups and light weightlifting.\n" +
                                "           2.Engage in regular outdoor activities like cycling or swimming.\n" +
                                "           3.Practice yoga or stretching exercises to improve flexibility.");
            } else if (bmi >= 18.5 && bmi <= 24.9) {
                dietPlan.append("You have a healthy weight.\n")
                        .append("- Breakfast:\n" +
                                "            Oatmeal with fresh fruits and nuts.\n")
                        .append("- Lunch:\n" +
                                "            Turkey sandwich with whole grain bread and fresh veggies.\n")
                        .append("- Snack:\n" +
                                "            Greek yogurt with honey.\n")
                        .append("- Dinner:\n" +
                                "            Stir-fried tofu with mixed vegetables and brown rice.\n")
                        .append("- Activity:\n" +
                                "            1.Maintain regular physical activities like cycling or swimming.\n" +
                                "            2.Include strength-building exercises like push-ups or light weightlifting.\n");
            } else {
                dietPlan.append("You are overweight.\n")
                        .append("- Breakfast:\n" +
                                "            Smoothie with spinach, banana, and almond milk.\n")
                        .append("- Lunch: \n" +
                                "           Grilled chicken salad with a light vinaigrette.\n")
                        .append("- Snack:\n" +
                                "            Fresh fruit or veggie sticks with hummus.\n")
                        .append("- Dinner:\n" +
                                "            Lentil soup with a side of whole grain bread.\n")
                        .append("- Activity:\n" +
                                "            1.Include aerobic exercises like jogging or brisk walking.\n" +
                                "            2.Focus on aerobic exercises like brisk walking, jogging, or dancing.");
            }
        }
        // Diet plan for age between 18 and 40
        else if (age >= 18 && age <= 40) {
            dietPlan.append("For age between 18 and 40:\n");
            if (bmi < 18.5) {
                dietPlan.append("You are underweight.\n")
                        .append("- Breakfast:\n" +
                                "            Peanut butter on whole grain toast with a banana.\n")
                        .append("- Lunch:\n" +
                                "            Grilled fish with roasted sweet potatoes and broccoli.\n")
                        .append("- Snack:\n" +
                                "            Trail mix with dried fruits and seeds.\n")
                        .append("- Dinner:\n" +
                                "            Pasta with chicken and a creamy sauce.\n")
                        .append("- Activity:\n" +
                                "            1.Incorporate gym workouts focused on muscle gain.\n" +
                                "            2.Maintain a consistent workout schedule, including walking or light jogging.\n");
            } else if (bmi >= 18.5 && bmi <= 24.9) {
                dietPlan.append("You have a healthy weight.\n")
                        .append("- Breakfast:\n" +
                                "            Scrambled eggs with spinach and whole grain toast.\n")
                        .append("- Lunch:\n" +
                                "            Quinoa salad with chickpeas and fresh vegetables.\n")
                        .append("- Snack:\n" +
                                "            Apple slices with almond butter.\n")
                        .append("- Dinner:\n" +
                                "            Grilled shrimp with a side of couscous and steamed asparagus.\n")
                        .append("- Activity:\n" +
                                "            1.Maintain a mix of cardio and strength training.\n" +
                                "            2.Include mindfulness activities like yoga or tai chi to reduce stress.\n");
            } else {
                dietPlan.append("You are overweight.\n")
                        .append("- Breakfast:\n" +
                                "            Green smoothie with kale, cucumber, and pineapple.\n")
                        .append("- Lunch:\n" +
                                "            Baked chicken breast with a quinoa salad.\n")
                        .append("- Snack:\n" +
                                "            Celery sticks with a small amount of peanut butter.\n")
                        .append("- Dinner:\n" +
                                "            Steamed fish with sautéed greens and brown rice.\n")
                        .append("- Activity:\n" +
                                "            1.Engage in HIIT (High-Intensity Interval Training).\n" +
                                "            2.Aim for at least 45 minutes of physical activity five days a week.\n");
            }
        }
        // Diet plan for age above 40
        else {
            dietPlan.append("For age above 40:\n");
            if (bmi < 18.5) {
                dietPlan.append("You are underweight.\n")
                        .append("- Breakfast:\n " +
                                "           Porridge with milk and nuts.\n")
                        .append("- Lunch:\n" +
                                "           Chicken curry with whole grain roti and spinach.\n")
                        .append("- Snack:\n" +
                                "            Cheese cubes with a handful of almonds.\n")
                        .append("- Dinner:\n" +
                                "            Vegetable stew with brown rice.\n")
                        .append("- Activity:\n" +
                                "            1.Light yoga or tai chi to maintain flexibility.\n");
            } else if (bmi >= 18.5 && bmi <= 24.9) {
                dietPlan.append("You have a healthy weight.\n")
                        .append("- Breakfast:\n" +
                                "            Avocado toast with eggs and tomatoes.\n")
                        .append("- Lunch:\n" +
                                "            Grilled chicken with quinoa and mixed greens.\n")
                        .append("- Snack:\n" +
                                "            A small handful of mixed nuts.\n")
                        .append("- Dinner:\n" +
                                "            Stir-fry tofu with broccoli, mushrooms, and brown rice.\n")
                        .append("- Activity:\n" +
                                "            1.Incorporate strength exercises, yoga, and walking.\n" +
                                "            2.Don't skip flexibility workouts.");
            } else {
                dietPlan.append("You are overweight.\n")
                        .append("- Breakfast:\n" +
                                "            Smoothie with protein powder, spinach, and almond milk.\n")
                        .append("- Lunch:\n" +
                                "            Baked fish with quinoa and steamed vegetables.\n")
                        .append("- Snack:\n" +
                                "            Cucumber with hummus.\n")
                        .append("- Dinner:\n" +
                                "            Zucchini noodles with lean protein and tomato sauce.\n")
                        .append("- Activity:\n" +
                                "            1.Opt for low-impact exercises such as walking or swimming.\n");
            }
        }

        return dietPlan.toString();
    }
}
