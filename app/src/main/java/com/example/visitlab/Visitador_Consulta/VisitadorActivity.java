package com.example.visitlab.Visitador_Consulta;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.Request;
import com.example.visitlab.R;

import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VisitadorActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private static final String URL_Visitadores = "http://192.168.1.11:8080/visitlabperu/consultar_visitador.php";
    //private static final String URL_Visitadores = "http://10.0.2.2/consultar_visitador.php";
    private List<Visitadores> visitadoresList;
    private RecyclerView rv_listaVisitadores;
    private SearchView txtBuscar;

    ListaVisitadoresAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitador);

        try {

            txtBuscar = findViewById(R.id.txtBuscar);

            rv_listaVisitadores = (RecyclerView)findViewById(R.id.listaVisitadores);
            rv_listaVisitadores.setHasFixedSize(true);
            rv_listaVisitadores.setLayoutManager(new LinearLayoutManager(this));

            visitadoresList = new ArrayList<>();

            loadvisitadores();

            txtBuscar.setOnQueryTextListener(this);   // debido a esto se cae la pantalla al querer sar el SearchView !!!!!!!!!!!!!!!!!
        }
        catch (Exception e){
            e.printStackTrace();
        }



    }

    private void loadvisitadores(){
        Toast.makeText(this,"Lista de Visitadores",Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_Visitadores,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {

                                JSONObject visitador = array.getJSONObject(i);

                                visitadoresList.add(new Visitadores(
                                        visitador.getInt("vm_id_visitador"),
                                        visitador.getInt("vm_dni"),
                                        visitador.getString("vm_apellido"),
                                        visitador.getString("vm_nombre"),
                                        visitador.getString("vm_email"),
                                        visitador.getString("vm_photo"),
                                        visitador.getInt("vm_citas_hechas"),
                                        visitador.getInt("vm_citas_total")

                                ));
                            }
                            adapter = new ListaVisitadoresAdapter(VisitadorActivity.this, visitadoresList);
                            rv_listaVisitadores.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }
}
