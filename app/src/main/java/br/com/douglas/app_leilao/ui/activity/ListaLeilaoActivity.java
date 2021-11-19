package br.com.douglas.app_leilao.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import br.com.douglas.app_leilao.R;
import br.com.douglas.app_leilao.api.retrofit.client.LeilaoWebClient;
import br.com.douglas.app_leilao.model.Leilao;
import br.com.douglas.app_leilao.ui.AtualizadorDeLeiloes;
import br.com.douglas.app_leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

public class ListaLeilaoActivity extends AppCompatActivity {

    private static final String MENSAGEM_AVISO_FALHA_AO_CARREGAR_LEILOES = "Não foi possível carregar os leilões";
    private static final String TITULO_APPBAR = "Leilões";
    private final LeilaoWebClient client = new LeilaoWebClient();
    private final AtualizadorDeLeiloes atualizadorDeLeiloes = new AtualizadorDeLeiloes();
    private ListaLeilaoAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_leilao);
        getSupportActionBar().setTitle(TITULO_APPBAR);
        configuraListaLeiloes();
    }

    private void configuraListaLeiloes() {
        configuraAdapter();
        configuraRecyclerView();
    }

    private void configuraRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.lista_leilao_recyclerview);
        recyclerView.setAdapter(adapter);
    }

    private void configuraAdapter() {
        adapter = new ListaLeilaoAdapter(this);
        adapter.setOnItemClickListener(this::vaiParaTelaDeLances);
    }

    private void vaiParaTelaDeLances(Leilao leilao) {
        Intent vaiParaLancesLeilao = new Intent(
                ListaLeilaoActivity.this,
                LancesLeilaoActivity.class);
        vaiParaLancesLeilao.putExtra("leilao", leilao);
        startActivity(vaiParaLancesLeilao);
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizadorDeLeiloes.buscaLeiloes(adapter, client, mensagem -> mostraMsgDeFalha());
    }

    public void mostraMsgDeFalha() {
        Toast.makeText(this,
                MENSAGEM_AVISO_FALHA_AO_CARREGAR_LEILOES,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lista_leilao_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.lista_leilao_menu_usuarios) {
            Intent vaiParaListaDeUsuarios = new Intent(this, ListaUsuarioActivity.class);
            startActivity(vaiParaListaDeUsuarios);
        }
        return super.onOptionsItemSelected(item);
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