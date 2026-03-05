package drones;

public abstract class Base {

	private int id;
	private int autonomiaMax;
	private double velocidadMax;
	private boolean operativo;

	public Base(int id, int autonomiaMax, double velocidadMax, boolean operativo) {
		this.id = id;
		this.autonomiaMax = autonomiaMax;
		this.velocidadMax = velocidadMax;
		this.operativo = operativo;
	}

	public Base() {
	}

	public void comprobarEstado() throws Exception {
		if (!operativo) {
			throw new Exception("El dron no está operativo.");
		}
	}

	public abstract double calcularTiempoRespuesta(double distancia) throws Exception;

	public int getId() {
		return id;
	}

	public int getAutonomiaMax() {
		return autonomiaMax;
	}

	public double getVelocidadMax() {
		return velocidadMax;
	}

	public boolean isOperativo() {
		return operativo;
	}
}