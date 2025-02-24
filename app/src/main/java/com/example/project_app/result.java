package com.example.project_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class result extends AppCompatActivity {

    // Step 1
    TextView remainingMoneyText, savingAmountText, TitleMessage, Compare, RatingThaiVer;
    Button mygoSugesstionBT,mygosummaryBT;
    ImageView setupimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        // Step 2
        remainingMoneyText = findViewById(R.id.sum);
        savingAmountText = findViewById(R.id.per_sum);
        TitleMessage = findViewById(R.id.ratig_result);
        Compare = findViewById(R.id.comparis);
        RatingThaiVer = findViewById(R.id.rating_thaiver);

        mygoSugesstionBT = findViewById(R.id.goSugestionBT2);
        mygosummaryBT = findViewById(R.id.gosummaryBT2);
        setupimg = findViewById(R.id.imageView2);

        // Get Data from UserInputData
        int income = UserInputData.getIncome();
        int totalExpenses = UserInputData.getFood() +
                UserInputData.getTransport() +
                UserInputData.getHousehold() +
                UserInputData.getPhoneInternet() +
                UserInputData.getOthers();
        int savingPercentage = UserInputData.getSavingsPercentage();

        // Calculate
        int savingAmount = (income * savingPercentage) / 100;
        int remainingMoney = income - totalExpenses;

        // Showing output Remaining-SavingAmount
        remainingMoneyText.setText("" + remainingMoney);
        savingAmountText.setText("" + savingAmount);

        // Conditions
        if (remainingMoney >= (savingAmount*75)/100) {
            if (remainingMoney >= (savingAmount) ) {
                setupimg.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.excellent));
                TitleMessage.setText("Excellent!!");
                Compare.setText("มากกว่า");
                RatingThaiVer.setText("ดีมาก!");
            }else{
                setupimg.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.welldone));
                TitleMessage.setText("Well done!!");
                Compare.setText("น้อยกว่า");
                RatingThaiVer.setText("ดี!");
            }
        } else if (remainingMoney >= (savingAmount*50)/100) {
            setupimg.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.good));
            TitleMessage.setText("Good!!");
            Compare.setText("น้อยกว่า");
            RatingThaiVer.setText("ปกติ!");
        } else {
            if (remainingMoney >= ((savingAmount*20)/100)) {
                setupimg.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.notgood));
                TitleMessage.setText("Not Good!!");
                Compare.setText("น้อยกว่า");
                RatingThaiVer.setText("ไม่ค่อยดี!");
            }else{
                setupimg.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.bad));
                TitleMessage.setText("Bad!!");
                Compare.setText("น้อยกว่า");
                RatingThaiVer.setText("ไม่ดี!");
            }
        }

        //step 3 Go to next page
        mygoSugesstionBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //step 4
                Intent myintent = new Intent(getApplicationContext(), Sugesstion.class);
                startActivity(myintent);
            }
        });

        //step 3 Back to Previous Page
        mygosummaryBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //step 4
                Intent myintent = new Intent(getApplicationContext(), summary.class);
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