/**
 * Algoritmos y estructuras de datos
 * Requisito de examen parcial 2
 * Main.java (Lista circular doble)

 * @author Martín Arce
 * Matricula: 1103883
 * Grupo: 241
 */

public class Main {
	// -------------------------- MÉTODOS ESTATICOS -----------------------------
	
	private static ListaCircularDoble<String> lista;
	private static String[] valores = {"pato", "ganso", "gallo"};
	private static String[] valores2 = {"lorem", "ipsum", "dolor", "sit", "amet"};
	
	public static void main(String[] args){
		
		System.out.println("============================ Pruebas de Lista Circular ============================");
		int n = args.length==0 ? 0 : Integer.parseInt(args[0]);
		switch (n) {
			case 1:
				pruebaInsertaInicio();
				break;
			case 2:
				pruebaInsertaFinal();
				break;
			case 3:
				pruebasEliminaInicio();
				break;
			case 4:
				pruebasEliminaFinal();
				break;
			case 5:
				pruebasMostrarRecursivo();
				break;
			case 6:
				pruebasEliminaX();
				break;
			case 7:
				pruebasBuscar();
				break;
			case 8:
				pruebasEliminaPosicion();
				break;
			case 9:
				pruebasOrdena();
				break;
			case 10:
				pruebasInsertaEnPosicion();
				break;
			default:
				System.out.println("Argumentos validos:");
				System.out.println(" 1: pruebaInsertaInicio");
				System.out.println(" 2: pruebaInsertaFinal");
				System.out.println(" 3: pruebasEliminaInicio");
				System.out.println(" 4: pruebasEliminaFinal");
				System.out.println(" 5: pruebasMostrarRecursivo");
				System.out.println(" 6: pruebasEliminaX");
				System.out.println(" 7: pruebasBuscar");
				System.out.println(" 8: pruebasEliminaPosicion");
				System.out.println(" 9: pruebasOrdena");
				System.out.println("10: pruebasInsertaEnPosicion");
				break;
		}
		
	}
	
	public static void pruebaInsertaInicio() {
			System.out.println("--------------------------- Pruebas de insertaInicio ---------------------------\n");
			
			lista = new ListaCircularDoble<>();
			
			System.out.println("Llamamos a insertaInicio con \"pato\"");
			lista.insertaInicio("pato");
			System.out.println(lista);
			
			System.out.println("\nLlamamos a insertaInicio con \"ganso\"");
			lista.insertaInicio("ganso");
			System.out.println(lista);
			
			System.out.println("\nLlamamos a insertaInicio con \"gallo\"");
			lista.insertaInicio("gallo");
			System.out.println(lista);
	}
	
	public static void pruebaInsertaFinal() {
		System.out.println("---------------------------- Pruebas de insertaFinal ---------------------------\n");
		
		lista = new ListaCircularDoble<>();
		
		System.out.println("Imprime lista vacia:");
		System.out.println(lista);
		
		System.out.println("\nLlamamos a insertaFinal con \"pato\"");
		lista.insertaFinal("pato");
		System.out.println(lista);
		
		System.out.println("\nLlamamos a insertaFinal con \"ganso\"");
		lista.insertaFinal("ganso");
		System.out.println(lista);
		
		System.out.println("\nLlamamos a insertaFinal con \"gallo\"");
		lista.insertaFinal("gallo");
		System.out.println(lista);
	}
	
	public static void pruebasEliminaInicio() {
		System.out.println("--------------------------- Pruebas de eliminaInicio ---------------------------\n");
		
		lista = new ListaCircularDoble<>(valores);
		System.out.println("Imprime lista con valores:");
		System.out.println(lista);
		
		System.out.println("\nLlamamos a eliminaInicio");
		try{lista.eliminaInicio();}
		catch(ExcepcionListaCircularDoble e){System.out.println(e);}
		System.out.println(lista);
		
		System.out.println("\nLlamamos a eliminaInicio");
		try{lista.eliminaInicio();}
		catch(ExcepcionListaCircularDoble e){System.out.println(e);}
		System.out.println(lista);
		
		System.out.println("\nLlamamos a eliminaInicio");
		try{lista.eliminaInicio();}
		catch(ExcepcionListaCircularDoble e){System.out.println(e);}
		System.out.println(lista);
		
		System.out.println("\nLlamamos a eliminaInicio");
		try{lista.eliminaInicio();}
		catch(ExcepcionListaCircularDoble e){System.out.println(e);}
		System.out.println(lista);
	}
	
