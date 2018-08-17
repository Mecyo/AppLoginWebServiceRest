package br.edu.ifba.samuv.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.samuv.R;
import br.edu.ifba.samuv.connection.RetrofitConfig;
import br.edu.ifba.samuv.models.Atendimento;
import br.edu.ifba.samuv.models.Ferida;
import br.edu.ifba.samuv.models.Profissional;
import br.edu.ifba.samuv.models.Tecnica;
import br.edu.ifba.samuv.models.Usuario;
import br.edu.ifba.samuv.util.CustomDialogFragment;
import br.edu.ifba.samuv.util.Utils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton fab_main;
    private static final int REQUEST_CAPTURE_CAMERA =100;
    private ImageView image_capture;
    private ProgressDialog load;
    private Profissional usuarioLogado;
    private Ferida ferida;
    private Uri mImageUri;
    private TextView txtResultado;
    private String anotacoes = "";
    private List<Tecnica> tecnicas =  new ArrayList<>();
    private String filename;
    private File file;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageUri = getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());

        Intent it = getIntent();

        //Recuperei a string da outra activity
        String jsonUser = it.getStringExtra("user");
        String jsonFerida = it.getStringExtra("ferida");

        //JSON from String to Object
        try {
            usuarioLogado = (Profissional)Utils.JsonToObject(jsonUser, Profissional.class);
            ferida = (Ferida)Utils.JsonToObject(jsonFerida, Ferida.class);
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        fab_main = findViewById(R.id.fab_main);
        image_capture = findViewById(R.id.img_photo_capture);
        txtResultado = findViewById(R.id.txtResultado);


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
            String path = getRealPathFromURI(mImageUri);
            filename = path.substring(path.lastIndexOf("/")+1);
            file = Utils.bitmapToFile(imBitmap, getApplicationContext(), filename);

            showTecnicaDialog();
            /*AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setTitle("Técnicas")
                    .setMessage("Selecione as técnicas aplicadas:")
                    .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                            builder.setTitle("Outras informações(Opcional)")
                                    .setMessage("Selecione as técnicas aplicadas:")
                                    .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            iniciarAtendimento();
                                        }
                                    })
                                    .create()
                                    .show();
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .create()
                    .show();*/
        }
    }

    public void iniciarAtendimento(List<Tecnica> tecnicas) {
        final String retorno;
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("foto", file.getName(), reqFile);

        try {
            Call<Atendimento> call = RetrofitConfig.samuvService().iniciarAtendimento(
                    body, filename, usuarioLogado.getId(), ferida.getPk(), anotacoes, tecnicas);

            call.enqueue(new Callback<Atendimento>() {
                @Override
                public void onResponse(Call<Atendimento> call, Response<Atendimento> response) {
                    // pegar a resposta
                    if (response.isSuccessful()) {
                        Atendimento novo = response.body();
                        txtResultado.setText(novo.toString());
                        //Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), response.errorBody().toString(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Atendimento> call, Throwable t) {
                    // tratar algum erro
                    Toast.makeText(getApplicationContext(), "Não foi possível realizar o login: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private String getRealPathFromURI(Uri contentUri) {

        // can post image
        String [] proj={MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery( contentUri,
                proj, // Which columns to return
                null,       // WHERE clause; which rows to return (all rows)
                null,       // WHERE clause selection arguments (none)
                null); // Order-by clause (ascending by name)
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        String res = cursor.getString(column_index);
        cursor.close();

        return res;
    }

    private void showTecnicaDialog() {
        FragmentManager fm = getSupportFragmentManager();
        CustomDialogFragment cdf = CustomDialogFragment.newInstance("Técnicas");
        cdf.show(fm, "dialog_tecnicas");
    }
}
