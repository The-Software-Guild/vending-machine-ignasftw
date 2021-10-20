package VendingMachine.View;

import java.math.BigDecimal;

public interface UserInput {
    BigDecimal moneyUserInput();
    int countUserInput(int max);
}
