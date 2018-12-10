public class SPIELER
{
    public BILD Skin;

    public static int Leben;
    public int Wurfdelay;
    private int Schaden;
    private int Geschwindigkeit;
    public static int Score;
    
    public int Level;

    private String RichtungH;
    public int Richtung;
    public Schneeball[] b;
    
    public int zeitVergangen;
    
    public String Name;
    
    public SPIELER()
    {
        if(CONTROLER.spezialFeature)
        {
            Name = "spieler2";
        }
        else
        {
            Name = "spieler";
        }
        Skin = new BILD("Grafiken\\"+Name+".png");
        Skin.TransparenzSetzen(255, 255, 255);
        Skin.SetzeBreite(Skin.LeseBreite()/2);
        Skin.SetzeHoehe(Skin.LeseHoehe()/2);
        Skin.SetzeX(GAMEWINDOW.Breite/2-Skin.LeseBreite()/2);
        Skin.SetzeY(GAMEWINDOW.Hoehe/2-Skin.LeseHoehe()/2);

        Leben = 3;
        Wurfdelay = 500;
        Schaden = 1;
        Geschwindigkeit = 4;
        Score = 0;
        b = new Schneeball[20];
        zeitVergangen=Wurfdelay;
        Level = 1;
    }
    
    public void Bewegen(int r) //Überprüfung welche Richtung gegeben wurde und anschließende Bewegung des "Spielers" in die entsprechende Richtung
    {
        if(r == 1){ BewTeil(0, -1);} //Oben
        if(r == 2){ BewTeil(1, -1);} //Oben Rechts
        if(r == 3){ BewTeil(1, 0);} //Rechts
        if(r == 4){ BewTeil(1, 1);} //Rechts Unten
        if(r == 5){ BewTeil(0, 1);} //Unten
        if(r == 6){ BewTeil(-1, 1);} //Unten Links
        if(r == 7){ BewTeil(-1, 0);} //Links
        if(r == 8){ BewTeil(-1, -1);} //Links Oben
    }
    
    public void BewTeil(int x, int y) // x,y => -1 || 0 || 1 ; geben bewegung an als Vorfaktor
    {
        int bewX = Geschwindigkeit*x;
        int bewY = Geschwindigkeit*y;
        if(! VerlaesstBildschirm(bewX, bewY))
        {
            Skin.SetzeX(Skin.LeseX()+bewX);
            Skin.SetzeY(Skin.LeseY()+bewY);
        }
        
        if(x == 0 || x == 1)
        {
            if((!Skin.LeseName().equals("Grafiken\\"+Name+".png")))
            {
                SkinAendern("Grafiken\\"+Name+".png");
            }
        }
        if(x == -1)
        {
            if(!(Skin.LeseName().equals("Grafiken\\"+Name+"_L.png")))
            {
                SkinAendern("Grafiken\\"+Name+"_L.png");
            }
        }
    }
    
    public boolean VerlaesstBildschirm(int bewx, int bewy) //ob der spieler mit den Bewegungen den Bildschirm verlaesst
    {
        int x = Skin.LeseX()+bewx;
        int y = Skin.LeseY()+bewy;
        if(x<0 || x>GAMEWINDOW.Breite-Skin.LeseBreite() || y<-20 || y>GAMEWINDOW.Hoehe-Skin.LeseHoehe())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void Werfen(int R)
    {
        if(zeitVergangen>=Wurfdelay)    // Überprüfen ob der Timer abgelaufen ist, d.h. ob der Spieler wieder wurfbereit ist
        {
            if(RichtungH == "L")    // wenn sich der Spieler nach links bewegt wird die entsprechende Werfgrafik eingefügt 
            {
                SkinAendern("Grafiken\\"+Name+"_wirft_L.png");
                for(int i=0; i<20;i++)  
                {
                    if(b[i] ==null) //Überprüfung für alle Elemente ob das aktuelle Element = null ist
                    {
                        b[i] = new Schneeball(Skin.LeseX()+Skin.LeseBreite()/2, Skin.LeseY()+Skin.LeseHoehe()/2,R);
                        zeitVergangen=0;     //Timer wird resetet => Wurfverzögerung
                        return;     //Erzeugung eines neuen Schneeballbildes und dieses bewegen
                    }
                }

                for(int i=0; i<20;i++) // ist das Feld voll werden alle Elemente gelöschet und anschließend beim 0te Element ein neues Schneeballbild erzeugt und bewegt
                {
                    b[i] = null;
                }

                b[0] = new Schneeball(Skin.LeseX()+Skin.LeseBreite()/2, Skin.LeseY()+Skin.LeseHoehe()/2,R);
                zeitVergangen=0;     //Timer wird resetet => Wurfverzögerung
            }
            else     // wenn sich der Spieler nach rechts bewegt wird die entsprechende Werfgrafik eingefügt 
            {
                SkinAendern("Grafiken\\"+Name+"_wirft.png");
                for(int i=0; i<20;i++)
                {
                    if(b[i] ==null)      //Überprüfung für alle Elemente ob das aktuelle Element = null ist
                    {
                        b[i] = new Schneeball(Skin.LeseX()+Skin.LeseBreite()/2, Skin.LeseY()+Skin.LeseHoehe()/2,R);
                        zeitVergangen=0;    //Timer wird resetet => Wurfverzögerung
                        return;     //Erzeugung eines neuen Schneeballbildes und dieses bewegen
                    }
                }

                for(int i=0; i<20;i++)  // ist das Feld voll werden alle Elemente gelöschet und anschließend beim 0te Element ein neues Schneeballbild erzeugt und bewegt
                {
                    b[i] = null;
                }

                b[0] = new Schneeball(Skin.LeseX()+Skin.LeseBreite()/2, Skin.LeseY()+Skin.LeseHoehe()/2,R);
                zeitVergangen=0;     //Timer wird resetet => Wurfverzögerung
            }

        }
    }

    public void SkinAendern(String g)
    {
        Skin.SichtbarSetzen(false);
        int x = Skin.LeseX();
        int y = Skin.LeseY();
        int h = Skin.LeseHoehe();
        int b = Skin.LeseBreite();
        Skin = new BILD (g);
        Skin.SichtbarSetzen(false);
        Skin.SetzeX(x);
        Skin.SetzeY(y);
        Skin.SetzeBreite(b);
        Skin.SetzeHoehe(h);
        Skin.TransparenzSetzen(255, 255, 255);
        Skin.SichtbarSetzen(true);
    }

    public int XGeben()
    {
        return Skin.LeseY();
    }

    public int YGeben()
    {
        return Skin.LeseY();
    }

    public int LeseSchaden()
    {
        return Schaden;
    }
}

