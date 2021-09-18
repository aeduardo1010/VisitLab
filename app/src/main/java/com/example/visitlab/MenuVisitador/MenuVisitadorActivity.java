package com.example.visitlab.MenuVisitador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.visitlab.ConsultaVisitador.VisitadorActivity;
import com.example.visitlab.R;
import com.example.visitlab.RegistraVisitador.RegistrarVisitadorActivity;

public class MenuVisitadorActivity extends AppCompatActivity implements View.OnClickListener {
    public CardView MasVisitadorCV,VerVisitadorCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_visitador);

        //defining cards
        MasVisitadorCV=(CardView) findViewById(R.id.ad_masvisitador);
        VerVisitadorCV=(CardView) findViewById(R.id.ad_vervisitador);

        //Add Click Listener to the cards
        MasVisitadorCV.setOnClickListener(this);
        VerVisitadorCV.setOnClickListener(this);
    }

    //@Override
    public void onClick(@NonNull View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.ad_masvisitador: i  = new Intent(this, RegistrarVisitadorActivity.class); startActivity(i); break;
            case R.id.ad_vervisitador: i  = new Intent(this, VisitadorActivity.class); startActivity(i); break;
            default:break;
        }
    }
}