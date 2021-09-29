package com.example.visitlab.medicamentos;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.visitlab.R;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class AgregarMedicamentoActivity extends AppCompatActivity {

    Spinner comboLabs, comboEspec;
    EditText textoMedicamento;
    Button btnGuardarMedicamento;

    private ImageView imageViewMedicamento;
    private String encodedImageMedicamento = "";
    int PERMISSION_ID = 44;

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Bitmap bmp = (Bitmap) result.getData().getExtras().get("data");
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                        imageViewMedicamento.setImageBitmap(bitmap);
                        encodedImageMedicamento = Base64.encodeToString(byteArray, Base64.DEFAULT);
                        Log.i("======>",encodedImageMedicamento);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_medicamento);

        comboLabs=(Spinner) findViewById(R.id.idSpinnerLabs);
        comboEspec=(Spinner) findViewById(R.id.idSpinnerEspec);

        ArrayAdapter<CharSequence> adapterLabs=ArrayAdapter.createFromResource(this,
                R.array.arrayLabs, android.R.layout.simple_spinner_item);
        comboLabs.setAdapter(adapterLabs);

        ArrayAdapter<CharSequence> adapterEspec=ArrayAdapter.createFromResource(this,
                R.array.arrayEspec, android.R.layout.simple_spinner_item);
        comboEspec.setAdapter(adapterEspec);

        textoMedicamento=(EditText) findViewById(R.id.txtMedicamento);
        btnGuardarMedicamento=(Button) findViewById(R.id.btnGuardarMedicamento);

        Button button = findViewById(R.id.tomarSelfie);//CAMBIAR VARIABLEEEEEEEEEEEEEE
        imageViewMedicamento = findViewById(R.id.selfiePrevia);//CAMBIAR VARIABLEEEEEEEEEEEEEE

        if (!checkPermissions()) {
            requestPermissions();
        }

        button.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            someActivityResultLauncher.launch(intent);
        });

        btnGuardarMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejecutarServicioMedicamento("http://192.168.0.104/visitlabperu/insertar_medicamento.php");
            }
        });
    }

    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.CAMERA}, PERMISSION_ID);
    }

    private void ejecutarServicioMedicamento(String URL){
        StringRequest stringRequestMed=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),"Operación exitosa", Toast.LENGTH_SHORT).show();
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
                param.put("ms_id_medicamento"," ");
                param.put("ms_nombre",textoMedicamento.getText().toString());
                param.put("ms_laboratorio",comboLabs.getSelectedItem().toString());
                param.put("ms_especialidad",comboEspec.getSelectedItem().toString());
                param.put("ms_photo",encodedImageMedicamento);
                return param;
            }
        };
        RequestQueue requestQueueMed= Volley.newRequestQueue(this);
        requestQueueMed.add(stringRequestMed);
    }
}