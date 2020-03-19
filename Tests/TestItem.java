import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestItem {
    @Test
    public void VerifyItemLoad(){
        Item arrow = new Item();
        arrow.LoadItem("ammunition", "arrow");

        assertEquals(arrow.getItemType(), "Arrow");
        assertEquals(arrow.getCost(), 5);
        assertEquals(arrow.getCostType(), "cp");

        Item shortsword = new Item();
        shortsword.LoadItem("weapons", "shortsword");

        assertEquals(shortsword.getItemType(), "Shortsword");
        assertEquals(shortsword.getCost(), 10);
        assertEquals(shortsword.getCostType(), "gp");
    }

    @Test
    public void VerifyItemCost(){
    }
}
