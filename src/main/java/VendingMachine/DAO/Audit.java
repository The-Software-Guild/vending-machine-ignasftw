package VendingMachine.DAO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Audit {
    private final LocalDateTime occurrence;
    private final String message;

    public Audit(String message){
        occurrence = LocalDateTime.now();
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    public LocalDateTime getOccurrence(){
        return this.occurrence;
    }
}
