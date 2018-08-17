package br.edu.ifba.samuv.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import br.edu.ifba.samuv.R;
import br.edu.ifba.samuv.adapters.AtendimentosAdapter;
import br.edu.ifba.samuv.connection.RetrofitConfig;
import br.edu.ifba.samuv.models.Atendimento;
import br.edu.ifba.samuv.models.Ferida;
import br.edu.ifba.samuv.models.Paciente;
import br.edu.ifba.samuv.models.Profissional;
import br.edu.ifba.samuv.models.Usuario;
import br.edu.ifba.samuv.util.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AtendimentosActivity extends AppCompatActivity {

    private Ferida ferida;
    private Profissional usuario;
    private FloatingActionButton fab_add_atendimento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atendimentos);

        Intent it = getIntent();

        String jsonFerida = it.getStringExtra("ferida");
        String jsonUsuario = it.getStringExtra("user");

        //JSON from String to Object
        try {
            ferida = (Ferida)Utils.JsonToObject(jsonFerida, Ferida.class);
            usuario = (Profissional)Utils.JsonToObject(jsonUsuario, Profissional.class);
            ((TextView)findViewById(R.id.txtLogado)).setText("Atendimento: " + ferida.getApelido());
            ((TextView)findViewById(R.id.txtTitulo)).setText("Atendimentos");
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        CriarEventos();
        configurarRecycler();
    }

    RecyclerView recyclerView;
    private AtendimentosAdapter adapterAtendimento;

    private void configurarRecycler() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewAtendimentos);
        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        try{

            Call<List<Atendimento>> call = new RetrofitConfig().samuvService().getAtendimentosPorFerida(ferida.getPk());

            call.enqueue(new Callback<List<Atendimento>>() {
                @Override
                public void onResponse(Call<List<Atendimento>> call, Response<List<Atendimento>> response) {
                    // pegar a resposta
                    if (response.isSuccessful()) {
                        List<Atendimento> atendimentos = response.body();

                        // Adiciona o adapter que irá anexar os objetos à lista.
                        adapterAtendimento = new AtendimentosAdapter(atendimentos, usuario);
                        recyclerView.setAdapter(adapterAtendimento);

                        // Configurando um separador entre linhas, para uma melhor visualização.
                        recyclerView.addItemDecoration(new DividerItemDecoration(AtendimentosActivity.this, DividerItemDecoration.VERTICAL));

                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Não foi possível carregar os atendimentos!", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Atendimento>> call, Throwable t) {
                    // tratar algum erro
                    Toast.makeText(getApplicationContext(), "Não foi possível carregar os atendimentos: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });}
        catch (Exception e){
            e.getMessage();
        }
    }

    //CRIA OS EVENTOS DOS COMPONENTES
    protected void CriarEventos() {
        //CRIANDO EVENTO DO BOTÃO DE ADD NOVO ATENDIMENTO
        fab_add_atendimento = findViewById(R.id.fab_add_atendimento);
        fab_add_atendimento.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AtendimentosActivity.this, MainActivity.class);
                try {
                    intent.putExtra("user", Utils.objectToJson(usuario, Profissional.class));
                    intent.putExtra("ferida", Utils.objectToJson(ferida, Ferida.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        //CRIANDO EVENTO NO CAMPO DE DATA PARA ABRIR A POPUP
        /*((RecyclerView)findViewById(R.id.recyclerViewAtendimentos)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(AtendimentosActivity.this, AtendimentosActivity.class);
                try {
                    main.putExtra("user", Utils.objectToJson(usuario, Usuario.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startActivity(main);
            }
        });
        */
    }
}
