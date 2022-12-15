package com.example.zakatgold;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class PaymentActivity extends AppCompatActivity {

    public static String WEIGHT = "WEIGHT";
    public static String CURRENTVALUE = "CURRENTVALUE";
    public static String RADIOTYPE = "RADIOTYPE";
    double goldBalance, totalValue, zakatPayable, totalZakat;
    private int Weight, CurrentValue;
    private String btType;
    TextView tvOutput, tvO1, tvO2, tvO3, tvO4;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        //Back Menu on Title Bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Output TextView Declaration
        tvOutput = findViewById(R.id.tvOutput);
        tvO1 = findViewById(R.id.tvO1);
        tvO2 = findViewById(R.id.tvO2);
        tvO3 = findViewById(R.id.tvO3);
        tvO4 = findViewById(R.id.tvO4);

        //Get Value from Input (MainActivity)
        Intent in = getIntent();
        Weight = in.getIntExtra(WEIGHT, 0);
        CurrentValue = in.getIntExtra(CURRENTVALUE, 0);
        btType = in.getStringExtra(RADIOTYPE);

        //Calculation for Zakat Payment

        //i. The Total Value of The Gold
        totalValue = Weight * CurrentValue;

        //ii. RadioButton Value for Balance Gold Weight
        if (btType.equals("Keep")) {
            goldBalance = Weight - 85;
        } else if (btType.equals("Wear")) {
            goldBalance = Weight - 200;
        }

        //iii. Zakat Payable (if balance < 0, no zakat)
        if(goldBalance<0){
            zakatPayable = 0 * CurrentValue;
        } else {
            zakatPayable = goldBalance * CurrentValue;
        }

        //iv. The Total Zakat should pay
        totalZakat = 0.025 * zakatPayable;
        if(totalZakat == 0){
            Toast.makeText(this, "You do not have to pay zakat.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Your total zakat is RM " +totalZakat, Toast.LENGTH_SHORT).show();
        }

        //Show Output
        tvO1.setText("Total Gold Value :  RM " + totalValue);
        tvO2.setText("Gold Weight (Zakat) : " + goldBalance + " g");
        tvO3.setText("Zakat Payable : RM " + zakatPayable);
        tvO4.setText("Total Zakat: RM " + totalZakat);
    }
}