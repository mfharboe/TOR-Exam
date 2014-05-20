package BLL;

import ObserverPattern.IErrorObserver;
import ObserverPattern.IErrorSubject;
import java.util.ArrayList;

public class BLLError implements IErrorSubject {

    private final ArrayList<IErrorObserver> observers;
    private static BLLError m_instance;
    private String error = "";

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
    
    public void RSSError(){
        error = "Der skete en uventet fejl ved indlæsning fra ODIN. Genstart programmet.";
        notifyObservers();
    }
    
    @Override
    public void register(IErrorObserver o) {
        observers.add(o);
    }

    @Override
    public void unregister(IErrorObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(IErrorObserver observer : observers)
            observer.update(error);
        
    }

    
}
