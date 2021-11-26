package br.com.douglas.app_leilao.api.retrofit;

import br.com.douglas.app_leilao.api.retrofit.service.TesteService;

public class TesteRetrofitInicializador extends RetrofitInicializador{

    public TesteService getTesteService() {
        return retrofit.create(TesteService.class);
    }
}
