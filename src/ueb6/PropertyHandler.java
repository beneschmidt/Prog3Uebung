package ueb6;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Singleton-Handler zum Laden und auslesen des Sprachfiles. Dieses ist hier fest verankert und traegt den Titel "Texts".
 * 
 * @author Benne
 */
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
	}

	public boolean textsAreLoaded() {
		return texts != null;
	}

	public String getText(String key) {
		return texts.getString(key);
	}
}
