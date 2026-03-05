package drones;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class DronMontanaTest {

    private static DronMontana dron;

    private double distancia;
    private double tiempoEsperado;

    public DronMontanaTest(double distancia, double tiempoEsperado) {
        this.distancia = distancia;
        this.tiempoEsperado = tiempoEsperado;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> datos() {
        return Arrays.asList(new Object[][]{
                {10, 1.5},
                {7, 1.05},
                {5, 0.75}
        });
    }

    // Se ejecuta una sola vez al principio
    @BeforeClass
    public static void crearDron() {
        dron = new DronMontana(1, 120, 10, false, 1.5);
    }

    // Se ejecuta antes de cada test
    @Before
    public void activarDron() {
        try {
            java.lang.reflect.Field f = Base.class.getDeclaredField("operativo");
            f.setAccessible(true);
            f.set(dron, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Se ejecuta después de cada test
    @After
    public void desactivarDron() {
        try {
            java.lang.reflect.Field f = Base.class.getDeclaredField("operativo");
            f.setAccessible(true);
            f.set(dron, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Se ejecuta una vez al final
    @AfterClass
    public static void liberarMemoria() {
        dron = null;
    }

    // TEST PARAMETRIZADO (casos correctos)
    @Test(timeout = 2000)
    public void testCalculoTiempoRespuesta() throws Exception {

        double resultado = dron.calcularTiempoRespuesta(distancia);

        assertEquals(tiempoEsperado, resultado, 0.01);
    }

    // Distancia negativa
    @Test(timeout = 2000, expected = Exception.class)
    public void testDistanciaNegativa() throws Exception {
        dron.calcularTiempoRespuesta(-5);
    }

    // Autonomía insuficiente
    @Test(timeout = 2000, expected = Exception.class)
    public void testAutonomiaInsuficiente() throws Exception {
        dron.calcularTiempoRespuesta(10000);
    }

    // Dron no operativo
    @Test(timeout = 2000, expected = Exception.class)
    public void testDronNoOperativo() throws Exception {

        try {
            java.lang.reflect.Field f = Base.class.getDeclaredField("operativo");
            f.setAccessible(true);
            f.set(dron, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        dron.calcularTiempoRespuesta(10);
    }

    // Test de resistencia (no se ejecuta)
    @Ignore
    @Test
    public void testResistenciaBaja() throws Exception {

        for(int i=0;i<100000;i++){
            dron.calcularTiempoRespuesta(10);
        }

    }
}