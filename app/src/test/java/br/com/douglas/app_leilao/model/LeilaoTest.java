package br.com.douglas.app_leilao.model;


import org.junit.Test;
import static org.junit.Assert.*;

public class LeilaoTest {

    @Test
    public void getDescricaoQuandoRecebeDescricaoDevolveDescricao() {
        // criar cenário de testes
        Leilao console = new Leilao("Console");

        // executar ação esperada
        String descricaoDevolvida = console.getDescricao();

        // testar resultado esperado
        assertEquals("Console", descricaoDevolvida);
    }

    //[nome do método][Estado do teste][resultado esperado]
    @Test
    public void getMaiorLanceQuandoRecebeApenasUmLanceDevolveMaiorLance() {
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Douglas"), 200.0));
        double maiorLanceDevolvidoDoConsole = console.getMaiorLance();
        assertEquals(200.0, maiorLanceDevolvidoDoConsole, 0.0001);
    }

    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemDescrescenteDevolveMaiorLance() {
        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Douglas"), 500.0));
        computador.propoe(new Lance(new Usuario("Carol"), 100.0));
        double maiorLanceDevolvidoDoComputador = computador.getMaiorLance();
        assertEquals(500.0, maiorLanceDevolvidoDoComputador, 0.0001);
    }

    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescenteDevolveMaiorLance() {
        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Douglas"), 7000.0));
        carro.propoe(new Lance(new Usuario("Carol"), 10000.0));
        double maiorLanceDevolvidoDoCarro = carro.getMaiorLance();
        assertEquals(10000.0, maiorLanceDevolvidoDoCarro, 0.0001);
    }
}