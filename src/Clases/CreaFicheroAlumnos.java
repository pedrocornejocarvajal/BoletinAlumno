package Clases;

import java.io.*;

public class CreaFicheroAlumnos {

	/*Interfaz
	 * Comentario: crea un fichero de texto para alumnos
	 * Prototipo: void creaFicheroTexto(String ruta, String nombre)
	 * Entrada: una cadena
	 * Precondiciones: ninguna
	 * Postcondiciones: se crea el fichero en la ruta especificada
	 * Salidas: ninguna*/
	public static void creaFichero(String directorio,String nombre){
		File ruta = null;
		File fichero = null;
		
		try{
			ruta = new File(directorio);
			fichero = new File(nombre+".dat");
			if(ruta.exists()==false && ruta.isDirectory()){
				
				if(ruta.mkdirs()){
					
					if(fichero.createNewFile())
						System.out.println("El fichero se ha creado correctamente");
					else
						System.out.println("No pudo crearse el fichero");
				}
				else
					System.out.println("No pudo crearse el directorio");
			}
			else{
				
				if(fichero.createNewFile())
					System.out.println("El fichero se ha creado correctamente");
				else
					System.out.println("No pudo crearse el fichero");
			}
				
		}
		catch(NullPointerException npe){
			npe.printStackTrace();
		}
		catch(SecurityException se){
			se.printStackTrace();
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}	
	}//CreaFichero
	
	public static void main(String args[]){
	
			String directorio="";
			creaFichero(directorio,"Alumnos");	
	}
}
