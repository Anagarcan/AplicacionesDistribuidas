package servidorFicheros;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author Ana
 */
public class ServidorMultiHilo {

	public static final int PUERTO = 1234;


	public static void main(String[] aArgs)
			throws java.io.IOException
	{

		ServerSocket socketServidor;
		Socket	socketconectado;


		System.out.println("*****************************************************");
		System.out.println("**      servidor ficheros                         **");
		System.out.println("*****************************************************");
		System.out.println("Para contactar conmigo la solicitud es ");
		System.out.println ("\n GET  <nombreFichero>");
		System.out.println("*****************************************************");
		System.out.println("yo estoy en: ");
		System.out.println ("\n       "+InetAddress.getLocalHost().getHostAddress() +" " + PUERTO);
		System.out.println ("       "+InetAddress.getLocalHost().getHostName() +" " + PUERTO);
		System.out.println("*****************************************************");


		//
		// creo un socket de servidor
		//
		socketServidor = new ServerSocket (PUERTO);

		//
		// por siempre
		//
		while (true) {

			// accepto conexions: aquí espero
			socketconectado = socketServidor.accept ();

			//
			// cuando sigo: alguien se ha conectado
			//
			System.out.println("conexión establecida con un cliente");

			//
			// creo un objeto que realizará el trabajo
			// con un thread propio
			//
			new HiloServidor (socketconectado);
			// una vez hecho, me vuelvo a esperar

		} // while true

	} // ()

} // class
