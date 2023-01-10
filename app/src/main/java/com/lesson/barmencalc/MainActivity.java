package com.lesson.barmencalc;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.nfc.FormatException;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.Format;

public class MainActivity extends AppCompatActivity {
    private EditText price;
    private SeekBar progress;
    private TextView procent,total;
    private RadioButton red,green;
    private CheckBox taple;
    private LinearLayout root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root=findViewById(R.id.root);
        price=findViewById(R.id.price);
        progress=findViewById(R.id.progress);
        procent=findViewById(R.id.procent);
        total=findViewById(R.id.total);
        red=findViewById(R.id.red);
        green=findViewById(R.id.green);
        taple=findViewById(R.id.taple);
        taple.setChecked(false);
        progress.setVisibility(View.GONE);
        procent.setVisibility(View.GONE);
        taple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(taple.isChecked())
                {
                    progress.setVisibility(View.VISIBLE);
                    procent.setVisibility(View.VISIBLE);
                }
                else
                {
                    progress.setVisibility(View.GONE);
                    procent.setVisibility(View.GONE);
                    progress.setProgress(0);
                }
            }
        });
        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    procent.setText(Integer.toString(progress.getProgress()));
                    double summa = Integer.parseInt(charSequence.toString()) *
                            (1 + progress.getProgress() * 0.01);
                    total.setText(String.format("%.2f", summa));
                }
                catch (Exception ex)
                {
                    procent.setText("0");
                    total.setText("0");
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                try {
                    procent.setText(Integer.toString(i));
                    double summa = Integer.parseInt(price.getText().toString()) *
                            (1 + i * 0.01);
                    total.setText(String.format("%.2f", summa));
                }
                catch (Exception ex)
                {
                    procent.setText("0");
                    total.setText("0");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(red.isChecked())
                    root.setBackgroundColor(Color.RED);
            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(green.isChecked())
                    root.setBackgroundColor(Color.GREEN);
            }
        });
    }

}