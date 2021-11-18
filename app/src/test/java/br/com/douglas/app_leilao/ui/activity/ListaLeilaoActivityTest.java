package br.com.douglas.app_leilao.ui.activity;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.douglas.app_leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ListaLeilaoActivityTest {

    @Mock
    private Context context;
    @Spy
    private final ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(context);

    @Test
    public void deve_AtualizarListaDeLeiloes_quandoBuscarLeilosDaApi() {
        ListaLeilaoActivity activity = new ListaLeilaoActivity();

        Mockito.doNothing().when(adapter).atualizaLista();
        activity.buscaLeiloes(adapter);
        int qtdLeiloesDevolvida = adapter.getItemCount();

        assertThat(qtdLeiloesDevolvida, is(3));
    }
}