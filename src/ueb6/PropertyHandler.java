package ueb6;

import java.util.Locale;
import java.util.ResourceBundle;

public class PropertyHandler {

	private static final String TEXT_FILE = "Texts";
	private static PropertyHandler instance;
	private ResourceBundle texts;

	private PropertyHandler() {
	}

	public static PropertyHandler getInstance() {
		if (instance == null)
			instance = new PropertyHandler();

		return instance;
	}

	public void loadTexts(Locale locale) {
		texts = ResourceBundle.getBundle(TEXT_FILE, locale);
		System.out.println("Texts geladen: "+texts);
	}
	
	public boolean textsAreLoaded(){
		return texts!=null;
	}

	public String getText(String key) {
		System.out.println("Texts: "+texts);
		return texts.getString(key);
	}
}