	public static void pruebasEliminaFinal() {
		System.out.println("---------------------------- Pruebas de eliminaFinal ---------------------------\n");
		
		lista = new ListaCircularDoble<>(valores);
		System.out.println("Imprime lista con valores:");
		System.out.println(lista);
		
		System.out.println("\nLlamamos a eliminaFinal");
		try{lista.eliminaFinal();}
		catch(ExcepcionListaCircularDoble e){System.out.println(e);}
		System.out.println(lista);
		
		System.out.println("\nLlamamos a eliminaFinal");
		try{lista.eliminaFinal();}
		catch(ExcepcionListaCircularDoble e){System.out.println(e);}
		System.out.println(lista);
		
		System.out.println("\nLlamamos a eliminaFinal");
		try{lista.eliminaFinal();}
		catch(ExcepcionListaCircularDoble e){System.out.println(e);}
		System.out.println(lista);
		
		System.out.println("\nLlamamos a eliminaFinal");
		try{lista.eliminaFinal();}
		catch(ExcepcionListaCircularDoble e){System.out.println(e);}
		System.out.println(lista);
	}
	
	public static void pruebasMostrarRecursivo() {
		System.out.println("-------------------------- Pruebas de mostrarRecursivo -------------------------\n");
		
		System.out.println("Lista vacia");
		lista = new ListaCircularDoble<>();
		System.out.println("\nLlamamos a mostrarRecursivo");
		System.out.println(lista.mostrarRecursivo(lista.getInicio()));
		
		System.out.println("\nLista con valores");
		lista = new ListaCircularDoble<>(valores);
		System.out.println(lista.mostrarRecursivo(lista.getInicio()));
	}
	
	public static void pruebasEliminaX() {
		System.out.println("------------------------------ Pruebas de eliminaX -----------------------------\n");
				
		System.out.println("Lista vacia:");
		lista = new ListaCircularDoble<>();
		System.out.println("Llamamos a eliminaX(\"ganso\")");
		try{lista.eliminaX("ganso");}
		catch(ExcepcionListaCircularDoble e){System.out.println(e);}
		System.out.println(lista);
		
		System.out.println("\nLista con valores:");
		lista = new ListaCircularDoble<>(valores);
		System.out.println(lista);
		System.out.println("Llamamos a eliminaX(\"pato\")");
		try{lista.eliminaX("pato");}
		catch(ExcepcionListaCircularDoble e){System.out.println(e);}
		System.out.println(lista);
		
		System.out.println("\nLista con valores:");
		lista = new ListaCircularDoble<>(valores);
		System.out.println(lista);
		System.out.println("Llamamos a eliminaX(\"gallo\")");
		try{lista.eliminaX("gallo");}
		catch(ExcepcionListaCircularDoble e){System.out.println(e);}
		System.out.println(lista);
		
		System.out.println("\nLista con valores:");
		lista = new ListaCircularDoble<>(valores);
		System.out.println(lista);
		System.out.println("Llamamos a eliminaX(\"ganso\")");
		try{lista.eliminaX("ganso");}
		catch(ExcepcionListaCircularDoble e){System.out.println(e);}
		System.out.println(lista);

		System.out.println("\nLista con valores:");
		lista = new ListaCircularDoble<>(valores);
		System.out.println(lista);
		System.out.println("Llamamos a eliminaX(\"gato\")");
		try{lista.eliminaX("gato");}
		catch(ExcepcionListaCircularDoble e){System.out.println(e);}
		System.out.println(lista);
	}
	
	public static void pruebasBuscar() {
		System.out.println("------------------------------- Pruebas de buscar ------------------------------\n");
		
		lista = new ListaCircularDoble<>(valores);
		System.out.println("Imprime lista con valores:");
		System.out.println(lista);
		
		System.out.println("\nLlamamos a buscar(\"pato\")");
		System.out.println(lista.buscar("pato"));
		
		System.out.println("\nLlamamos a buscar(\"ganso\")");
		System.out.println(lista.buscar("ganso"));
		
		System.out.println("\nLlamamos a buscar(\"gallo\")");
		System.out.println(lista.buscar("gallo"));
		
		System.out.println("\nLlamamos a buscar(\"gato\")");
		System.out.println(lista.buscar("gato"));
	}
	
