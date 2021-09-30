package taller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;

public class Taller0 {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		String rut = "";
		String contrase�a = ""; 
		String opcion = "";
		
		int posicionCliente = 0;
		int contadorSalasDeCine = -1;
		int contadorCliente = 0;
		int idxCliente = 0;
		
		String [] nombres = new String[50];
		String [] apellidos = new String[50];
		String [] ruts = new String[50];
		String [] contrase�as = new String[50];
		String [] clientePelicula = new String[50];
		String [] nombrePeliculas = new String[50];
		String [] tiposDePelicula = new String[50];
		String [][] horariosDePeliculas = new String[50][50];
		String [][] idxHorarioPelicula = new String[50][50];
		
		int [] saldos = new int[50];
		int [] peliculaElegir = new int[50];
		int [] recaudacionPeliculas = new int[50];
		int [] cantFunciones = new int[50];
		
		leerArchivoClientes(nombres, apellidos, ruts, contrase�as, saldos,peliculaElegir,contadorCliente);
	
		System.out.println("ingrese su rut");
	    rut = scan.nextLine();	    
	     for(int i = 0; i<50;i++) {
	    	 if(rut.equals(ruts[i])) {
	    		 idxCliente = i; 
	    	 }
	     }
	     
		System.out.println("ingrese su contrase�a");
		contrase�a = scan.nextLine();
		
		System.out.println("Bienvenido al menu del cliente. \nPorfavor ingrese una opcion \n1) Compra de entrada \n2) informacion del usuario \n3) devoluci�n \n4) cartelera");
		opcion = scan.nextLine();
		

		leerArchivoPeliculas(contadorSalasDeCine,posicionCliente, nombrePeliculas, tiposDePelicula, recaudacionPeliculas, horariosDePeliculas,idxHorarioPelicula,cantFunciones);

		menuCliente(opcion,nombrePeliculas,horariosDePeliculas,idxHorarioPelicula,cantFunciones,rut,ruts,tiposDePelicula,saldos,idxCliente,clientePelicula,nombres,apellidos);
	}
/**
 * En general esta funcion nos permite a�adir los datos de los archivos de textosa las diferentes funciones
 * @param lista0 contiene los nombres de los clientes
 * @param lista1 contiene los apellidos de los clientes
 * @param lista2 contiene los ruts de los clientes
 * @param lista3 contiene las contrase�as de los clientes
 * @param lista4 contiene los saldos de los clientes
 * @param lista5 esta lista se llenara con 0 ya que mas adelante nos servira para almacenar informacion con respecto a las compras
 * @param contador este contador aumentara para saber cuantos clientes existen
 * @throws Exception
 */
public static void leerArchivoClientes(String [] lista0, String [] lista1, String [] lista2, String [] lista3, int [] lista4,int [] lista5,int contador) throws Exception {
	BufferedReader arch = new BufferedReader(new FileReader("clientes.txt"));
	String line;
	while ((line = arch.readLine()) != null) {
		String [] partes = line.split(",");
		String nombre = partes[0];
		String apellido = partes[1];
		String rut = partes[2];
		String contrase�a = partes[3];
		
		int saldo = Integer.parseInt(partes[4]);

		lista0[contador] = nombre;
		lista1[contador] = apellido;
		lista2[contador] = rut;
		lista3[contador] = contrase�a;
		lista4[contador] = saldo;
		lista5[contador] = 0;
		contador++;

	}

}
/**
 * this function reads the text file "movies" and adds each section to its respective list
 * @param sumador this variable stores the amount of additional lines that the txt file gives us
 * @param counter this counter gives us the number of movies that exist in the txt file
 * @param lista0 this list stores the name of the movie
 * @param lista1 this list stores the movie type, premiere / Released
 * @param lista2 This list stores the amount of collection of the movies
 * @param matrix This matrix will contain the room and time of the movies with a special syntax
 * @param matrix2 This matrix will contain the room and time of the movies with their exact values
 * @param lista4 this list will store the line of the text file in a variable way
 * @return this function returns the number of movies that exist
 * @throws Exception
 */
