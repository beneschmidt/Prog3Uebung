package ueb5;

import java.awt.Font;

import javax.swing.JTextArea;

/**
 * Repraesentiert einen anzeigbaren Text in der Oberflaeche.
 * 
 * @author Benne
 */
public class BigTextArea extends JTextArea {

	private static final int DEFAULTS_IZE = 12;
	private static final String DEFAULT_FONT_NAME = "Arial";

	public BigTextArea() {
		this.setFont(new Font(DEFAULT_FONT_NAME, Font.PLAIN, DEFAULTS_IZE));
	}

	public String getFontName() {
		return this.getFont().getName();
	}

	public void setFontName(String name) {
		setFont(new Font(name, getFont().getStyle(), getFont().getSize()));
	}

	public int getFontSize() {
		return getFont().getSize();
	}

	public void setFontSize(int size) {
		System.out.println("neue Size wurde ausgegeben: "+size);
		setFont(new Font(getFont().getName(), getFont().getStyle(), size));
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("Font: ").append(getFontName()).append(", size: ").append(getFontSize()).append(", char count: ").append(getText().length());
		return string.toString();
	}
}
