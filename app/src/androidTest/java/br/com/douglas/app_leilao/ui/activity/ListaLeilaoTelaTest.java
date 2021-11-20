package br.com.douglas.app_leilao.ui.activity;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class ListaLeilaoTelaTest {

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(ListaLeilaoActivity.class);

    @Test
    public void deve_AparecerUmLeilao_QuandoCarregarUmLeilaoNaApi() {
        onView(withText("Carro"))
                .check(matches(isDisplayed()));
    }

}