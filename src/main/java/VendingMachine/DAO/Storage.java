package VendingMachine.DAO;

import java.util.HashMap;

public interface Storage {
    void addItem(Data item);
    void updateItem(int id, Data item);
    void removeItem(int id);
    HashMap<Integer, Data> getItems();
    Data getItem(int id);
    int getLastId();
}
