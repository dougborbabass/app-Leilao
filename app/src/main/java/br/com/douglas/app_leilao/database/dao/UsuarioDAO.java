package br.com.douglas.app_leilao.database.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.douglas.app_leilao.database.DatabaseHelper;
import br.com.douglas.app_leilao.database.contrato.UsuarioContrato;
import br.com.douglas.app_leilao.model.Usuario;

import static br.com.douglas.app_leilao.database.contrato.UsuarioContrato.CHAVE_NOME;
import static br.com.douglas.app_leilao.database.contrato.UsuarioContrato.TABELA_NOME;

public class UsuarioDAO extends DatabaseHelper {

    public UsuarioDAO(Context context) {
        super(context);
    }

    public Usuario salva(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = preencheValores(usuario);
        long id = db.insert(TABELA_NOME, null, valores);
        return new Usuario(id, usuario.getNome());
    }

    public List<Usuario> todos() {
        String selecionaTodos = "SELECT * FROM " + UsuarioContrato.TABELA_NOME;

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(selecionaTodos, null);

        return armazenaUsuariosEmLista(c);
    }

    @NonNull
    private List<Usuario> armazenaUsuariosEmLista(Cursor c) {
        List<Usuario> usuarios = new ArrayList<>();
        while (c.moveToNext()) {
            Usuario usuarioRetornado = preencheUsuario(c);
            usuarios.add(usuarioRetornado);
        }
        return usuarios;
    }

    @NonNull
    private ContentValues preencheValores(Usuario usuario) {
        ContentValues valores = new ContentValues();
        valores.put(CHAVE_NOME, usuario.getNome());
        return valores;
    }

    @NonNull
    private Usuario preencheUsuario(Cursor c) {
        @SuppressLint("Range") Long id = c.getLong(c.getColumnIndex(UsuarioContrato._ID));
        @SuppressLint("Range") String nome = c.getString(c.getColumnIndex(CHAVE_NOME));
        return new Usuario(id, nome);
    }
}