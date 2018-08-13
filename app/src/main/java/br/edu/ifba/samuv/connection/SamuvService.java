package br.edu.ifba.samuv.connection;

import java.util.List;

import br.edu.ifba.samuv.models.Atendimento;
import br.edu.ifba.samuv.models.Ferida;
import br.edu.ifba.samuv.models.Imagem;
import br.edu.ifba.samuv.models.Paciente;
import br.edu.ifba.samuv.models.Usuario;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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

    @GET("pacientes/")
    Call<List<Paciente>> getPacientesList();

    @GET("feridas/")
    Call<List<Ferida>> getFeridasList();

    @GET("feridas_paciente/")
    Call<List<Ferida>> getFeridasPaciente(@Field("paciente") String idPaciente);

    /*@Multipart
    @POST("samuv_analise/upload_image/")
    public Call<Url> upload(@Part("file\"; filename=\"" + file + "\"") RequestBody file,
                            @Part("descricao") RequestBody descricao);*/

    @Multipart
    @POST("upload_image/")
    Call<ResponseBody> postImage(@Part MultipartBody.Part image, @Field("imageName") String name, @Field("profissional_id") int profissional_id, @Field("paciente_id") int paciente_id);
}
