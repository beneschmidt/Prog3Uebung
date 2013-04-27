package ueb4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import ueb3.CloseHelper;

public class FileLoader {

	public static String loadFileContent(String filename) {
		BufferedReader reader = null;
		StringBuilder text = new StringBuilder();
		try {
			// Datei angeben
			final File file = new File(filename);

			// BufferedReader anlegen
			reader = new BufferedReader(new FileReader(file));

			String line = "";
			// Zeilenweise durchlauf der Datei
			while ((line = reader.readLine()) != null) {
				text.append(line).append("\n");
			}

			System.out.println("Datei " + filename + " wurde fertig durchlaufen.");
		} catch (FileNotFoundException e) {
			System.out.println("Fehler beim Einlesen der Datei: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Fehler beim Verarbeiten der Datei: " + e.getMessage());
		} finally {
			CloseHelper.close(reader);
		}

		return text.toString();
	}
}
