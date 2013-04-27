package ueb3;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PolygonFileHandler {

	public Graph handlePolygonFile(String dateiname){
		BufferedReader reader = null;
		// Writer zum Schreiben der neuen Datei
		Graph graph = null;
		try {
			// Datei angeben
			final File file = new File(dateiname);

			// BufferedReader anlegen
			reader = new BufferedReader(new FileReader(file));

			String line = "";
			Polygon currentPolygon = null;
			// Zeilenweise durchlauf der Datei
			while ((line = reader.readLine()) != null) {
				// Anzahl Polygone werden festgelegt
				if(graph==null){
					int polygonCount = Integer.parseInt(line);
					graph = new Graph(polygonCount);
				} 
				// einzelnes Polygon wird definiert
				else if(!line.contains(" ")){
					if(currentPolygon!=null)
						graph.addPolygon(currentPolygon);
					
					// neues Objekt anlegen
					currentPolygon = new Polygon();
				} 
				// Punkte für ein Polygon werden spezifiziert
				else {
					String[] pointValues = line.split(" ");
					int xValue = Integer.parseInt(pointValues[0]);
					int yValue = Integer.parseInt(pointValues[1]);
					currentPolygon.addPoint(new Point(xValue, yValue));
				}
			}
			
			if(currentPolygon!=null)
				graph.addPolygon(currentPolygon);

			System.out.println("Datei " + dateiname + " wurde fertig durchlaufen.");
		} catch (FileNotFoundException e) {
			System.out.println("Fehler beim Einlesen der Datei: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Fehler beim Verarbeiten der Datei: " + e.getMessage());
		} finally {
			CloseHelper.close(reader);
		}
		
		return graph;
	}
}
