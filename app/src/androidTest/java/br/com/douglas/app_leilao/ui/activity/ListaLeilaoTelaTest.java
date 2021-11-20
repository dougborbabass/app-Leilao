package br.com.douglas.app_leilao.ui.activity;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class ListaLeilaoTelaTest {

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(ListaLeilaoActivity.class);

    @Test
    public void deve_AparecerUmLeilao_QuandoCarregarUmLeilaoNaApi() {
        Espresso.onView(ViewMatchers.withText("Carro"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

}