package GUI.TableModel;

import BE.BERoleTime;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModelRoleTime extends AbstractTableModel {

    private ArrayList<BERoleTime> roleTime;

    private final String[] colNames = {"Navn",
        "Funktion",
        "Køretøj",
        "Timer",};
    private final Class[] classes = {String.class,
        String.class,
        int.class,
        int.class};

    /**
     * Updates the model
     *
     * @param allRoleTimes
     */
    public TableModelRoleTime(ArrayList<BERoleTime> allRoleTimes) {
        roleTime = allRoleTimes;
        fireTableDataChanged();
    }

    /**
     * 
     * @return the number of rows
     */
    @Override
    public int getRowCount() {
        return roleTime.size();
    }

    /**
     * 
     * @return the number of columns 
     */
    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    /**
     * 
     * @param row
     * @param col
     * @return the selected row 
     */
    @Override
    public Object getValueAt(int row, int col) {
        BERoleTime e = roleTime.get(row);
        switch (col) {
            case 0:
                return e.getM_fireman().getM_lastName() + ", " + e.getM_fireman().getM_firstName();
            case 1:
                return e.getM_role().getM_description();
            case 2:
                String tmp = "-";
                if (!e.isM_isOnStation()) {
                    tmp = e.getM_vehicle().getM_odinNumber() + "";
                }
                return tmp;
            case 3:
                return e.getM_hours();
        }

        return null;
    }

    /**
     * 
     * @param col
     * @return the column name 
     */
    @Override
    public String getColumnName(int col) {

        return colNames[col];
    }

    /**
     * 
     * @param col
     * @return the column class 
     */
    @Override
    public Class<?> getColumnClass(int col) {
        return classes[col];
    }

    /**
     * 
     * @param row
     * @param col
     * @return true if the cells are editable 
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    /**
     * Sets the content of the table model to the given list of roleTimes
     * @param roleTimeList 
     */
    public void setRoleTimeList(ArrayList<BERoleTime> roleTimeList) {
        roleTime = roleTimeList;
        fireTableDataChanged();
    }

    /**
     * 
     * @param row
     * @return the roletime set at the given row index
     */
    public BERoleTime getRoleTimeByRow(int row) {
        return roleTime.get(row);
    }
}