public static int leerArchivoPeliculas(int sumador,int contador,String [] lista0,String [] lista1,int [] lista2,String [][] matrix,String [][] matrix2,int [] lista4) throws Exception {
	BufferedReader arch = new BufferedReader(new FileReader("peliculas.txt"));
	String line;
	while ((line = arch.readLine()) != null) {
		int h = 0;	
		String [] partes = line.split(",");
		String nombrePelicula = partes[0];
		String tipoPelicula = partes[1];
		int recaudacion = Integer.parseInt(partes[2]);
		
		for (int i = 3;i<partes.length;i += 2) {
			String numeroSala = partes[i];
			String horarioPelicula = partes[i+1];
			matrix2[contador][h] = numeroSala;
			matrix2[contador][h+1] = horarioPelicula;
			
			if(numeroSala.equals("1")) {
				matrix[contador][h] = "Sala 1";
				}else if(numeroSala.equals("2")){
				matrix[contador][h] = "Sala 2";
					}else if(numeroSala.equals("3")){
				matrix[contador][h] = "Sala 3";
							}else {
				matrix[contador][h]="";
			}
				
			if(horarioPelicula.equals("M")) {
				matrix[contador][h+1] = "en la Ma�ana";
			}else if(horarioPelicula.equals("T")){
				matrix[contador][h+1] = "en la Tarde";
			}else {
				matrix[contador][h+1]="";
			}
				
			if(partes.length-3 > sumador) {
				sumador = partes.length-3;
					
			}
			h +=2;
		}
		
		lista0[contador] = nombrePelicula;
		lista1[contador] = tipoPelicula;
		lista2[contador] = recaudacion;
		lista4[contador] = partes.length-3;
		contador++;
	}
	
 return contador;
 
}
/**
 * esta funcion nos permite elegir la funcion de la pelicula
 * @param matrix0 esta matriz contiene los valores exactos de los horarios de las peliculas
 * @param lista0 esta lista contiene los nombres de las peliculas
 * @param pelicula esta sera la posicion de la pelicula en su respectiva lista
 */
public static void eleccionFuncion(String[][]matrix0,String [] lista0,int pelicula) {
	Scanner scan = new Scanner(System.in);
	System.out.println("elija una sala: ");
	String eleccionSala = scan.nextLine();
	
	for (int k = 0; k < lista0.length; k += 2) {		
		if (eleccionSala.equals(matrix0[pelicula][k])){
			System.out.println("usted eligi� la sala "+matrix0[pelicula][k]);
			System.out.println("por favor ahora elija el horario de la funcion (M/T)");
			String eleccionHorario = scan.nextLine();
			for(int j = 0;  j < lista0.length; j += 2) {
				
			if (eleccionHorario.equals(matrix0[pelicula][j+1])) {
				System.out.println("ustede eligio la sala "+matrix0[pelicula][k] + " "+ matrix0[pelicula][j+1]);
				System.out.println("a continuacion, se mostrara la sala correspondiente \n____________________________________");
					break;
					}
			   }
		 break;
		}
	}

}
/**
 * This function contains the Customer menu with its respective points, it contains other functions within
 * and allows us to get the information requested by customers
 * @param parametro0 It is the option that is given to the client to choose between the different menu options
 * @param lista0 list containing movie names
 * @param matrix this matrix prints the movie times
 * @param matrix2 This array is used as a parameter in another function (it contains the schedules but with a special syntax)
 * @param lista1 this list stores the number of existing functions
 * @param rutCliente parameter that contains the rout of the client that I entered
 * @param listaClientes list containing customer ruts
 * @param tipoPelicula list containing the type of movie
 * @param saldoClientes list containing customer balances
 * @param idxCliente parameter that contains the customer's index in its respective list
 * @param peliculasCompradas This list stores the movie tickets that were purchased
 * @param nombres this list contains the names of the clients
 * @param apellidos this list contains the last names of the clients
 */
