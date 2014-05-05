package GUI.TableModel;

import BE.BEIncidentVehicle;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModelForces extends AbstractTableModel {

    private ArrayList<BEIncidentVehicle> forces;

    private final String[] colNames = {"Køretøj",
        "Kørsel",
        "Bemanding",
        "Afvigelser"};

    private final Class[] classes = {String.class,
        String.class,
        int.class,
        Boolean.class};

    public TableModelForces(ArrayList<BEIncidentVehicle> allIncidentVehicles) {
        forces = allIncidentVehicles;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return forces.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        BEIncidentVehicle e = forces.get(row);
        switch (col) {
            case 0:
                return e.getM_vehicle().getM_odinNumber();
            case 1:
                return e.getM_emergency().getM_description();
            case 2:
                return e.getM_amountCrew();
            case 3:
                return e.isM_isDiverged();

        }
        return null;
    }

    @Override
    public String getColumnName(int col) {
        return colNames[col];
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return classes[col];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void setForceList(ArrayList<BEIncidentVehicle> forcesList) {
        forces = forcesList;
        fireTableDataChanged();
    }

    public BEIncidentVehicle getForcesByRow(int row) {
        return forces.get(row);
    }

}
