package com.example.visitlab.RegistraVisitador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.visitlab.R;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class RegistrarVisitadorActivity extends AppCompatActivity {
    EditText edtCodigo, edtDNI, edtApellido, edtNombre, edtPassword, edtEmail;
    Button btnAgregar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_visitador);

        edtCodigo=(EditText)findViewById(R.id.txtCodigo);
        edtDNI=(EditText)findViewById(R.id.txtDNI);
        edtApellido=(EditText)findViewById(R.id.txtApellido);
        edtNombre=(EditText)findViewById(R.id.txtNombre);
        edtPassword=(EditText)findViewById(R.id.txtPassword);
        edtEmail=(EditText)findViewById(R.id.txtEmail);
        btnAgregar=(Button)findViewById(R.id.btnGuardarVisitador);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejecutarServicio("http://192.168.1.18:8080/visitlabperu/insertar_visitador.php");
            }
        });


    }

    private void ejecutarServicio(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),"Operación exitosa", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),"=========> Error", Toast.LENGTH_SHORT).show();
            }
        }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> parametros=new HashMap<String, String>();
                parametros.put("vm_id_visitador",edtCodigo.getText().toString());
                parametros.put("vm_dni",edtDNI.getText().toString());
                parametros.put("vm_apellido",edtApellido.getText().toString());
                parametros.put("vm_nombre",edtNombre.getText().toString());
                parametros.put("vm_password",edtPassword.getText().toString());
                parametros.put("vm_email",edtEmail.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}