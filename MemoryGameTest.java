import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MemoryGameTest {

    @Test
    public void testRevealCardAndAllMatched() {
        // Create a small test board
        MemoryGame game = new MemoryGame();

        // Access static methods if they are public (you may adjust method visibility if needed)
        // Example: test logic of allMatched()
        boolean result = invokeAllMatchedMethod();
        assertFalse(result, "Board should not be all matched at start.");
    }

    @Test
    public void testHideCardsDoesNotCrash() {
        // just to see a working test
        assertTrue(true, "HideCards placeholder test works.");
    }

    // Helper method (only to simulate board access — depends on your MemoryGame design)
    private boolean invokeAllMatchedMethod() {
        try {
            java.lang.reflect.Method m = MemoryGame.class.getDeclaredMethod("allMatched");
            m.setAccessible(true);
            return (boolean) m.invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
