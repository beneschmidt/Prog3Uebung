package ueb6;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.Transient;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class SplitContentPane extends JSplitPane {

	private static final Dimension PREFERRED_DIMENSION = new Dimension(800, 450);
	private static final Dimension MINIMUM_DIMENSION = new Dimension(800, 100);
	private JList<File> list;
	private JTabbedPane tabbedPane;
	private JPanel contentTab;
	private JButton load;
	private JTextArea contentArea;
	private JPanel propertyTab;
	private JLabel readable;
	private JLabel directory;

	public SplitContentPane() {
		initList();
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setPreferredSize(new Dimension(300, 450));

		initPropertyTab();
		initContentTab();

		setLayout(new BorderLayout());
		this.add(list, BorderLayout.CENTER, 0);
		this.add(tabbedPane, BorderLayout.CENTER, -1);
	}

	private void initContentTab() {
		contentTab = new JPanel();
		contentTab.setLayout(new BorderLayout());
		// TODO: Sprachabhaengig machen
		load = new JButton("load");
		contentArea = new JTextArea();
		contentTab.add(load, BorderLayout.NORTH);
		contentTab.add(contentArea, BorderLayout.CENTER);
		tabbedPane.add("Content", contentTab);
	}

	private void initPropertyTab() {
		propertyTab = new JPanel();
		propertyTab.setLayout(new GridLayout(4, 1));
		readable = new JLabel("Lesbar: ");
		directory = new JLabel("Directory: ");
		propertyTab.add(readable);
		propertyTab.add(directory);
		tabbedPane.addTab("Property", propertyTab);
	}

	@Override
	@Transient
	public Dimension getPreferredSize() {
		return PREFERRED_DIMENSION;
	}

	@Override
	@Transient
	public Dimension getMinimumSize() {
		return MINIMUM_DIMENSION;
	}

	/**
	 * Initialisiert die Liste, in dem der aktuelle Ordner ausgelesen wird und
	 * alle vorhandenen Dateien in die Liste gefuegt werden.
	 */
	private void initList() {
		list = new JList<>();
		File rootFile = new File(System.getProperty("user.dir"));
		loadFilesInFolder(rootFile);
	}

	/**
	 * Liest alle Dateien aus einem uebergebene Ordner aus und wirft sie in die
	 * Liste
	 * 
	 * @param rootFile
	 */
	public void loadFilesInFolder(File rootFile) {
		DefaultListModel<File> model = new DefaultListModel<>();
		for (File nextFile : rootFile.listFiles()) {
			model.addElement(nextFile);
		}
		list.setModel(model);
	}

}
