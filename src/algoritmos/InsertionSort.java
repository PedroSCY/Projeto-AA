package algoritmos;

import java.time.Duration;
import java.time.Instant;


public class InsertionSort extends Algoritmo {

	public long[] sort(Long[] array) {

		long totalCC = 0;
		long totalMR = 0;
		Instant inicio = Instant.now();

		for (int i = 0; i < array.length - 1; i++) {
			int j = i + 1;

			long number = array[j]; 

			
			totalMR++; 

			
			totalCC++; 

			while (j > 0 && number < array[j - 1]) {

				totalCC++; 

				totalMR++; 
				array[j] = array[j - 1];
				j--;
			}

			array[j] = number;
			totalMR++; 
		}

		// Pegar o intante inicio e fim, usar a Classe Duration para recuperar o intermedio entre o inicio e fim,
		// Depois transformar  em milisegundos.
		Instant fim = Instant.now();

		Duration duration = Duration.between(inicio, fim);
		long tempoGasto = duration.toMillis();

		return new long[] { tempoGasto, totalCC, totalMR };
	}
}
