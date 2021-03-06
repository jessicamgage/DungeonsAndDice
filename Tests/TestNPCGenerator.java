import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestNPCGenerator {
    @Test
    public void verifyRaceGenerator() throws Exception{
        NPCGenerator generator = new NPCGenerator();
        String chosen = generator.raceRandomizer();

        assertNotEquals(null, chosen);
        assertTrue(generator.properFileNameFormat(generator.getRaceFile()));
        assertFalse(generator.properNameFormat(generator.getRaceString()));

    }

    @Test
    public void testRandomRaceAbility() throws Exception{
        NPCGenerator generator = new NPCGenerator();

        Race unknownNPC = new Race();
        generator.generateRace(unknownNPC);

        assertTrue(unknownNPC.hasRacialAbility(unknownNPC.getRacialAbility()));
    }

    @Test
    public void verifyPredeterminedRaceGeneration() throws Exception{
        NPCGenerator koboldGen = new NPCGenerator();
        Race kobold = new Race();

        koboldGen.setRaceString("kobold");
        koboldGen.setRaceFile("kobold.json");

        koboldGen.generateRace(kobold);

        assertEquals(koboldGen.getRaceString(), "kobold");
        assertEquals(koboldGen.getRaceFile(), "kobold.json");
        assertTrue(kobold.hasRacialAbility("Darkvision"));
    }

    @Test
    public void verifyClassAndRaceGeneration() throws Exception{
        NPCGenerator raceOfClass = new NPCGenerator();

        Race raceOfNPC = new Race();
        CharacterClass classOfNPC = new CharacterClass();

        raceOfClass.generateRaceAndClass(raceOfNPC, classOfNPC);

        assertNotNull(raceOfClass.getClassString());
        assertNotNull(raceOfClass.getClassFile());
        assertNotNull(raceOfClass.getRaceFile());
        assertNotNull(raceOfClass.getRaceString());

        assertTrue(raceOfNPC.hasRacialAbilities(raceOfNPC.getRacialAbilities()));
        assertTrue(raceOfNPC.hasRacialAbility(raceOfNPC.getRacialAbility()));

        assertNotNull(classOfNPC.getHitDie());
        assertNotNull(classOfNPC.getNumberOfProficiencies());
    }

    @Test
    public void testingHumans() throws Exception{ //humans are being tested separately because they lack racial abilities.
        NPCGenerator humanNPC = new NPCGenerator();

        Race human = new Race();

        humanNPC.setRaceFile("human.json");
        humanNPC.setRaceString("human");

        humanNPC.generateRace(human);

        assertFalse(human.hasRacialAbility("Darkvision"));
    }

    @Test
    public void verifyWeaponGeneration() throws Exception{
        NPCGenerator weaponGen = new NPCGenerator();
        Weapon chosenWeapon = new Weapon();

        weaponGen.generateWeapon(chosenWeapon);

        assertNotNull(weaponGen.getWeaponString());
        assertNotNull(weaponGen.getWeaponFile());

        assertSame(chosenWeapon.getDamageRange(), chosenWeapon.getDamageRange());
        assertTrue(chosenWeapon.getCost() > 0);
        assertNotNull(chosenWeapon.getDamageType());
    }

    @Test
    public void verifyNPCGenerator() throws Exception{
        NPCGenerator NPC = new NPCGenerator();
        NPC.generateNPC();

        assertNotNull(NPC.getClassFile());
        assertNotNull(NPC.getClassString());
        assertNotNull(NPC.getWeaponFile());
        assertNotNull(NPC.getWeaponFile());
        assertNotNull(NPC.getRaceFile());
        assertNotNull(NPC.getRaceString());

        assertTrue(NPC.getHitPoints() > 0);
        assertTrue(NPC.getArmorClass() > 0);

        if(NPC.getClassString().equalsIgnoreCase("monk")){
            assertEquals(NPC.getArmorClass(), (NPC.getDexMod() + NPC.getWisMod() + 10));
        }else if(NPC.getClassString().equalsIgnoreCase("barbarian")){
            assertEquals(NPC.getArmorClass(), (NPC.getDexMod() + NPC.getConMod() + 10));
        }else{
            assertTrue(NPC.getArmorClass() == NPC.getDexMod() + 10);
        }

        assertTrue(NPC.getStrScore() > 0);
        assertTrue(NPC.getDexScore() > 0);
        assertTrue(NPC.getConScore() > 0);
        assertTrue(NPC.getIntScore() > 0);
        assertTrue(NPC.getWisScore() > 0);
        assertTrue(NPC.getChaScore() > 0);
    }
}
