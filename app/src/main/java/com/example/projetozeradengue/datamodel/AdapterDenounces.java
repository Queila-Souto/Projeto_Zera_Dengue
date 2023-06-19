package com.example.projetozeradengue.datamodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projetozeradengue.R;
import com.example.projetozeradengue.model.Denounces;

import java.util.List;

public class AdapterDenounces extends RecyclerView.Adapter<AdapterDenounces.ViewHolder> {
    private List<Denounces> denouncesdata;

    public AdapterDenounces(List<Denounces> dados) {
        this.denouncesdata = dados;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemtitle;
        public TextView ceplabel;
        public TextView adresslabel;
        public TextView complementlabel;
        public TextView idlabel;


        public ViewHolder(View itemView) {
            super(itemView);
            itemtitle = itemView.findViewById(R.id.itemtitle);
            ceplabel = itemView.findViewById(R.id.labelcep);
            adresslabel = itemView.findViewById(R.id.labelstreet);
            complementlabel = itemView.findViewById(R.id.labelcomplement);
            idlabel = itemView.findViewById(R.id.labelid);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.denounce_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Denounces dado = denouncesdata.get(position);
        holder.itemtitle.setText(dado.getA_district());
        holder.ceplabel.setText(dado.getCep());
        holder.adresslabel.setText(dado.getA_Street()+" número "+ dado.getA_number());
        holder.complementlabel.setText(dado.getA_complement());
        holder.idlabel.setText(dado.getId());
    }

    @Override
    public int getItemCount() {
        return denouncesdata.size();
    }
}