/**
 * Algoritmos y estructuras de datos
 * Requisito de examen parcial 2
 * ListaCircular.java

 * @author Martín Arce
 * Matricula: 1103883
 * Grupo: 241
 */
public class ListaCircular<T> {
	// ------------------------------ ATRIBUTOS ---------------------------------
	
	private NodoLC<T> inicio;
	private NodoLC<T> fin;
	
	// ---------------------------- CONSTRUCTORES -------------------------------
	
	public ListaCircular() {
		inicio = null;
		fin = null;
	}
	
	public ListaCircular(NodoLC<T> inicio){
		this.inicio = inicio;
	}
	
	public ListaCircular(NodoLC<T> inicio, NodoLC<T> fin){
		this.inicio = inicio;
		this.fin = fin;
	}
	
	public ListaCircular(T[] valores){
		for(T valor : valores){
			this.insertaFinal(valor);
		}
	}
	
	
	// ------------------------------- MÉTODOS ----------------------------------
	
	// GETERS Y SETTERS
	
	void setInicio(NodoLC<T> inicio) { this.inicio = inicio; }
	void setFin(NodoLC<T> fin) { this.fin = fin; }
	
	NodoLC<T> getInicio() { return inicio; }
	NodoLC<T> getFin() { return fin; }
	
	// MÉTODOS DE INSERCIÓN Y ELIMINACIÓN DE ELEMENTOS
	
	public void insertaInicio(T valor) {
		if(inicio == null){ 
			inicio = new NodoLC<>(valor);
			fin = inicio;
			inicio.setSiguiente(fin);
			fin.setSiguiente(inicio);
		}
		else{
			inicio = new NodoLC<>(valor,inicio);
			fin.setSiguiente(inicio);
		}
		return;
	}
	
	public void insertaFinal(T valor) {
		// lista vacia
		if(inicio == null){
			inicio = new NodoLC<>(valor);
			fin = inicio;
			inicio.setSiguiente(fin);
			fin.setSiguiente(inicio);
			return;
		}
		// lista no vacia
		else{
			NodoLC<T> aux = new NodoLC<>(valor, inicio);
			fin.setSiguiente(aux);
			fin = aux;
		}
		return;
	}

	public T eliminaInicio() throws ExcepcionListaCircular{
		// lista vacia
		if(inicio == null){ throw new ExcepcionListaCircular("Lista vacia!"); }
		
		T valor = inicio.getValor();
		
		// lista con un solo nodo
		if(inicio == fin){
			inicio = null;
			fin = null;
		}
		// lista con mas de un nodo
		else {
			inicio = inicio.getSiguiente();
			fin.setSiguiente(inicio);
		}
		return valor;
	}
	
