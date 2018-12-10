import java.awt.Point;

public class CONTROLER implements Runnable
{
    public SPIEL spiel;
    
    public START start;
    
    private boolean IsRunning=true;
    
    public boolean AmLaufen=true;
    
    public double SpeedFaktor;
    
    /*    ZU ERLEDIGEN:
    
     LevelAlsZahlInsBildReinschreiben? || Sterne als Level?
    
     Bilder für 10+ bei levelauswahl
     
    //Wiki machen -> Werte von Upgrades einfuegen
    
    //Turmspezialisierungen -> Werte -> ueberpruefen ob Passen
    
    //(mehr wellen)
    
    //mehr level
    */
    public CONTROLER(int Level, START s)
    {
        SpeedFaktor = 1;
        start = s;
        spiel = new SPIEL(this, Level);
        new Thread(this).start();
    }
    
    public CONTROLER(START s)
    {
        SpeedFaktor = 1;
        start = s;
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
    
    public boolean Laden()
    {
        return DE_SERIALISIERUNG.Laden(this);
    }
}