package com.example.healthyyou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView resetBtn;
    private EditText heightET, weightET;
    private Button calculateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        heightET = findViewById(R.id.heightET);
        weightET = findViewById(R.id.weightET);
        calculateBtn = findViewById(R.id.calculateBtn);
        resetBtn = findViewById(R.id.resetBtn);

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heightStr = heightET.getText().toString();
                String weightStr = weightET.getText().toString();

                if (heightStr.isEmpty() || weightStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter both height and weight", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double height = Double.parseDouble(heightStr);
                    double weight = Double.parseDouble(weightStr);

                    if (height <= 0 || weight <= 0) {
                        Toast.makeText(MainActivity.this, "Height and weight must be positive values", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Calculate BMI
                    double bmi = weight / (height * height);
                    String category = getCategory(bmi);
                    String healthTip = getHealthTip(bmi);

                    // Navigate to ResultActivity with BMI, category, and health tip values
                    Intent intent = new Intent(MainActivity.this, Result.class);
                    intent.putExtra("BMI_RESULT", String.format("%.2f", bmi));
                    intent.putExtra("CATEGORY", category);
                    intent.putExtra("HEALTH_TIP", healthTip);
                    startActivity(intent);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset fields
                heightET.setText("");
                weightET.setText("");
            }
        });
    }

    private String getCategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal Weight";
        } else if (bmi >= 25.0 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }

    private String getHealthTip(double bmi) {
        if (bmi < 18.5) {
            return "You are underweight. Consider incorporating more nutritious calories into your diet. Focus on whole grains, lean proteins, and healthy fats.";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "You have a healthy weight! Maintain a balanced diet and regular physical activity.";
        } else if (bmi >= 25.0 && bmi < 29.9) {
            return "You are slightly overweight. Aim for a balanced diet, regular exercise, and portion control.";
        } else {
            return "Your BMI indicates obesity. Consult a healthcare provider for a tailored weight management plan.";
        }
    }
}
