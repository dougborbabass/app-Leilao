package br.com.douglas.app_leilao.ui;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import br.com.douglas.app_leilao.api.retrofit.client.LeilaoWebClient;
import br.com.douglas.app_leilao.api.retrofit.client.RespostaListener;
import br.com.douglas.app_leilao.model.Leilao;
import br.com.douglas.app_leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

public class AtualizadorDeLeiloes {

    private static final String MENSAGEM_AVISO_FALHA_AO_CARREGAR_LEILOES = "Não foi possível carregar os leilões";

    public void buscaLeiloes(final ListaLeilaoAdapter adapter,
                             LeilaoWebClient client,
                             final Context context) {
        client.todos(new RespostaListener<List<Leilao>>() {
            @Override
            public void sucesso(List<Leilao> leiloes) {
                adapter.atualiza(leiloes);
            }

            @Override
            public void falha(String mensagem) {
                mostraMsgDeFalha(context);
            }
        });
    }

    public void mostraMsgDeFalha(Context context) {
        Toast.makeText(context,
                MENSAGEM_AVISO_FALHA_AO_CARREGAR_LEILOES,
                Toast.LENGTH_SHORT).show();
    }
}
