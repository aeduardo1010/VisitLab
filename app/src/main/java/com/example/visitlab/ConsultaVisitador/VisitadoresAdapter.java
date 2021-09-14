package com.example.visitlab.ConsultaVisitador;

import static com.example.visitlab.R.*;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VisitadoresAdapter extends RecyclerView.Adapter<VisitadoresAdapter.MyViewHolder>
        implements View.OnClickListener{

    private List<Visitador> visitadorList;

    private View.OnClickListener listener;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre, genero, año;

        public MyViewHolder(View view) {
            super(view);
            nombre = (TextView) view.findViewById(id.nombre);
            genero = (TextView) view.findViewById(id.genero);
            año = (TextView) view.findViewById(id.año);
        }
    }


    public VisitadoresAdapter(List<Visitador> visitadoresList) {
        this.visitadorList = visitadoresList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layout.visitador_fila, parent, false);

        view.setOnClickListener(this);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Visitador visitador = visitadorList.get(position);
        holder.nombre.setText(visitador.getNombre());
        holder.genero.setText(visitador.getGenero());
        holder.año.setText(visitador.getAño());
    }

    @Override
    public int getItemCount() {
        return visitadorList.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }


    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

/*
    public void onClick(View v){
        showpopup(v);
    }

    public void showpopup(View view){
        PopupMenu popupMenu=new PopupMenu(view.getContext(),view);
        popupMenu.inflate(R.layout.custompopup);
        popupMenu.show();
    }
*/
}
