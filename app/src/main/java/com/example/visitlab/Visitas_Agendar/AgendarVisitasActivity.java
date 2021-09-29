package com.example.visitlab.Visitas_Agendar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.visitlab.R;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AgendarVisitasActivity extends AppCompatActivity {

    EditText etDate;
    EditText etHour;
    EditText etCodCli, etCodVisitador;
    Button btnAgregar;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_visitas);

        btnAgregar=(Button)findViewById(R.id.btnGuardarVisita);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejecutarServicio("http://192.168.1.11:8080/visitlabperu/agendar_visita.php");
            }
        });

        etCodCli=findViewById(R.id.txtIdCliente);
        etCodVisitador=findViewById(R.id.txtIdVisitador);
        etDate=findViewById(R.id.et_date);
        etHour=findViewById(R.id.et_hour);
        Calendar calendar = Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        final int hour=calendar.get(Calendar.HOUR_OF_DAY);
        final int minute=calendar.get(Calendar.MINUTE);

        etDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AgendarVisitasActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        //String date=day+"/"+month+"/"+year;
                        String date=year+"/"+month+"/"+day;
                        etDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        etHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        AgendarVisitasActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        String time=hour+":"+minute;
                        etHour.setText(time);
                    }
                },hour,minute,false);
                timePickerDialog.show();
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
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros=new HashMap<String, String>();
                parametros.put("cita_id_visita"," ");
                parametros.put("cita_fecha",etDate.getText().toString());
                parametros.put("cita_hora",etHour.getText().toString());
                parametros.put("cita_id_cliente",etCodCli.getText().toString());
                parametros.put("cita_id_visitador",etCodVisitador.getText().toString());
                parametros.put("cita_estado","0");
                parametros.put("cita_pos_x","0");
                parametros.put("cita_pos_y","0");
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}