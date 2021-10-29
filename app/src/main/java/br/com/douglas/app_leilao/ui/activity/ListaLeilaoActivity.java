package br.com.douglas.app_leilao.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.douglas.app_leilao.R;
import br.com.douglas.app_leilao.model.Lance;
import br.com.douglas.app_leilao.model.Leilao;
import br.com.douglas.app_leilao.model.Usuario;
import br.com.douglas.app_leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

public class ListaLeilaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_leilao);
        ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(this, leiloesDeExemplo());
        RecyclerView recyclerView = findViewById(R.id.lista_leilao_recyclerview);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(leilao -> {
            Intent vaiParaLancesLeilao = new Intent(ListaLeilaoActivity.this, LancesLeilaoActivity.class);
            vaiParaLancesLeilao.putExtra("leilao", leilao);
            startActivity(vaiParaLancesLeilao);
        });
    }

    private List<Leilao> leiloesDeExemplo() {
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Douglas"), 200));
        console.propoe(new Lance(new Usuario("Carol"), 300));

        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Douglas"), 1000));
        computador.propoe(new Lance(new Usuario("Carol"), 3000));
        computador.propoe(new Lance(new Usuario("Carol"), 4000));

        Leilao carro = new Leilao("Computador");
        carro.propoe(new Lance(new Usuario("Douglas"), 1));
        carro.propoe(new Lance(new Usuario("Carol"), 300));
        carro.propoe(new Lance(new Usuario("Carol"), 4000000));

        return new ArrayList<>(Arrays.asList(
                console,
                computador,
                carro
        ));
    }
}