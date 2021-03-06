import java.awt.Image;
import java.awt.image.*;
import java.awt.Graphics;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;

public class BILD
{
    private boolean IstSichtbar;
    private int Breite;
    private int Hoehe;
    private int X=0;
    private int Y=0;
    
    private String DateiName;
    private Image image;
    private AffineTransform tm = new AffineTransform();

    private static Hashtable<String,Image> ImageTable;

    public BILD(String NeuerDateiName)
    {
        if(ImageTable==null)
        {
            ImageTable = new Hashtable<String,Image>();
        }

        DateiName=NeuerDateiName;

        if(ImageTable.containsKey(DateiName))
        {
            image = ImageTable.get(DateiName);
        }
        else
        { 
            URL url = this.getClass().getClassLoader().getResource(DateiName);
            try
            {
                image = ImageIO.read(url);
                ImageTable.put(DateiName,image);
            }
            catch(Exception e)
            {
                System.out.println("Bild " + NeuerDateiName + " konnte nicht geladen werden!");
            }
        }
        GAMEWINDOW w = GAMEWINDOW.getInstance();
        w.AddSprite(this);
        Breite = image.getWidth(null);
        Hoehe = image.getHeight(null);
        IstSichtbar = true;
        UpdateAffine();
    }

    public void Draw(Graphics2D graphics)
    {
        synchronized(this)
        {
            graphics.drawImage(image,tm,null);
        }
    }

    private void UpdateAffine()
    {
        double b = (double)Breite / (double)image.getWidth(null);
        double h = (double)Hoehe / (double)image.getHeight(null);
        tm.setToIdentity();

        tm.translate(X,Y);
        tm.scale(b,h);
    }

    public void SetzeX(int neuX)
    {
        synchronized(this)
        {
            X = neuX;
            UpdateAffine();
        }
    }

    public void SetzeY(int neuY)
    {
        synchronized(this)
        {
            Y = neuY;
            UpdateAffine();
        }
    }

    public void SetzeBreite(int neueBreite)
    {
        synchronized(this)
        {
            Breite = neueBreite;
            UpdateAffine();
        }
    }

    public void SetzeHoehe(int neueHoehe)
    {
        synchronized(this)
        {
            Hoehe = neueHoehe;
            UpdateAffine();
        }
    }

    public int LeseX()
    {
        return X;   
    }
    
    public int LeseBreite()
    {
        return Breite;   
    }
    
    public int LeseHoehe()
    {
        return Hoehe;   
    }

    public int LeseY()
    {
        return Y;   
    }

    public boolean LeseSichtbar()
    {
        return IstSichtbar;
    }

    public void SichtbarSetzen(boolean NeuIstSichtbar)
    {
        IstSichtbar = NeuIstSichtbar;   
    }

    public double Abstand(BILD bild)
    {
        double ax = X + Breite/2.0;
        double ay = Y + Hoehe/2.0;
        double bx = bild.X + bild.Breite/2.0;
        double by = bild.Y + bild.Hoehe/2.0;

        return Math.sqrt( (ax-bx)*(ax-bx) + (ay-by)*(ay-by) );
    }

    public void TransparenzSetzen(int red, int green, int blue)
    {
        final Color color = new Color(red,green,blue);

        ImageFilter filter = new RGBImageFilter() 
            {
                public int markerRGB = color.getRGB() | 0xFF000000;

                public final int filterRGB(int x, int y, int rgb) {
                    if ( ( rgb | 0xFF000000 ) == markerRGB ) {
                        // Mark the alpha bits as zero - transparent
                        return 0x00FFFFFF & rgb;
                    }
                    else {
                        // nothing to do
                        return rgb;
                    }
                }
            }; 

        ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
        image = Toolkit.getDefaultToolkit().createImage(ip);

    }
    
    public String LeseBildname()
    {
        return DateiName;
    }
}
