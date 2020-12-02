public class ERSTELLEN
{
    public TISCH[] Tische;
    public SPIELER[] Spielerliste;

    public TISCH[][] BelegungListen; // 1te klammer=nummer der liste; 2te = tischnummer => Speichern

    public int anzSpieler;
    public int anzTische;
    public int anzWechsel;

    /** anzWechsel = wie oft man an einen anderen tisch geht
    -> anzTische an denen man sitzt = anzWechsel+1
     */
    public ERSTELLEN(int anzSpieler_p, int anzWechsel_p, String[] Namensliste)
    {
        anzSpieler = anzSpieler_p;
        anzWechsel = anzWechsel_p;
        anzTische = anzSpieler/4;

        Tische = new TISCH[anzTische];
        Spielerliste = new SPIELER[anzSpieler];

        for(int i = 0; i<anzSpieler; i++)
        {
            Spielerliste[i] = new SPIELER(Namensliste[i], (anzWechsel+1)*3);
        }

        BelegungListen = new TISCH[anzWechsel+1][anzTische];
    }

    public void Neue_Listen_Erstellen()
    {
        for(int i = 0; i<anzWechsel+1; i++)
        {
            TISCH[] a = Eine_Neue_Liste();
            for(int j = 0; j<anzTische; j++)
            {
                BelegungListen[i][j] = a[j];
            }
            for(int j = 0; j<20; j++)//wie oft soll versucht werden eine passende liste zu erstellen
            {
                if(BelegungTesten(BelegungListen[i]))
                {
                    j = 20;
                }
                else
                {
                    a = Eine_Neue_Liste();
                    for(int k = 0; k<anzTische; k++)
                    {
                        BelegungListen[i][k] = a[k];
                    }
                }
            }
            for(int j = 0; j<anzTische; j++)
            {
                BelegungListen[i][j].MitspielerVomTischHinzufuegen();
            }
        }
    }

    public boolean BelegungTesten(TISCH[] t)
    {
        for(int i = 0; i<anzTische; i++)
        {
            if(!t[i].TischTesten())
            {
                return false;
            }
        }
        return true;
    }

    public TISCH[] Eine_Neue_Liste()
    {
        SPIELER[] liste = Spielerliste;
        for(int i = 0; i<anzTische; i++)
        {
            Tische[i] = new TISCH(i);
        }
        for(int i = 0; i<anzSpieler; i++)
        {
            int rt = (int) (Math.random()*anzTische); // zufaelliger Tisch

            //solange versuchen, bis der spieler an einem tisch platziert werden konnte
            while(!Tische[rt].SpielerDazu(liste[i]))
            {
                rt = (int) (Math.random()*anzTische);
            }
        }
        return Tische;
    }
}