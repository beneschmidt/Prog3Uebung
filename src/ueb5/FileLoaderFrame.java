package ueb5;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class FileLoaderFrame extends JFrame {

	private static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(300, 80);
	private JTextField fileTextField;
	private FileAdmin fileAdmin;

	public FileLoaderFrame(FileAdmin fileAdmin) {
		this.fileAdmin = fileAdmin;
		init();
	}

	public void init() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		fileTextField = new JTextField();
		fileTextField.addKeyListener(new TextFieldKeyListener());
		this.add(fileTextField, BorderLayout.NORTH);
	}

	@Override
	public Dimension getPreferredSize() {
		return DEFAULT_WINDOW_SIZE;
	}
	
	@Override
	public Dimension getMinimumSize() {
		return DEFAULT_WINDOW_SIZE;
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
				System.out.println("Eingabe durchgefuehrt, eingegebener Text: " + fileTextField.getText());
				fileAdmin.setFileName(fileTextField.getText());
				fileAdmin.setFileContent(FileHandler.loadFileContent(fileTextField.getText()));
				dispose();
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
