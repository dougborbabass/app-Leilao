package br.com.douglas.app_leilao.ui.activity;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import br.com.douglas.app_leilao.api.retrofit.client.LeilaoWebClient;
import br.com.douglas.app_leilao.model.Leilao;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class ListaLeilaoTelaTest {

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(ListaLeilaoActivity.class);

    @Test
    public void deve_AparecerUmLeilao_QuandoCarregarUmLeilaoNaApi() throws IOException {

        Leilao leilaoSalvo = new LeilaoWebClient().salva(new Leilao("Carro"));

        if (leilaoSalvo == null) {
            Assert.fail("Leilão não foi salvo");
        }

        onView(withText("Carro"))
                .check(matches(isDisplayed()));

    }

}