package br.com.douglas.app_leilao.api.retrofit.service;

import java.util.List;

import br.com.douglas.app_leilao.model.Lance;
import br.com.douglas.app_leilao.model.Leilao;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LeilaoService {

    @GET("leilao")
    Call<List<Leilao>> todos();

    @PUT("leilao/{id}/lance")
    Call<Void> propoe(@Path("id") Long id, @Body Lance lance);

}