	public T eliminaFinal() throws ExcepcionListaCircular {
		// lista vacia
		if(inicio == null){ throw new ExcepcionListaCircular("Lista vacia!"); }
		
		T valor = fin.getValor();
		
		// lista con un solo nodo
		if (inicio == fin) {
			inicio = null;
			fin = null;
		}
		// lista con mas de un nodo
		else {
			NodoLC<T> it = inicio;
			while (it.getSiguiente()!=fin) { it = it.getSiguiente(); }
			fin = it;
			fin.setSiguiente(inicio);
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
	public String mostrarRecursivo(NodoLC<T> nodo) {
		// Lista vacia
		if(nodo == null){ return ""; }
		// Caso base
		if(nodo == fin){ return nodo.toString(); }
		// Paso recursivo
		else{ return nodo.toString() + " -> " + mostrarRecursivo(nodo.getSiguiente()); }
	}
	
	/**
	 * Método que elimina el primer nodo con el valor dado
	 * Si no encuentra algun nodo con el valor x arroja una excepción
	 * @param x Valor a eliminar en la lista
	 * @return Valor eliminado
	 * @throws ExcepcionListaCircular si no encuentra el valor dado
	 */
	public T eliminaX(T x) throws ExcepcionListaCircular {
		// lista vacia
		if(inicio == null){ throw new ExcepcionListaCircular("Lista vacia"); }
		
		if(inicio.getValor() == x){ return eliminaInicio(); }
		if(fin.getValor() == x){ return eliminaFinal(); }
		
		NodoLC<T> aux = inicio;
		boolean bandera = true;
		
		while (bandera) {
			if(aux.getSiguiente().getValor() == x){
				aux.setSiguiente(aux.getSiguiente().getSiguiente());
				return x;
			}
			aux = aux.getSiguiente();
			if(aux == inicio){ bandera = false;}
		}
		throw new ExcepcionListaCircular("Valor no encontrado");
	}

	/**
	 * Método que regresa el indice del nodo que contiene el valor dado
	 * @param x Valor buscado
	 * @return Indice del valor en la lista, -1 si no se encuentra
	 */
	public int buscar(T x) {
		int i=0;
		NodoLC<T> it = inicio;
		boolean bandera = true;
		while (bandera) {
			if(it.getValor() == x){
				return i;
			}
			i++;
			it = it.getSiguiente();
			if(it == inicio){ bandera = false; }
		}
		return -1;
	}
	
	/**
	 * Método que elimina el nodo en la posicion dada
	 * @param pos es el indice del nodo que se quiere eliminar
	 * @return El valor guardado por el nodo eliminado
	 * @throws ExcepcionListaCircular en caso de lista vacia o indice invalido
	 */
	public T eliminaPosicion(int pos) throws ExcepcionListaCircular {
		// Lista vacia
		if(inicio == null) { throw new ExcepcionListaCircular("Lista vacia");}
		
		int i,n = cuentaNodos();
		NodoLC<T> it = inicio;
		T valor;
		
		// Verifica parametro pos
		if(pos<0 || n-1 < pos) {  throw new ExcepcionListaCircular("Indice invalido"); }
		
		if (n == 0 || pos == 0) { return eliminaInicio(); }
		
		// Busca nodo a eliminar
		for (i=0; i<pos-1; i++) { it = it.getSiguiente(); }
		
		// Extrae el valor
		valor = it.getSiguiente().getValor();
		
		// Elimina el nodo de la lista
		if (it.getSiguiente() == fin) {
			fin = it;
			fin.setSiguiente(inicio);
			// it.setSiguiente(it.getSiguiente().getSiguiente());
		}
		else {
			it.setSiguiente(it.getSiguiente().getSiguiente());
		}
		return valor;
	}
	
	/**
	 * Método que ordena la lista. 
	 * Ordena una lista si el tipo T es string float o int.
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
			){
			return ;
		}
		
		NodoLC<T> it;
		boolean bandera, bandera0;
		
		// implementa método de la burbuja para ordenar
		do{
			bandera = false;
			for (it = inicio, bandera0 = true; bandera0; it = it.getSiguiente()) {
				if(
					((it.getValor() instanceof Integer) && ((Integer)(it.getValor()) > (Integer)it.getSiguiente().getValor()))
					|| ((it.getValor() instanceof Float) && ((float)(it.getValor()) > (float)it.getSiguiente().getValor()))
					|| ((it.getValor() instanceof String) && ((String)it.getValor()).compareTo((String)it.getSiguiente().getValor())>0)
				)
				{
					intercambia(it, it.getSiguiente());
					bandera = true;
				}
				if(it.getSiguiente().getSiguiente()==inicio){ bandera0 = false; }
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
	public void insertaEnPosicion(T valor, int pos) throws ExcepcionListaCircular {
		int n = cuentaNodos(),i;
		if(pos<0 || n<pos){ throw  new ExcepcionListaCircular("Indice invalido");}
		
		NodoLC<T> it = inicio;
		
		if(pos==0){ insertaInicio(valor); }
		else if(pos == n){ insertaFinal(valor); }
		else{
			for (i=0; i<pos-1; i++) { it = it.getSiguiente(); }
			it.setSiguiente( new NodoLC<T>(valor, it.getSiguiente()) );
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
		NodoLC<T> it = inicio;
		String salida = "-";
		boolean bandera = true;
		while (bandera) {
			salida = salida + "[" + it + "]-";
			it = it.getSiguiente();
			if(it == inicio){ bandera = false; }
		}
		return salida;
	}
	
	/**
	 * Método que cuenta cuantos nodos tiene la lista
	 * @return la cantidad de nodos que hay en la lista
	 */
	public int cuentaNodos(){
		int i=0;
		NodoLC<T> it = inicio;
		boolean bandera = true;
		while (bandera) {
			i++;
			it = it.getSiguiente();
			if(it == inicio) { bandera = false; }
		}
		return i;
	}
	
	/**
	 * Método que intercambia los valores guardados de dos nodos.
	 * @param  a nodo a intercambiar valor
	 * @param  b nodo a intercambiar valor
	 */
	public void intercambia(NodoLC<T> a, NodoLC<T> b) {
		if(a == null || b == null) { return; }
		T aux = a.getValor();
		a.setValor(b.getValor());
		b.setValor(aux);
		return;
	}
	
}