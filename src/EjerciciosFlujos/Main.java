package EjerciciosFlujos;

import util.Utilidades;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        double media = ejercicioMedia("C:\\Users\\ana_\\Desktop\\Git\\AplicacionesDistribuidas\\Ejemplo.txt");
        System.out.println("LA MEDIA DE LOS NÚMEROS DEL FICHERO ES: " + media);
    }

    //Ejercicio 1: Hacer una función que escriba desde 1 hasta n los números en un fichero de texto y cada 100
    //imprima un salto de linea
    public static void escribeEnteros(String ruta) {
        int n = Utilidades.leerEnteroC("dime n");

        try {
            //Abro los flujos
            FileWriter fW = new FileWriter(ruta);

            //Trabajar con los flujos
            for (int i = 1; i <= n; i++) {
                if (i % 100 == 0) {
                    fW.write(i + "\n");
                } else {
                    fW.write(i + " ");
                }
            }

            //Cerrar flujos
            fW.close();

        } catch (IOException e) {
            System.out.println("Error en I/O" + e);
        }

    }
    //Ejercicio 2: Voy a leer de un fichero de texto que contiene un numero por linea.
    //voy a querer hacer la media de los números que encontremos dentro

    public static double ejercicioMedia(String ruta) {
        int nLin = 0;
        double total = 0;
        String linea;

        try {
            //Creamos los flujos
            FileReader fR = new FileReader(ruta);
            BufferedReader bR = new BufferedReader(fR);

            //Trabajar con los flujos
            linea = bR.readLine();
            while (linea != null) {
                nLin++;
                total = total + Double.parseDouble(linea);
                linea = bR.readLine();
            }

            //Cerrar flujos
            fR.close();
            bR.close();

        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo" + e);
        } catch (IOException e) {
            System.out.println("Error I/O" + e);
        }

        if (nLin == 0) {
            return 0;
        }
        double media = total / nLin;
        return media;
    }

}
