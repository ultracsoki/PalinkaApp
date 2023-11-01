package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdatKeresesActivity extends AppCompatActivity {

    private EditText editTextFozoKeres;
    private EditText editTextGyumolcsKeres;
    private Button buttonKereses;
    private Button buttonKeresesVissza;
    private TextView textViewKeresesAlkoholtartalom;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adat_kereses);
        init();

        buttonKereses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adatKereses();
            }
        });

        buttonKeresesVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdatKeresesActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void adatKereses()
    {
        Cursor adatok = dbHelper.kereses(editTextFozoKeres.getText().toString().trim(),editTextGyumolcsKeres.getText().toString().trim());
        StringBuffer builder = new StringBuffer();
        if (editTextFozoKeres.getText().toString().isEmpty() || editTextGyumolcsKeres.getText().toString().isEmpty())
        {
            builder.append("Hiba a keresés során!");
            Toast.makeText(this, "Minden mezőt ki kell töltenie!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (adatok.moveToNext())
            {
                builder.append("Alkoholtartalom: ").append(adatok.getInt(3)).append(" %").append("\n");
            }
            if (builder.toString().equals(""))
            {
                builder.append("A megadott adatokkal nem található pálinka!");
            }
        }
        textViewKeresesAlkoholtartalom.setText(builder);

    }

    public void init()
    {
        editTextFozoKeres = findViewById(R.id.editTextFozoKeres);
        editTextGyumolcsKeres = findViewById(R.id.editTextGyumolcsKeres);
        buttonKereses = findViewById(R.id.buttonKereses);
        buttonKeresesVissza = findViewById(R.id.buttonKeresesVissza);
        textViewKeresesAlkoholtartalom = findViewById(R.id.textViewKeresesAlkoholtartalom);
        dbHelper = new DBHelper(AdatKeresesActivity.this);
    }
}