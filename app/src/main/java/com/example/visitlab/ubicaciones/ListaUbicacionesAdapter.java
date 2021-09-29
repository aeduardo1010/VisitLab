package com.example.visitlab.ubicaciones;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.visitlab.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaUbicacionesAdapter extends RecyclerView.Adapter<ListaUbicacionesAdapter.UbicacionesViewHolder> {

    private Context mCtx;
    private List<Ubicaciones> listaUbicaciones;
    private List<Ubicaciones> listaOriginal;
    private List<Ubicaciones> originalItems;

    public ListaUbicacionesAdapter(Context mCtx, List<Ubicaciones> UbicacionesList) {
        this.mCtx = mCtx;
        this.listaUbicaciones = UbicacionesList;

        listaOriginal=new ArrayList<>();
        listaOriginal.addAll(UbicacionesList);

    }

    @Override
    public UbicacionesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout_ubicacion, null);

        return new UbicacionesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UbicacionesViewHolder holder, int position) {
        Ubicaciones ubicacion = listaUbicaciones.get(position);
        // URL de prueba para cargar imagen
        //String URL2 = "https://www.eventosfilm.com/wp-content/uploads/2018/02/foto-visa-5x5-para-los-Estados-Unidos-300x300.gif";
        //

        //Glide.with(mCtx)
        //        .load(ubicacion.getphoto())
        //       .centerCrop()
        //.placeholder(R.drawable.ic_person)
        //        .into(holder.imageView);

        holder.txtCodigo.setText(String.valueOf(ubicacion.getId_codigo()));
        holder.txtDNI.setText(String.valueOf(ubicacion.getDni()));
        holder.txtX.setText(ubicacion.getUbi_X());
        holder.txtY.setText(ubicacion.getUbi_Y());
        holder.txtFecha.setText(ubicacion.getFecha());
        holder.txtHora.setText(String.valueOf(ubicacion.getHora()));

    }

    public void filtrado(final String txtBuscar){
        int longitud = txtBuscar.length();
        if (longitud==0){
            listaUbicaciones.clear();
            listaUbicaciones.addAll(listaOriginal);
        }
        else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Ubicaciones> collecion = listaUbicaciones.stream()
                        .filter(i -> i.getFecha().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaUbicaciones.clear();
                listaUbicaciones.addAll(collecion);
            }else{
                for(Ubicaciones c: listaOriginal){
                    if(c.getFecha().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaUbicaciones.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() { return listaUbicaciones.size();}

    class UbicacionesViewHolder extends RecyclerView.ViewHolder{
        TextView txtCodigo, txtDNI, txtX, txtY, txtFecha, txtHora;


        public UbicacionesViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCodigo=itemView.findViewById(R.id.cod_visitador);
            txtDNI=itemView.findViewById(R.id.num_dni);

            txtX=itemView.findViewById(R.id.ubi_x);
            txtY=itemView.findViewById(R.id.ubi_y);
            txtFecha=itemView.findViewById(R.id.fecha);
            txtHora=itemView.findViewById(R.id.hora);
            //imageView=itemView.findViewById(R.id.txtDNI);


        }
    }


}