package br.edu.ifba.samuv.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
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
    private DatePickerDialog datePickerDialogDataPublicacao;
    private EditText txtDataNascimento;

    static final int DATE_DIALOG_ID = 0;

    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes);


        Intent it = getIntent();

        if(it.hasExtra("paciente")){
            txtDataNascimento = (EditText)this.findViewById(R.id.txtDataNascimento);
            Spinner spnGenero = (Spinner)findViewById(R.id.spnGenero);
            pacienteEditado = (Paciente) it.getSerializableExtra("paciente");
            ((EditText)findViewById(R.id.txtNomePaciente)).setText(pacienteEditado.getNomeCompleto());
            ((EditText)findViewById(R.id.txtDataNascimento)).setText(pacienteEditado.getDataNascimento().toString());
            ((TextView)findViewById(R.id.txtDataCadastro)).setText("Data de Cadastro: " + pacienteEditado.getDataCadastro().toString());

            spnGenero.setSelection(getIndex(spnGenero, pacienteEditado.getSexo()));

            findViewById(R.id.recyclerViewPacientes).setVisibility(View.INVISIBLE);
            findViewById(R.id.include_cadastro_paciente).setVisibility(View.VISIBLE);
        }
        else if(it.hasExtra("user")) {

            String jsonInString = it.getStringExtra("user");

            //JSON from String to Object
            try {
                usuario = (Profissional) Utils.JsonToObject(jsonInString, Profissional.class);
                ((TextView) findViewById(R.id.txtLogado)).setText("Bem vindo, " + usuario.getNomeUsuario());
                ((TextView) findViewById(R.id.txtTitulo)).setText("Pacientes");
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            CriarEventos();
            configurarRecycler();
        }

        Button btnSalvarPaciente = (Button)findViewById(R.id.btnSalvarPaciente);
        btnSalvarPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pacienteEditado == null)
                    pacienteEditado = new Paciente();

                //carregando os campos
                EditText txtNomePaciente = (EditText)findViewById(R.id.txtNomePaciente);
                EditText txtDataNascimento = (EditText)findViewById(R.id.txtDataNascimento);
                Spinner spnGenero = (Spinner)findViewById(R.id.spnGenero);

                //pegando os valores
                pacienteEditado.setNomeCompleto(txtNomePaciente.getText().toString());
                pacienteEditado.setSexo(spnGenero.getSelectedItem().toString());
                pacienteEditado.setDataNascimento(txtDataNascimento.getText().toString());
                boolean vip = chkVip.isChecked();
                String sexo = rgSexo.getCheckedRadioButtonId() == R.id.rbMasculino ? "M" : "F";

                //salvando os dados
                AlunoDAO dao = new AlunoDAO(getBaseContext());
                boolean sucesso;
                if(alunoEditado != null)
                    sucesso = dao.salvar(alunoEditado.getId(), nome, sexo, matricula, uf, vip);
                else
                    sucesso = dao.salvar(nome, sexo, matricula, uf, vip);

                if(sucesso) {
                    Aluno aluno = dao.retornarUltimo();
                    if(alunoEditado != null)
                        adapterAluno.atualizarAluno(aluno);
                    else
                        adapterAluno.adicionarAluno(aluno);

                    //limpa os campos
                    alunoEditado = null;
                    txtNome.setText("");
                    rgSexo.setSelected(false);
                    spnEstado.setSelection(0);
                    chkVip.setChecked(false);

                    Snackbar.make(view, "Salvou!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    findViewById(R.id.includemain).setVisibility(View.VISIBLE);
                    findViewById(R.id.includecadastroaluno).setVisibility(View.INVISIBLE);
                    findViewById(R.id.fab).setVisibility(View.VISIBLE);
                }else{
                    Snackbar.make(view, "Erro ao salvar, consulte os logs!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    RecyclerView recyclerView;
    private PacienteAdapter adapterPaciente;

    private void configurarRecycler() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPacientes);
        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Call<List<Paciente>> call = RetrofitConfig.samuvService().getPacientesList();

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
        ((FloatingActionButton)findViewById(R.id.fab_add_paciente)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            findViewById(R.id.recyclerViewPacientes).setVisibility(View.INVISIBLE);
            findViewById(R.id.include_cadastro_paciente).setVisibility(View.VISIBLE);
            }
        });

        final Calendar calendarDataAtual = Calendar.getInstance();
        int anoAtual = calendarDataAtual.get(Calendar.YEAR);
        int mesAtual = calendarDataAtual.get(Calendar.MONTH);
        int diaAtual = calendarDataAtual.get(Calendar.DAY_OF_MONTH);
        //MONTANDO O OBJETO DE DATA PARA PREENCHER O CAMPOS QUANDO FOR SELECIONADO
        //FORMATO DD/MM/YYYY
        datePickerDialogDataPublicacao = new DatePickerDialog(this, new
                DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int anoSelecionado, int
                            mesSelecionado, int diaSelecionado) {
                        //FORMATANDO O MÊS COM DOIS DÍGITOS
                        String mes = (String.valueOf((mesSelecionado + 1)).length() == 1 ?
                                "0" + (mesSelecionado + 1) : String.valueOf(mesSelecionado));
                        txtDataNascimento.setText(diaSelecionado + "/" + mes + "/" +
                                anoSelecionado);
                    }
                }, anoAtual, mesAtual, diaAtual);
        //CRIANDO EVENTO NO CAMPO DE DATA PARA ABRIR A POPUP
        txtDataNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialogDataPublicacao.show();
            }
        });
        //CRIANDO EVENTO NO CAMPO DE DATA PARA ABRIR A POPUP
        txtDataNascimento.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {

                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        datePickerDialogDataPublicacao.show();
                    }
                }
        );
    }
}
