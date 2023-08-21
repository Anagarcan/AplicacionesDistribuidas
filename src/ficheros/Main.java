
package ficheros;


import util.Utilidades;

import java.io.*;

public class Main {

	public static void main (String[] args) {


		try {
			//LA RUTA DEL ARCHIVO DONDE ESCRIBIR
			String nombreFicheroEscribir = Utilidades.leerTextoC("dime el nombre de un fichero para escribir");

			//CREA EL ARCHIVO DONDE ESCRIBIR
			FileWriter fE =new FileWriter(nombreFicheroEscribir);
			// del FileWriter saco un PrintWriter para escribir líneas con println()
			PrintWriter salida = new PrintWriter(fE);

			//LINEA QUE VOY A ESCRIBIR
			String linea = "Luis Balaguer Senabre";

			//Imprimo en el archivo dado anteriormente
			salida.println(linea);

			//CERRAR LOS FLUJOS
			salida.close();
			fE.close();

		} catch (IOException e) {
			System.out.println("No se ha podido acceder al archivo especificado");
		}


	}
	 // ()
} // class
