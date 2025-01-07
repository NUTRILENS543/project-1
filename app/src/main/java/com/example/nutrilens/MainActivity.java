package com.example.nutrilens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView videoView = findViewById(R.id.videoView);
        Button getStartedButton = findViewById(R.id.getStartedButton);
        TextView quotationText = findViewById(R.id.quotationText);

        // Set video loop
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.background_video);
        videoView.setVideoURI(videoUri);
        videoView.start();
        videoView.setOnPreparedListener(mp -> mp.setLooping(true));

        // Display a random quotation
        int quoteIndex = new Random().nextInt(50) + 1;  // Adjust range based on the number of quotes
        String quoteName = "quote_" + quoteIndex;
        int resId = getResources().getIdentifier(quoteName, "string", getPackageName());
        quotationText.setText(getString(resId));

        // Button click to navigate
        getStartedButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
        });
    }
}
