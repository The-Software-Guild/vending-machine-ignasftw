package VendingMachine.DAO;

import java.math.BigDecimal;

public enum Cash {
    TWO_POUNDS(new BigDecimal("2.00")),
    ONE_POUND(new BigDecimal("1.00")),
    FIFTY_PENCES(new BigDecimal("0.50")),
    TWENTY_PENCES(new BigDecimal("0.20")),
    TEN_PENCES(new BigDecimal("0.10")),
    FIVE_PENCES(new BigDecimal("0.05")),
    TWO_PENCES (new BigDecimal("0.02")),
    ONE_PENCE (new BigDecimal("0.01"));

    public final BigDecimal label;

    Cash(BigDecimal label){
        this.label = label;
    }
}
