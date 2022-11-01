package com.selle.appjogares;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class JogadorAdapter extends BaseAdapter {

    private Context context;
    private List<Jogador> jogadores;
    private LayoutInflater inflater;

    public JogadorAdapter(Context context, List<Jogador> listaJogadores, ) {
        this.context = context;
        this.jogadores = listaJogadores;
        this.inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return jogadores.size();
    }

    @Override
    public Object getItem(int i) {
        return jogadores.get(i);
    }

    @Override
    public long getItemId(int i) {
        return jogadores.get(i).id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SuportItem item;
        if(view == null){
            view = inflater.inflate(R.layout.layout_item_list, null);

            item = new SuportItem();
            item.tvNome = view.findViewById(R.id.tvNomeJogador);
            item.tvNumero = view.findViewById(R.id.tvNumeroJogador);
            item.layoutFundo = view.findViewById(R.id.llTela);
            view.setTag (item);

        }else{

            item = (SuportItem) view.getTag();
        }


        return view;
    }
// preencher os campos com os valores dos jogadores
    // pedir para o professor explicar novamente o que significa o inflate!


// Classe de suporte: classe dentro de outra classe
    private class SuportItem {
        TextView tvNome, tvNumero;
        LinearLayout layoutFundo;
    }

}
