import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.*;
import java.awt.event.*;

public class GAMEWINDOW extends Canvas implements Runnable, KeyListener, MouseListener, MouseMotionListener
{
    public static int Breite = 400;
    public static int Hoehe = 500;
    
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
            g.fillRect(0,0,Breite,Hoehe);

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


