package br.com.douglas.app_leilao.ui.activity;

import android.content.Intent;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import br.com.douglas.app_leilao.BaseTesteIntegracao;
import br.com.douglas.app_leilao.R;
import br.com.douglas.app_leilao.model.Leilao;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static br.com.douglas.app_leilao.matchers.ViewMatcher.apareceLeilaoNaPosicao;

public class ListaLeilaoTelaTest extends BaseTesteIntegracao {

    @Rule
    public ActivityTestRule<ListaLeilaoActivity> activity =
            new ActivityTestRule<>(ListaLeilaoActivity.class, true, false);

    @Before
    public void setup() throws IOException {
        limpaBancoDeDadosDaApi();
    }

    @Test
    public void deve_AparecerUmLeilao_QuandoCarregarUmLeilaoNaApi() throws IOException {
        tentaSalvarLeilaoNaApi(new Leilao("Carro"));

        activity.launchActivity(new Intent());

        onView(withId(R.id.lista_leilao_recyclerview))
                .check(matches(apareceLeilaoNaPosicao(0, "Carro", 0.00)));
    }

    @Test
    public void deve_AparecerDoisLeilao_QuandoCarregarDoisLeilaoNaApi() throws IOException {
        tentaSalvarLeilaoNaApi(
                new Leilao("Carro"),
                new Leilao("Xicara"));

        activity.launchActivity(new Intent());

        onView(withId(R.id.lista_leilao_recyclerview))
                .check(matches(apareceLeilaoNaPosicao(0, "Carro", 0.00)));

        onView(withId(R.id.lista_leilao_recyclerview))
                .check(matches(apareceLeilaoNaPosicao(1, "Xicara", 0.00)));
    }

    @Test
    public void deve_AparecerUltimoLeilao_QuandoCarregarDezLeiloesDaApi() throws IOException {
        tentaSalvarLeilaoNaApi(
                new Leilao("Carro"),
                new Leilao("Computador"),
                new Leilao("TV"),
                new Leilao("Notebook"),
                new Leilao("Console"),
                new Leilao("Jogo"),
                new Leilao("Bicicleta"),
                new Leilao("Moto"),
                new Leilao("Patinete"),
                new Leilao("Apartamento"));

        activity.launchActivity(new Intent());

        onView(withId(R.id.lista_leilao_recyclerview))
                .perform(RecyclerViewActions.scrollToPosition(9))
                .check(matches(apareceLeilaoNaPosicao(9, "Apartamento", 0.00)));
    }

    @After
    public void tearDown() throws IOException {
        limpaBancoDeDadosDaApi();
    }
}