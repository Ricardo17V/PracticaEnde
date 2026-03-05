/**
 * @author ricardo
 * @version v.1.0.Drones
 * @date 27/02/2026
 */
package drones;

/**
 * Clase abstracta que representa la base común de todos los drones
 * Define los atributos generales como identificador, autonomía máxima, velocidad máxima y estado operativo
 * Las clases hijas deben implementar el método calcularTiempoRespuesta()
 */
public abstract class Base {

	/**
	 * Identificador único del dron
	 */
	private int id;

	/**
	 * Autonomía máxima del dron (tiempo máximo que puede operar)
	 */
	private int autonomiaMax;

	/**
	 * Velocidad máxima del dron
	 */
	private double velocidadMax;

	/**
	 * Indica si el dron está operativo o no
	 */
	private boolean operativo;

	/**
	 * Constructor parametrizado que inicializa todos los atributos del dron
	 * @param id identificador único del dron
	 * @param autonomiaMax autonomía máxima disponible
	 * @param velocidadMax velocidad máxima que puede alcanzar
	 * @param operativo indica si el dron está operativo
	 */
	public Base(int id, int autonomiaMax, double velocidadMax, boolean operativo) {
		this.id = id;
		this.autonomiaMax = autonomiaMax;
		this.velocidadMax = velocidadMax;
		this.operativo = operativo;
	}

	/**
	 * Constructor vacío, permite crear un objeto sin inicializar los atributos
	 */
	public Base() {

	}

	/**
	 * Comprueba si el dron está operativo
	 * Si el dron no está operativo, lanza una excepción
	 * @throws Exception si el dron no está operativo
	 */
	public void comprobarEstado() throws Exception {
		if (!operativo) {
			throw new Exception("El dron no está operativo.");
		}
	}

	/**
	 * Calcula el tiempo de respuesta del dron para una distancia dada.
	 * Este método debe ser implementado por las clases hijas.
	 * @param distancia distancia que debe recorrer el dron
	 * @return tiempo estimado de respuesta
	 * @throws Exception si ocurre algún problema en el cálculo
	 */
	public abstract double calcularTiempoRespuesta(double distancia) throws Exception;

	/**
	 * Obtiene el identificador del dron.
	 * @return el id del dron
	 */
	public int getId() {
		return id;
	}

	/**
	 * Obtiene la autonomía máxima del dron.
	 * @return autonomía máxima disponible
	 */
	public int getAutonomiaMax() {
		return autonomiaMax;
	}

	/**
	 * Obtiene la velocidad máxima del dron.
	 * @return velocidad máxima del dron
	 */
	public double getVelocidadMax() {
		return velocidadMax;
	}

	/**
	 * Indica si el dron está operativo.
	 * @return true si está operativo, false en caso contrario
	 */
	public boolean isOperativo() {
		return operativo;
	}
}