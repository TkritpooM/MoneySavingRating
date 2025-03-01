package com.example.project_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class summary extends AppCompatActivity {

    //step 1 Declare components names
    TextView incomeTextView, totalExpenTextView, savingsPercentageTextView, savingsAmountTextView, remainingBalanceTextView;
    Button mygographBT,mygoexpenBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);

        //step 2
        incomeTextView = findViewById(R.id.re_income);  // Assuming you have a TextView with ID incomeTextView
        totalExpenTextView = findViewById(R.id.re_expen);  // Assuming you have a TextView with ID totalExpenTextView
        savingsPercentageTextView = findViewById(R.id.re_percen);  // Assuming you have a TextView with ID savingsPercentageTextView
        savingsAmountTextView = findViewById(R.id.re_baht);  // Assuming you have a TextView with ID savingsAmountTextView
        remainingBalanceTextView = findViewById(R.id.re_sum);

        mygographBT = findViewById(R.id.gographBT);
        mygoexpenBT = findViewById(R.id.goexpenBT);

        // Step 1: Get the values from UserInputData.java
        int income = UserInputData.getIncome();  // Assuming UserInputData has a method to get income
        int food = UserInputData.getFood();
        int transport = UserInputData.getTransport();
        int household = UserInputData.getHousehold();
        int phoneInternet = UserInputData.getPhoneInternet();
        int others = UserInputData.getOthers();
        int savingsPercentage = UserInputData.getSavingsPercentage();  // Assuming UserInputData has a method to get savings percentage

        // Step 2: Calculate total expenses, savings, and remaining balance
        int totalExpen = food + transport + household + phoneInternet + others;
        int savingsAmount = (income * savingsPercentage) / 100;
        int remainingBalance = income - totalExpen;

        // Step 3: Display the results in TextViews
        incomeTextView.setText(""+income);
        totalExpenTextView.setText("" + totalExpen);
        savingsPercentageTextView.setText("" + savingsPercentage + "%");
        savingsAmountTextView.setText("" + savingsAmount);
        remainingBalanceTextView.setText("" + remainingBalance);

        //step 3 Go to next page
        mygographBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //step 4
                Intent myintent = new Intent(getApplicationContext(), result.class);
                startActivity(myintent);
            }
        });

        //step 3 Back to Previous Page
        mygoexpenBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //step 4
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