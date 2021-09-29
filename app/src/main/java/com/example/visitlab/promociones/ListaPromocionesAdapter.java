package com.example.visitlab.promociones;

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

public class ListaPromocionesAdapter extends RecyclerView.Adapter<ListaPromocionesAdapter.PromocionesViewHolder> {

    private Context mCtx;
    private List<Promociones> listaPromociones;
    private List<Promociones> listaOriginal;
    private List<Promociones> originalItems;

    public ListaPromocionesAdapter(Context mCtx, List<Promociones> PromocionesList) {
        this.mCtx = mCtx;
        this.listaPromociones = PromocionesList;

        listaOriginal=new ArrayList<>();
        listaOriginal.addAll(PromocionesList);

    }

    @Override
    public PromocionesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout_promocion, null);

        return new PromocionesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PromocionesViewHolder holder, int position) {
        Promociones promociones = listaPromociones.get(position);
        // URL de prueba para cargar imagen
        //String URL2 = "https://www.eventosfilm.com/wp-content/uploads/2018/02/foto-visa-5x5-para-los-Estados-Unidos-300x300.gif";
        //

        Glide.with(mCtx)
                .load(promociones.getphoto())
                .centerCrop()
                //.placeholder(R.drawable.ic_person)
                .into(holder.imageView);

        holder.txtCodigo.setText(String.valueOf(promociones.getId_codigo()));
        holder.txtDescripcion.setText(String.valueOf(promociones.getDescripcion()));

    }

     public void filtrado(final String txtBuscar){
        int longitud = txtBuscar.length();
        if (longitud==0){
            listaPromociones.clear();
            listaPromociones.addAll(listaOriginal);
        }
        else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Promociones> collecion = listaPromociones.stream()
                        .filter(i -> i.getDescripcion().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaPromociones.clear();
                listaPromociones.addAll(collecion);
            }else{
                for(Promociones c: listaOriginal){
                    if(c.getDescripcion().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaPromociones.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() { return listaPromociones.size();}

    class PromocionesViewHolder extends RecyclerView.ViewHolder{
        TextView txtCodigo, txtDescripcion;
        ImageView imageView;

        public PromocionesViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCodigo=itemView.findViewById(R.id.cod_promocion);
            txtDescripcion=itemView.findViewById(R.id.descripcion);
            imageView=itemView.findViewById(R.id.pic_promocion);

        }
    }


}