package com.example.visitlab.Sesion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.visitlab.Home;
import com.example.visitlab.R;
import com.example.visitlab.data.Result;

import java.util.HashMap;
import java.util.Map;

public class InicioSesionActivity extends AppCompatActivity {

    private EditText etCodUser, etPassword;
    private String coduser, password;
    private String URL = "http://192.168.1.11:8080/visitlabperu/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        coduser = password = "";
        etCodUser = findViewById(R.id.coduser);
        etPassword = findViewById(R.id.password);
    }

    public void login(View view) {
        coduser = etCodUser.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        if(!coduser.equals("") && !password.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("res", response);
                    if (response.equals("success")) {
                        //Intent intent = new Intent(InicioSesionActivity.this, Result.Success.class);
                        Intent intent = new Intent(InicioSesionActivity.this, Home.class);
                        startActivity(intent);
                        finish();
                    } else if (response.equals("failure")) {
                        Toast.makeText(InicioSesionActivity.this, "Invalid Login Id/Password...", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(InicioSesionActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("coduser", coduser);
                    data.put("password", password);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(this, "Fields can not be empty!", Toast.LENGTH_SHORT).show();
        }
    }
/*
    public void register(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        finish();
    }
*/
}