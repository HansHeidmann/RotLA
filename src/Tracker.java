import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;


public class Tracker implements PropertyChangeListener {


    public void propertyChange(PropertyChangeEvent event){ // prints Tracking message 
        System.out.println("Tracker: turn " + event.getPropertyName());
        System.out.print("Total Active Adventurers: ");
        System.out.println(event.getNewValue());
        System.out.println("Adventurers       Room      Damage      Treasure");
        System.out.println(event.getOldValue());
        



    }


    
}
