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
import br.edu.ifba.samuv.activities.PacientesActivity;
import br.edu.ifba.samuv.holders.PacienteHolder;
import br.edu.ifba.samuv.models.Paciente;
import br.edu.ifba.samuv.models.Usuario;
import br.edu.ifba.samuv.util.Utils;

/**
 * Created by Emerson Santos on 30/07/18.
 */

public class PacienteAdapter extends RecyclerView.Adapter<PacienteHolder> {

    private final List<Paciente> pacientes;

    public PacienteAdapter(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public void atualizarPaciente(Paciente paciente){
        pacientes.set(pacientes.indexOf(paciente), paciente);
        notifyItemChanged(pacientes.indexOf(paciente));
    }

    public void adicionarPaciente(Paciente paciente){
        pacientes.add(paciente);
        notifyItemInserted(getItemCount());
    }

    public void removerPaciente(Paciente paciente){
        int position = pacientes.indexOf(paciente);
        pacientes.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public PacienteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PacienteHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_paciente, parent, false));
    }

    @Override
    public void onBindViewHolder(PacienteHolder holder, int position) {
        holder.nomePaciente.setText(pacientes.get(position).getNomeCompleto());
        final Paciente paciente = pacientes.get(position);
        final Activity activity = getActivity(holder.itemView);
        ((RecyclerView)activity.findViewById(R.id.recyclerViewPacientes)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, FeridasActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("paciente", paciente.toString());
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
                        .setMessage("Tem certeza que deseja excluir este paciente?")
                        .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //boolean sucesso = dao.excluir(paciente.getId());
                                boolean sucesso = true;
                                if(sucesso) {
                                    removerPaciente(paciente);
                                    Snackbar.make(view, "Excluiu!", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }else{
                                    Snackbar.make(view, "Erro ao excluir o paciente!", Snackbar.LENGTH_LONG)
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
                Intent intent = new Intent(activity, FeridasActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                try {
                    intent.putExtra("paciente", Utils.objectToJson(paciente, Paciente.class));
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
        return pacientes != null ? pacientes.size() : 0;
    }
}