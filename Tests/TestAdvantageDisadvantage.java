import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestAdvantageDisadvantage {
    @Test
    public void verifyAdvantage() throws Exception{
        AdvantageDisadvantage advantage = new AdvantageDisadvantage();
        advantage.rollWithAdvantage();

        assertTrue(advantage.getNum1() >= advantage.getNum2());
    }

    @Test
    public void verifyDisadvantage() throws Exception{
        AdvantageDisadvantage disadvantage = new AdvantageDisadvantage();
        disadvantage.rollWithDisadvantage();

        assertTrue(disadvantage.getNum1() <= disadvantage.getNum2());
    }
}
