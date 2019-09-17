import static org.junit.Assert.*;
import org.junit.Test;
public class TemperatureConverterTest {

    @Test
    public void celsisusToFahrenheit() {
        assertEquals(-40,TemperatureConverter.celsisusToFahrenheit(-40),0);
    }

    @Test
    public void fahrenheitToCelsius() {
        assertEquals(12,TemperatureConverter.fahrenheitToCelsius(53.6),0.00001);
    }
}