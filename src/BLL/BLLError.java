package BLL;

import ObserverPattern.IObserver;
import ObserverPattern.ISubject;
import java.util.ArrayList;

public class BLLError implements ISubject {

    private final ArrayList<IObserver> observers;
    private static BLLError m_instance;
    private final String ERROR_SYMBOL = "¤";
    private String error = ERROR_SYMBOL;

    private BLLError() {
        observers = new ArrayList<>();
    }

    public static BLLError getInstance() {
        if (m_instance == null) {
            m_instance = new BLLError();
        }
        return m_instance;
    }

    public void dbError() {
        error = "Der er sket en fejl, handlingen afsluttes";
        notifyObservers();
    }

    public void readFiremanError() {
        error = "Kunne ikke læse listen for brandmænd";
        notifyObservers();
    }

    public void readVehicleError() {
        error = "Kunne ikke læse listen for biler";
        notifyObservers();
    }

    public void readIncidentTypesError() {
        error = "Kunne ikke læse listen for indsatstyperne";
        notifyObservers();
    }

    public void readIncidentError() {
        error = "Kunne ikke læse listen for indsatser";
        notifyObservers();
    }

    public void readRoleError() {
        error = "Kunne ikke læse listen for funktioner";
        notifyObservers();
    }

    public void readRoleTimeError() {
        error = "Kunne ikke læse listen for indsatte styrker";
        notifyObservers();
    }

    public void readUsageError() {
        error = "Kunne ikke læse forbruget";
        notifyObservers();
    }

    public void readMaterialError() {
        error = "Kunne ikke læse listen for materialer";
        notifyObservers();
    }

    public void readAlarmError() {
        error = "Kunne ikke læse listen for beretninger";
        notifyObservers();
    }

    public void readIncidentDetailsError(){
        error = "Kunne ikke læse detaljerne over denne indsats";
        notifyObservers();
    }
    
    public void updateIncidentDetailsError(){
        error = "Kunne ikke opdaterer detaljerne for denne indsats";
        notifyObservers();
    }
    public void createIncidentError() {
        error = "Kunne ikke oprette en ny indsats";
        notifyObservers();
    }

    public void createUsageError() {
        error = "Kunne ikke tilføje forbruget";
        notifyObservers();
    }

    public void createErrorReportError() {
        error = "Kunne ikke oprette en fejlrapport";
        notifyObservers();
    }

    public void deleteMaterialFromUsageError() {
        error = "Kunne ikke fjerne forbruget";
        notifyObservers();
    }

    public void deleteFiremanFromRoleTimeError() {
        error = "Kunne ikke fjerne brandmændene fra indsatsen";
        notifyObservers();
    }

    public void functionError() {
        error = "Du har allerede en funktion (BM/HL/CH) på denne indsats";
        notifyObservers();
    }
    
    /**
     * @return true if there is an error, false if there is no error
     */
    public boolean isError() {
        return !error.equals(ERROR_SYMBOL);
    }

    /**
     * Resets the error String
     */
    public void resetError() {
        error = ERROR_SYMBOL;
    }

    /**
     * @return the current error message
     */
    public String getError() {
        return error;
    }

    @Override
    public void register(IObserver o) {
        observers.add(o);
    }

    @Override
    public void unregister(IObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(IObserver observer : observers)
            observer.update();
    }
}
