package ueb3;

import java.awt.Dimension;

public class DisplayData {

	private static final String FILE_PARAM = "-file";
	private static final String HEIGHT_PARAM = "-height";
	private static final String WIDTH_PARAM = "-width";
	/** Dateiname aus der die Daten gelesen werden sollen */
	private String filename;
	/** Hoehe und Breite des Fensters */
	private int width, height;

	public static void main(String[] args) {
		DisplayData displayData = new DisplayData();
		displayData.parseArguments(args);
		System.out.println(displayData.toString());

		Graph graph = new PolygonFileHandler().handlePolygonFile(displayData.filename);
		System.out.println("Graph gelesen: " + graph);
		
		MainFrame mainFrame = new MainFrame(new Dimension(graph.getMaxX(), graph.getMaxY()));
		mainFrame.setVisible(true);
		mainFrame.drawGraph(graph);
	}

	private void parseArguments(String[] args) {
		for (int i = 0; i < args.length; i++) {
			String argument = args[i];
			if (argument.equals(FILE_PARAM)) {
				filename = args[i + 1];
			} else if (argument.equals(WIDTH_PARAM)) {
				width = Integer.parseInt(args[i + 1]);
			} else if (argument.equals(HEIGHT_PARAM)) {
				height = Integer.parseInt(args[i + 1]);
			}
		}
	}

	@Override
	public String toString() {
		return "DisplayData [filename=" + filename + ", width=" + width + ", height=" + height + "]";
	}
}
