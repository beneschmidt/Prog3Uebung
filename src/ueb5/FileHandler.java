package ueb5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ueb3.CloseHelper;

public class FileHandler {

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

	public static void saveFileContent(String content, String filename) {
		BufferedWriter writer = null;
		try {
			// Datei angeben
			final File file = new File(filename);

			// BufferedReader anlegen
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);

			System.out.println("Datei " + filename + " wurde fertig durchlaufen.");
		} catch (FileNotFoundException e) {
			System.out.println("Fehler beim Einlesen der Datei: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Fehler beim Verarbeiten der Datei: " + e.getMessage());
		} finally {
			CloseHelper.close(writer);
		}
	}
}
