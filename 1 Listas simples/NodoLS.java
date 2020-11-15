/*
	Algoritmos y estructuras de datos
	Requisito de examen parcial 2
	NodoLS.java (Nodo de lista simple)

	Martín Arce
	Matricula: 1103883
	Grupo: 241
*/

public class NodoLS<T> {
	// ------------------------------ ATRIBUTOS ---------------------------------
	
	private T valor;
	private NodoLS<T> siguiente;
	
	// ---------------------------- CONSTRUCTORES -------------------------------
	
	public NodoLS(){
		this.valor = null;
		this.siguiente = null;
	}
	
	public NodoLS(T valor){
		this.valor = valor;
		this.siguiente = null;
	}
	
	public NodoLS(T valor, NodoLS<T> siguiente){
		this.valor = valor;
		this.siguiente = siguiente;
	}
	
	// ------------------------------- MÉTODOS ----------------------------------
	
	// GETERS Y SETTERS
	
	public void setValor(T valor){this.valor = valor;}
	public void setSiguiente(NodoLS<T> siguiente){this.siguiente = siguiente;}
	public T getValor(){return valor;}
	public NodoLS<T> getSiguiente(){return siguiente;}
	
	// MÉTODOS AUXILIARES
	
	@Override
	public String toString(){return valor.toString();}
	
}