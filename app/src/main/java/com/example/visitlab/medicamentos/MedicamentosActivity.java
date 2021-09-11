package com.example.visitlab.medicamentos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.visitlab.R;
import android.widget.TextView;

//import com.demotxt.myapp.myapplication.R;

public class MedicamentosActivity extends AppCompatActivity {

//TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos);

        //Ocultar el actionbar por defecto
        getSupportActionBar().hide();
    }
}