package br.com.ernando.aspirador;

import javax.swing.JOptionPane;

/**
 * Aspirador aleatório.
 * @author Ernando
 * @version 2.0
 */
public class Principal {
	
	private static final Integer INICIO = 0;
	private static final char SUJO = '1';
	private static final char ASPIRADOR = '#';
	private static final char LIMPO = '0';
	
	private static int tamanho;
	private static Integer ultimo;

	Integer i = INICIO;
	Integer j = INICIO;
	String saida = " ";
	
	static int contador = 0;
	static int execucoes = 0;
	
	private Posicao[][] ambiente; 
	
	public static void main(String[] args) {
		Principal p = inicializar();
		p.aspirar();
		finalizar(p);
	}

	private static void finalizar(Principal p) {
		System.out.println();
		System.out.println("Anbiente após aspirado.");
		System.out.println(p.saida);
		System.out.println(contador+" posições foram aspiradas.\n");
	}

	private static Principal inicializar() {
		Principal p = new Principal();
		tamanho = Integer.parseInt(JOptionPane.showInputDialog("Infome o tamanho da área a ser aspirada"));
		ultimo = tamanho - 1;
		p.ambiente = new Posicao[tamanho][tamanho];
		p.carregarArea();
		return p;
	}

	public void carregarArea(){
		for(int i = INICIO; i < tamanho; i++){
			for(int j = INICIO; j < tamanho; j ++){
				Posicao posicao = new Posicao(i,j);
				posicao.setLimpo((byte) (Math.random() * 2) == 0?LIMPO:SUJO);
				ambiente[i][j] = posicao;
			}
		}
		for(int i = INICIO; i < tamanho; i++){
			for(int j = INICIO; j < tamanho; j ++){
				Posicao posicao = ambiente[i][j];
				montarAdjacencias(i, j, posicao);
			}
		}
		System.out.println("Ambiente a ser aspirado.");
		mostrarArea();
	}

	private void montarAdjacencias(int i, int j, Posicao posicao) {
		iMaiorQueZero(i, j, posicao);
		iIgualZero(i, j, posicao);
	}

	private void iMaiorQueZero(int i, int j, Posicao posicao) {
		if((i - 1) >=0){
			posicao.getAdjacentes().add(ambiente[i-1][j]);
			if((j - 1) >=0){
				posicao.getAdjacentes().add(ambiente[i-1][j-1]);
				posicao.getAdjacentes().add(ambiente[i][j-1]);
			}
			if((j + 1) < tamanho){
				posicao.getAdjacentes().add(ambiente[i][j+1]);
				posicao.getAdjacentes().add(ambiente[i-1][j+1]);
			}
		}
	}

	private void iIgualZero(int i, int j, Posicao posicao) {
		if((i + 1) < tamanho){
			posicao.getAdjacentes().add(ambiente[i+1][j]);
			if((j - 1) >=0){
				posicao.getAdjacentes().add(ambiente[i+1][j-1]);
				posicao.getAdjacentes().add(ambiente[i][j-1]);
			}
			if((j + 1) < tamanho){
				posicao.getAdjacentes().add(ambiente[i+1][j+1]);
				posicao.getAdjacentes().add(ambiente[i][j+1]);
			}
		}
	}

	public void aspirar() {
		int loop = tamanho * tamanho;
		int k = 0;
		int i = 0;
		int j = 0;
		observaLimpa(i, j);
		while (k < loop) {
			int n = (int) (Math.random() * ambiente[i][i].getAdjacentes().size());
			Posicao p = ambiente[i][i].getAdjacentes().get(n);
			observaLimpa(p.getX(), p.getY());
			if(p.isVisitado()){
				p.setX((int) (Math.random() * tamanho));
				p.setY((int) (Math.random() * tamanho));
			}
			k++; 
		}
	}
	
	private void observaLimpa(int i, int j) {
		if (ambiente[i][j].getLimpo() == SUJO) {
			ambiente[i][j].setLimpo(ASPIRADOR);
			ambiente[i][j].setVisitado(true);
			saida = " ";
			mostrarArea();
			ambiente[i][j].setLimpo(LIMPO);
			contador++;
		}
		for (Posicao e : ambiente[i][j].getAdjacentes()) {
			if (e != null && e.getLimpo() == SUJO) {
				e.setLimpo(ASPIRADOR);
				e.setVisitado(true);
				saida = " ";
				mostrarArea();
				e.setLimpo(LIMPO);
				contador++;
			}
		}
	}
	
	public void mostrarArea(){
		getBorda();
		for(int i = INICIO; i < tamanho; i++){
			saida += "|";
			for(int j = INICIO; j < tamanho; j ++){
				saida += ambiente[i][j].getLimpo();
				if(j == ultimo){
					saida += "| \n";
				}
			}
		}
		getBorda();
		JOptionPane.showMessageDialog(null, saida);
		System.out.println(saida);
	}

	private void getBorda() {
		for(int x = 0; x < tamanho; x++){
			saida += "-";
		}
		saida += "\n";
	}
}
