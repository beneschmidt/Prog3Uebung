package ueb6;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Transient;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SplitContentPane extends JSplitPane implements RootFolder {

	private static final String LOAD_COMMAND = "LOAD";
	private static final Dimension PREFERRED_DIMENSION = new Dimension(800, 450);
	private static final Dimension MINIMUM_DIMENSION = new Dimension(800, 100);
	private JList<File> list;
	private JTabbedPane tabbedPane;
	private JPanel contentTab;
	private JButton load;
	private JTextArea contentArea;
	private JPanel propertyTab;
	private JLabel readable, directory;
	private JScrollPane textScrollPane;
	private PropertyHandler props;
	private static String YES, NO;

	public SplitContentPane() {
		props = PropertyHandler.getInstance();
		YES = props.getText("yes");
		NO = props.getText("no");
		initList();
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setPreferredSize(new Dimension(300, 450));

		initPropertyTab();
		initContentTab();

		setLayout(new BorderLayout());
		this.add(list, BorderLayout.WEST);
		this.add(tabbedPane, BorderLayout.CENTER, -1);
	}

	private void initContentTab() {
		contentTab = new JPanel();
		contentTab.setLayout(new BorderLayout());
		load = new JButton(props.getText("load"));
		load.setActionCommand(LOAD_COMMAND);
		load.addActionListener(new LoadActionListener());

		contentArea = new JTextArea();
		textScrollPane = new JScrollPane(contentArea);
		contentTab.add(load, BorderLayout.NORTH);
		contentTab.add(textScrollPane, BorderLayout.CENTER);
		tabbedPane.add(props.getText("content"), contentTab);
	}

	private void initPropertyTab() {
		propertyTab = new JPanel();
		propertyTab.setLayout(new GridLayout(4, 1));
		readable = new JLabel(props.getText("readable") + ": ");
		directory = new JLabel(props.getText("directory") + ": ");
		propertyTab.add(readable);
		propertyTab.add(directory);
		tabbedPane.addTab(props.getText("properties"), propertyTab);
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
		list.addListSelectionListener(new FileListSelectionListener());
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

	/**
	 * Faengt das Druecken eines Load-Buttons ab und handhabt entsprechend das
	 * Laden eines gewaehlten Files.
	 * 
	 * @author schmidtb
	 */
	private class LoadActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getActionCommand().equals(LOAD_COMMAND)) {
				try {
					if (isCurrentFileTextReadable()) {
						File file = list.getSelectedValue();
						String text = FileHandler.loadFileContent(file);
						contentArea.setText(text);
					}
				} catch (FileNotFoundException e) {
					// TODO: an höhere Schicht weitergeben
					e.printStackTrace();
				} catch (IOException e) {
					// TODO: an höhere Schicht weitergeben
					e.printStackTrace();
				}
			}
		}

		private boolean isCurrentFileTextReadable() {
			return list.getSelectedValue() != null && !list.getSelectedValue().isDirectory() && list.getSelectedValue().canRead();
		}
	}

	/**
	 * Handhabt die Auswahl eines Elements in der File-Liste.
	 * 
	 * @author schmidtb
	 */
	private class FileListSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			File selectedFile = list.getSelectedValue();

			if (selectedFile != null) {
				readable.setText(props.getText("readable") + ": " + (selectedFile.canRead() ? YES : NO));
				directory.setText(props.getText("directory")+": " + (selectedFile.isDirectory() ? YES : NO));
			}
		}
	}

	@Override
	public void setRootFolder(String rootFolder) {
		File file = new File(rootFolder);
		loadFilesInFolder(file);
	}
}
