/*
	Algoritmos y estructuras de datos
	Requisito de examen parcial 2
	ListaSimple.java

	Martín Arce
	Matricula: 1103883
	Grupo: 241
*/


public class ListaSimple<T> {
	// ------------------------------ ATRIBUTOS ---------------------------------
	
	private NodoLS<T> inicio;
	
	// ---------------------------- CONSTRUCTORES -------------------------------
	
	public ListaSimple() { inicio = null; }
	
	public ListaSimple(T valor){ inicio = new NodoLS<T>(valor); }
	
	public ListaSimple(T[] valores){
		inicio = null;
		for(T v : valores){
			this.insertaFinal(v);
		}
	}
	
	// ------------------------------- MÉTODOS ----------------------------------
	
	// GETERS Y SETTERS
	
	public void setInicio(NodoLS<T> inicio) { this.inicio = inicio; }
	public NodoLS<T> getInicio(){ return inicio; }
	
	// MÉTODOS DE INSERCIÓN Y ELIMINACIÓN DE ELEMENTOS
	
	public void insertaInicio(T valor) {
		if(inicio == null){
			inicio = new NodoLS<>(valor);
			return;
		}
		inicio = new NodoLS<>(valor, inicio);
	}
	
	public void insertaFinal(T valor) {
		if(inicio==null){
			inicio = new NodoLS<>(valor);
			return;
		}
		NodoLS<T> aux = inicio;
		while (aux.getSiguiente() != null) { aux = aux.getSiguiente(); }
		aux.setSiguiente(new NodoLS<T>(valor));
	}
	
	public T eliminaInicio() throws ExcepcionListaSimple {
		if(inicio == null)
			throw new ExcepcionListaSimple("Lista vacia!");
		T aux = inicio.getValor();
		inicio = inicio.getSiguiente();
		return aux;
	}
	
