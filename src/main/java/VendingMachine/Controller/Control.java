package VendingMachine.Controller;

import VendingMachine.DAO.Audit;
import VendingMachine.DAO.Cash;
import VendingMachine.DAO.Data;
import VendingMachine.DAO.Storage;
import VendingMachine.ServiceLayer.InsufficientFundsException;
import VendingMachine.ServiceLayer.NoItemInventoryException;
import VendingMachine.View.UserInput;
import VendingMachine.View.UserInterface;
import VendingMachine.View.UserOutput;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

public class Control {
    private final UserInput userInput;
    private final UserOutput userOutput;
    private final Storage storage;
    private final FileModifier fileMod = new FileModifier();
    private final AuditLogger auditLogger = new AuditLogger();
    private BigDecimal currentMoney;
    private int latestItem;

    private final String[] options = {"INSERT", "SELECT", "LIST", "EXIT"};

    public Control(UserInput userInput, UserOutput userOutput, Storage storage){
        this.userInput = userInput;
        this.userOutput = userOutput;
        this.storage = storage;
    }

    public void start() throws NoItemInventoryException, InsufficientFundsException {
        String input;
        currentMoney = new BigDecimal("0.00");

        try{
            fileMod.readFile();
        }
        catch (FileNotFoundException e){
            userOutput.printMessage(e.getLocalizedMessage());
        }
        for(Data data : fileMod.getData()){
            storage.addItem(data);
        }

        greetMessage();

        do{
            initialMenu();
            int index = userInput.countUserInput(options.length);
            if (index == 0) index = 3;
            input = options[index-1];
            switch (input){
                case "INSERT":
                    userOutput.printMessage("Insert amount:");
                    currentMoney = currentMoney.add(userInput.moneyUserInput());
                    userOutput.printMessage(String.format("You have inserted amount [%.2f]",currentMoney));
                    break;
                case "SELECT":
                    displayAvailable();
                    userOutput.printMessage("Please enter item ID.");
                    int itemId = userInput.countUserInput(storage.getLastId());
                    latestItem = itemId;
                    userOutput.printMessage(String.format("You have selected [%d] item",itemId));
                    purchase(itemId);
                    fileMod.writeFile(storage);
                    break;
                case "LIST":
                    userOutput.printMessage("VENDING MACHINE MENU:");
                    displayAvailable();
                    break;
                default:
                    displayChange();
                    userOutput.printMessage("Exiting the vending machine software.");
                    break;
            }
        }while(input.compareTo(options[options.length-1])!=0);

    }
    private void greetMessage(){
        userOutput.printMessage(
                         "=====================\n" +
                         "VENDING MACHINE MENU:");
        displayAvailable();
        userOutput.printMessage("=====================");
        userOutput.printMessage("Please insert money to select an item.");
    }

    private void initialMenu() {
        userOutput.printMessage("Please select the operation you wish to perform:");
        userOutput.printMessage(
                "            1. Insert money.\n" +
                "            2. Select item.\n" +
                "            3. Display all items.\n" +
                "            4. Exit the program."
        );
    }

    private void displayAvailable(){
        Map<Integer,Data> items = storage.getItems().entrySet()
                .stream()
                .filter(map -> map.getValue().getAmount()>0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        items.forEach((k,v) -> userOutput.printMessage(String.format("[%s] %s",k,v)));
    }

    private void purchase(int id) throws InsufficientFundsException, NoItemInventoryException {
        if(storage.getItem(id) == null){
            userOutput.printMessage("Sorry, invalid item id.");
        }
        else{
            Data item = storage.getItem(id);
            if(item.getAmount()>0){
                BigDecimal cost = item.getPrice();
                if(cost.compareTo(currentMoney) <= 0)
                {
                    currentMoney = currentMoney.subtract(cost);
                    item.updateAmount(item.getAmount()-1);
                    userOutput.printMessage(String.format("Successful purchase. Item price [%.2f]",cost));
                    displayChange();
                }
                else
                {
                    throw new InsufficientFundsException(String.format("Sorry, insufficient funds. Current amount: [%.2f] item price [%.2f]",currentMoney,cost));
                }
            }
            else
            {
                throw new NoItemInventoryException(String.format("The item [%s] %s is out of stock.",id,item.getName()));
            }
        }
    }

    private void displayChange(){
        String message = "";
        for(Cash coin : Cash.values()){
            int coins = currentMoney.divide(coin.label).intValue();
            if(coins > 0){
                currentMoney = currentMoney.subtract(coin.label.multiply(new BigDecimal(coins)));
                message += coin.name() + ":" + coins + " ";
            }
        }
        userOutput.printMessage(message);
    }

    public void logAudit(Exception e){
        //Create a log file with DAO with exception and time
        Audit auditLog = new Audit(e.toString() + "::" + latestItem + "::" + currentMoney+ "£");
        auditLogger.writeFile(auditLog);
    }
}
