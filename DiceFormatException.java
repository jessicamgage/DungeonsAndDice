public class DiceFormatException extends Exception{
    public DiceFormatException(String errorMessage){
        super(errorMessage);

        errorMessage = ("Sorry, your dice format was invalid. Please try again.");

        System.out.println(errorMessage);
    }
}
