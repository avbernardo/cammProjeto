/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class Centraliza extends DefaultTableCellRenderer{
    
    public Centraliza() 
{
	super();
}

public Component getTableCellRendererComponent(JTable table, Object value,
		boolean isSelected, boolean hasFocus, int row, int column) {
	this.setHorizontalAlignment(LEFT);
        this.setVerticalAlignment(CENTER);

	return super.getTableCellRendererComponent(table, value, isSelected,
			hasFocus, row, column);
}
    
}
