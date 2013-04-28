package ueb5;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class InfoFrame extends JFrame {

	private static final String INFO_HTML_FILE = "files/info.html";
	private JLabel infoTextLabel;

	public InfoFrame() {
		this.setVisible(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		infoTextLabel = new JLabel();
		this.add(infoTextLabel, BorderLayout.CENTER);
		
		loadText();
	}

	private void loadText() {
		try {
			String infoText = FileHandler.loadFileContent(INFO_HTML_FILE);
			infoTextLabel.setText(infoText);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
