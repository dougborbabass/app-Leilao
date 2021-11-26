package br.com.douglas.app_leilao.matchers;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import br.com.douglas.app_leilao.R;
import br.com.douglas.app_leilao.formatter.FormatadorDeMoeda;

public class ViewMatcher {
    public static Matcher<? super View> apareceLeilaoNaPosicao(final int posicao,
                                                               final String descricaoEsperada,
                                                               final double maiorLanceEsperado) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            private final FormatadorDeMoeda formatadorDeMoeda = new FormatadorDeMoeda();
            private final String maiorLanceEsperadoFormatado = formatadorDeMoeda.formata(maiorLanceEsperado);

            @Override
            public void describeTo(Description description) {
                description.appendText("View com descrição ")
                        .appendValue(descricaoEsperada)
                        .appendText(", maior lance ")
                        .appendValue(maiorLanceEsperadoFormatado)
                        .appendText(" na posicao ")
                        .appendValue(posicao).appendText(" não foi encontrada.");
            }

            @Override
            protected boolean matchesSafely(RecyclerView item) {
                RecyclerView.ViewHolder viewHolderDevolvido = item.findViewHolderForAdapterPosition(posicao);
                if (viewHolderDevolvido == null) {
                    throw new IndexOutOfBoundsException("View do ViewHolder na posição " + posicao + " não foi encontrado");
                }
                View viewDoViewHolder = viewHolderDevolvido.itemView;
                boolean temDescricaoEsperada = verificaDescricaoEsperada(viewDoViewHolder);
                boolean temMaiorLanceEsperado = verificaMaiorLanceEsperado(viewDoViewHolder);

                return temDescricaoEsperada && temMaiorLanceEsperado;
            }

            private boolean verificaMaiorLanceEsperado(View viewDoViewHolder) {
                TextView textViewMaiorLance = viewDoViewHolder.findViewById(R.id.item_leilao_maior_lance);
                return textViewMaiorLance.getText().toString()
                        .equals(maiorLanceEsperadoFormatado);
            }

            private boolean verificaDescricaoEsperada(View viewDoViewHolder) {
                TextView textViewDescricao = viewDoViewHolder.findViewById(R.id.item_leilao_descricao);
                return textViewDescricao.getText().toString()
                        .equals(descricaoEsperada);
            }
        };
    }
}
