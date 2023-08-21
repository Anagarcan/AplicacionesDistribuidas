
package echoTCP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Ana
 */
public class HiloServidor
	implements Runnable
{

	// socket donde estamos conectados
	private Socket elSocket;

	// stream para leer (recibir)
	private BufferedReader entrada;

	// stream para escribir (enviar)
	private PrintWriter salida;

	//......................................................................
	//......................................................................
	public HiloServidor (Socket con)
		throws java.io.IOException
	{

		// guarda el socket (que me envía ServidorMultiHilo (el del while true) )
		this.elSocket = con;

		// obtén streams para  entrada (recepción) y salida (envío)
		// (de este modo puedo leer y escribir lineas enteras (modo texto))
		this.entrada = new BufferedReader(new InputStreamReader(con.getInputStream()));
		this.salida = new PrintWriter(con.getOutputStream());

		//
		// crea un trhead para ejecute esta clase
		// y arráncalo
		//
		Thread th = new Thread (this);
		th.start (); // ahora hay un nuevo thread en run()

	} // ()

	//......................................................................
	//......................................................................
	public void run ()
	{

		// aquí empieza el nuevo thread que realizará *de verdad*
		// el trabajo

		String linea;

		try {

			// leo (recibo)
			linea = entrada.readLine();

			// aviso
			System.out.println("he recibido >" + linea + "< ");

			//
			// respondo (escribo)
			//
			salida.println ( "'" + linea + "'"+ " mide "  + linea.length());
			salida.flush ();

			// aviso final
			System.out.println (" servicio termina ");

			//
			// cierro streams y socket
			//
			salida.close ();
			entrada.close ();
			elSocket.close ();

			//
			// tratamiento de excepciones
			//
		} catch (java.io.IOException e) {
			// aviso
			System.out.println ("error leyendo o escribiendo");
			// termino
			return;

		}  // catch

	} // ()

} // class