	public static void pruebasEliminaPosicion() {
		System.out.println("-------------------------- Pruebas de eliminaPosicion --------------------------\n");
		
		System.out.println("Lista con valores:");
		lista = new ListaCircularDoble<>(valores2);
		System.out.println(lista);
		System.out.println("Llamamos a eliminaPosicion(0)");
		try{lista.eliminaPosicion(0);}
		catch(ExcepcionListaCircularDoble e){System.out.println(e);}
		System.out.println(lista);
		
		System.out.println("\nLista con valores:");
		lista = new ListaCircularDoble<>(valores2);
		System.out.println(lista);
		System.out.println("Llamamos a eliminaPosicion(1)");
		try{lista.eliminaPosicion(1);}
		catch(ExcepcionListaCircularDoble e){System.out.println(e);}
		System.out.println(lista);
		
		System.out.println("\nLista con valores:");
		lista = new ListaCircularDoble<>(valores2);
		System.out.println(lista);
		System.out.println("Llamamos a eliminaPosicion(2)");
		try{lista.eliminaPosicion(2);}
		catch(ExcepcionListaCircularDoble e){System.out.println(e);}
		System.out.println(lista);
		
		System.out.println("\nLista con valores:");
		lista = new ListaCircularDoble<>(valores2);
		System.out.println(lista);
		System.out.println("Llamamos a eliminaPosicion(7)");
		try{lista.eliminaPosicion(7);}
		catch(ExcepcionListaCircularDoble e){System.out.println(e);}
		System.out.println(lista);
	}
	
	public static void pruebasOrdena() {
		System.out.println("---------------------------- Pruebas de ordenaLista ----------------------------\n");
		
		System.out.println("Lista con strings:");
		lista = new ListaCircularDoble<>(valores2);
		System.out.println(lista);
		lista.ordenaLista();
		System.out.println(lista);
		
		System.out.println("\nLista con enteros:");
		Integer[] enteros = {5,4,7,3,8,-3};
		ListaCircularDoble<Integer> lista2 = new ListaCircularDoble<>(enteros);
		System.out.println(lista2);
		lista2.ordenaLista();
		System.out.println(lista2);
	}
	
	public static void pruebasInsertaEnPosicion() {
		System.out.println("------------------------- Pruebas de insertaEnPosicion -------------------------\n");
		
		System.out.println("Lista con valores:");
		lista = new ListaCircularDoble<>(valores);
		System.out.println(lista);
		System.out.println("Llamamos a insertaEnPosicion(\"pollo\", 0)");
		try{lista.insertaEnPosicion("pollo",0);}
		catch(ExcepcionListaCircularDoble e){System.out.println(e);}
		System.out.println(lista);
		
		System.out.println("\nLista con valores:");
		lista = new ListaCircularDoble<>(valores);
		System.out.println(lista);
		System.out.println("Llamamos a insertaEnPosicion(\"pollo\", 1)");
		try{lista.insertaEnPosicion("pollo",1);}
		catch(ExcepcionListaCircularDoble e){System.out.println(e);}
		System.out.println(lista);
		
		System.out.println("\nLista con valores:");
		lista = new ListaCircularDoble<>(valores);
		System.out.println(lista);
		System.out.println("Llamamos a insertaEnPosicion(\"pollo\", 2)");
		try{lista.insertaEnPosicion("pollo",2);}
		catch(ExcepcionListaCircularDoble e){System.out.println(e);}
		System.out.println(lista);

		System.out.println("\nLista con valores:");
		lista = new ListaCircularDoble<>(valores);
		System.out.println(lista);
		System.out.println("Llamamos a insertaEnPosicion(\"pollo\", 3)");
		try{lista.insertaEnPosicion("pollo",3);}
		catch(ExcepcionListaCircularDoble e){System.out.println(e);}
		System.out.println(lista);

		System.out.println("\nLista con valores:");
		lista = new ListaCircularDoble<>(valores);
		System.out.println(lista);
		System.out.println("Llamamos a insertaEnPosicion(\"pollo\", 5)");
		try{lista.insertaEnPosicion("pollo",5);}
		catch(ExcepcionListaCircularDoble e){System.out.println(e);}
		System.out.println(lista);
		
		System.out.println("\nLista con valores:");
		lista = new ListaCircularDoble<>(valores);
		System.out.println(lista);
		System.out.println("Llamamos a insertaEnPosicion(\"pollo\", -3)");
		try{lista.insertaEnPosicion("pollo",-3);}
		catch(ExcepcionListaCircularDoble e){System.out.println(e);}
		System.out.println(lista);
	}
}