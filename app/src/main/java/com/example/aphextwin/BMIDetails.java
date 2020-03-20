package com.example.aphextwin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BMIDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_details);
        categoryChosen = getIntent().getAction();
        fillListOfDescriptions();
        retrieveTextToShow();
        displayDetails();
    }
    protected String categoryChosen;
    protected String textSelected;

    String[] listOfDescriptions = null;
    public void fillListOfDescriptions() {
        listOfDescriptions = new String[]{
                getString(R.string.details_underweight),
                getString(R.string.details_normal),
                getString(R.string.details_overweight),
                getString(R.string.details_obese),
                getString(R.string.details_extremelyobese)
        };
    }
    public void retrieveTextToShow(){
        switch(categoryChosen){
            case "UNDERWEIGHT":
                textSelected = listOfDescriptions[0];
                break;
            case "NORMAL":
                textSelected = listOfDescriptions[1];
                break;
            case "OVERWEIGHT":
                textSelected = listOfDescriptions[2];
                break;
            case "OBESE":
                textSelected = listOfDescriptions[3];
                break;
            case "EXTREMELY OBESE":
                textSelected = listOfDescriptions[4];
                break;
        }
    }

    public void displayDetails(){
        //We change the text on the title and the details textView according to the data
        //passed from the BMI calculator
        TextView titleCategory = (TextView) findViewById(R.id.categoryLabelText);
        titleCategory.setText(categoryChosen);

        TextView textDetails = (TextView) findViewById(R.id.textDetails);
        textDetails.setText(textSelected);
    }







}
