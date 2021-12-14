import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.*;
import java.awt.event.*;

public class GAMEWINDOW extends Canvas implements Runnable, KeyListener, MouseListener, MouseMotionListener
{
    public static int Wide;
    public static int High;
    
    public static boolean Reveal_Field = true;
    
    public static boolean new_Spiel = false;
    
    public JFrame container;

    private BufferStrategy strategy;
    private long lastLoopTime;
    private boolean gameRunning=true;
    private static GAMEWINDOW instance;
    private String Taste="";

    private JButton button_Mode;
    private JButton button_new;
    private JLabel label_Marked;
    private JLabel label_Mines;
    private JTextField textfield_H;
    private JTextField textfield_W;

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

        container = new JFrame("Spielfeld");
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // get hold the content of the frame and set up the 
        // resolution of the game
        JPanel panel = (JPanel) container.getContentPane();
        panel.setPreferredSize(new Dimension(Wide,High));
        panel.setLayout(null);

        button_Mode = new JButton();
        button_Mode.setBounds(185,5,60,25);
        button_Mode.setBackground(new Color(214,217,223));
        button_Mode.setForeground(new Color(0,0,0));
        button_Mode.setEnabled(true);
        button_Mode.setFont(new Font("sansserif",0,12));
        button_Mode.setText("Flip");
        button_Mode.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        button_Mode.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent evt){
                    Change_Mode(evt);
                }
            });

        button_new = new JButton();
        button_new.setBounds(110,5,65,25);
        button_new.setBackground(new Color(214,217,223));
        button_new.setForeground(new Color(0,0,0));
        button_new.setEnabled(true);
        button_new.setFont(new Font("sansserif",0,12));
        button_new.setText("NEW");
        button_new.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        button_new.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent evt){
                    New_Field(evt);
                }
            });

        label_Marked = new JLabel();
        label_Marked.setBounds(125,30,115,25);
        label_Marked.setBackground(new Color(214,217,223));
        label_Marked.setForeground(new Color(0,0,0));
        label_Marked.setEnabled(true);
        label_Marked.setFont(new Font("sansserif",0,12));
        label_Marked.setText("Tiles Marked:");
        label_Marked.setVisible(true);

        label_Mines = new JLabel();
        label_Mines.setBounds(10,30,110,25);
        label_Mines.setBackground(new Color(214,217,223));
        label_Mines.setForeground(new Color(0,0,0));
        label_Mines.setEnabled(true);
        label_Mines.setFont(new Font("sansserif",0,12));
        label_Mines.setText("Mines Placed:");
        label_Mines.setVisible(true);

        textfield_H = new JTextField();
        textfield_H.setBounds(5,5,50,25);
        textfield_H.setBackground(new Color(255,255,255));
        textfield_H.setForeground(new Color(0,0,0));
        textfield_H.setEnabled(true);
        textfield_H.setFont(new Font("SansSerif",0,12));
        textfield_H.setText("Height");
        textfield_H.setVisible(true);

        textfield_W = new JTextField();
        textfield_W.setBounds(60,5,50,25);
        textfield_W.setBackground(new Color(255,255,255));
        textfield_W.setForeground(new Color(0,0,0));
        textfield_W.setEnabled(true);
        textfield_W.setFont(new Font("sansserif",0,12));
        textfield_W.setText("Width");
        textfield_W.setVisible(true);

        //adding components to contentPane panel
        panel.add(button_Mode);
        panel.add(button_new);
        panel.add(label_Marked);
        panel.add(label_Mines);
        panel.add(textfield_H);
        panel.add(textfield_W);

        // setup our canvas size and put it into the content of the frame
        setBounds(0,0,Wide,High);
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

    //Method mouseRelesed for button_Mode
    private void Change_Mode (MouseEvent evt) {
        if(Reveal_Field)    //-> changing from flipping to marking
        {
            button_Mode.setText("Mark");
            Reveal_Field = false;
        }
        else                //-> changing from marking to flipping
        {
            button_Mode.setText("Flip");
            Reveal_Field = true;
        }
    }

    //Method mouseRelesed for button_new
    private void New_Field (MouseEvent evt) {
        String W = textfield_W.getText();
        String H = textfield_H.getText();
        
        int Wide_tmp = ConvertInput(W);
        int High_tmp = ConvertInput(H);
        
        if(Wide_tmp == -1 || High_tmp == -1)
        {
            textfield_H.setText("Error");
            textfield_W.setText("Width");
            return;
        }
        
        if(Wide_tmp < 9 || Wide_tmp > 40 || High_tmp < 9 || High_tmp > 40)
        {
            textfield_H.setText("Size");
            textfield_W.setText("Width");
            return;
        }
        
        Wide = Wide_tmp*25;
        High = High_tmp*25+55;
        
        stopRunning();
        new_Spiel = true;
        container.dispose();
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
            g.fillRect(0,0,Wide,High);

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
    
    public void setNumberMines(int number)
    {
        label_Mines.setText("Mines Placed: " + number);
    }
    
    public void setNumberMarked(int number)
    {
        label_Marked.setText("Tiles Marked: " + number);
    }
}

