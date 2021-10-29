package br.com.douglas.app_leilao.model;


import org.junit.Test;
import static org.junit.Assert.*;

public class LeilaoTest {

    @Test
    public void deveDevolverDescricaoQuandoRecebeDescricao() {
        // criar cenário de testes
        Leilao console = new Leilao("Console");

        // executar ação esperada
        String descricaoDevolvida = console.getDescricao();

        // testar resultado esperado
        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void deveDevolveMaiorLanceLanceQuandoRecebeApenasUmLance() {
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Douglas"), 200.0));
        double maiorLanceDevolvido = console.getMaiorLance();
        assertEquals(200.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deveDevolveMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemDescrescente() {
        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Douglas"), 500.0));
        computador.propoe(new Lance(new Usuario("Carol"), 100.0));
        double maiorLanceDevolvido = computador.getMaiorLance();
        assertEquals(500.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deveDevolveMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Douglas"), 7000.0));
        carro.propoe(new Lance(new Usuario("Carol"), 10000.0));
        double maiorLanceDevolvido = carro.getMaiorLance();
        assertEquals(10000.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deveDevolveMenorLanceQuandoRecebeApenasUmLance() {
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Douglas"), 200.0));
        double menorLanceDevolvido = console.getMenorLance();
        assertEquals(200.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deveDevolveMenorLanceQuandoRecebeMaisDeUmLanceEmOrdemDescrescente() {
        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Douglas"), 500.0));
        computador.propoe(new Lance(new Usuario("Carol"), 100.0));
        double menorLanceDevolvido = computador.getMenorLance();
        assertEquals(100.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deveDevolveMenorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Douglas"), 7000.0));
        carro.propoe(new Lance(new Usuario("Carol"), 10000.0));
        double menorLanceDevolvido = carro.getMenorLance();
        assertEquals(7000.0, menorLanceDevolvido, 0.0001);
    }
}