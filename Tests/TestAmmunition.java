import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestAmmunition{
    @Test
    public void TestAmmoLoader(){
        Ammunition arrow = new Ammunition();
        arrow.LoadAmmunition("arrow");

        assertEquals(arrow.getCost(), 5);
    }
}