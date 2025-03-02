package com.example.project_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// For Chart
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class GraphManifest extends AppCompatActivity {

    private FrameLayout chartContainer; // Used to output any type of chart
    private boolean isPieChart = true; // Start with PieChart
    Button mybackBT, myresetBT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_graph_manifest);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        chartContainer = findViewById(R.id.piexbar_chart);
        Button btnToggleChart = findViewById(R.id.btn_toggle_chart);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            setupPieChart();
        }, 200);

        // กดปุ่มเพื่อสลับกราฟ
        btnToggleChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chartContainer.removeAllViews(); // Delete previous chart or graph etc.
                if (isPieChart) {
                    setupBarChart();
                    Toast.makeText(GraphManifest.this, "เปลี่ยนเป็น Bar Chart", Toast.LENGTH_SHORT).show();
                } else {
                    setupPieChart();
                    Toast.makeText(GraphManifest.this, "เปลี่ยนเป็น Pie Chart", Toast.LENGTH_SHORT).show();
                }
                isPieChart = !isPieChart; // Change Stage (boolean)

            }
        });

        //-----------------------------------------------------------------------------//

        mybackBT = findViewById(R.id.btn_option1back);
        myresetBT = findViewById(R.id.btn_option2reset);

        //step 3 Go to Sugesstion Page
        mybackBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //step 4
                Intent myintent = new Intent(getApplicationContext(), Sugesstion.class);
                startActivity(myintent);
            }
        });

        //step 3 Back to Input Page & Reset variable
        myresetBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset Data on press
                UserInputData.reset();

                //step 4
                Intent myintent = new Intent(getApplicationContext(), income.class);
                startActivity(myintent);
            }
        });
    }

    private void setupPieChart(){

        // Pie Graph //

        // Step 1 Crate Pie Chart : Connect XML
        AnyChartView chartView = new AnyChartView(this);
        Pie pie = AnyChart.pie();

        // Step 2 Prepare Data for chart
        List<DataEntry> data1 = new ArrayList<>();
        data1.add(new ValueDataEntry("อาหาร", UserInputData.getFood()));
        data1.add(new ValueDataEntry("การเดินทาง", UserInputData.getTransport()));
        data1.add(new ValueDataEntry("ค่าใช้จ่ายภายในบ้าน", UserInputData.getHousehold()));
        data1.add(new ValueDataEntry("ค่าโทรศัพท์-ค่าเน็ต", UserInputData.getPhoneInternet()));
        data1.add(new ValueDataEntry("อื่นๆ", UserInputData.getOthers()));

        // Make Chart Header
        pie.title("Expense");

        // Step 4 Add data into hart
        pie.data(data1);

        // Step 5 Output AnyChartView
        chartView.setChart(pie);

        // Step 6 Send Chart to Framelayout to see output
        chartContainer.addView(chartView);

        //-----------------------------------------------------------------------------//

        // Show OutPut for PieChart
        outputtype1();
    }

    private void setupBarChart(){

        // Prepare Data //

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

        //-----------------------------------------------------------------------------//

        // Bar Graph //

        // Step 1 Create Bar Chart
        AnyChartView chartView = new AnyChartView(this);
        Cartesian barChart = AnyChart.column();

        // Step 2 prepare data into graph
        List<DataEntry> data = new ArrayList<>();
        data.add(new CustomDataEntry("เงินคงเหลือ("+remainingMoney+")", remainingMoney,"#4CAF50"));
        data.add(new CustomDataEntry("ตั้งเป้าไว้("+savingAmount+")", savingAmount,"#FF9800"));

        // Step 3 กำหนดข้อมูลให้ Column
        Column column = barChart.column(data);
        column.data(data);

        // Make Graph Header
        barChart.title("Remaininng VS Goals");

        // Step 4 Graph Setting
        barChart.yScale().minimum(0d);
        barChart.yAxis(0).labels().format("{%Value} บาท");

        // Step 5 Output AnyChartView
        chartView.setChart(barChart);

        // Step 6 Send Chart to Framelayout to see output
        chartContainer.addView(chartView);

        //-----------------------------------------------------------------------------//

        // Show OutPut for BarChart
        outputtype2();
    }

    private class CustomDataEntry extends ValueDataEntry {
        CustomDataEntry(String x, Number value, String color) {
            super(x, value);
            setValue("fill", color);
        }
    }

    // Calculate and Show Output for PieChart
    private void outputtype1(){
        // Calculate and show the max details(Percents)

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

        // TotalValue
        int totalValue = 0;
        for (ExpenseItem entry : expenses) {
            totalValue += entry.amount; // ดึงค่าของแต่ละรายการแล้วบวกกัน
        }

        // ดึงค่ามากที่สุด (อันแรกหลังจาก Sort)
        ExpenseItem maxExpense = expenses.get(0);
        String maxCategory = maxExpense.category; // ✅ หมวดหมู่ที่ใช้เงินมากที่สุด
        int maxAmount = maxExpense.amount;

        // Output
        // Notes : Percent = ( max  / sum ) * 100
        TextView maxCategoryText = findViewById(R.id.max_percent_Pie);
        maxCategoryText.setText("จากกราฟแสดงให้เห็นว่า\n"+
                                "ค่าใช้จ่ายสูงสุด: " + maxCategory + " (" + maxAmount + " บาท)\n"+
                                "คิดเป็น: "+((int)(((double)maxAmount/totalValue)*100))+"% ของรายจ่ายทั้งหมด\n"+
                                "แนะนำให้ลด \"ค่า"+ maxCategory + "\" ลง");

    }

    // Calculate and Show Output for PieChart
    private void outputtype2(){

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

        // Conditions
        TextView compare = findViewById(R.id.max_percent_Pie);
        if (remainingMoney >= (savingAmount*75)/100) {
            if (remainingMoney >= (savingAmount) ) {
                compare.setText("จากกราฟแสดงให้เห็นว่า\n"+
                                "เงินคงเหลือมีค่า " + "\"มากกว่า\"" + " เงินออมที่ตั้งเป้าไว้\n"+
                                "แสดงว่าการออมของคุณ -> "+"ดีมาก!");
            }else{
                compare.setText("จากกราฟแสดงให้เห็นว่า\n"+
                        "เงินคงเหลือมีค่า " + "\"น้อยกว่า\"" + " เงินออมที่ตั้งเป้าไว้\n"+
                        "แสดงว่าการออมของคุณ -> "+"ดี!");
            }
        } else if (remainingMoney >= (savingAmount*50)/100) {
            compare.setText("จากกราฟแสดงให้เห็นว่า\n"+
                    "เงินคงเหลือมีค่า " + "\"น้อยกว่า\"" + " เงินออมที่ตั้งเป้าไว้\n"+
                    "แสดงว่าการออมของคุณ -> "+"ปกติ!");
        } else {
            if (remainingMoney >= ((savingAmount*20)/100)) {
                compare.setText("จากกราฟแสดงให้เห็นว่า\n"+
                        "เงินคงเหลือมีค่า " + "\"น้อยกว่า\"" + " เงินออมที่ตั้งเป้าไว้\n"+
                        "แสดงว่าการออมของคุณ -> "+"ไม่ค่อยดี!");
            }else{
                compare.setText("จากกราฟแสดงให้เห็นว่า\n"+
                        "เงินคงเหลือมีค่า " + "\"น้อยกว่า\"" + " เงินออมที่ตั้งเป้าไว้\n"+
                        "แสดงว่าการออมของคุณ -> "+"ไม่ดี!");
            }
        }
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