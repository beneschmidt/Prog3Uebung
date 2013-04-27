package ueb5;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.Transient;

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

		refreshLabel();
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

	/**
	 * aktualisiert das angezeigte Label
	 */
	public void refreshLabel() {
		label.setText(text.toString());
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
		FileHandler.saveFileContent(text.getText(), fileName);
	}

	@Override
	public void saveFile(String fileName) {
		FileHandler.saveFileContent(text.getText(), fileName);
	}
}
