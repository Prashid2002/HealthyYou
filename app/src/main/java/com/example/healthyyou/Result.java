package com.example.healthyyou;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView bmiResult = findViewById(R.id.bmiResultTextView);
        TextView categoryResult = findViewById(R.id.bmiCategoryTextView);
        TextView healthTipResult = findViewById(R.id.healthTipResult);

        // Retrieve data from intent
        String bmi = getIntent().getStringExtra("BMI_RESULT");
        String category = getIntent().getStringExtra("CATEGORY");
        String healthTip = getIntent().getStringExtra("HEALTH_TIP");

        // Set text in TextViews
        bmiResult.setText("Your BMI: " + bmi);
        categoryResult.setText("Category: " + category);
        healthTipResult.setText("Health Tip: " + healthTip);
    }
}
