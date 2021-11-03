package br.com.douglas.app_leilao.model;


import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

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
        itemDoLeilao.propoe(new Lance(new Usuario("Carol"), 300.0));
        List<Lance> tresMaioresLancesDevolvidos = itemDoLeilao.tresMaioresLances();

        assertEquals(2, tresMaioresLancesDevolvidos.size());
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeMaisDeTresLance() {
        itemDoLeilao.propoe(new Lance(douglas, 200.0));
        itemDoLeilao.propoe(new Lance(new Usuario("Carol"), 300.0));
        itemDoLeilao.propoe(new Lance(new Usuario("Felipe"), 400.0));
        itemDoLeilao.propoe(new Lance(new Usuario("Roberto"), 100.0));
        List<Lance> tresMaioresLancesDevolvidosParaQuatroLances = itemDoLeilao.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidosParaQuatroLances.size());
        assertEquals(400.0, tresMaioresLancesDevolvidosParaQuatroLances.get(0).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidosParaQuatroLances.get(1).getValor(), DELTA);
        assertEquals(200.0, tresMaioresLancesDevolvidosParaQuatroLances.get(2).getValor(), DELTA);

        itemDoLeilao.propoe(new Lance(new Usuario("Caroline"), 700.0));
        List<Lance> tresMaioresLancesDevolvidosParaCincoLances = itemDoLeilao.tresMaioresLances();
        assertEquals(3, tresMaioresLancesDevolvidosParaCincoLances.size());
        assertEquals(700.0, tresMaioresLancesDevolvidosParaCincoLances.get(0).getValor(), DELTA);
        assertEquals(400.0, tresMaioresLancesDevolvidosParaCincoLances.get(1).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidosParaCincoLances.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMaiorLance_QuandoNaoTiverLances() {
        double maiorLanceDevolvido = itemDoLeilao.getMaiorLance();
        assertEquals(0.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMenorLance_QuandoNaoTiverLances() {
        double menorLanceDevolvido = itemDoLeilao.getMenorLance();
        assertEquals(0.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void naoDeve_AdicionarLance_QuandoForMenorQueOMaiorLance() {
        itemDoLeilao.propoe(new Lance(douglas, 500.00));
        itemDoLeilao.propoe(new Lance(new Usuario("Carol"), 400.00));
        int quantidadeDeLancesDevolvida = itemDoLeilao.quantidadeDeLances();
        assertEquals(1, quantidadeDeLancesDevolvida);
    }

    @Test
    public void naoDeve_AdicionarLance_QuandoForOMesmoUsuarioDoUltimoLance() {
        itemDoLeilao.propoe(new Lance(douglas, 500.00));
        itemDoLeilao.propoe(new Lance(douglas, 600.00));
        int quantidadeDeLancesDevolvida = itemDoLeilao.quantidadeDeLances();
        assertEquals(1, quantidadeDeLancesDevolvida);
    }

    @Test
    public void naoDeve_AdicionarLance_QuandoUsuarioDerCincoLances() {
        itemDoLeilao.propoe(new Lance(douglas, 500.00));
        final Usuario carol = new Usuario("Carol");
        itemDoLeilao.propoe(new Lance(carol, 600.00));
        itemDoLeilao.propoe(new Lance(douglas, 700.00));
        itemDoLeilao.propoe(new Lance(carol, 800.00));
        itemDoLeilao.propoe(new Lance(douglas, 900.00));
        itemDoLeilao.propoe(new Lance(carol, 1000.00));
        itemDoLeilao.propoe(new Lance(douglas, 1100.00));
        itemDoLeilao.propoe(new Lance(carol, 1200.00));
        itemDoLeilao.propoe(new Lance(douglas, 1300.00));
        itemDoLeilao.propoe(new Lance(carol, 1400.00));

        itemDoLeilao.propoe(new Lance(douglas, 1500.00));
        itemDoLeilao.propoe(new Lance(carol, 1600.00));

        int quantidadeDeLancesDevolvida = itemDoLeilao.quantidadeDeLances();
        assertEquals(10, quantidadeDeLancesDevolvida);
    }
}