package com.example.projetozeradengue.datamodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetozeradengue.R;

import java.util.List;

public class AdapterDenounces extends RecyclerView.Adapter<AdapterDenounces.ViewHolder> {
    private List<String> dados;

    public AdapterDenounces(List<String> dados) {
        this.dados = dados;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_denounce, parent, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String dado = dados.get(position);
        holder.textView.setText(dado);
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
}