package GUI.TableModel;

import BE.BEUsage;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModelUsage extends AbstractTableModel {

    private ArrayList<BEUsage> usage;

    private final String[] colNames = {"Materiel",
        "Antal"};

    private final Class[] classes = {String.class,
        int.class};
    
    public TableModelUsage(ArrayList<BEUsage> allUsage){
        usage = allUsage;
        fireTableDataChanged();
    }

    /**
     * 
     * @return
     */
    @Override
    public int getRowCount() {
        return usage.size();
    }

    /**
     * 
     * @return
     */
    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    /**
     * 
     * @param row
     * @param col
     * @return
     */
    @Override
    public Object getValueAt(int row, int col) {
        BEUsage e = usage.get(row);
        switch (col) {
            case 0:
                return e.getM_material().getM_description();
            case 1:
                return e.getM_amount();
        }
        return null;
    }
    
    /**
     * 
     * @param col
     * @return 
     */
    @Override
    public String getColumnName(int col) {
        return colNames[col];
    }

    /**
     * 
     * @param col
     * @return 
     */
    @Override
    public Class<?> getColumnClass(int col) {
        return classes[col];
    }

    /**
     * 
     * @param row
     * @param col
     * @return 
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    /**
     * 
     * @param usageList 
     */
    public void setUsageList(ArrayList<BEUsage> usageList) {
        usage = usageList;
        fireTableDataChanged();
    }

    /**
     * 
     * @param row
     * @return 
     */
    public BEUsage getUsageByRow(int row) {
        return usage.get(row);
    }
    
}
