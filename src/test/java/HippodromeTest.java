import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {

    @Test
    public void checkingForNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void checkingForEmptyList() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            List<Horse> list = new ArrayList<>();
            new Hippodrome(list);
        });
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void getHorsesTest() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void moveTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        new Hippodrome(horses).move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    public void getWinnerTest() {
        Horse horse1 = new Horse("horse1", 2, 24);
        Horse horse2 = new Horse("horse1", 4, 35);
        Horse horse3 = new Horse("horse1", 3, 15);
        Horse horse4 = new Horse("horse1", 4, 40);
        Horse horse5 = new Horse("horse1", 1, 23);

        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3, horse4, horse5));

        assertEquals(horse4, hippodrome.getWinner());
    }
}
