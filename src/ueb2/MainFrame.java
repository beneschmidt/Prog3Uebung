package ueb2;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends Frame{

	private static final Dimension FRAME_DIMENSION = new Dimension(300, 300);

	public MainFrame(){
		this.setSize(FRAME_DIMENSION);
		
		this.setLayout(new FlowLayout());
		this.add(new EditorCanvas3(FRAME_DIMENSION));
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}
}
