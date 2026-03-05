package drones;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.*;
import org.junit.runners.Parameterized.Parameters;
import java.util.*;

@RunWith(Parameterized.class)
public class DronMaritimoTest {

    private static DronMaritimo dron;
    private double distancia;
    private double tiempoEsperado;

    @Parameters
    public static Collection<Object[]> datos() {
        return Arrays.asList(new Object[][] {
            { 10.0, 1.0 },
            { 20.0, 2.0 },
            { 5.0, 0.5 }
        });
    }

    public DronMaritimoTest(double distancia, double tiempoEsperado) {
        this.distancia = distancia;
        this.tiempoEsperado = tiempoEsperado;
    }

    @BeforeClass
    public static void inicioTotal() {
        dron = new DronMaritimo(1, 120, 10, false, 80);
    }

    @AfterClass
    public static void finTotal() {
        dron = null;
    }

    @Before
    public void activarDron() {
        try {
            java.lang.reflect.Field f = Base.class.getDeclaredField("operativo");
            f.setAccessible(true);
            f.set(dron, true);
        } catch (Exception e) {}
    }

    @After
    public void desactivarDron() {
        try {
            java.lang.reflect.Field f = Base.class.getDeclaredField("operativo");
            f.setAccessible(true);
            f.set(dron, false);
        } catch (Exception e) {}
    }

    @Test(timeout = 2000)
    public void testCalculoTiempoParametrizado() throws Exception {
        double resultado = dron.calcularTiempoRespuesta(distancia);
        assertEquals(tiempoEsperado, resultado, 0.001);
    }

    @Test(expected = Exception.class)
    public void testErrorDistanciaNegativa() throws Exception {
        dron.calcularTiempoRespuesta(-5.0);
    }

    @Test(expected = Exception.class)
    public void testErrorAutonomia() throws Exception {
        dron.calcularTiempoRespuesta(5000.0);
    }

    @Test(expected = Exception.class)
    public void testErrorNoOperativo() throws Exception {
        java.lang.reflect.Field f = Base.class.getDeclaredField("operativo");
        f.setAccessible(true);
        f.set(dron, false);
        dron.calcularTiempoRespuesta(10.0);
    }

    @Ignore
    @Test
    public void testResistenciaBaja() {
    }
}