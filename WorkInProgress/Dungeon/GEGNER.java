public class GEGNER
{
    public String Art;
    public String Name;
    public BILD Skin;
    public int Angriff;
    public int Leben;
    public int Ausweichen;
    public int Crit;
    public int BrennenDmg;
    public int BrennenZeit;
    public int KaelteWert;
    public int KaelteZeit;
    public int FAusweichen; // fuer Fallen
    public int Zerstoeren;  //  ""     ""
    public double BelohnungMax; // > 1
    public int U;
    public int R;
    public boolean Intelligent;
    public String Hand = "links";
    private boolean links;
    private boolean rechts;
    private boolean oben;
    private boolean unten;
    public boolean hatGold;

    public GEGNER(int Gold)
    {
        Art = "Gegner";
        U = 0;
        R = 10;
        int N = 0;
        if(Gold <  10){N = 0;}
        if(Gold == 10){N = 1;}
        GegnerMachen(N);
    }

    /*
     * n = nummer des Gegners; haengt von Gold ab, teilweise zufaellig
     * n:   0   Bauer (Bestrafung)
     *      1   Bauer (Normal)
     */
    private void GegnerMachen(int n)
    {
        if(n == 0)
        {
            Name = "Bauer";
            Skin = new BILD("g"+Name+".png");
            Skin.SetzeX(400);
            Skin.TransparenzSetzen(255, 255, 255);
            Angriff = 6;
            Leben = 13;
            Ausweichen = 20;
            Crit = 15;
            FAusweichen = 15;
            Zerstoeren = 5;
            BelohnungMax = 1.2;
            Intelligent = true;
        }
        if(n == 1)
        {
            Name = "Bauer";
            Skin = new BILD("g"+Name+".png");
            Skin.SetzeX(400);
            Skin.TransparenzSetzen(255, 255, 255);
            Angriff = 5;
            Leben = 17;
            Ausweichen = 9;
            Crit = 5;
            FAusweichen = 5;
            Zerstoeren = 1;
            BelohnungMax = 1.9;
            Intelligent = false;
        }
    }

    public void KI(SPIEL s) //KI abhaengig von art: intelligent ja/nein
    {
        if(Intelligent)//"mit der rechten hand an der wand bleiben"
        {
            if(MonsterPruefen(s.Monster))
            {
                return;
            }
            WegRechts(s.Felder);
        }
        else //"zufaellig"
        {
            if(MonsterPruefen(s.Monster))
            {
                return;
            }
            ZufaelligGehen(s);
        }
    }

    private boolean MonsterPruefen(MONSTER[][] Feld)
    {
        if(R > 0)
        {
            if(Feld[U][R-1] != null)
            {
                return true;
            }
        }
        if(R < SPIEL.Seite-1)
        {
            if(Feld[R][U+1] != null)
            {
                return true;
            }
        }
        if(U > 0)
        {
            if(Feld[U-1][R] != null)
            {
                return true;
            }
        }
        if(U < SPIEL.Seite-1)
        {
            if(Feld[U+1][R] != null)
            {
                return true;
            }
        }

        return false;
    }

    private void WegRechts(FELD[][] Feld)
    {
        if(Hand.equals("links"))
        {
            if(R > 0 && !Feld[U][R-1].Zustand.equals("Wand"))
            {
                LinksGehen();
                return;
            }
            if(U < SPIEL.Seite-1 && !Feld[U+1][R].Zustand.equals("Wand"))
            {
                UntenGehen();
                return;
            }
            if(R < SPIEL.Seite-1 && !Feld[U][R+1].Zustand.equals("Wand"))
            {
                RechtsGehen();
                return;
            }
            if(U > 0 && !Feld[U-1][R].Zustand.equals("Wand"))
            {
                ObenGehen();
                return;
            }
        }
        if(Hand.equals("unten"))
        {
            if(U < SPIEL.Seite-1 && !Feld[U+1][R].Zustand.equals("Wand"))
            {
                UntenGehen();
                return;
            }
            if(R < SPIEL.Seite-1 && !Feld[U][R+1].Zustand.equals("Wand"))
            {
                RechtsGehen();
                return;
            }
            if(U > 0 && !Feld[U-1][R].Zustand.equals("Wand"))
            {
                ObenGehen();
                return;
            }
            if(R > 0 && !Feld[U][R-1].Zustand.equals("Wand"))
            {
                LinksGehen();
                return;
            }
        }
        if(Hand.equals("rechts"))
        {
            if(R < SPIEL.Seite-1 && !Feld[U][R+1].Zustand.equals("Wand"))
            {
                RechtsGehen();
                return;
            }
            if(U > 0 && !Feld[U-1][R].Zustand.equals("Wand"))
            {
                ObenGehen();
                return;
            }
            if(R > 0 && !Feld[U][R-1].Zustand.equals("Wand"))
            {
                LinksGehen();
                return;
            }
            if(U < SPIEL.Seite-1 && !Feld[U+1][R].Zustand.equals("Wand"))
            {
                UntenGehen();
                return;
            }
        }
        if(Hand.equals("oben"))
        {
            if(U > 0 && !Feld[U-1][R].Zustand.equals("Wand"))
            {
                ObenGehen();
                return;
            }
            if(R > 0 && !Feld[U][R-1].Zustand.equals("Wand"))
            {
                LinksGehen();
                return;
            }
            if(U < SPIEL.Seite-1 && !Feld[U+1][R].Zustand.equals("Wand"))
            {
                UntenGehen();
                return;
            }
            if(R < SPIEL.Seite-1 && !Feld[U][R+1].Zustand.equals("Wand"))
            {
                RechtsGehen();
                return;
            }
        }
    }

    private void ObenGehen()
    {
        U--;
        Skin.SetzeY(Skin.LeseY()-40);
        Hand = "rechts";
    }

    private void LinksGehen()
    {
        R--;
        Skin.SetzeX(Skin.LeseX()-40);
        Hand = "oben";
    }

    private void UntenGehen()
    {
        U++;
        Skin.SetzeY(Skin.LeseY()+40);
        Hand = "links";
    }

    private void RechtsGehen()
    {
        R++;
        Skin.SetzeX(Skin.LeseX()+40);
        Hand = "unten";
    }
    
    private void ZufaelligGehen(SPIEL s)
    {
        int anzWege = AnzWege(s);
        if(anzWege == 0)
        {
            return;
        }
        if(anzWege == 1)
        {
            if(oben){Bewegen("oben");}
            if(links){Bewegen("links");}
            if(unten){Bewegen("unten");}
            if(rechts){Bewegen("rechts");}
        }
        if(anzWege == 2)
        {
            String a = ""; String b = "";
            if(!oben)
            {
                if(!links){a = "unten"; b = "rechts";}
                if(!unten){a = "links"; b = "rechts";}
                if(!rechts){a = "unten"; b = "links";}
            }
            if(!links)
            {
                if(!unten){a = "oben"; b = "rechts";}
                if(!rechts){a = "unten"; b = "oben";}
            }
            if(!unten)
            {
                if(!rechts){a = "oben"; b = "links";}
            }
            int z = (int)(Math.random()*2);
            if(z == 0){Bewegen(a);}
            if(z == 1){Bewegen(b);}
        }
        if(anzWege == 3)
        {
            String a = "oben";String b = "links";String c = "unten"; // = !rechts
            if(!oben)
            {
                a = "links"; b = "unten"; c = "rechts";
            }
            if(!links)
            {
                a = "unten"; b = "rechts"; c = "oben";
            }
            if(!unten)
            {
                a = "rechts"; b = "oben"; c = "links";
            }
            int z = (int)(Math.random()*3);
            if(z == 0){Bewegen(a);}
            if(z == 1){Bewegen(b);}
            if(z == 2){Bewegen(c);}
        }
        if(anzWege == 4)
        {
            int z = (int)(Math.random()*4);
            if(z == 0){Bewegen("oben");}
            if(z == 1){Bewegen("links");}
            if(z == 2){Bewegen("unten");}
            if(z == 3){Bewegen("rechts");}
        }

        oben = false;
        links = false;
        unten = false;
        rechts = false;
    }
    
    private void Bewegen(String richtung)
    {
        if(richtung.equals("oben")){U--;Skin.SetzeY(U*40);}
        if(richtung.equals("links")){R--;Skin.SetzeX(R*40);}
        if(richtung.equals("unten")){U++;Skin.SetzeY(U*40);}
        if(richtung.equals("rechts")){R++;Skin.SetzeX(R*40);}
    }
    
    private int AnzWege(SPIEL s)
    {
        int anz = 0;
        if(R > 0)
        {
            if(!s.Felder[U][R-1].Zustand.equals("Wand"))
            {
                anz++;
                links = true;
            }
        }
        if(R < SPIEL.Seite-1)
        {
            if(!s.Felder[U][R+1].Zustand.equals("Wand"))
            {
                anz++;
                rechts = true;
            }
        }
        if(U > 0)
        {
            if(!s.Felder[U-1][R].Zustand.equals("Wand"))
            {
                anz++;
                oben = true;
            }
        }
        if(U < SPIEL.Seite-1)
        {
            if(!s.Felder[U+1][R].Zustand.equals("Wand"))
            {
                anz++;
                unten = true;
            }
        }
        return anz;
    }
}