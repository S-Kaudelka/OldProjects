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
    private JButton bNew;
    private JLabel label1;
    private JLabel lErgebnis;
    
    private SPIEL s;
    private String RichtigerBuchstabe;
    private boolean Beantwortet;

    //Constructor 
    public GUI(){

        s = new SPIEL(this);
        
        this.setTitle("GUI");
        this.setSize(500,150);
        //menu generate method
        generateMenu();
        this.setJMenuBar(menuBar);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500,200));
        contentPane.setBackground(new Color(192,192,192));

        button1 = new JButton();
        button1.setBounds(10,50,230,35);
        button1.setBackground(new Color(214,217,223));
        button1.setForeground(new Color(0,0,0));
        button1.setEnabled(true);
        button1.setFont(new Font("sansserif",0,12));
        button1.setText("Antwort1");
        button1.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        button1.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent evt){
                    B1(evt);
                }
            });

        button2 = new JButton();
        button2.setBounds(260,50,230,35);
        button2.setBackground(new Color(214,217,223));
        button2.setForeground(new Color(0,0,0));
        button2.setEnabled(true);
        button2.setFont(new Font("sansserif",0,12));
        button2.setText("Antwort2");
        button2.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        button2.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent evt){
                    B2(evt);
                }
            });

        button3 = new JButton();
        button3.setBounds(10,100,230,35);
        button3.setBackground(new Color(214,217,223));
        button3.setForeground(new Color(0,0,0));
        button3.setEnabled(true);
        button3.setFont(new Font("sansserif",0,12));
        button3.setText("Antwort3");
        button3.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        button3.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent evt){
                    B3(evt);
                }
            });

        button4 = new JButton();
        button4.setBounds(260,100,230,35);
        button4.setBackground(new Color(214,217,223));
        button4.setForeground(new Color(0,0,0));
        button4.setEnabled(true);
        button4.setFont(new Font("sansserif",0,12));
        button4.setText("Antwort4");
        button4.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        button4.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent evt){
                    B4(evt);
                }
            });

        bNew = new JButton();
        bNew.setBounds(350,150,100,35);
        bNew.setBackground(new Color(214,217,223));
        bNew.setForeground(new Color(0,0,0));
        bNew.setEnabled(true);
        bNew.setFont(new Font("sansserif",0,12));
        bNew.setText("Neue Frage");
        bNew.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        bNew.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent evt){
                    New(evt);
                }
            });

        lErgebnis = new JLabel();
        lErgebnis.setBounds(25,150,250,35);
        lErgebnis.setBackground(new Color(214,217,223));
        lErgebnis.setForeground(new Color(0,0,0));
        lErgebnis.setEnabled(true);
        lErgebnis.setFont(new Font("sansserif",0,12));
        lErgebnis.setText("");
        lErgebnis.setVisible(true);
            
        label1 = new JLabel();
        label1.setBounds(25,5,475,35);
        label1.setBackground(new Color(214,217,223));
        label1.setForeground(new Color(0,0,0));
        label1.setEnabled(true);
        label1.setFont(new Font("sansserif",0,12));
        label1.setText("Fragestellung");
        label1.setVisible(true);

        //adding components to contentPane panel
        contentPane.add(button1);
        contentPane.add(button2);
        contentPane.add(button3);
        contentPane.add(button4);
        contentPane.add(bNew);
        contentPane.add(lErgebnis);
        contentPane.add(label1);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        
        Neu();
    }

    //Method mouseRelesed for button1
    private void B1 (MouseEvent evt) {
        Antwort(button1.getText());
    }

    //Method mouseRelesed for button2
    private void B2 (MouseEvent evt) {
        Antwort(button2.getText());
    }

    //Method mouseRelesed for button3
    private void B3 (MouseEvent evt) {
        Antwort(button3.getText());
    }

    //Method mouseRelesed for button4
    private void B4 (MouseEvent evt) {
        Antwort(button4.getText());
    }
    
    //Method mouseRelesed for bNew
    private void New (MouseEvent evt) {
        Neu();
    }
    
    public void Ausgabe(String Text)
    {
        if(Text == null)
        {
            Text = "";
        }
        lErgebnis.setText(Text);
    }
    
    public String Ausgabewert()
    {
        String a = lErgebnis.getText();
        return a;
    }
    
    private void Neu()
    {
        s.Neu();
        if(Ausgabewert().equals("Alle Fragen beantwortet."))
        {
            Ausgabe("Alle Fragen beantwortet. Richtig:"+s.richtig+"/"+s.f.AnzFragen);
            return;
        }
        int a = (int)(Math.random()*4+1);
        int b = (int)(Math.random()*4+1);
        int c = (int)(Math.random()*4+1);
        int d = (int)(Math.random()*4+1);
        while(b == a)
        {
            b = (int)(Math.random()*4+1);
        }
        while(c == a || c == b)
        {
            c = (int)(Math.random()*4+1);
        }
        while(d == a || d == b || d == c)
        {
            d = (int)(Math.random()*4+1);
        }
        if(a == 1){RichtigerBuchstabe = "A";}
        if(b == 1){RichtigerBuchstabe = "B";}
        if(c == 1){RichtigerBuchstabe = "C";}
        if(d == 1){RichtigerBuchstabe = "D";}
        
        button1.setText("A:"+s.f.Antworten[a-1]);
        button2.setText("B:"+s.f.Antworten[b-1]);
        button3.setText("C:"+s.f.Antworten[c-1]);
        button4.setText("D:"+s.f.Antworten[d-1]);
        
        lErgebnis.setText("");
        
        label1.setText(s.f.Fragestellung);
        
        Beantwortet = false;
    }

    public void Antwort(String Antwort)
    {
        if(Beantwortet)
        {
            return;
        }
        Beantwortet = true;
        if((Antwort.charAt(0)+"").equals(RichtigerBuchstabe))
        {
            lErgebnis.setText("Richtige Antwort");
            s.richtig++;
        }
        else
        {
            lErgebnis.setText("Falsche Antwort. Richtig ist " + RichtigerBuchstabe);
        }
    }

    //method for generate menu
    public void generateMenu(){/*
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
        menuBar.add(help);*/
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