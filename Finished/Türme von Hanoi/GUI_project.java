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

public class GUI_project extends JFrame {
    private JButton button1;
    private JLabel label2;
    private JLabel label3;
    private JTextField textfield1;

    //Constructor 
    public GUI_project(){

        this.setTitle("GUI_project");
        this.setSize(200,200);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(200,200));
        contentPane.setBackground(new Color(192,192,192));

        button1 = new JButton();
        button1.setBounds(50,130,100,35);
        button1.setBackground(new Color(192,192,192));
        button1.setForeground(new Color(0,0,0));
        button1.setEnabled(true);
        button1.setFont(new Font("sansserif",0,12));
        button1.setText("Start");
        button1.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        button1.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent evt){
                    Start(evt);
                }
            });

        label2 = new JLabel();
        label2.setBounds(35,10,130,35);
        label2.setBackground(new Color(192,192,192));
        label2.setForeground(new Color(0,0,0));
        label2.setEnabled(true);
        label2.setFont(new Font("sansserif",0,12));
        label2.setText("Enter number of Layers");
        label2.setVisible(true);

        label3 = new JLabel();
        label3.setBounds(55,30,90,35);
        label3.setBackground(new Color(192,192,192));
        label3.setForeground(new Color(0,0,0));
        label3.setEnabled(true);
        label3.setFont(new Font("SansSerif",0,10));
        label3.setText("1 <= number <= 7");
        label3.setVisible(true);

        textfield1 = new JTextField();
        textfield1.setBounds(50,75,100,35);
        textfield1.setBackground(new Color(255,255,255));
        textfield1.setForeground(new Color(0,0,0));
        textfield1.setEnabled(true);
        textfield1.setFont(new Font("sansserif",0,12));
        textfield1.setText("");
        textfield1.setVisible(true);

        //adding components to contentPane panel
        contentPane.add(button1);
        contentPane.add(label2);
        contentPane.add(label3);
        contentPane.add(textfield1);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    //Method mouseRelesed for button1
    private void Start (MouseEvent evt) {
        String tmp = textfield1.getText();
        int n = 0;
        for(int i = 0; i <= 7; i++)
        {
            if(tmp.equals(i+""))
            {
                n = i;
            }
        }
        if(n > 0)
        {
            this.dispose();
            new CONTROLER(n);
        }
    }

    public static void main(String[] args){
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new GUI_project();
                }
            });
    }

}