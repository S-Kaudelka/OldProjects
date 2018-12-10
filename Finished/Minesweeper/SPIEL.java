import java.awt.Point;

public class SPIEL
{
    private CONTROLER c;

    private boolean IstVeraendert = true;

    private FELD[][] SF; //Spielfeld
    private int X;
    private int Y;

    private int anzMinen;
    private int MinenPlatziert;
    private int anzMarkiert;
    private boolean Aufdecken;
    private boolean zuViele;

    public SPIEL(CONTROLER c_p, int x, int y)
    {
        c = c_p;
        X = x;
        Y = y;
        anzMinen = x*y/7;
        if(anzMinen == 0)
        {
            anzMinen = 1;
        }
        Aufdecken = true;

        SF = new FELD[Y][X];
        MinenPlatziert = 0;
        for(int u = 0; u < Y; u++)
        {
            for(int r = 0; r < X; r++)
            {
                SF[u][r] = new FELD(r, u);
                int z = (int) (Math.random()*X*Y);
                if(z <= anzMinen && MinenPlatziert < anzMinen)
                {
                    SF[u][r].Mine = true;
                    MinenPlatziert++;
                }
            }
        }
        while(MinenPlatziert < anzMinen)
        {
            for(int u = Y - 1; u >= 0 ; u--)
            {
                for(int r = X - 1; r >= 0; r--)
                {
                    int z = (int) (Math.random()*X*Y);
                    if(z <= anzMinen && MinenPlatziert < anzMinen && !SF[u][r].Mine)
                    {
                        SF[u][r].Mine = true;
                        MinenPlatziert++;
                    }
                }
            }
        }
        for(int u = 0; u < Y; u++)
        {
            for(int r = 0; r < X; r++)
            {
                if(u < Y-1 && SF[u+1][r].Mine) // unten
                {
                    SF[u][r].MinenAngrenzend++;
                }
                if(u > 0 && SF[u-1][r].Mine) // oben
                {
                    SF[u][r].MinenAngrenzend++;
                }
                if(r < X-1 && SF[u][r+1].Mine) //rechts
                {
                    SF[u][r].MinenAngrenzend++;
                }
                if(r > 0 && SF[u][r-1].Mine) // links
                {
                    SF[u][r].MinenAngrenzend++;
                }
                if(u < Y-1 && r < X-1 && SF[u+1][r+1].Mine) //unten rechts
                {
                    SF[u][r].MinenAngrenzend++;
                }
                if(u < Y-1 && r > 0 && SF[u+1][r-1].Mine) //unten links 
                {
                    SF[u][r].MinenAngrenzend++;
                }
                if(u > 0 && r < X-1 && SF[u-1][r+1].Mine) //oben rechts
                {
                    SF[u][r].MinenAngrenzend++;
                }
                if(u > 0 && r > 0 && SF[u-1][r-1].Mine) //oben links
                {
                    SF[u][r].MinenAngrenzend++;
                }
            }
        }
    }

    public void RUN(KEYSTATE keystate)
    {
        if(!zuViele && anzMarkiert == anzMinen)
        {
            c.stop();
            AusgabeLoeschen();
            Ausgabe();
            System.out.println("Gewonnen");
            return;
        }
        
        TastenPruefen(keystate);

        Point p = keystate.PickLastMouseClickPosition();
        if(p != null)
        {
            MausklickUeberpruefen(p);
        }

        AlleGefunden();

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
        if(keystate.IsPressed("M")) // Markieren
        {
            Aufdecken = false;
            IstVeraendert = true;
        }
        if(keystate.IsPressed("N")) // Aufdecken
        {
            Aufdecken = true;
            IstVeraendert = true;
        }
    }

    public void MausklickUeberpruefen(Point p)
    {
        for(int u = 0; u < Y; u++)
        {
            for(int r = 0; r < X; r++)
            {
                if(SF[u][r].Skin.PunktBeruehrt(p))
                {
                    Beruehrt(u, r);
                }
            }
        }
    }

