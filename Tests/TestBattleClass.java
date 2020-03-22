import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class TestBattleClass {

    @Test
    public void verifyClassLoader() throws DiceFormatException {
        BattleClass paladin = new BattleClass();
        paladin.Load("paladin");

        assertEquals(paladin.getClassType(), "Paladin");

        BattleClass druid = new BattleClass();
        druid.Load("druid");

        assertEquals(druid.getClassType(), "Druid");
    }

    @Test
    public void verifyProficiency() throws DiceFormatException{
        BattleClass barbarian = new BattleClass();
        barbarian.Load("barbarian");

        assertEquals(barbarian.getSkillProficiency(), "{\"name\":\"Skill: Animal Handling\",\"url\":\"\\/api\\/proficiencies\\/skill-animal-handling\"}");
    }
}
