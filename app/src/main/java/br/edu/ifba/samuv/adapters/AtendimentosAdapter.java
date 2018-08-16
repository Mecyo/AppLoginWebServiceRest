package br.edu.ifba.samuv.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.IOException;
import java.util.List;

import br.edu.ifba.samuv.R;
import br.edu.ifba.samuv.activities.FeridasActivity;
import br.edu.ifba.samuv.holders.AtendimentoHolder;
import br.edu.ifba.samuv.models.Atendimento;
import br.edu.ifba.samuv.models.Usuario;
import br.edu.ifba.samuv.util.Utils;

/**
 * Created by Emerson Santos on 30/07/18.
 */

public class AtendimentosAdapter extends RecyclerView.Adapter<AtendimentoHolder> {

    private final List<Atendimento> atendimentos;
    private Usuario usuario;

    public AtendimentosAdapter(List<Atendimento> atendimentos, Usuario usuario) {
        this.atendimentos = atendimentos;
        this.usuario = usuario;
    }

    public void atualizarAtendimento(Atendimento atendimento){
        atendimentos.set(atendimentos.indexOf(atendimento), atendimento);
        notifyItemChanged(atendimentos.indexOf(atendimento));
    }

    public void adicionarAtendimento(Atendimento atendimento){
        atendimentos.add(atendimento);
        notifyItemInserted(getItemCount());
    }

    public void removerAtendimento(Atendimento atendimento){
        int position = atendimentos.indexOf(atendimento);
        atendimentos.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public AtendimentoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AtendimentoHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_atendimento, parent, false));
    }

    @Override
    public void onBindViewHolder(AtendimentoHolder holder, int position) {
        holder.dataHora.setText(atendimentos.get(position).getDataHora().toString());
        final Atendimento atendimento = atendimentos.get(position);
        final Activity activity = getActivity(holder.itemView);
        ((RecyclerView)activity.findViewById(R.id.recyclerViewAtendimentos)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, FeridasActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("atendimento", atendimento.toString());
                activity.finish();
                activity.startActivity(intent);
            }
        });

        holder.btnExcluir.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Confirmação")
                        .setMessage("Tem certeza que deseja excluir este atendimento?")
                        .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //boolean sucesso = dao.excluir(atendimento.getId());
                                boolean sucesso = true;
                                if(sucesso) {
                                    removerAtendimento(atendimento);
                                    Snackbar.make(view, "Excluiu!", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }else{
                                    Snackbar.make(view, "Erro ao excluir o atendimento!", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .create()
                        .show();
            }
        });

        holder.btnEditar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity(v);
                Intent intent = new Intent(activity, FeridasActivity.class);//ALTERAR PARA DETALHE ATENDIMENTO
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                try {
                    intent.putExtra("atendimento", Utils.objectToJson(atendimento, Atendimento.class));
                    intent.putExtra("user", Utils.objectToJson(usuario, Usuario.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                activity.finish();
                activity.startActivity(intent);
            }
        });
    }

    private Activity getActivity(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return atendimentos != null ? atendimentos.size() : 0;
    }
}