package hilos;

/**
 *
 * @author Ana
 */
public class Peligro {

	private int valor = 0;

	/*
	 * incrementar() es peligroso si dos threads
	 * lo llaman al mismo tiempo, no por sus variables
	 * locales (que son distintas para cada thread), sino por la variable
	 * global valor (que es la misma para todos los threads), ya que ambos threads
	 * estarían  manipulándola a la vez
	 *
	 */
	public void incrementar() {

		try {

			int aux;

			// esta parte no es sección crítica

			// Probad a poner y quitar el synchronized
			// (cuando no esté puesto, valor será inconsistente)
			synchronized (this) {
				System.out.println("\n\t\t **** thread que entra en sección crítica: " + Thread.currentThread().getName());

				aux = valor; // lectura explícita de la variable global común
				// cuando no está el synchronized, los dos threads
				// ven EL MISMO valor

				aux = aux + 1; // sumamos 1 a la copia de valor

				Thread.sleep(3000); // duermo el thread antes de que escriba el valor
				// para dar tiempo a que el segundo thread lea el mismo
				// valor antes de escribirlo

				valor = aux; // escribo el nuevo valor
				// si los dos threads han visto el mismo valor
				// a efectos prácticos solo habrá habido un incremento

				System.out.println("\n\t\t **** thread que sale en sección crítica: " + Thread.currentThread().getName());
				//} // synchronized

				// esta parte no es sección crítica
				// ...
			}
			} catch(InterruptedException ex){
				System.out.println("Thread.sleep() se ha interrumpido");
			}
		} // ()

	public int dimeValor() {
		return valor;
	} // ()

} // class