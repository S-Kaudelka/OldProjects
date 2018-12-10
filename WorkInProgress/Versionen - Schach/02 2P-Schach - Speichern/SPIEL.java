import java.awt.Point;

public class SPIEL
{
    private CONTROLER c;

    //Ausgabevariablen
    public boolean IstVeraendert;
    public boolean Schachmatt;
    public String Sieger;
    public boolean Schach;
    public String F_K;
    public boolean KbewInSch; //Koenig waere nach dem bewegen im Schach
    public boolean RochadeNichtM;
    
    public boolean Umwandeln;
    public int NummerUmw;

    public BILD[] hintergrund;

    //Figuren
    public FIGUR[] BauerW;
    public FIGUR[] LaeuferW;
    public FIGUR[] TurmW;
    public FIGUR[] SpringerW;
    public FIGUR   DameW;
    public FIGUR   KoenigW;

    public FIGUR[] BauerS;
    public FIGUR[] LaeuferS;
    public FIGUR[] TurmS;
    public FIGUR[] SpringerS;
    public FIGUR   DameS;
    public FIGUR   KoenigS;

    public FIGUR[][] SF; //Spielfeld

    public int FeldAusgew;
    public boolean FigurAusgew;

    public String AmZug;

    public SPIEL(CONTROLER c_p)
    {
        c = c_p;

        IstVeraendert = true;

        FeldAusgew = -1;

        AmZug = "W";
        
        NummerUmw = -1;

        HintergrundZeichnen();

        BauerW = new FIGUR[9];
        for(int i = 1; i<9; i++)
        {
            BauerW[i]   = new FIGUR("W", "Bauer",i,"B");
        }
        TurmW           = new FIGUR[2];
        TurmW[0]        = new FIGUR("W","Turm",1,"A");
        TurmW[1]        = new FIGUR("W","Turm",8,"A");
        SpringerW       = new FIGUR[2];
        SpringerW[0]    = new FIGUR("W","Springer",2,"A");
        SpringerW[1]    = new FIGUR("W","Springer",7,"A");
        LaeuferW        = new FIGUR[2];
        LaeuferW[0]     = new FIGUR("W","Laeufer",3,"A");
        LaeuferW[1]     = new FIGUR("W","Laeufer",6,"A");
        DameW           = new FIGUR("W","Dame",4,"A");
        KoenigW         = new FIGUR("W","Koenig",5,"A");

        BauerS = new FIGUR[9];
        for(int i = 1; i<9; i++)
        {
            BauerS[i]   = new FIGUR("S","Bauer",i,"G");
        }
        TurmS           = new FIGUR[2];
        TurmS[0]        = new FIGUR("S","Turm",1,"H");
        TurmS[1]        = new FIGUR("S","Turm",8,"H");
        SpringerS       = new FIGUR[2];
        SpringerS[0]    = new FIGUR("S","Springer",2,"H");
        SpringerS[1]    = new FIGUR("S","Springer",7,"H");
        LaeuferS        = new FIGUR[2];
        LaeuferS[0]     = new FIGUR("S","Laeufer",3,"H");
        LaeuferS[1]     = new FIGUR("S","Laeufer",6,"H");
        DameS           = new FIGUR("S","Dame",4,"H");
        KoenigS         = new FIGUR("S","Koenig",5,"H");

        SF = new FIGUR[8][8];

        for(int i = 1; i<9; i++)
        {
            SF[1][i-1] = BauerW[i];
        }
        SF[0][0] = TurmW[0];
        SF[0][1] = SpringerW[0];
        SF[0][2] = LaeuferW[0];
        SF[0][3] = DameW;
        SF[0][4] = KoenigW;
        SF[0][5] = LaeuferW[1];
        SF[0][6] = SpringerW[1];
        SF[0][7] = TurmW[1];

        for(int i = 1; i<9; i++)
        {
            SF[6][i-1] = BauerS[i];
        }
        SF[7][0] = TurmS[0];
        SF[7][1] = SpringerS[0];
        SF[7][2] = LaeuferS[0];
        SF[7][3] = DameS;
        SF[7][4] = KoenigS;
        SF[7][5] = LaeuferS[1];
        SF[7][6] = SpringerS[1];
        SF[7][7] = TurmS[1];
    }

