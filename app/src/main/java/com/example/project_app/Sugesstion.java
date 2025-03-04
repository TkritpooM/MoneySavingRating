package com.example.project_app;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sugesstion extends AppCompatActivity {

    //step 1 Declare components names
    TextView no1text, no2text, no3text, no4text, no5text, highestExpenseText;
    ImageButton imgBT1, imgBT2, imgBT3;
    Button myresetBT, myinfoBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sugesstion);

        //step 2
        no1text = findViewById(R.id.no1);
        no2text = findViewById(R.id.no2);
        no3text = findViewById(R.id.no3);
        no4text = findViewById(R.id.no4);
        no5text = findViewById(R.id.no5);
        highestExpenseText = findViewById(R.id.no1_butdiff);

        imgBT1 = findViewById(R.id.image_link1);
        imgBT2 = findViewById(R.id.image_link2);
        imgBT3 = findViewById(R.id.image_link3);

        myresetBT = findViewById(R.id.redo);
        myinfoBT = findViewById(R.id.info);

        // Get Data from UserInputData.java
        List<ExpenseItem> expenses = new ArrayList<>();
        expenses.add(new ExpenseItem("อาหาร", UserInputData.getFood()));
        expenses.add(new ExpenseItem("การเดินทาง", UserInputData.getTransport()));
        expenses.add(new ExpenseItem("ค่าใช้จ่ายภายในบ้าน", UserInputData.getHousehold()));
        expenses.add(new ExpenseItem("ค่าโทรศัพท์-ค่าเน็ต", UserInputData.getPhoneInternet()));
        expenses.add(new ExpenseItem("อื่นๆ", UserInputData.getOthers()));

        // Sort from highest to Lowest
        Collections.sort(expenses, new Comparator<ExpenseItem>() {
            @Override
            public int compare(ExpenseItem o1, ExpenseItem o2) {
                return Integer.compare(o2.amount, o1.amount);
            }
        });

        // Output
        TextView[] textViews = {no1text, no2text, no3text, no4text, no5text};
        for (int i = 0; i < expenses.size(); i++) {
            textViews[i].setText("" + expenses.get(i).category + " (" + expenses.get(i).amount + ")");
        }

        // Show highest
        highestExpenseText.setText("" + expenses.get(0).category + " (" + expenses.get(0).amount + ")");

        // ImageButton1
        imgBT1.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.imgbutt1));
        imgBT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cimbthai.com/th/personal/blog/financial-guru/5-tips-for-manage-money.html"));
                startActivity(browserIntent);
            }
        });

        // ImageButton2
        imgBT2.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.imgbutt2));
        imgBT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://makebykbank.kbtg.tech/articles/smart-way-to-save-money"));
                startActivity(browserIntent);
            }
        });

        // ImageButton3
        imgBT3.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.imgbutt3));
        imgBT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tokiomarine.com/th/th/life/about-us/media-centre/9-tips-money-saving-can-follow.html"));
                startActivity(browserIntent);
            }
        });

        //step 3 Back to result Page
        myresetBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //step 4
                Intent myintent = new Intent(getApplicationContext(), result.class);
                startActivity(myintent);
            }
        });

        //step 3 Go to graph Page
        myinfoBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //step 4
                Intent myintent = new Intent(getApplicationContext(), GraphManifest.class);
                startActivity(myintent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // คลาสสำหรับเก็บข้อมูลค่าใช้จ่าย
    static class ExpenseItem {
        String category;
        int amount;

        ExpenseItem(String category, int amount) {
            this.category = category;
            this.amount = amount;
        }
    }
}