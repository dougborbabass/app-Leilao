package br.com.douglas.app_leilao.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import br.com.douglas.app_leilao.api.retrofit.client.LeilaoWebClient;
import br.com.douglas.app_leilao.api.retrofit.client.RespostaListener;
import br.com.douglas.app_leilao.exception.LanceMenorQueOUltimoLanceException;
import br.com.douglas.app_leilao.exception.LanceSeguidoDoMesmoUsuarioException;
import br.com.douglas.app_leilao.exception.UsuarioJaDeuCincoLancesException;
import br.com.douglas.app_leilao.model.Lance;
import br.com.douglas.app_leilao.model.Leilao;
import br.com.douglas.app_leilao.model.Usuario;
import br.com.douglas.app_leilao.ui.dialog.AvisoDialogManager;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EnviadorDeLanceTest {

    @Mock
    private EnviadorDeLance.LanceProcessadoListener listener;
    @Mock
    private LeilaoWebClient client;
    @Mock
    private AvisoDialogManager managerAviso;
    @Mock
    private Leilao leilao;

    @Test
    public void deve_MostrarMensagemDeFalha_QuandoLanceForMenorQueUltimoLance() {
        EnviadorDeLance enviadorDeLance = new EnviadorDeLance(client, listener, managerAviso);

        doThrow(LanceMenorQueOUltimoLanceException.class)
                .when(leilao).propoe(any(Lance.class));

        enviadorDeLance.envia(leilao, new Lance(new Usuario("Rafael"), 1000));

        verify(managerAviso).mostraAvisoLanceMenorQueUltimoLance();
    }

    @Test
    public void deve_MostrarMensagemDeFalha_QuandoUsuarioComCincoLancesDerNovoLance() {
        EnviadorDeLance enviadorDeLance = new EnviadorDeLance(client, listener, managerAviso);

        doThrow(UsuarioJaDeuCincoLancesException.class)
                .when(leilao).propoe(any(Lance.class));

        enviadorDeLance.envia(leilao, new Lance(new Usuario("Carol"), 200));

        verify(managerAviso).mostraAvisoUsuarioJaDeuCincoLances();
    }

    @Test
    public void deve_MostrarMensagemDeFalha_QuandoOUsuarioDoUltimoLanceDerNovoLance(){
        EnviadorDeLance enviador = new EnviadorDeLance(
                client,
                listener,
                managerAviso);
        doThrow(LanceSeguidoDoMesmoUsuarioException.class)
                .when(leilao).propoe(any(Lance.class));

        enviador.envia(leilao, new Lance(new Usuario("Douglas"), 200));

        verify(managerAviso).mostraAvisoLanceSeguidoDoMesmoUsuario();
    }

    @Test
    public void deve_MostraMensagemDeFalha_QuandoFalharEnvioDeLanceParaAPI(){
        EnviadorDeLance enviador = new EnviadorDeLance(
                client,
                listener,
                managerAviso);
        doAnswer(invocation -> {
            RespostaListener<Void> argument = invocation.getArgument(2);
            argument.falha("");
            return null;
        }).when(client)
                .propoe(any(Lance.class),
                        anyLong(),
                        any(RespostaListener.class));

        enviador.envia(new Leilao("Computador"),
                new Lance(new Usuario("Douglas"), 200));

        verify(managerAviso).mostraToastFalhaNoEnvio();
        verify(listener, never()).processado(new Leilao("Computador"));
    }

    @Test
    public void deve_NotificarLanceProcessado_QuandoEnviarLanceParaAPIComSucesso(){
        EnviadorDeLance enviador = new EnviadorDeLance(
                client,
                listener,
                managerAviso);
        doAnswer(invocation -> {
            RespostaListener<Void> argument = invocation.getArgument(2);
            argument.sucesso(any(Void.class));
            return null;
        }).when(client)
                .propoe(any(Lance.class), anyLong(), any(RespostaListener.class));

        enviador.envia(new Leilao("Computador"),
                new Lance(new Usuario("Douglas"), 200));

        verify(listener).processado(any(Leilao.class));
        verify(managerAviso, never()).mostraToastFalhaNoEnvio();
    }
}