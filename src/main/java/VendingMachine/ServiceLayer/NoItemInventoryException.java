package VendingMachine.ServiceLayer;

public class NoItemInventoryException extends Exception{
    public NoItemInventoryException(){
        System.err.println("The item is out of stock.");
    }
    public NoItemInventoryException(String message){
        System.err.println(message);
    }
}
