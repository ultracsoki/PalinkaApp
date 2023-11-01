package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.security.KeyRep;

public class MainActivity extends AppCompatActivity {

    private Button buttonPalinkaFelvetele;
    private Button buttonPalinkaKeresese;
    private Button buttonPalinkakListazasa;
    private TextView textViewPalinkakKilistazva;
    private DBHelper dbHelper;

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
                Cursor adatok = dbHelper.adatLekerdezes();
                if (adatok.getCount() == 0)
                {
                    Toast.makeText(MainActivity.this, "Nincs az adatbázisban bejegyzés!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "A listázás sikeres volt!", Toast.LENGTH_SHORT).show();
                    StringBuffer builder = new StringBuffer();
                    while (adatok.moveToNext())
                    {
                        builder.append("ID:").append(adatok.getInt(0)).append("\n");
                        builder.append("Főző:").append(adatok.getString(1)).append("\n");
                        builder.append("Gyümölcs:").append(adatok.getString(2)).append("\n");
                        builder.append("Alkoholtartalom:").append(adatok.getInt(3)).append("\n\n");
                    }
                    textViewPalinkakKilistazva.setText(builder);
                }
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
        dbHelper = new DBHelper(MainActivity.this);
    }
}