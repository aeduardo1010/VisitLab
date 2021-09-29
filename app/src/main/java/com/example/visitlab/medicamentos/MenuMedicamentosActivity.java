package com.example.visitlab.medicamentos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.visitlab.R;
//import com.example.visitlab.RegistraVisitador.RegistrarVisitadorActivity;

public class MenuMedicamentosActivity extends AppCompatActivity implements View.OnClickListener{

    public CardView AgregarMedicamentoCV, BuscarMedicamentoCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_medicamentos);

        AgregarMedicamentoCV=(CardView) findViewById(R.id.ad_agregar_medicamento);
        BuscarMedicamentoCV=(CardView) findViewById(R.id.ad_buscar_medicamento);

        AgregarMedicamentoCV.setOnClickListener(this);
        BuscarMedicamentoCV.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.ad_agregar_medicamento: i  = new Intent(this, AgregarMedicamentoActivity.class); startActivity(i); break;
            case R.id.ad_buscar_medicamento: i  = new Intent(this, BuscarMedicamentoActivity.class); startActivity(i); break;
            default:break;
        }
    }
}