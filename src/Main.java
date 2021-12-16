import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import algoritmos.Algoritmo;
import algoritmos.BubbleSort;
import algoritmos.InsertionSort;
import algoritmos.MergeSort;
import algoritmos.QuickSort;
import algoritmos.SelectionSort;
import gerador.GeradorDeSequencias;


public class Main {

	private static Long[] arrayOrdenado = null;
	private static Long[] arrayInverso = null;
	private static Long[][] arraysQuaseOrdenados = new Long[14][];
	private static Long[][] arraysAleatorios = new Long[14][];
	private static Algoritmo algoritmoOrdenacao = null;

	public static void main(String[] args) {

		int[] valores = {10,100,1000,10000,100000,1000000};
		
		for(int i : valores) {
			
			int tamanhoDaSequencia = i;
			gerarArrays(tamanhoDaSequencia);
			
			escolherAlgoritmo("BubbleSort");
			executarPrograma("BubbleSort_Tamanho_"+tamanhoDaSequencia);
			
			escolherAlgoritmo("InsertionSort");
			executarPrograma("InsertionSort_Tamanho_"+tamanhoDaSequencia);
			
			escolherAlgoritmo("MergeSort");
			executarPrograma("MergeSort_Tamanho_"+tamanhoDaSequencia);
			
			escolherAlgoritmo("QuickSort");
			executarPrograma("QuickSort_Tamanho_"+tamanhoDaSequencia);
			
			escolherAlgoritmo("SelectionSort");
			executarPrograma("SelectionSort_Tamanho_"+tamanhoDaSequencia);
		}
		

	}

	public static void executarPrograma(String name) {

		ArrayList<long[]> inteiros = new ArrayList<long[]>();
		
		
		long[] resultado;

		Long[] arrayCopia;

		arrayCopia = Arrays.copyOf(arrayOrdenado, arrayOrdenado.length);
		resultado = algoritmoOrdenacao.sort(arrayCopia);


		inteiros.add(resultado);
		
		arrayCopia = Arrays.copyOf(arrayInverso, arrayInverso.length);
		resultado = algoritmoOrdenacao.sort(arrayCopia);

		inteiros.add(resultado);
		
		long x = 0;
		long y = 0;
		long z = 0;
		
		for (int i = 0; i < 14; i++) {
			arrayCopia = Arrays.copyOf(arraysQuaseOrdenados[i], arraysQuaseOrdenados[i].length);
			resultado = algoritmoOrdenacao.sort(arrayCopia);

			x += resultado[0];
			y += resultado[1];
			z += resultado[2];
			
		}

		resultado[0] = x/14;
		resultado[1] = y/14;
		resultado[2] = z/14;
		inteiros.add(resultado);
		
		x = 0;
		y = 0;
		z = 0;
		
		for (int i = 0; i < 14; i++) {
			arrayCopia = Arrays.copyOf(arraysAleatorios[i], arraysAleatorios[i].length);
			resultado = algoritmoOrdenacao.sort(arrayCopia);

			x += resultado[0];
			y += resultado[1];
			z += resultado[2];
		}

		resultado[0] = x/14;
		resultado[1] = y/14;
		resultado[2] = z/14;
		inteiros.add(resultado);
		
		x = 0;
		y = 0;
		z = 0;
		
		gerarCSV(inteiros, name);
	}

	public static void escolherAlgoritmo(String nomeAlgoritmo) {

		if (nomeAlgoritmo.equals("BubbleSort")) {

			algoritmoOrdenacao = new BubbleSort();

		} else if (nomeAlgoritmo.equals("InsertionSort")) {

			algoritmoOrdenacao = new InsertionSort();

		} else if (nomeAlgoritmo.equals("MergeSort")) {

			algoritmoOrdenacao = new MergeSort();

		} else if (nomeAlgoritmo.equals("QuickSort")) {

			algoritmoOrdenacao = new QuickSort();

		} else if (nomeAlgoritmo.equals("SelectionSort")) {

			algoritmoOrdenacao = new SelectionSort();
		}
	}

	public static void gerarArrays(int tamanho) {

		arrayOrdenado = GeradorDeSequencias.gerarSequenciaOrdenada(tamanho);
		arrayInverso = GeradorDeSequencias.gerarSequenciaInversamenteOrdenada(tamanho);

		for (int i = 0; i < 14; i++) {
			arraysQuaseOrdenados[i] = GeradorDeSequencias.gerarSequenciaQuaseOrdenada(tamanho);
		}

		for (int i = 0; i < 14; i++) {
			arraysAleatorios[i] = GeradorDeSequencias.gerarSequenciaAleatoria(tamanho);
		}

	}
	
	public static void gerarCSV(ArrayList<long[]> array, String name) {
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(name);

		sheet.setDefaultColumnWidth(15);
		sheet.setDefaultRowHeight((short) 400);

		int rownum = 0;
		int cellnum = 0;
		Cell cell;
		Row row;

		HSSFDataFormat numberFormat = workbook.createDataFormat();

		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		CellStyle textStyle = workbook.createCellStyle();
		textStyle.setAlignment(CellStyle.ALIGN_CENTER);
		textStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		CellStyle numberStyle = workbook.createCellStyle();
		numberStyle.setDataFormat(numberFormat.getFormat("#,##0.00"));
		numberStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		row = sheet.createRow(rownum++);
		cell = row.createCell(cellnum++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue("TempML");

		cell = row.createCell(cellnum++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue("NComparacoesChaves");

		cell = row.createCell(cellnum++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue("NMovimentacoes");

		for (long[] i : array) {
			row = sheet.createRow(rownum++);
			cellnum = 0;

			cell = row.createCell(cellnum++);
			cell.setCellStyle(textStyle);
			cell.setCellValue(i[0]);

			cell = row.createCell(cellnum++);
			cell.setCellStyle(textStyle);
			cell.setCellValue(i[1]);

			cell = row.createCell(cellnum++);
			cell.setCellStyle(numberStyle);
			cell.setCellValue(i[2]);
		}

		try {

			FileOutputStream out = new FileOutputStream(new File("C:/Users/plgon/Desktop/Algoritmos/"+name+".xls"));
			workbook.write(out);
			out.close();
			workbook.close();
			System.out.println("Success!!");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	

}
