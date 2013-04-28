package ueb6;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;

public class JItemHelper {

	private ActionListener actionListener;

	public JItemHelper(ActionListener listenerToUse) {
		this.actionListener = listenerToUse;
	}

	/**
	 * Erstellt ein neues JMenuItem, weisst ihm den ActionListener zu und gibt
	 * es dann zurueck.
	 * 
	 * @param text
	 * @return ein neues JMenuItem mit ActionListener
	 */
	public JMenuItem createNewMenuItem(String text) {
		JMenuItem newItem = new JMenuItem(text);
		newItem.addActionListener(actionListener);
		return newItem;
	}

	/**
	 * Erstellt ein neues JMenuItem, weisst ihm den ActionListener zu und gibt
	 * es dann zurueck.
	 * 
	 * @param actionCommand
	 * @param iconPath
	 * @return ein neues JMenuItem mit ActionListener
	 */
	public JButton createNewToolBarButton(String iconPath, String actionCommand) {
		ImageIcon icon = new ImageIcon(iconPath);
		JButton newButton = new JButton(icon);
		newButton.setActionCommand(actionCommand);
		newButton.addActionListener(actionListener);
		return newButton;
	}

	/**
	 * Erstellt ein neues JMenuItem mit Icon (nur wenn der Pfad nicht null ist),
	 * weisst ihm den ActionListener zu, und gibt es dann zurueck.
	 * 
	 * @param text
	 * @return ein neues JMenuItem mit ActionListener
	 */
	public JMenuItem createNewMenuItemWithIcon(String text, String iconPath) {
		if (iconPath != null) {
			ImageIcon icon = new ImageIcon(iconPath);
			JMenuItem newItem = new JMenuItem(text, icon);
			newItem.addActionListener(actionListener);
			return newItem;
		} else
			return createNewMenuItem(text);
	}
}
