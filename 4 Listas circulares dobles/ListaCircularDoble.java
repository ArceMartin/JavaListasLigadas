/**
 * Algoritmos y estructuras de datos
 * Requisito de examen parcial 2
 * ListaCircularDoble.java

 * @author Martín Arce
 * Matricula: 1103883
 * Grupo: 241
 */
public class ListaCircularDoble<T> {
	// ------------------------------ ATRIBUTOS ---------------------------------
	
	private NodoLCD<T> inicio;
	private NodoLCD<T> fin;
	
	// ---------------------------- CONSTRUCTORES -------------------------------
	
	public ListaCircularDoble() {
		inicio = null;
		fin = null;
	}
	
	public ListaCircularDoble(NodoLCD<T> inicio){
		this.inicio = inicio;
	}
	
	public ListaCircularDoble(NodoLCD<T> inicio, NodoLCD<T> fin){
		this.inicio = inicio;
		this.fin = fin;
	}
	
	public ListaCircularDoble(T[] valores){
		for(T valor : valores){
			this.insertaFinal(valor);
		}
	}
	
	
	// ------------------------------- MÉTODOS ----------------------------------
	
	// GETERS Y SETTERS
	
	void setInicio(NodoLCD<T> inicio) { this.inicio = inicio; }
	void setFin(NodoLCD<T> fin) { this.fin = fin; }
	
	NodoLCD<T> getInicio() { return inicio; }
	NodoLCD<T> getFin() { return fin; }
	
	// MÉTODOS DE INSERCIÓN Y ELIMINACIÓN DE ELEMENTOS
	
	public void insertaInicio(T valor) {
		if(inicio == null){ 
			inicio = new NodoLCD<>(valor);
			fin = inicio;
			inicio.setAnterior(fin);
			inicio.setSiguiente(fin);
			fin.setAnterior(inicio);
			fin.setSiguiente(inicio);
		}
		else{
			NodoLCD<T> aux = new NodoLCD<>(valor, fin, inicio);
			inicio.setAnterior(aux);
			fin.setSiguiente(aux);
			inicio = aux;
		}
		return;
	}
	
	public void insertaFinal(T valor) {
		// lista vacia
		if(inicio == null){
			inicio = new NodoLCD<>(valor);
			fin = inicio;
			inicio.setAnterior(fin);
			inicio.setSiguiente(fin);
			fin.setAnterior(inicio);
			fin.setSiguiente(inicio);
			return;
		}
		// lista no vacia
		else{
			NodoLCD<T> aux = new NodoLCD<>(valor, fin, inicio);
			inicio.setAnterior(aux);
			fin.setSiguiente(aux);
			fin = aux;
		}
		return;
	}

	public T eliminaInicio() throws ExcepcionListaCircularDoble{
		// lista vacia
		if(inicio == null){ throw new ExcepcionListaCircularDoble("Lista vacia!"); }
		
		T valor = inicio.getValor();
		
		// lista con un solo nodo
		if(inicio == fin){
			inicio = null;
			fin = null;
		}
		// lista con mas de un nodo
		else {
			inicio = inicio.getSiguiente();
			inicio.setAnterior(fin);
			fin.setSiguiente(inicio);
		}
		return valor;
	}
	
