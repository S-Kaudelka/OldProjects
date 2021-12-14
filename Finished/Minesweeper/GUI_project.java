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
    private JMenuBar menuBar;
    private JButton button1;
    private JLabel label1;
    private JLabel label4;
    private JLabel labelW;
    private JLabel label_H;
    private JTextField textfield_heigth;
    private JTextField textfieldwidth;

    //Constructor 
    public GUI_project(){

        this.setTitle("GUI_project");
        this.setSize(200,200);
        //menu generate method
        generateMenu();
        this.setJMenuBar(menuBar);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(200,200));
        contentPane.setBackground(new Color(192,192,192));

        button1 = new JButton();
        button1.setBounds(55,150,90,35);
        button1.setBackground(new Color(214,217,223));
        button1.setForeground(new Color(0,0,0));
        button1.setEnabled(true);
        button1.setFont(new Font("sansserif",0,12));
        button1.setText("Start");
        button1.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        button1.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent evt){
                    Start_Game(evt);
                }
            });

        label1 = new JLabel();
        label1.setBounds(20,10,160,35);
        label1.setBackground(new Color(214,217,223));
        label1.setForeground(new Color(0,0,0));
        label1.setEnabled(true);
        label1.setFont(new Font("SansSerif",0,14));
        label1.setText("Enter Height and Width");
        label1.setVisible(true);

        label4 = new JLabel();
        label4.setBounds(45,35,110,35);
        label4.setBackground(new Color(214,217,223));
        label4.setForeground(new Color(0,0,0));
        label4.setEnabled(true);
        label4.setFont(new Font("SansSerif",0,14));
        label4.setText("and press Start");
        label4.setVisible(true);

        labelW = new JLabel();
        labelW.setBounds(30,70,60,35);
        labelW.setBackground(new Color(214,217,223));
        labelW.setForeground(new Color(0,0,0));
        labelW.setEnabled(true);
        labelW.setFont(new Font("SansSerif",0,14));
        labelW.setText("Width");
        labelW.setVisible(true);

        label_H = new JLabel();
        label_H.setBounds(120,70,60,35);
        label_H.setBackground(new Color(214,217,223));
        label_H.setForeground(new Color(0,0,0));
        label_H.setEnabled(true);
        label_H.setFont(new Font("SansSerif",0,14));
        label_H.setText("Height");
        label_H.setVisible(true);

        textfield_heigth = new JTextField();
        textfield_heigth.setBounds(100,105,90,35);
        textfield_heigth.setBackground(new Color(255,255,255));
        textfield_heigth.setForeground(new Color(0,0,0));
        textfield_heigth.setEnabled(true);
        textfield_heigth.setFont(new Font("sansserif",0,12));
        textfield_heigth.setText("");
        textfield_heigth.setVisible(true);

        textfieldwidth = new JTextField();
        textfieldwidth.setBounds(10,105,90,35);
        textfieldwidth.setBackground(new Color(255,255,255));
        textfieldwidth.setForeground(new Color(0,0,0));
        textfieldwidth.setEnabled(true);
        textfieldwidth.setFont(new Font("sansserif",0,12));
        textfieldwidth.setText("");
        textfieldwidth.setVisible(true);

        //adding components to contentPane panel
        contentPane.add(button1);
        contentPane.add(label1);
        contentPane.add(label4);
        contentPane.add(labelW);
        contentPane.add(label_H);
        contentPane.add(textfield_heigth);
        contentPane.add(textfieldwidth);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    //Method mouseRelesed for button1
    private void Start_Game (MouseEvent evt) {
        String W = textfieldwidth.getText();
        String H = textfield_heigth.getText();
        
        int Wide_tmp = ConvertInput(W);
        int High_tmp = ConvertInput(H);
        
        if(Wide_tmp == -1 || High_tmp == -1)
        {
            textfield_heigth.setText("Error");
            textfieldwidth.setText("Width");
            return;
        }
        
        if(Wide_tmp < 9 || Wide_tmp > 40 || High_tmp < 9 || High_tmp > 40)
        {
            textfield_heigth.setText("Size");
            textfieldwidth.setText("Width");
            return;
        }
        
        int Wide = Wide_tmp*25;
        int High = High_tmp*25+55;
        
        this.dispose();
        
        CONTROLLER c = new CONTROLLER(High,Wide);
    }

    //method for generate menu
    public void generateMenu(){
        menuBar = new JMenuBar();
    }


    public static void main(String[] args){
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new GUI_project();
                }
            });
    }

    //Converts a string consisting of numbers into the respective int-value
    //returns -1, if it contains anything that isnt a number
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
}