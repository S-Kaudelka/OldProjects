public class DE_SERIALISIERUNG
{
    public DE_SERIALISIERUNG()
    {
        
    }
    
    public static void StartSpeichern(int stage, String Dateiname)
    {
        String[] args = new String[1];
        args[0] = stage+"";
        SPEICHERN.main(Dateiname, args);
    }
    
    public static int StartLaden(String Dateiname)
    {
        String s[] = new String[1];
        
        s = LADEN.main(Dateiname, "START");
        
        if(s == null)
        {
            return 0;
        }
        
        int stage = TzZ(s[0]);
        
        if(stage == -1)
        {
            NeuenSpieler(Dateiname);
        }
        
        return stage;
    }
    
    private static void NeuenSpieler(String SpielerName)
    {
        String[] s = LADEN.main("", "DE_SERIALISIERUNG");
        String[] t = new String[s.length+1];
        for(int i = 0; i<s.length; i++)
        {
            t[i] = s[i];
        }
        t[s.length] = SpielerName;
        SPEICHERN.main("Spielernamen", s);
        String[] a = {"0"};
        SPEICHERN.main(SpielerName, a);
    }
    
    public static void SpielerUeberpruefen()
    {
        //Spielernamen laden, jeden Namen ueberpruefen, ob er eine datei hat; wenn nicht, namen löschen
        String[] s = LADEN.main(null, "DE_SERIALISIERUNG");
        
        int leereFelder = 0;
        
        for(int i = 0; i < s.length; i++)
        {
            boolean u = LADEN.Ueberpruefung(s[i]);
            if(!u)
            {
                s[i] = null;
                leereFelder++;
            }
        }
        
        String[] b = new String[s.length-leereFelder];
        int c = 0;
        for(int a = 0; a < s.length; a++)
        {
            if(s[a] != null)
            {
                b[c] = s[a];
                c++;
            }
        }
    }
    
    /**
     * Was soll gespeichert werden? + Reihenfolge
     * 
     * Spieler
     * Spiel -> alles was außerhalb des konstruktors verändert wird
     * Wellen -> Wz
     * Turmplatz
     * Bild
     * 
     * Was nicht?     
     * 
     * Controler
     * Gegner -> Speichern nur möglich, wenn welle fertig ist
     * Gamewindow
     * Keystate
     * Werte
     * Wegabschnitt
     * Weg
     */
    public static void Speichern(CONTROLER c)
    {
        String[] ArgsFest = new String[12];
        ArgsFest[1] = c.spiel.Spieler.Leben+"";
        ArgsFest[2] = c.spiel.Spieler.Gold+"";
        
        ArgsFest[3] = c.spiel.SpielLevel+"";
        ArgsFest[4] = c.spiel.Welle+"";
        ArgsFest[5] = c.spiel.Wellengegner+"";
        ArgsFest[6] = c.spiel.GoldBekommen+"";
        ArgsFest[7] = c.spiel.AnzPfeil+"";
        ArgsFest[8] = c.spiel.AnzArmbr+"";
        ArgsFest[9] = c.spiel.AnzKanone+"";
        ArgsFest[10] = c.spiel.TeurerUm+"";
        ArgsFest[11] = c.spiel.Wellen.Wz+"";
        
        int anzTuerme = 0;
        
        for(int i = 0; i<c.spiel.AnzTuerme; i++){ if(c.spiel.Turmplaetze[i] != null){anzTuerme++;} };
        
        int anzVariablenTuerme = c.spiel.AnzTuerme*2 + anzTuerme*10;
        
        String[] ArgsTuerme = new String[anzVariablenTuerme];
        int TuermeGespeichert = 0;
        
        for(int i = 0; i < anzVariablenTuerme && TuermeGespeichert <= c.spiel.AnzTuerme; i = i+2)
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
                
                i = i + 10;
            }
            else
            {
                ArgsTuerme[i] = TuermeGespeichert + "";
                ArgsTuerme[i+1] = false+"";
            }
            TuermeGespeichert++;
        }
        
        ArgsFest[0] = anzVariablenTuerme + "";
        
        String Args[] = new String[12+anzVariablenTuerme];
        
        for(int i = 0; i<12; i++)
        {
            Args[i] = ArgsFest[i];
        }
        for(int i = 12; i<12+anzVariablenTuerme; i++)
        {
            Args[i] = ArgsTuerme[i-12];
        }
        
        SPEICHERN.main(c.start.Spielername+"-save", Args);
        
    }
    
    public static boolean Laden(CONTROLER c_p)
    {
        CONTROLER c = c_p;
        
        String[] Args = LADEN.main(c.start.Spielername, "CONTROLER");
        
        if(Args[2] == null){return false;}
        
        c.spiel = new SPIEL(c, TzZ(Args[3]));
        
        c.spiel.Spieler.Leben = TzZ(Args[1]);
        c.spiel.Spieler.Gold = TzZ(Args[2]);
        c.spiel.Welle = TzZ(Args[4]);
        c.spiel.Wellengegner = TzZ(Args[5]);
        c.spiel.GoldBekommen = TzB(Args[6]);
        c.spiel.AnzPfeil = TzZ(Args[7]);
        c.spiel.AnzArmbr = TzZ(Args[8]);
        c.spiel.AnzKanone = TzZ(Args[9]);
        c.spiel.TeurerUm = TzZ(Args[10]);
        c.spiel.Wellen.Wz = TzZ(Args[11]);
        
        
        int i = 12;
        
        for(int nTuerme = 0; nTuerme < c.spiel.AnzTuerme; nTuerme++)
        {
            if(TzB(Args[i + 1]))
            {
                c.spiel.Turmplaetze[nTuerme] = new TURMPLATZ(Args[i+2], TzZ(Args[i+8]), TzZ(Args[i+9]));
                c.spiel.Turmplaetze[nTuerme].Wert = TzZ(Args[i+3]);
                c.spiel.Turmplaetze[nTuerme].UpgradeKosten = TzZ(Args[i+4]);
                c.spiel.Turmplaetze[nTuerme].Angriff = TzZ(Args[i+5]);
                c.spiel.Turmplaetze[nTuerme].Reichweite = TzZ(Args[i+6]);
                c.spiel.Turmplaetze[nTuerme].Nachladezeit = TzZ(Args[i+7]);
                c.spiel.Turmplaetze[nTuerme].Flaeche = TzZ(Args[i+10]);
                c.spiel.Turmplaetze[nTuerme].Level = TzZ(Args[i+11]);
                i = i + 12;
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
        
        return true;
    }
    
    
    //Sonstige Methoden
    private static int TzZ(String Text) //Text zu Zahl
    {
        for(int i = -1; i <= 2000; i++)
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