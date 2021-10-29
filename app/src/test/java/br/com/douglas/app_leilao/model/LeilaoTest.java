package br.com.douglas.app_leilao.model;


import org.junit.Test;
import static org.junit.Assert.*;

public class LeilaoTest {

    private final Leilao itemDoLeilao = new Leilao("Console");
    private final Usuario douglas = new Usuario("Douglas");
    private final Usuario carol = new Usuario("Carol");

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
        itemDoLeilao.propoe(new Lance(douglas, 200.0));
        double maiorLanceDevolvido = itemDoLeilao.getMaiorLance();
        assertEquals(200.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deveDevolveMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemDescrescente() {
        itemDoLeilao.propoe(new Lance(douglas, 500.0));
        itemDoLeilao.propoe(new Lance(carol, 100.0));
        double maiorLanceDevolvido = itemDoLeilao.getMaiorLance();
        assertEquals(500.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deveDevolveMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        itemDoLeilao.propoe(new Lance(douglas, 7000.0));
        itemDoLeilao.propoe(new Lance(carol, 10000.0));
        double maiorLanceDevolvido = itemDoLeilao.getMaiorLance();
        assertEquals(10000.0, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deveDevolveMenorLanceQuandoRecebeApenasUmLance() {
        itemDoLeilao.propoe(new Lance(douglas, 200.0));
        double menorLanceDevolvido = itemDoLeilao.getMenorLance();
        assertEquals(200.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deveDevolveMenorLanceQuandoRecebeMaisDeUmLanceEmOrdemDescrescente() {
        itemDoLeilao.propoe(new Lance(douglas, 500.0));
        itemDoLeilao.propoe(new Lance(carol, 100.0));
        double menorLanceDevolvido = itemDoLeilao.getMenorLance();
        assertEquals(100.0, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deveDevolveMenorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        itemDoLeilao.propoe(new Lance(douglas, 7000.0));
        itemDoLeilao.propoe(new Lance(carol, 10000.0));
        double menorLanceDevolvido = itemDoLeilao.getMenorLance();
        assertEquals(7000.0, menorLanceDevolvido, 0.0001);
    }
}