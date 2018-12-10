import java.awt.Point;

public class SPIEL
{
    private CONTROLER c;
    
    public static final int Breite = 450;
    public static final int Hoehe = 600;
    
    private boolean IstVeraendert;
    
    private SPIELER s;
    private PLATFORM[] p;
    
    public SPIEL(CONTROLER c_p)
    {
        c = c_p;
        s = new SPIELER();
        p = new PLATFORM[20];
        for(int i = 0; i<19; i++)
        {
            p[i] = new PLATFORM();
        }
        p[19] = new PLATFORM();
        p[19].Platform.SetzeX(((SPIEL.Breite-p[19].Platform.LeseBreite())/2));
        p[19].Platform.SetzeY(SPIEL.Hoehe-p[19].Platform.LeseHoehe());
    }

    public void RUN(KEYSTATE keystate)
    {
        TastenPruefen(keystate);
        
        Point p = keystate.PickLastMouseClickPosition();
        if(p != null)
        {
            MausklickUeberpruefen(p);
        }
        
        Kollisionsabfrage();
        Springen();
        
        if(IstVeraendert==true)
        {
            Ausgabe();
        }
    }

    //
    //
    // Teile der RUN-Methode
    //
    //

    public void TastenPruefen(KEYSTATE keystate)
    {
        if(keystate.IsPressed("B")) //Bsp
        {
            
        }
    }

    public void MausklickUeberpruefen(Point m)
    {
        if(m.x >= 560 && m.x <= 601 && m.y>=759 && m.y<=800) //Bsp
        {
            
        }
    }
    
    public void Kollisionsabfrage()
    {
        if(s.Faellt)
        {
            for(int i = 0; i<20; i++)
            {
                if(p[i].Platform.LeseX()<s.Doodle.LeseX()+s.Doodle.LeseBreite() &&
                   s.Doodle.LeseX() < p[i].Platform.LeseX()+p[i].Platform.LeseBreite() &&
                   p[i].Platform.LeseY()-(s.Doodle.LeseY()+s.Doodle.LeseHoehe())<0 &&
                   p[i].Platform.LeseY()-(s.Doodle.LeseY()+s.Doodle.LeseHoehe())>=-5)
                {
                    s.SprungPositionY = s.Doodle.LeseY();
                    s.Faellt = false;
                }
            }
        }
    }
    
    public void Springen()
    {
        if(s.Faellt) //er faellt
        {
            if(s.Doodle.LeseY() >= SPIEL.Hoehe)
            {
                c.stop();
                return;
            }
            
            s.Doodle.SetzeY(s.Doodle.LeseY()+s.FallGeschw);
        }
        else // er springt
        {
            if(s.Doodle.LeseY()+s.Sprunghoehe > s.SprungPositionY)
            {
                s.Doodle.SetzeY(s.Doodle.LeseY()-s.SprungGeschw);
            }
            else
            {
                s.Faellt = true;
            }
        }
    }

    public void Ausgabe()
    {
        IstVeraendert = false;
    }

    //Ende Run

    public void AusgabeLoeschen()
    {
        System.out.print('\u000C');
    }
}