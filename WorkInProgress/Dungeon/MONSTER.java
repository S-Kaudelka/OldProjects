public class MONSTER
{
    public String Art;
    public String Name;
    public BILD Skin;
    public int Angriff;
    public int Leben;
    public int Wert;
    public int Ausweichen;
    public int Crit;
    public int Effekt;
    public int Dauer;
    public int Prozent;
    public int U;
    public int R;

    //fuer bewegen
    public boolean bewegt;
    private boolean links;
    private boolean rechts;
    private boolean oben;
    private boolean unten;

    public MONSTER(String Name_p, int u_p, int r_p)
    {
        Name = Name_p;
        U = u_p;
        R = r_p;
        Monster();
    }

    private void Monster()
    {
        if(Name.equals("Skelett"))
        {
            Skin = new BILD("m"+Name+".png");
            Skin.TransparenzSetzen(255, 255, 255);
            Skin.SetzeX(R*40);
            Skin.SetzeY(U*40);
            Angriff = 3;
            Leben = 30;
            Ausweichen = 10;
            Crit = 8;
            Wert = 30;
        }
        if(Name.equals("Rauch"))
        {
            Skin = new BILD("m"+Name+".png");
            Skin.TransparenzSetzen(255, 255, 255);
            Skin.SetzeX(R*40);
            Skin.SetzeY(U*40);
            Art = "Feuer";
            Angriff = 3;
            Leben = 17;
            Ausweichen = 30;
            Crit = 5;
            Effekt = 1;
            Dauer = 3;
            Prozent = 35;
            Wert = 40;
        }
        if(Name.equals("Adept"))
        {
            Skin = new BILD("m"+Name+".png");
            Skin.TransparenzSetzen(255, 255, 255);
            Skin.SetzeX(R*40);
            Skin.SetzeY(U*40);
            Angriff = 5;
            Leben = 22;
            Ausweichen = 5;
            Crit = 10;
            Wert = 50;
        }
        if(Name.equals("Nebel"))
        {
            Skin = new BILD("m"+Name+".png");
            Skin.TransparenzSetzen(255, 255, 255);
            Skin.SetzeX(R*40);
            Skin.SetzeY(U*40);
            Art = "Kaelte";
            Angriff = 2;
            Leben = 17;
            Ausweichen = 30;
            Crit = 5;
            Effekt = 1;
            Dauer = 4;
            Prozent = 45;
            Wert = 40;
        }
    }

    public boolean KI(SPIEL s, int u, int r)
    {
        if(GegnerPruefen(s.gegner, u, r))
        {
            return true;
        }
        int anzWege = WegePruefen(s, u, r);
        if(anzWege == 0)
        {
            return false;
        }
        if(anzWege == 1)
        {
            if(oben){Bewegen("oben", s, u, r);}
            if(links){Bewegen("links", s, u, r);}
            if(unten){Bewegen("unten", s, u, r);}
            if(rechts){Bewegen("rechts", s, u, r);}
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
            if(z == 0){Bewegen(a, s, u, r);}
            if(z == 1){Bewegen(b, s, u, r);}
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
            if(z == 0){Bewegen(a, s, u, r);}
            if(z == 1){Bewegen(b, s, u, r);}
            if(z == 2){Bewegen(c, s, u, r);}
        }
        if(anzWege == 4)
        {
            int z = (int)(Math.random()*4);
            if(z == 0){Bewegen("oben", s, u, r);}
            if(z == 1){Bewegen("links", s, u, r);}
            if(z == 2){Bewegen("unten", s, u, r);}
            if(z == 3){Bewegen("rechts", s, u, r);}
        }

        oben = false;
        links = false;
        unten = false;
        rechts = false;

        return false;
    }
    
    private void Bewegen(String richtung, SPIEL s, int u, int r)
    {
        if(richtung.equals("oben"))
        {Skin.SetzeY((u-1)*40); s.Monster[u][r] = null; s.Monster[u-1][r] = this; bewegt = true;}
        if(richtung.equals("links"))
        {Skin.SetzeX((r-1)*40); s.Monster[u][r] = null; s.Monster[u][r-1] = this; bewegt = true;}
        if(richtung.equals("unten"))
        {Skin.SetzeY((u+1)*40); s.Monster[u][r] = null; s.Monster[u+1][r] = this; bewegt = true;}
        if(richtung.equals("rechts"))
        {Skin.SetzeX((r+1)*40); s.Monster[u][r] = null; s.Monster[u][r+1] = this; bewegt = true;}
    }

    public boolean GegnerPruefen(GEGNER g, int u, int r)
    {
        int dU = g.U - u;
        int dR = g.R - r;
        if(dU == 0)
        {
            if(dR == -1 || dR == 1)
            {
                return true;
            }
        }
        if(dR == 0)
        {
            if(dU == -1 || dU == 1)
            {
                return true;
            }
        }

        return false;
    }

    public int WegePruefen(SPIEL s, int u, int r)
    {
        int anz = 0;
        if(r > 0)
        {
            if(s.Felder[u][r-1].Skin.LeseName().equals("fLeer.png") && (s.Felder[u][r-1].Zustand.equals("Leer")
               || s.Felder[u][r-1].Zustand.equals("Monster")) && s.Monster[u][r-1] == null)
            {
                anz++;
                links = true;
            }
        }
        if(r < SPIEL.Seite-1)
        {
            if(s.Felder[u][r+1].Skin.LeseName().equals("fLeer.png") && (s.Felder[u][r+1].Zustand.equals("Leer")
               || s.Felder[u][r+1].Zustand.equals("Monster")) && s.Monster[u][r+1] == null)
            {
                anz++;
                rechts = true;
            }
        }
        if(u > 0)
        {
            if(s.Felder[u-1][r].Skin.LeseName().equals("fLeer.png") && (s.Felder[u-1][r].Zustand.equals("Leer")
               || s.Felder[u-1][r].Zustand.equals("Monster")) && s.Monster[u-1][r] == null)
            {
                anz++;
                oben = true;
            }
        }
        if(u < SPIEL.Seite-1)
        {
            if(s.Felder[u+1][r].Skin.LeseName().equals("fLeer.png") && (s.Felder[u+1][r].Zustand.equals("Leer")
               || s.Felder[u+1][r].Zustand.equals("Monster")) && s.Monster[u+1][r] == null)
            {
                anz++;
                unten = true;
            }
        }
        return anz;
    }
}