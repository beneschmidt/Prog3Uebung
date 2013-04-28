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
		list = new JList<>();
		DefaultListModel<File> model = new DefaultListModel<>();
		File file = new File(System.getProperty("user.dir"));
		for (File nextFile : file.listFiles()) {
			model.addElement(nextFile);
		}
		list.setModel(model);
		tabbedPane = new JTabbedPane();
		setLayout(new BorderLayout());
		this.add(list, BorderLayout.WEST);
		this.add(tabbedPane, BorderLayout.EAST);
	}

}
