package ServidorBanco;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

/**
 * @author Ana
 */
public class HiloServidor
        implements Runnable {

    // socket donde estamos conectados
    private Socket elSocket;

    // stream para leer (recibir)
    private InputStream entrada;
    // stream para escribir (enviar)
    private OutputStream salida;
    private String CuentaBancaria;
    private Banco b;

    //......................................................................
    //......................................................................
    public HiloServidor(Socket con)
            throws IOException {

        // guarda el socket (que me envía ServidorMultiHilo (en while true) )
        this.elSocket = con;

        // obtén streams para  entrada (recepción) y salida (envío)
        this.entrada = con.getInputStream();
        this.salida = con.getOutputStream();
        b = new Banco();
        //
        // crea un trhead para ejecute esta clase
        // y arráncalo
        //

        Thread th = new Thread(this);
        th.start(); // ahora hay un nuevo thread en run()

    } // ()

    //......................................................................
    //......................................................................
    public void run() {
        try {
            String linea = servidorFicheros.IO.leeLinea(this.entrada);
            System.out.println(linea);
            String[] trozos = linea.split(";");

            if (trozos[0].equals("CREAR_CUENTA")) {
                if (trozos.length == 3) {
                    String usuario = trozos[1];
                    String numeroCuenta = trozos[2];
                    b.crearCuenta(usuario, numeroCuenta);
                    IO.escribeLinea("Tu cuenta se ha creado correctamente", this.salida);
                }
                entrada.close();
                salida.close();
                elSocket.close();

            } else if (trozos[0].equals("GET_SALDO")) {
                if (trozos.length == 2) {
                    String usuario = trozos[1];
                    double saldo = b.getCuentaBancaria(usuario).getSaldo();
                    IO.escribeLinea("Tu saldo es " + saldo, this.salida);
                }
                entrada.close();
                salida.close();
                elSocket.close();

            } else if (trozos[0].equals("HACER_INGRESO")) {
                if (trozos.length == 3) {
                    String usuario = trozos[1];
                    double importeIngreso = Double.parseDouble(trozos[2]);
                    b.hacerIngreso(usuario, importeIngreso);
                    IO.escribeLinea("Tu ingreso de " + importeIngreso + " €" + " se ha realizado correctamente", this.salida);
                }
                entrada.close();
                salida.close();
                elSocket.close();

            } else if (trozos[0].equals("RETIRAR")) {
                if (trozos.length == 3) {
                    String usuario = trozos[1];
                    double importeRetiro = Double.parseDouble(trozos[2]);
                    b.retirar(usuario, importeRetiro);
                    IO.escribeLinea("Se han retirado " + importeRetiro + "€ correctamente", this.salida);
                }
                entrada.close();
                salida.close();
                elSocket.close();

            } else if (trozos[0].equals("HACER_TRANSFERENCIA")) {
                if (trozos.length == 4) {
                    String usuarioOrigen = trozos[1];
                    String usuarioDestino = trozos[2];
                    double importeTransferencia = Double.parseDouble(trozos[3]);
                    b.hacerTransferencia(usuarioOrigen, usuarioDestino, importeTransferencia);
                    IO.escribeLinea("La transferencia se ha realizado con éxite", this.salida);
                }
                entrada.close();
                salida.close();
                elSocket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            try {
                IO.escribeLinea("Ha ocurrido un error procesando su orden: " + e.getMessage(), this.salida);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.out.println("ha habido un error");
        }

    }

    private void muestraError(String mensaje) throws IOException {
        IO.escribeLinea("HTTP/1.1 400 Bad Request : " + mensaje, this.salida);
        IO.escribeLinea("", this.salida);
        entrada.close();
        salida.close();
        elSocket.close();

    }
} // class
