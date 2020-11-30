import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.*;
import java.awt.event.*;

import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.border.Border;

public class GAMEWINDOW extends Canvas implements Runnable, KeyListener, MouseListener, MouseMotionListener
{
    public static final int Breite = 396;
    public static final int Hoehe = 466;
    
    public SPIEL s;

    private JButton button1;
    private JButton button2;

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;

    private BufferStrategy strategy;
    private long lastLoopTime;
    private boolean gameRunning=true;
    private static GAMEWINDOW instance;
    private String Taste="";

    private Vector<BILD> sprites=new Vector<BILD>();

    KEYSTATE ks = new KEYSTATE();

    public static GAMEWINDOW getInstance()
    {
        if(instance==null)
        {
            instance = new GAMEWINDOW();
        }

        return instance;
    }

    private GAMEWINDOW()
    {
        // create a frame to contain our game

        JFrame container = new JFrame("Spielfeld");
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // get hold the content of the frame and set up the 
        // resolution of the game
        JPanel panel = (JPanel) container.getContentPane();
        panel.setPreferredSize(new Dimension(Breite,Hoehe));
        panel.setLayout(null);

        button1 = new JButton();
        button1.setBounds(0,400,80,30);
        button1.setBackground(new Color(214,217,223));
        button1.setForeground(new Color(0,0,0));
        button1.setEnabled(true);
        button1.setFont(new Font("sansserif",0,12));
        button1.setText("Check");
        button1.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        button1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    Check(evt);
                }
            });
            
        button2 = new JButton();
        button2.setBounds(316,400,80,30);
        button2.setBackground(new Color(214,217,223));
        button2.setForeground(new Color(0,0,0));
        button2.setEnabled(true);
        button2.setFont(new Font("sansserif",0,12));
        button2.setText("New");
        button2.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        button2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    New(evt);
                }
            });

        label1 = new JLabel();
        label1.setBounds(90,400,210,35);
        label1.setBackground(new Color(214,217,223));
        label1.setForeground(new Color(0,0,0));
        label1.setEnabled(true);
        label1.setFont(new Font("sansserif",0,12));
        label1.setText("Press the button to check the sudoku.");
        label1.setVisible(true);

        label2 = new JLabel();
        label2.setBounds(10,435,396,14);
        label2.setBackground(new Color(214,217,223));
        label2.setForeground(new Color(0,0,0));
        label2.setEnabled(true);
        label2.setFont(new Font("sansserif",0,12));
        label2.setText("Select a field and press the number you want to insert.");
        label2.setVisible(true);
        
        label3 = new JLabel();
        label3.setBounds(10,449,396,14);
        label3.setBackground(new Color(214,217,223));
        label3.setForeground(new Color(0,0,0));
        label3.setEnabled(true);
        label3.setFont(new Font("sansserif",0,12));
        label3.setText("0 deletes the number in the selected field.");
        label3.setVisible(true);

        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(button1);
        panel.add(button2);

        // setup our canvas size and put it into the content of the frame
        setBounds(0,0,Breite,Hoehe);
        panel.add(this);

        // Tell AWT not to bother repainting our canvas since we're
        // going to do that our self in accelerated mode
        setIgnoreRepaint(true);

        // finally make the window visible 
        container.pack();
        container.setResizable(false);
        container.setVisible(true);

        // create the buffering strategy which will allow AWT
        // to manage our accelerated graphics
        createBufferStrategy(2);
        strategy = getBufferStrategy();

        new Thread(this).start();
        //addHierarchyListener(this);
        addKeyListener(this);

        addMouseMotionListener(this);

        addMouseListener(this);
    }

    public void run()
    {
        while (gameRunning) {
            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should
            // move this loop
            long delta = System.currentTimeMillis() - lastLoopTime;
            lastLoopTime = System.currentTimeMillis();

            // Get hold of a graphics context for the accelerated 
            // surface and blank it out
            Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
            g.setColor(Color.white);
            g.fillRect(0,0,Breite,396);

            synchronized(sprites)
            {
                Iterator<BILD> iterator = sprites.iterator();
                while(iterator.hasNext())
                {
                    BILD sprite = iterator.next();
                    if(sprite.LeseSichtbar())
                    {
                        sprite.Draw(g);
                    }
                }
            }

            // finally, we've completed drawing so clear up the graphics
            // and flip the buffer over
            g.dispose();
            strategy.show();

            // finally pause for a bit. Note: this should run us at about
            // 100 fps but on windows this might vary each loop due to
            // a bad implementation of timer
            try { Thread.sleep(10); } catch (Exception e) {}
        }
    }

    //Method mouseClicked for button1
    private void Check (MouseEvent evt) {
        boolean b = s.Test();
        if(!b)
        {
            label1.setText("Mistakes have been made.");
        }
        else
        {
            label1.setText("Correct solution :) .");
        }
    }
    
    //Method mouseClicked for button2
    private void New (MouseEvent evt) {
        s.New();
    }

    public KEYSTATE getKeystate()
    {
        return ks;
    }

    public void AddSprite(BILD bild)
    {
        synchronized(sprites)
        {
            sprites.add(bild);
        }
    }

    public void stopRunning()
    {
        gameRunning=false;
        try { Thread.sleep(1000); } catch (Exception e) {}
        synchronized(sprites)
        {
            sprites.clear();
        }
        instance=null;
    }

    public void hierarchyChanged(HierarchyEvent e) 
    {
        gameRunning=false;  

    }

    public void keyPressed(KeyEvent e) 
    {
        ks.add(e);
    }

    public void keyReleased(KeyEvent e) 
    {
        ks.remove(e);
    }

    public void keyTyped(KeyEvent e) 
    {

    }

    public String LeseTaste()
    {
        return Taste;
    }

    public void mouseClicked(MouseEvent e) 
    {
        ks.setNewMouseClick(e.getPoint()); 
    }

    public void mouseExited(MouseEvent e) 
    {

    }

    public void mousePressed(MouseEvent e) 
    {

    }

    public void mouseEntered(MouseEvent e) 
    {

    }

    public void mouseReleased(MouseEvent e) 
    {
        ks.setNewMouseClick(e.getPoint()); 
    }

    public void mouseMoved(MouseEvent e) 
    {
        ks.SetMousePosition(e.getPoint());  
    }

    public void mouseDragged(MouseEvent e) 
    {

    }
}

