import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAbilityScoreModifier {
    @Test
    public void verifyModScore(){
        AbilityScoreModifier mod = new AbilityScoreModifier();
        mod.setAbilityScoreModifier(15);

        assertEquals(mod.getAbilityScoreModifier(), 2);

        mod.setAbilityScoreModifier(1);

        assertEquals(mod.getAbilityScoreModifier(), -5);

        mod.setAbilityScoreModifier(10);

        assertEquals(mod.getAbilityScoreModifier(), 0);

        mod.setAbilityScoreModifier(20);

        assertEquals(mod.getAbilityScoreModifier(), 5);

        mod.setAbilityScoreModifier(30);

        assertEquals(mod.getAbilityScoreModifier(), 10);
    }
}
