package algoritmos;

import java.time.Duration;
import java.time.Instant;


public class BubbleSort extends Algoritmo{

	public long[] sort(Long[] array) {

		long totalCC = 0;
		long totalMR = 0;

		Instant inicio = Instant.now();

		boolean hasChanged = true;

		while (hasChanged) {
			hasChanged = false;

			int j = 0;

			for (int i = 0; i < array.length - 1 - j; i++) {

				totalCC++;

				if (array[i] > array[i + 1]) {
					long aux = array[i];
					array[i] = array[i + 1];
					array[i + 1] = aux;

					totalMR += 3;

					hasChanged = true;
				}
			}
			j++;
		}

		// Pegar o intante inicio e fim, usar a Classe Duration para recuperar o intermedio entre o inicio e fim,
		// Depois transformar  em milisegundos.
		Instant fim = Instant.now();

		Duration intervalo = Duration.between(inicio, fim);
		long TempoGasto = intervalo.toMillis();

		return new long[] { TempoGasto, totalCC, totalMR };
	}
}
