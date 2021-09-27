package taller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Taller0 {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		String rut = "";
		String contraseña = ""; 
		int opcion = 0;
		int posicionCliente = 0;
		String [] nombres = new String[50];
		String [] apellidos = new String[50];
		String [] ruts = new String[50];
		String [] contraseñas = new String[50];
		int [] saldos = new int[50];
		
		BufferedReader arch = new BufferedReader(new FileReader("clientes.txt"));
		String line;
		while ((line = arch.readLine()) != null) {
			String [] partes = line.split(",");
			String nombre = partes[0];
			String apellido = partes[1];
			rut = partes[2];
			contraseña = partes[3];
			int saldo = Integer.parseInt(partes[4]);
			
			nombres[posicionCliente] = nombre;
			apellidos[posicionCliente] = apellido;
			ruts[posicionCliente] = rut;
			contraseñas[posicionCliente] = contraseña;
			saldos[posicionCliente] = saldo;
			
			posicionCliente++;
			
		}
		
		//Iniciar secion -------->
		System.out.println("ingrese su rut");
		rut = iniciarSecion(rut,2);
		
		while (!leerArchivoClientes(rut, 2)) {
			System.out.println("informacion incorrecta, ingrese un rut valido: ");
			rut = iniciarSecion(rut,2);
		}
		
		System.out.println("ingrese su contraseña");
		contraseña = iniciarSecion(contraseña,3);
		
		while (!leerArchivoClientes(contraseña, 3)) {
			System.out.println("informacion incorrecta, ingrese una contraseña valida");
			contraseña = iniciarSecion(contraseña,3);
		}
		//Iniciar secion<---------
		
		System.out.println("Bienvenido al menu del cliente. \nPorfavor ingrese una opcion \n1) Compra de entrada \n2) informacion del usuario \n3) devolución \n4) cartelera");
		opcion = scan.nextInt();
		scan.nextLine();
		menuCliente(opcion);
		
	}
	
public static String iniciarSecion(String parametro, int parametro0) throws Exception {
	Scanner scan = new Scanner(System.in);
	parametro = scan.nextLine();
	if (leerArchivoClientes(parametro, parametro0)) {
		System.out.println("correcto");
}

return parametro;

}
	

public static boolean leerArchivoClientes(String parametro0,int parametro1) throws Exception{
	BufferedReader arch = new BufferedReader(new FileReader("clientes.txt"));
	String line;
	while ((line = arch.readLine()) != null) {
		String [] partes = line.split(",");
		if (partes[parametro1].equals(parametro0)) {
			return true;
		}
			
		
		
	}return false;
	
}
public static void menuCliente(int parametro0) {
	 if (parametro0 == 1){
	        System.out.println("Usted eligio la opcion 1 \nCompra de entradas: ");
	        
	    }
	    if (parametro0 == 2){
	      
	    }
	    if (parametro0 == 3){
	    
	    }
	    if (parametro0 == 4){
	    
	    }
	
}

}
