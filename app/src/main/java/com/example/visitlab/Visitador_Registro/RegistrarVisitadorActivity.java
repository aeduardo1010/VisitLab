package com.example.visitlab.Visitador_Registro;

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

import java.util.HashMap;
import java.util.Map;
//================================================================================================== Cambio foto INI
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
//================================================================================================== Cambio foto FIN


public class RegistrarVisitadorActivity extends AppCompatActivity {
    EditText edtCodigo, edtDNI, edtApellido, edtNombre, edtPassword, edtEmail;
    Button btnAgregar;

//================================================================================================== Cambio foto INI
private ImageView imageView;
    private String encodedImage1 = "";
    int PERMISSION_ID = 44;

    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
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
                        imageView.setImageBitmap(bitmap);
                        encodedImage1 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                        Log.i("======>",encodedImage1);
                    }
                }
            });
//================================================================================================== Cambio foto FIN

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_visitador);

        //edtCodigo=(EditText)findViewById(R.id.txtCodigo);
        edtDNI=(EditText)findViewById(R.id.txtDNI);
        edtApellido=(EditText)findViewById(R.id.txtApellido);
        edtNombre=(EditText)findViewById(R.id.txtNombre);
        edtPassword=(EditText)findViewById(R.id.txtPassword);
        edtEmail=(EditText)findViewById(R.id.txtEmail);
        btnAgregar=(Button)findViewById(R.id.btnGuardarVisitador);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejecutarServicio("http://192.168.1.11:8080/visitlabperu/insertar_visitador.php");
            }
        });

//================================================================================================== Cambio foto INI
        Button button = findViewById(R.id.tomarSelfie);
        imageView = findViewById(R.id.selfiePrevia);

        if (!checkPermissions()) {
            requestPermissions();
        }

        button.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            someActivityResultLauncher.launch(intent);
        });
//================================================================================================== Cambio foto FIN


    }

//================================================================================================== Cambio foto INI
    private boolean checkPermissions() {
    return ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
}

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.CAMERA}, PERMISSION_ID);
    }

//================================================================================================== Cambio foto FIN

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
                parametros.put("vm_id_visitador"," ");
                parametros.put("vm_dni",edtDNI.getText().toString());
                parametros.put("vm_apellido",edtApellido.getText().toString());
                parametros.put("vm_nombre",edtNombre.getText().toString());
                parametros.put("vm_password",edtPassword.getText().toString());
                parametros.put("vm_email",edtEmail.getText().toString());
                parametros.put("vm_photo",encodedImage1);
                parametros.put("vm_citas_hechas","0");
                parametros.put("vm_citas_totales","0");
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}