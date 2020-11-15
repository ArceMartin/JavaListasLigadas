/**
 * Algoritmos y estructuras de datos
 * Requisito de examen parcial 2
 * ListaDoble.java

 * @author Martín Arce
 * Matricula: 1103883
 * Grupo: 241
 */
public class ListaDoble<T> {
	// ------------------------------ ATRIBUTOS ---------------------------------
	
	private NodoLD<T> inicio;
	
	// ---------------------------- CONSTRUCTORES -------------------------------
	
	public ListaDoble() {
		inicio = null;
	}
	
	public ListaDoble(NodoLD<T> inicio){
		this.inicio = inicio;
	}
	
	public ListaDoble(T[] valores){
		inicio = null;
		for(T valor : valores){
			this.insertaFinal(valor);
		}
	}
	
	
	// ------------------------------- MÉTODOS ----------------------------------
	
	// GETERS Y SETTERS
	
	void setInicio(NodoLD<T> inicio) { this.inicio = inicio; }
	NodoLD<T> getInicio() { return inicio; }
	
	// MÉTODOS DE INSERCIÓN Y ELIMINACIÓN DE ELEMENTOS
	
	public void insertaInicio(T valor) {
		if(inicio == null){
			inicio = new NodoLD<>(valor);
		}
		else
			inicio = new NodoLD<>(valor,null, inicio);
		return;
	}
	
	public void insertaFinal(T valor) {
		if(inicio == null){
			inicio = new NodoLD<>(valor);
			return;
		}
		NodoLD<T> aux = inicio;
		while (aux.getSiguiente() != null) { aux = aux.getSiguiente(); }
		aux.setSiguiente(new NodoLD<T>(valor, aux, null));
	}

	public T eliminaInicio() throws ExcepcionListaDoble{
		if(inicio == null)
			throw new ExcepcionListaDoble("Lista vacia!");
		T valor = inicio.getValor();
		inicio = inicio.getSiguiente();
		if(inicio!=null)
			inicio.setAnterior(null);
		return valor;
	}
	
	public T eliminaFinal() throws ExcepcionListaDoble {
		// lista vacia
		if(inicio == null)
			throw new ExcepcionListaDoble("Lista vacia!");
		
		T valor;
		NodoLD<T> aux = inicio;
		while (aux.getSiguiente()!=null) { aux = aux.getSiguiente(); }
		valor = aux.getValor();
		if(aux==inicio){
			inicio = null;
		}
		else{
			aux.getAnterior().setSiguiente(null);
		}
		return valor;
	}
	
	// MÉTODOS REQUISITO
	
	/**
	 * Método recursivo que regresa un String con la representación de la lista
	 * Se manda llamar como mostarRecursivo(this.inicio);
	 * @param nodo Es el nodo desde donde empezamos a crear la cadena
	 * @return Cadena representación de la lista
	 */
	public String mostrarRecursivo(NodoLD<T> nodo) {
		// Lista vacia
		if(nodo == null)
			return "";
		if(nodo.getSiguiente() == null)
			return nodo.toString();
		else
			return nodo.toString() + " <-> " + mostrarRecursivo(nodo.getSiguiente());
	}
	
	/**
	 * Método que elimina el primer nodo con el valor dado
	 * Si no encuentra algun nodo con el valor x arroja una excepción
	 * @param x Valor a eliminar en la lista
	 * @return Valor eliminado
	 * @throws ExcepcionListaDoble si no encuentra el valor dado
	 */
	public T eliminaX(T x) throws ExcepcionListaDoble {
		// lista vacia
		if(inicio == null)
			throw new ExcepcionListaDoble("Lista vacia");
		
		NodoLD<T> aux = inicio;
		
		while (aux!=null) {
			if(aux.getValor() == x){
				if(aux == inicio){
					eliminaInicio();
				}
				else if(aux.getSiguiente()==null){
					eliminaFinal();
				}
				else{
					aux.getAnterior().setSiguiente(aux.getSiguiente());
				}
				return x;
			}
			aux = aux.getSiguiente();
		}
		throw new ExcepcionListaDoble("Valor no encontrado");
	}

	/**
	 * Método que regresa el indice del nodo que contiene el valor dado
	 * @param x Valor buscado
	 * @return Indice del valor en la lista, -1 si no se encuentra
	 */
	public int buscar(T x) {
		int i=0;
		NodoLD<T> it = inicio;
		while (it!=null) {
			if(it.getValor() == x){
				return i;
			}
			i++;
			it = it.getSiguiente();
		}
		return -1;
	}
	