public static void menuCliente(String parametro0,String [] lista0,String [][] matrix,String [][]matrix2,int [] lista1,String rutCliente,String [] listaClientes,String [] tipoPelicula,int [] saldoClientes,int idxCliente,String [] peliculasCompradas,String [] nombres,String [] apellidos ) {

	Scanner scan = new Scanner(System.in);
	 if (parametro0.equals("1")){
	        System.out.println("Usted eligio la opcion 1 \nCompra de entradas: ");
	        System.out.println("ingrese el nombre de la pelicula para desplegar los horarios: ");
	        String nombrePelicula = scan.nextLine();
	        int indexPelicula = 0;
	        for (int i = 0; i < lista0.length; i++){
				if(nombrePelicula.equals(lista0[i])){
					indexPelicula = i;
					System.out.println("Pelicula encontrada!!, usted eligio la pelicula "+lista0[i]);
					System.out.println("los horarios disponibles son:");
					for (int j = 0; j < lista1[i]; j++) {
						
					System.out.print(matrix[i][j]+" ");
					
					}
				System.out.println("\n_____________");
				eleccionFuncion(matrix2, lista0, i);
				break;
				}
				
				
			}
	        String []letraAsiento = {"A","B","C","D","E","F","G","H","I","J"};
			String salaCine[][] =new String [10][31];

			for(int i=0;i<salaCine.length;i++) {
				for(int j=1;j<salaCine[0].length;j++) {
					if(i<5 && j>5 && j<26) {
						salaCine[i][j]= letraAsiento [i]+j;
						
					}
					else if(i>=4) {
						salaCine[i][j]= letraAsiento [i]+j;
						
					}										
					System.out.print(salaCine[i][j]+" ");
				}		
				System.out.println();
			}
			
			System.out.println("Cuantos asientos desea: ");
			int cantAsientos = scan.nextInt();
			String[][]salaNueva=consultarAsiento(salaCine,cantAsientos,rutCliente);
			String confirmacion;
			for (int i = 0; i < listaClientes.length; i++) {
				if (rutCliente.equals(listaClientes[i])){
					if(tipoPelicula[indexPelicula].equals("estreno")) {
						int cobroAsientos = cantAsientos*5500;
						System.out.println("la cantidad a pagar es de: "+cobroAsientos+ " Desea confirmar la compra? si/no");
						confirmacion = scan.next();
						if (confirmacion.equalsIgnoreCase("si")) {
							saldoClientes[idxCliente] -= cobroAsientos;
							peliculasCompradas[idxCliente] = nombrePelicula;
				
					
						}else {
							cobroAsientos = 0;
									}
					}else {
						int cobroAsientos = cantAsientos*4000;
						System.out.println("la cantidad a pagar es de: "+cobroAsientos+ " Desea confirmar la compra? si/no");
						confirmacion = scan.next();
						if (confirmacion.equalsIgnoreCase("si")) {
							saldoClientes[idxCliente] -= cobroAsientos;
							peliculasCompradas[idxCliente] = nombrePelicula;
						
					}else {
						cobroAsientos = 0;
					}
				}
			}
		}
			

	    }
	    if (parametro0.equals("2")){
	    	System.out.println("usted eligio la opcion 2, informacion de usuario");
	    	System.out.println("El usuario con rut: "+rutCliente);
	    	System.out.println("Nombre completo: "+nombres[idxCliente]+" "+apellidos[idxCliente]);
	    	System.out.println("con un saldo de: "+saldoClientes[idxCliente]);
	    	
	    }
	    if (parametro0.equals("3")){

	    }
	    if (parametro0.equals("4")){

	    }









}
/**
 * 
 * @param salaCine
 * @param cantAsientos
 * @param rutCliente
 * @return
 */
public static String[][] consultarAsiento(String salaCine[][],int cantAsientos,String rutCliente) {
	Scanner scan = new Scanner(System.in);
	String asientoInicial=null ;
	String asientoNormal=null ;
	String distanciaAsiento=null;	



	System.out.println("Desea asientos juntos o separados: ");
	distanciaAsiento = scan.next();

	if(distanciaAsiento.equalsIgnoreCase("separados")) {
		for(int a=0;a<cantAsientos;a++){
			Scanner scane=new Scanner(System.in);
			System.out.println("Escoja su Asiento con 1 asiento de separacion a ambos lados(Ejemplo: A1): ");
			asientoNormal= scane.nextLine();


	for(int i=0;i<salaCine.length;i++) {
		for(int j=1;j<salaCine[0].length;j++) { 
			if(asientoNormal.equalsIgnoreCase(salaCine[i][j])) {
				salaCine[i][j-1]="X";	
				salaCine[i][j]="X";
				salaCine[i][j+1]="X";

					}
				}			
			}
		}
	}
	
	if(distanciaAsiento.equalsIgnoreCase("juntos")) {
		Scanner scane=new Scanner(System.in);
		System.out.println("Escoja su primer asiento(Ejemplo: A1)\nAVISO:se rellenan los asientos a la derecha");
		asientoInicial= scane.nextLine();			
		int cont=0;
		for(int i=0;i<salaCine.length;i++) {
			for(int j=1;j<salaCine[0].length;j++) { 
				if(asientoInicial.equalsIgnoreCase(salaCine[i][j])) {
					salaCine[i][j-1]="X";
					for(int a=0;a<cantAsientos+1;a++) {
						salaCine[i][j+cont]="X";
						 cont++;
					}
				}
			}
		}

	}
	
	return salaCine;	
}								

}
