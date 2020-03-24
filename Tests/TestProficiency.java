import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestProficiency {

    @Test
    public void verifyProficiency(){
        Proficiency acrobatics = new Proficiency();
        acrobatics.Load("acrobatics");

        Proficiency religion = new Proficiency();
        religion.Load("religion");

        assertEquals(acrobatics.getProficiency(), "Acrobatics");
        assertEquals(religion.getProficiency(), "Religion");
    }

}
