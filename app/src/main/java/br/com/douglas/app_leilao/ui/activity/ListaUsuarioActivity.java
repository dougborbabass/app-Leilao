package br.com.douglas.app_leilao.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.douglas.app_leilao.R;
import br.com.douglas.app_leilao.database.dao.UsuarioDAO;
import br.com.douglas.app_leilao.ui.AtualizadorDeUsuario;
import br.com.douglas.app_leilao.ui.dialog.NovoUsuarioDialog;
import br.com.douglas.app_leilao.ui.recyclerview.adapter.ListaUsuarioAdapter;

public class ListaUsuarioActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Usuários";
    private UsuarioDAO dao;
    private ListaUsuarioAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuario);
        getSupportActionBar().setTitle(TITULO_APPBAR);
        inicializaAtributos();
        configuraRecyclerView();
        configuraFab();
    }

    private void inicializaAtributos() {
        dao = new UsuarioDAO(this);
        adapter = new ListaUsuarioAdapter(this);
    }

    private void configuraFab() {
        FloatingActionButton fabAdicionaUsuario = findViewById(R.id.lista_usuario_fab_adiciona);
        fabAdicionaUsuario.setOnClickListener(view -> mostraDialogAdicionaNovoUsuario());
    }

    private void configuraRecyclerView() {
        recyclerView = findViewById(R.id.lista_usuario_recyclerview);
        recyclerView.setAdapter(adapter);
        adapter.adiciona(dao.todos());
    }

    private void mostraDialogAdicionaNovoUsuario() {
        NovoUsuarioDialog dialog = new NovoUsuarioDialog(
                this,
                usuario -> new AtualizadorDeUsuario(
                        dao,
                        adapter,
                        recyclerView)
                        .salva(usuario));
        dialog.mostra();
    }
}
