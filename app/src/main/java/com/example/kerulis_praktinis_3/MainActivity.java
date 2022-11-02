package com.example.kerulis_praktinis_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, solutionTv;
    MaterialButton buttonC, buttonPlusMinus, buttonSqrt, buttonMultiply;
    MaterialButton button7, button8, button9, buttonDivide;
    MaterialButton button4, button5, button6, buttonSum;
    MaterialButton button1, button2, button3, buttonMinus;
    MaterialButton buttonAC, button0, buttonDot, buttonEquals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_textview);
        solutionTv = findViewById(R.id.solution_textview);

        assignId(buttonC,R.id.button_clear);
        assignId(buttonPlusMinus,R.id.button_plus_or_minus);
        assignId(buttonSqrt,R.id.button_sqrt);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonDivide,R.id.button_divide);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(buttonSum,R.id.button_sum);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(buttonMinus,R.id.button_minus);
        assignId(buttonAC,R.id.button_AC);
        assignId(button0,R.id.button_0);
        assignId(buttonDot,R.id.button_dot);
        assignId(buttonEquals,R.id.button_equals);

    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataCalculate = solutionTv.getText().toString();

        if(buttonText.equals("AC"))
        {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("="))
        {
            solutionTv.setText(resultTv.getText());
            return;
        }

        if(buttonText.equals("C"))
        {
            dataCalculate = dataCalculate.substring(0, dataCalculate.length()-1);
        }
        else if(buttonText.equals("√"))
        {
            double sqrt = Integer.parseInt(dataCalculate);
            sqrt = Math.sqrt(sqrt);
            dataCalculate = Double.toString(sqrt);
        }
        else if (buttonText.equals("±"))
        {

        }
        else
        {
            dataCalculate = dataCalculate + buttonText;
        }

        solutionTv.setText(dataCalculate);

        String finalResult = getResult(dataCalculate);

        if(!finalResult.equals("Error!"))
        {
            resultTv.setText(finalResult);
        }
    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0"))
            {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e){
            return "Error!";
        }
    }
}