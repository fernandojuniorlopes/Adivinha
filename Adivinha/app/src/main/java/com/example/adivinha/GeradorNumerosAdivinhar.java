package com.example.adivinha;
import java.util.Random;

public class GeradorNumerosAdivinhar {
    private Random random = new Random();

    public int getProximoNumeroAdivinhar(){
        return random.nextInt(10); //Erro: está a gerar um número entre 0 e 9
    }
}
