/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NullProgrammers_08.Libs;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author DRONE003
 */
public class AbsensiTableRenderer extends JComboBox implements TableCellRenderer{
    public AbsensiTableRenderer(String[] items)
    {
        super(items);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected)
        {
            super.setBackground(table.getSelectionBackground());
            super.setForeground(table.getSelectionForeground());
        }
        else
        {
            super.setBackground(table.getBackground());
            super.setForeground(table.getForeground());
        }
        this.setSelectedItem(value);
        return this;
    }
    
}
