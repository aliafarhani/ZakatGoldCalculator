package com.example.zakatgoldcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editWeight, editValue;
    Spinner spinnerType;
    Button btnCalculate, btnReset;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editWeight = findViewById(R.id.editWeight);
        editValue = findViewById(R.id.editValue);
        spinnerType = findViewById(R.id.spinnerType);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnReset = findViewById(R.id.btnReset);
        textResult = findViewById(R.id.textResult);

        String[] type = {
                "Select Gold Type",
                getString(R.string.type_keep),
                getString(R.string.type_wear)
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        type
                );

        spinnerType.setAdapter(adapter);

        btnCalculate.setOnClickListener(v -> {

            String weightStr = editWeight.getText().toString();
            String valueStr = editValue.getText().toString();

            if (weightStr.isEmpty()) {
                editWeight.setError(getString(R.string.error_weight));
                editWeight.requestFocus();
                return;
            }

            if (valueStr.isEmpty()) {
                editValue.setError(getString(R.string.error_value));
                editValue.requestFocus();
                return;
            }

            double weight = Double.parseDouble(weightStr);
            double value = Double.parseDouble(valueStr);

            int uruf;

            String selectedType =
                    spinnerType.getSelectedItem().toString();

            if(selectedType.equals("Select Gold Type")){

                textResult.setText("Please select gold type");
                return;

            }
            else if(selectedType.equals(getString(R.string.type_keep))){

                uruf = 85;

            }
            else{

                uruf = 200;
            }

            double zakatWeight = weight - uruf;

            if (zakatWeight < 0) {
                zakatWeight = 0;
            }

            double totalGold = weight * value;
            double zakatPayable = zakatWeight * value;
            double totalZakat = zakatPayable * 0.025;

            textResult.setText(
                    getString(
                            R.string.calc_result,
                            totalGold,
                            zakatPayable,
                            totalZakat
                    )
            );

        });

        btnReset.setOnClickListener(v -> {

            editWeight.setText("");
            editValue.setText("");

            textResult.setText(
                    getString(R.string.result_placeholder)
            );

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(
                R.menu.main_menu,
                menu
        );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.mnuabout) {

            Intent aboutIntent =
                    new Intent(
                            MainActivity.this,
                            AboutActivity.class
                    );

            startActivity(aboutIntent);

            return true;
        }

        else if (id == R.id.mnushare) {

            Intent shareIntent =
                    new Intent(Intent.ACTION_SEND);

            shareIntent.setType("text/plain");

            shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    getString(R.string.share_text)
            );

            startActivity(
                    Intent.createChooser(
                            shareIntent,
                            getString(R.string.share_title)
                    )
            );

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}