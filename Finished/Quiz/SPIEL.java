public class SPIEL
{
    public FRAGE f;

    public GUI g;
    
    public int richtig;

    public int[] nFragen; //Nummern der Fragen die bisher beantwortet wurden

    public SPIEL(GUI gui)
    {
        g = gui;
    }

    public void Neu()
    {
        if(nFragen != null && nFragen[nFragen.length-1] != -1)
        {
            g.Ausgabe("Alle Fragen beantwortet.");
            return;
        }
        if(g.Ausgabewert().equals("") && nFragen != null && f != null)
        {
            ZahlEintragen(-1, -2);
        }
        f = new FRAGE(nFragen);
        if(nFragen == null)
        {
            nFragen = new int[f.AnzFragen];
            for(int i = 0; i<f.AnzFragen; i++)
            {
                nFragen[i] = -1;
            }
        }
        ZahlEintragen(f.Nummer, -1);
    }

    private void ZahlEintragen(int a, int Position)
    {
        if(Position == -1) // an der naechsten freien stelle
        {
            for(int i = 0;i<nFragen.length; i++)
            {
                if(nFragen[i] == -1)
                {
                    nFragen[i] = a;
                    return;
                }
            }
        }
        else
        {
            if(Position == -2) // an der letzten belegten stelle
            {
                for(int i = 0;i<nFragen.length; i++)
                {
                    if(nFragen[i] == -1)
                    {
                        nFragen[i-1] = a;
                        return;
                    }
                }
                nFragen[nFragen.length-1] = a;
                return;
            }
            if(Position >= nFragen.length)
            {
                return;
            }
            else
            {
                nFragen[Position] = a;
            }
        }
    }
}