package br.edu.ifba.samuv;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import br.edu.ifba.samuv.connection.RetrofitConfig;
import br.edu.ifba.samuv.models.Atendimento;
import br.edu.ifba.samuv.models.Imagem;
import br.edu.ifba.samuv.models.Usuario;
import br.edu.ifba.samuv.util.Utils;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton fab_main;
    private static final int REQUEST_CAPTURE_CAMERA =100;
    private ImageView image_capture;
    private TextView txtLogado;
    private ProgressDialog load;
    private Usuario usuarioLogado;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent it = getIntent();

        //Recuperei a string da outra activity
        String jsonInString = it.getStringExtra("user");

        //JSON from String to Object
        try {
            usuarioLogado = (Usuario)Utils.JsonToObject(jsonInString, Usuario.class);
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        String nomeUsuario = usuarioLogado.getNomeUsuario();

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        txtLogado = findViewById(R.id.txtLogado);
        txtLogado.setText("Bem vindo, " + nomeUsuario);

        fab_main = findViewById(R.id.fab_main);
        image_capture = findViewById(R.id.img_photo_capture);

        fab_main.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.fab_main:{
                openCamera();
                break;
            }
        }
    }

    public void openCamera(){
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)==
                PackageManager.PERMISSION_GRANTED){
            startActivityForResult(pictureIntent,REQUEST_CAPTURE_CAMERA);
        }else{
            ActivityCompat.requestPermissions(this,
                    new String[]{
                    Manifest.permission.CAMERA
                    },REQUEST_CAPTURE_CAMERA);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CAPTURE_CAMERA && resultCode == RESULT_OK){
            Bundle extra = data.getExtras();
            Bitmap imBitmap = (Bitmap)extra.get("data");
            image_capture.setImageBitmap(imBitmap);
        }
    }

    public Atendimento iniciarAtendimento(final Imagem imagem, final MainActivity mainActivity){
        try {
            showProgress(mainActivity,"Aguarde","Criando seu anúncio...");

            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imagem.getFoto());
            RequestBody descricao= RequestBody.create(MediaType.parse("text/plain"), anuncio.getDescricao());
            Call<Atendimento> call = new RetrofitConfig().samuvService().upload(requestBody, descricao);
            call.enqueue(new Callback<Atendimento>() {
                @Override
                public void onResponse(Call<Atendimento> call, Response<Atendimento> response) {

                }

                @Override
                public void onFailure(Call<Atendimento> call, Throwable t) {

                }
            });
		}catch(Exception e) {
		}
}
