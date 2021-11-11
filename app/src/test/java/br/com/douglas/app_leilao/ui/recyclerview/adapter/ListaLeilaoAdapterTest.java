package br.com.douglas.app_leilao.ui.recyclerview.adapter;

import org.junit.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

import br.com.douglas.app_leilao.model.Leilao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ListaLeilaoAdapterTest {

    @Test
    public void deve_AtualizarAListaDeLeiloes_QuandoReceberListaDeLeiloes() {
        ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(null);

        adapter.atualiza(new ArrayList<>(Arrays.asList(
                new Leilao("Console"),
                new Leilao("Computdaor")
        )));

        int quantidadeLeiloesDevolvida = adapter.getItemCount();

        assertThat(quantidadeLeiloesDevolvida, is(2));
    }
}