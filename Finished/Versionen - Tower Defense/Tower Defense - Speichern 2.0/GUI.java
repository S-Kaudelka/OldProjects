/**
*Text genereted by Simple GUI Extension for BlueJ
*/
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.border.Border;
import javax.swing.*;


public class GUI extends JFrame {

	private JMenuBar menuBar;
	private JButton button1;
	private JLabel label1;
	private JTextField textfield1;
	
	private START s;

	//Constructor 
	public GUI(){

		this.setTitle("GUI");
		this.setSize(223,159);
		//menu generate method
		generateMenu();
		this.setJMenuBar(menuBar);

		//pane with null layout
		JPanel contentPane = new JPanel(null);
		contentPane.setPreferredSize(new Dimension(223,159));
		contentPane.setBackground(new Color(192,192,192));


		button1 = new JButton();
		button1.setBounds(67,98,90,35);
		button1.setBackground(new Color(214,217,223));
		button1.setForeground(new Color(0,0,0));
		button1.setEnabled(true);
		button1.setFont(new Font("sansserif",0,12));
		button1.setText("Starten");
		button1.setVisible(true);
		//Set methods for mouse events
		//Call defined methods
		button1.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt){
				b1gedrueckt(evt);
			}
		});


		label1 = new JLabel();
		label1.setBounds(5,5,230,36);
		label1.setBackground(new Color(214,217,223));
		label1.setForeground(new Color(0,0,0));
		label1.setEnabled(true);
		label1.setFont(new Font("sansserif",0,12));
		label1.setText("Hier den Benutzernamen eingeben");
		label1.setVisible(true);

		textfield1 = new JTextField();
		textfield1.setBounds(66,49,90,35);
		textfield1.setBackground(new Color(255,255,255));
		textfield1.setForeground(new Color(0,0,0));
		textfield1.setEnabled(true);
		textfield1.setFont(new Font("sansserif",0,12));
		textfield1.setText("");
		textfield1.setVisible(true);

		//adding components to contentPane panel
		contentPane.add(button1);
		contentPane.add(label1);
		contentPane.add(textfield1);

		//adding panel to JFrame and seting of window position and close operation
		this.add(contentPane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setVisible(true);
	}

	//Method mouseRelesed for button1
	private void b1gedrueckt (MouseEvent evt) 
	{
	    String t = textfield1.getText();
	    if(t.equals("")) return;
	    if(t.equals("Spielernamen")) return;
	    textfield1.setText("");
	    s = new START(t);
	    this.dispose();
	}

	//method for generate menu
	public void generateMenu(){
		menuBar = new JMenuBar();

		JMenu file = new JMenu("File");
		JMenu tools = new JMenu("Tools");
		JMenu help = new JMenu("Help");

		JMenuItem open = new JMenuItem("Open   ");
		JMenuItem save = new JMenuItem("Save   ");
		JMenuItem exit = new JMenuItem("Exit   ");
		JMenuItem preferences = new JMenuItem("Preferences   ");
		JMenuItem about = new JMenuItem("About   ");


		file.add(open);
		file.add(save);
		file.addSeparator();
		file.add(exit);
		tools.add(preferences);
		help.add(about);

		menuBar.add(file);
		menuBar.add(tools);
		menuBar.add(help);
	}



	 public static void main(String[] args){
		System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GUI();
			}
		});
	}

}