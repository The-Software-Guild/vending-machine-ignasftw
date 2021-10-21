package VendingMachine;

import VendingMachine.Controller.Control;
import VendingMachine.DAO.Audit;
import VendingMachine.DAO.DataStorage;
import VendingMachine.ServiceLayer.InsufficientFundsException;
import VendingMachine.ServiceLayer.NoItemInventoryException;
import VendingMachine.View.UserInput;
import VendingMachine.View.UserInterface;
import VendingMachine.View.UserOutput;

public class VendingMachineProgram {
    public VendingMachineProgram(){
        UserInterface userInterface = new UserInterface();
        DataStorage storage = new DataStorage();
        Control control = new Control(userInterface,userInterface,storage);
        try {
            control.start();
        } catch (NoItemInventoryException | InsufficientFundsException e) {
            control.logAudit(e);
        }
    }
}
