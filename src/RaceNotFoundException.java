import java.io.FileNotFoundException;

public class RaceNotFoundException extends FileNotFoundException {
    public RaceNotFoundException(String errorMessage){
        super(errorMessage);

        errorMessage = "Sorry, your selected race was not found in the local cache. Please try again.";

        System.out.println(errorMessage);
    }
}
