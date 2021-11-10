package br.com.douglas.app_leilao.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputLayout;

import br.com.douglas.app_leilao.R;
import br.com.douglas.app_leilao.model.Usuario;

public class NovoUsuarioDialog {

    private static final String TITULO = "Novo usuário";
    private static final String DESCRICAO_BOTAO_POSITIVO = "Adicionar";
    private final Context context;
    private final UsuarioCriadoListener listener;

    public NovoUsuarioDialog(Context context,
                             UsuarioCriadoListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void mostra() {
        final View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.form_usuario, null, false);
        TextInputLayout textInputNome = viewCriada.findViewById(R.id.form_usuario_nome);

        EditText campoNome = textInputNome.getEditText();

        new AlertDialog.Builder(context)
                .setTitle(TITULO)
                .setView(viewCriada)
                .setPositiveButton(DESCRICAO_BOTAO_POSITIVO,
                        criaNovoUsuarioListener(campoNome))
                .show();
    }

    @NonNull
    private DialogInterface.OnClickListener criaNovoUsuarioListener(final EditText campoNome) {
        return (dialogInterface, i) -> {
            String nome = campoNome.getText().toString();
            Usuario novoUsuario = new Usuario(nome);
            listener.criado(novoUsuario);
        };
    }

    public interface UsuarioCriadoListener {
        void criado(Usuario usuario);
    }
}
