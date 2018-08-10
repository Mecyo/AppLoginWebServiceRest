package br.edu.ifba.samuv.connection;

import java.util.List;

import br.edu.ifba.samuv.models.Atendimento;
import br.edu.ifba.samuv.models.Imagem;
import br.edu.ifba.samuv.models.Usuario;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface SamuvService {

    @FormUrlEncoded
    @POST("logar/")
    Call<Usuario> logar(@Field("login") String login, @Field("senha") String senha);

    @GET("usuario/{id}/")
    Call<Usuario> getUsuario(@Path("id") String id);

    @GET("usuarios/")
    Call<List<Usuario>> getUsuariosList();

    /*@Multipart
    @POST("samuv_analise/upload_image/")
    public Call<Url> upload(@Part("file\"; filename=\"" + file + "\"") RequestBody file,
                            @Part("descricao") RequestBody descricao);*/

    @Multipart
    @POST("samuv_analise/upload_image/")
    Call<String> postImage(@Part MultipartBody.Part image, @Part("name") RequestBody name);
}
