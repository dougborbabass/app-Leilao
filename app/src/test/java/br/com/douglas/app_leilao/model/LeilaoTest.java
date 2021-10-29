package br.com.douglas.app_leilao.model;


import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao itemDoLeilao = new Leilao("Console");
    private final Usuario douglas = new Usuario("Douglas");

    @Test
    public void deve_DevolverDescricao_QuandoRecebeDescricao() {
        // criar cenário de testes
        Leilao console = new Leilao("Console");

        // executar ação esperada
        String descricaoDevolvida = console.getDescricao();

        // testar resultado esperado
        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeApenasUmLance() {
        itemDoLeilao.propoe(new Lance(douglas, 200.0));
        double maiorLanceDevolvido = itemDoLeilao.getMaiorLance();
        assertEquals(200.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDescrescente() {
        itemDoLeilao.propoe(new Lance(douglas, 500.0));
        itemDoLeilao.propoe(new Lance(new Usuario("Carol"), 100.0));
        double maiorLanceDevolvido = itemDoLeilao.getMaiorLance();
        assertEquals(500.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        itemDoLeilao.propoe(new Lance(douglas, 7000.0));
        itemDoLeilao.propoe(new Lance(new Usuario("Carol"), 10000.0));
        double maiorLanceDevolvido = itemDoLeilao.getMaiorLance();
        assertEquals(10000.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeApenasUmLance() {
        itemDoLeilao.propoe(new Lance(douglas, 200.0));
        double menorLanceDevolvido = itemDoLeilao.getMenorLance();
        assertEquals(200.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDescrescente() {
        itemDoLeilao.propoe(new Lance(douglas, 500.0));
        itemDoLeilao.propoe(new Lance(new Usuario("Carol"), 100.0));
        double menorLanceDevolvido = itemDoLeilao.getMenorLance();
        assertEquals(100.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        itemDoLeilao.propoe(new Lance(douglas, 7000.0));
        itemDoLeilao.propoe(new Lance(new Usuario("Carol"), 10000.0));
        double menorLanceDevolvido = itemDoLeilao.getMenorLance();
        assertEquals(7000.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeExatosTresLances() {
        itemDoLeilao.propoe(new Lance(douglas, 200.0));
        itemDoLeilao.propoe(new Lance(new Usuario("Carol"), 300.0));
        itemDoLeilao.propoe(new Lance(douglas, 400.0));

        List<Lance> tresMaioresLancesDevolvidos = itemDoLeilao.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoNaoRecebeLances() {
        List<Lance> tresMaioresLancesDevolvidos = itemDoLeilao.tresMaioresLances();

        assertEquals(0, tresMaioresLancesDevolvidos.size());
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasUmLance() {
        itemDoLeilao.propoe(new Lance(douglas, 200.0));
        List<Lance> tresMaioresLancesDevolvidos = itemDoLeilao.tresMaioresLances();

        assertEquals(1, tresMaioresLancesDevolvidos.size());
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasDoisLanceS() {
        itemDoLeilao.propoe(new Lance(douglas, 200.0));
        itemDoLeilao.propoe(new Lance(douglas, 300.0));
        List<Lance> tresMaioresLancesDevolvidos = itemDoLeilao.tresMaioresLances();

        assertEquals(2, tresMaioresLancesDevolvidos.size());
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeMaisDeTresLance() {
        itemDoLeilao.propoe(new Lance(douglas, 200.0));
        itemDoLeilao.propoe(new Lance(douglas, 300.0));
        itemDoLeilao.propoe(new Lance(douglas, 400.0));
        itemDoLeilao.propoe(new Lance(douglas, 100.0));
        List<Lance> tresMaioresLancesDevolvidosParaQuatroLances = itemDoLeilao.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidosParaQuatroLances.size());
        assertEquals(400.0, tresMaioresLancesDevolvidosParaQuatroLances.get(0).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidosParaQuatroLances.get(1).getValor(), DELTA);
        assertEquals(200.0, tresMaioresLancesDevolvidosParaQuatroLances.get(2).getValor(), DELTA);

        itemDoLeilao.propoe(new Lance(douglas, 700.0));
        List<Lance> tresMaioresLancesDevolvidosParaCincoLances = itemDoLeilao.tresMaioresLances();
        assertEquals(3, tresMaioresLancesDevolvidosParaCincoLances.size());
        assertEquals(700.0, tresMaioresLancesDevolvidosParaCincoLances.get(0).getValor(), DELTA);
        assertEquals(400.0, tresMaioresLancesDevolvidosParaCincoLances.get(1).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidosParaCincoLances.get(2).getValor(), DELTA);
    }
}