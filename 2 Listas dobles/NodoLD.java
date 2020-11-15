/**
 * Algoritmos y estructuras de datos
 * Requisito de examen parcial 2
 * NodoLD.java (Nodo de lista doble)

 * @author Martín Arce
 * Matricula: 1103883
 * Grupo: 241
*/
public class NodoLD<T> {
	// ------------------------------ ATRIBUTOS ---------------------------------
	
	private T valor;
	private NodoLD<T> anterior;
	private NodoLD<T> siguiente;
	
	// ---------------------------- CONSTRUCTORES -------------------------------
	
	public NodoLD() {
		this.valor = null;
		this.anterior = null;
		this.siguiente = null;
	}
	
	public NodoLD(T valor) {
		this.valor = valor;
		this.anterior = null;
		this.siguiente = null;
	}
	
	public NodoLD(T valor, NodoLD<T> anterior, NodoLD<T> siguiente) {
		this.valor = valor;
		this.anterior = anterior;
		this.siguiente = siguiente;
	}
	
	// ------------------------------- MÉTODOS ----------------------------------
	
	// GETERS Y SETTERS
	
	public void setValor(T valor) { this.valor = valor; }
	public void setAnterior(NodoLD<T> anterior) { this.anterior = anterior; }
	public void setSiguiente(NodoLD<T> siguiente) { this.siguiente = siguiente; }
	public T getValor() { return valor; }
	public NodoLD<T> getAnterior() { return anterior; }
	public NodoLD<T> getSiguiente() { return siguiente; }
	
	// MÉTODOS AUXILIARES
	
	@Override
	public String toString() { return valor.toString(); }
	
}