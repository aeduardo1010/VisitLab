package com.example.visitlab.preorden;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.example.visitlab.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuPreOrdenActivity extends AppCompatActivity implements View.OnClickListener{

    public CardView RegistrarPO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pre_orden);

        RegistrarPO=(CardView) findViewById(R.id.ad_registrar_po);
        RegistrarPO.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.ad_registrar_po: i  = new Intent(this, RegistroPreOrdenActivity.class); startActivity(i); break;
            default:break;
        }
    }
}