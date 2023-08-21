package util;

import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana
 */

//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
public class Utilidades {

	private final static BufferedReader entradaConsola = new java.io.BufferedReader ( new java.io.InputStreamReader (System.in));

	//.........................................................................
	//.........................................................................
	public static void muestraMensajeC (String mensaje) {
		System.out.println (mensaje);
	}

	//.........................................................................
	//.........................................................................
	public static int leerEnteroC (String mensaje) {
		return Integer.parseInt( leerTextoC(mensaje));
	}

	//.........................................................................
	//.........................................................................
	public static double leerRealC (String mensaje) {
		return  Double.parseDouble( leerTextoC(mensaje));
	}

	//.........................................................................
	//.........................................................................
	public static String leerTextoC (String mensaje) {
		try {
			System.out.println(mensaje);
			return entradaConsola.readLine();
		} // ()
		catch (IOException ex) {
		}
		return null;
	} // ()

	//.........................................................................
	//.........................................................................
	public static String leerTextoG (String mensaje) {
		return  JOptionPane.showInputDialog(mensaje);
	} // ()

	//.........................................................................
	//.........................................................................
	public static int leerEnteroG (String mensaje) {
		int v = Integer.parseInt( leerTextoG(mensaje));
		return v;
	} // ()

	//.........................................................................
	//.........................................................................
	public static double leerRealG (String mensaje) {
		return  Double.parseDouble( leerTextoG(mensaje));
	} // ()

	//.........................................................................
	//.........................................................................
	public static void muestraMensajeG (String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	} // ()


		//.........................................................................
	//.........................................................................
	public static  void copiarBytes (byte[] destino, int inicioDestino, byte[] origen, int inicioOrigen, int longitud) {
		for (int i=0; i<=longitud-1; i++) {
			destino[inicioDestino+i] = origen[inicioOrigen+i];
		}
	} // ()

	//.........................................................................
	//.........................................................................
	public static byte[] juntarBytes (byte[] ... lista) {
		int totalBytes = 0;
		for (byte[] arr : lista) {
			totalBytes += arr.length;
		}

		byte[] result = new byte[totalBytes];
		int inicioDestino = 0;

		for (byte[] arr : lista) {
			copiarBytes(result, inicioDestino, arr, 0, arr.length);
			inicioDestino += arr.length;
		}


		return result;
	} // ()

	//public static byte[] trimByte(); // se puede hacer con Arrays.copyOf
} // class
