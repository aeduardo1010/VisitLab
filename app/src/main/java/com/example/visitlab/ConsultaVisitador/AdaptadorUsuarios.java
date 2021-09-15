package com.example.visitlab.ConsultaVisitador;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.visitlab.R;

import java.util.List;
import java.util.ArrayList;


public class AdaptadorUsuarios extends RecyclerView.Adapter<AdaptadorUsuarios.UsuarioViewHolder> {

    Context context;
    List<Usuario> listaUsuarios;

    public AdaptadorUsuarios(Context context, List<Usuario> listaUsuarios) {
        this.context = context;
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_layout_visitador, viewGroup, false);
        return new UsuarioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder usuarioViewHolder, int i) {

        usuarioViewHolder.txtCodigo.setText(String.valueOf(listaUsuarios.get(i).getId_codigo()));
        usuarioViewHolder.txtApellido.setText(listaUsuarios.get(i).getApellido());
        usuarioViewHolder.txtNombre.setText(listaUsuarios.get(i).getNombre());
        usuarioViewHolder.txtEmail.setText(listaUsuarios.get(i).getEmail());
        usuarioViewHolder.txtCitasHechas.setText(String.valueOf(listaUsuarios.get(i).getCitas_hechas()));
        usuarioViewHolder.txtCitasTotal.setText(String.valueOf(listaUsuarios.get(i).getCitas_totales()));


        /*
        usuarioViewHolder.tvIdUsuario.setText(listaUsuarios.get(i).getIdUsuario());
        usuarioViewHolder.tvNombre.setText(listaUsuarios.get(i).getNombre());
        usuarioViewHolder.tvTelefono.setText(listaUsuarios.get(i).getTelefono());
        usuarioViewHolder.tvEmail.setText(listaUsuarios.get(i).getEmail());
        */

    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {

        //TextView tvIdUsuario, tvNombre, tvTelefono, tvEmail;

        TextView txtCodigo, txtApellido, txtNombre, txtEmail, txtCitasHechas, txtCitasTotal;
        ImageView imageView;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
/*
            tvIdUsuario = itemView.findViewById(R.id.tvIdUsuario);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvEmail = itemView.findViewById(R.id.tvEmail);*/
            txtCodigo=itemView.findViewById(R.id.cod_visitador);
            txtApellido=itemView.findViewById(R.id.ape_visitador);
            txtNombre=itemView.findViewById(R.id.nom_visitador);
            txtEmail=itemView.findViewById(R.id.mail_visitador);
            txtCitasHechas=itemView.findViewById(R.id.num_citas_hechas);
            txtCitasTotal=itemView.findViewById(R.id.num_citas_total);
        }
    }

    public void filtrar(ArrayList<Usuario> filtroUsuarios) {
        this.listaUsuarios = filtroUsuarios;
        notifyDataSetChanged();
    }
}