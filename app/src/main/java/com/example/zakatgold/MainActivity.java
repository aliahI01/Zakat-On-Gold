package com.example.zakatgold;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    EditText currentValue, weight;
    RadioGroup rgType;
    RadioButton btKeep, btWear;
    TextView tvType, tvGoldWeight, tvCurrentGold;
    Button btnCalculate, btnReset;
    int Weight, CurrentValue, GoldType;
    String radioType;
    SharedPreferences sharedPref;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Define input in main
        weight = findViewById(R.id.weight);
        currentValue = findViewById(R.id.currentValue);
        rgType = findViewById(R.id.rgType);
        btKeep = findViewById(R.id.btKeep);
        btWear = findViewById(R.id.btWear);
        tvGoldWeight = findViewById(R.id.tvZakat);
        tvType = findViewById(R.id.tvType);
        tvCurrentGold = findViewById(R.id.tvCurrentGold);

        btnCalculate = findViewById(R.id.btnCalculate);
        btnReset = findViewById(R.id.btnReset);
        GoldType = rgType.getCheckedRadioButtonId();
        sharedPref = this.getSharedPreferences("weight", Context.MODE_PRIVATE);
        sharedPref = this.getSharedPreferences("rgType", Context.MODE_PRIVATE);
        sharedPref = this.getSharedPreferences("currentValue", Context.MODE_PRIVATE);

        //Calculate Button Action
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (weight.getText().toString().length() == 0) {
                    weight.setError("Please fill in the gold weight.");
                } else if (rgType.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(MainActivity.this, "Please choose type of gold.", Toast.LENGTH_SHORT).show();
                } else if (currentValue.getText().toString().length() == 0){
                    currentValue.setError("Please fill in the current gold value.");
                } else {
                    Toast.makeText(MainActivity.this, "Calculate for zakat.", Toast.LENGTH_SHORT).show();
                    passValue();
                }
            }
        });

        //Reset Button Action
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight.getText().clear();
                rgType.clearCheck();
                currentValue.getText().clear();
            }
        });
    }

    //Method to Pass Value from MainActivity to PaymentActivity
    public void passValue() {

        Weight = Integer.parseInt(weight.getText().toString().trim());
        GoldType = rgType.getCheckedRadioButtonId();
        btKeep = findViewById(GoldType);
        btWear = findViewById(GoldType);
        radioType = btKeep.getText().toString();
        radioType = btWear.getText().toString();
        CurrentValue = Integer.parseInt(currentValue.getText().toString().trim());

        Intent in = new Intent(MainActivity.this, PaymentActivity.class);
        in.putExtra(PaymentActivity.WEIGHT, Weight);
        in.putExtra(PaymentActivity.CURRENTVALUE, CurrentValue);
        in.putExtra(PaymentActivity.RADIOTYPE, radioType);
        startActivity(in);
    }

    //Hamburger Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //Hamburger Menu Item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.calc:
                Intent in = new Intent(this, MainActivity.class);
                startActivity(in);
                break;

            case R.id.about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}