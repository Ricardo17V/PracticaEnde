package drones;

/**
 * Representa un dron especializado para operaciones en zonas de montaña.
 * Extiende la funcionalidad básica de un dron añadiendo un factor de altitud
 * que afecta al cálculo del tiempo de respuesta.
 * 
 * @author Antonio Toret Cárdenas
 * @date 2026-03-05
 */
public class DronMontana extends Base {

    /** Factor que incrementa el tiempo de respuesta debido a la altitud */
    private double factorAltitud;

    /**
     * Constructor del dron de montaña.
     * 
     * @param id identificador del dron
     * @param autonomiaMax autonomía máxima disponible
     * @param velocidadMax velocidad máxima del dron
     * @param operativo indica si el dron está operativo
     * @param factorAltitud factor que modifica el tiempo de respuesta
     * @throws IllegalArgumentException si el factor de altitud es menor o igual que 0
     */
    public DronMontana(int id, int autonomiaMax, double velocidadMax, boolean operativo, double factorAltitud) {
        super(id, autonomiaMax, velocidadMax, operativo);

        if (factorAltitud <= 0) {
            throw new IllegalArgumentException("El factor de altitud debe ser mayor que 0");
        }

        this.factorAltitud = factorAltitud;
    }

    /**
     * Calcula el tiempo de respuesta del dron para una misión dada.
     * 
     * @param distancia distancia de la misión
     * @return tiempo estimado de respuesta
     * @throws Exception si el dron no está operativo, la distancia es negativa
     * o el tiempo supera la autonomía disponible
     */
    @Override
    public double calcularTiempoRespuesta(double distancia) throws Exception {

        comprobarEstado();

        if (distancia < 0) {
            throw new Exception("La distancia no puede ser negativa");
        }

        double tiempo = distancia / getVelocidadMax();

        tiempo = tiempo * factorAltitud;

        if (tiempo > getAutonomiaMax()) {
            throw new Exception("Tiempo de respuesta superior a la autonomía del dron");
        }

        return tiempo;
    }

    /**
     * Devuelve el factor de altitud del dron.
     * 
     * @return factor de altitud
     */
    public double getFactorAltitud() {
        return factorAltitud;
    }
}