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

public class expen extends AppCompatActivity {

    //step 1 Declare components(functions) names
    EditText foodEditText, transportEditText, householdEditText, phoneInternetEditText, othersEditText;
    Button mysumBT,myincomeT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_expen);

        //step 2
        foodEditText = findViewById(R.id.input_food);  // Assuming you have an EditText with ID foodEditText
        transportEditText = findViewById(R.id.input_fare); // Assuming you have an EditText with ID transportEditText
        householdEditText = findViewById(R.id.input_home); // Assuming you have an EditText with ID householdEditText
        phoneInternetEditText = findViewById(R.id.input_util); // Assuming you have an EditText with ID phoneInternetEditText
        othersEditText = findViewById(R.id.input_other);

        mysumBT = findViewById(R.id.gosummaryBT);
        myincomeT = findViewById(R.id.goincomeBT);

        foodEditText.setText(UserInputData.getFood() == 0 ? "" : String.valueOf(UserInputData.getFood()));
        transportEditText.setText(UserInputData.getTransport() == 0 ? "" : String.valueOf(UserInputData.getTransport()));
        householdEditText.setText(UserInputData.getHousehold() == 0 ? "" : String.valueOf(UserInputData.getHousehold()));
        phoneInternetEditText.setText(UserInputData.getPhoneInternet() == 0 ? "" : String.valueOf(UserInputData.getPhoneInternet()));
        othersEditText.setText(UserInputData.getOthers() == 0 ? "" : String.valueOf(UserInputData.getOthers()));

        //step 3 go summary page (Next Page)
        mysumBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVariable();

                //step 4
                Intent myintent = new Intent(getApplicationContext(), summary.class);
                startActivity(myintent);
            }
        });

        //step 3 Back to income page (Previous Page)
        myincomeT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVariable();

                //step 4
                Intent myintent = new Intent(getApplicationContext(), income.class);
                startActivity(myintent);
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void setVariable(){
        // Step 1: Get the values from EditText
        String foodStr = foodEditText.getText().toString();
        String transportStr = transportEditText.getText().toString();
        String householdStr = householdEditText.getText().toString();
        String phoneInternetStr = phoneInternetEditText.getText().toString();
        String othersStr = othersEditText.getText().toString();

        // Step 2: Check if the inputs are valid
        if (foodStr.isEmpty() || transportStr.isEmpty() || householdStr.isEmpty() || phoneInternetStr.isEmpty() || othersStr.isEmpty()) {
            Toast.makeText(expen.this, "Please enter all expense values", Toast.LENGTH_SHORT).show();
            return;
        }

        // Step 3: Convert input to integers
        int food = Integer.parseInt(foodStr);
        int transport = Integer.parseInt(transportStr);
        int household = Integer.parseInt(householdStr);
        int phoneInternet = Integer.parseInt(phoneInternetStr);
        int others = Integer.parseInt(othersStr);

        // Step 4: Save data to UserInputData
        UserInputData.setFood(food);
        UserInputData.setTransport(transport);
        UserInputData.setHousehold(household);
        UserInputData.setPhoneInternet(phoneInternet);
        UserInputData.setOthers(others);

    }
}