    public void RUN(KEYSTATE keystate)
    {
        TastenPruefen(keystate);
        if(Umwandeln)
        {
            return;
        }

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
        //Umwandeln
        if(keystate.IsPressed("Y") && Umwandeln) //Turm
        {
            FigurUmwandeln("Turm");
        }
        if(keystate.IsPressed("X") && Umwandeln) //Springer
        {
            FigurUmwandeln("Springer");
        }
        if(keystate.IsPressed("C") && Umwandeln) //Laeufer
        {
            FigurUmwandeln("Laeufer");
        }
        if(keystate.IsPressed("V") && Umwandeln) //Dame
        {
            FigurUmwandeln("Dame");
        }
        
        if(keystate.IsPressed("N"))
        {
            if(Schachmatt)
            {
                c.spiel = new SPIEL(c);
                return;
            }
        }
        if(keystate.IsPressed("A")) //Laden
        {
            c.Laden();
            Ausgabe();
            System.out.println("Geladen");
        }
        if(keystate.IsPressed("S")) //Speichern
        {
            Ausgabe();
            c.Speichern();
        }
        if(keystate.IsPressed("R"))
        {
            int u = 0;int r = 0;int f = FeldAusgew;while(f > 7){u++;f = f - 8;} r = f;
            if(u != -1 && r != -1)
            {
                if(SF[u][r].Art.equals("Turm") && !SF[u][r].Bewegt)
                {
                    FIGUR turm = SF[u][r];
                    FIGUR koenig;
                    if(turm.Farbe.equals("W"))
                    {
                        koenig = KoenigW;
                    }
                    else
                    {
                        koenig = KoenigS;
                    }
                    if(!koenig.Bewegt)
                    {
                        Rochade(turm, koenig);
                        IstVeraendert=true;
                    }
                }
            }
        }
    }

    public void MausklickUeberpruefen(Point p)
    {
        if(Schachmatt){return;}
        for(int i = 0; i < 64; i++)
        {
            if(hintergrund[i].PunktBeruehrt(p))
            {
                if(FeldAusgew == -1) // kein feld ausgew & Figur ausgewaehlt bestimmen
                {
                    FeldAusgew = i;
                    int u = 0;
                    int r = 0;
                    int f = i;

                    while(f > 7)
                    {
                        u++;
                        f = f - 8;
                    }
                    r = f;

                    if(SF[u][r] != null)
                    {
                        if(SF[u][r].Farbe.equals(AmZug))
                        {
                            FigurAusgew = true;
                        }
                        else
                        {
                            FeldAusgew = -1;
                        }
                    }
                    else
                    {
                        FeldAusgew = -1;
                    }
                }
                else
                {
                    if(FigurAusgew && i != FeldAusgew) // Figur Ausgew
                    {
                        boolean b = FigurBewegen(FeldAusgew, i);
                        if(b)
                        {
                            if(AmZug.equals("W"))
                            {
                                AmZug = "S";
                            }
                            else
                            {
                                AmZug = "W";
                            }
                        }
                        FigurAusgew = false;
                        FeldAusgew = -1;
                    }
                }
                IstVeraendert = true;
            }
        }
    }

    public boolean FigurBewegen(int von, int zu)
    {
        // für Ausgangsfeld
        int u_a = 0;
        int r_a = 0;
        int f_a = von;

        while(f_a > 7)
        {
            u_a++;
            f_a = f_a - 8;
        }
        r_a = f_a;

        // für Zielfeld
        int u_z = 0;
        int r_z = 0;
        int f_z = zu;

        while(f_z > 7)
        {
            u_z++;
            f_z = f_z - 8;
        }
        r_z = f_z;

        FIGUR a = SF[u_a][r_a];
        if(SF[u_z][r_z] != null && a.Farbe == SF[u_z][r_z].Farbe)
        {
            System.out.println("Eigene Figur im Weg");
            return false;
        }

        boolean bewegungMoeglich = false;
        if(a.Art.equals("Bauer")){bewegungMoeglich = BBauer(u_a, r_a, u_z, r_z, a.Farbe, "Bew");}
        if(a.Art.equals("Turm")){bewegungMoeglich = BTurm(u_a, r_a, u_z, r_z, a.Farbe, "Bew");}
        if(a.Art.equals("Springer")){bewegungMoeglich = BSpringer(u_a, r_a, u_z, r_z, a.Farbe, "Bew");}
        if(a.Art.equals("Laeufer")){bewegungMoeglich = BLaeufer(u_a, r_a, u_z, r_z, a.Farbe, "Bew");}
        if(a.Art.equals("Koenig")){bewegungMoeglich = BKoenig(u_a, r_a, u_z, r_z, "Bew");}
        if(a.Art.equals("Dame")){bewegungMoeglich = BDame(u_a, r_a, u_z, r_z, a.Farbe, "Bew");}

        if(bewegungMoeglich)
        {
            if(SF[u_z][r_z] != null)
            {
                if(SF[u_z][r_z].Art.equals("Koenig") && SF[u_z][r_z].Farbe != a.Farbe)
                {
                    Schachmatt = true;
                    if(SF[u_z][r_z].Farbe.equals("W")){Sieger = "Schwarz";}else{Sieger = "Weiss";}
                    return false;
                }
            }

            if(SF[u_z][r_z] != null)
            {
                SF[u_z][r_z].X = -1;
                SF[u_z][r_z].BildBew();
                SF[u_z][r_z] = null;
            }
            SF[u_a][r_a] = null;
            SF[u_z][r_z] = a;
            a.FN = zu;
            a.X = r_z + 1;
            a.Y = a.ZzB(u_z);
            a.BildBew();
            a.Bewegt = true;
            
            if(a.Art.equals("Bauer"))
            {
                if(a.Farbe.equals("W"))
                {
                    if(u_z == 7)
                    {
                        FigurUmwandelnStart(a.FN);
                    }
                }
                else
                {
                    if(u_z == 0)
                    {
                        FigurUmwandelnStart(a.FN);
                    }
                }
            }

            //Schach testen
            SchachTest(-1, -1, "");
            return true;
        }

        return false;
    }

