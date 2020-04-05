import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestEnvironment {
    @Test
    public void verifyDarkness(){
        Environment murkyCave = new Environment();
        murkyCave.setDim(true);

        assertTrue(murkyCave.isDim());
        assertFalse(murkyCave.isDark());
    }
}
