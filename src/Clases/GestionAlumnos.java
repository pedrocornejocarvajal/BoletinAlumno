package Clases;
import java.io.*;
import java.util.*;



//import alumno.Alumno;

public class GestionAlumnos {

	/*Interfaz
	 * Comentario: inserta un registro de tipo Alumno en un ficherio de texto
	 * Prototipo: void escribeAlumno(String fichero, alumno)
	 * Entradas: una cadena y un objeto de tipo alumno
	 * Precondiciones: el fichero debe existir
	 * Salidas: ninguna
	 * Postcondiciones: el fichero tendra un registro mas
	 * */
	public static void escribeAlumno(String rutaFichero, Alumno nuevo){
		File fichero = null;
		RandomAccessFile raf = null;
		int[] notas = nuevo.getNotas();				
		
		try{
			fichero = new File(rutaFichero);
			raf = new RandomAccessFile(fichero, "rw");
			raf.seek(raf.length());
			raf.writeInt(nuevo.getMatricula());
			raf.writeBytes(nuevo.getApellidos()+"\n");
			raf.writeBytes(nuevo.getNombre()+"\n");
			for (int i = 0; i < notas.length; i++) {
				raf.writeInt(notas[i]);
			}
		}catch(NullPointerException npe){
			npe.printStackTrace();
		}catch(FileNotFoundException fnf){
			fnf.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		finally{
			try{
				if(fichero!=null)
					raf.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
	}
	
	/*Interfaz
	 * Comentario: lee un registro de un alumno segun su posicion
	 * Prototipo Alumno leeAlumno(String  rutaFichero,int pos)
	 * Entradas: una cadena y un entero
	 * Precondiciones: la ruta del fichero debe existir
	 * Salida: un objeto de tipo Alumno
	 * Postcondiciones: Asociado al nombre se devuelve el objeto leido del registro indicado*/
	public static Alumno leeAlumno(String rutaFichero,int pos){
				
		File fichero = null;
		RandomAccessFile raf = null;
		
		int matricula=0;
		int[] notas = {0,0,0,0};
		String nombre="";
		String apellidos="";
		
		try{
			fichero = new File(rutaFichero);
			raf = new RandomAccessFile(fichero, "r");
			raf.seek(0); //apunta al principio del fichero
			raf.seek((Alumno.SIZE + 2)*(pos)); //+2 por los dos caracteres de salto de linea 
			matricula=raf.readInt();
			apellidos=raf.readLine();
			nombre=raf.readLine();
			for (int i = 0; i < notas.length; i++) {
				notas[i]=raf.readInt();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(raf!=null)
					raf.close();
			}
			catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
		
		return (new Alumno(matricula,nombre,apellidos,notas));
	}
	
	/*Interfaz
	 * Comentario: devuelve una lista de tipo Alumno buscados en el fichero
	 * Prototipo List <Alumno> buscaAlumnosPorNombre(String  rutaFichero, String nombre)
	 * Entradas: dos cadenas
	 * Precondiciones: la ruta del fichero debe existir
	 * Salida: una lista de alumnos
	 * Postcondiciones: Asociado al nombre se devuelve una lista con los alumnos que coincidan en el nombre pasado por parametro*/
	
	public static List <Alumno> buscaAlumnosPorNombre(String path,String nombreBusqueda){
		File fichero = null;
		RandomAccessFile raf = null;
		long tamanoFichero = 0;
		int matricula=0;
		int[] notas = {0,0,0,0};
		String nombre="";
		String apellidos="";
		List <Alumno> aux = new ArrayList<Alumno>();
		
		try{
			fichero = new File(path);
			raf = new RandomAccessFile(fichero, "r");
			raf.seek(0);
			tamanoFichero=raf.length(); //calculo el tama�o del fichero EOF
			while(raf.getFilePointer()<tamanoFichero){
				
				matricula=raf.readInt(); //lectura de la matricula
				apellidos=raf.readLine(); //lectura del apellido
				nombre=raf.readLine();//lectura del nombre
				for (int i = 0; i < notas.length; i++)//lectura de notas
				{
					notas[i]=raf.readInt();
				}
				if(nombre.contains(nombreBusqueda)){
					aux.add(new Alumno(matricula,nombre,apellidos,notas)); //inserto el alumno en la lista
				}
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(raf!=null)
					raf.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}
		return aux;
	}
	
	public static void main(String[] args){
		Alumno uno = new Alumno(1,"Daniel","Rivas Miguel",new int[]{1,1,1,1});
		Alumno dos = new Alumno(2,"Maikel","Lopez Garcia",new int[]{2,2,2,2});
		Alumno tres = new Alumno(3,"Roberto","Gonzalez Serna",new int[]{3,3,3,3});
		Alumno cuatro = new Alumno(4,"Roberto","Somet Laurinho",new int[]{4,4,4,4});
		Alumno cinco = new Alumno(5,"Francisco","Gutierrez Rodriguez",new int[]{5,5,5,5});
				
		escribeAlumno("Alumnos.dat",uno);
		escribeAlumno("Alumnos.dat",dos);
		escribeAlumno("Alumnos.dat",tres);
		escribeAlumno("Alumnos.dat",cuatro);
		escribeAlumno("Alumnos.dat",cinco);
		
		System.out.println(leeAlumno("Alumnos.dat",1));
		System.out.println(leeAlumno("Alumnos.dat",3));
		
		
		List <Alumno> alumnos = buscaAlumnosPorNombre("Alumnos.dat", "Roberto");
		
		for (Alumno alumno : alumnos) {
			System.out.println(alumno);
		}
		
	}//main
		
}//clase
