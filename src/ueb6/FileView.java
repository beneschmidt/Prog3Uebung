package ueb6;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;

public class FileView extends JFrame  {

	private static final String SAVE_AS = "saveAs";
	private static final String SAVE = "save";
	private static final String OPEN = "open";
	private static final String NEW = "new";
	private static final String INFO = "info";
	private static final String EXIT = "exit";
	private static final Dimension FRAME_DIMENSION = new Dimension(800, 600);
	// private static final Dimension PANEL_DIMENSION = new Dimension((int)
	// FRAME_DIMENSION.getWidth() - 20, (int) FRAME_DIMENSION.getHeight() - 90);

	private JMenuBar menuBar;
	private JToolBar toolBar;
	private JMenu fileMenu;
	private JMenu fontMenu;
	private JMenu sizeMenu;
	private JMenu helpMenu;
	private FileLoaderFrame fileLoaderFrame;
	private FileSaverFrame fileSaverFrame;
	private InfoFrame infoFrame;
	private FileRootPanel rootPanel;
	private SplitContentPane splitPane;
	// private ContentPanel contentPanel;
	private PropertyHandler propertyHandler;
	private List<Integer> fontSizes = new LinkedList<>();
	private List<String> fontTypes = new LinkedList<>();
	private List<FileMenuEntry> fileMenuEntries = new LinkedList<>();
	private List<String> helpMenuEntries = new LinkedList<>();

	public FileView() {

		this.setSize(FRAME_DIMENSION);
		propertyHandler = PropertyHandler.getInstance();

		initFontSizes();
		initFontTypes();
		initFileMenuEntries();
		initHelpMenuEntries();
		createAndFillMenuBar();
		this.setJMenuBar(menuBar);
		this.setLayout(new BorderLayout());

		createAndFillToolBar();

		// contentPanel = new ContentPanel(PANEL_DIMENSION);
		// contentPanel.setTextSize(fontSizes.get(0));
		// contentPanel.setTextFontName(fontTypes.get(0));
		// this.add(contentPanel, BorderLayout.SOUTH);

		rootPanel = new FileRootPanel();
		splitPane = new SplitContentPane();
		this.add(rootPanel, BorderLayout.NORTH);
		this.add(splitPane, BorderLayout.SOUTH);

		// fileLoaderFrame = new FileLoaderFrame(contentPanel);
		// fileLoaderFrame.dispose();
		// fileSaverFrame = new FileSaverFrame(contentPanel);
		// fileSaverFrame.dispose();
		infoFrame = new InfoFrame();
		infoFrame.dispose();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void createAndFillToolBar() {
		toolBar = new JToolBar(JToolBar.HORIZONTAL);
		this.add(toolBar, BorderLayout.NORTH);

		JItemHelper buttonHelper = new JItemHelper(new ToolBarItemListener());
		toolBar.add(buttonHelper.createNewToolBarButton("res/new.jpg", NEW));
		toolBar.add(buttonHelper.createNewToolBarButton("res/open.jpg", OPEN));
		toolBar.add(buttonHelper.createNewToolBarButton("res/save.jpg", SAVE));
		toolBar.add(buttonHelper.createNewToolBarButton("res/saveAs.jpg", SAVE_AS));
		toolBar.add(buttonHelper.createNewToolBarButton("res/exit.jpg", EXIT));
	}

	private void initHelpMenuEntries() {
		helpMenuEntries.add(propertyHandler.getText(INFO));

	}

	private void initFileMenuEntries() {
		fileMenuEntries.add(new FileMenuEntry(propertyHandler.getText(NEW), "res/new.jpg"));
		fileMenuEntries.add(new FileMenuEntry(propertyHandler.getText(OPEN), "res/open.jpg"));
		fileMenuEntries.add(new FileMenuEntry(propertyHandler.getText(SAVE), "res/save.jpg"));
		fileMenuEntries.add(new FileMenuEntry(propertyHandler.getText(SAVE_AS), "res/saveAs.jpg"));
		fileMenuEntries.add(new FileMenuEntry(propertyHandler.getText(EXIT), "res/exit.jpg"));
	}

	private void initFontTypes() {
		fontTypes.add("Arial");
		fontTypes.add("Courier");
	}

	private void createAndFillMenuBar() {
		JItemHelper itemHelper = new JItemHelper(new MenuItemListener());

		menuBar = new JMenuBar();
		fileMenu = new JMenu(propertyHandler.getText("file"));
		for (FileMenuEntry fileMenuEntry : fileMenuEntries)
			fileMenu.add(itemHelper.createNewMenuItemWithIcon(fileMenuEntry.getText(), fileMenuEntry.getIconPath()));

		fontMenu = new JMenu(propertyHandler.getText("font"));
		for (String fontType : fontTypes)
			fontMenu.add(itemHelper.createNewMenuItem(fontType));

		sizeMenu = new JMenu(propertyHandler.getText("size"));
		for (Integer fontSize : fontSizes)
			sizeMenu.add(itemHelper.createNewMenuItem(fontSize.toString()));

		helpMenu = new JMenu("?");
		for (String helpEntry : helpMenuEntries)
			helpMenu.add(itemHelper.createNewMenuItem(helpEntry));

		menuBar.add(fileMenu);
		menuBar.add(fontMenu);
		menuBar.add(sizeMenu);
		menuBar.add(helpMenu);
	}

	/** Initialisiert die Fonts von 10 bis 18 in 2er-Schritten */
	private void initFontSizes() {
		for (int i = 10; i <= 18; i = i + 2)
			fontSizes.add(i);
	}

	private void newAction() {
		// contentPanel.clearTextAndFileName();
	}

	private void openAction() {
		fileLoaderFrame.setVisible(true);
		fileLoaderFrame.pack();
	}

	private void saveAction() {
		// if (contentPanel.canQuickSave()) {
		// contentPanel.save();
		// } else {
		// saveAsAction();
		// }
	}

	private void exitAction() {
		System.out.println("Programm wird beendet! ...");
		System.exit(0);
	}

	private void saveAsAction() {
		fileSaverFrame.setVisible(true);
		fileSaverFrame.pack();
	}

	private class MenuItemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals(propertyHandler.getText(EXIT))) {
				exitAction();
			} else if (e.getActionCommand().equals(propertyHandler.getText(NEW))) {
				newAction();
			} else if (e.getActionCommand().equals(propertyHandler.getText(OPEN))) {
				openAction();
			} else if (e.getActionCommand().equals(propertyHandler.getText(SAVE))) {
				saveAction();
			} else if (e.getActionCommand().equals(propertyHandler.getText(SAVE_AS))) {
				saveAsAction();
			} else if (isFontSize(e.getActionCommand())) {
				// contentPanel.setTextSize(Integer.parseInt(e.getActionCommand()));
			} else if (isFontType(e.getActionCommand())) {
				// contentPanel.setTextFontName(e.getActionCommand());
			} else if (e.getActionCommand().equals(propertyHandler.getText(INFO))) {
				infoFrame.setVisible(true);
				infoFrame.pack();
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

	private class ToolBarItemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
			if (action.equals(NEW)) {
				newAction();
			} else if (action.equals(OPEN)) {
				openAction();
			} else if (action.equals(SAVE)) {
				saveAction();
			} else if (action.equals(SAVE_AS)) {
				saveAsAction();
			} else if (action.equals(EXIT)) {
				exitAction();
			}

		}
	}
}
