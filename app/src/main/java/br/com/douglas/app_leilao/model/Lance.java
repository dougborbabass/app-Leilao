package br.com.douglas.app_leilao.model;

import java.io.Serializable;

public class Lance implements Serializable, Comparable {

    private final Usuario usuario;
    private final double valor;

    public Lance(Usuario usuario, double valor) {
        this.usuario = usuario;
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public int compareTo(Object o) {
        Lance lance = (Lance) o;
        return Double.compare(lance.getValor(), valor);
    }

    public Usuario getUsuario() {
        return usuario;
    }
}

