package br.com.douglas.app_leilao;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;

import java.io.IOException;

import br.com.douglas.app_leilao.api.retrofit.client.TesteWebClient;
import br.com.douglas.app_leilao.model.Leilao;

public abstract class BaseTesteIntegracao {

    private static final String BANCO_DE_DADOS_NÃO_FOI_LIMPO = "Banco de dados não foi limpo";
    private static final String LEILÃO_NÃO_FOI_SALVO = "Leilão não foi salvo ";

    private final TesteWebClient webClient = new TesteWebClient();

    protected void limpaBancoDeDadosDaApi() throws IOException {
        boolean bancoDeDadosNaoFoiLimpo = !webClient.limpaBancoDeDados();
        if (bancoDeDadosNaoFoiLimpo) {
            Assert.fail(BANCO_DE_DADOS_NÃO_FOI_LIMPO);
        }
    }

    protected void tentaSalvarLeilaoNaApi(Leilao... leiloes) throws IOException {
        for (Leilao leilao : leiloes) {
            Leilao leilaoSalvo = webClient.salva(leilao);

            if (leilaoSalvo == null) {
                Assert.fail(LEILÃO_NÃO_FOI_SALVO + leilao.getDescricao());
            }
        }
    }

    protected void limpaBancoDeDadosInterno() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        appContext.deleteDatabase(BuildConfig.BANCO_DE_DADOS);
    }
}
