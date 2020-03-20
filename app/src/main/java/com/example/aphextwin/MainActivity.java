package com.example.aphextwin;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Unit system chosen at first, by default is Metric
    public boolean isUnitSystemMetric = true;
    BMICalculator calculator = null;

    //Reference for accessing the string resources from BMICalculator and BMIDetails
    public MainActivity referenceToMainActivity = this;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = findViewById(R.id.buttonCalculate);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //displayMessage("Button clicked");
                 calculator = new BMICalculator(referenceToMainActivity, getApplicationContext(),true, (EditText)findViewById(R.id.inputHeight), (EditText)findViewById((R.id.inputWeight)),
                        (TextView) findViewById(R.id.textHeight), (TextView) findViewById(R.id.textWeight), (TextView) findViewById(R.id.textResult));
                 calculator.checkInputs();
            }
        });

        final TextView resultsTextView = findViewById(R.id.textResult);
        resultsTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //displayMessage("Result clicked");
                Intent intent=new Intent(MainActivity.this,BMIDetails.class);
                int codeForRequest;
                if(calculator.resultBMI != null) {
                    codeForRequest = calculator.resultBMI.categoryChosen;
                    intent.setAction(calculator.resultBMI.categoryString);
                    startActivityForResult(intent, codeForRequest);
                }

            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_units,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int itemId = item.getItemId();
        if (itemId == R.id.action_metric) {
           displayMessage(getString(R.string.changet_to_metric));
            TextView textViewHeight = (TextView)findViewById(R.id.textHeight);
            textViewHeight.setText(R.string.height_cm);

            TextView textViewWeight = (TextView)findViewById(R.id.textWeight);
            textViewWeight.setText(R.string.weight_kg);

            return true;
        } else if (itemId == R.id.action_imperial) {
            displayMessage(getString(R.string.change_to_imperial));

            TextView textViewHeight = (TextView)findViewById(R.id.textHeight);
            textViewHeight.setText(R.string.height_ft);

            TextView textViewWeight = (TextView)findViewById(R.id.textWeight);
            textViewWeight.setText(R.string.weight_lb);

            return true;
        } else if(itemId == R.id.action_about){
            //displayMessage("Changing to about page");
            Intent intent = new Intent(MainActivity.this, About.class);
            startActivity(intent);
            return true;
        }
        return true;
    }

    private void displayMessage(String optionSelectedString){
        Toast.makeText(this,optionSelectedString,Toast.LENGTH_SHORT).show();
    }

}




