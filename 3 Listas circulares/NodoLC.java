/**
 * Algoritmos y estructuras de datos
 * Requisito de examen parcial 2
 * NodoLC.java (Nodo de lista circular)

 * @author Martín Arce
 * Matricula: 1103883
 * Grupo: 241
 */
public class NodoLC<T> {
	// ------------------------------ ATRIBUTOS ---------------------------------
	
	private T valor;
	private NodoLC<T> siguiente;
	
	// ---------------------------- CONSTRUCTORES -------------------------------
	
	public NodoLC() {
		this.valor = null;
		this.siguiente = null;
	}
	
	public NodoLC(T valor) {
		this.valor = valor;
		this.siguiente = null;
	}
	
	public NodoLC(T valor, NodoLC<T> siguiente) {
		this.valor = valor;
		this.siguiente = siguiente;
	}
	
	// ------------------------------- MÉTODOS ----------------------------------
	
	// GETERS Y SETTERS
	
	public void setValor(T valor) { this.valor = valor; }
	public void setSiguiente(NodoLC<T> siguiente) { this.siguiente = siguiente; }
	
	public T getValor() { return valor; }
	public NodoLC<T> getSiguiente() { return siguiente; }
	
	// MÉTODOS AUXILIARES
	
	@Override
	public String toString() { return valor.toString(); }
	
}