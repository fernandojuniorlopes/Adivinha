package com.example.adivinha;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class EstatisticasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatisticas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mostrarEstatisticas();
    }

    private void mostrarEstatisticas() {
        Intent intent = getIntent();

        int minTentativas = intent.getIntExtra(App.MIN_TENTATIVAS, -1);
        int maxTentativas = intent.getIntExtra(App.MAX_TENTATIVAS, -1);
        int totalTentativas = intent.getIntExtra(App.TOTAL_TENTATIVAS, -1);

        int jogos = intent.getIntExtra(App.JOGOS, -1);
        int vitorias = intent.getIntExtra(App.VITORIAS, -1);
        int derrotas = intent.getIntExtra(App.DERROTAS, -1);

        TextView textViewMinimo = (TextView)findViewById(R.id.textViewMinimo);
        TextView textViewMaximo = (TextView)findViewById(R.id.textViewMaximo);
        TextView textViewMedia = (TextView)findViewById(R.id.textViewMedia);


        TextView textViewJogos = (TextView)findViewById(R.id.textViewJogos);
        TextView textViewVitorias = (TextView)findViewById(R.id.textViewVitorias);
        TextView textViewDerrotas = (TextView)findViewById(R.id.textViewDerrotas);

        textViewMinimo.setText(String.valueOf(minTentativas));
        textViewMaximo.setText(String.valueOf(maxTentativas));
        textViewMedia.setText(String.format("%.2f",(double)totalTentativas/jogos));

        textViewJogos.setText(String.valueOf(jogos));
        textViewVitorias.setText(String.valueOf(vitorias));
        textViewDerrotas.setText(String.valueOf(derrotas));




    }

}
