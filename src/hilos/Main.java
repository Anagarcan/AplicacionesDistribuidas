package hilos;

/**
 *
 * @author Ana
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		//
		// crea UN objeto de la clase Peligro
		// crea DOS objetos corredores
		// que utlizan EL MISMO objeto Peligro
		// (si no, no hay peligro :-) )
		//
		Peligro peli = new Peligro();
		Corredor alo = new Corredor("Alonso", peli);
		Corredor vet = new Corredor("Vetel", peli);

		//
		// Se crean 2 trheads y se les da a ejecutar
		// instancias ditintas de la clase Corredor
		//
		Thread th1 = new Thread(alo); // th1 ejecutará el run de Alonso
		Thread th2 = new Thread(vet); // th2 ejecutará el run de Vetel

		//
		// hace que efectivamente el metodo run()
		// se ejecute
		//
		th1.start(); // ALO
		th2.start(); // VET
		//

		//
		// join: hace que el trhead de main() (el original)
		// espere que termine cada uno de los otros 2 trheads
		//  th1 (ALO) y th2 (VET)
		//
		th1.join();
		th2.join();

		System.out.println("\nvalor = " + peli.dimeValor() +  "(debería ser 2)");
	} // ()
} // class
