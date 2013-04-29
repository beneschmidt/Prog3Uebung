package ueb6;

import java.util.Locale;

public class Main {

	public static void main(String[] args) {
		PropertyHandler.getInstance().loadTexts(Locale.GERMANY);
		FileViewNavigator view = new FileViewNavigator();
		view.setVisible(true);
	}
}
