package com.example.project_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class income extends AppCompatActivity {

    //step 1 Declare components(functions) names
    EditText incomeEditText, savingsEditText;
    Button myopenexpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_income);

        //step 2
        incomeEditText = findViewById(R.id.input_income);  // Assuming you have an EditText with ID incomeEditText
        savingsEditText = findViewById(R.id.input_point);
        myopenexpen = findViewById(R.id.goexpenBT);

        //step 3
        myopenexpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Step 1: Get the values from EditText
                String incomeStr = incomeEditText.getText().toString();
                String savingsStr = savingsEditText.getText().toString();

                // Step 2: Check if the inputs are valid
                if (incomeStr.isEmpty() || savingsStr.isEmpty()) {
                    Toast.makeText(income.this, "Please enter both values", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Step 3: Convert input to integers
                int income = Integer.parseInt(incomeStr);
                int savingsPercentage = Integer.parseInt(savingsStr);

                // Step 4: Save data to UserInputData.java
                UserInputData.setIncome(income);
                UserInputData.setSavingsPercentage(savingsPercentage);

                //step 5 Navigate to next page
                Intent myintent = new Intent(getApplicationContext(), expen.class);
                startActivity(myintent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}