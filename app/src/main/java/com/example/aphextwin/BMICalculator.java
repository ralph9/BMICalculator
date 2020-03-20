package com.example.aphextwin;

import android.content.Context;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


public class BMICalculator extends AppCompatActivity{

    //First we get the units from the inputs and we check for validity
    EditText heightInput, weightInput;
    TextView heightText, weightText;
    double valueHeight, valueWeight;
    double centimetersPerson, kilogramsPerson;
    boolean isUnitSystemMetric = true;
    Context context;
    BMICategory resultBMI;
    TextView resultText;
    MainActivity parent;
    public BMICalculator(MainActivity parent, Context cont, boolean unitsChosenAreMetric, EditText heightInputPassed, EditText weightInputPassed, TextView heightText, TextView weightText, TextView resultText){
        context = cont;
        isUnitSystemMetric = unitsChosenAreMetric;
        this.heightInput = heightInputPassed;
        this.weightInput = weightInputPassed;
        this.heightText = heightText;
        this.weightText = weightText;
        this.resultText = resultText;
        this.parent = parent;
    }

    protected void checkInputs(){

        //Check units and make any changes if necessary
        String textContentHeight = heightText.getText().toString();
        String textContentWeight = weightText.getText().toString();

        //Change the flag accordingly based on the text labels
        isUnitSystemMetric = !textContentHeight.contains(parent.getResources().getString(R.string.ft_unit)) && !textContentWeight.contains(parent.getResources().getString(R.string.lb_unit));

        if (heightInput.getText() != null && weightInput.getText() != null && !heightInput.getText().toString().equals("") && !weightInput.getText().toString().equals("")) {
                valueHeight = Double.parseDouble(String.valueOf(heightInput.getText()));
                valueWeight = Double.parseDouble(String.valueOf(weightInput.getText()));
            //displayMessage(String.valueOf(valueHeight) + String.valueOf(valueWeight));
            checkRange();
        } else {
            displayMessage(getString(R.string.empty_inputs));
        }
    }

    protected void checkRange() {
        String errorMessageWrongInput = parent.getResources().getString(R.string.wrong_inputs);
        //We have to check for different range depending on the unit system selected
        if (isUnitSystemMetric) {
            //Metric system chosen
            if (valueHeight > 240 || valueHeight < 100 || valueWeight > 300 || valueWeight < 40) {
                displayMessage(errorMessageWrongInput);

            } else {
               // displayMessage(valueHeight + " " + valueWeight);
                centimetersPerson = valueHeight;
                kilogramsPerson = valueWeight;
                calculateBMI();
            }
        } else {
            //Imperial system chosen
            //660 pounds top, 88 pounds low, 3,2 foot low, 7.9 foot top
            if(valueHeight > 7.9 || valueHeight < 3.2 || valueWeight > 660 || valueWeight < 88){
                displayMessage(errorMessageWrongInput);
            }
            else{
              //  displayMessage(valueHeight + " " + valueWeight);
                //Here we convert to metric units in order to apply the BMI formula
                double feetsInCentimeters = valueHeight * 30.48;
                double lbsInKilograms = valueWeight * 0.45359237;
                centimetersPerson = feetsInCentimeters;
                kilogramsPerson = lbsInKilograms;
                calculateBMI();
            }
        }

    }

    protected void calculateBMI(){
            double metersPerson = centimetersPerson / 100;
            //We apply the formula BMI = kgs / m^2
            double valueBMI = kilogramsPerson / (metersPerson*metersPerson);
            if(valueBMI < 18.5){
                resultBMI = new BMICategory(0);
            }else if(valueBMI < 24.9){
                resultBMI = new BMICategory(1);
            }else if(valueBMI < 29.9){
                resultBMI = new BMICategory(2);
            }else if(valueBMI < 39.9){
                resultBMI = new BMICategory(3);
            }else if(valueBMI > 39.9){
                resultBMI = new BMICategory(4);
            }
           // displayMessage("Category " + resultBMI.toString());
           // String colorCategory = resultBMI.getColor(resultBMI.categoryChosen);


            //We obtain the color, set the text to the according category and change its color
            // to the designated value
            resultBMI.getColor(resultBMI.categoryChosen);
            System.out.println(resultBMI.colorToDepict);
            resultText.setText(resultBMI.categoryString);
            if(resultBMI.colorToDepict != R.color.ORANGE){
                resultText.setBackgroundColor(resultBMI.colorToDepict);
            }else{
                resultText.setBackgroundColor(Color.rgb(255, 140,0));
            }

    }

    //Method used to display customized text within a given context
    public void displayMessage(String optionSelectedString){
        Toast.makeText(context,optionSelectedString,Toast.LENGTH_SHORT).show();
    }

}

//Class pertaining the different BMI categories
class BMICategory {

    //Variables, with a category, its color and its String representation
    //Mainly for displaying purposes
    int categoryChosen;
    int colorToDepict;
    String categoryString;


    BMICategory(int category) {
        //System.out.println("Category:" + category);
        categoryChosen = category;
    }

    //Method to retrieve a color depending on the category
    void getColor(int categoryChosen){
        switch(categoryChosen){
            case 0:
                colorToDepict = Color.BLUE;
                categoryString = "UNDERWEIGHT";
                break;
            case 1:
                colorToDepict = Color.GREEN;
                categoryString = "NORMAL";
                break;
            case 2:
                colorToDepict = Color.YELLOW;
                categoryString = "OVERWEIGHT";
                break;
            case 3:
                colorToDepict = R.color.ORANGE;
                categoryString = "OBESE";
                break;
            case 4:
                colorToDepict = Color.RED;
                categoryString = "EXTREMELY OBESE";
                break;
        }

        //System.out.println(colorToDepict);
    }
}