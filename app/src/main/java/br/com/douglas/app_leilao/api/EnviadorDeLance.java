package br.com.douglas.app_leilao.api;

import br.com.douglas.app_leilao.api.retrofit.client.LeilaoWebClient;
import br.com.douglas.app_leilao.api.retrofit.client.RespostaListener;
import br.com.douglas.app_leilao.exception.LanceMenorQueOUltimoLanceException;
import br.com.douglas.app_leilao.exception.LanceSeguidoDoMesmoUsuarioException;
import br.com.douglas.app_leilao.exception.UsuarioJaDeuCincoLancesException;
import br.com.douglas.app_leilao.model.Lance;
import br.com.douglas.app_leilao.model.Leilao;
import br.com.douglas.app_leilao.ui.dialog.AvisoDialogManager;

public class EnviadorDeLance {

    private final LeilaoWebClient client;
    private final LanceProcessadoListener listener;
    private final AvisoDialogManager avisoManager;

    public EnviadorDeLance(LeilaoWebClient client,
                           LanceProcessadoListener listener,
                           AvisoDialogManager avisoManager) {
        this.client = client;
        this.listener = listener;
        this.avisoManager = avisoManager;
    }

    public void envia(final Leilao leilao, Lance lance) {
        try {
            leilao.propoe(lance);
            client.propoe(lance, leilao.getId(), new RespostaListener<Void>() {
                @Override
                public void sucesso(Void resposta) {
                    listener.processado(leilao);
                }

                @Override
                public void falha(String mensagem) {
                    avisoManager.mostraToastFalhaNoEnvio();
                }
            });
        } catch (LanceMenorQueOUltimoLanceException exception) {
            avisoManager.mostraAvisoLanceMenorQueUltimoLance();
        } catch (LanceSeguidoDoMesmoUsuarioException exception) {
            avisoManager.mostraAvisoLanceSeguidoDoMesmoUsuario();
        } catch (UsuarioJaDeuCincoLancesException exception) {
            avisoManager.mostraAvisoUsuarioJaDeuCincoLances();
        }
    }

    public interface LanceProcessadoListener {
        void processado(Leilao leilao);
    }
}
