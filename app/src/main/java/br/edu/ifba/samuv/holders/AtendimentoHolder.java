package br.edu.ifba.samuv.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import br.edu.ifba.samuv.R;

/**
 * Created by Emerson Santos on 30/07/18.
 */

public class AtendimentoHolder extends RecyclerView.ViewHolder {

    public TextView dataHora;
    public ImageButton btnEditar;
    public ImageButton btnExcluir;

    public AtendimentoHolder(View itemView) {
        super(itemView);
        dataHora = (TextView) itemView.findViewById(R.id.dataHora);
        btnEditar = (ImageButton) itemView.findViewById(R.id.btnEditAtendimento);
        btnExcluir = (ImageButton) itemView.findViewById(R.id.btnDeleteAtendimento);
    }
}