    // Teile Bewegen

    //Aufrufer: "Bew" || "test"

    public boolean BBauer(int u_a, int r_a, int u_z, int r_z, String Farbe, String Aufrufer)
    {
        int du = u_z-u_a;
        int dr = r_z-r_a;
        if((du == 1 && Farbe.equals("W") || du == -1 && Farbe.equals("S"))
        && r_a == r_z && SF[u_z][r_z] == null && Aufrufer.equals("Bew"))
        {
            return true;
        }
        if((du == 1 && Farbe.equals("W") || du == -1 && Farbe.equals("S")) && Betrag(dr) == 1)
        {
            if(Aufrufer.equals("test"))
            {
                return true;
            }
            if(SF[u_z][r_z] != null && !Aufrufer.equals("test"))
            {
                return true;
            }
        }
        if(Farbe.equals("W") && u_a == 1 && Betrag(du) == 2 && r_a == r_z && Aufrufer.equals("Bew"))
        {
            return true;
        }
        if(Farbe.equals("S") && u_a == 6 && Betrag(du) == 2 && r_a == r_z && Aufrufer.equals("Bew"))
        {
            return true;
        }
        return false;
    }

    public boolean BTurm(int u_a, int r_a, int u_z, int r_z, String Farbe, String Aufrufer)
    {
        if(u_a != u_z && r_a == r_z)
        {
            int i = u_a + 1;
            int a = u_z;
            if(u_a > u_z)
            {
                i = u_z + 1;
                a = u_a;
            }
            while(i < a)
            {
                if(SF[i][r_z] != null)
                {
                    if(SF[i][r_z].Art.equals("Koenig") && Aufrufer.equals("test") && !SF[i][r_z].Farbe.equals(Farbe))
                    {
                        return true;
                    }
                    else
                    {
                        System.out.println("Figur im Weg");
                        return false;
                    }
                }
                i++;
            }
            return true;
        }
        if(u_a == u_z && r_a != r_z)
        {
            int i = r_a + 1;
            int a = r_z;
            if(r_a > r_z)
            {
                i = r_z + 1;
                a = r_a;
            }
            for(; i < a; i++)
            {
                if(SF[u_z][i] != null)
                {
                    if(SF[u_z][i].Art.equals("Koenig") && Aufrufer.equals("test") && !SF[i][r_z].Farbe.equals(Farbe))
                    {
                        return true;
                    }
                    else
                    {
                        System.out.println("Figur im Weg");
                        return false;
                    }
                }
            }
            return true;
        }

        return false;
    }

    public boolean BSpringer(int u_a, int r_a, int u_z, int r_z, String Farbe, String Aufrufer)
    {
        int u = Betrag(u_a - u_z);
        int r = Betrag(r_a - r_z);

        if(u+r == 3 && r != 0 && u != 0)
        {
            return true;
        }

        return false;
    }

