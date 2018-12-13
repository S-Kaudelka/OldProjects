import java.awt.Point;

public class CONTROLER implements Runnable
{
    public SPIEL spiel = new SPIEL(this);
    
    private boolean IsRunning=true;
    
    public boolean AmLaufen=true;
    
    public double SpeedFaktor;
    
    //    ZU ERLEDIGEN:
    //
    //Turmspezialisierungen -> Werte -> ueberpruefen ob Passen
    //'-> unendlich verbessern
    //
    //mehr wellen, wenn sonst nichts ist
    //
    // Speichern und Laden in der GUI + Spiel-Klasse -> momentan m/n
    
    public CONTROLER()
    {
        SpeedFaktor = 1;
        new Thread(this).start();
    }
    
    public void run()
    {
        while(IsRunning)
        {
            if(AmLaufen==true)
            {
                spiel.RUN(GAMEWINDOW.getInstance().getKeystate());
                if(spiel.GegnerAmLeben > 0)
                {
                    for(int i = 0; i<spiel.Wellengegner; i++)
                    {
                        spiel.Gegner[i].Bewegen();
                    }
                }
            }
            else
            {
                if(Weiter(GAMEWINDOW.getInstance().getKeystate().PickLastMouseClickPosition()))
                {
                    AmLaufen=true;
                    spiel.SPB();
                }
            }
            try { Thread.sleep((int) (20/SpeedFaktor)); } catch (Exception e) {}
        }
    }
    
    public void end()
    {
        IsRunning=false;
    }
    
    public boolean Weiter(Point p)
    {
        if(p != null)
        {   
            if(p.x >= 0 && p.x <= 1500 && p.y>=0 && p.y<=800)
            {
                if(p.x >= 340 && p.x <= 464 && p.y > 759)
                {
                    return false;
                }
                return true;
            }
        }
        return false;
    }
    
    public void Speichern()
    {
        DE_SERIALISIERUNG.Speichern(this);
    }
    
    public void Laden()
    {
        DE_SERIALISIERUNG.Laden(this);
    }
}