package br.edu.ifba.samuv.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import br.edu.ifba.samuv.R;
import br.edu.ifba.samuv.adapters.PacienteAdapter;
import br.edu.ifba.samuv.connection.RetrofitConfig;
import br.edu.ifba.samuv.models.Paciente;
import br.edu.ifba.samuv.models.Profissional;
import br.edu.ifba.samuv.models.Usuario;
import br.edu.ifba.samuv.util.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PacientesActivity extends AppCompatActivity {
    private Paciente pacienteEditado = null;
    private Profissional usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes);

        Intent it = getIntent();

        String jsonInString = it.getStringExtra("user");

        //JSON from String to Object
        try {
            usuario = (Profissional)Utils.JsonToObject(jsonInString, Profissional.class);
            ((TextView)findViewById(R.id.txtLogado)).setText("Bem vindo, " + usuario.getNomeUsuario());
            ((TextView)findViewById(R.id.txtTitulo)).setText("Pacientes");
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        CriarEventos();
        configurarRecycler();
    }

    RecyclerView recyclerView;
    private PacienteAdapter adapterPaciente;

    private void configurarRecycler() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPacientes);
        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Call<List<Paciente>> call = new RetrofitConfig().samuvService().getPacientesList();

        call.enqueue(new Callback<List<Paciente>>() {
            @Override
            public void onResponse(Call<List<Paciente>> call, Response<List<Paciente>> response) {
                // pegar a resposta
                if (response.isSuccessful()) {
                    List<Paciente> pacientes = response.body();

                    // Adiciona o adapter que irá anexar os objetos à lista.
                    adapterPaciente = new PacienteAdapter(pacientes, usuario);
                    recyclerView.setAdapter(adapterPaciente);

                    // Configurando um separador entre linhas, para uma melhor visualização.
                    recyclerView.addItemDecoration(new DividerItemDecoration(PacientesActivity.this, DividerItemDecoration.VERTICAL));
                }
                else {
                    Toast.makeText(getApplicationContext(), "Não foi possível carregar os pacientes!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Paciente>> call, Throwable t) {
                // tratar algum erro
                Toast.makeText(getApplicationContext(), "Não foi possível carregar os pacientes: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    //CRIA OS EVENTOS DOS COMPONENTES
    protected void CriarEventos() {
        //CRIANDO EVENTO NO CAMPO DE DATA PARA ABRIR A POPUP
        ((RecyclerView)findViewById(R.id.recyclerViewPacientes)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(PacientesActivity.this, FeridasActivity.class);
                try {
                    main.putExtra("paciente", Utils.objectToJson(pacienteEditado, Usuario.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startActivity(main);
            }
        });
        //CRIANDO EVENTO NO CAMPO DE DATA PARA ABRIR A POPUP
        /*editTextDataPublicacao.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {

                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        datePickerDialogDataPublicacao.show();
                    }
                }
        );*/
    }
}
