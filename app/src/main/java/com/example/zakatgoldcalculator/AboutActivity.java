package com.example.zakatgoldcalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    Button btnGithub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        btnGithub = findViewById(R.id.btnGithub);

        btnGithub.setOnClickListener(v -> {

            Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/aliafarhani/zakat-gold-calculator.git")
            );

            startActivity(intent);

        });
    }
}