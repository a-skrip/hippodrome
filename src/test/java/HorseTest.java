import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {
    @Test
    public void checkingForNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 0);
        });
        assertEquals("Name cannot be null.", exception.getMessage());

    }

    @ParameterizedTest
    @ValueSource(strings = {"\t", "\n", " "})
    public void checkingForBlank(String arg) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(arg, 0);
        });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void checkingForValidNumbersOfSpeed() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Конь", -1);
        });
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void checkingForValidNumbersOfDistance() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Конь", 1, -9);
        });
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getNameTest() {
        Horse name = new Horse("Pony", 1, 2);
        assertEquals("Pony", name.getName());
    }

    @Test
    public void getSpeedTest() {
        Horse speed = new Horse("horse", 20, 36);
        assertEquals(20, speed.getSpeed());
    }

    @Test
    public void getDistanceTest() {
        Horse dist = new Horse("horse", 20, 36);
        assertEquals(36, dist.getDistance());
        Horse distTwoParam = new Horse("Pony2", 25);
        assertEquals(0, distTwoParam.getDistance());
    }

    @Test
//    @ExtendWith(MockitoExtension.class)

    public void moveTest() {
        try (MockedStatic<Horse> mockStatic = mockStatic(Horse.class)) {
            new Horse("конь", 2, 35).move();

            mockStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = 0.5)
    public void moveTest1(Double arg) {
        try (MockedStatic<Horse> mockStatic = mockStatic(Horse.class)) {
            mockStatic.when(() -> Horse.getRandomDouble(anyDouble(), anyDouble())).thenReturn(arg);

            Horse horse = new Horse("Horse", 3, 15);
            Double asew = horse.getDistance() + horse.getSpeed() * arg;
            horse.move();
            assertEquals(asew, horse.getDistance());

        }
    }
}
