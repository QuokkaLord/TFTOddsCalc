package com.example.tftoddscalc;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import org.apache.commons.math3.distribution.BinomialDistribution;

import org.apache.commons.math3.util.Precision;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button calc;
    EditText lvl;
    EditText rls;

    TextView R0C0;
    TextView R0C1;
    TextView R0C2;
    TextView R0C3;
    TextView R0C4;
    TextView R1C0;
    TextView R1C1;
    TextView R1C2;
    TextView R1C3;
    TextView R1C4;

    private double[][] pTable = {
            {1, 1, .75, .55, .45, .23, .19, .15, .10, .05, .01},
            {0, 0, .25, .3, .33, .4, .3, .2, .12, .1, .02},
            {0, 0, 0, .15, .2, .3, .35, .35, .3, .2, .12},
            {0, 0, 0, 0, .02, .05, .15, .25, .3, .4, .5},
            {0, 0, 0, 0, 0, 0, .01, .05, .15, .25, .35}};

    private int[] numUnits = {12, 12, 12, 11, 8};

    private double[][] calculate(int rolls, int level){
        double[][] p = new double[2][5];
        for (int i = 0; i < 5; i++) {
            BinomialDistribution binomialAlg = new BinomialDistribution(rolls*5, pTable[i][level-1]/numUnits[i]);
            p[0][i] = ((1-binomialAlg.cumulativeProbability(1))*100);
            p[1][i] = ((1-binomialAlg.cumulativeProbability(3))*100);
        }
        return p;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        R0C0 = (TextView)findViewById(R.id.R0C0);
        R0C1 = (TextView)findViewById(R.id.R0C1);
        R0C2 = (TextView)findViewById(R.id.R0C2);
        R0C3 = (TextView)findViewById(R.id.R0C3);
        R0C4 = (TextView)findViewById(R.id.R0C4);

        R1C0 = (TextView)findViewById(R.id.R1C0);
        R1C1 = (TextView)findViewById(R.id.R1C1);
        R1C2 = (TextView)findViewById(R.id.R1C2);
        R1C3 = (TextView)findViewById(R.id.R1C3);
        R1C4 = (TextView)findViewById(R.id.R1C4);

        R0C0.setText("");
        R0C1.setText("");
        R0C2.setText("");
        R0C3.setText("");
        R0C4.setText("");

        R1C0.setText("");
        R1C1.setText("");
        R1C2.setText("");
        R1C3.setText("");
        R1C4.setText("");

        calc = (Button)findViewById(R.id.CalcButton);

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvl = (EditText)findViewById(R.id.LvlInput);
                rls = (EditText)findViewById(R.id.RlsInput);

                double[][] p = calculate(Integer.parseInt(rls.getText().toString()), Integer.parseInt(lvl.getText().toString()));

                R0C0.setText(Double.toString(Precision.round(p[0][0], 2))+"%");
                R0C1.setText(Double.toString(Precision.round(p[0][1], 2))+"%");
                R0C2.setText(Double.toString(Precision.round(p[0][2], 2))+"%");
                R0C3.setText(Double.toString(Precision.round(p[0][3], 2))+"%");
                R0C4.setText(Double.toString(Precision.round(p[0][4], 2))+"%");

                R1C0.setText(Double.toString(Precision.round(p[1][0], 2))+"%");
                R1C1.setText(Double.toString(Precision.round(p[1][1], 2))+"%");
                R1C2.setText(Double.toString(Precision.round(p[1][2], 2))+"%");
                R1C3.setText(Double.toString(Precision.round(p[1][3], 2))+"%");
                R1C4.setText(Double.toString(Precision.round(p[1][4], 2))+"%");
            }
        });
    }


}