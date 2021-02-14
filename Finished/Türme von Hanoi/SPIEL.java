import java.awt.Point;

public class SPIEL
{
    private CONTROLER c;

    private boolean IstVeraendert;
    //Ausgabevariablen
    public boolean SLeer;

    public STAB[] Staebe;
    public SCHICHT[] Schichten;

    public SCHICHT Genommen;
    public static String von;

    public int AnzEbenen;

    public SPIEL(CONTROLER c_p, int AnzEbenen_p)
    {
        c = c_p;
        GAMEWINDOW.nLayers = AnzEbenen_p;
        AnzEbenen = AnzEbenen_p;
        IstVeraendert = true;
        
        Staebe = new STAB[3];
        Staebe[0] = new STAB(AnzEbenen);
        Staebe[0].Skin.SetzeX(140);
        Staebe[0].Skin.SetzeY(30);
        Staebe[1] = new STAB(AnzEbenen);
        Staebe[1].Skin.SetzeX(340);
        Staebe[1].Skin.SetzeY(30);
        Staebe[2] = new STAB(AnzEbenen);
        Staebe[2].Skin.SetzeX(540);
        Staebe[2].Skin.SetzeY(30);

        Schichten = new SCHICHT[AnzEbenen];
        for(int i = 0; i<AnzEbenen; i++)
        {
            Schichten[i] = new SCHICHT(i);
            Schichten[i].Skin.SetzeX(70+i*10);
            Schichten[i].Skin.SetzeY(280-i*20);
            Staebe[0].Ablegen(Schichten[i]);
        }
    }

    public void RUN(KEYSTATE keystate)
    {
        TastenPruefen(keystate);

        Point p = keystate.PickLastMouseClickPosition();
        if(p != null)
        {
            MausklickUeberpruefen(p);
        }

        if(IstVeraendert==true)
        {
            Ausgabe();
        }
        
        TestEnde();
    }

    //
    //
    // Teile der RUN-Methode
    //
    //

    public void TastenPruefen(KEYSTATE keystate)
    {
        if(keystate.IsPressed("N"))
        {
            for(int i = 0; i<AnzEbenen; i++)
            {
                Schichten[i].Skin.SetzeY(-30);

            }
            Staebe[0].Skin.SetzeX(-50);
            Staebe[1].Skin.SetzeX(-50);
            Staebe[2].Skin.SetzeX(-50);

            c.spiel = new SPIEL(c, AnzEbenen);
        }
        if(keystate.IsPressed("A"))
        {
            if(Genommen != null)
            {
                IstVeraendert = true;
                return;
            }
            SCHICHT s = Staebe[0].Nehmen();
            if(s == null)
            {
                SLeer = true;
                return;
            }
            Genommen = s;
            von = "A";
            GAMEWINDOW.getInstance().SetLayer(von);
        }
        if(keystate.IsPressed("S"))
        {
            if(Genommen != null)
            {
                IstVeraendert = true;
                return;
            }
            SCHICHT s = Staebe[1].Nehmen();
            if(s == null)
            {
                SLeer = true;
                return;
            }
            Genommen = s;
            von = "S";
            GAMEWINDOW.getInstance().SetLayer(von);
        }
        if(keystate.IsPressed("D"))
        {
            if(Genommen != null)
            {
                IstVeraendert = true;
                return;
            }
            SCHICHT s = Staebe[2].Nehmen();
            if(s == null)
            {
                SLeer = true;
                return;
            }
            Genommen = s;
            von = "D";
            GAMEWINDOW.getInstance().SetLayer(von);
        }
        if(keystate.IsPressed("Y"))
        {
            if(von.equals("A"))
            {
                if(Genommen != null)
                {
                    Staebe[0].Ablegen(Genommen);
                    Genommen=null;
                }
                von = "";
                GAMEWINDOW.getInstance().SetLayer(von);
            }
            if(von.equals("S"))
            {
                if(Genommen != null)
                {
                    Staebe[1].Ablegen(Genommen);
                    Genommen=null;
                }
                von = "";
                GAMEWINDOW.getInstance().SetLayer(von);
            }
            if(von.equals("D"))
            {
                if(Genommen != null)
                {
                    Staebe[2].Ablegen(Genommen);
                    Genommen=null;
                }
                von = "";
                GAMEWINDOW.getInstance().SetLayer(von);
            }
            IstVeraendert = true;
        }
    }

    public void MausklickUeberpruefen(Point p)
    {
        for(int i = 0; i<3; i++)
        {
            if(Staebe[i].Skin.PunktBeruehrt(p))
            {
                if(Genommen != null)
                {
                    boolean b = Staebe[i].Ablegen(Genommen);
                    if(b)
                    {
                        switch(i)
                        {
                            case 0: Genommen.Skin.SetzeX(70+Genommen.Hoehe*10); break;
                            case 1: Genommen.Skin.SetzeX(270+Genommen.Hoehe*10); break;
                            case 2: Genommen.Skin.SetzeX(470+Genommen.Hoehe*10); break;
                        }

                        Genommen.Skin.SetzeY(280-Staebe[i].NummerOberster()*20);
                        Genommen = null;
                        IstVeraendert = true;
                        von = "";
                        GAMEWINDOW.getInstance().SetLayer(von);
                    }
                }
            }
        }
    }
    
    public void TestEnde()
    {
        for(int i = 0; i<AnzEbenen; i++)
        {
            if(Staebe[2].Stapel[i] == null)
            {
                return;
            }
        }
        GAMEWINDOW.getInstance().End();
        c.stop();
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