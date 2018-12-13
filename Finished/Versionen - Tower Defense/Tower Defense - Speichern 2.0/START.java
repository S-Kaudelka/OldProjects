import java.awt.Point;

public class START implements Runnable
{
    public CONTROLER c;

    private WIKI w;

    public String Spielername;

    public int StageMax;

    private BILD[] Level_Nummern;
    private BILD Hintergrund;
    private BILD Wiki;

    public boolean Running;
    public boolean StageCleared;

    public START(String name)
    {
        Spielername = name;
        Laden();

        Running = true;

        Anzeigen(false);

        System.out.print('\u000C');

        new Thread(this).start();
    }

    public void Anzeigen(boolean SClear) //Stage cleared ?
    {
        StageCleared = SClear;
        if(StageCleared)
        {
            AusgabeLoeschen();
            System.out.println("Stage cleared");
        }

        DE_SERIALISIERUNG.SpielerUeberpruefen();

        Hintergrund = new BILD("Hintergrund.PNG");
        Hintergrund.SetzeBreite(1500);
        Hintergrund.SetzeHoehe(800);

        Wiki = new BILD("bWiki.PNG");
        Wiki.SetzeX(1275);
        Wiki.SetzeY(100);

        Level_Nummern = new BILD[SPIEL.AnzLevel];

        for(int i = 0; i<Level_Nummern.length; i++)
        {
            String c = "";
            if(i>StageMax){c = "na";}
            Level_Nummern[i] = new BILD((i+1) + c + ".PNG");
            int a = i;
            int b = 0;
            while(a>=10){a = a -10; b++;}
            Level_Nummern[i].SetzeY(50+125*b);
            Level_Nummern[i].SetzeX(50+125*a);
        }
    }

    public void run()
    {
        KEYSTATE k = GAMEWINDOW.getInstance().getKeystate();
        while(Running)
        {
            Point p = k.PickLastMouseClickPosition();

            if(k.IsPressed("N")) //Laden
            {
                c = new CONTROLER(this);
                if(c.Laden())
                {
                    new Thread(c).start();
                    stop();
                    return;
                }
                c = null;
            }
            if(Wiki.PunktBeruehrt(p))
            {
                w = new WIKI();
            }
            for(int i = 0; i < Level_Nummern.length; i++)
            {
                if(Level_Nummern[i] != null)
                {
                    if(Level_Nummern[i].PunktBeruehrt(p))
                    {
                        if(Level_Nummern[i].LeseName().contains("na.")){break;}
                        c = new CONTROLER(i, this);
                        stop();
                        return ;
                    }
                }
            }

            if(StageCleared)
            {
                AusgabeLoeschen();
                System.out.println("Stage cleared");
            }
        }
    }

    public void stop()
    {
        Running = false;
    }

    public void Speichern()
    {
        DE_SERIALISIERUNG.StartSpeichern(StageMax, Spielername);
    }

    public void Laden()
    {
        StageMax = DE_SERIALISIERUNG.StartLaden(Spielername);
        if(StageMax < 0)
        {
            StageMax = 0;
        }
    }

    public void AusgabeLoeschen()
    {
        System.out.print('\u000C');
    }
}