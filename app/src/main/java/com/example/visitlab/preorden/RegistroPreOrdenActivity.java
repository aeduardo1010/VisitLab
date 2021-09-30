package com.example.visitlab.preorden;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.visitlab.R;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class RegistroPreOrdenActivity extends AppCompatActivity {

    Spinner comboTipoVisita, comboListaLabs;
    EditText etDescripcion;
    Button btnGuardarPre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_pre_orden);

        comboListaLabs=(Spinner) findViewById(R.id.idSpinnerPOLaboratorios);
        comboTipoVisita=(Spinner) findViewById(R.id.idSpinnerPOTipoVisita);

        ArrayAdapter<CharSequence> adapterPOLabs=ArrayAdapter.createFromResource(this,
                R.array.arrayLabs, android.R.layout.simple_spinner_item);
        comboListaLabs.setAdapter(adapterPOLabs);

        ArrayAdapter<CharSequence> adapterPOTipoVisita=ArrayAdapter.createFromResource(this,
                R.array.arrayTipoVisita, android.R.layout.simple_spinner_item);
        comboTipoVisita.setAdapter(adapterPOTipoVisita);

        etDescripcion=(EditText) findViewById(R.id.idDescripcionPO);
        btnGuardarPre=(Button) findViewById(R.id.btnGuardarPreOrden);

        btnGuardarPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejecutarServicioPO("http://192.168.0.104/visitlabperu/insertar_preorden.php");
            }
        });
    }


    private void ejecutarServicioPO(String URL){
        StringRequest stringRequestMed=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),"¡Registro exitoso!", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param=new HashMap<String, String>();
                param.put("po_id_preorden"," ");
                param.put("po_descripcion",etDescripcion.getText().toString());
                param.put("po_nombre_laboratorio",comboListaLabs.getSelectedItem().toString());
                param.put("po_tipo_visita",comboTipoVisita.getSelectedItem().toString());
                return param;
            }
        };
        RequestQueue requestQueueMed= Volley.newRequestQueue(this);
        requestQueueMed.add(stringRequestMed);
    }
}