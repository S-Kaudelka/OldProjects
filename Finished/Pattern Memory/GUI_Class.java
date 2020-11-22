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

public class GUI_Class extends JFrame {
    private JMenuBar menuBar;
    private JTextField SizeInput;
    private JButton button1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;

    //Constructor 
    public GUI_Class(){

        this.setTitle("GUI_Class");
        this.setSize(500,400);
        //menu generate method
        generateMenu();
        this.setJMenuBar(menuBar);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500,400));
        contentPane.setBackground(new Color(192,192,192));

        SizeInput = new JTextField();
        SizeInput.setBounds(205,185,90,35);
        SizeInput.setBackground(new Color(255,255,255));
        SizeInput.setForeground(new Color(0,0,0));
        SizeInput.setEnabled(true);
        SizeInput.setFont(new Font("sansserif",0,12));
        SizeInput.setText("");
        SizeInput.setVisible(true);

        button1 = new JButton();
        button1.setBounds(205,260,90,35);
        button1.setBackground(new Color(214,217,223));
        button1.setForeground(new Color(0,0,0));
        button1.setEnabled(true);
        button1.setFont(new Font("sansserif",0,12));
        button1.setText("Start");
        button1.setVisible(true);
        button1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    StartGame(evt);
                }
            });

        label1 = new JLabel();
        label1.setBounds(140,15,220,35);
        label1.setBackground(new Color(214,217,223));
        label1.setForeground(new Color(0,0,0));
        label1.setEnabled(true);
        label1.setFont(new Font("SansSerif",0,20));
        label1.setText("Pattern - Memory Game");
        label1.setVisible(true);

        label2 = new JLabel();
        label2.setBounds(175,150,150,35);
        label2.setBackground(new Color(214,217,223));
        label2.setForeground(new Color(0,0,0));
        label2.setEnabled(true);
        label2.setFont(new Font("sansserif",0,12));
        label2.setText("Enter the size for the field:");
        label2.setVisible(true);

        label3 = new JLabel();
        label3.setBounds(90,70,330,35);
        label3.setBackground(new Color(214,217,223));
        label3.setForeground(new Color(0,0,0));
        label3.setEnabled(true);
        label3.setFont(new Font("sansserif",0,12));
        label3.setText("A random sequence will be played. You have to repeat it.");
        label3.setVisible(true);

        label4 = new JLabel();
        label4.setBounds(35,90,440,35);
        label4.setBackground(new Color(214,217,223));
        label4.setForeground(new Color(0,0,0));
        label4.setEnabled(true);
        label4.setFont(new Font("sansserif",0,12));
        label4.setText("The length of the sequence will be increased by 1 after each successfull repeat.");
        label4.setVisible(true);

        label5 = new JLabel();
        label5.setBounds(187,320,130,35);
        label5.setBackground(new Color(214,217,223));
        label5.setForeground(new Color(0,0,0));
        label5.setEnabled(true);
        label5.setFont(new Font("sansserif",0,12));
        label5.setText("");
        label5.setVisible(true);

        //adding components to contentPane panel
        contentPane.add(SizeInput);
        contentPane.add(button1);
        contentPane.add(label1);
        contentPane.add(label2);
        contentPane.add(label3);
        contentPane.add(label4);
        contentPane.add(label5);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    //Method mouseClicked for button1
    private void StartGame (MouseEvent evt) {
        String Input = SizeInput.getText();

        if(Input == null)
        {
            label5.setText("Problem with the input");
            return;
        }
        
        int[] digits = new int[Input.length()];
        
        for(int i = 0; i < Input.length(); i++)
        {
            digits[i] = ((int) Input.charAt(i)) - 48;
        }
        
        for(int i = 0; i < Input.length(); i++)
        {
            if(digits[i] < 0 || digits[i] > 9) // character is not a number
            {
                label5.setText("Problem with the input");
                return;
            }
        }
        
        int In = 0; // contains the converted Input, if it is a number
        
        for(int i = 0; i < Input.length(); i++)
        {
            int tmp = digits[i];
            for(int j = 1; j < digits.length - i; j++)
            {
                tmp = tmp * 10;
            }
            In = In + tmp;
        }
        
        if(In < 2 || In > 10)
        {
            label5.setText("Number too low or high");
            return;
        }
        
        GAMEWINDOW.Breite = In*60 + 10;
        GAMEWINDOW.Hoehe = In*60 + 10;
        
        new CONTROLLER(In);
        this.dispose();
    }

    //method for generate menu
    public void generateMenu(){
        menuBar = new JMenuBar();
    }

    public static void main(String[] args){
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new GUI_Class();
                }
            });
    }

}