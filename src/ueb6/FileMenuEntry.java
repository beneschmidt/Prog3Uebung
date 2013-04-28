package ueb6;

public class FileMenuEntry {

	private final String text;
	private final String iconPath;

	public FileMenuEntry(String text, String iconPath) {
		super();
		this.text = text;
		this.iconPath = iconPath;
	}

	public String getText() {
		return text;
	}

	public String getIconPath() {
		return iconPath;
	}

}
