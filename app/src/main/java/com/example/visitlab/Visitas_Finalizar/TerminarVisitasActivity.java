package com.example.visitlab.Visitas_Finalizar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
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

public class TerminarVisitasActivity extends AppCompatActivity {

    EditText etCodVisita, etCodVisitador;
    Button btnCargarCitas, btnCargarPosicion, btnGuardar;
    ListView listaResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminar_visitas);

        etCodVisita = (EditText)findViewById(R.id.etIdVisita);
        etCodVisitador = (EditText)findViewById(R.id.etIdVisitador);
        btnCargarCitas = (Button)findViewById(R.id.btnCargaCitas);
        btnCargarPosicion = (Button)findViewById(R.id.btnPosicion);
        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        listaResultado = (ListView)findViewById(R.id.lvLista);


/*
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String registro = "http://10.0.3.2/hotelejemplo/registrarReserva.php?idr=NULL&NHab="+etHabitacion.getText()+"&fe="+etFechaEntrada.getText()+"&fs="+etFechaSalida.getText();
                EnviarRecibirDatos(registro);

            }
        });
*/
        btnCargarCitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String consulta = "http://192.168.1.11:8080/visitlabperu/consultarReserva.php?idr="+etCodVisitador.getText();
                EnviarRecibirDatos(consulta);

            }
        });
    }

    public void EnviarRecibirDatos(String URL){

        Toast.makeText(getApplicationContext(), ""+URL, Toast.LENGTH_SHORT).show();

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                response = response.replace("][",",");
                if (response.length()>0){
                    try {
                        JSONArray ja = new JSONArray(response);
                        Log.i("sizejson",""+ja.length());
                        CargarListView(ja);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);

    }

    public void CargarListView(JSONArray ja){

        ArrayList<String> lista = new ArrayList<>();

        for(int i=0;i<ja.length();i+=8){

            try {
                lista.add("COD VISITA:  "+ja.getString(i)+"            COD CLIENTE:  "+ja.getString(i+3)
                        +"   FECHA:  "+ja.getString(i+1)+"    HORA:  "+ja.getString(i+2)
                );
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        listaResultado.setAdapter(adaptador);
    }

}