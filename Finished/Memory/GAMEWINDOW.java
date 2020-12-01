import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.*;
import java.awt.event.*;

import javax.swing.border.Border;

public class GAMEWINDOW extends Canvas implements Runnable, KeyListener, MouseListener, MouseMotionListener
{
    public static final int Breite = 230;
    public static final int Hoehe = 280;

    private BufferStrategy strategy;
    private long lastLoopTime;
    private boolean gameRunning=true;
    private static GAMEWINDOW instance;
    private String Taste="";

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;

    private JButton button1;

    private Vector<BILD> sprites=new Vector<BILD>();

    KEYSTATE ks = new KEYSTATE();
    
    public boolean New_Game = false;

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

        label1 = new JLabel();
        label1.setBounds(5,240,90,16);
        label1.setBackground(new Color(214,217,223));
        label1.setForeground(new Color(0,0,0));
        label1.setEnabled(true);
        label1.setFont(new Font("sansserif",0,12));
        label1.setText("Player 1:");
        label1.setVisible(true);

        label2 = new JLabel();
        label2.setBounds(170,240,90,14);
        label2.setBackground(new Color(214,217,223));
        label2.setForeground(new Color(0,0,0));
        label2.setEnabled(true);
        label2.setFont(new Font("sansserif",0,12));
        label2.setText("Player 2:");
        label2.setVisible(true);

        label3 = new JLabel();
        label3.setBounds(10,262,130,18);
        label3.setBackground(new Color(214,217,223));
        label3.setForeground(new Color(0,0,0));
        label3.setEnabled(true);
        label3.setFont(new Font("sansserif",0,14));
        label3.setText("It's Player1's turn.");
        label3.setVisible(true);

        panel.add(label1);
        panel.add(label2);
        panel.add(label3);

        button1 = new JButton();
        button1.setBounds(135,260,90,25);
        button1.setBackground(new Color(214,217,223));
        button1.setForeground(new Color(0,0,0));
        button1.setEnabled(true);
        button1.setFont(new Font("sansserif",0,12));
        button1.setText("New");
        button1.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        button1.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent evt) {
                    New(evt);
                }
            });

        panel.add(button1);

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
            g.fillRect(0,0,Breite,232);

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
    private void New (MouseEvent evt)
    {
        New_Game = true;
    }

    public void UpdateScore(int P1, int P2)
    {
        label1.setText("Player 1: " + P1);
        label2.setText("Player 2: " + P2);
    }

    public void ChangeTurn(boolean P1Turn)
    {
        if(P1Turn)
        {
            label3.setText("It's Player1's turn.");
        }
        else
        {
            label3.setText("It's Player2's turn.");
        }
    }

    public void Victory(boolean P1Victory)
    {
        if(P1Victory)
        {
            label3.setText("Player1 has won!");
        }
        else
        {
            label3.setText("Player2 has won!");
        }
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

