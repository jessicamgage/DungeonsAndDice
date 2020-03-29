import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAbilityScoreModifier {
    @Test
    public void verifyModScore(){
        SetAbilityModifiers mod = new SetAbilityModifiers();
        mod.setAbilityScoreModifier(15);

        assertEquals(mod.getAbilityScoreModifier(), 2);

        mod.setAbilityScoreModifier(1);

        assertEquals(mod.getAbilityScoreModifier(), -5);

        mod.setAbilityScoreModifier(10);

        assertEquals(mod.getAbilityScoreModifier(), 0);
    }
}
