package br.edu.ifba.samuv.connection;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private static final String urlBaseSamuv = "http://samuv.pythonanywhere.com/api_rest/";

    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlBaseSamuv)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public RetrofitConfig(String teste) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(teste)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public SamuvService samuvService() {
        return this.retrofit.create(SamuvService.class);
    }
}
