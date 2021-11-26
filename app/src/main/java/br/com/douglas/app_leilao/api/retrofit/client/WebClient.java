package br.com.douglas.app_leilao.api.retrofit.client;

import retrofit2.Response;

abstract class WebClient {
    protected <T> boolean temDados(Response<T> response) {
        return response.isSuccessful() && response.body() != null;
    }
}
