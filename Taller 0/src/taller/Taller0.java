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
		int contadorSalasDeCine = 0;
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
		String [] horariosDePeliculas = new String[50];
		int [] cantFunciones = new int[50];
		
		leerArchivoPeliculas(contadorSalasDeCine,posicionCliente, nombrePeliculas, tiposDePelicula, recaudacionPeliculas, numerosDeSalas, horariosDePeliculas, cantFunciones);
		
		
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
		menuCliente(opcion,nombrePeliculas,numerosDeSalas,horariosDePeliculas,cantFunciones);
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
public static void leerArchivoPeliculas(int sumador,int contador,String [] lista0,String [] lista1,int [] lista2,String [] lista3,String [] lista4,int [] lista5) throws Exception {
	BufferedReader arch = new BufferedReader(new FileReader("peliculas.txt"));
	String line;
	while ((line = arch.readLine()) != null) {
		String [] partes = line.split(",");
		String nombrePelicula = partes[0];
		String tipoPelicula = partes[1];
		int recaudacion = Integer.parseInt(partes[2]);
		for (int i = 3;i<partes.length;i += 2) {
			String numeroSala = partes[i];
			String horarioPelicula = partes[i+1];
			lista3[sumador] = numeroSala;
			lista4[sumador] = horarioPelicula;
			
			sumador++;
			
		}
		lista0[contador] = nombrePelicula;
		lista1[contador] = tipoPelicula;
		lista2[contador] = recaudacion;
		lista5[contador] = partes.length-3;
		contador++;
		
	}

}
public static void menuCliente(int parametro0,String [] lista0,String [] lista1,String [] lista2, int [] lista3) {
	Scanner scan = new Scanner(System.in);
	 if (parametro0 == 1){
	        System.out.println("Usted eligio la opcion 1 \nCompra de entradas: ");
	        System.out.println("ingrese el nombre de la pelicula para desplegar los horarios: ");
	        String nombrePelicula = scan.nextLine();
	        
	        for (int i = 0; i < lista0.length; i++){
				if(nombrePelicula.equals(lista0[i])){
					System.out.println("Pelicula encontrada!!, usted eligio la pelicula "+lista0[i]);
					System.out.println("los horarios disponibles son:");
					if(i==0){
						for (int j = 0; j < lista3[0]/2; j++) {
							System.out.println(lista1[j]+ " "+lista2[j]);
						}
						
					} else {
					for(int j = lista3[i];j < lista3[j] ;j++) {
					System.out.println(lista1[j]+ " "+lista2[j]);
					}
					break;
					}
				}
			}
	        
	        
	    }
	    if (parametro0 == 2){
	      
	    }
	    if (parametro0 == 3){
	    
	    }
	    if (parametro0 == 4){
	    
	    }
	
}

}
