package algoritmos;

import java.time.Duration;
import java.time.Instant;

public class MergeSort extends Algoritmo{

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
		if (i < f) {
			int middle = (i + f) / 2;
			sort(array, i, middle);
			sort(array, middle + 1, f);
			merge(array, i, middle, f);
		}
	}

	public void merge(Long array[], int left, int middle, int right) {
		int n1 = middle - left + 1;
		int n2 = right - middle;

		long Left[] = new long[n1];
		long Right[] = new long[n2];

		for (int i = 0; i < n1; ++i) {
			Left[i] = array[left + i]; 
			totalMR++;
		}

		for (int j = 0; j < n2; ++j) {
			Right[j] = array[middle + 1 + j]; 
			totalMR++;
		}

		int i = 0, j = 0;

		int k = left;
		while (i < n1 && j < n2) {

			totalCC++;

			if (Left[i] <= Right[j]) { 
				array[k] = Left[i];
				i++;
			} else {
				array[k] = Right[j];
				j++;
			}

			totalMR++; 
			k++;
		}

		while (i < n1) {

			array[k] = Left[i]; 
			totalMR++;

			i++;
			k++;
		}

		while (j < n2) {

			array[k] = Right[j]; 
			totalMR++;
			j++;
			k++;
		}
	}
}
