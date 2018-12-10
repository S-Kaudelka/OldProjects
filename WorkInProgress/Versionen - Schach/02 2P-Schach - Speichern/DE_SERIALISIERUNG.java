public class DE_SERIALISIERUNG
{
    public DE_SERIALISIERUNG()
    {

    }

    /**
     * Was soll gespeichert werden? + Reihenfolge
     * 
     * Spiel:
     * AusgabeVariablen
     * Feld-&FigurAusgew
     * AmZug
     * 
     * Alle Figurenreferenzen <-> Komplettes feld != null
     * 
     * Figur:
     * 5 Variablen -> u,r statt X,Y
     */

    public static void Speichern(CONTROLER c, String Dateiname)
    {
        int nFig = 0;
        for(int u = 0; u<8; u++)
        {
            for(int r = 0; r<8; r++)
            {
                if(c.spiel.SF[u][r] != null)
                {
                    nFig++;
                }
            }
        }

        String[] Werte = new String[12+nFig*5];

        //Ausgabe
        Werte[0] = c.spiel.IstVeraendert+"";
        Werte[1] = c.spiel.Schachmatt+"";
        Werte[2] = c.spiel.Sieger;
        Werte[3] = c.spiel.Schach+"";
        Werte[4] = c.spiel.F_K;
        Werte[5] = c.spiel.KbewInSch+"";
        Werte[6] = c.spiel.RochadeNichtM+"";
        //Sonst
        Werte[7] = c.spiel.Umwandeln+"";
        Werte[8] = c.spiel.Umwandeln+"";
        Werte[9] = c.spiel.FeldAusgew+"";
        Werte[10] = c.spiel.FigurAusgew+"";
        Werte[11] = c.spiel.AmZug;
        //Figuren
        int anzFig = 0;
        for(int u = 0; u<8; u++)
        {
            for(int r = 0; r<8; r++)
            {
                if(c.spiel.SF[u][r] != null)
                {
                    Werte[12+anzFig*5] = c.spiel.SF[u][r].Farbe;
                    Werte[13+anzFig*5] = c.spiel.SF[u][r].Art;
                    Werte[14+anzFig*5] = r+"";
                    Werte[15+anzFig*5] = u+"";
                    Werte[16+anzFig*5] = c.spiel.SF[u][r].Bewegt+"";

                    anzFig++;
                }
            }
        }
        SPEICHERN.main(Werte, Dateiname);
    }

    public static boolean Laden(CONTROLER c, String Dateiname)
    {
        String[] Werte = LADEN.main(Dateiname);

        if(Werte[0].equals("nicht vorhanden"))
        {
            return false;
        }

        ladenTrue(c.spiel);

        c.spiel.HintergrundZeichnen();

        int a = Werte.length-12;

        int anzFig = a/5;

        c.spiel.IstVeraendert = TzB(Werte[0]);
        c.spiel.Schachmatt = TzB(Werte[1]);
        c.spiel.Sieger = Werte[2];
        c.spiel.Schach = TzB(Werte[3]);
        c.spiel.F_K = Werte[4];
        c.spiel.KbewInSch = TzB(Werte[5]);
        c.spiel.RochadeNichtM = TzB(Werte[6]);

        c.spiel.Umwandeln = TzB(Werte[7]);
        c.spiel.NummerUmw = TzZ(Werte[8]);
        c.spiel.FeldAusgew = -1;
        c.spiel.FigurAusgew = false;
        c.spiel.AmZug = Werte[11];
        

        for(int i = 0; i<anzFig; i++)
        {
            int r = TzZ(Werte[14+5*i]);
            int u = TzZ(Werte[15+5*i]);
            FIGUR f = new FIGUR(Werte[12+5*i], Werte[13+5*i], r+1, FIGUR.ZzB(u), TzB(Werte[16+5*i]));
            c.spiel.SF[u][r] = f;
            if(f.Art.equals("Koenig"))
            {
                if(f.Farbe.equals("W"))
                {
                    c.spiel.KoenigW = f;
                }
                else
                {
                    c.spiel.KoenigS = f;
                }
            }
        }
        return true;
    }

    public static SPIEL ladenTrue(SPIEL spiel)
    {
        for(int i = 1; i<9; i++)
        {
            spiel.BauerW[i].X =-1;
            spiel.BauerW[i].BildBew();
        }
        for(int i = 1; i<9; i++)
        {
            spiel.BauerS[i].X =-1;
            spiel.BauerS[i].BildBew();
        }
        spiel.TurmW[0].X =-1;        spiel.TurmW[0].BildBew();
        spiel.TurmW[1].X =-1;        spiel.TurmW[1].BildBew();
        spiel.TurmS[0].X =-1;        spiel.TurmS[0].BildBew();
        spiel.TurmS[1].X =-1;        spiel.TurmS[1].BildBew();
        spiel.SpringerW[0].X =-1;        spiel.SpringerW[0].BildBew();
        spiel.SpringerW[1].X =-1;        spiel.SpringerW[1].BildBew();
        spiel.SpringerS[0].X =-1;        spiel.SpringerS[0].BildBew();
        spiel.SpringerS[1].X =-1;        spiel.SpringerS[1].BildBew();
        spiel.LaeuferW[0].X =-1;        spiel.LaeuferW[0].BildBew();
        spiel.LaeuferW[1].X =-1;        spiel.LaeuferW[1].BildBew();
        spiel.LaeuferS[0].X =-1;        spiel.LaeuferS[0].BildBew();
        spiel.LaeuferS[1].X =-1;        spiel.LaeuferS[1].BildBew();
        spiel.DameW.X =-1;        spiel.DameW.BildBew();
        spiel.DameS.X =-1;        spiel.DameS.BildBew();
        for(int u = 0; u<8; u++)
        {
            for(int r = 0; r<8; r++)
            {
                if(spiel.SF[u][r] != null && spiel.SF[u][r].Art.equals("Koenig"))
                {
                    if(spiel.SF[u][r].Farbe.equals("W"))
                    {
                        spiel.KoenigW = spiel.SF[u][r];
                    }
                    else
                    {
                        spiel.KoenigS = spiel.SF[u][r];
                    }
                }
            }
        }
        for(int u = 0; u<2; u++)
        {
            for(int r = 0; r<8; r++)
            {
                spiel.SF[u][r] = null;
            }
        }
        for(int u = 6; u<8; u++)
        {
            for(int r = 0; r<8; r++)
            {
                spiel.SF[u][r] = null;
            }
        }
        return spiel;
    }

    //Sonst

    private static int TzZ(String Text) //Text zu Zahl
    {
        if(Text == null)
        {
            return 0;
        }
        for(int i = 0; i <= 2000; i++)
        {
            if(Text.equals(i + ""))
            {
                return i;
            }
        }
        return 0;
    }

    private static boolean TzB(String Text) //Text zu Boolean
    {
        if(Text.equals("true"))
        {
            return true;
        }
        return false;
    }
}