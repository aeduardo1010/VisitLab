package com.example.visitlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.example.visitlab.calendario.CalendarioActivity;
import com.example.visitlab.medicamentos.MedicamentosActivity;
import com.example.visitlab.perfil.PerfilActivity;
import com.example.visitlab.promociones.PromocionesActivity;
import com.example.visitlab.visitas.VisitasActivity;

public class Home extends AppCompatActivity implements View.OnClickListener{

    private CardView CalendarioCV,PerfilCV, VisitaCV, MedicamentosCV, PromocionesCV, LocalizacionCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //defining cards
        CalendarioCV=(CardView) findViewById(R.id.ad_calendario);
        PerfilCV=(CardView) findViewById(R.id.ad_perfil);
        VisitaCV=(CardView) findViewById(R.id.ad_visita);
        MedicamentosCV=(CardView) findViewById(R.id.ad_medicamentos);
        PromocionesCV=(CardView) findViewById(R.id.ad_promociones);
        LocalizacionCV=(CardView) findViewById(R.id.ad_localizacíón);

        //Add Click Listener to the cards
        CalendarioCV.setOnClickListener(this);
        PerfilCV.setOnClickListener(this);
        VisitaCV.setOnClickListener(this);
        MedicamentosCV.setOnClickListener(this);
        PromocionesCV.setOnClickListener(this);
        LocalizacionCV.setOnClickListener(this);


    }



    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.ad_promociones: i  = new Intent(this, PromocionesActivity.class); startActivity(i); break;
            case R.id.ad_visita: i  = new Intent(this, VisitasActivity.class); startActivity(i); break;
            case R.id.ad_perfil: i  = new Intent(this, PerfilActivity.class); startActivity(i); break;
            case R.id.ad_calendario: i  = new Intent(this, CalendarioActivity.class); startActivity(i); break;
            case R.id.ad_medicamentos: i  = new Intent(this, MedicamentosActivity.class); startActivity(i); break;
            default:break;
        }

    }
}