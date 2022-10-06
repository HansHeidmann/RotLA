import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.print.event.PrintEvent;
// reference https://www.baeldung.com/java-observer-pattern
public class Logger implements PropertyChangeListener{
    //
    // observer pattern
    //
    private String update;
    private int turn;
    
    public void updateTurn(int turnUpdate){
        turn = turnUpdate;
    }

    public void propertyChange(PropertyChangeEvent event) {
        
        update = event.getPropertyName() + (String)event.getOldValue() + (String)event.getNewValue() ;
            

            try{ // opens and writes to file
                FileWriter fileWriter = new FileWriter("./logs/Logger-"+ turn +"-.txt",true);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(update);
                printWriter.close();
            }
            catch (IOException ioe){
                System.out.println("can't write file  " + ioe.getMessage());
            }

            System.out.println(event.getPropertyName() + (String)event.getOldValue() + (String)event.getNewValue() ) ;
    }
        //Files.writeLines(path, update.getBytes());

        


}
    

