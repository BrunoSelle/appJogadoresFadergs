package com.selle.appjogares;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

// Adapter serve para receber os dados 'crus' e formatá-los no layout desejado
public class JogadorAdapter extends BaseAdapter {

    private Context context;
    private List<Jogador> jogadores;
    private LayoutInflater inflater;

    public JogadorAdapter(Context context, List<Jogador> listaJogadores) {
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

        Jogador j = jogadores.get(i);
        item.tvNome.setText(j.nome);
        item.tvNumero.setText( String.valueOf(j.numero));
        if ( i % 2 == 0){
            item.layoutFundo.setBackgroundColor(Color.LTGRAY);
        }else {
            item.layoutFundo.setBackgroundColor(Color.rgb(255,255,255));
        }

        return view;
    }



// Classe de suporte: classe dentro de outra classe
    private class SuportItem {
        TextView tvNome, tvNumero;
        LinearLayout layoutFundo;
    }

}
