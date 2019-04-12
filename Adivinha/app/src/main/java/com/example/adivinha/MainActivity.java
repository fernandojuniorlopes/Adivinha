package com.example.adivinha;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static int TENTATIVAS_APOS_QUAIS_PERDE = 5;

    private GeradorNumerosAdivinhar geradorNumeros;
    private int numeroAdivinhar;
    private int tentativas;


    private int minTentativasGanhar = TENTATIVAS_APOS_QUAIS_PERDE;
    private int maxTentativasGanhar = 0;
    private int totalTentativasTodosJogos = 0;
    private int jogos = 0;
    private int vitorias = 0;
    private int derrotas = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adivinha();
            }
        });

        geradorNumeros = new GeradorNumerosAdivinhar();
        if(savedInstanceState==null){
            novoJogo();
        }else{
            //todo: repor o estado da atividade
        }
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //todo: guardar estado da atividade
        super.onSaveInstanceState(outState);
    }

    private void novoJogo() {
        numeroAdivinhar = geradorNumeros.getProximoNumeroAdivinhar();
        Toast.makeText(this, "Jogo iniciado", Toast.LENGTH_LONG).show();
        tentativas = 0;
    }

    private void adivinha() {
        EditText editTextNumero = findViewById(R.id.EditTextNumero);
        String textoNumero = editTextNumero.getText().toString();

        if(textoNumero.isEmpty()){
            editTextNumero.setError("Introduza um numero entre 1 e 10!");
            editTextNumero.requestFocus();
            return;
        }
        int numero;
        try {
            numero = Integer.parseInt(textoNumero);
        } catch (NumberFormatException e) {
            editTextNumero.setError("Número inválido! Introduza um numero entre 1 e 10!");
            editTextNumero.requestFocus();
            return;
        }

        if(numero < 1 || numero > 10){
            editTextNumero.setError("Número inválido! Introduza um numero entre 1 e 10!");
            editTextNumero.requestFocus();
            return;
        }

        verificaAcertou(numero);

    }

    private void verificaAcertou(int numero) {
        tentativas++;
        if (numero == numeroAdivinhar) {
            acertou();
            return;
        }
        if(tentativas >= TENTATIVAS_APOS_QUAIS_PERDE){
            perdeu();
            return;
        } else if (numero < numeroAdivinhar) {
            Toast.makeText(this, "O número que estou a pensar é maior. Tente novamente", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "O número que estou a pensar é menor. Tente novamente", Toast.LENGTH_LONG).show();
    }
    }

    private void perdeu() {
        totalTentativasTodosJogos += tentativas;
        jogos++;
        vitorias++;

        String mensagem = getString (R.string.tentarnovo);
        perguntaSeQuerJogarOutraVez(R.string.perdeu, mensagem);
    }

    private void acertou(){
        totalTentativasTodosJogos += tentativas;
        jogos++;
        vitorias++;
        if(tentativas < minTentativasGanhar){
            minTentativasGanhar = tentativas;
        }
        if(tentativas > maxTentativasGanhar){
            maxTentativasGanhar = tentativas;
        }
        String mensagem = getString(R.string.acertouaofim)+" "+tentativas+" "+" "+ (getString(R.string.tentativas))+" "+(getString(R.string.tentarnovo));
        perguntaSeQuerJogarOutraVez(R.string.acertou, mensagem);
    }

    private void perguntaSeQuerJogarOutraVez(int recursoString, String mensagem) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle(recursoString);
        dialogBuilder.setMessage(mensagem);

        dialogBuilder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                novoJogo();
            }
        });

        dialogBuilder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        dialogBuilder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Adivinha - versão 0.1", Toast.LENGTH_LONG).show();
            return true;
        }else if(id == R.id.action_novo){
            actionNovo();
            return true;
        }else if(id == R.id.action_estatisticas){
            actionEstatisticas();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void actionEstatisticas() {
        Intent intent = new Intent(this, EstatisticasActivity.class);

        intent.putExtra(App.MIN_TENTATIVAS, minTentativasGanhar);
        intent.putExtra(App.MAX_TENTATIVAS, maxTentativasGanhar);
        intent.putExtra(App.TOTAL_TENTATIVAS, totalTentativasTodosJogos);

        intent.putExtra(App.JOGOS, jogos);
        intent.putExtra(App.VITORIAS, vitorias);
        intent.putExtra(App.DERROTAS, derrotas);


        startActivity(intent);

    }

    private void actionNovo() {
    }

}