	/**
	 * Método que elimina el nodo en la posicion dada
	 * @param pos es el indice del nodo que se quiere eliminar
	 * @return El valor guardado por el nodo eliminado
	 * @throws ExcepcionListaDoble en caso de lista vacia o indice invalido
	 */
	public T eliminaPosicion(int pos) throws ExcepcionListaDoble {
		// Lista vacia
		if(inicio == null) { throw new ExcepcionListaDoble("Lista vacia"); }
		
		int i,n = cuentaNodos();
		NodoLD<T> it = inicio;
		T valor;
		
		// Verifica parametro pos
		if(pos<0 || n-1 < pos) {  throw new ExcepcionListaDoble("Indice invalido"); }
		
		// Busca nodo a eliminar
		for (i=0; i<pos; i++) { it = it.getSiguiente(); }
		
		// Extrae el valor
		valor = it.getValor();
		
		// Elimina el nodo de la lista
		if(it == inicio){ // El nodo a eliminar es inicio
			inicio = it.getSiguiente();
			if(inicio!=null){
				inicio.setAnterior(null);
			}
		}
		else{ // el nodo a eliminar no es inicio
			it.getAnterior().setSiguiente(it.getSiguiente());
			if(it.getSiguiente() != null) {
				it.getSiguiente().setAnterior(it.getAnterior());
			}
		}
		
		return valor;
	}
	
	/**
	 * Método que ordena la lista. 
	 * Ordena una lista si el tipo T es string float o int.
	 */
	public void ordenaLista() {
		int n = cuentaNodos();
		if(n<2){ return; }
		if(!(inicio.getValor() instanceof Integer) 
			&& !(inicio.getValor() instanceof Float)
			&& !(inicio.getValor() instanceof String)){
			return ;
		}
		NodoLD<T> it;
		boolean bandera;
		do{
			bandera = false;
			for (it = inicio.getSiguiente(); it!=null; it = it.getSiguiente()) {
				if(
					((it.getValor() instanceof Integer) && ((Integer)(it.getAnterior().getValor()) > (Integer)it.getValor()))
					|| ((it.getValor() instanceof Float) && ((float)(it.getAnterior().getValor()) > (float)it.getValor()))
					|| ((it.getValor() instanceof String) && ((String)it.getAnterior().getValor()).compareTo((String)it.getValor())>0)
				)
				{
					intercambia(it.getAnterior(), it);
					bandera = true;
				}
			}
		}
		while(bandera);
	}
	
	/**
	 * Método que inserta un elemento dado en una posición especifica
	 * @param dato es el valor que se quiere agregar la lista
	 * @param pos es la posición en la que se quiere guardar
	 * @throws ExcepcionListaSimple en caso de indice invalido (acepta valores 
	 * entre 0 y la cantidad de nodos)
	 */
	public void insertaEnPosicion(T valor, int pos) throws ExcepcionListaDoble {
		int n = cuentaNodos(),i;
		if(pos<0 || n<pos){ throw  new ExcepcionListaDoble("Indice invalido");}
		
		NodoLD<T> it = inicio;
		
		if(pos==0){ insertaInicio(valor); }
		else if(pos == n){ insertaFinal(valor); }
		else{
			for (i=0; i<pos-1; i++) { it = it.getSiguiente(); }
			it.setSiguiente( new NodoLD<T>(valor, it, it.getSiguiente()) );
		}
		return;
	}
	
	// MÉTODOS AUXILIARES
	
	/**
	 * Método toString regresa cadena de caracteres que representa el objeto ListaDoble
	 * @return cadena de caracteres representación de la lista
	 */
	@Override
	public String toString() {
		if(inicio == null)
			return "-[]-";
		NodoLD<T> it = inicio;
		String salida = "-";
		while (it!=null) {
			salida = salida + "[" + it + "]-";
			it = it.getSiguiente();
		}
		return salida;
	}
	
	/**
	 * Método que cuenta cuantos nodos tiene la lista
	 * @return la cantidad de nodos que hay en la lista
	 */
	public int cuentaNodos(){
		int i=0;
		NodoLD<T> it = inicio;
		while (it!= null) {
			i++;
			it = it.getSiguiente();
		}
		return i;
	}
	
	/**
	 * Método que intercambia los valores guardados de dos nodos.
	 * @param  a nodo a intercambiar valor
	 * @param  b nodo a intercambiar valor
	 */
	public void intercambia(NodoLD<T> a, NodoLD<T> b) {
		if(a == null || b == null) { return; }
		T aux = a.getValor();
		a.setValor(b.getValor());
		b.setValor(aux);
		return;
	}
	
}