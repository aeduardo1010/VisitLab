package com.example.visitlab.ConsultaVisitador;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;

import com.android.volley.Request;
import com.example.visitlab.R;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//=================================================================================================
public class PerfilActivity extends AppCompatActivity {
/*
        EditText etBuscador;
        RecyclerView rvLista;
        AdaptadorUsuarios adaptador;
        List<Usuario> listaUsuarios;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_perfil);

            etBuscador = findViewById(R.id.etBuscador);
            etBuscador.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }

                @Override
                public void afterTextChanged(Editable s) {
                    filtrar(s.toString());
                }
            });

            rvLista = findViewById(R.id.rvLista);
            rvLista.setLayoutManager(new GridLayoutManager(this, 1));

            listaUsuarios = new ArrayList<>();

            obtenerUsuarios();

            adaptador = new AdaptadorUsuarios(PerfilActivity.this, listaUsuarios);
            rvLista.setAdapter(adaptador);
        }

        public void obtenerUsuarios() {
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.URL_Usuarios),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("Usuarios");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    listaUsuarios.add(
                                            new Usuario(
                                                    jsonObject1.getInt("vm_id_visitador"),
                                                    jsonObject1.getInt("vm_dni"),
                                                    jsonObject1.getString("vm_apellido"),
                                                    jsonObject1.getString("vm_nombre"),
                                                    jsonObject1.getString("vm_email"),
                                                    jsonObject1.getString("vm_photo"),
                                                    jsonObject1.getInt("vm_citas_hechas"),
                                                    jsonObject1.getInt("vm_citas_total")
                                            )
                                    );
                                }

                                adaptador = new AdaptadorUsuarios(PerfilActivity.this, listaUsuarios);
                                rvLista.setAdapter(adaptador);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            }
            );

            requestQueue.add(stringRequest);
        }

        public void filtrar(String texto) {
            ArrayList<Usuario> filtrarLista = new ArrayList<>();

            for(Usuario usuario : listaUsuarios) {
                if(usuario.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                    filtrarLista.add(usuario);
                }
            }

            adaptador.filtrar(filtrarLista);
        }
    }
*/
//==================================================================================================
    private static final String URL_players = "http://192.168.1.11:8080/visitlabperu/consultar_visitador.php";

    private List<Players> playerList;

    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);


        recyclerView = (RecyclerView)findViewById(R.id.rvLista);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        playerList = new ArrayList<>();

        loadplayers();
    }

    private void loadplayers(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_players,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            //********
                            /*JSONObject player = array.getJSONObject(0);
                            Adapter adapter = new Adapter (PerfilActivity.this, playerList);
                            recyclerView.setAdapter(adapter);*/
                            //********
                            for (int i = 0; i < array.length(); i++) {

                                JSONObject player = array.getJSONObject(i);

                                playerList.add(new Players(
                                        player.getInt("vm_id_visitador"),
                                        player.getInt("vm_dni"),
                                        player.getString("vm_apellido"),
                                        player.getString("vm_nombre"),
                                        player.getString("vm_email"),
                                        player.getString("vm_photo"),
                                        player.getInt("vm_citas_hechas"),
                                        player.getInt("vm_citas_total")

                                ));
                            }
                            Adapter adapter = new Adapter (PerfilActivity.this, playerList);
                            recyclerView.setAdapter(adapter);
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
}

//==================================================================================================
/*
    private List<Visitador> visitadorList = new ArrayList<>();
    private RecyclerView recyclerView;
    private VisitadoresAdapter mAdapter;
    private SearchView svSearch;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        myDialog = new Dialog(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_visitador);

        mAdapter = new VisitadoresAdapter(visitadorList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Prueba",Toast.LENGTH_SHORT).show();
                PopupMenu popupMenu=new PopupMenu(view.getContext(),view);
                //popupMenu.inflate(R.layout.custompopup);

            }


        });

        recyclerView.setAdapter(mAdapter);

        prepareVisitadorData();
        //recyclerView.setOnClickListener();
    }
*/
/*
    public void ShowPopUp(View v){
        TextView = txtclose;

    }
*/
/*
    private void prepareVisitadorData() {
        Visitador visitador = new Visitador("Maria Perez", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Espinoza", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Salas", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Ojeda", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Cordova", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Silva", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Montalvo", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Leon", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Benites", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Bermudez", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Pasos", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Carpio", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Cuadros", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Flores", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Fuentes", " ", " ");
        visitadorList.add(visitador);

        visitador = new Visitador("Maria Herrera", " ", " ");
        visitadorList.add(visitador);

        mAdapter.notifyDataSetChanged();
    }

}
*/
/*
public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ListView lstAsesores = (ListView)findViewById(R.id.lstAsesores);

        int i = 0;
        String[] matriz = new String[15];
        matriz[i++] = "Maria Perez";
        matriz[i++] = "Maria Gomez";
        matriz[i++] = "Maria Paz";
        matriz[i++] = "Maria Guerra";
        matriz[i++] = "Maria Sofia";
        matriz[i++] = "Maria Cristina";
        matriz[i++] = "Maria Vasquez";
        matriz[i++] = "Maria Rosas";
        matriz[i++] = "Maria Ospina";
        matriz[i++] = "Maria Ruiz";
        matriz[i++] = "Maria Lopez";
        matriz[i++] = "Maria Estela";
        matriz[i++] = "Maria Alcala";
        matriz[i++] = "Maria Benites";
        matriz[i++] = "Maria Bueno";


        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(PerfilActivity.this,
                android.R.layout.simple_list_item_1,
                matriz);
        lstAsesores.setAdapter(adaptador);

    }
}
*/