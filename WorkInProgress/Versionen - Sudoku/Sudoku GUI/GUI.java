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
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JTextField textfield1;
	private JTextField textfield2;
	private JTextField textfield3;
	private JTextField textfield4;
	
	private SUDOKU Sudoku;

	//Constructor 
	public GUI(){

		this.setTitle("GUI_project");
		this.setSize(500,400);
		//menu generate method
		generateMenu();
		this.setJMenuBar(menuBar);

		//pane with null layout
		JPanel contentPane = new JPanel(null);
		contentPane.setPreferredSize(new Dimension(500,400));
		contentPane.setBackground(new Color(192,192,192));


		button1 = new JButton();
		button1.setBounds(370,100,120,35);
		button1.setBackground(new Color(214,217,223));
		button1.setForeground(new Color(0,0,0));
		button1.setEnabled(true);
		button1.setFont(new Font("sansserif",0,12));
		button1.setText("Zahl Einsetzen");
		button1.setVisible(true);
		//Set methods for mouse events
		//Call defined methods
		button1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				Zahl_Einsetzen(evt);
			}
		});


		button2 = new JButton();
		button2.setBounds(220,290,120,35);
		button2.setBackground(new Color(214,217,223));
		button2.setForeground(new Color(0,0,0));
		button2.setEnabled(true);
		button2.setFont(new Font("sansserif",0,12));
		button2.setText("Neues Sudoku");
		button2.setVisible(true);
		//Set methods for mouse events
		//Call defined methods
		button2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				Neues_Sudoku(evt);
			}
		});


		button3 = new JButton();
		button3.setBounds(80,175,140,35);
		button3.setBackground(new Color(214,217,223));
		button3.setForeground(new Color(0,0,0));
		button3.setEnabled(true);
		button3.setFont(new Font("sansserif",0,12));
		button3.setText("Sudoku Ausgeben");
		button3.setVisible(true);
		//Set methods for mouse events
		//Call defined methods
		button3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				Sudoku_Ausgeben(evt);
			}
		});
		
		
		button4 = new JButton();
		button4.setBounds(240,175,140,35);
		button4.setBackground(new Color(214,217,223));
		button4.setForeground(new Color(0,0,0));
		button4.setEnabled(true);
		button4.setFont(new Font("sansserif",0,12));
		button4.setText("Sudoku Kontrolle");
		button4.setVisible(true);
		//Set methods for mouse events
		//Call defined methods
		button4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				Sudoku_Kontrolle(evt);
			}
		});


		label1 = new JLabel();
		label1.setBounds(60,40,90,35);
		label1.setBackground(new Color(214,217,223));
		label1.setForeground(new Color(0,0,0));
		label1.setEnabled(true);
		label1.setFont(new Font("sansserif",0,12));
		label1.setText("Zahl");
		label1.setVisible(true);

		label2 = new JLabel();
		label2.setBounds(150,40,90,35);
		label2.setBackground(new Color(214,217,223));
		label2.setForeground(new Color(0,0,0));
		label2.setEnabled(true);
		label2.setFont(new Font("sansserif",0,12));
		label2.setText("Senkrechte Zahl");
		label2.setVisible(true);

		label3 = new JLabel();
		label3.setBounds(100,240,90,35);
		label3.setBackground(new Color(214,217,223));
		label3.setForeground(new Color(0,0,0));
		label3.setEnabled(true);
		label3.setFont(new Font("sansserif",0,12));
		label3.setText("Nummer");
		label3.setVisible(true);
		
		label4 = new JLabel();
		label4.setBounds(265,40,100,35);
		label4.setBackground(new Color(214,217,223));
		label4.setForeground(new Color(0,0,0));
		label4.setEnabled(true);
		label4.setFont(new Font("sansserif",0,12));
		label4.setText("Waagrechte Zahl");
		label4.setVisible(true);

		textfield1 = new JTextField();
		textfield1.setBounds(35,100,90,35);
		textfield1.setBackground(new Color(255,255,255));
		textfield1.setForeground(new Color(0,0,0));
		textfield1.setEnabled(true);
		textfield1.setFont(new Font("sansserif",0,12));
		textfield1.setText("");
		textfield1.setVisible(true);

		textfield2 = new JTextField();
		textfield2.setBounds(150,100,90,35);
		textfield2.setBackground(new Color(255,255,255));
		textfield2.setForeground(new Color(0,0,0));
		textfield2.setEnabled(true);
		textfield2.setFont(new Font("sansserif",0,12));
		textfield2.setText("");
		textfield2.setVisible(true);

		textfield3 = new JTextField();
		textfield3.setBounds(80,290,90,35);
		textfield3.setBackground(new Color(255,255,255));
		textfield3.setForeground(new Color(0,0,0));
		textfield3.setEnabled(true);
		textfield3.setFont(new Font("sansserif",0,12));
		textfield3.setText("");
		textfield3.setVisible(true);
		
		textfield4 = new JTextField();
		textfield4.setBounds(265,100,90,35);
		textfield4.setBackground(new Color(255,255,255));
		textfield4.setForeground(new Color(0,0,0));
		textfield4.setEnabled(true);
		textfield4.setFont(new Font("sansserif",0,12));
		textfield4.setText("");
		textfield4.setVisible(true);

		//adding components to contentPane panel
		contentPane.add(button1);
		contentPane.add(button2);
		contentPane.add(button3);
		contentPane.add(button4);
		contentPane.add(label1);
		contentPane.add(label2);
		contentPane.add(label3);
		contentPane.add(label4);
		contentPane.add(textfield1);
		contentPane.add(textfield2);
		contentPane.add(textfield3);
		contentPane.add(textfield4);

		//adding panel to JFrame and seting of window position and close operation
		this.add(contentPane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setVisible(true);
		
		Sudoku=new SUDOKU(0);
	}

	//Method mouseClicked for button1
	private void Zahl_Einsetzen (MouseEvent evt) 
	{
	    String a = textfield1.getText();
	    String b = textfield2.getText();
	    String c = textfield4.getText();
	    
	    if(a.equals("") || b.equals("") || c.equals(""))
	    {
	        return;
	    }
	    
	    double Zahl=Double.parseDouble(a);
	    double Feld_Senkrecht=Double.parseDouble(b);
	    double Feld_Waagrecht=Double.parseDouble(c);
	    
	    int Feld_s=(int) Feld_Senkrecht;
	    int Feld_w=(int) Feld_Waagrecht;
	    
	    textfield1.setText("");
	    textfield2.setText("");
	    textfield4.setText("");
	    
	    int Zahl_einsetzen=(int) Zahl;
        
        Sudoku.GUIZahlEinsetzen(Zahl_einsetzen ,Feld_s ,Feld_w);
	}

	//Method mouseClicked for button2
	private void Neues_Sudoku (MouseEvent evt) 
	{
	    String Textfeld=textfield3.getText();
	    textfield3.setText("");
	    Sudoku.GUISudokuErstellen(Textfeld);
	}

	//Method mouseClicked for button3
	private void Sudoku_Ausgeben (MouseEvent evt) 
	{
	    Sudoku.SudokuAnzeigen();
	}
	
	private void Sudoku_Kontrolle (MouseEvent evt) 
	{
	    Sudoku.Kontrolle();
	}

	//method for generate menu
	public void generateMenu(){
		menuBar = new JMenuBar();
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