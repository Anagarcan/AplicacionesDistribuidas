package echoTCP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author Ana
 */
public class Servidor {

	public static final int PUERTO = 1234;

	public static void main(String[] args)
		throws java.io.IOException {

		Socket socketConectado;
		String linea;
		BufferedReader entrada;
		PrintWriter salida;
		ServerSocket socketServidor;

		System.out.println("*****************************************************");
		System.out.println("Para contactar conmigo: ");
		System.out.println ("\n       "+InetAddress.getLocalHost().getHostAddress() +" " + PUERTO);
		System.out.println ("       "+InetAddress.getLocalHost().getHostName() +" " + PUERTO);
		System.out.println("*****************************************************");

		//
		// creo un socket de servidor
		//
		socketServidor = new ServerSocket(PUERTO);

		//
		// por siempre
		//
		while (true) {

			System.out.print("espero conexiones ... ");

			//
			// acepto conexiones (me paro si no hay)
			//
			socketConectado = socketServidor.accept();

			//
			// cuando sigo: hay una conexión
			//
			System.out.println(" conexión establecida");

			//
			// saco streams (flujos) para leer y escribir, asociados al socket conectado
			// (de esta forma puedo trabajar con  lineas de texto completas)
			//
			entrada = new BufferedReader(new InputStreamReader(socketConectado.getInputStream()));
			salida = new PrintWriter(socketConectado.getOutputStream());

			//
			// leo una linea de texto = RECIBIR
			//
			linea = entrada.readLine();

			System.out.println("he recibido >" + linea + "<");

			//
			// respondo (ESCRIBIR) haciendo echo
			//
			salida.println( linea );
			salida.flush(); // que se envíe ya

			//
			// cierro los streams
			//
			entrada.close();
			salida.close();
			socketConectado.close(); // cierro la conexion (con este cliente)

		} // while true
	} // ()

} // class

