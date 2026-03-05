/**
 * @author ricardo
 * @version 1.0
 * @date 27/02/2026
 */
package drones;
/**
 * Clase que representa un dron marítimo
 * Hereda de la clase Base y añade una característica específica: resistencia a la salinidad.
 */
public class DronMaritimo extends Base {

    /**
     * nivel de resistencia del dron a la salinidad del agua.
     */
    private int resistenciaSalinidad;

    /**
     * Constructor parametrizado del dron marítimo
     * @param id identificador único del dron
     * @param autonomia autonomía máxima del dron
     * @param velocidad velocidad máxima del dron
     * @param operativo indica si el dron está operativo
     * @param resistenciaSalinidad nivel de resistencia a la salinidad
     */
    public DronMaritimo(int id, int autonomia, double velocidad, boolean operativo, int resistenciaSalinidad) {
        super(id, autonomia, velocidad, operativo);
        this.resistenciaSalinidad = resistenciaSalinidad;
    }

    /**
     * Constructor vacío del dron marítimo
     */
    public DronMaritimo() {

    }

    /**
     * Calcula el tiempo de respuesta del dron marítimo en función de la distancia
     * Proceso
     * -Comprueba si el dron está operativo
     * -Verifica que la distancia no sea negativa
     * -Calcula el tiempo base (distancia / velocidad)
     * -Si la resistencia a la salinidad es baja (< 30), aumenta el tiempo un 20%
     * -Comprueba que el tiempo no supere la autonomía máxima
     * 
     * @param distancia distancia que debe recorrer el dron
     * @return tiempo estimado de respuesta
     * @throws Exception si:
     * -el dron no está operativo
     * -la distancia es negativa
     * -el tiempo calculado supera la autonomía disponible
     */
    @Override
    public double calcularTiempoRespuesta(double distancia) throws Exception {

        comprobarEstado();

        if (distancia < 0) {
            throw new Exception("la distancia no puede ser negativa");
        }

        double tiempo = distancia / getVelocidadMax();

        if (resistenciaSalinidad < 30) {
            tiempo *= 1.20;
        }

        if (tiempo > getAutonomiaMax()) {
            throw new Exception("el tiempo excede la autonomía disponible");
        }

        return tiempo;
    }
}