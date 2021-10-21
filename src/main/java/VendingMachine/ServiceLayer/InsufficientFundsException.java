package VendingMachine.ServiceLayer;

public class InsufficientFundsException extends Exception{
    String message;
    public InsufficientFundsException(){
        message = "The item is out of stock.";
        System.err.println(message);
    }

    public InsufficientFundsException(String message){
        this.message = message;
        System.err.println(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
