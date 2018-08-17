package br.edu.ifba.samuv.connection;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private static final String URL_BASE_SAMUV = "http://samuv.pythonanywhere.com/api_rest/";
    private static Retrofit instance;

    private RetrofitConfig() {}

    public static Retrofit getInstance(){

        if(instance == null){
            instance = new Retrofit.Builder()
                    .baseUrl(URL_BASE_SAMUV)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return instance;
    }

    public static SamuvService samuvService() {
        return getInstance().create(SamuvService.class);
    }
}
