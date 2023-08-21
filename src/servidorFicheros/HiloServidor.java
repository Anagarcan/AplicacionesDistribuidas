package servidorFicheros;

import java.io.*;
import java.net.Socket;

import util.Utilidades;

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

    //......................................................................
    //......................................................................
    public HiloServidor(Socket con)
            throws java.io.IOException {

        // guarda el socket (que me envía ServidorMultiHilo (en while true) )
        this.elSocket = con;

        // obtén streams para  entrada (recepción) y salida (envío)
        this.entrada = con.getInputStream();
        this.salida = con.getOutputStream();

        //
        // Se crea un thread para ejecute esta clase
        // y  se arranca
        //
        Thread th = new Thread(this);
        th.start(); // ahora hay un nuevo thread en run()

    } // ()

    //......................................................................
    //......................................................................
    public void run() {
        try {
            String linea = IO.leeLinea(this.entrada);
            System.out.println(linea);
            String[] trozos = linea.split("[ ]+");

            if(trozos.length !=2){
                IO.escribeLinea("HTTP/1.1 400 Bad Request : la solicitud no tiene 2 palabras", this.salida);
                IO.escribeLinea("", this.salida);
                entrada.close();
                salida.close();
                elSocket.close();
            }

            if(!trozos[0].equals("GET")){
                IO.escribeLinea("HTTP/1.1 400 Bad Request : la solicitud no es GET", this.salida);
                IO.escribeLinea("", this.salida);
                entrada.close();
                salida.close();
                elSocket.close();
            }

            FileInputStream fich = null;
            try{
                fich = new FileInputStream(trozos[1]);
                IO.escribeLinea("HTTP/1.1 200 OK", this.salida);
                IO.escribeLinea("", this.salida);
                int numBytes = IO.copia(fich, this.salida);

            }catch(FileNotFoundException e){
                IO.escribeLinea("HTTP/1.1 404 Not Found : no he encontrado el recurso", this.salida);
                IO.escribeLinea("", this.salida);
                entrada.close();
                salida.close();
                elSocket.close();
            }



        } catch (IOException e) {
            System.out.println("Error en entrada/salida" + e);
        }


    }
} // class