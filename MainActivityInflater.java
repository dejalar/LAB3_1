package com.lab1a.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lab1a.R;
import com.lab1a.execution.Factorization;
import com.lab1a.utils.exception.TimeExceededException;


public class MainActivityInflater {

    public static void inflate(AppCompatActivity activity) {

        EditText editTextInputN = activity.findViewById(R.id.input_n);
        Button buttonCount = activity.findViewById(R.id.button_count);
        TextView textViewOutputResult = activity.findViewById(R.id.textview_output_result);

        View.OnClickListener onButtonCountClick = v -> {

            String stringN = String.valueOf(editTextInputN.getText());

            if (stringN.trim().equals("")) {

                textViewOutputResult.setTextColor(activity.getResources().getColor(R.color.red));
                textViewOutputResult.setText("Не введені дані");

            } else if (stringN.trim().equals("0")) {

                textViewOutputResult.setTextColor(activity.getResources().getColor(R.color.red));
                textViewOutputResult.setText("Введене число не є натуральним");

            } else {

                long n = Long.parseLong(stringN);

                try {

                    Long [] multipliers = new Factorization().factorize(n);

                    StringBuilder result = new StringBuilder("n = " + n + " = ");

                    for (int i = 0; i < multipliers.length - 1; i++) {
                        result.append(multipliers[i]).append(" * ");
                    }

                    result.append(multipliers[multipliers.length-1]);

                    if (multipliers[0] == n) {
                        result.append("\n").append("(Введене число є простим)");
                    }

                    textViewOutputResult.setTextColor(activity.getResources().getColor(R.color.green));
                    textViewOutputResult.setText(result);

                } catch (TimeExceededException exception) {

                    textViewOutputResult.setTextColor(activity.getResources().getColor(R.color.red));
                    textViewOutputResult.setText("Час факторизації перевищив 3 секунди");

                }

            }

        };

        buttonCount.setOnClickListener(onButtonCountClick);

    }

}
