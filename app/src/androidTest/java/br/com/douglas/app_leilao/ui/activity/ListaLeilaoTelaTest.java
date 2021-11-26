package br.com.douglas.app_leilao.ui.activity;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.rule.ActivityTestRule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import br.com.douglas.app_leilao.api.retrofit.client.LeilaoWebClient;
import br.com.douglas.app_leilao.api.retrofit.client.TesteWebClient;
import br.com.douglas.app_leilao.model.Leilao;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class ListaLeilaoTelaTest {

    @Rule
    public ActivityTestRule<ListaLeilaoActivity> activity =
            new ActivityTestRule<>(ListaLeilaoActivity.class, true, false);

    @Rule
    public ActivityScenarioRule<ListaLeilaoActivity> rule = new ActivityScenarioRule<>(ListaLeilaoActivity.class);

    @Test
    public void deve_AparecerUmLeilao_QuandoCarregarUmLeilaoNaApi() throws IOException {
        TesteWebClient webClient = new TesteWebClient();

        boolean bancoDeDadosNaoFoiLimpo = !webClient.limpaBancoDeDados();

        if (bancoDeDadosNaoFoiLimpo) {
            Assert.fail("Banco de dados não foi limpo");
        }

        Leilao leilaoSalvo = webClient.salva(new Leilao("Carro"));

        if (leilaoSalvo == null) {
            Assert.fail("Leilão não foi salvo");
        }

        activity.launchActivity(new Intent());

        onView(withText("Carro"))
                .check(matches(isDisplayed()));
    }
}