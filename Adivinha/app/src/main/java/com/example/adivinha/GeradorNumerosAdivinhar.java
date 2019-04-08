package com.example.adivinha;
import java.util.Random;

/**
 * Esta class permite gerar números a adivinhar entre 1 e 10
 */
public class GeradorNumerosAdivinhar {
    private Random random = new Random();

    /**
     * Devolve o próximo a adivinhar
     * @return um número entre 1 e 10
     */
    public int getProximoNumeroAdivinhar(){
        return random.nextInt(10) + 1; //Erro: está a gerar um número entre 0 e 9
    }
}
