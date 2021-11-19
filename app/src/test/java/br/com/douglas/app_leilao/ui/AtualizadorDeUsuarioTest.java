package br.com.douglas.app_leilao.ui;

import androidx.recyclerview.widget.RecyclerView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.douglas.app_leilao.database.dao.UsuarioDAO;
import br.com.douglas.app_leilao.model.Usuario;
import br.com.douglas.app_leilao.ui.recyclerview.adapter.ListaUsuarioAdapter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AtualizadorDeUsuarioTest {

    @Mock
    private UsuarioDAO dao;
    @Mock
    private ListaUsuarioAdapter adapter;
    @Mock
    private RecyclerView recyclerview;

    @Test
    public void deve_AtualizarListaDeUsuarios_QuandoSalvarUsario() {
        AtualizadorDeUsuario atualizadorDeUsuario = new AtualizadorDeUsuario(dao, adapter, recyclerview);

        Usuario douglas = new Usuario("Douglas");
        when(dao.salva(douglas)).thenReturn(new Usuario(1, "Douglas"));
        when(adapter.getItemCount()).thenReturn(1);

        atualizadorDeUsuario.salva(douglas);

        verify(dao).salva(new Usuario("Douglas"));
        verify(adapter).adiciona(new Usuario(1, "Douglas"));
        verify(recyclerview).smoothScrollToPosition(0);
    }

}