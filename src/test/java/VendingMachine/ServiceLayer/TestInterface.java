package VendingMachine.ServiceLayer;

import VendingMachine.View.UserInput;
import VendingMachine.View.UserOutput;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestInterface implements UserOutput, UserInput,TestInterfaceInterf {
    private List<Integer> inputs = new ArrayList<>();
    private int inputIndex = 0;

    public void setInputs(List<Integer> inputs){
        this.inputs = inputs;
    }

    @Override
    public BigDecimal moneyUserInput() {
        BigDecimal newNumber = new BigDecimal(inputs.get(inputIndex));
        inputIndex++;
        return newNumber;
    }

    @Override
    public int countUserInput(int max) {
        int newNumber = inputs.get(inputIndex);
        inputIndex++;
        return newNumber;
    }

    @Override
    public void printMessage(String message) {

    }
}
