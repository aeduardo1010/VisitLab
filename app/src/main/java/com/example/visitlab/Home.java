package com.example.visitlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.visitlab.Visitador_Menu.MenuVisitadorActivity;
import com.example.visitlab.Visitas_Menu.MenuVisitaActivity;
import com.example.visitlab.calendario.CalendarioActivity;
import com.example.visitlab.medicamentos.MedicamentosActivity;
import com.example.visitlab.medicamentos.MenuMedicamentosActivity;
import com.example.visitlab.promociones.PromocionesActivity;
import com.example.visitlab.ubicaciones.UbicacionesActivity;

public class Home extends AppCompatActivity implements View.OnClickListener{

    private CardView CalendarioCV,PerfilCV, VisitaCV, MedicamentosCV, PromocionesCV, LocalizacionCV; // MasVisitadorCV,VerVisitadorCV;

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

     //   MasVisitadorCV=(CardView) findViewById(R.id.ad_calendario);
     //   VerVisitadorCV=(CardView) findViewById(R.id.ad_perfil);


        //Add Click Listener to the cards
        CalendarioCV.setOnClickListener(this);
        PerfilCV.setOnClickListener(this);
        VisitaCV.setOnClickListener(this);
        MedicamentosCV.setOnClickListener(this);
        PromocionesCV.setOnClickListener(this);
        LocalizacionCV.setOnClickListener(this);

     //   MasVisitadorCV.setOnClickListener(this);
     //   VerVisitadorCV.setOnClickListener(this);

    }



    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.ad_promociones: i  = new Intent(this, PromocionesActivity.class); startActivity(i); break;
            case R.id.ad_visita: i  = new Intent(this, MenuVisitaActivity.class); startActivity(i); break;
            case R.id.ad_perfil: i  = new Intent(this, MenuVisitadorActivity.class); startActivity(i); break;
            case R.id.ad_calendario: i  = new Intent(this, CalendarioActivity.class); startActivity(i); break;
            case R.id.ad_medicamentos: i  = new Intent(this, MenuMedicamentosActivity.class); startActivity(i); break;
            case R.id.ad_localizacíón: i  = new Intent(this, UbicacionesActivity.class); startActivity(i); break;
         //   case R.id.ad_masvisitador: i  = new Intent(this, PerfilActivity.class); startActivity(i); break;
        //    case R.id.ad_vervisitador: i  = new Intent(this, VisitasActivity.class); startActivity(i); break;
            default:break;
        }

    }
}