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

public class GUI_AddNames extends JFrame {
    private JButton button1;
    private JButton button2;
    private JLabel label1;
    private JLabel label2;
    private JEditorPane editorpane1;
    private JTextField textfield1;

    private int nPlayers;
    private int currentPlayers;
    
    private String[] Names;

    //Constructor 
    public GUI_AddNames(int nP){

        nPlayers = nP;
        
        Names = new String[nPlayers];

        this.setTitle("GUI_AddNames");
        this.setSize(550,470);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(550,470));
        contentPane.setBackground(new Color(192,192,192));

        button1 = new JButton();
        button1.setBounds(225,130,100,35);
        button1.setBackground(new Color(214,217,223));
        button1.setForeground(new Color(0,0,0));
        button1.setEnabled(true);
        button1.setFont(new Font("sansserif",0,12));
        button1.setText("Add Name");
        button1.setVisible(true);
        button1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    AddPerson(evt);
                }
            });
            
        button2 = new JButton();
        button2.setBounds(225,380,100,35);
        button2.setBackground(new Color(214,217,223));
        button2.setForeground(new Color(0,0,0));
        button2.setEnabled(true);
        button2.setFont(new Font("sansserif",0,12));
        button2.setText("Create List");
        button2.setVisible(true);
        button2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    CreateList(evt);
                }
            });

        label1 = new JLabel();
        label1.setBounds(10,10,530,35);
        label1.setBackground(new Color(214,217,223));
        label1.setForeground(new Color(0,0,0));
        label1.setEnabled(true);
        label1.setFont(new Font("SansSerif",0,12));
        label1.setText("Write the names one after another in the textfield below and press the button after each name.");
        label1.setVisible(true);
        
        label2 = new JLabel();
        label2.setBounds(150,425,250,35);
        label2.setBackground(new Color(214,217,223));
        label2.setForeground(new Color(0,0,0));
        label2.setEnabled(true);
        label2.setFont(new Font("SansSerif",0,12));
        label2.setText("");
        label2.setVisible(true);

        editorpane1 = new JEditorPane();
        editorpane1.setBounds(150,180,250,170);
        editorpane1.setBackground(new Color(214,217,223));
        editorpane1.setForeground(new Color(0,0,0));
        editorpane1.setEnabled(true);
        editorpane1.setEditable(false);
        editorpane1.setFont(new Font("sansserif",0,12));
        editorpane1.setText("");
        editorpane1.setVisible(true);

        JScrollPane editorScrollPane = new JScrollPane(editorpane1);
        editorScrollPane.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editorScrollPane.setHorizontalScrollBarPolicy(
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        editorScrollPane.setBounds(150,180,250,190);

        textfield1 = new JTextField();
        textfield1.setBounds(150,70,250,35);
        textfield1.setBackground(new Color(255,255,255));
        textfield1.setForeground(new Color(0,0,0));
        textfield1.setEnabled(true);
        textfield1.setFont(new Font("sansserif",0,12));
        textfield1.setText("");
        textfield1.setVisible(true);

        //adding components to contentPane panel
        contentPane.add(button1);
        contentPane.add(button2);
        contentPane.add(label1);
        contentPane.add(label2);
        contentPane.add(textfield1);

        //adding panel to JFrame and seting of window position and close operation
        this.add(editorScrollPane);
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    //Method mouseClicked for button1
    private void AddPerson (MouseEvent evt)
    {
        if(currentPlayers < nPlayers && !textfield1.getText().equals(""))
        {
            Names[currentPlayers] = textfield1.getText();
            currentPlayers++;
            editorpane1.setText(editorpane1.getText()+textfield1.getText()+'\n');
        }
        else
        {
            if(currentPlayers == nPlayers)
            {
                label2.setText("Max number of players reached");
            }
            else
            {
                if(textfield1.getText().equals(""))
                {
                    label2.setText("Add a Name");
                }
            }
        }
    }
    
    //Method mouseClicked for button2
    private void CreateList (MouseEvent evt)
    {
        if(currentPlayers < nPlayers)
        {
            label2.setText("List not full");
        }
        else
        {
            new TEST(Names);
            label2.setText("List created");
        }
    }

    public static void main(int args){
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new GUI_AddNames(args);
                }
            });
    }

}