import java.awt.Point;

public class SPIEL
{
    private CONTROLER c;

    private boolean IstVeraendert;

    private FELD[][] SF;

    public SPIEL(CONTROLER c_p)
    {
        c = c_p;

        SF = new FELD[20][20];
        HintergrundNeu();
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

    public void Ausgabe()
    {
        IstVeraendert = false;
    }

    //Ende Run

    public void AusgabeLoeschen()
    {
        System.out.print('\u000C');
    }

    private void HintergrundNeu()
    {
        //Art zuweisen
        for(int u = 0; u<20; u++)
        {
            for(int r = 0; r<20; r++)
            {
                String Typ = "";

                int z = (int) (Math.random()*100);
                if(z >= 0 && z < 60)
                {
                    Typ = "Leer";
                }
                if(z >= 60 && z < 70)
                {
                    Typ = "Stadt";
                }
                if(z >= 70 && z < 100)
                {
                    Typ = "Wasser";
                }

                if(u == 1)
                {
                    if(r == 1){Typ = "HauptR";}
                    if(r == 18){Typ = "HauptG";}
                }
                if(u == 18)
                {
                    if(r == 1){Typ = "HauptB";}
                    if(r == 18){Typ = "HauptS";}
                }

                SF[u][r] = new FELD(Typ);
            }
        }
        //wenn zeit/lust/idee -> wasserverteilung anpassen
        
        //Bestimmen, ob Stadt/Hafen && Wasser neben Hauptstadt zu Land machen
        AngrenzendTest("Haupt",1,1);
        AngrenzendTest("Haupt",1,18);
        AngrenzendTest("Haupt",18,1);
        AngrenzendTest("Haupt",18,18);
        for(int u = 0; u<20; u++)
        {
            for(int r = 0; r<20; r++)
            {
                if(SF[u][r].Typ.equals("Stadt"))
                {
                    boolean hafen = AngrenzendTest("Stadt",u,r);
                    if(hafen){SF[u][r].Typ = "Hafen";}
                    else{SF[u][r].Typ = "Ort";}
                }
            }
        }

        //BildAnzeigen
        for(int u = 0; u<20; u++)
        {
            for(int r = 0; r<20; r++)
            {
                SF[u][r].BildAnzeigen(u,r);
            }
        }
    }

    /*
     * Bei Benutzer:
     * Stadt -> gibt zurück, ob ort ein Hafen ist
     * Haupt -> Macht Wasser um Hauptstadt zu Land, rückgabe egal
     */
    public boolean AngrenzendTest(String Benutzer, int u, int r)
    {
        if(Benutzer.equals("Stadt"))
        {
            boolean t = false;
            if(u>0)
            {
                if(SF[u-1][r].Typ.equals("Wasser")){t = true;}
            }
            if(r>0) {if(SF[u][r-1].Typ.equals("Wasser")){t = true;}}
            if(r<19){if(SF[u][r+1].Typ.equals("Wasser")){t = true;}}
            if(u<19)
            {
                {if(SF[u+1][r].Typ.equals("Wasser")){t = true;}}
            }
            return t;
        }
        if(Benutzer.equals("Haupt"))
        {
            if(SF[u-1][r-1].Typ.equals("Wasser") || SF[u-1][r-1].Typ.equals("Stadt"))
            {SF[u-1][r-1].Typ = "Leer";}
            if(SF[u-1][r].Typ.equals("Wasser") || SF[u-1][r].Typ.equals("Stadt"))
            {SF[u-1][r].Typ = "Leer";}
            if(SF[u-1][r+1].Typ.equals("Wasser") || SF[u-1][r+1].Typ.equals("Stadt"))
            {SF[u-1][r+1].Typ = "Leer";}
            if(SF[u][r-1].Typ.equals("Wasser") || SF[u][r-1].Typ.equals("Stadt"))
            {SF[u][r-1].Typ = "Leer";}
            if(SF[u][r+1].Typ.equals("Wasser") || SF[u][r+1].Typ.equals("Stadt"))
            {SF[u][r+1].Typ = "Leer";}
            if(SF[u+1][r-1].Typ.equals("Wasser") || SF[u+1][r-1].Typ.equals("Stadt"))
            {SF[u+1][r-1].Typ = "Leer";}
            if(SF[u+1][r].Typ.equals("Wasser") || SF[u+1][r].Typ.equals("Stadt"))
            {SF[u+1][r].Typ = "Leer";}
            if(SF[u+1][r+1].Typ.equals("Wasser") || SF[u+1][r+1].Typ.equals("Stadt"))
            {SF[u+1][r+1].Typ = "Leer";}

        }
        return false;
    }
}