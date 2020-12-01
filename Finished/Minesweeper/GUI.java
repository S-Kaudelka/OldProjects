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
    private JLabel label2;
    private JLabel label3;
    private JTextField textfield1;
    private JTextField textfield2;

    //Constructor 
    public GUI(){

        this.setTitle("GUI");
        this.setSize(197,175);
        //menu generate method
        generateMenu();
        this.setJMenuBar(menuBar);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(197,175));
        contentPane.setBackground(new Color(192,192,192));

        button1 = new JButton();
        button1.setBounds(55,95,90,35);
        button1.setBackground(new Color(214,217,223));
        button1.setForeground(new Color(0,0,0));
        button1.setEnabled(true);
        button1.setFont(new Font("sansserif",0,12));
        button1.setText("OK");
        button1.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        button1.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent evt){
                    Mausklick(evt);
                }
            });

        label1 = new JLabel();
        label1.setBounds(35,5,50,35);
        label1.setBackground(new Color(214,217,223));
        label1.setForeground(new Color(0,0,0));
        label1.setEnabled(true);
        label1.setFont(new Font("sansserif",0,12));
        label1.setText("Breite");
        label1.setVisible(true);

        label2 = new JLabel();
        label2.setBounds(120,5,45,35);
        label2.setBackground(new Color(214,217,223));
        label2.setForeground(new Color(0,0,0));
        label2.setEnabled(true);
        label2.setFont(new Font("sansserif",0,12));
        label2.setText("Hoehe");
        label2.setVisible(true);
        
        label3 = new JLabel();
        label3.setBounds(55,135,125,35);
        label3.setBackground(new Color(214,217,223));
        label3.setForeground(new Color(0,0,0));
        label3.setEnabled(true);
        label3.setFont(new Font("sansserif",0,12));
        label3.setText("");
        label3.setVisible(true);

        textfield1 = new JTextField();
        textfield1.setBounds(5,46,90,35);
        textfield1.setBackground(new Color(255,255,255));
        textfield1.setForeground(new Color(0,0,0));
        textfield1.setEnabled(true);
        textfield1.setFont(new Font("sansserif",0,12));
        textfield1.setText("");
        textfield1.setVisible(true);

        textfield2 = new JTextField();
        textfield2.setBounds(100,46,90,35);
        textfield2.setBackground(new Color(255,255,255));
        textfield2.setForeground(new Color(0,0,0));
        textfield2.setEnabled(true);
        textfield2.setFont(new Font("sansserif",0,12));
        textfield2.setText("");
        textfield2.setVisible(true);

        //adding components to contentPane panel
        contentPane.add(button1);
        contentPane.add(label1);
        contentPane.add(label2);
        contentPane.add(label3);
        contentPane.add(textfield1);
        contentPane.add(textfield2);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    //Method mouseRelesed for button1
    private void Mausklick (MouseEvent evt) {
        String X = textfield1.getText();
        String Y = textfield2.getText();
        if(X.equals("") || Y.equals("")) return;
        int x = (int) Double.parseDouble(X);
        int y = (int) Double.parseDouble(Y);
        
        if(x > 40 || y > 40)
        {
            label3.setText("Spielfeld zu gross");
            return;
        }
        if(x < 5 || y < 5)
        {
            label3.setText("Spielfeld zu klein");
            return;
        }
        
        this.dispose();
        
        new CONTROLER(x, y);
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