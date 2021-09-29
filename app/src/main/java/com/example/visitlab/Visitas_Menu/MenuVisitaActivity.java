package com.example.visitlab.Visitas_Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.visitlab.Visitas_Agendar.AgendarVisitasActivity;
import com.example.visitlab.R;
import com.example.visitlab.Visitas_Finalizar.TerminarVisitasActivity;

public class MenuVisitaActivity extends AppCompatActivity implements View.OnClickListener{
    public CardView AgendaCitaCV,FinalizaCitaCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_visita);

        //defining cards
        AgendaCitaCV=(CardView) findViewById(R.id.ad_agendacita);
        FinalizaCitaCV=(CardView) findViewById(R.id.ad_finalizacita);

        //Add Click Listener to the cards
        AgendaCitaCV.setOnClickListener(this);
        FinalizaCitaCV.setOnClickListener(this);
    }

    //@Override
    public void onClick(@NonNull View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.ad_agendacita: i  = new Intent(this, AgendarVisitasActivity.class); startActivity(i); break;
            case R.id.ad_finalizacita: i  = new Intent(this, TerminarVisitasActivity.class); startActivity(i); break;
            default:break;
        }
    }
}