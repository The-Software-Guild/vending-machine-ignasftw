package VendingMachine;

import VendingMachine.Controller.Control;
import VendingMachine.DAO.DataStorage;
import VendingMachine.View.UserInterface;

public class VendingMachineProgram {
    public VendingMachineProgram(){
        UserInterface userInterface = new UserInterface();
        DataStorage storage = new DataStorage();
        Control control = new Control(userInterface,storage);
        control.start();
    }
}
