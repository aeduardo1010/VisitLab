package com.example.visitlab.medicamentos;

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

public class ListaMedicamentoAdapter extends RecyclerView.Adapter<ListaMedicamentoAdapter.MedicamentoViewHolder> {

    private Context medicamentoCtx;
    private List<Medicamento> listaMedicamento;
    private List<Medicamento> listaOriginalMedicamento;

    public ListaMedicamentoAdapter(Context medicamentoCtx, List<Medicamento> medicamentoList) {
        this.medicamentoCtx = medicamentoCtx;
        this.listaMedicamento = medicamentoList;

        listaOriginalMedicamento=new ArrayList<>();
        listaOriginalMedicamento.addAll(medicamentoList);
    }

    @Override
    public MedicamentoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(medicamentoCtx);
        View view = inflater.inflate(R.layout.activity_buscar_medicamento, null);

        return new MedicamentoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicamentoViewHolder holder, int position) {
        Medicamento medicamento = listaMedicamento.get(position);

        Glide.with(medicamentoCtx)
                .load(medicamento.getPhoto())
                .centerCrop()
                .into(holder.imageView);


        holder.tvNombre.setText(String.valueOf(medicamento.getNombre()));
        holder.tvLaboratorio.setText(medicamento.getLaboratorio());
        holder.tvExpecialidad.setText(medicamento.getEspecialidad());
    }


    public void filtrado(final String txtBuscar){
        int longitud = txtBuscar.length();
        if (longitud==0){
            listaMedicamento.clear();
            listaMedicamento.addAll(listaOriginalMedicamento);
        }
        else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Medicamento> collecion = listaMedicamento.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaMedicamento.clear();
                listaMedicamento.addAll(collecion);
            }else{
                for(Medicamento c: listaOriginalMedicamento){
                    if(c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaMedicamento.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();

    }


    @Override
    public int getItemCount() { return listaMedicamento.size();}

    class MedicamentoViewHolder extends RecyclerView.ViewHolder{
        TextView tvNombre, tvLaboratorio, tvExpecialidad;
        ImageView imageView;

        public MedicamentoViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombre=itemView.findViewById(R.id.tv_nom_medic);
            tvLaboratorio=itemView.findViewById(R.id.tv_laboratorio);
            tvExpecialidad=itemView.findViewById(R.id.tv_especialidad);
            imageView=itemView.findViewById(R.id.pic_medicamento);
        }
    }
}