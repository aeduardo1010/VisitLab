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

public class Adapter extends RecyclerView.Adapter<Adapter.PlayerViewHolder> {

    private Context mCtx;
    private List<Players> playerList;

    public Adapter(Context mCtx, List<Players> playerList) {
        this.mCtx = mCtx;
        this.playerList = playerList;
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout_visitador, null);

        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        Players player = playerList.get(position);
        // prueba de carga imagen
        //String URL2 = "https://www.eventosfilm.com/wp-content/uploads/2018/02/foto-visa-5x5-para-los-Estados-Unidos-300x300.gif";
        //
        /*Glide.with(mCtx)
                .load(player.getImage())
                //.load(URL2);
                .into(holder.imageView);*/

        holder.txtCodigo.setText(String.valueOf(player.getId_codigo()));
        //holder.txtDNI.setText(String.valueOf(player.getDni()));
        holder.txtApellido.setText(player.getApellido());
        holder.txtNombre.setText(player.getNombre());
        holder.txtEmail.setText(player.getEmail());
        holder.txtCitasHechas.setText(String.valueOf(player.getCitas_hechas()));
        holder.txtCitasTotal.setText(String.valueOf(player.getCitas_totales()));


    }

    @Override
    public int getItemCount() { return playerList.size();}

    class PlayerViewHolder extends RecyclerView.ViewHolder{
        TextView txtCodigo, txtDNI, txtApellido, txtNombre, txtEmail, txtCitasHechas, txtCitasTotal;
        ImageView imageView;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCodigo=itemView.findViewById(R.id.cod_visitador);
            txtDNI=itemView.findViewById(R.id.txtDNI);
            txtApellido=itemView.findViewById(R.id.ape_visitador);
            txtNombre=itemView.findViewById(R.id.nom_visitador);
            txtEmail=itemView.findViewById(R.id.mail_visitador);
            txtCitasHechas=itemView.findViewById(R.id.num_citas_hechas);
            txtCitasTotal=itemView.findViewById(R.id.num_citas_total);
            imageView=itemView.findViewById(R.id.pic_visitador);

        }
    }

}