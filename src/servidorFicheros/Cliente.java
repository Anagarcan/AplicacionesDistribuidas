package servidorFicheros;

import java.io.*;
import java.net.*;
import util.Utilidades;

/**
 *
 * @author Ana
 */
public class Cliente {

	
	public static void main(String[] args) {
		//File dir = new File("test");
		//if(!dir.exists())
		//	dir.mkdir();
		String hostServidor = "localhost";

		String nombreRecurso = "hola.txt";

		int PUERTO = 1234;

		// saca el el nombre del servidor, el puerto y nombre del fichero de la linea de comandos
		if (args.length == 3) {
			hostServidor = args[0];
			PUERTO = Integer.parseInt(args[1]);
			nombreRecurso = args[2];
		}

		// empezamos
		Socket sock;

		try {
			sock = new Socket(hostServidor, PUERTO);
		} catch (IOException ex) {
			System.out.println("No se ha podido conectar " + ex.getMessage());
			return;
		}

		//
		// ya estoy conectado
		//
		Utilidades.muestraMensajeC("ya estoy conectado con " + hostServidor + ":" + PUERTO);

		try {

			//
			// obtengo streams para leer y escribir a través del socket (en modo binario)
			//
			InputStream entrada = sock.getInputStream();
			OutputStream salida = sock.getOutputStream(); 

			//
			// envio el mensaje
			//
			String solicitud = Utilidades.leerTextoC("GET");

			Utilidades.muestraMensajeC("orden que envio >" + solicitud + "<"); // informo (localmente)

			// Envía la solicitud al servidor a través de salida
			IO.escribeLinea("GET " + solicitud, salida);

			//
			// ahora, espero la respuesta
			// Lee la respuesta del servidor y luego una línea vacía con IO.leeLinea()
			String respuesta = IO.leeLinea(entrada);
			String vacía = IO.leeLinea(entrada);

			Utilidades.muestraMensajeC("el servidor me ha respondido  >" + respuesta + "<");

			// troceo la respuesta, para ver qué hay
			String[] trozos = respuesta.split("[ ]+");

			String codigoError = trozos[1]; // el codigo de error es la segunda palabra

			// Si el string codigoError no es "200"
			// avisa de que ha habido problemas y termina con return.
			if (!codigoError.equals("200")) {
				System.out.println("Ha habido un error");
				return;
			}
			// ...

			// si continuamos todo está yendo bien
			Utilidades.muestraMensajeC("el servidor ha aceptado la solicitud");

			// intento abrir un fichero local para guardar lo que me envían (modo binario)
			FileOutputStream fich;
			try {
				fich = new FileOutputStream(Utilidades.leerTextoC("Dime el fichero ya"));
			} catch (java.io.IOException delfin) {
				System.out.println("Error malo" + delfin.getMessage());
				entrada.close();
				salida.close();
				sock.close();
				return;
			}

			
			
			// Se copia con IO.copia los bytes que nos llegan de entrada al fichero fich
			int numBytes = IO.copia(entrada, fich); // cambia el 0 por la llamada a IO.copia

			// cierro los stream
			fich.close();
			entrada.close();
			salida.close();
			sock.close();

			// POR FIN !
			Utilidades.muestraMensajeC("Recibidos " + numBytes + " bytes");
			Utilidades.muestraMensajeC(" *** FINAL FELIZ *** ");
			Utilidades.muestraMensajeG(" *** FINAL FELIZ *** ");

		} catch (IOException ex) {
			Utilidades.muestraMensajeC("se produjo un error de entrada/salida");
		}
	} // ()
} // class

