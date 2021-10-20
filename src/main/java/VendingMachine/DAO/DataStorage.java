package VendingMachine.DAO;

import java.util.HashMap;

public class DataStorage implements Storage{
    private Integer i=100;
    private HashMap<Integer, Data> itemMap= new HashMap<>();

    public void addItem(Data item){
        itemMap.put(i++,item);
    }

    public void updateItem(int id, Data item){
        itemMap.replace(id, item);
    }

    public void removeItem(int id){
        itemMap.remove(id);
    }

    public HashMap<Integer, Data> getItems() {
        return itemMap;
    }

    public Data getItem(int id) {
        return itemMap.get(id);
    }

    public int getLastId(){
        return i;
    }
}

