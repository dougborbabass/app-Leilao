package br.com.douglas.app_leilao.ui.recyclerview.adapter;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

import br.com.douglas.app_leilao.model.Leilao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ListaLeilaoAdapterTest {

    @Mock
    private Context context;
    @Spy
    private final ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(context);

    @Test
    public void deve_AtualizarAListaDeLeiloes_QuandoReceberListaDeLeiloes() {
        doNothing().when(adapter).atualizaLista();
        adapter.atualiza(new ArrayList<>(Arrays.asList(
                new Leilao("Console"),
                new Leilao("Computdaor")
        )));

        int quantidadeLeiloesDevolvida = adapter.getItemCount();

        verify(adapter).atualizaLista();
        assertThat(quantidadeLeiloesDevolvida, is(2));
    }
}