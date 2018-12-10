import java.awt.Point;

public class SPIEL
{
    private CONTROLER c;

    private boolean IstVeraendert;

    public KARTE[][] K;

    public int AnzOffen;

    public int ox1;
    public int oy1;
    public int ox2;
    public int oy2;

    public boolean S1AmZug;
    public int Paare1;
    public int Paare2;

    public SPIEL(CONTROLER c_p)
    {
        c = c_p;
        K = new KARTE[4][4];

        IstVeraendert = true;
        S1AmZug = true;

        KartenVerteilen();
    }
    
    public void KartenVerteilen()
    {
        int aDrei = 0; int aHerz = 0; int aKreis = 0; int aKreuz = 0; int aPL = 0;
        int aPO = 0; int aRau = 0; int aSechs = 0; int aStern = 0;

        for(int u = 0; u<4; u++)
        {
            for(int r = 0; r<4; r++)
            {
                K[u][r] = new KARTE();
                K[u][r].Skin.SetzeX(r*60);
                K[u][r].Skin.SetzeY(u*60);

                while(K[u][r].Typ.equals(""))
                {
                    int z = (int) (Math.random()*9);
                    if(z == 0 && aDrei < 2)
                    { aDrei++; K[u][r].Typ = "Dreieck";}
                    if(z == 1 && aHerz < 2)
                    { aHerz++; K[u][r].Typ = "Herz";}
                    if(z == 2 && aKreis < 2)
                    { aKreis++; K[u][r].Typ = "Kreis";}
                    if(z == 3 && aKreuz < 2)
                    { aKreuz++; K[u][r].Typ = "Kreuz";}
                    if(z == 4 && aPL < 2)
                    { aPL++; K[u][r].Typ = "PfeilL";}
                    if(z == 5 && aPO < 2)
                    { aPO++; K[u][r].Typ = "PfeilO";}
                    if(z == 6 && aRau < 2)
                    { aRau++; K[u][r].Typ = "Raute";}
                    if(z == 7 && aSechs < 2)
                    { aSechs++; K[u][r].Typ = "Sechseck";}
                    if(z == 8 && aStern < 2)
                    { aStern++; K[u][r].Typ = "Stern";}
                }
            }
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

        KartenPruefen(keystate);

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

    public void KartenPruefen(KEYSTATE keystate)
    {
        if(AnzOffen == 2)
        {
            IstVeraendert = true;
            try { Thread.sleep((1300)); } catch (Exception e) {}
            Point p = keystate.PickLastMouseClickPosition();
            if(K[oy1][ox1].Skin.LeseName().equals(K[oy2][ox2].Skin.LeseName()))
            {
                K[oy1][ox1].Skin.SetzeY(2000);
                K[oy2][ox2].Skin.SetzeY(2000);
                if(S1AmZug)
                {
                    Paare1++;
                    S1AmZug = false;
                }
                else
                {
                    Paare2++;
                    S1AmZug = true;
                }
            }
            else
            {
                K[oy1][ox1].Verdecken();
                K[oy2][ox2].Verdecken();
            }
            AnzOffen = 0;
            if(S1AmZug)
            {
                S1AmZug = false;
            }
            else
            {
                S1AmZug = true;
            }
        }
    }

    public void TastenPruefen(KEYSTATE keystate)
    {
        if(keystate.IsPressed("B")) //Bsp
        {

        }
    }

    public void MausklickUeberpruefen(Point p)
    {
        for(int u = 0; u<4; u++)
        {
            for(int r = 0; r<4; r++)
            {
                if(K[u][r].Skin.PunktBeruehrt(p) && !K[u][r].offen)
                {
                    K[u][r].Aufdecken();
                    if(AnzOffen == 0)
                    {
                        ox1 = r;
                        oy1 = u;
                    }
                    else
                    {
                        ox2 = r;
                        oy2 = u;
                    }
                    AnzOffen++;
                }
            }
        }
    }

    public void Ausgabe()
    {
        IstVeraendert = false;

        AusgabeLoeschen();

        if(S1AmZug)
        {
            System.out.println("Spieler 1 ist am Zug");
        }
        else
        {
            System.out.println("Spieler 2 ist am Zug");
        }
        System.out.println("Paare Spieler 1: " + Paare1);
        System.out.println("Paare Spieler 2: " + Paare2);

        if(Paare1 + Paare2 == 7)
        {
            c.stop();

            for(int u = 0; u<4; u++)
            {
                for(int r = 0; r<4; r++)
                {
                    K[u][r].Aufdecken();
                }
            }

            if(Paare1 > Paare2)
            {
                System.out.println("Spieler 1 hat Gewonnen");
            }
            else
            {
                System.out.println("Spieler 2 hat Gewonnen");
            }
        }
    }

    //Ende Run

    public void AusgabeLoeschen()
    {
        System.out.print('\u000C');
    }
}