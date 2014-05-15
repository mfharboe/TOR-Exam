package BLL;

public class BLLError {

    private static BLLError m_instance;
    private final String ERROR_SYMBOL = "¤";
    private String error = ERROR_SYMBOL;

    private BLLError() {

    }

    public static BLLError getInstance() {
        if (m_instance == null) {
            m_instance = new BLLError();
        }
        return m_instance;
    }

    public void dbError() {
        error = "Der er sket en fejl, handlingen afsluttes";
    }

    public void readFiremanError() {
        error = "Kunne ikke læse listen for brandmænd";
    }

    public void readVehicleError() {
        error = "Kunne ikke læse listen for biler";
    }

    public void readIncidentTypesError() {
        error = "Kunne ikke læse listen for indsatstyperne";
    }

    public void readIncidentError() {
        error = "Kunne ikke læse listen for indsatser";
    }

    public void readRoleError() {
        error = "Kunne ikke læse listen for funktioner";
    }

    public void readRoleTimeError() {
        error = "Kunne ikke læse listen for indsatte styrker";
    }

    public void readUsageError() {
        error = "Kunne ikke læse forbruget";
    }

    public void readMaterialError() {
        error = "Kunne ikke læse listen for materialer";
    }

    public void readAlarmError() {
        error = "Kunne ikke læse listen for beretninger";
    }

    public void readIncidentDetailsError(){
        error = "Kunne ikke læse detaljerne over denne indsats";
    }
    
    public void updateIncidentDetailsError(){
        error = "Kunne ikke opdaterer detaljerne for denne indsats";
    }
    public void createIncidentError() {
        error = "Kunne ikke oprette en ny indsats";
    }

    public void createUsageError() {
        error = "Kunne ikke tilføje forbruget";
    }

    public void createErrorReportError() {
        error = "Kunne ikke oprette en fejlrapport";
    }

    public void deleteMaterialFromUsageError() {
        error = "Kunne ikke fjerne forbruget";
    }

    public void deleteFiremanFromRoleTimeError() {
        error = "Kunne ikke fjerne brandmændene fra indsatsen";
    }

    public void functionError() {
        error = "Du har allerede en funktion (BM/HL/CH) på denne indsats";
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

}
