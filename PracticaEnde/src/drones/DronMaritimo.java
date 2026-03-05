package drones;

public class DronMaritimo extends Base {

    private int resistenciaSalinidad;

    public DronMaritimo(int id, int autonomia, double velocidad, boolean operativo, int resistenciaSalinidad) {
        super(id, autonomia, velocidad, operativo);
        this.resistenciaSalinidad = resistenciaSalinidad;
    }

    public DronMaritimo() {
    }

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