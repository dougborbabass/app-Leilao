package br.com.douglas.app_leilao.ui;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.douglas.app_leilao.api.retrofit.client.LeilaoWebClient;
import br.com.douglas.app_leilao.api.retrofit.client.RespostaListener;
import br.com.douglas.app_leilao.model.Leilao;
import br.com.douglas.app_leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AtualizadorDeLeiloesTest {

    @Mock
    private ListaLeilaoAdapter adapter;
    @Mock
    private LeilaoWebClient client;
    @Mock
    private Context context;

    @Test
    public void deve_AtualizarListaDeLeiloes_QuandoBuscarLeiloesDaApi() {
        AtualizadorDeLeiloes atualizador = new AtualizadorDeLeiloes();
        doAnswer(invocation -> {
            RespostaListener<List<Leilao>> argument = invocation.getArgument(0);
            argument.sucesso(new ArrayList<>(Arrays.asList(
                    new Leilao("Computador"),
                    new Leilao("Carro")
            )));
            return null;
        }).when(client).todos(any(RespostaListener.class));

        atualizador.buscaLeiloes(adapter, client, context);

        verify(client).todos(any(RespostaListener.class));
        verify(adapter).atualiza(new ArrayList<>(Arrays.asList(
                new Leilao("Computador"),
                new Leilao("Carro")
        )));
    }

    @Test
    public void deve_ApresentarMensagemDeFalha_QuandoFalharABuscaDeLeiloes() {
        AtualizadorDeLeiloes atualizador = Mockito.spy(new AtualizadorDeLeiloes());
        doNothing().when(atualizador).mostraMsgDeFalha(context);

        doAnswer(invocation -> {
            RespostaListener<List<Leilao>> argument = invocation.getArgument(0);
            argument.falha(Mockito.anyString());
            return null;
        }).when(client).todos(any(RespostaListener.class));

        atualizador.buscaLeiloes(adapter, client, context);

        verify(atualizador).mostraMsgDeFalha(context);
    }
}