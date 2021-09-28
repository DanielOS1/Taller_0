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
		String contraseña = ""; 
		int opcion = 0;
		int posicionCliente = 0;
		int contadorSalasDeCine = -1;
		String [] nombres = new String[50];
		String [] apellidos = new String[50];
		String [] ruts = new String[50];
		String [] contraseñas = new String[50];
		int [] saldos = new int[50];
		leerArchivoClientes(nombres, apellidos, ruts, contraseñas, saldos, posicionCliente);

		String [] nombrePeliculas = new String[50];
		String [] tiposDePelicula = new String[50];
		int [] recaudacionPeliculas = new int[50];
		String [] numerosDeSalas = new String[50];
		String [][] horariosDePeliculas = new String[50][50];
		int [] cantFunciones = new int[50];

		leerArchivoPeliculas(contadorSalasDeCine,posicionCliente, nombrePeliculas, tiposDePelicula, recaudacionPeliculas, horariosDePeliculas,cantFunciones);
		
			
			
		

		//Iniciar secion -------->
		System.out.println("ingrese su rut");
		rut = iniciarSecion(rut,2);

		while (!archivoClientesIniciarSecion(rut, 2)) {
			System.out.println("informacion incorrecta, ingrese un rut valido: ");
			rut = iniciarSecion(rut,2);
		}

		System.out.println("ingrese su contraseña");
		contraseña = iniciarSecion(contraseña,3);

		while (!archivoClientesIniciarSecion(contraseña, 3)) {
			System.out.println("informacion incorrecta, ingrese una contraseña valida");
			contraseña = iniciarSecion(contraseña,3);
		}
		//Iniciar secion<---------

		System.out.println("Bienvenido al menu del cliente. \nPorfavor ingrese una opcion \n1) Compra de entrada \n2) informacion del usuario \n3) devolución \n4) cartelera");
		opcion = scan.nextInt();
		scan.nextLine();
		menuCliente(opcion,nombrePeliculas,horariosDePeliculas,cantFunciones);
	}

public static String iniciarSecion(String parametro, int parametro0) throws Exception {
	Scanner scan = new Scanner(System.in);
	parametro = scan.nextLine();
	if (archivoClientesIniciarSecion(parametro, parametro0)) {
		System.out.println("correcto");
}

return parametro;

}


public static boolean archivoClientesIniciarSecion(String parametro0,int parametro1) throws Exception{
	BufferedReader arch = new BufferedReader(new FileReader("clientes.txt"));
	String line;
	while ((line = arch.readLine()) != null) {
		String [] partes = line.split(",");
		if (partes[parametro1].equals(parametro0)) {
			return true;
		}



	}return false;


}
public static void leerArchivoClientes(String [] lista0, String [] lista1, String [] lista2, String [] lista3, int [] lista4,int contador) throws Exception {
	BufferedReader arch = new BufferedReader(new FileReader("clientes.txt"));
	String line;
	while ((line = arch.readLine()) != null) {
		String [] partes = line.split(",");
		String nombre = partes[0];
		String apellido = partes[1];
		String rut = partes[2];
		String contraseña = partes[3];
		int saldo = Integer.parseInt(partes[4]);

		lista0[contador] = nombre;
		lista1[contador] = apellido;
		lista2[contador] = rut;
		lista3[contador] = contraseña;
		lista4[contador] = saldo;

		contador++;

	}

}
public static void leerArchivoPeliculas(int sumador,int contador,String [] lista0,String [] lista1,int [] lista2,String [][] lista3,int [] lista4) throws Exception {
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
				if(numeroSala.equals("1")) {
					lista3[contador][h] = "Sala 1 ";
				}else if(numeroSala.equals("2")){
					lista3[contador][h] = "Sala 2 ";
				}else if(numeroSala.equals("3")){
					lista3[contador][h] = "Sala 3 ";
				}else {
					lista3[contador][h]="";
				}
				
				if(horarioPelicula.equals("M")) {
					lista3[contador][h+1] = "en la Mañana |";
				}else if(horarioPelicula.equals("T")){
					lista3[contador][h+1] = "en la Tarde  |";
				}else {
					lista3[contador][h+1]="";
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

}
public static void menuCliente(int parametro0,String [] lista0,String [][] matrix,int [] lista2) {
	Scanner scan = new Scanner(System.in);
	 if (parametro0 == 1){
	        System.out.println("Usted eligio la opcion 1 \nCompra de entradas: ");
	        System.out.println("ingrese el nombre de la pelicula para desplegar los horarios: ");
	        String nombrePelicula = scan.nextLine();
	        
	        for (int i = 0; i < lista0.length; i++){
				if(nombrePelicula.equals(lista0[i])){
					System.out.println("Pelicula encontrada!!, usted eligio la pelicula "+lista0[i]);
					System.out.println("los horarios disponibles son:");
				for (int j = 0; j < lista2[i]; j++) {
					System.out.print(matrix[i][j]+"\n");
					
				 }
				System.out.println("_____________");
				break;
				}
				
				
			}
	        String []letraAsiento = {"A","B","C","D","E","F","G","H","I","J",};
			String salaCine[][] =new String [10][31];

			for(int i=0;i<salaCine.length;i++) {
				for(int j=1;j<salaCine[0].length;j++) {
					if(i<5 && j>5 && j<26) {
						salaCine[i][j]= letraAsiento [i]+j;
						//System.out.print(salaCine[i][j]+" ");
					}
					else if(i>=4) {
						salaCine[i][j]= letraAsiento [i]+j;
						//System.out.print(salaCine[i][j]+" ");
					}										
					System.out.print(salaCine[i][j]+" ");
				}		
				System.out.println();
			}
		String[][]salaNueva=consultarAsiento(salaCine);

			for(int i=0;i<salaNueva.length;i++) {
				for(int j=1;j<salaNueva[0].length;j++) {
					if(i<5 && j>5 && j<26) {
						//System.out.print(salaNueva[i][j]+" "); //imprime la sala sin los null
					}else if(i>=4) {
						//System.out.print(salaNueva[i][j]+" "); //imprime la sala sin los null
				}				
					//System.out.print(salaNueva[i][j]+" "); //imprime la sala con nulls
			}
				//System.out.println(); //imprime la sala con nulls
		} 

	    }
	    if (parametro0 == 2){

	    }
	    if (parametro0 == 3){

	    }
	    if (parametro0 == 4){

	    }






	//return false; // te deje esto en comentario ya que me tiraba un error todo el rato :c




}

public static String[][] consultarAsiento(String salaCine[][]) {

	String asientoInicial=null ;
	String asientoNormal=null ;
	String distanciaAsiento=null;
	int cantAsientos;

	Scanner scan=new Scanner(System.in);
	System.out.println("Cuantos asientos desea: ");
	cantAsientos = scan.nextInt();

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

				//System.out.println("esta enla posicion "+i+","+j);

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
