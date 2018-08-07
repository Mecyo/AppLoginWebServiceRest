package br.edu.ifba.samuv.connection;

import java.util.List;

import br.edu.ifba.samuv.models.Usuario;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SamuvService {

    @FormUrlEncoded
    @POST("logar/")
    Call<Usuario> logar(@Field("login") String login, @Field("senha") String senha);

    @GET("usuario/{id}/")
    Call<Usuario> getUsuario(@Path("id") String id);

    @GET("usuarios/")
    Call<List<Usuario>> getUsuariosList();
}
