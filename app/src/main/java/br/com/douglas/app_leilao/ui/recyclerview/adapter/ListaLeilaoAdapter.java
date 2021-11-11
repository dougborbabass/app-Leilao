package br.com.douglas.app_leilao.ui.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.douglas.app_leilao.R;
import br.com.douglas.app_leilao.formatter.FormatadorDeMoeda;
import br.com.douglas.app_leilao.model.Leilao;

public class ListaLeilaoAdapter extends RecyclerView.Adapter<ListaLeilaoAdapter.ViewHolder> {

    private final List<Leilao> leiloes;
    private final Context context;
    private final FormatadorDeMoeda formatadorDeMoeda;
    private OnItemClickListener onItemClickListener;

    public ListaLeilaoAdapter(Context context) {
        this.context = context;
        this.leiloes = new ArrayList<>();;
        this.formatadorDeMoeda = new FormatadorDeMoeda();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_leilao, parent, false);
        return new ViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Leilao leilao = pegaLeilaoPorPosicao(position);
        holder.vincula(leilao);
    }

    @Override
    public int getItemCount() {
        return leiloes.size();
    }

    public void atualiza(List<Leilao> leiloes) {
        this.leiloes.clear();
        this.leiloes.addAll(leiloes);
        atualizaLista();
    }

    public void atualizaLista() {
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView descricao;
        private final TextView maiorLance;
        private Leilao leilao;

        ViewHolder(View itemView) {
            super(itemView);
            descricao = itemView.findViewById(R.id.item_leilao_descricao);
            maiorLance = itemView.findViewById(R.id.item_leilao_maior_lance);
            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(leilao));
        }

        void vincula(Leilao leilao) {
            this.leilao = leilao;
            descricao.setText(leilao.getDescricao());
            maiorLance.setText(formatadorDeMoeda.formata(leilao.getMaiorLance()));
        }

    }

    public Leilao pegaLeilaoPorPosicao(int posicao) {
        return this.leiloes.get(posicao);
    }

    public interface OnItemClickListener {
        void onItemClick(Leilao leilao);
    }
}
