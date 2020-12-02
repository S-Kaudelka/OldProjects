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

public class GUI_NumberPlayers extends JFrame {
    private JButton button1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField textfield1;

    //Constructor 
    public GUI_NumberPlayers(){

        this.setTitle("GUI_NumberPlayers");
        this.setSize(300,300);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(300,300));
        contentPane.setBackground(new Color(192,192,192));

        button1 = new JButton();
        button1.setBounds(100,190,100,35);
        button1.setBackground(new Color(214,217,223));
        button1.setForeground(new Color(0,0,0));
        button1.setEnabled(true);
        button1.setFont(new Font("sansserif",0,12));
        button1.setText("Button1");
        button1.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        button1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    NextStep(evt);
                }
            });

        label1 = new JLabel();
        label1.setBounds(50,70,200,35);
        label1.setBackground(new Color(214,217,223));
        label1.setForeground(new Color(0,0,0));
        label1.setEnabled(true);
        label1.setFont(new Font("sansserif",0,12));
        label1.setText("Enter the number of players below.");
        label1.setVisible(true);

        label2 = new JLabel();
        label2.setBounds(75,10,150,35);
        label2.setBackground(new Color(214,217,223));
        label2.setForeground(new Color(0,0,0));
        label2.setEnabled(true);
        label2.setFont(new Font("SansSerif",0,16));
        label2.setText("Generate seating list");
        label2.setVisible(true);

        label3 = new JLabel();
        label3.setBounds(75,250,150,35);
        label3.setBackground(new Color(214,217,223));
        label3.setForeground(new Color(0,0,0));
        label3.setEnabled(true);
        label3.setFont(new Font("sansserif",0,12));
        label3.setText("");
        label3.setVisible(true);

        textfield1 = new JTextField();
        textfield1.setBounds(100,130,100,35);
        textfield1.setBackground(new Color(255,255,255));
        textfield1.setForeground(new Color(0,0,0));
        textfield1.setEnabled(true);
        textfield1.setFont(new Font("sansserif",0,12));
        textfield1.setText("");
        textfield1.setVisible(true);

        //adding components to contentPane panel
        contentPane.add(button1);
        contentPane.add(label1);
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

    //Method mouseClicked for button1
    private void NextStep (MouseEvent evt)
    {
        String input = textfield1.getText();
        
        int number = ConvertInput(input);
        
        if(number < 1 || number%4 != 0)
        {
            label3.setText("Problem with input.");
        }
        else
        {
            this.dispose();
            GUI_AddNames.main(number);
        }
    }

    private int ConvertInput(String S)
    {
        int ret = 0;
        for(int i = 0; i < S.length(); i++)
        {
            boolean found = false;
            for(int j = 0; j <= 9; j++)
            {
                String a = S.charAt(i)+"";
                String b = j+"";
                boolean c = a.charAt(0) == b.charAt(0);
                if(c)
                {
                    found = true;
                    int exp = S.length() - i;
                    int add = j;
                    for(int k = 1; k < exp; k++){add = add * 10;}
                    ret = ret + add;
                }
            }
            //current character is not a number -> error
            if(!found)
            {
                return -1;
            }
        }
        return ret;
    }

    public static void main(String[] args){
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new GUI_NumberPlayers();
                }
            });
    }

}