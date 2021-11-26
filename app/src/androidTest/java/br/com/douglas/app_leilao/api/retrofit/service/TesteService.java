package br.com.douglas.app_leilao.api.retrofit.service;

import br.com.douglas.app_leilao.model.Leilao;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TesteService {

    @POST("leilao")
    Call<Leilao> salva(@Body Leilao leilao);

    @GET("reset")
    Call<Void> limpaBancoDeDados();
}
