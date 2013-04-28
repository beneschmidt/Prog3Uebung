package ueb5;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.Transient;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ContentPanel extends JPanel implements FileAdmin, FileSaver {

	private BigTextArea text;
	private JLabel label;
	private Dimension dimension;
	private String fileContent;
	private String fileName;

	public ContentPanel(Dimension dimension) {
		this.dimension = dimension;
		text = new BigTextArea();

		label = new JLabel();
		text = new BigTextArea();
		BorderLayout layout = new BorderLayout();
		layout.setVgap(10);
		this.setLayout(layout);
		this.add(text, BorderLayout.CENTER);
		this.add(label, BorderLayout.SOUTH);
	}

	@Override
	@Transient
	public Dimension getPreferredSize() {
		return dimension;
	}

	public void setTextSize(int size) {
		this.text.setFontSize(size);
	}

	public void setTextFontName(String fontName) {
		this.text.setFontName(fontName);
	}

	public void clearTextAndFileName() {
		this.text.setText("");
		fileName = null;
		fileContent = null;
	}

	@Override
	public String getFileName() {
		return fileName;
	}

	@Override
	public String getFileContent() {
		return fileContent;
	}

	@Override
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
		text.setText(fileContent);
	}

	public boolean canQuickSave() {
		return fileName != null;
	}

	public void save() {
		setFileNameAndSave(fileName);
	}

	@Override
	public void setFileNameAndSave(String fileName) {
		this.fileName = fileName;
		try {
			FileHandler.saveFileContent(text.getText(), fileName);
		} catch (FileNotFoundException e) {
			setError("Die Datei " + text.getText() + " konnte nicht gefunden werden!");
		} catch (IOException e) {
			setError("Die Datei " + text.getText() + " konnte nicht gelesen werden!");
		}
	}

	@Override
	public void setError(String errorMessage) {
		label.setText(errorMessage);
	}
}
