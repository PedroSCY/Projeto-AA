package algoritmos;

import java.time.Duration;
import java.time.Instant;


public class SelectionSort  extends Algoritmo{

	public long[] sort(Long[] array) {

		long totalCC = 0;
		long totalMR = 0;

		Instant inicio = Instant.now();

		for (int i = 0; i < array.length; i++) {
			int indexSmaller = i; 

			for (int j = i + 1; j < array.length; j++) {

				totalCC++;
				if (array[j] < array[indexSmaller]) 
					indexSmaller = j;
			}

			long aux = array[i];
			array[i] = array[indexSmaller];
			array[indexSmaller] = aux;

			totalMR += 3; 
		}

		// Pegar o intante inicio e fim, usar a Classe Duration para recuperar o intermedio entre o inicio e fim,
		// Depois transformar  em milisegundos.
		Instant fim = Instant.now();

		Duration duration = Duration.between(inicio, fim);
		long durationInMillisseconds = duration.toMillis();
		return new long[] { durationInMillisseconds, totalCC, totalMR };
	}
}
