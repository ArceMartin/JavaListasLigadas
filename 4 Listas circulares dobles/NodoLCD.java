/**
 * Algoritmos y estructuras de datos
 * Requisito de examen parcial 2
 * NodoLCD.java (Nodo de lista circular)

 * @author Martín Arce
 * Matricula: 1103883
 * Grupo: 241
 */
public class NodoLCD<T> {
	// ------------------------------ ATRIBUTOS ---------------------------------
	
	private T valor;
	private NodoLCD<T> anterior;
	private NodoLCD<T> siguiente;
	
	// ---------------------------- CONSTRUCTORES -------------------------------
	
	public NodoLCD() {
		this.valor = null;
		this.anterior = null;
		this.siguiente = null;
	}
	
	public NodoLCD(T valor) {
		this.valor = valor;
		this.anterior = null;
		this.siguiente = null;
	}
	
	public NodoLCD(T valor, NodoLCD<T> anterior, NodoLCD<T> siguiente) {
		this.valor = valor;
		this.anterior = anterior;
		this.siguiente = siguiente;
	}
	
	// ------------------------------- MÉTODOS ----------------------------------
	
	// GETERS Y SETTERS
	
	public void setValor(T valor) { this.valor = valor; }
	public void setAnterior(NodoLCD<T> anterior) { this.anterior = anterior; }
	public void setSiguiente(NodoLCD<T> siguiente) { this.siguiente = siguiente; }
	
	public T getValor() { return valor; }
	public NodoLCD<T> getAnterior() { return anterior; }
	public NodoLCD<T> getSiguiente() { return siguiente; }
	
	// MÉTODOS AUXILIARES
	
	@Override
	public String toString() { return valor.toString(); }
	
}