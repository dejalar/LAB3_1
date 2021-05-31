package com.example.lab1_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button getResult;
    private EditText input;
    private TextView result;
    private static int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        Activate();
    }


    private static long[] GetSumOfSquares(long n) {
        double x, y;
        counter = 0;
        x = Math.ceil(Math.sqrt(n));
        y = Math.pow(x, 2) - n;


        while (Math.abs(Math.sqrt(y) - Math.ceil(Math.sqrt(y))) > 0.0001f) {
            x++;
            y = Math.pow(x, 2) - n;
            counter++;
        }

        return new long[] {(long) x, (long) Math.sqrt(y)};
    }

    public void Activate(){

        result = (TextView)findViewById(R.id.textView5);
        input = (EditText)findViewById(R.id.editTextNumber2);
        getResult = (Button)findViewById(R.id.button);

        getResult.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        boolean flag = true;

                        int n = Integer.parseInt(input.getText().toString());

                        if (n % 2 == 0) {
                            result.setText("N має бути непарним");
                            flag = false;
                        }

                        if (n <= 1) {
                            result.setText("N має бути більше 1");
                            flag = false;
                        }

                        long[] multipliers = new long[2];
                        long[] sqrts = GetSumOfSquares(n);
                        multipliers[0] = Math.abs(sqrts[0] + sqrts[1]);
                        multipliers[1] = Math.abs(sqrts[0] - sqrts[1]);

                        if (flag) {
                            String res = String.format(n + " = " + multipliers[0] + "*" + multipliers[1] + "\nКількість ітерацій: " + counter);
                            result.setText(res);

                        }

                    }
                }
        );
    }

}
