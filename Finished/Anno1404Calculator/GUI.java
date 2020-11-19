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
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JLabel label14;
    private JLabel label15;
    private JLabel label16;
    private JLabel label17;
    private JLabel label18;
    private JTextField textfield1;
    private JTextField textfield2;
    private JTextField textfield3;
    private JTextField textfield4;
    private JTextField textfield5;
    private JTextField textfield6;
    private JTextField textfield7;
    private JTextField textfield8;

    //Constructor 
    public GUI(){

        this.setTitle("GUI");
        this.setSize(1100,400);
        //menu generate method
        generateMenu();
        this.setJMenuBar(menuBar);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(1100,400));
        contentPane.setBackground(new Color(192,192,192));

        button1 = new JButton();
        button1.setBounds(180,300,90,35);
        button1.setBackground(new Color(214,217,223));
        button1.setForeground(new Color(0,0,0));
        button1.setEnabled(true);
        button1.setFont(new Font("sansserif",0,12));
        button1.setText("Calculate");
        button1.setVisible(true);
        button1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    Calculate(evt);
                }
            });

        button2 = new JButton();
        button2.setBounds(710,300,90,35);
        button2.setBackground(new Color(214,217,223));
        button2.setForeground(new Color(0,0,0));
        button2.setEnabled(true);
        button2.setFont(new Font("sansserif",0,12));
        button2.setText("Calculate");
        button2.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        button2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    CalculateOrient(evt);
                }
            });

        label1 = new JLabel();
        label1.setBounds(350,10,370,35);
        label1.setBackground(new Color(214,217,223));
        label1.setForeground(new Color(0,0,0));
        label1.setEnabled(true);
        label1.setFont(new Font("SansSerif",0,18));
        label1.setText("Calculator for Anno 1404 Consumption goods");
        label1.setVisible(true);

        label2 = new JLabel();
        label2.setBounds(375,50,350,35);
        label2.setBackground(new Color(214,217,223));
        label2.setForeground(new Color(0,0,0));
        label2.setEnabled(true);
        label2.setFont(new Font("sansserif",0,12));
        label2.setText("Insert respective population count for Okzident or Orient");
        label2.setVisible(true);

        label3 = new JLabel();
        label3.setBounds(10,205,500,35);
        label3.setBackground(new Color(214,217,223));
        label3.setForeground(new Color(0,0,0));
        label3.setEnabled(true);
        label3.setFont(new Font("sansserif",0,12));
        label3.setText("1 Fish; 2 Cider; 3 Spices; 4 Linen Garments; 5 Bread; 6 Beer; 7 Leather Jerkins; 8 Books;");
        label3.setVisible(true);

        label4 = new JLabel();
        label4.setBounds(10,90,90,35);
        label4.setBackground(new Color(214,217,223));
        label4.setForeground(new Color(0,0,0));
        label4.setEnabled(true);
        label4.setFont(new Font("sansserif",0,12));
        label4.setText("Peasants");
        label4.setVisible(true);

        label5 = new JLabel();
        label5.setBounds(130,90,90,35);
        label5.setBackground(new Color(214,217,223));
        label5.setForeground(new Color(0,0,0));
        label5.setEnabled(true);
        label5.setFont(new Font("sansserif",0,12));
        label5.setText("Citizens");
        label5.setVisible(true);

        label6 = new JLabel();
        label6.setBounds(250,90,90,35);
        label6.setBackground(new Color(214,217,223));
        label6.setForeground(new Color(0,0,0));
        label6.setEnabled(true);
        label6.setFont(new Font("sansserif",0,12));
        label6.setText("Patricians");
        label6.setVisible(true);

        label7 = new JLabel();
        label7.setBounds(370,90,90,35);
        label7.setBackground(new Color(214,217,223));
        label7.setForeground(new Color(0,0,0));
        label7.setEnabled(true);
        label7.setFont(new Font("sansserif",0,12));
        label7.setText("Noblemen");
        label7.setVisible(true);

        label8 = new JLabel();
        label8.setBounds(10,350,480,35);
        label8.setBackground(new Color(214,217,223));
        label8.setForeground(new Color(0,0,0));
        label8.setEnabled(true);
        label8.setFont(new Font("sansserif",0,12));
        label8.setText("Result:");
        label8.setVisible(true);

        label9 = new JLabel();
        label9.setBounds(10,260,280,35);
        label9.setBackground(new Color(214,217,223));
        label9.setForeground(new Color(0,0,0));
        label9.setEnabled(true);
        label9.setFont(new Font("sansserif",0,12));
        label9.setText("Enter number for consumption good");
        label9.setVisible(true);

        label10 = new JLabel();
        label10.setBounds(10,225,500,35);
        label10.setBackground(new Color(214,217,223));
        label10.setForeground(new Color(0,0,0));
        label10.setEnabled(true);
        label10.setFont(new Font("sansserif",0,12));
        label10.setText("9 Meat; 10 Fur Coats; 11 Wine; 12 Glasses; 13 Candlestick; 14 Brocade Robes");
        label10.setVisible(true);

        label11 = new JLabel();
        label11.setBounds(10,180,480,35);
        label11.setBackground(new Color(214,217,223));
        label11.setForeground(new Color(0,0,0));
        label11.setEnabled(true);
        label11.setFont(new Font("sansserif",0,12));
        label11.setText("Numbers for the consumptions goods:");
        label11.setVisible(true);
        
        //
        //Orient Labels
        //

        label12 = new JLabel();
        label12.setBounds(600,90,90,35);
        label12.setBackground(new Color(214,217,223));
        label12.setForeground(new Color(0,0,0));
        label12.setEnabled(true);
        label12.setFont(new Font("sansserif",0,12));
        label12.setText("Nomads");
        label12.setVisible(true);

        label13 = new JLabel();
        label13.setBounds(720,90,90,35);
        label13.setBackground(new Color(214,217,223));
        label13.setForeground(new Color(0,0,0));
        label13.setEnabled(true);
        label13.setFont(new Font("sansserif",0,12));
        label13.setText("Envoy");
        label13.setVisible(true);

        label14 = new JLabel();
        label14.setBounds(600,210,360,35);
        label14.setBackground(new Color(214,217,223));
        label14.setForeground(new Color(0,0,0));
        label14.setEnabled(true);
        label14.setFont(new Font("sansserif",0,12));
        label14.setText("1 Dates; 2 Goat Milk; 3 Carpet; 4 Coffee; 5 Pearl Necklace;");
        label14.setVisible(true);

        label15 = new JLabel();
        label15.setBounds(600,225,360,35);
        label15.setBackground(new Color(214,217,223));
        label15.setForeground(new Color(0,0,0));
        label15.setEnabled(true);
        label15.setFont(new Font("sansserif",0,12));
        label15.setText("6 Perfume; 7 Marzipan");
        label15.setVisible(true);

        label16 = new JLabel();
        label16.setBounds(600,180,400,35);
        label16.setBackground(new Color(214,217,223));
        label16.setForeground(new Color(0,0,0));
        label16.setEnabled(true);
        label16.setFont(new Font("sansserif",0,12));
        label16.setText("Numbers for the consumptions goods:");
        label16.setVisible(true);

        label17 = new JLabel();
        label17.setBounds(600,260,400,35);
        label17.setBackground(new Color(214,217,223));
        label17.setForeground(new Color(0,0,0));
        label17.setEnabled(true);
        label17.setFont(new Font("sansserif",0,12));
        label17.setText("Enter number for consumption good");
        label17.setVisible(true);

        label18 = new JLabel();
        label18.setBounds(600,350,490,35);
        label18.setBackground(new Color(214,217,223));
        label18.setForeground(new Color(0,0,0));
        label18.setEnabled(true);
        label18.setFont(new Font("sansserif",0,12));
        label18.setText("Result:");
        label18.setVisible(true);

        textfield1 = new JTextField();
        textfield1.setBounds(10,130,90,35);
        textfield1.setBackground(new Color(255,255,255));
        textfield1.setForeground(new Color(0,0,0));
        textfield1.setEnabled(true);
        textfield1.setFont(new Font("sansserif",0,12));
        textfield1.setVisible(true);

        textfield2 = new JTextField();
        textfield2.setBounds(130,130,90,35);
        textfield2.setBackground(new Color(255,255,255));
        textfield2.setForeground(new Color(0,0,0));
        textfield2.setEnabled(true);
        textfield2.setFont(new Font("sansserif",0,12));
        textfield2.setText("");
        textfield2.setVisible(true);

        textfield3 = new JTextField();
        textfield3.setBounds(250,130,90,35);
        textfield3.setBackground(new Color(255,255,255));
        textfield3.setForeground(new Color(0,0,0));
        textfield3.setEnabled(true);
        textfield3.setFont(new Font("sansserif",0,12));
        textfield3.setText("");
        textfield3.setVisible(true);

        textfield4 = new JTextField();
        textfield4.setBounds(370,130,90,35);
        textfield4.setBackground(new Color(255,255,255));
        textfield4.setForeground(new Color(0,0,0));
        textfield4.setEnabled(true);
        textfield4.setFont(new Font("sansserif",0,12));
        textfield4.setText("");
        textfield4.setVisible(true);

        textfield5 = new JTextField();
        textfield5.setBounds(10,300,90,35);
        textfield5.setBackground(new Color(255,255,255));
        textfield5.setForeground(new Color(0,0,0));
        textfield5.setEnabled(true);
        textfield5.setFont(new Font("sansserif",0,12));
        textfield5.setText("");
        textfield5.setVisible(true);

        textfield6 = new JTextField();
        textfield6.setBounds(600,130,90,35);
        textfield6.setBackground(new Color(255,255,255));
        textfield6.setForeground(new Color(0,0,0));
        textfield6.setEnabled(true);
        textfield6.setFont(new Font("sansserif",0,12));
        textfield6.setText("");
        textfield6.setVisible(true);

        textfield7 = new JTextField();
        textfield7.setBounds(720,130,90,35);
        textfield7.setBackground(new Color(255,255,255));
        textfield7.setForeground(new Color(0,0,0));
        textfield7.setEnabled(true);
        textfield7.setFont(new Font("sansserif",0,12));
        textfield7.setText("");
        textfield7.setVisible(true);

        textfield8 = new JTextField();
        textfield8.setBounds(600,300,90,35);
        textfield8.setBackground(new Color(255,255,255));
        textfield8.setForeground(new Color(0,0,0));
        textfield8.setEnabled(true);
        textfield8.setFont(new Font("sansserif",0,12));
        textfield8.setText("");
        textfield8.setVisible(true);

        //adding components to contentPane panel
        contentPane.add(button1);
        contentPane.add(button2);
        contentPane.add(label1);
        contentPane.add(label2);
        contentPane.add(label3);
        contentPane.add(label4);
        contentPane.add(label5);
        contentPane.add(label6);
        contentPane.add(label7);
        contentPane.add(label8);
        contentPane.add(label9);
        contentPane.add(label10);
        contentPane.add(label11);
        contentPane.add(label12);
        contentPane.add(label13);
        contentPane.add(label14);
        contentPane.add(label15);
        contentPane.add(label16);
        contentPane.add(label17);
        contentPane.add(label18);
        contentPane.add(textfield1);
        contentPane.add(textfield2);
        contentPane.add(textfield3);
        contentPane.add(textfield4);
        contentPane.add(textfield5);
        contentPane.add(textfield6);
        contentPane.add(textfield7);
        contentPane.add(textfield8);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    private void Calculate (MouseEvent evt)
    {
        int Pe = 0;
        int Ci = 0;
        int Pa = 0;
        int No = 0;
        //get number of people per social class
        Pe = ConvertInput(textfield1.getText());
        Ci = ConvertInput(textfield2.getText());
        Pa = ConvertInput(textfield3.getText());
        No = ConvertInput(textfield4.getText());

        int ConNum = -1;
        if(!textfield5.getText().equals(""))
            ConNum = ConvertInput(textfield5.getText());

        if(Pe == -1 || Pe == -1 || Pe == -1 || Pe == -1 || ConNum == -1 ||
        ConNum == 0 || ConNum > 14)
        {
            label8.setText("Result: problem with input");
            return;
        }

        Calculator c = new Calculator();

        double consumption = c.CalculateConsumption(Pe, Ci, Pa, No, ConNum);

        consumption = consumption * 1000;
        consumption = Math.round(consumption);
        consumption = consumption / 1000;

        String ConGoodName = DetConName(ConNum);

        int NrBuildings = c.CalculateNrBuildingsNeccessary(consumption, ConNum);

        String tmp = "Number of " + DetBuildingsName(ConNum) + " required: " + NrBuildings;

        label8.setText("Result: " + consumption + " t " + ConGoodName + " /min. " + tmp);
    }

    private void CalculateOrient (MouseEvent evt)
    {
        int No = 0;
        int En = 0;
        //get number of people per social class
        No = ConvertInput(textfield6.getText());
        En = ConvertInput(textfield7.getText());
        
        int ConNum = -1;
        if(!textfield8.getText().equals(""))
            ConNum = ConvertInput(textfield8.getText());
            
        if(No == -1 || En == -1 || ConNum == -1 || ConNum == 0 || ConNum > 7)
        {
            label18.setText("Result: problem with input");
            return;
        }
        
        CalculatorOrient c = new CalculatorOrient();
        
        double consumption = c.CalculateConsumption(No, En, ConNum);
        
        consumption = consumption * 1000;
        consumption = Math.round(consumption);
        consumption = consumption / 1000;

        String ConGoodName = DetConNameOrient(ConNum);

        int NrBuildings = c.CalculateNrBuildingsNeccessary(consumption, ConNum);

        String tmp = "Number of " + DetBuildingsNameOrient(ConNum) + " required: " + NrBuildings;

        label18.setText("Result: " + consumption + " t " + ConGoodName + " /min. " + tmp);
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

    private String DetConName(int ConNum)
    {
        if(ConNum==1) return "Fish";
        if(ConNum==2) return "Cider";
        if(ConNum==3) return "Spices";
        if(ConNum==4) return "Linen Garments";
        if(ConNum==5) return "Bread";
        if(ConNum==6) return "Beer";
        if(ConNum==7) return "Leather Jerkins";
        if(ConNum==8) return "Books";
        if(ConNum==9) return "Meat";
        if(ConNum==10) return "Fur Coats";
        if(ConNum==11) return "Wine";
        if(ConNum==12) return "Glasses";
        if(ConNum==13) return "Candlesticks";
        if(ConNum==14) return "Brocade Robes";
        return "";
    }

    private String DetBuildingsName(int ConNum)
    {
        if(ConNum==1) return "Fisherman's hut";
        if(ConNum==2) return "Cider farm";
        if(ConNum==3) return "Spice farm";
        if(ConNum==4) return "Weaver's hut";
        if(ConNum==5) return "Bakery";
        if(ConNum==6) return "Monastery brewery";
        if(ConNum==7) return "Tannery";
        if(ConNum==8) return "Printing house";
        if(ConNum==9) return "Butcher's shop";
        if(ConNum==10) return "Furrier's workshop";
        if(ConNum==11) return "Wine press";
        if(ConNum==12) return "Optician's workshop";
        if(ConNum==13) return "Redsmith's Workshop";
        if(ConNum==14) return "Silk weaving mill";
        return "";
    }
    
    private String DetConNameOrient(int ConNum)
    {
        if(ConNum==1) return "Date";
        if(ConNum==2) return "Goat Milk";
        if(ConNum==3) return "Carpet";
        if(ConNum==4) return "Coffee";
        if(ConNum==5) return "Pearl Necklace";
        if(ConNum==6) return "Perfume";
        if(ConNum==7) return "Marzipan";
        return "";
    }
    
    private String DetBuildingsNameOrient(int ConNum)
    {
        if(ConNum==1) return "Date plantation";
        if(ConNum==2) return "Goat farm";
        if(ConNum==3) return "Carpet workshop";
        if(ConNum==4) return "Roasting house";
        if(ConNum==5) return "Pearl workshop";
        if(ConNum==6) return "Perfumery";
        if(ConNum==7) return "Confectioner's workshop";
        return "";
    }
}