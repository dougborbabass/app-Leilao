package br.com.douglas.app_leilao.model;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {

    private final long id;
    private final String nome;

    public Usuario(String nome) {
        this.id = 0L;
        this.nome = nome;
    }

    public Usuario(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return Objects.equals(nome, usuario.nome);
    }

    @Override
    public int hashCode() {
        return nome != null ? nome.hashCode() : 0;
    }

    public String toString() {
        return "(" + id + ") " + nome;
    }

    public String getNome() {
        return nome;
    }
}
