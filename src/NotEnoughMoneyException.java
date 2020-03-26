public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException (String errorMessage){
        super(errorMessage);

        errorMessage = "Sorry, you don't have enough money for that.";

        System.out.println(errorMessage);
    }
}
