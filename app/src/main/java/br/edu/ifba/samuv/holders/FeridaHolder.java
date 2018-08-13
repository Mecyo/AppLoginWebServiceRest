package br.edu.ifba.samuv.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import br.edu.ifba.samuv.R;


/**
 * Created by Emerson Santos on 30/07/18.
 */

public class FeridaHolder extends RecyclerView.ViewHolder {

    public TextView titulo;
    public ImageButton btnEditar;
    public ImageButton btnExcluir;

    public FeridaHolder(View itemView) {
        super(itemView);
        titulo = (TextView) itemView.findViewById(R.id.titulo);
        btnEditar = (ImageButton) itemView.findViewById(R.id.btnEdit);
        btnExcluir = (ImageButton) itemView.findViewById(R.id.btnDelete);
    }
}