package br.edu.ifba.samuv.connection;

import java.util.List;

import br.edu.ifba.samuv.models.Atendimento;
import br.edu.ifba.samuv.models.Ferida;
import br.edu.ifba.samuv.models.Imagem;
import br.edu.ifba.samuv.models.Paciente;
import br.edu.ifba.samuv.models.Profissional;
import br.edu.ifba.samuv.models.Tecnica;
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
    Call<Profissional> logar(@Field("login") String login, @Field("senha") String senha);

    @GET("usuario/{id}/")
    Call<Usuario> getUsuario(@Path("id") String id);

    @GET("usuario/{id}/")
    Call<Profissional> getProfissionalUsuario(@Path("usuario_id") int id);

    @GET("usuarios/")
    Call<List<Usuario>> getUsuariosList();

    @GET("pacientes/")
    Call<List<Paciente>> getPacientesList();

    @GET("feridas/")
    Call<List<Ferida>> getFeridasList();

    @GET("tecnicas/")
    Call<List<Tecnica>> getTecnicasList();

    @FormUrlEncoded
    @POST("feridas_paciente/")
    Call<List<Ferida>> getFeridasPaciente(@Field("paciente_id") int idPaciente);

    @FormUrlEncoded
    @POST("atendimentos_ferida/")
    Call<List<Atendimento>> getAtendimentosPorFerida(@Field("ferida_id") int idFerida);

    /*@Multipart
    @POST("samuv_analise/upload_image/")
    public Call<Url> upload(@Part("file\"; filename=\"" + file + "\"") RequestBody file,
                            @Part("descricao") RequestBody descricao);*/

    @Multipart
    @POST("iniciar_atendimento/")
    Call<Atendimento> iniciarAtendimento(@Part MultipartBody.Part foto,
                                               @Part("imageName") String name,
                                               @Part("profissional_id") int profissional_id,
                                               @Part("ferida_id") int ferida_id,
                                               @Part("descricao") String nota,
                                               @Part("tecnicas") List<Tecnica> tecnicas);
}
