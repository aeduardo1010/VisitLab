package com.example.visitlab.Visitador_Consulta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.visitlab.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaVisitadoresAdapter extends RecyclerView.Adapter<ListaVisitadoresAdapter.VisitadorViewHolder> {

    private Context mCtx;
    private List<Visitadores> listaVisitadores;
    private List<Visitadores> listaOriginal;
    private List<Visitadores> originalItems;

    public ListaVisitadoresAdapter(Context mCtx, List<Visitadores> visitadoresList) {
        this.mCtx = mCtx;
        this.listaVisitadores = visitadoresList;

        listaOriginal=new ArrayList<>();
        listaOriginal.addAll(visitadoresList);

    }

    @Override
    public VisitadorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout_visitador, null);

        return new VisitadorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitadorViewHolder holder, int position) {
        Visitadores visitador = listaVisitadores.get(position);
        // URL de prueba para cargar imagen
        //String URL2 = "https://www.eventosfilm.com/wp-content/uploads/2018/02/foto-visa-5x5-para-los-Estados-Unidos-300x300.gif";
        //

        Glide.with(mCtx)
                .load(visitador.getphoto())
                .centerCrop()
                .placeholder(R.drawable.ic_person)
                .into(holder.imageView);

        holder.txtCodigo.setText(String.valueOf(visitador.getId_codigo()));
        holder.txtDNI.setText(String.valueOf(visitador.getDni()));
        holder.txtApellido.setText(visitador.getApellido());
        holder.txtNombre.setText(visitador.getNombre());
        holder.txtEmail.setText(visitador.getEmail());
        holder.txtCitasHechas.setText(String.valueOf(visitador.getCitas_hechas()));
        holder.txtCitasTotal.setText(String.valueOf(visitador.getCitas_totales()));


    }

    public void filtrado(final String txtBuscar){
        int longitud = txtBuscar.length();
        if (longitud==0){
            listaVisitadores.clear();
            listaVisitadores.addAll(listaOriginal);
        }
        else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Visitadores> collecion = listaVisitadores.stream()
                        .filter(i -> i.getApellido().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaVisitadores.clear();
                listaVisitadores.addAll(collecion);
            }else{
                for(Visitadores c: listaOriginal){
                    if(c.getApellido().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaVisitadores.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() { return listaVisitadores.size();}

    class VisitadorViewHolder extends RecyclerView.ViewHolder{
        TextView txtCodigo, txtDNI, txtApellido, txtNombre, txtEmail, txtCitasHechas, txtCitasTotal;
        ImageView imageView;

        public VisitadorViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCodigo=itemView.findViewById(R.id.cod_visitador);
            txtDNI=itemView.findViewById(R.id.num_dni);
                txtApellido=itemView.findViewById(R.id.ape_visitador);
            txtNombre=itemView.findViewById(R.id.nom_visitador);
            txtEmail=itemView.findViewById(R.id.mail_visitador);
            txtCitasHechas=itemView.findViewById(R.id.num_citas_hechas);
            txtCitasTotal=itemView.findViewById(R.id.num_citas_total);
            imageView=itemView.findViewById(R.id.pic_visitador);
        }
    }


}