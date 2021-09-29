package com.example.visitlab.medicamentos;

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

public class BuscarMedicamentoActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private static final String URL_BuscarMedicamento = "http://192.168.0.104/visitlabperu/consultar_medicamento.php";
    private List<Medicamento> listaMedicamento;
    private RecyclerView rvListaMedicamento;
    private SearchView txtBuscarMedicamento;

    ListaMedicamentoAdapter adapterMedicamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento);

        try{
            txtBuscarMedicamento = findViewById(R.id.txtBuscarMedicamento);

            rvListaMedicamento = (RecyclerView) findViewById(R.id.listaMedicamentos);
            rvListaMedicamento.setHasFixedSize(true);
            rvListaMedicamento.setLayoutManager(new LinearLayoutManager(this));

            listaMedicamento = new ArrayList<>();

            cargarListaMedicamentos();
            txtBuscarMedicamento.setOnQueryTextListener(this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void cargarListaMedicamentos(){
        //Toast.makeText(this,"Lista de Medicamentos",Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_BuscarMedicamento,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {

                                JSONObject jsonMedicamento = array.getJSONObject(i);

                                listaMedicamento.add(new Medicamento(
                                        jsonMedicamento.getString("ms_nombre"),
                                        jsonMedicamento.getString("ms_laboratorio"),
                                        jsonMedicamento.getString("ms_especialidad"),
                                        jsonMedicamento.getString("ms_photo")
                                ));
                            }
                            adapterMedicamento = new ListaMedicamentoAdapter(BuscarMedicamentoActivity.this, listaMedicamento);
                            rvListaMedicamento.setAdapter(adapterMedicamento);
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
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapterMedicamento.filtrado(newText);
        return false;
    }
}