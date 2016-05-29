package br.com.ernando.aspirador;

import java.util.ArrayList;
import java.util.List;

public class Posicao {

	private char limpo;
	boolean visitado;
	private int x, y;
	
	private List<Posicao> adjacentes = new ArrayList<Posicao>();
	public Posicao(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public boolean isVisitado() {
		return visitado;
	}
	
	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public char getLimpo() {
		return limpo;
	}

	public void setLimpo(char limpo) {
		this.limpo = limpo;
	}

	public List<Posicao> getAdjacentes() {
		return adjacentes;
	}

	public void setAdjacentes(List<Posicao> adjacentes) {
		this.adjacentes = adjacentes;
	}
	
	
}
