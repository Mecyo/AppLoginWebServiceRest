package br.edu.ifba.samuv.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import br.edu.ifba.samuv.R;

/**
 * Created by Emerson Santos on 30/07/18.
 */

public class PacienteHolder extends RecyclerView.ViewHolder {

    public TextView nomePaciente;
    public ImageButton btnEditar;
    public ImageButton btnExcluir;

    public PacienteHolder(View itemView) {
        super(itemView);
        nomePaciente = (TextView) itemView.findViewById(R.id.nomePaciente);
        btnEditar = (ImageButton) itemView.findViewById(R.id.btnEdit);
        btnExcluir = (ImageButton) itemView.findViewById(R.id.btnDelete);
    }
}