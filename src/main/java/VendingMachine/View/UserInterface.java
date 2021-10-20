package VendingMachine.View;

import java.math.BigDecimal;
import java.util.Scanner;

public class UserInterface implements UserInput, UserOutput{
    private Scanner scanner = new Scanner(System.in);

    public void printMessage(String message)
    {
        System.out.println(message);
    }

    public BigDecimal moneyUserInput()
    {
        BigDecimal number;
        do
        {
            number = scanner.nextBigDecimal();
            if(number.compareTo(BigDecimal.ZERO) < 0){
                printMessage("Invalid number. Try again: ");
            }
        }while(number.compareTo(BigDecimal.ZERO) < 0);
        return number;
    }

    public int countUserInput(int max)
    {
        int number = 0;
        try{
            do
            {
                number = scanner.nextInt();
                if(number <= 0 || number > max){
                    printMessage("Invalid number. Try again: ");
                }
            }while(number <= 0 || number > max);
        }
        catch (Exception e){
            printMessage("Invalid input. Error: " + e.getLocalizedMessage());
            number = 0;
        }
        finally {
            return number;
        }
    }
}
