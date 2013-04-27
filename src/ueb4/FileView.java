package ueb4;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class FileView extends JFrame {

	private static final String INFO = "Info";
	private static final String EXIT = "Exit";
	private static final Dimension FRAME_DIMENSION = new Dimension(800, 600);
	private static final Dimension PANEL_DIMENSION = new Dimension((int) FRAME_DIMENSION.getWidth() - 20, (int) FRAME_DIMENSION.getHeight() - 80);
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu fontMenu;
	private JMenu sizeMenu;
	private JMenu helpMenu;
	private ContentPanel contentPanel;
	private List<Integer> fontSizes = new LinkedList<>();
	private List<String> fontTypes = new LinkedList<>();

	public FileView() {

		this.setSize(FRAME_DIMENSION);

		initFontSizes();
		initFontTypes();
		createAndFilleMenuBar();
		this.setJMenuBar(menuBar);
		this.setLayout(new FlowLayout());

		contentPanel = new ContentPanel(PANEL_DIMENSION);
		contentPanel.setTextSize(fontSizes.get(0));
		contentPanel.setTextFontName(fontTypes.get(0));
		this.add(contentPanel);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void initFontTypes() {
		fontTypes.add("Arial");
		fontTypes.add("Courier");
	}

	private void createAndFilleMenuBar() {
		JMenuItemHelper itemHelper = new JMenuItemHelper(new MenuItemListener());

		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		fileMenu.add(itemHelper.createNewItem(EXIT));

		fontMenu = new JMenu("Font");
		for (String fontType : fontTypes)
			fontMenu.add(itemHelper.createNewItem(fontType));

		sizeMenu = new JMenu("Size");
		for (Integer fontSize : fontSizes)
			sizeMenu.add(itemHelper.createNewItem(fontSize.toString()));

		helpMenu = new JMenu("?");
		helpMenu.add(itemHelper.createNewItem(INFO));

		menuBar.add(fileMenu);
		menuBar.add(fontMenu);
		menuBar.add(sizeMenu);
		menuBar.add(helpMenu);
	}

	/** Initialisiert die Fonts von 10 bis 18 in 2er-Schritten*/
	private void initFontSizes() {
		for (int i = 10; i <= 18; i = i + 2)
			fontSizes.add(i);
	}
	
	private class MenuItemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals(EXIT)) {
				System.out.println("Programm wird beendet! ...");
				System.exit(0);
			} else if (isFontSize(e.getActionCommand())) {
				contentPanel.setTextSize(Integer.parseInt(e.getActionCommand()));
				contentPanel.refreshLabel();
			} else if (isFontType(e.getActionCommand())) {
				contentPanel.setTextFontName(e.getActionCommand());
				contentPanel.refreshLabel();
			} else if (e.getActionCommand().equals(INFO)) {

			}
		}

		private boolean isFontType(String actionCommand) {
			return fontTypes.contains(actionCommand);
		}

		private boolean isFontSize(String actionCommand) {
			try {
				return fontSizes.contains(Integer.parseInt(actionCommand));
			} catch (Exception e) {
				return false;
			}
		}
	}

}
