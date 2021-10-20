package VendingMachine.ServiceLayer;

public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(){
        System.err.println("The item is out of stock.");
    }

    public InsufficientFundsException(String message){
        System.err.println(message);
    }
}
