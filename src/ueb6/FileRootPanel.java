package ueb6;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.Transient;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Klasse fuer die fileRoot-Anzeige zur Auswahl des Startordners. Enthaelt auch
 * den ActionListener fuer das Textfeld
 * 
 * @author Benne
 */
public class FileRootPanel extends JPanel {
	private static final Dimension DIMENSION = new Dimension(800, 50);
	private JLabel label;
	private JTextField textField;

	public FileRootPanel() {
		PropertyHandler instance = PropertyHandler.getInstance();
		label = new JLabel(instance.getText("fileRoot") + ":");
		label.setHorizontalAlignment( SwingConstants.CENTER );
		textField = new JTextField(40);
		textField.addKeyListener(new TextKeyListener());

		this.setLayout(new GridLayout(1, 2));
		this.add(label);
		this.add(textField);
	}
	
	@Override
	@Transient
	public Dimension getPreferredSize() {
		return DIMENSION;
	}
	
	@Override
	@Transient
	public Dimension getMinimumSize() {
		return new Dimension(100, 50);
	}
	
	/**
	 * Faengt die Eingabe von Texten im Textfeld ab
	 * 
	 * @author Benne
	 * 
	 */
	private class TextKeyListener implements KeyListener {
		private static final char ENTER = 10;

		@Override
		public void keyTyped(KeyEvent event) {
			if (event.getKeyChar() == ENTER) {
				System.out.println("Eingabe durchgefuehrt, eingegebener Text: " + textField.getText());
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
}
