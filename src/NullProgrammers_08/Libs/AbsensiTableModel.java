/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NullProgrammers_08.Libs;

/**
 *
 * @author DRONE003
 */
public class AbsensiTableModel extends javax.swing.table.DefaultTableModel {
    public static Object[] rowsType;
    @Override
    public int getRowCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return super.getRowCount();
    }

    @Override
    public int getColumnCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return super.getColumnCount();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return super.getValueAt(rowIndex, columnIndex);
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return rowsType[columnIndex].getClass();
    }
}
