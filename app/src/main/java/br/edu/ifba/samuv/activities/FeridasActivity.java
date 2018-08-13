package br.edu.ifba.samuv.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import br.edu.ifba.samuv.R;
import br.edu.ifba.samuv.adapters.FeridasAdapter;
import br.edu.ifba.samuv.connection.RetrofitConfig;
import br.edu.ifba.samuv.models.Ferida;
import br.edu.ifba.samuv.models.Paciente;
import br.edu.ifba.samuv.util.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeridasActivity extends AppCompatActivity {

    private Paciente paciente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feridas);

        Intent it = getIntent();

        String jsonInString = it.getStringExtra("paciente");

        //JSON from String to Object
        try {
            paciente = (Paciente)Utils.JsonToObject(jsonInString, Paciente.class);
            ((TextView)findViewById(R.id.txtLogado)).setText("Paciente: " + paciente.getUsuario().getNomeUsuario());
            ((TextView)findViewById(R.id.txtTitulo)).setText("Feridas");
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        CriarEventos();
        configurarRecycler();
    }

    RecyclerView recyclerView;
    private FeridasAdapter adapterFerida;

    private void configurarRecycler() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewFeridas);
        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Call<List<Ferida>> call = new RetrofitConfig().samuvService().getFeridasPaciente(String.valueOf(paciente.getIdPaciente()));

        call.enqueue(new Callback<List<Ferida>>() {
            @Override
            public void onResponse(Call<List<Ferida>> call, Response<List<Ferida>> response) {
                // pegar a resposta
                if (response.isSuccessful()) {
                    List<Ferida> feridas = response.body();

                    // Adiciona o adapter que irá anexar os objetos à lista.
                    adapterFerida = new FeridasAdapter(feridas);
                    recyclerView.setAdapter(adapterFerida);

                    // Configurando um separador entre linhas, para uma melhor visualização.
                    recyclerView.addItemDecoration(new DividerItemDecoration(FeridasActivity.this, DividerItemDecoration.VERTICAL));

                }
                else {
                    Toast.makeText(getApplicationContext(), "Não foi possível carregar os feridas!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Ferida>> call, Throwable t) {
                // tratar algum erro
                Toast.makeText(getApplicationContext(), "Não foi possível carregar os feridas: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    //CRIA OS EVENTOS DOS COMPONENTES
    protected void CriarEventos() {
        //CRIANDO EVENTO NO CAMPO DE DATA PARA ABRIR A POPUP
        /*((RecyclerView)findViewById(R.id.recyclerViewFeridas)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(FeridasActivity.this, FeridasActivity.class);
                try {
                    main.putExtra("user", Utils.objectToJson(usuario, Usuario.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startActivity(main);
            }
        });
        //CRIANDO EVENTO NO CAMPO DE DATA PARA ABRIR A POPUP
        editTextDataPublicacao.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {

                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        datePickerDialogDataPublicacao.show();
                    }
                }
        );*/
    }
}
