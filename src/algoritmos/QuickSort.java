package algoritmos;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;


public class QuickSort extends Algoritmo{

	private long totalCC = 0;
	private long totalMR = 0;

	public long[] sort(Long[] array) {

		Instant inicio = Instant.now();

		sort(array, 0, array.length - 1);

		Instant fim = Instant.now();

		Duration duration = Duration.between(inicio, fim);
		long tempoGasto = duration.toMillis();

		return new long[] { tempoGasto, totalCC, totalMR };
	}

	public void sort(Long[] array, int i, int f) {
		int e = i, d = f;

		Random random = new Random();
		long pivot = array[e + random.nextInt(d - e + 1)]; 
		totalMR++;

		while (e <= d) {

			while (e <= f && array[e] < pivot) { 
				totalCC++;
				e++;
			}
			while (d >= i && array[d] > pivot) { 
				totalCC++;
				d--;
			}

			if (e <= d) {
				long aux = array[e];
				array[e] = array[d];
				array[d] = aux;

				totalMR += 3; 

				e++;
				d--;
			}
		}

		if (e < f)
			sort(array, e, f);
		if (d > i)
			sort(array, i, d);
	}
}
