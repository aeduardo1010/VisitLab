package com.example.visitlab.ConsultaVisitador;

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

import java.util.List;

public class VisitadoresNewAdapter extends RecyclerView.Adapter<VisitadoresNewAdapter.VisitadorViewHolder> {

    private Context mCtx;
    private List<VisitadoresNew> VisitadoresNewList;

    public VisitadoresNewAdapter(Context mCtx, List<VisitadoresNew> VisitadoresNewList){
        this.mCtx = mCtx;
        this.VisitadoresNewList=VisitadoresNewList;
    }

    @Override
    public VisitadorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout_visitador, null);

        return new VisitadorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitadorViewHolder holder, int position) {
        VisitadoresNew visitador=VisitadoresNewList.get(position);

        Glide.with(mCtx)
                .load(visitador.getPhoto())
                .into(holder.imageView);

        holder.txtCodigo.setText(String.valueOf(visitador.getId_codigo()));
        holder.txtApellido.setText(visitador.getApellido());
        holder.txtNombre.setText(visitador.getNombre());
        holder.txtEmail.setText(visitador.getEmail());
        holder.txtCitasHechas.setText(String.valueOf(visitador.getCitas_hechas()));
        holder.txtCitasTotal.setText(String.valueOf(visitador.getCitas_totales()));
    }

    @Override
    public int getItemCount() { return VisitadoresNewList.size();}

    class VisitadorViewHolder extends RecyclerView.ViewHolder{
        TextView txtCodigo, txtApellido, txtNombre, txtEmail, txtCitasHechas, txtCitasTotal;
        ImageView imageView;

        public VisitadorViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCodigo=itemView.findViewById(R.id.cod_visitador);
            txtApellido=itemView.findViewById(R.id.ape_visitador);
            txtNombre=itemView.findViewById(R.id.nom_visitador);
            txtEmail=itemView.findViewById(R.id.mail_visitador);
            txtCitasHechas=itemView.findViewById(R.id.num_citas_hechas);
            txtCitasTotal=itemView.findViewById(R.id.num_citas_total);

        }
    }


}