    public void Beruehrt(int u, int r)
    {
        if(!SF[u][r].Skin.LeseName().equals("fNeu.png") && !SF[u][r].Skin.LeseName().equals("fMarkiert.png") || 
        (SF[u][r].Skin.LeseName().equals("fMarkiert.png") && Aufdecken))
        {
            return;
        }
        boolean e = SF[u][r].Beruehrt(Aufdecken);
        if(!e && Aufdecken)
        {
            IstVeraendert = true;
            if(SF[u][r].MinenAngrenzend == 0)
            {
                //Direkt angenzend
                if(r > 0) //links
                {
                    Beruehrt(u, r-1);
                }
                if(u > 0) //oben
                {
                    Beruehrt(u-1, r);
                }
                if(r+1 < X)//rechts
                {
                    Beruehrt(u, r+1);
                }
                if(u+1 < Y)//unten
                {
                    Beruehrt(u+1, r);
                }
                
                //Diagonal angrenzend
                if(r > 0 && u > 0) //oben links
                {
                    Beruehrt(u-1, r-1);
                }
                if(r+1 < X && u > 0) //oben rechts
                {
                    Beruehrt(u-1, r+1);
                }
                if(r+1 < X && u+1 < Y) //unten rechts
                {
                    Beruehrt(u+1, r+1);
                }
                if(r > 0 && u+1 < Y) //unten links
                {
                    Beruehrt(u+1, r-1);
                }
            }
            else
            {
                return;
            }
        }
        else
        {
            if(Aufdecken)
            {
                c.stop();
                GameOver();
            }
            else
            {
                if(SF[u][r].markiert)
                {
                    anzMarkiert++;
                }
                else
                {
                    anzMarkiert--;
                }
                IstVeraendert = true;
            }
        }
    }
    
    public void GameOver()
    {
        int AnzRichtigMark = 0;
        int AnzFalschMark = 0;
        for(int u = 0; u<Y; u++)
        {
            for(int r = 0; r<X; r++)
            {
                if(SF[u][r].Mine && !SF[u][r].Skin.LeseName().equals("fMarkiert.png"))
                {
                    int x = SF[u][r].Skin.LeseX();
                    int y = SF[u][r].Skin.LeseY();
                    SF[u][r].Skin = new BILD("fMine.png");
                    SF[u][r].Skin.SetzeX(x);
                    SF[u][r].Skin.SetzeY(y);
                    SF[u][r].Skin.SetzeHoehe(25);
                    SF[u][r].Skin.SetzeBreite(25);
                }
                if(!SF[u][r].Mine && SF[u][r].Skin.LeseName().equals("fMarkiert.png"))
                {
                    int x = SF[u][r].Skin.LeseX();
                    int y = SF[u][r].Skin.LeseY();
                    SF[u][r].Skin = new BILD("fMarkiertfalse.png");
                    SF[u][r].Skin.SetzeX(x);
                    SF[u][r].Skin.SetzeY(y);
                    SF[u][r].Skin.SetzeHoehe(25);
                    SF[u][r].Skin.SetzeBreite(25);
                    AnzFalschMark++;
                }
                if(SF[u][r].Mine && SF[u][r].Skin.LeseName().equals("fMarkiert.png"))
                {
                    int x = SF[u][r].Skin.LeseX();
                    int y = SF[u][r].Skin.LeseY();
                    SF[u][r].Skin = new BILD("fMarkierttrue.png");
                    SF[u][r].Skin.SetzeX(x);
                    SF[u][r].Skin.SetzeY(y);
                    SF[u][r].Skin.SetzeHoehe(25);
                    SF[u][r].Skin.SetzeBreite(25);
                    AnzRichtigMark++;
                }
            }
        }
        AusgabeLoeschen();
        System.out.println("Anzahl Minen: " + anzMinen);
        System.out.println("Anzahl Markierte: " + anzMarkiert);
        System.out.println("Richtig Markierte: " + AnzRichtigMark);
        System.out.println("Falsch Markierte: " + AnzFalschMark);
        IstVeraendert = false;
    }

    public void AlleGefunden()
    {
        if(anzMarkiert < anzMinen)
        {
            zuViele = false;
            return;
        }
        if(anzMarkiert > anzMinen)
        {
            zuViele = true;
            return;
        }
        if(anzMarkiert == anzMinen)
        {
            for(int u = 0; u < Y; u++)
            {
                for(int r = 0; r < X; r++)
                {
                    int gefundene = 0;
                    if(SF[u][r].markiert && SF[u][r].Mine)
                    {
                        gefundene++;
                        if(gefundene == anzMinen)
                        {
                            IstVeraendert = false;
                            AusgabeLoeschen();
                            System.out.println("Gewonnen");
                            c.stop();
                        }
                    }
                    else
                    {
                        if(SF[u][r].markiert)
                        {
                            if(!zuViele)
                            {
                                zuViele = true;
                                IstVeraendert = true;
                            }
                            return;
                        }
                    }
                }
            }
            zuViele = false;
        }
    }

    public void Ausgabe()
    {
        IstVeraendert = false;
        AusgabeLoeschen();
        System.out.println("Anzahl Minen: " + anzMinen);
        if(Aufdecken)
        {
            System.out.println("Modus: Aufdecken");
        }
        else
        {
            System.out.println("Modus: Markieren");
        }
        System.out.println("Anzahl Markierte: " + anzMarkiert);
        if(zuViele)
        {
            System.out.println("Zu viele markiert");
        }
    }

    //
    //Ende Run
    //

    public void AusgabeLoeschen()
    {
        System.out.print('\u000C');
    }
}