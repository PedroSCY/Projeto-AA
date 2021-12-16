package gerador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;

public class GeradorDeSequencias {

	private static Set<Long> arranjos;

	/**
	 * Metodo para gerar vetor de sequencia ordenada.
	 * @param tamanho define o tamanho do vetor.
	 * @return retorna o vetor preenchido e com o tamanho defino por parametro.
	 */
	public static Long[] gerarSequenciaOrdenada(int tamanho) {

		preencherArranjo(tamanho);

		Long[] arranjo = new Long[tamanho];
		arranjo = arranjos.toArray(arranjo);

		return arranjo;
	}

	/**
	 * Metodo para gerar vetor de sequencia inversamente ordenada.
	 * @param tamanho define o tamanho do vetor.
	 * @return retorna o vetor preenchido e com o tamanho defino por parametro.
	 */
	public static Long[] gerarSequenciaInversamenteOrdenada(int tamanho) {

		Long[] arranjo = gerarSequenciaOrdenada(tamanho);

		Long[] arranjoInverso = arranjo.clone();

		int contador = 0;

		for (int i = arranjo.length - 1; i >= 0; i--) {

			arranjo[contador++] = arranjoInverso[i];
		}

		return arranjo;
	}

	/**
	 * Metodo para gerar vetor de sequencia quase ordenada.
	 * @param tamanho define o tamanho do vetor.
	 * @return retorna o vetor preenchido e com o tamanho defino por parametro.
	 */
	public static Long[] gerarSequenciaQuaseOrdenada(int tamanho) {

		Long[] arranjo = gerarSequenciaOrdenada(tamanho);


		Long[] arranjoOrdenado = Arrays.copyOfRange(arranjo, 0, tamanho / 2);
		Long[] arranjoDesordenado = Arrays.copyOfRange(arranjo, tamanho / 2, tamanho);

		Collections.shuffle(Arrays.asList(arranjoDesordenado));

		arranjo = ArrayUtils.addAll(arranjoOrdenado, arranjoDesordenado);

		return arranjo;
	}

	/**
	 * Metodo para gerar vetor de sequencia aleatoria.
	 * @param tamanho define o tamanho do vetor.
	 * @return retorna o vetor preenchido e com o tamanho defino por parametro.
	 */
	public static Long[] gerarSequenciaAleatoria(int tamanho) {

		preencherArranjo(tamanho);

		List<Long> sequencia = new ArrayList<Long>(arranjos);

		Collections.shuffle(sequencia);

		Long[] arranjo = new Long[tamanho];
		arranjo = sequencia.toArray(arranjo);

		return arranjo;
	}

	/**
	 * metodo que preenche o set arranjos com o numero de valores passado por parametro.
	 * @param tamanho tamanho do vetor.
	 */
	private static void preencherArranjo(int tamanho) {

		arranjos = new LinkedHashSet<Long>();

		for (int i = 0; i < tamanho; i++) {

			arranjos.add((long) i);
		}
	}
}
