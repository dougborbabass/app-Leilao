package br.com.douglas.app_leilao.api.retrofit.client;

public interface RespostaListener<T> {
    void sucesso(T resposta);

    void falha(String mensagem);
}
