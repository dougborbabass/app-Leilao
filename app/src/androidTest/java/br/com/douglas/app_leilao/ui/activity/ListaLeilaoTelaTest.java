package br.com.douglas.app_leilao.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.Objects;

import br.com.douglas.app_leilao.R;
import br.com.douglas.app_leilao.api.retrofit.client.TesteWebClient;
import br.com.douglas.app_leilao.formatter.FormatadorDeMoeda;
import br.com.douglas.app_leilao.model.Leilao;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

public class ListaLeilaoTelaTest {

    private static final String BANCO_DE_DADOS_NÃO_FOI_LIMPO = "Banco de dados não foi limpo";
    private static final String LEILÃO_NÃO_FOI_SALVO = "Leilão não foi salvo ";
    @Rule
    public ActivityTestRule<ListaLeilaoActivity> activity =
            new ActivityTestRule<>(ListaLeilaoActivity.class, true, false);

    private final TesteWebClient webClient = new TesteWebClient();
    private final FormatadorDeMoeda formatadorDeMoeda = new FormatadorDeMoeda();
    ;

    @Before
    public void setup() throws IOException {
        boolean bancoDeDadosNaoFoiLimpo = !webClient.limpaBancoDeDados();
        if (bancoDeDadosNaoFoiLimpo) {
            Assert.fail(BANCO_DE_DADOS_NÃO_FOI_LIMPO);
        }
    }

    @Test
    public void deve_AparecerUmLeilao_QuandoCarregarUmLeilaoNaApi() throws IOException {
        tentaSalvarLeilaoNaApi(new Leilao("Carro"));

        activity.launchActivity(new Intent());
        onView(allOf(withText("Carro"),
                withId(R.id.item_leilao_descricao)))
                .check(matches(isDisplayed()));

        String formatoEsperado = formatadorDeMoeda.formata(0.00);
        onView(allOf(withText(formatoEsperado),
                withId(R.id.item_leilao_maior_lance)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void deve_AparecerDoisLeilao_QuandoCarregarDoisLeilaoNaApi() throws IOException {
        tentaSalvarLeilaoNaApi(
                new Leilao("Carro"),
                new Leilao("Xicara"));

        String formatoEsperado = formatadorDeMoeda.formata(0.00);

        activity.launchActivity(new Intent());
//        onView(allOf(withText("Carro"),
//                withId(R.id.item_leilao_descricao)))
//                .check(matches(isDisplayed()));
//
//        onView(allOf(withText(formatoEsperado),
//                withId(R.id.item_leilao_maior_lance)))
//                .check(matches(isDisplayed()));
//
//        onView(allOf(withText("Xicara"),
//                withId(R.id.item_leilao_descricao)))
//                .check(matches(isDisplayed()));
//
//        onView(allOf(withText(formatoEsperado),
//                withId(R.id.item_leilao_maior_lance)))
//                .check(matches(isDisplayed()));

        onView(withId(R.id.lista_leilao_recyclerview))
                .check(matches(apareceLeilao(0, "Carro", 0.00)));
    }

    private Matcher<? super View> apareceLeilao(int posicao,
                                                String descricaoEsperada,
                                                double maiorLanceEsperado) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {

            }

            @Override
            protected boolean matchesSafely(RecyclerView item) {
                View viewDoViewHolder = Objects.requireNonNull(item.findViewHolderForAdapterPosition(posicao)).itemView;
                TextView textViewDescricao = viewDoViewHolder.findViewById(R.id.item_leilao_descricao);
                boolean temDescricaoEsperada = textViewDescricao.getText().toString()
                        .equals(descricaoEsperada);

                TextView textViewMaiorLance = viewDoViewHolder.findViewById(R.id.item_leilao_maior_lance);
                FormatadorDeMoeda formatadorDeMoeda = new FormatadorDeMoeda();
                boolean temMaiorLanceEsperado = textViewMaiorLance.getText().toString()
                        .equals(formatadorDeMoeda.formata(maiorLanceEsperado));

                return temDescricaoEsperada && temMaiorLanceEsperado;
            }
        };
    }

    private void tentaSalvarLeilaoNaApi(Leilao... leiloes) throws IOException {
        for (Leilao leilao : leiloes) {
            Leilao leilaoSalvo = webClient.salva(leilao);

            if (leilaoSalvo == null) {
                Assert.fail(LEILÃO_NÃO_FOI_SALVO + leilao.getDescricao());
            }
        }
    }
}