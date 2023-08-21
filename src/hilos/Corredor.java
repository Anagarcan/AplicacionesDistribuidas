package hilos;

/**
 *
 * @author Ana
 */
public class Corredor
	implements Runnable {

	private String nom;
	private Peligro peligro;

	public Corredor (String name, Peligro per) {
		nom = name;
		peligro = per;
	} // ()

	public void run () {
		// run = main() para un hilo

		Thread qui = Thread.currentThread ();

		System.out.println ("\nthread: " + qui.getName() + "(" + nom + ") llamando al método peligroso: incrementar() ");

		peligro.incrementar (); // este es el método peligroso

		System.out.println ("\n" + nom + ": he acabado");
	} // ()
} // class

