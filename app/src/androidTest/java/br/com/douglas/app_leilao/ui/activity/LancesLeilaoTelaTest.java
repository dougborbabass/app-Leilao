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
import br.com.douglas.app_leilao.database.dao.UsuarioDAO;
import br.com.douglas.app_leilao.formatter.FormatadorDeMoeda;
import br.com.douglas.app_leilao.model.Leilao;
import br.com.douglas.app_leilao.model.Usuario;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.fail;

public class LancesLeilaoTelaTest extends BaseTesteIntegracao {

    @Rule
    public ActivityTestRule<ListaLeilaoActivity> activity =
            new ActivityTestRule<>(ListaLeilaoActivity.class, true, false);

    @Before
    public void setup() throws IOException {
        limpaBancoDeDadosDaApi();
        limpaBancoDeDadosInterno();
    }

    @Test
    public void deve_AtualizarLancesDoLeilao_QuandoReceberUmLance() throws IOException {

        //Salvar leilão na API
        tentaSalvarLeilaoNaApi(new Leilao("Carro"));

        //Inicializar a main Activity
        activity.launchActivity(new Intent());

        //Clica no leilão
        onView(withId(R.id.lista_leilao_recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        //Clica no FAB da tela de lances do leilao
        onView(allOf(withId(R.id.lances_leilao_fab_adiciona),
                isDisplayed()))
                .perform(click());

        //Verifica se aparece dialog de aviso por não ter usuario cadastrado
        onView(withText("Usuários não encontrados"))
                .check(matches(isDisplayed()));

        onView(allOf(withText("Não existe usuários cadastrados! Cadastre um usuário para propor o lance."),
                withId(android.R.id.message)))
                .check(matches(isDisplayed()));

        //Clica no botão cadastrar usuário
        onView(allOf(withText("Cadastrar usuário"),
                isDisplayed()))
                .perform(click());

        //Clica no FAB da tela lista de usuarios
        onView(withId(R.id.lista_usuario_fab_adiciona))
                .perform(click());

        //Clica no editText e preenche com o nome do usuario
        onView(withId(R.id.form_usuario_nome_edit_text))
                .perform(typeText("Douglas"), closeSoftKeyboard());

        //Clica em adicionar
        onView(withText("ADICIONAR"))
                .perform(click());

        //Clica no back do android
        pressBack();

        //Clica no FAB lances do leilao
        onView(allOf(withId(R.id.lances_leilao_fab_adiciona),
                isDisplayed()))
                .perform(click());

        //Verifica visibilidade do dialog com o titulo esperado
        propoeNovoLance("200", 1, "Douglas");

        //Fazer assertion para as views de maior e maior lances e tb para os maiores lances
        FormatadorDeMoeda formatadorDeMoeda = new FormatadorDeMoeda();
        onView(withId(R.id.lances_leilao_maior_lance))
                .check(matches(
                        allOf(withText(formatadorDeMoeda.formata(200)),
                                isDisplayed())));

        onView(withId(R.id.lances_leilao_menor_lance))
                .check(matches(
                        allOf(withText(formatadorDeMoeda.formata(200)),
                                isDisplayed())));

        onView(withId(R.id.lances_leilao_maiores_lances))
                .check(matches(allOf(withText("200.0 - (1) Douglas\n"),
                        isDisplayed())));
    }

    @Test
    public void deve_AtualizarLancesDoLeilao_QuandoRecebeTresLances() throws IOException {
        tentaSalvarLeilaoNaApi(new Leilao("Carro"));
        tentaSalvarUsuariosNoBancoDeDados(new Usuario("Douglas"), new Usuario("Carol"));

        activity.launchActivity(new Intent());

        onView(withId(R.id.lista_leilao_recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        propoeNovoLance("200", 1, "Douglas");
        propoeNovoLance("300", 2, "Carol");
        propoeNovoLance("400", 1, "Douglas");

        FormatadorDeMoeda formatadorDeMoeda = new FormatadorDeMoeda();
        onView(withId(R.id.lances_leilao_maior_lance))
                .check(matches(
                        allOf(withText(formatadorDeMoeda.formata(400)),
                                isDisplayed())));

        onView(withId(R.id.lances_leilao_menor_lance))
                .check(matches(
                        allOf(withText(formatadorDeMoeda.formata(200)),
                                isDisplayed())));

        onView(withId(R.id.lances_leilao_maiores_lances))
                .check(matches(allOf(withText("400.0 - (1) Douglas\n" +
                                "300.0 - (2) Carol\n" +
                                "200.0 - (1) Douglas\n"),
                        isDisplayed())));
    }

    private void propoeNovoLance(String valorLance, int idUsuario, String usuario) {
        onView(allOf(withId(R.id.lances_leilao_fab_adiciona),
                isDisplayed()))
                .perform(click());

        onView(withText("Novo lance"))
                .check(matches(isDisplayed()));

        onView(allOf(withId(R.id.form_lance_valor_editText)
                , isDisplayed()))
                .perform(click(), typeText(valorLance), closeSoftKeyboard());

        onView(allOf(withId(R.id.sp_form_lance_usuario),
                isDisplayed()))
                .perform(click());
        onData(is(new Usuario(idUsuario, usuario)))
                .inRoot(isPlatformPopup())
                .perform(click());

        onView(allOf(withText("PROPOR"),
                isDisplayed()))
                .perform(click());
    }

    @After
    public void tearDown() throws Exception {
        limpaBancoDeDadosDaApi();
        limpaBancoDeDadosInterno();
    }
}
