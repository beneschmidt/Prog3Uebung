package ueb4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.Transient;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ContentPanel extends JPanel {

	private BigTextArea text;
	private JTextField fileField;
	private JLabel label;
	private Dimension dimension;

	public ContentPanel(Dimension dimension) {
		this.dimension = dimension;
		text = new BigTextArea();

		fileField = new JTextField("");
		fileField.addKeyListener(new TextFieldKeyListener());
		label = new JLabel();
		text = new BigTextArea();
		BorderLayout layout = new BorderLayout();
		layout.setVgap(10);
		this.setLayout(layout);
		this.add(fileField, BorderLayout.NORTH);
		this.add(text, BorderLayout.CENTER);
		this.add(label, BorderLayout.SOUTH);

		refreshLabel();
	}

	@Override
	@Transient
	public Dimension getPreferredSize() {
		return dimension;
	}

	/**
	 * Spezieller Listener fuer das Eingabetextfeld
	 * 
	 * @author Benne
	 */
	private class TextFieldKeyListener implements KeyListener {

		private static final char ENTER = 10;

		@Override
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == ENTER) {
				System.out.println("Eingabe durchgefuehrt, eingegebener Text: " + fileField.getText());
				String textFromFile = FileLoader.loadFileContent(fileField.getText());
				text.setText(textFromFile);
				refreshLabel();
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}

	public void setTextSize(int size) {
		this.text.setFontSize(size);
	}

	public void setTextFontName(String fontName) {
		this.text.setFontName(fontName);
	}

	/**
	 * aktualisiert das angezeigte Label
	 */
	public void refreshLabel() {
		label.setText(text.toString());
	}
}
