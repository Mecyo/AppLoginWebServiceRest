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
import br.edu.ifba.samuv.holders.FeridaHolder;
import br.edu.ifba.samuv.models.Ferida;
import br.edu.ifba.samuv.util.Utils;

/**
 * Created by Emerson Santos on 30/07/18.
 */

public class FeridasAdapter extends RecyclerView.Adapter<FeridaHolder> {

    private final List<Ferida> feridas;

    public FeridasAdapter(List<Ferida> feridas) {
        this.feridas = feridas;
    }

    public void atualizarFerida(Ferida ferida){
        feridas.set(feridas.indexOf(ferida), ferida);
        notifyItemChanged(feridas.indexOf(ferida));
    }

    public void adicionarFerida(Ferida ferida){
        feridas.add(ferida);
        notifyItemInserted(getItemCount());
    }

    public void removerFerida(Ferida ferida){
        int position = feridas.indexOf(ferida);
        feridas.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public FeridaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FeridaHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_ferida, parent, false));
    }

    @Override
    public void onBindViewHolder(FeridaHolder holder, int position) {
        holder.titulo.setText(feridas.get(position).getApelido());
        final Ferida ferida = feridas.get(position);
        final Activity activity = getActivity(holder.itemView);
        holder.btnExcluir.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Confirmação")
                        .setMessage("Tem certeza que deseja excluir este ferida?")
                        .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //FeridaDAO dao = new FeridaDAO(view.getContext());
                                //boolean sucesso = dao.excluir(ferida.getId());
                                boolean sucesso = true;
                                if(sucesso) {
                                    removerFerida(ferida);
                                    Snackbar.make(view, "Excluiu!", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }else{
                                    Snackbar.make(view, "Erro ao excluir o ferida!", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .create()
                        .show();
            }
        });

        ((RecyclerView)activity.findViewById(R.id.recyclerViewFeridas)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*ALTERAR*/Intent intent = new Intent(activity, FeridasActivity.class);//alterar!!!!!!
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("ferida", ferida.toString());
                activity.finish();
                activity.startActivity(intent);
            }
        });

        holder.btnEditar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity(v);
                Intent intent = new Intent(activity, FeridasActivity.class);//alterar!!!!!!
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                try {
                    intent.putExtra("ferida", Utils.objectToJson(ferida, Ferida.class));
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
        return feridas != null ? feridas.size() : 0;
    }
}