package com.example.visitlab.ConsultaVisitador;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import com.example.visitlab.R;

import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;


public class PerfilActivity extends AppCompatActivity {

    private List<Visitador> visitadorList = new ArrayList<>();
    private RecyclerView recyclerView;
    private VisitadoresAdapter mAdapter;
    private SearchView svSearch;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        myDialog = new Dialog(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new VisitadoresAdapter(visitadorList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Prueba",Toast.LENGTH_SHORT).show();
                PopupMenu popupMenu=new PopupMenu(view.getContext(),view);
                //popupMenu.inflate(R.layout.custompopup);

            }


        });

        recyclerView.setAdapter(mAdapter);

        prepareVisitadorData();
        //recyclerView.setOnClickListener();
    }
/*
    public void ShowPopUp(View v){
        TextView = txtclose;

    }
*/

    private void prepareVisitadorData() {
        Visitador visitador = new Visitador("Maria Perez", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Espinoza", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Salas", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Ojeda", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Cordova", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Silva", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Montalvo", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Leon", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Benites", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Bermudez", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Pasos", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Carpio", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Cuadros", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Flores", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Fuentes", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Herrera", " ", " ");
        visitadorList.add(visitador);

        mAdapter.notifyDataSetChanged();
    }

}

/*
public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ListView lstAsesores = (ListView)findViewById(R.id.lstAsesores);

        int i = 0;
        String[] matriz = new String[15];
        matriz[i++] = "Maria Perez";
        matriz[i++] = "Maria Gomez";
        matriz[i++] = "Maria Paz";
        matriz[i++] = "Maria Guerra";
        matriz[i++] = "Maria Sofia";
        matriz[i++] = "Maria Cristina";
        matriz[i++] = "Maria Vasquez";
        matriz[i++] = "Maria Rosas";
        matriz[i++] = "Maria Ospina";
        matriz[i++] = "Maria Ruiz";
        matriz[i++] = "Maria Lopez";
        matriz[i++] = "Maria Estela";
        matriz[i++] = "Maria Alcala";
        matriz[i++] = "Maria Benites";
        matriz[i++] = "Maria Bueno";


        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(PerfilActivity.this,
                android.R.layout.simple_list_item_1,
                matriz);
        lstAsesores.setAdapter(adaptador);

    }
}
*/