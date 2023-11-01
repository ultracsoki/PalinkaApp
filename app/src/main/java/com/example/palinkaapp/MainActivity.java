package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.KeyRep;

public class MainActivity extends AppCompatActivity {

    private Button buttonPalinkaFelvetele;
    private Button buttonPalinkaKeresese;
    private Button buttonPalinkakListazasa;
    private TextView textViewPalinkakKilistazva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        buttonPalinkaFelvetele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AdatFelvetelActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonPalinkaKeresese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AdatKeresesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonPalinkakListazasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void init()
    {
        buttonPalinkaFelvetele = findViewById(R.id.buttonPalinkaFelvetele);
        buttonPalinkaKeresese = findViewById(R.id.buttonPalinkaKeresese);
        buttonPalinkakListazasa = findViewById(R.id.buttonPalinkakListazasa);
        textViewPalinkakKilistazva = findViewById(R.id.textViewPalinkakKilistazva);
        textViewPalinkakKilistazva.setMovementMethod(new ScrollingMovementMethod());
    }
}