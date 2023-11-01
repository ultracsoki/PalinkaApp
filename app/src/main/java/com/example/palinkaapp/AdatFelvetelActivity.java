package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothLeAudioCodecStatus;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdatFelvetelActivity extends AppCompatActivity {

    private EditText editTextFozo;
    private EditText editTextGyumolcs;
    private EditText editTextAlkoholtartalom;
    private Button buttonRogzit;
    private Button buttonRogzitVissza;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adat_felvetel);
        init();

        buttonRogzit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextFozo.getText().toString().isEmpty() || editTextGyumolcs.getText().toString().isEmpty() || editTextAlkoholtartalom.getText().toString().isEmpty())
                {
                    Toast.makeText(AdatFelvetelActivity.this, "Minden adatot meg kell adnia!", Toast.LENGTH_SHORT).show();
                }
                else 
                {
                    adatRogzites();
                }
            }
        });

        buttonRogzitVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdatFelvetelActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void adatRogzites()
    {
        String fozo = editTextFozo.getText().toString();
        String gyumolcs = editTextGyumolcs.getText().toString();
        String alkoholTartalomString = editTextAlkoholtartalom.getText().toString();
        int alkoholTartalom = Integer.parseInt(alkoholTartalomString);
        if (dbHelper.rogzites(fozo,gyumolcs,alkoholTartalom))
        {
            Toast.makeText(this, "Az adat felvétele sikeres volt!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Az adat felvétele sikertelen volt!", Toast.LENGTH_SHORT).show();
        }
    }

    public void init()
    {
        editTextFozo = findViewById(R.id.editTextFozo);
        editTextGyumolcs = findViewById(R.id.editTextGyumolcs);
        editTextAlkoholtartalom = findViewById(R.id.editTextAlkoholtartalom);
        buttonRogzit = findViewById(R.id.buttonRogzit);
        buttonRogzitVissza = findViewById(R.id.buttonRogzitVissza);
        dbHelper = new DBHelper(AdatFelvetelActivity.this);
    }
}

