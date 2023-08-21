package ServidorBanco;

import util.Utilidades;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author Ana
 */
public class Cliente {

    public static final int CODIGO_CREAR_CUENTA = 1;
    public static final int CODIGO_VER_SALDO = 2;
    public static final int CODIGO_INGRESAR = 3;
    public static final int CODIGO_RETIRAR = 4;
    public static final int CODIGO_HACER_TRANSFERENCIA = 5;

    /*
     * main INCOMPLETO ! Busca COMPLETAR y haz lo que dice
     *
     * (Hay varios COMPLETAR hasta COMPLETAR-ULTIMO )
     */
    public static void main(String[] args) {
        //File dir = new File("test");
        //if(!dir.exists())
        //	dir.mkdir();
        String hostServidor = "localhost";

        String usuario = Utilidades.leerTextoC("Dime tu usuario");

        int PUERTO = 1234;

        // saca el el nombre del servidor, el puerto y nombre del fichero de la linea de comandos
        if (args.length == 3) {
            hostServidor = args[0];
            PUERTO = Integer.parseInt(args[1]);
            usuario = args[2];
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

            System.out.println("1-CREAR CUENTA");
            System.out.println("2-VER SALDO");
            System.out.println("3-INGRESAR;");
            System.out.println("4-RETIRAR");
            System.out.println("5-HACER TRANSFERENCIA");

            String solicitud = Utilidades.leerTextoC("¿Qué operación quieres hacer?");

            int codigoSolicitud = Integer.parseInt(solicitud);

            if (codigoSolicitud == CODIGO_CREAR_CUENTA) {
                String numeroDeCuenta = Utilidades.leerTextoC("dime un usuario");
                IO.escribeLinea("CREAR_CUENTA;" + usuario + ";" + numeroDeCuenta, salida);
                String resp = IO.leeLinea(entrada);
                System.out.println(resp);
            } else if (codigoSolicitud == CODIGO_VER_SALDO) {
                IO.escribeLinea("GET_SALDO;" + usuario, salida);
                String resp = IO.leeLinea(entrada);
                System.out.println(resp);

            } else if (codigoSolicitud == CODIGO_INGRESAR) {
                String mensajeingreso = Utilidades.leerTextoC("dime la cantidad que quieres transferir");
                double importeIngreso = Double.parseDouble(mensajeingreso);
                IO.escribeLinea("HACER_INGRESO;" + usuario + ";" + importeIngreso, salida);
                String resp = IO.leeLinea(entrada);
                System.out.println(resp);
            } else if (codigoSolicitud == CODIGO_RETIRAR) {
                String retirada = Utilidades.leerTextoC("dime la cantidad que quieres retirar");
                double importeRetirar = Double.parseDouble(retirada);
                IO.escribeLinea("RETIRAR;" + usuario + ";" + importeRetirar, salida);
                String resp = IO.leeLinea(entrada);
                System.out.println(resp);

            } else if (codigoSolicitud == CODIGO_HACER_TRANSFERENCIA) {
                String titularDestinatario = Utilidades.leerTextoC("dime el titular destinatario");
                String transferencia = Utilidades.leerTextoC("dime la cantidad que quieres transferir");
                double importeTransferencia = Double.parseDouble(transferencia);
                IO.escribeLinea("HACER_TRANSFERENCIA;" + usuario + ";" + titularDestinatario + ";" + importeTransferencia, salida);
                String resp = IO.leeLinea(entrada);
                System.out.println(resp);
            }

            Utilidades.muestraMensajeC("orden que envio >" + solicitud + "<"); // informo (localmente)

        } catch (IOException ex) {
            Utilidades.muestraMensajeC("se produjo un error de entrada/salida");
        }
    } // ()
} // class

