package ficheros;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import util.Utilidades;

/**
 *
 * @author Ana
 */
public class UsarFicherosBinarios {

	public static void main(String[] args) {

		try {

			String nombreFicheroLeer = Utilidades.leerTextoC("dime el nombre de un fichero para leer: ");
			String nombreFicheroEscribir = Utilidades.leerTextoC("dime el nombre de un fichero para escribir: ");

			// 1.
			// abro fichero de lectura (binario)
			FileInputStream entrada = new FileInputStream(nombreFicheroLeer);

			// 2.
			// abro fichero de escritura (binario)
			// (lo crea si no existe. Si exite, lo sobre-escribirá)
			FileOutputStream salida = new FileOutputStream(nombreFicheroEscribir);

			// 3.
			//
			// bucle que lee un byte de entrada y lo escribe en salida (realiza una copia, pues)
			int unByteLeido;
			int numBytes = 0;
			while ((unByteLeido = entrada.read()) != -1) {
				numBytes++;
				salida.write(unByteLeido);
			}

			// información
			Utilidades.muestraMensajeC("copiados " + numBytes + " bytes");

			// 4.
			//
			// cierro los flujos
			//
			entrada.close();
			salida.close();

			// 5.
			// la misma copia mediante la utilidad leeFichero()
			// y sin utilizar bucles
			byte[] contenido = leeFichero(nombreFicheroLeer);
			salida = new FileOutputStream(nombreFicheroEscribir + "_bis");
			salida.write(contenido);


			// excepciones
		} catch (FileNotFoundException err) {

			System.out.println("fitchero no encontrado: " + err.getMessage());

		} catch (IOException err) {

			System.out.println("error entrada/salida: " + err.getMessage());

		}

	} // ()

	//.........................................................................
	// función de utilidad que lee todo un fichero de cualquier tipo (binario o texto)
	// y lo devuelve en un array de bytes
	//.........................................................................
	public static byte[] leeFichero (String nombre) {
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(nombre));
			byte[] losBytes = new byte[dis.available()];
			// lee todos los bytes de golpe
			dis.read(losBytes, 0, dis.available());
			dis.close();
			return losBytes;
		} catch (IOException ex) {
		}
		return null;
	} // ()
} // class
