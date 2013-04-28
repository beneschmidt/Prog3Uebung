package ueb5;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

public class JMenuItemHelper {

	private ActionListener actionListener;

	public JMenuItemHelper(ActionListener listenerToUse) {
		this.actionListener = listenerToUse;
	}

	/**
	 * Erstellt ein neues JMenuItem, weisst ihm den ActionListener zu und gibt
	 * es dann zurueck.
	 * 
	 * @param text
	 * @return ein neues JMenuItem mit ActionListener
	 */
	public JMenuItem createNewItem(String text) {
		JMenuItem newItem = new JMenuItem(text);
		newItem.addActionListener(actionListener);
		return newItem;
	}

	/**
	 * Erstellt ein neues JMenuItem mit Icon (nur wenn der Pfad nicht null ist),
	 * weisst ihm den ActionListener zu und gibt es dann zurueck.
	 * 
	 * @param text
	 * @return ein neues JMenuItem mit ActionListener
	 */
	public JMenuItem createNewItemWithIcon(String text, String iconPath) {
		if (iconPath != null) {
			ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(iconPath));
			JMenuItem newItem = new JMenuItem(text, icon);
			newItem.addActionListener(actionListener);
			return newItem;
		} else
			return createNewItem(text);
	}
}
