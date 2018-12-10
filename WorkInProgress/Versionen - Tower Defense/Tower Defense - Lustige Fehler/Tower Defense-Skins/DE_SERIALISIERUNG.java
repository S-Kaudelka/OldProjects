public class DE_SERIALISIERUNG
{
    public DE_SERIALISIERUNG()
    {
        
    }
    
    /**
     * Was soll gespeichert werden? + Reihenfolge
     * 
     * Spieler
     * Spiel -> alles was außerhalb des konstruktors verändert wird
     * Wellen -> Wz
     * Türme
     * Bild
     * 
     * Was nicht?     
     * 
     * Controler
     * Gegner -> Speichern nur möglich, wenn welle fertig ist
     * Gamewindow
     * Keystate
     * Werte
     */
    
    public static void Speichern(CONTROLER c)
    {
        String[] ArgsFest = new String[13];
        
        ArgsFest[1] = c.spiel.Spieler.Leben+"";
        ArgsFest[2] = c.spiel.Spieler.Gold+"";
        
        ArgsFest[3] = c.spiel.Welle+"";
        ArgsFest[4] = c.spiel.Wellengegner+"";
        ArgsFest[5] = c.spiel.GegnerAmLeben+"";
        ArgsFest[6] = c.spiel.GegnerBesiegt+"";
        ArgsFest[7] = c.spiel.GoldBekommen+"";
        ArgsFest[8] = c.spiel.AnzPfeil+"";
        ArgsFest[9] = c.spiel.AnzArmbr+"";
        ArgsFest[10] = c.spiel.AnzKanone+"";
        ArgsFest[11] = c.spiel.TeurerUm+"";
        ArgsFest[12] = c.spiel.Wellen.Wz+"";
        
        int anzTuerme = 0;
        
        for(int i = 0; i<20; i++){ if(c.spiel.Turmplaetze[i] != null){anzTuerme++;} };
        
        int anzVariablenTuerme = 20*2 + anzTuerme*11;
        
        String[] ArgsTuerme = new String[anzVariablenTuerme];
        int TuermeGespeichert = 0;
        
        for(int i = 0; i < anzVariablenTuerme && TuermeGespeichert <= 20; i = i+2)
        {
            if(c.spiel.Turmplaetze[TuermeGespeichert] != null)
            {
                ArgsTuerme[i] = TuermeGespeichert + "";
                ArgsTuerme[i+1] = true+"";
                
                ArgsTuerme[i+2] = c.spiel.Turmplaetze[TuermeGespeichert].Art+"";
                ArgsTuerme[i+3] = c.spiel.Turmplaetze[TuermeGespeichert].Wert+"";
                ArgsTuerme[i+4] = c.spiel.Turmplaetze[TuermeGespeichert].UpgradeKosten+"";
                ArgsTuerme[i+5] = c.spiel.Turmplaetze[TuermeGespeichert].Angriff+"";
                ArgsTuerme[i+6] = c.spiel.Turmplaetze[TuermeGespeichert].Reichweite+"";
                ArgsTuerme[i+7] = c.spiel.Turmplaetze[TuermeGespeichert].Nachladezeit+"";
                ArgsTuerme[i+8] = c.spiel.Turmplaetze[TuermeGespeichert].Turm.LeseX()+"";
                ArgsTuerme[i+9] = c.spiel.Turmplaetze[TuermeGespeichert].Turm.LeseY()+"";
                ArgsTuerme[i+10] = c.spiel.Turmplaetze[TuermeGespeichert].Flaeche+"";
                ArgsTuerme[i+11] = c.spiel.Turmplaetze[TuermeGespeichert].Level+"";
                ArgsTuerme[i+12] = c.spiel.Turmplaetze[TuermeGespeichert].SeitenFaktor+"";
                
                i = i + 11;
            }
            else
            {
                ArgsTuerme[i] = TuermeGespeichert + "";
                ArgsTuerme[i+1] = false+"";
            }
            TuermeGespeichert++;
        }
        
        ArgsFest[0] = anzVariablenTuerme + "";
        
        SPEICHERN.main(ArgsFest, ArgsTuerme);
    }
    
    public static void Laden(CONTROLER c_p)
    {
        CONTROLER c = c_p;
        
        String[] Args = LADEN.main();
        
        c.spiel.Spieler.Leben = TzZ(Args[0]);
        c.spiel.Spieler.Gold = TzZ(Args[1]);
        c.spiel.Welle = TzZ(Args[2]);
        c.spiel.Wellengegner = TzZ(Args[3]);
        c.spiel.GegnerAmLeben = TzZ(Args[4]);
        c.spiel.GegnerBesiegt = TzZ(Args[5]);
        c.spiel.GoldBekommen = TzB(Args[6]);
        c.spiel.AnzPfeil = TzZ(Args[7]);
        c.spiel.AnzArmbr = TzZ(Args[8]);
        c.spiel.AnzKanone = TzZ(Args[9]);
        c.spiel.TeurerUm = TzZ(Args[10]);
        c.spiel.Wellen.Wz = TzZ(Args[11]);
        
        
        int i = 12;
        
        for(int nTuerme = 0; nTuerme < 20; nTuerme++)
        {
            if(TzB(Args[i + 1]))
            {
                c.spiel.Turmplaetze[nTuerme] = new TURMPLATZ(Args[i+2], Args[i+12], TzZ(Args[i+8]), TzZ(Args[i+9]));
                c.spiel.Turmplaetze[nTuerme].Wert = TzZ(Args[i+3]);
                c.spiel.Turmplaetze[nTuerme].UpgradeKosten = TzZ(Args[i+4]);
                c.spiel.Turmplaetze[nTuerme].Angriff = TzZ(Args[i+5]);
                c.spiel.Turmplaetze[nTuerme].Reichweite = TzZ(Args[i+6]);
                c.spiel.Turmplaetze[nTuerme].Nachladezeit = TzZ(Args[i+7]);
                c.spiel.Turmplaetze[nTuerme].Flaeche = TzZ(Args[i+10]);
                c.spiel.Turmplaetze[nTuerme].Level = TzZ(Args[i+11]);
                i = i + 13;
            }
            else
            {
                //Hier passiert nichts, da automatisch im Konstruktor
                //ein leerer Turmplatz erzeugt wird
                i = i + 2;
            }
        }
        
        c.spiel.Ausgabe();
        System.out.println("");
        System.out.println("Erfolgreich Geladen");
        
        for(int t = 0; t < 10; t++)
        {
            if(c.spiel.Turmplaetze[t] != null)
            {
                c.spiel.Turmplaetze[t].SeitenFaktor = 1;
            }
        }
    }
    
    private static int TzZ(String Text) //Text zu Zahl
    {
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