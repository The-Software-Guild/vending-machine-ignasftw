package VendingMachine.ServiceLayer;

import VendingMachine.Controller.Control;
import VendingMachine.DAO.Data;
import VendingMachine.DAO.DataStorage;
import VendingMachine.View.UserInterface;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineTesting {
    public VendingMachineTesting(){
    }

    @Test
    public void insufficientFundsExceptionThrows(){
        TestInterface userInterface = new TestInterface();
        List<Integer> inputs = new ArrayList<>();
        inputs.add(2);
        inputs.add(102);
        inputs.add(4);
        userInterface.setInputs(inputs);
        DataStorage storage = new DataStorage();
        Control control = new Control(userInterface,userInterface,storage);
        InsufficientFundsException exception = assertThrows(InsufficientFundsException.class,()->control.start());
        Assert.assertEquals("Sorry, insufficient funds. Current amount: [0.00] item price [1.99]", exception.getMessage());
    }

    @Test
    public void noItemInventoryExceptionThrows(){
        TestInterface userInterface = new TestInterface();
        List<Integer> inputs = new ArrayList<>();
        inputs.add(1);
        inputs.add(10);
        inputs.add(2);
        inputs.add(101);
        inputs.add(4);
        userInterface.setInputs(inputs);
        DataStorage storage = new DataStorage();
        Control control = new Control(userInterface,userInterface,storage);
        NoItemInventoryException exception = assertThrows(NoItemInventoryException.class,()->control.start());
        Assert.assertEquals("The item [101] ItemNr1 is out of stock.", exception.getMessage());
    }

    @Test
    public void firstItemImported() {
        TestInterface userInterface = new TestInterface();
        List<Integer> inputs = new ArrayList<>();
        inputs.add(4);
        userInterface.setInputs(inputs);
        DataStorage storage = new DataStorage();
        Control control = new Control(userInterface, userInterface, storage);
        try {
            control.start();
        } catch (NoItemInventoryException | InsufficientFundsException e) {
            e.printStackTrace();
        }
        Data item = storage.getItem(100);
        Boolean test =  item.getName().equals("ItemNr1") &&
                        item.getPrice().compareTo(new BigDecimal("0.99")) == 0 &&
                        item.getAmount() == 0;
        ;
        Assert.assertTrue(test);
    }

    @Test
    public void firstItemNotEqualSecond() {
        TestInterface userInterface = new TestInterface();
        List<Integer> inputs = new ArrayList<>();
        inputs.add(4);
        userInterface.setInputs(inputs);
        DataStorage storage = new DataStorage();
        Control control = new Control(userInterface, userInterface, storage);
        try {
            control.start();
        } catch (NoItemInventoryException | InsufficientFundsException e) {
            e.printStackTrace();
        }
        Data item = storage.getItem(101);
        Boolean test =  item.getName().equals("ItemNr1") &&
                item.getPrice().compareTo(new BigDecimal("0.99")) == 0 &&
                item.getAmount() == 0;
        ;
        Assert.assertFalse(test);
    }

    @Test
    public void updateItemImported() {
        TestInterface userInterface = new TestInterface();
        List<Integer> inputs = new ArrayList<>();
        inputs.add(4);
        userInterface.setInputs(inputs);
        DataStorage storage = new DataStorage();
        Control control = new Control(userInterface, userInterface, storage);
        try {
            control.start();
        } catch (NoItemInventoryException | InsufficientFundsException e) {
            e.printStackTrace();
        }
        Data item = storage.getItem(100);
        Data itemUpdated = storage.getItem(100);
        itemUpdated.updateName("NewItem");
        itemUpdated.updateAmount(10);
        itemUpdated.updatePrice(new BigDecimal("0.49"));
        
        Boolean test =  item.getName().equals("ItemNr1") &&
                item.getPrice().compareTo(new BigDecimal("0.99")) == 0 &&
                item.getAmount() == 0;
        ;
        Assert.assertFalse(test);
    }
}