    public boolean BLaeufer(int u_a, int r_a, int u_z, int r_z, String Farbe, String Aufrufer)
    {
        int y = u_z - u_a;
        int x = r_z - r_a;

        int vx;
        int vy;

        if(x<0){vx = -1;}else{vx = 1;}
        if(y<0){vy = -1;}else{vy = 1;}

        if(Betrag(x) != Betrag(y))
        {
            return false;
        }

        int i = 1;
        int a = Betrag(x);
        for(; i < a; i++)
        {
            int u = u_a+i*vy;
            int r = r_a+i*vx;
            if(SF[u][r] != null)
            {
                if(SF[u][r].Art.equals("Koenig") && Aufrufer.equals("test") && !SF[u][r].Farbe.equals(Farbe))
                {
                    return true;
                }
                else
                {
                    System.out.println("Figur im Weg");
                    return false;
                }
            }
        }

        return true;
    }

    public boolean BKoenig(int u_a, int r_a, int u_z, int r_z, String Aufrufer)
    {
        int u = u_a - u_z;
        int r = r_a - r_z;
        if(SchachTest(u_z, r_z, SF[u_a][r_a].Farbe))
        {
            KbewInSch = true;
            return false;
        }
        if((Betrag(u) == 0 || Betrag(u) == 1) &&
        (Betrag(r) == 0 || Betrag(r) == 1) && (r != 0 || u != 0))
        {
            return true;
        }

        return false;
    }

    public boolean BDame(int u_a, int r_a, int u_z, int r_z, String Farbe, String Aufrufer)
    {
        if(BTurm(u_a, r_a, u_z, r_z, Farbe, Aufrufer) || BLaeufer(u_a, r_a, u_z, r_z, Farbe, Aufrufer))
        {
            return true;
        }

        return false;
    }

    //Parameter dann, wenn der König sich bewegen will
    public boolean SchachTest(int u_t, int r_t, String F)
    {
        for(int u = 0; u<8; u++)
        {
            for(int r = 0; r<8; r++)
            {
                FIGUR a = SF[u][r];
                if(SF[u][r] != null && !SF[u][r].Art.equals("Koenig"))
                {
                    String f = a.Farbe;
                    String f_k;

                    int u_a = u;
                    int r_a = r;

                    int u_z = 0;
                    int r_z = 0;

                    if(f.equals("W"))
                    {
                        r_z = KoenigS.X-1;
                        u_z = FIGUR.BzZ(KoenigS.Y);
                        f_k = "S";
                    }
                    else
                    {
                        r_z = KoenigW.X-1;
                        u_z = FIGUR.BzZ(KoenigW.Y);
                        f_k = "W";
                    }

                    if(u_t != -1 && !f.equals(F))
                    {
                        u_z = u_t;
                        r_z = r_t;
                    }

                    boolean bewegungMoeglich = false;
                    if(a.Art.equals("Bauer")){bewegungMoeglich = BBauer(u_a, r_a, u_z, r_z, a.Farbe, "test");}
                    if(a.Art.equals("Turm")){bewegungMoeglich = BTurm(u_a, r_a, u_z, r_z, a.Farbe, "test");}
                    if(a.Art.equals("Springer")){bewegungMoeglich = BSpringer(u_a, r_a, u_z, r_z, a.Farbe, "test");}
                    if(a.Art.equals("Laeufer")){bewegungMoeglich = BLaeufer(u_a, r_a, u_z, r_z, a.Farbe, "test");}
                    if(a.Art.equals("Dame")){bewegungMoeglich = BDame(u_a, r_a, u_z, r_z, a.Farbe, "test");}

                    if(u_t != -1 && bewegungMoeglich && !f.equals(f_k))
                    {
                        return true;
                    }

                    if(bewegungMoeglich && !f.equals(f_k) && u_t == -1)
                    {
                        Schach = true;
                        F_K = f_k;
                        return true;
                    }
                    else
                    {
                        Schach = false;
                    }
                }
            }
        }
        return false;
    }

