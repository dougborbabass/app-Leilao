package br.com.douglas.app_leilao;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;

import java.io.IOException;

import br.com.douglas.app_leilao.api.retrofit.client.TesteWebClient;
import br.com.douglas.app_leilao.database.dao.UsuarioDAO;
import br.com.douglas.app_leilao.model.Leilao;
import br.com.douglas.app_leilao.model.Usuario;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.fail;

public abstract class BaseTesteIntegracao {

    private static final String BANCO_DE_DADOS_NÃO_FOI_LIMPO = "Banco de dados não foi limpo";
    private static final String LEILÃO_NÃO_FOI_SALVO = "Leilão não foi salvo ";
    public static final String USUÁRIO_NÃO_FOI_SALVO = "Usuário não foi salvo";

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

    protected void tentaSalvarUsuariosNoBancoDeDados(Usuario... usuarios) {
        UsuarioDAO dao = new UsuarioDAO(getInstrumentation().getTargetContext());
        for (Usuario usuario: usuarios) {
            Usuario usuarioSalvo = dao.salva(usuario);
            if (usuarioSalvo == null) {
                fail(USUÁRIO_NÃO_FOI_SALVO);
            }
        }
    }

    protected void limpaBancoDeDadosInterno() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        appContext.deleteDatabase(BuildConfig.BANCO_DE_DADOS);
    }
}
