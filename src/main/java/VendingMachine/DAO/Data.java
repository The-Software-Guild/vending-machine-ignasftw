package VendingMachine.DAO;

import java.math.BigDecimal;

public class Data {
    private String name;
    private int amount;
    private BigDecimal price;

    public void setItem(String name, BigDecimal price, int amount){
        this.name = name;
        this.price= price;
        this.amount = amount;
    }

    public void updateName(String name){
        this.name = name;
    }

    public void updateAmount(int amount){
        this.amount = amount;
    }

    public void updatePrice(BigDecimal price){
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public int getAmount(){
        return amount;
    }

    public BigDecimal getPrice(){
        return price;
    }

    @Override
    public String toString(){
        return String.format("Item: %s | Price: %.2f| Stock: %d",name,price,amount);
    }
}
