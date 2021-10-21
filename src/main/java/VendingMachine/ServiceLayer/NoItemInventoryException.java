package VendingMachine.ServiceLayer;

public class NoItemInventoryException extends Exception{
    String message;
    public NoItemInventoryException(){
        message = "The item is out of stock.";
        System.err.println(message);
    }

    public NoItemInventoryException(String message){
        this.message = message;
        System.err.println(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
