package VendingMachine.Controller;

import VendingMachine.DAO.Audit;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class AuditLogger {
    private final String path = System.getProperty("user.dir")+"/src/main/java/VendingMachine/DAO/";
    private List<Audit> audits = new ArrayList<>();

    public void writeFile(Audit data){
        audits.add(data);
        try {
            PrintWriter out = new PrintWriter(new FileWriter(path+"AuditLog.txt"));
            for(Audit row : audits){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formatDateTime = row.getOccurrence().format(formatter);

                out.println(String.format(
                        "[%s]::%s",
                        formatDateTime,
                        row.getMessage()
                ));
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