	public T eliminaFinal() throws ExcepcionListaCircularDoble {
		// lista vacia
		if(inicio == null){ throw new ExcepcionListaCircularDoble("Lista vacia!"); }
		
		T valor = fin.getValor();
		
		// lista con un solo nodo
		if (inicio == fin) {
			inicio = null;
			fin = null;
		}
		// lista con mas de un nodo
		else {
			fin = fin.getAnterior();
			fin.setSiguiente(inicio);
			inicio.setAnterior(fin);
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
	public String mostrarRecursivo(NodoLCD<T> nodo) {
		// Lista vacia
		if(nodo == null){ return ""; }
		// Caso base
		if(nodo == fin){ return nodo.toString(); }
		// Paso recursivo
		else{ return nodo.toString() + " <-> " + mostrarRecursivo(nodo.getSiguiente()); }
	}
	
	/**
	 * Método que elimina el primer nodo con el valor dado
	 * Si no encuentra algun nodo con el valor x arroja una excepción
	 * @param x Valor a eliminar en la lista
	 * @return Valor eliminado
	 * @throws ExcepcionListaCircularDoble si no encuentra el valor dado
	 */
	public T eliminaX(T x) throws ExcepcionListaCircularDoble {
		// lista vacia
		if(inicio == null){ throw new ExcepcionListaCircularDoble("Lista vacia"); }
		
		if(inicio.getValor() == x){ return eliminaInicio(); }
		if(fin.getValor() == x){ return eliminaFinal(); }
		
		NodoLCD<T> aux = inicio;
		boolean bandera = true;
		
		while (bandera) {
			if(aux.getValor() == x){
				aux.getAnterior().setSiguiente(aux.getSiguiente());
				aux.getSiguiente().setAnterior(aux.getAnterior());
				return x;
			}
			aux = aux.getSiguiente();
			if(aux == inicio){ bandera = false;}
		}
		throw new ExcepcionListaCircularDoble("Valor no encontrado");
	}

	/**
	 * Método que regresa el indice del nodo que contiene el valor dado
	 * @param x Valor buscado
	 * @return Indice del valor en la lista, -1 si no se encuentra
	 */
	public int buscar(T x) {
		int i=0;
		NodoLCD<T> it = inicio;
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
	 * @throws ExcepcionListaCircularDoble en caso de lista vacia o indice invalido
	 */
	public T eliminaPosicion(int pos) throws ExcepcionListaCircularDoble {
		// Lista vacia
		if(inicio == null) { throw new ExcepcionListaCircularDoble("Lista vacia");}
		
		int i,n = cuentaNodos();
		NodoLCD<T> it = inicio;
		T valor;
		
		// Verifica parametro pos
		if(pos<0 || n-1 < pos) {  throw new ExcepcionListaCircularDoble("Indice invalido"); }
		
		if (n == 0 || pos == 0) { return eliminaInicio(); }
		if (pos == n-1) { return eliminaFinal(); }
		
		// Busca nodo a eliminar
		for (i=0; i<pos; i++) { it = it.getSiguiente(); }
		
		// Extrae el valor
		valor = it.getValor();
		
		// Elimina el nodo de la lista
		it.getAnterior().setSiguiente(it.getSiguiente());
		it.getSiguiente().setAnterior(it.getAnterior());
		return valor;
	}
	
	/**
	 * Método que ordena la lista. 
	 * Ordena una lista si el tipo T es string float o int.
	 */
	public void ordenaLista() {
		int n = cuentaNodos();
		
		// Lista vacia y de un nodo ya estan en orden
		if(n<2){ return; }
		
		// Verifica que sea una lista de un tipo ordenable
		if(   !(inicio.getValor() instanceof Integer)
			&& !(inicio.getValor() instanceof Float)
			&& !(inicio.getValor() instanceof String)
			){ return ; }
			
		NodoLCD<T> it;
		boolean bandera, bandera0=true;
		do{
			bandera = false;
			for (it = inicio.getSiguiente(), bandera0=true; bandera0; it = it.getSiguiente()) {
				if(
					((it.getValor() instanceof Integer) && ((Integer)(it.getAnterior().getValor()) > (Integer)it.getValor()))
					|| ((it.getValor() instanceof Float) && ((float)(it.getAnterior().getValor()) > (float)it.getValor()))
					|| ((it.getValor() instanceof String) && ((String)it.getAnterior().getValor()).compareTo((String)it.getValor())>0)
				)
				{
					intercambia(it.getAnterior(), it);
					bandera = true;
				}
				if(it.getSiguiente()==inicio){ bandera0 = false; }
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
	public void insertaEnPosicion(T valor, int pos) throws ExcepcionListaCircularDoble {
		int n = cuentaNodos(),i;
		if(pos<0 || n<pos){ throw  new ExcepcionListaCircularDoble("Indice invalido");}
		
		NodoLCD<T> it = inicio;
		
		if(pos==0){ insertaInicio(valor); }
		else if(pos == n){ insertaFinal(valor); }
		
		else{
			for (i=0; i<pos-1; i++) { it = it.getSiguiente(); }
			it.setSiguiente( new NodoLCD<T>(valor, it,it.getSiguiente()) );
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
		if(inicio == null){ return "-[]-"; }
		NodoLCD<T> it = inicio;
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
		NodoLCD<T> it = inicio;
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
	public void intercambia(NodoLCD<T> a, NodoLCD<T> b) {
		if(a == null || b == null) { return; }
		T aux = a.getValor();
		a.setValor(b.getValor());
		b.setValor(aux);
		return;
	}
	
}