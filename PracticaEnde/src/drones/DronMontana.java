package drones;

public class DronMontana extends Base {

    private double factorAltitud;

    public DronMontana(int id, int autonomiaMax, double velocidadMax, boolean operativo, double factorAltitud) {
        super(id, autonomiaMax, velocidadMax, operativo);

        if (factorAltitud <= 0) {
            throw new IllegalArgumentException("El factor de altitud debe ser mayor que 0");
        }

        this.factorAltitud = factorAltitud;
    }

    @Override
    public double calcularTiempoRespuesta(double distancia) throws Exception {

        // 1. Revisar estado del dron
        comprobarEstado();

        // 5. Distancia negativa
        if (distancia < 0) {
            throw new Exception("La distancia no puede ser negativa");
        }

        // 2. Calcular tiempo base
        double tiempo = distancia / getVelocidadMax();

        // 3. Aplicar factor de altitud
        tiempo = tiempo * factorAltitud;

        // 4. Comprobar autonomía
        if (tiempo > getAutonomiaMax()) {
            throw new Exception("Tiempo de respuesta superior a la autonomía del dron");
        }

        return tiempo;
    }

    public double getFactorAltitud() {
        return factorAltitud;
    }
}