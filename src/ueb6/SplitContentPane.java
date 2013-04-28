package ueb6;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

public class SplitContentPane extends JSplitPane {

	private JList<File> list;
	private JTabbedPane tabbedPane;

	public SplitContentPane() {
		initList();
		tabbedPane = new JTabbedPane();
		setLayout(new BorderLayout());
		this.add(list, BorderLayout.WEST);
		this.add(tabbedPane, BorderLayout.EAST);
	}

	/**
	 * Initialisiert die Liste, in dem der aktuelle Ordner ausgelesen wird und alle vorhandenen Dateien in die Liste gefuegt werden.
	 */
	private void initList() {
		list = new JList<>();
		File rootFile = new File(System.getProperty("user.dir"));
		loadFilesInFolder(rootFile);
	}

	/**
	 * Liest alle Dateien aus einem uebergebene Ordner aus und wirft sie in die Liste
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
