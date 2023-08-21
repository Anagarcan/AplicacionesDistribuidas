package ficheros;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import util.Utilidades;

/**
 *
 * @author Ana
 */
public class UsarFicherosDeTexto {

	public static void main(String[] args) {

		try {

			String nombreFicheroLeer = Utilidades.leerTextoC("dime el nombre de un fichero DE TEXTO para leer (uno que no aprecies)"); // RUTA
			String nombreFicheroEscribir = Utilidades.leerTextoC("dime el nombre de un fichero para escribir");

			// 1.
			// abro fichero de lectura
			FileReader fL = new FileReader(nombreFicheroLeer);
			// del FileReader saco un BufferedReader, para
			// leer lineas con readLine()
			BufferedReader entrada = new BufferedReader(fL);

			// 2.
			// abro fichero de escritura
			// (lo crea si no existe. Si existe, lo sobre-escribirá)
			FileWriter fE = new FileWriter(nombreFicheroEscribir);
			// del FileWriter saco un PrintWriter para escribir líneas con println()
			PrintWriter salida = new PrintWriter(fE);

			// 3.
			//
			// leo una linea de entrada
			// y la pongo en salida
			//
			int nLin = 0;
			String linea;
			while ((linea = entrada.readLine()) != null) {
				nLin++;
				Utilidades.muestraMensajeC("linea " + nLin + " = " + linea);
				salida.println(linea);
			}

			// 4.
			//
			// cierro los flujos
			//
			entrada.close();
			salida.close();
			fE.close();
			fL.close();

			// 5.
			// ahora reabro el primer fichero pero para escribir algo
			salida = new PrintWriter( new FileWriter(nombreFicheroLeer) );

			salida.println ("este fichero contenía " + nLin + " lineas (espero que no fuera importante)");
			salida.println ("de todas formas lo copié a " + nombreFicheroEscribir);

			salida.close();

			// 6.
			// pruebo la utilidad  leeFicheroTexto()
			String contenido = leeFicheroTexto(nombreFicheroEscribir);


			Utilidades.muestraMensajeC(contenido);

		// excepciones
		} catch (FileNotFoundException err) {

			System.out.println("fichero no encontrado: " + err.getMessage());

		} catch (IOException err) {

			System.out.println("error entrada/salida: " + err.getMessage());

		}

	} // ()

	//.........................................................................
	// función de utilidad que lee  un fichero de texto completo
	// y lo devuelve en un String
	//.........................................................................
	public static String leeFicheroTexto (String nombre)
		throws FileNotFoundException, IOException {

		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(nombre));
			byte[] losBytes = new byte[dis.available()];
			// carga todos los bytes del fichero, de golpe
			dis.read(losBytes, 0, dis.available());
			dis.close();
			// devuelve un string a partir de los bytes del fichero
			return new String(losBytes);
		} catch (IOException ex) {
		}
		return null;
	} // ()

	//.........................................................................
	// función de utilidad que lee  un fichero de texto completo
	// y lo devuelve en un String
	// OTRA FORMA DE HACERLO
	//.........................................................................
	public static String leeFicheroTextoBis (String nombre)
		throws FileNotFoundException, IOException {

		FileReader f = new FileReader (nombre);
		StringBuilder sb = new StringBuilder();

		// bucle que lee los bytes de uno en uno
		// los acumula en sb, y después los devolveremos
		// en un string
		int car;
		while (  (car = f.read ()) > 0 ) {
			sb.append((char) car);
		}

		f.close();
		return sb.toString();
	} // ()

} // class

