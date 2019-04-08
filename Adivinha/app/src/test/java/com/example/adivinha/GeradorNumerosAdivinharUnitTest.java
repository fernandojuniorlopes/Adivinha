package com.example.adivinha;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GeradorNumerosAdivinharUnitTest {
    @Test
    public void getProximoNumeroAdivinhar_isCorrect() {
        GeradorNumerosAdivinhar gerador = new GeradorNumerosAdivinhar();

        int min = 10;
        int max = 1;
        for (int i = 0; i < 100000; i++) {
            int numero = gerador.getProximoNumeroAdivinhar();

            assertTrue(numero >= 1 && numero <= 10);
            if(numero < min) min = numero;
            if(numero > max) max = numero;
        }
        assertEquals(1, min);
        assertEquals(10, max);
    }
}