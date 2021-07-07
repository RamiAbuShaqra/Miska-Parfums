package com.example.miskaparfums;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FullImageActivity extends AppCompatActivity {

    private boolean captionAppearance = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        Bundle bundle = getIntent().getExtras();
        int imagePath = bundle.getInt("Image Path");
        int captionPath = bundle.getInt("Caption Path");

        ImageView fullImage = findViewById(R.id.full_image);
        fullImage.setImageResource(imagePath);

        String output = "";

        try {
            // Load the file from the raw folder
            output = MainActivity.loadFile("" + captionPath);
        } catch (Exception e) {
            // Display an error toast message
            Toast toast = Toast.makeText(this, "File not found", Toast.LENGTH_SHORT);
            toast.show();
        }

        TextView captionText = findViewById(R.id.image_caption);
        captionText.setText(output);

        fullImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (captionAppearance){
                    captionText.setVisibility(View.GONE);
                    captionAppearance = false;
                } else {
                    captionText.setVisibility(View.VISIBLE);
                    captionAppearance = true;
                }
            }
        });
    }
}