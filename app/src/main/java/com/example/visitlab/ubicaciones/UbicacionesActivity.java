package com.example.visitlab.ubicaciones;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.visitlab.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UbicacionesActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private static final String URL_Ubicaciones = "http://192.168.1.11:8080/visitlabperu/consultar_ubicacion.php";

    private List<Ubicaciones> ubicacionesList;
    private RecyclerView rv_listaUbicacion;
    private SearchView txtBuscar;

    ListaUbicacionesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicaciones);

        try {

            txtBuscar = findViewById(R.id.txtBuscar);

            rv_listaUbicacion = (RecyclerView)findViewById(R.id.listaubicaciones);
            rv_listaUbicacion.setHasFixedSize(true);
            rv_listaUbicacion.setLayoutManager(new LinearLayoutManager(this));

            ubicacionesList = new ArrayList<>();

           loadUbicacion();

            txtBuscar.setOnQueryTextListener(this);   // debido a esto se cae la pantalla al querer sar el SearchView !!!!!!!!!!!!!!!!!
        }
        catch (Exception e){
            e.printStackTrace();
        }



    }

    private void loadUbicacion(){
        Toast.makeText(this,"Lista de Ubicaciones",Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_Ubicaciones,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {

                                JSONObject ubicaciones = array.getJSONObject(i);

                                ubicacionesList.add(new Ubicaciones(
                                        ubicaciones.getInt("UV_ID_Visitador"),
                                        ubicaciones.getInt("UV_ID_Ubicacion"),
                                        ubicaciones.getString("UV_X"),
                                        ubicaciones.getString("UV_Y"),
                                        ubicaciones.getString("UV_Fecha"),
                                        ubicaciones.getString("UV_Hora")
                                ));
                            }
                            adapter = new ListaUbicacionesAdapter(UbicacionesActivity.this, ubicacionesList);
                            rv_listaUbicacion.setAdapter(adapter);
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