	public T eliminaFinal() throws ExcepcionListaSimple {
		// lista vacia
		if(inicio == null)
			throw new ExcepcionListaSimple("Lista vacia!");
		
		T valor;
		NodoLS<T> aux;
		// lista con un solo elemento
		if(inicio.getSiguiente() == null){
			valor = inicio.getValor();
			inicio = null;
		}
		// lista con más de un elemento
		else{
			aux = inicio;
			while (aux.getSiguiente().getSiguiente() != null) {
				aux = aux.getSiguiente();
			}
			valor = aux.getSiguiente().getValor();
			aux.setSiguiente(null);
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
	public String mostrarRecursivo(NodoLS<T> nodo) {
		if(nodo == null)
			return "";
		if(nodo.getSiguiente()==null)
			return nodo.toString();
		else{
			return nodo.toString() + "->"+mostrarRecursivo(nodo.getSiguiente());
		}
	}
	
	/**
	 * Método que elimina el primer nodo con el valor dado
	 * Si no encuentra algun nodo con el valor x arroja una excepción
	 * @param x Valor a eliminar en la lista
	 * @return Valor eliminado
	 * @throws ExcepcionListaSimple si no encuentra el valor dado
	 */
	public T eliminaX(T x) throws  ExcepcionListaSimple{
		// lista vacia
		if(inicio == null){
			throw new ExcepcionListaSimple("Lista vacia!");
		}
		NodoLS<T> aux = inicio;
		
		// lista con un solo elemento
		if(inicio.getSiguiente() == null){
			if(inicio.getValor() == x){ // x en inicio
				return eliminaInicio();
			}
			else{ //x no en inicio
				throw new ExcepcionListaSimple("Valor no encontrado!");
			}
		}
		
		// lista con más de un elemento
		else{
			if(inicio.getValor() == x){ // x en inicio
				return eliminaInicio();
			}
			else{ // x no en inicio
				while(aux.getSiguiente().getSiguiente() != null){
					
					
					if(aux.getSiguiente().getValor() == x){ // x en primer nodo que no es inicio
						aux.setSiguiente(aux.getSiguiente().getSiguiente()); // elimina nodo intermedio
						return x;
					}
					aux = aux.getSiguiente();
				}
				if(aux.getSiguiente().getValor() == x){ // falta revisar último nodo
					return eliminaFinal();
				}
				else {
					throw new ExcepcionListaSimple("Valor no encontrado!");
				}
			}
		}
	}
	
	/**
	 * Método que regresa el indice del nodo que contiene el valor dado
	 * @param x Valor buscado
	 * @return Indice del valor en la lista, -1 si no se encuentra
	 */
	public int buscar(T x) {
		int i = 0;
		NodoLS<T> it = inicio;
		while(it != null){
			if(it.getValor() == x){ return i; }
			i++;
			it = it.getSiguiente();
		}
		return -1;
	}
	
	/**
	 * Método que elimina el nodo en la posicion dada
	 * @param pos es el indice del nodo que se quiere eliminar
	 * @return El valor guardado por el nodo eliminado
	 * @throws ExcepcionListaSimple en caso de lista vacia o indice invalido
	 */
	public T eliminaPosicion(int pos) throws ExcepcionListaSimple{
		NodoLS<T> it = inicio;
		int n,i;
		
		// lista vacía
		if(inicio == null) { throw new ExcepcionListaSimple("Lista vacia!"); }
		
		// contamos cuantos nodos tiene la lista y lo guardamos en n
		n=cuentaNodos();
		
		// Sabiendo cuantos nodos tiene, podemos verificar si pos es valido (esta entre 0 y n-1)
		if(pos < 0 || n-1 < pos) { throw  new ExcepcionListaSimple("Indice invalido"); }
		
		// Casos de los extremos de la lista
		if(pos==0) { return eliminaInicio(); }

		// Si es valido, usamos un iterador para encontrar el nodo en la posicion pos-1
		it = inicio;
		for (i=0; i<pos-1; i++) { it = it.getSiguiente(); }
		
		// copiamos el valor del nodo pos
		T valor = it.getSiguiente().getValor();
		
		// ponemos el siguiente de este nodo igual a el siguiente del nodo pos
		it.setSiguiente(it.getSiguiente().getSiguiente());
		
		// regresamos el valor del nodo pos
		return valor;
	}
	
	
	/**
	 * Método que ordena la lista
	 */
	public void ordenaLista() {
		int n = cuentaNodos();
		
		// Lista vacia y de un nodo ya esta ordenada
		if(n<2){ return; }
		
		// Verifica que sea de un tipo ordenable
		if(
			   !(inicio.getValor() instanceof Integer)
			&& !(inicio.getValor() instanceof Float)
			&& !(inicio.getValor() instanceof String)
			){ return ; }
		
		NodoLS<T> it;
		boolean bandera;
		
		// implementa método de la burbuja para ordenar
		do{
			bandera = false;
			for (it = inicio; it.getSiguiente()!=null; it = it.getSiguiente()) {
				if(
					((it.getValor() instanceof Integer) && ((Integer)(it.getValor()) > (Integer)it.getSiguiente().getValor()))
					|| ((it.getValor() instanceof Float) && ((float)(it.getValor()) > (float)it.getSiguiente().getValor()))
					|| ((it.getValor() instanceof String) && ((String)it.getValor()).compareTo((String)it.getSiguiente().getValor())>0)
				)
				{
					intercambia(it, it.getSiguiente());
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
	public void insertaEnPosicion(T dato, int pos) throws ExcepcionListaSimple {
		int n = cuentaNodos(),i;
		if (pos<0 || n < pos) { throw new ExcepcionListaSimple("Indice invalido"); }
		
		NodoLS<T> it = inicio;
		
		if(pos==0){
			insertaInicio(dato);
			return;
		}
		if(pos==n){
			insertaFinal(dato);
			return;
		}
		
		for (i=0; i<pos-1; i++) { it = it.getSiguiente(); }
		it.setSiguiente( new NodoLS<T>(dato, it.getSiguiente()) );
		return;
	}
	
	// MÉTODOS AUXILIARES
	
	/**
	 * Método toString regresa cadena de caracteres que representa el objeto ListaSimple
	 */
	@Override
	public String toString() {
		if(inicio == null){
			return "[]-";
		}
		NodoLS<T> aux = inicio;
		String salida = "";
		while (aux!= null) {
				salida = salida + "[" + aux + "]-";
				aux = aux.getSiguiente();
		}
		return salida;
	}
	
	/**
	 * Método que cuenta la cantidad de nodos de forma iterativa
	 * @return La cantidad de nodos en la lista
	 */
	public int cuentaNodos(){
		int i=0;
		NodoLS<T> it = inicio;
		while (it!=null) {
			i++;
			it = it.getSiguiente();
		}
		return i;
	}
	
	/**
	 * Método que cuenta la cantidad de nodos de forma recursiva
	 * @param nodo es el nodo desde donde esta llamada empieza a contar
	 * @return La cantidad de nodos de la lista contando desde nodo argumento
	 */
	public int cuentaNodosRecursivo(NodoLS<T> nodo){
		// Lista vacía
		if(inicio == null)
			return 0;
		
		// Caso base
		if(nodo.getSiguiente() == null)
			return 1;
		// Paso recursivo
		else{
			return 1 + cuentaNodosRecursivo(nodo.getSiguiente());
		}
	}
	
	/**
	 * Método que intercambia los valores guardados de dos nodos.
	 * @param  a nodo a intercambiar valor
	 * @param  b nodo a intercambiar valor
	 */
	public void intercambia(NodoLS<T> a, NodoLS<T> b) {
		if(a == null || b == null) { return; }
		T aux = a.getValor();
		a.setValor(b.getValor());
		b.setValor(aux);
		return;
	}
	
}