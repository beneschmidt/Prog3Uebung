package ueb4;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class JMenuItemHelper {

	private ActionListener actionListener;
	
	public JMenuItemHelper(ActionListener listenerToUse){
		this.actionListener = listenerToUse;
	}
	
	/**
	 * Erstellt ein neues JMenuItem, weisst ihm den ActionListener zu und gibt es dann zurueck.
	 * @param text
	 * @return ein neues JMenuItem mit ActionListener
	 */
	public JMenuItem createNewItem(String text){
		JMenuItem newItem = new JMenuItem(text);
		newItem.addActionListener(actionListener);
		return newItem;
	}
}
