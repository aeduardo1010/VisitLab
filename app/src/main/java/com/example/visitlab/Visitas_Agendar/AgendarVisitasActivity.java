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

import com.example.visitlab.R;

import java.sql.Date;
import java.util.Calendar;

//public class AgendarVisitasActivity extends AppCompatActivity implements View.OnClickListener {
public class AgendarVisitasActivity extends AppCompatActivity {
//===============================================================
/*
    Button bt_fecha;
    EditText edt_fecha;
    Button bt_hora;
    EditText edt_hora;
    private int dia, mes, ano, hora, minutos;
*/
//===============================================================
    EditText etDate;
    EditText etHour;
    DatePickerDialog.OnDateSetListener setListener;

//===============================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_visitas);

//===============================================================
/*
        bt_fecha=(Button)findViewById(R.id.bt_fecha);
        edt_fecha=(EditText)findViewById(R.id.edt_fecha);
        bt_hora=(Button)findViewById(R.id.bt_hora);
        edt_hora=(EditText)findViewById(R.id.edt_hora);

        bt_hora.setOnClickListener(this);
        bt_fecha.setOnClickListener(this);
*/
//================================================================
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
                        String date=day+"/"+month+"/"+year;
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

//================================================================
    }
//================================================================
/*
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {

        if(v==bt_fecha){
            final Calendar c=Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    edt_fecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                }
            },dia,mes,ano);
            datePickerDialog.show();
        }


        if(v==bt_hora){
            final Calendar c =Calendar.getInstance();
            hora=c.get(Calendar.HOUR_OF_DAY);
            minutos=c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    edt_hora.setText(hourOfDay+":"+minute);
                }
            },hora,minutos,false);
            timePickerDialog.show();
        }
    }
*/
//================================================================
}