    public void Rochade(FIGUR Turm, FIGUR Koenig)
    {
        int r_t = Turm.X-1;
        int r_k = 4;
        int u;
        if(Koenig.Farbe.equals("W"))
        {
            u = 0;
        }
        else
        {
            u = 7;
        }

        //Kurze Rochade -> nach rechts
        if(r_t == 7)
        {
            for(int r = 5; r<7; r++)
            {
                if(SF[u][r] != null || SchachTest(u, r, Koenig.Farbe))
                {
                    RochadeNichtM = true;
                    return;
                }
            }

            SF[u][7] = null;
            SF[u][5] = Turm;
            Turm.FN = Turm.FN-2;
            Turm.X = 6;
            Turm.Y = Turm.ZzB(u);
            Turm.BildBew();
            Turm.Bewegt = true;

            SF[u][4] = null;
            SF[u][6] = Koenig;
            Koenig.FN = Koenig.FN+2;
            Koenig.X = 7;
            Koenig.Y = Koenig.ZzB(u);
            
            Koenig.Skin.SetzeX(470);
            Koenig.Skin.SetzeY(530);
            //Koenig.BildBew();
            Koenig.Bewegt = true;

            if(AmZug.equals("W"))
            {
                AmZug = "S";
            }
            else
            {
                AmZug = "W";
            }
            FigurAusgew = false;
            FeldAusgew = -1;
        }
        //Lange Rochade -> nach links
        if(r_t == 0)
        {
            for(int r = 2; r<4; r++)
            {
                if(SF[u][r] != null || SchachTest(u, r, Koenig.Farbe))
                {
                    RochadeNichtM = true;
                    return;
                }
            }
            if(SF[u][1] != null)
            {
                RochadeNichtM = true;
                return;
            }

            SF[u][0] = null;
            SF[u][3] = Turm;
            Turm.FN = Turm.FN+3;
            Turm.X = 4;
            Turm.Y = Turm.ZzB(u);
            Turm.BildBew();
            Turm.Bewegt = true;

            SF[u][4] = null;
            SF[u][2] = Koenig;
            Koenig.FN = Koenig.FN-2;
            Koenig.X = 3;
            Koenig.Y = Koenig.ZzB(u);
            Koenig.BildBew();
            Koenig.Bewegt = true;

            if(AmZug.equals("W"))
            {
                AmZug = "S";
            }
            else
            {
                AmZug = "W";
            }
            FigurAusgew = false;
            FeldAusgew = -1;
        }
    }
    
    private void FigurUmwandelnStart(int FeldNummer)
    {
        Umwandeln = true;
        
        NummerUmw = FeldNummer;
    }
    
    private void FigurUmwandeln(String Art)
    {
        int u = 0;int r = 0;int f = NummerUmw;while(f > 7){u++;f = f - 8;} r = f;
        
        FIGUR c = new FIGUR(SF[u][r].Farbe, Art, SF[u][r].X, SF[u][r].Y);
        SF[u][r].X = -1;
        SF[u][r].BildBew();
        SF[u][r] = c;
        
        Umwandeln = false;
        Ausgabe();
    }
    

    //Ende Bewegen
    

    public void Ausgabe()
    {
        IstVeraendert = false;
        AusgabeLoeschen();

        if(Schachmatt)
        {
            System.out.println("Schachmatt");
            System.out.println("Sieger ist " + Sieger);
            System.out.println("Druecke 'N', um eine neue Runde zu starten");
            return;
        }

        if(Schach)
        {
            System.out.println("Koenig "+F_K+" ist im Schach");
        }

        if(KbewInSch)
        {
            System.out.println("Der Koenig waere nach dem Bewegen im Schach");
            KbewInSch = false;
        }

        System.out.println("AmZug: " + AmZug);
        if(FeldAusgew == -1)
        {
            System.out.println("Kein Feld Ausgewaehlt");
        }
        else
        {
            int u = 0;int r = 0;int f = FeldAusgew;while(f > 7){u++;f = f - 8;} r = f;
            System.out.println("Feld Ausgewaehlt: Unten " + u + ", Rechts " + r 
                + " (" + SF[u][r].Art +")");
        }

        if(RochadeNichtM)
        {
            RochadeNichtM = false;
            System.out.println("Rochade nicht moeglich");
        }
        
        if(Umwandeln)
        {
            AusgabeLoeschen();
            System.out.println("Umwandeln:");
            System.out.println("Y: Turm");
            System.out.println("X: Springer");
            System.out.println("C: Laeufer");
            System.out.println("V: Dame");
        }
    }

    //Ende Run

    public void AusgabeLoeschen()
    {
        System.out.print('\u000C');
    }

    public int Betrag(int z)
    {
        if(z < 0)
        {
            return -z;
        }
        else
        {
            return z;
        }
    }

    public void HintergrundZeichnen()
    {
        hintergrund = new BILD[64];

        for(int u = 0; u<8; u++)
        {
            for(int r = 0; r<8; r++)
            {
                int n = u*8+r;
                if(u%2 == r%2)
                {
                    hintergrund[n] = new BILD("images\\hSchwarz.png");
                }
                else
                {
                    hintergrund[n] = new BILD("images\\hWeiss.png");
                }
                hintergrund[n].SetzeX(r*75);
                hintergrund[n].SetzeY(u*75);
            }
        }
    }
}