package com.example.visitlab.promociones;

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

public class PromocionesActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private static final String URL_Promociones = "http://192.168.1.11:8080/visitlabperu/consultar_promocion.php";

    private List<Promociones> promocionesList;
    private RecyclerView rv_listaPromocion;
    private SearchView txtBuscar;

    ListaPromocionesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promociones);

        try {

            txtBuscar = findViewById(R.id.txtBuscar);

            rv_listaPromocion = (RecyclerView)findViewById(R.id.listapromociones);
            rv_listaPromocion.setHasFixedSize(true);
            rv_listaPromocion.setLayoutManager(new LinearLayoutManager(this));

            promocionesList = new ArrayList<>();

            loadPromocion();

            txtBuscar.setOnQueryTextListener(this);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private void loadPromocion(){
        Toast.makeText(this,"Lista de Promociones",Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_Promociones,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {

                                JSONObject promociones = array.getJSONObject(i);

                                promocionesList.add(new Promociones(
                                        promociones.getInt("P_id_promocion"),
                                        promociones.getString("P_descripcion"),
                                        promociones.getString("P_photo")
                                ));
                            }
                            adapter = new ListaPromocionesAdapter(PromocionesActivity.this, promocionesList);
                            rv_listaPromocion.setAdapter(adapter);
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
