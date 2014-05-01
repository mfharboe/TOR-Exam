
package GUI.TableModel;

import BE.BERoleTime;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class TableModelRoleTime extends  AbstractTableModel{
    

    private ArrayList<BERoleTime> roleTime;

    private final String[] colNames = {"Navn",    
        "Funktion",
        "Køretøj",
        "Timer",
        };
    private final Class[] classes = {String.class, 
        String.class,
        int.class,
        int.class,
        };
/**
 * Updates the model
 * @param allRoleTimes 
 */
    public TableModelRoleTime(ArrayList<BERoleTime> allRoleTimes) {
        roleTime = allRoleTimes;
        fireTableDataChanged();
    }
/**
 *Returns the number of rows 
 */
    @Override
    public int getRowCount() {
        return roleTime.size();
    }
/**
 *Return the number of column 
 */
    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    /**
     * Returns the selected row
     * @param row
     * @param col 
     */
    @Override
    public Object getValueAt(int row, int col) {
        BERoleTime e = roleTime.get(row);
        switch (col) {
            case 0:
                return e.getM_fireman().getM_lastName() +", "+ e.getM_fireman().getM_firstName();
            case 1:
                return e.getM_role().getM_description();
            case 2:
                return e.getM_vehicle().getM_odinNumber();
            case 3:
                return e.getM_hours();
            }

        return null;
    }
/**
 * Returns the column name
 * @param col 
 */
    @Override
    public String getColumnName(int col) {

        return colNames[col];
    }
/**
 * Returns the column class
 * @param col 
 */
    @Override
    public Class<?> getColumnClass(int col) {
        return classes[col];
    }
/**
 * Returns if the cell is editable
 * @param row
 * @param col 
 */
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    /**
     * Sets the content of the table model to the given list of Role Times.
     *
     * @param roleTimeList
     */
    public void setRoleTimeList(ArrayList<BERoleTime> roleTimeList) {
        roleTime = roleTimeList;
        fireTableDataChanged();
    }

    /**.
     *
     * @param row the index for the roletimes in the roletime list.
     * @return the roletime set the given row index.
     */
    public BERoleTime getRoleTimeByRow(int row) {
        return roleTime.get(row);
    }
}
