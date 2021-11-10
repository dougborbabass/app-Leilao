package br.com.douglas.app_leilao.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.douglas.app_leilao.R;
import br.com.douglas.app_leilao.api.retrofit.client.LeilaoWebClient;
import br.com.douglas.app_leilao.api.retrofit.client.RespostaListener;
import br.com.douglas.app_leilao.model.Leilao;
import br.com.douglas.app_leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

public class ListaLeilaoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Leilões";
    private static final String MENSAGEM_AVISO_FALHA_AO_CARREGAR_LEILOES = "Não foi possível carregar os leilões";
    private final LeilaoWebClient client = new LeilaoWebClient();
    private ListaLeilaoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_leilao);
        configuraListaLeiloes();
    }

    private void configuraListaLeiloes() {
        configuraAdapter();
        configuraReyclerView();
    }

    private void configuraAdapter() {
        adapter = new ListaLeilaoAdapter(this); //leiloesDeExemplo()
        adapter.setOnItemClickListener(leilao -> {
            vaiParaTelaDeLances(leilao);
        });
    }

    private void configuraReyclerView() {
        RecyclerView recyclerView = findViewById(R.id.lista_leilao_recyclerview);
        recyclerView.setAdapter(adapter);
    }

    private void vaiParaTelaDeLances(Leilao leilao) {
        Intent vaiParaLancesLeilao = new Intent(ListaLeilaoActivity.this, LancesLeilaoActivity.class);
        vaiParaLancesLeilao.putExtra("leilao", leilao);
        startActivity(vaiParaLancesLeilao);
    }

    @Override
    protected void onResume() {
        super.onResume();
        client.todos(new RespostaListener<List<Leilao>>() {
            @Override
            public void sucesso(List<Leilao> leiloes) {
                adapter.atualiza(leiloes);
            }

            @Override
            public void falha(String mensagem) {
                Toast.makeText(ListaLeilaoActivity.this,
                        MENSAGEM_AVISO_FALHA_AO_CARREGAR_LEILOES,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private List<Leilao> leiloesDeExemplo() {
//        Leilao console = new Leilao("Console");
//        console.propoe(new Lance(new Usuario("Alex"), 200.0));
//        console.propoe(new Lance(new Usuario("Fran"), 300.0));
//
//        Leilao computador = new Leilao("Computador");
//        computador.propoe(new Lance(new Usuario("João"), 1000.0));
//
//        Leilao carro = new Leilao("Carro");
//        carro.propoe(new Lance(new Usuario("Joana"), 10000.0));
//        carro.propoe(new Lance(new Usuario("Mario"), 15000.0));
//        carro.propoe(new Lance(new Usuario("Ana"), 17000.0));
//        return new ArrayList<>(Arrays.asList(
//                console,
//                computador,
//                carro
//        ));
//    }
}