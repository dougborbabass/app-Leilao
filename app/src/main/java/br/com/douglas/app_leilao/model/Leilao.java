package br.com.douglas.app_leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.douglas.app_leilao.exception.LanceMenorQueOUltimoLanceException;
import br.com.douglas.app_leilao.exception.LanceSeguidoDoMesmoUsuarioException;
import br.com.douglas.app_leilao.exception.UsuarioJaDeuCincoLancesException;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance = 0.0;
    private double menorLance = 0.0;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public String getDescricao() {
        return descricao;
    }

    public double getMaiorLance() {
        return maiorLance;
    }

    public double getMenorLance() {
        return menorLance;
    }

    public void propoe(Lance lance) {
        if (lanceNaoValido(lance)) return;
        lances.add(lance);

        double valorLance = lance.getValor();
        if (defineMaiorEMenorLanceParaOPrimeiroLance(valorLance)) return;
        Collections.sort(lances);
        calculaMaiorLance(valorLance);
        calculaMenorLance(valorLance);
    }

    private boolean defineMaiorEMenorLanceParaOPrimeiroLance(double valorLance) {
        if (lances.size() == 1) {
            maiorLance = valorLance;
            menorLance = valorLance;
            return true;
        }
        return false;
    }

    private boolean lanceNaoValido(Lance lance) {
        if (lanceForMenorQueUltimoLance(lance))
            throw new LanceMenorQueOUltimoLanceException();

        if (temLances()) {
            Usuario usuarioNovo = lance.getUsuario();
            if (usuarioForOMesmoDoUltimoLance(usuarioNovo))
                throw new LanceSeguidoDoMesmoUsuarioException();

            if (usuarioDeuCincoLances(usuarioNovo))
                throw new UsuarioJaDeuCincoLancesException();
        }
        return false;
    }

    private boolean temLances() {
        return !lances.isEmpty();
    }

    private boolean usuarioDeuCincoLances(Usuario usuarioNovo) {
        int lancesDoUsuario = 0;
        for (Lance l : lances) {
            final Usuario usuarioExistente = l.getUsuario();
            if (usuarioExistente.equals(usuarioNovo)) {
                lancesDoUsuario++;
                if (lancesDoUsuario == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean usuarioForOMesmoDoUltimoLance(Usuario usuarioNovo) {
        Usuario ulitmoUsuario = lances.get(0).getUsuario();
        if (usuarioNovo.equals(ulitmoUsuario)) {
            return true;
        }
        return false;
    }

    private boolean lanceForMenorQueUltimoLance(Lance lance) {
        if (maiorLance > lance.getValor()) {
            return true;
        }
        return false;
    }

    private void calculaMenorLance(double valorLance) {
        if (valorLance < menorLance) {
            menorLance = valorLance;
        }
    }

    private void calculaMaiorLance(double valorLance) {
        if (valorLance > maiorLance) {
            maiorLance = valorLance;
        }
    }

    public List<Lance> tresMaioresLances() {
        int qtdMaximaLances = lances.size();
        if (qtdMaximaLances > 3) {
            qtdMaximaLances = 3;
        }
        return lances.subList(0, qtdMaximaLances);
    }

    public int quantidadeDeLances() {
        return lances.size();
    }
}
